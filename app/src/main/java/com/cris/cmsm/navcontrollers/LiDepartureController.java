package com.cris.cmsm.navcontrollers;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.LoginActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.GPSRequest;
import com.cris.cmsm.models.request.LiMovementRequest;
import com.cris.cmsm.models.response.LiMovementVOsResponseNew;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.NearestStationResponse;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.GPSTracker;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.cris.cmsm.util.GPSTracker.MY_PERMISSIONS_REQUEST_LOCATION;

/**
 *
 */

public class LiDepartureController extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView, LocationListener {


    // Get Class Name
    private static String TAG = GPSTracker.class.getName();


    private AppCompatSpinner spn_movt_auth,spn_dutytype,spn_purpose,spn_serviceTyp;
    private EditText et_frm_sttn,et_to_sttn,et_via1, et_via2, et_loco_no,et_train_no,et_remarks;


    private ImageView iv_title_icon;
    private TextView action_bar_title, tv_filters , tv_frm_date, tv_nearest_station, tv_cord;
    private Typeface font;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private LoginIfoVO loginUser;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;


    // FROM DATE RELATED
    String fromDate;
    NumberFormat format;
    // GLOBAL VARIABLES FOR TIME PICKER
    int mHour, mMinute;
    int pickfrmhour,pickfrmmin;
    String Hour, Minute, fromDateTime;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_departure);


        format = new DecimalFormat("00");


        monthlyRequestPresenter = new MonthlyRequestPresenter(LiDepartureController.this);
        requestPresenter = new RequestPresenter(LiDepartureController.this);
        userLoginPreferences = new UserLoginPreferences(LiDepartureController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        loginUser = new UserLoginPreferences(LiDepartureController.this).getLoginUser();
        commonClass = new CommonClass(LiDepartureController.this);
        dataBaseManager = new DataBaseManager(LiDepartureController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(LiDepartureController.this).getBold();



        spn_movt_auth = findViewById(R.id.spn_movt_auth);
        spn_dutytype = findViewById(R.id.spn_dutytype);
        spn_purpose = findViewById(R.id.spn_purpose);
        spn_serviceTyp= findViewById(R.id.spn_serviceTyp);

        tv_frm_date= findViewById(R.id.tv_frm_date);
        tv_cord = findViewById(R.id.tv_cord);
        tv_nearest_station= findViewById(R.id.tv_nearest_station);
        tv_cord.setCompoundDrawablesWithIntrinsicBounds(R.drawable.coordinates, 0, 0, 0);


        et_frm_sttn= findViewById(R.id.et_frm_sttn);
        et_to_sttn= findViewById(R.id.et_to_sttn);
        et_via1= findViewById(R.id.et_via1);
        et_via2= findViewById(R.id.et_via2);
        et_loco_no= findViewById(R.id.et_loco_no);
        et_train_no= findViewById(R.id.et_train_no);
        et_remarks= findViewById(R.id.et_remarks);

        et_frm_sttn.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_to_sttn.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_via1.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_via2.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_loco_no.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_train_no.setFilters(new InputFilter[]{new InputFilter.AllCaps()});



        btn_filter = findViewById(R.id.btn_filter);


        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // TITLE BAR
        action_bar_title = findViewById(R.id.action_bar_title);
        action_bar_title.setTypeface(font);
        action_bar_title.setText("CMS- " + loginInfoModel.getFname());


        // PAGE TITLE
        tv_filters = findViewById(R.id.tv_filters);
        tv_filters.setTypeface(font);
        tv_filters.setText(getResources().getString(R.string.li_departure_title));




        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);




        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_movt_auth.setAdapter(new SpinnerAdapter(LiDepartureController.this, CommonClass.getMovementAuthprity("Select Authority")));
        spn_dutytype.setAdapter(new SpinnerAdapter(LiDepartureController.this, CommonClass.getDemoValues("Select Duty Type")));
        spn_purpose.setAdapter(new SpinnerAdapter(LiDepartureController.this, CommonClass.getLiDeparturePurpose("Select Purpose")));
        spn_serviceTyp.setAdapter(new SpinnerAdapter(LiDepartureController.this, CommonClass.getServiceType("Select Service Type")));



        Bundle extra = getIntent().getExtras();
        if (extra != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            fromDate=extra.getString(Constants.STR_PARAM);
            String currenttime= sdf.format(Calendar.getInstance().getTime());
            tv_frm_date.setText(fromDate + " " + currenttime);

        }


        tv_nearest_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GET THE LATTITUDE & LONGITUDE
                getLocation();

                // CALL WEBSERVICE TO GET THE NEAREST STATION
                callWebService(Constants.GET_NEAREST_STATION_GPS);
            }
        });

        tv_frm_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiemPicker(fromDate);
            }
        });

/*
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGPS();
            }
        });

*/

        // GET THE LATTITUDE & LONGITUDE
        getLocation();

        // CALL WEBSERVICE TO GET THE NEAREST STATION
        callWebService(Constants.GET_NEAREST_STATION_GPS);




    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_filter:

                // CALL WEBSERVICE TO SAVE DEPARTURE DATA

                if (isValid()) {
                    callWebService(Constants.LI_DEPARTURE_DATA);
                }

                break;


            case R.id.iv_title_icon:
                onBackPressed();
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    private void callWebService(int category) {
        try {


            DataHolder.setLevel(0);
            System.out.println(">>>>>>>>>>>>>>>>>>>> Inside web servivce" );

            switch(category)
            {
                case Constants.GET_NEAREST_STATION_GPS :

                    // PREPARE THE REQUEST OBJECT
                    GPSRequest request = new GPSRequest();
                    request.setLatitude(latitude);
                    request.setLongitude(longitude);
                    request.setNoOfStations(1);

                    tv_cord.setText(" " + latitude.substring(0,6) + "  :  " + longitude .substring(0,6));

                    //CALL THE WEB SERVICE
                    requestPresenter.Request(request, "Getting Data", Constants.GET_NEAREST_STATION_GPS);
                    break;


                case Constants.LI_DEPARTURE_DATA :
                    // PREPARE THE REQUEST OBJECT
                    System.out.println(">>>>>>>>>>>>>>>>>>>> Inside LI_DEPARTURE_DATA" + loginInfoModel.getLoginid() );
                    LiMovementRequest liMovementRequest = new LiMovementRequest();

                    liMovementRequest.setLi_id(loginInfoModel.getLoginid());
                    liMovementRequest.setFromLongitude(longitude);
                    liMovementRequest.setFromLatitude(latitude);
                    liMovementRequest.setFromDateTime(tv_frm_date.getText().toString());
                    //liMovementRequest.setNeartestStation(tv_nearest_station.getText().toString());
                    liMovementRequest.setFromStation(et_frm_sttn.getText().toString());
                    liMovementRequest.setToStation(et_to_sttn.getText().toString());
                    liMovementRequest.setVia1((et_via1.getText()== null)?et_via1.getText().toString():"");
                    liMovementRequest.setVia2((et_via2.getText()== null)?et_via2.getText().toString():"");
                    liMovementRequest.setAuthority(spn_movt_auth.getSelectedItem().toString());
                    liMovementRequest.setDutyType(spn_dutytype.getSelectedItem().toString());
                    liMovementRequest.setLocoNo(et_loco_no.getText().toString());
                    liMovementRequest.setTrainNo(et_train_no.getText().toString());
                    liMovementRequest.setPurpose(spn_purpose.getSelectedItem().toString());
                    liMovementRequest.setRemarks(et_remarks.getText().toString());
                    liMovementRequest.setServiceType(spn_serviceTyp.getSelectedItem().toString());

                    System.out.println("Request is " + new Gson().toJson(liMovementRequest));
                    //CALL THE WEB SERVICE
                    requestPresenter.Request(liMovementRequest, "Getting Data", Constants.LI_DEPARTURE_DATA);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void ResponseOk(Object object, int position) {
        switch (position) {

            case Constants.GET_NEAREST_STATION_GPS:
                List<NearestStationResponse> nearestStationResponseList = (List<NearestStationResponse>) object;

                System.out.println("Response is " + new Gson().toJson(nearestStationResponseList));


                if (nearestStationResponseList != null) {

                    // SET THE NEAREST STATION
                    NearestStationResponse nearestStationResponse = nearestStationResponseList.get(0);
                    System.out.println("Response item is " + new Gson().toJson(nearestStationResponse));
                    tv_nearest_station.setText(nearestStationResponse.getStationCode() + " / (" + nearestStationResponse.getDistance() + " KM )");

                } else
                    commonClass.showToast("No data available.");
                break;

            case Constants.LI_DEPARTURE_DATA:
                LiMovementVOsResponseNew liMovementVOsResponseNew = (LiMovementVOsResponseNew) object;

                System.out.println("Response is " + new Gson().toJson(liMovementVOsResponseNew));


                if (liMovementVOsResponseNew != null) {

                    commonClass.showToast(liMovementVOsResponseNew.getMessage());


                } else
                    commonClass.showToast("Failed to save");
                break;




        }
    }



    private boolean isValid() {
        if (et_frm_sttn.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter From Station.");
            return false;
        } else if (et_to_sttn.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter To Station.");
            return false;
        }else if (et_loco_no.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter Loco No.");
            return false;
        }else if (et_train_no.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter train No.");
            return false;
        }else
            return true;
    }

    @Override
    public void Error() {
        commonClass.showToast("Unable to fetch data. Please try again.");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();

    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }





    private void tiemPicker( String fromDate){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        pickfrmhour=hourOfDay;
                        pickfrmmin=minute;
                        Hour=format.format(hourOfDay);
                        Minute=format.format(minute);
                        mHour = hourOfDay;
                        mMinute = minute;
                        fromDateTime= fromDate +" "+Hour + ":" + Minute;
                        tv_frm_date.setText(fromDateTime);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }




    @Override
    public void onBackPressed() {

            //DataHolder.getInstance().setBoardStateModel(null);
            DataHolder.setLevel(DataHolder.getLevel() - 1);

            if(DataHolder.getLevel() < DataHolder.getGlobal_level())
                DataHolder.setLevel(DataHolder.getGlobal_level());

            WebServices.getInstance().cancelAllRequest();
            super.onBackPressed();

    }















    /*
    *
    *
    * GPS RELATED CODE
    *
    *
    *
    *
    * */



    // GPS RELATED VARIABLES
    private LocationManager locationManager;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean isGPSTrackingEnabled = false;
    // Store LocationManager.GPS_PROVIDER or LocationManager.NETWORK_PROVIDER information
    private String provider_info;
    // The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    Location location;
    private String  latitude;
    private String longitude;

    /**
     * Try to get my current location by GPS or Network Provider
     */
    public void getLocation() {

        try {
            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

            //getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            //getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            // Try to get location if you GPS Service is enabled
            if (isGPSEnabled) {
                this.isGPSTrackingEnabled = true;

                Log.d(TAG, "Application use GPS Service");

                /*
                 * This provider determines location using
                 * satellites. Depending on conditions, this provider may take a while to return
                 * a location fix.
                 */

                provider_info = LocationManager.GPS_PROVIDER;

            } else if (isNetworkEnabled) { // Try to get location if you Network Service is enabled
                this.isGPSTrackingEnabled = true;

                Log.d(TAG, "Application use Network State to get GPS coordinates");

                /*
                 * This provider determines location based on
                 * availability of cell tower and WiFi access points. Results are retrieved
                 * by means of a network lookup.
                 */
                provider_info = LocationManager.NETWORK_PROVIDER;

            }


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            // Application can use GPS or Network Provider
            if (!provider_info.isEmpty()) {
                locationManager.requestLocationUpdates(
                        provider_info,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES,
                        this
                );

                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(provider_info);
                    updateGPSCoordinates();
                }
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            Log.e(TAG, "Impossible to connect to LocationManager", e);
        }
    }


    /**
     * Update GPSTracker latitude and longitude
     */
    public void updateGPSCoordinates() {

        Log.d(TAG, "Getting Location");
        if (location != null) {
            Log.d(TAG, "Getting Location NOT NULL");
            latitude = location.getLatitude() + "";
            longitude = location.getLongitude() + "";

            Toast toast=Toast.makeText(getApplicationContext(),latitude + " : " + longitude, Toast.LENGTH_LONG);
            toast.setMargin(50,50);
            toast.show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }


            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

}
