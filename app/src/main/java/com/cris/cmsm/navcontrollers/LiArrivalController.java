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
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cris.cmsm.BaseActivity;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static com.cris.cmsm.util.GPSTracker.MY_PERMISSIONS_REQUEST_LOCATION;

/**
 *
 */

public class LiArrivalController extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView, LocationListener {


    // Get Class Name
    private static String TAG = GPSTracker.class.getName();


    private AppCompatSpinner spn_dutytype,spn_nearest_station, spn_other_dutytype,spn_serviceTyp;
    private EditText et_to_sttn,et_via1, et_via2, et_loco_no,et_train_no,et_remarks, et_kms;


    private ImageView iv_title_icon;
    private TextView action_bar_title, tv_filters , tv_frm_date, tv_to_date, tv_cord, tv_ref_no, tv_frm_sttn;
    private Typeface font;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private LoginIfoVO loginUser;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;

    private LiMovementRequest liMovementRequest;
    // FROM DATE RELATED
    String toDate;
    NumberFormat format;
    // GLOBAL VARIABLES FOR TIME PICKER
    int mHour, mMinute;
    int pickfrmhour,pickfrmmin;
    String Hour, Minute, toDateTime;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_arrival);


        format = new DecimalFormat("00");


        monthlyRequestPresenter = new MonthlyRequestPresenter(LiArrivalController.this);
        requestPresenter = new RequestPresenter(LiArrivalController.this);
        userLoginPreferences = new UserLoginPreferences(LiArrivalController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        loginUser = new UserLoginPreferences(LiArrivalController.this).getLoginUser();
        commonClass = new CommonClass(LiArrivalController.this);
        dataBaseManager = new DataBaseManager(LiArrivalController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(LiArrivalController.this).getBold();




        spn_dutytype = findViewById(R.id.spn_dutytype);
        //spn_other_dutytype = findViewById(R.id.spn_other_dutytype);
        spn_serviceTyp= findViewById(R.id.spn_serviceTyp);
        spn_nearest_station = findViewById(R.id.spn_nearest_station);


        tv_frm_date = findViewById(R.id.tv_frm_date);
        tv_to_date = findViewById(R.id.tv_to_date);
        tv_cord = findViewById(R.id.tv_cord);
        tv_cord.setCompoundDrawablesWithIntrinsicBounds(R.drawable.coordinates, 0, 0, 0);
        tv_ref_no  = findViewById(R.id.tv_ref_no);
        tv_frm_sttn = findViewById(R.id.tv_frm_sttn);


        et_to_sttn= findViewById(R.id.et_to_sttn);
        et_via1= findViewById(R.id.et_via1);
        et_via2= findViewById(R.id.et_via2);
        et_loco_no= findViewById(R.id.et_loco_no);
        et_train_no= findViewById(R.id.et_train_no);
        et_remarks= findViewById(R.id.et_remarks);
        et_kms = findViewById(R.id.et_kms);

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
        tv_filters.setText(getResources().getString(R.string.li_arrival_title));




        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);




        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_dutytype.setAdapter(new SpinnerAdapter(LiArrivalController.this, CommonClass.getDutyType("Select Duty Type")));
        //spn_other_dutytype.setAdapter(new SpinnerAdapter(LiArrivalController.this, CommonClass.getDemoValues("Select Duty Type")));
        spn_serviceTyp.setAdapter(new SpinnerAdapter(LiArrivalController.this, CommonClass.getServiceType("Select Service Type")));



        Bundle extra = getIntent().getExtras();
        if (extra != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            toDate=extra.getString(Constants.STR_PARAM);
            String currenttime= sdf.format(Calendar.getInstance().getTime());
            tv_to_date.setText(toDate + " " + currenttime);

        }



        tv_to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiemPicker(toDate);
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


        // CALL WEBSERVICE TO GET DEPARTURE DATA
        callWebService(Constants.GET_LI_MOVEMENT_DEPARTURE_DATA);



    }

    @Override
    public void onClick(View v) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>   Clicked");
        switch (v.getId()) {

            case R.id.btn_filter:

                // CALL WEBSERVICE TO SAVE DEPARTURE DATA

                System.out.println(">>>>>>>>>>>>>>>>>>>>>>   Here");
                if (isValid()) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>   Calling");
                    callWebService(Constants.LI_ARRIVAL_DATA);
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
                    request.setNoOfStations(5);

                    //tv_cord.setText(" " + latitude.substring(0,6) + "  :  " + longitude .substring(0,6));
                    tv_cord.setText(" " + latitude + "  :  " + longitude);
                    //CALL THE WEB SERVICE
                    requestPresenter.Request(request, "Fetching nearest stations", Constants.GET_NEAREST_STATION_GPS);
                    break;

                case Constants.GET_LI_MOVEMENT_DEPARTURE_DATA :

                    // PREPARE THE REQUEST OBJECT
                    liMovementRequest = new LiMovementRequest();
                    liMovementRequest.setLi_id(loginInfoModel.getLoginid());

                    // HOW TO PROGRAMATICALLY CHANGE THE SHAPE OF INPUT ELEMENT
                    //et_loco_no.setBackgroundDrawable(ContextCompat.getDrawable(LiArrivalController.this, R.drawable.roundshapeorange));



                    //CALL THE WEB SERVICE
                    requestPresenter.Request(liMovementRequest, "Getting Data", Constants.GET_LI_MOVEMENT_DEPARTURE_DATA);
                    break;



                case Constants.LI_ARRIVAL_DATA :
                    // PREPARE THE REQUEST OBJECT
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>   Called");
                    LiMovementRequest liMovementRequest = new LiMovementRequest();


                    liMovementRequest.setRefNo(tv_ref_no.getText().toString());
                    liMovementRequest.setLi_id(loginInfoModel.getLoginid());
                    liMovementRequest.setToLongitude(longitude);
                    liMovementRequest.setToLatitude(latitude);
                    liMovementRequest.setTodateTime(tv_to_date.getText().toString());
                    liMovementRequest.setToStation(et_to_sttn.getText().toString());
                    liMovementRequest.setVia1((et_via1.getText()== null)?"":et_via1.getText().toString());
                    liMovementRequest.setVia2((et_via2.getText()== null)?"":et_via2.getText().toString());
                    liMovementRequest.setDutyType(spn_dutytype.getSelectedItem().toString());
                    liMovementRequest.setOtherDyty(spn_other_dutytype.getSelectedItem().toString());
                    liMovementRequest.setLocoNo(et_loco_no.getText().toString());
                    liMovementRequest.setTrainNo(et_train_no.getText().toString());
                    liMovementRequest.setRemarks((et_remarks.getText()== null)?"":et_remarks.getText().toString());
                    liMovementRequest.setServiceType(spn_serviceTyp.getSelectedItem().toString());
                    liMovementRequest.setDutyKms((et_kms.getText()== null)?"":et_kms.getText().toString());

                    liMovementRequest.setOtherDyty("Other Duty");


                    System.out.println("My Request is " + new Gson().toJson(liMovementRequest));




                    //CALL THE WEB SERVICE
                    requestPresenter.Request(liMovementRequest, "Getting Data", Constants.LI_ARRIVAL_DATA);
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
                List<String> nearestStations = new ArrayList();

                System.out.println("Response is " + new Gson().toJson(nearestStationResponseList));


                if (nearestStationResponseList != null) {

                    // ARRAYLIST TO HOLD THE NEAREST STATIONS WITH DISTANCE
                    NearestStationResponse nearestStationResponse=null;

                    // POPULATE
                    Iterator<NearestStationResponse> itr = nearestStationResponseList.iterator();
                    while (itr.hasNext())
                    {
                        nearestStationResponse = itr.next();
                        nearestStations.add(nearestStationResponse.getStationCode() + " / (" + nearestStationResponse.getDistance() + " KM )");
                    }
                    // SET ADAPTER
                    spn_nearest_station.setAdapter(new SpinnerAdapter(LiArrivalController.this, nearestStations));

                } else
                    commonClass.showToast("No data available.");
                break;

            case Constants.LI_ARRIVAL_DATA:
                LiMovementVOsResponseNew liMovementVOsResponseNew = (LiMovementVOsResponseNew) object;

                System.out.println("Response is " + new Gson().toJson(liMovementVOsResponseNew));


                if (liMovementVOsResponseNew != null) {

                    commonClass.showToast(liMovementVOsResponseNew.getMessage());


                } else
                    commonClass.showToast("Failed to save");
                break;

            case Constants.GET_LI_MOVEMENT_DEPARTURE_DATA:
                liMovementVOsResponseNew = (LiMovementVOsResponseNew) object;


                if (liMovementVOsResponseNew != null) {
                    liMovementRequest = liMovementVOsResponseNew.getLiMovementDetailsResponse().get(0);
                    System.out.println("Response is " + new Gson().toJson(liMovementVOsResponseNew));

                    tv_ref_no.setText(liMovementRequest.getRefNo());
                    tv_frm_date.setText(liMovementRequest.getFromDateTime());
                    tv_frm_sttn.setText(liMovementRequest.getFromStation());
                    et_to_sttn.setText(liMovementRequest.getToStation());
                    et_via1.setText(liMovementRequest.getVia1());
                    et_via2.setText(liMovementRequest.getVia2());
                    spn_dutytype.setSelection(((SpinnerAdapter)spn_dutytype.getAdapter()).getPosition(liMovementRequest.getDutyType()));
                    //spn_other_dutytype.setSelection(((SpinnerAdapter)spn_other_dutytype.getAdapter()).getPosition(liMovementRequest.getOtherDyty()));
                    et_loco_no.setText(liMovementRequest.getLocoNo());
                    et_train_no.setText(liMovementRequest.getTrainNo());
                    spn_serviceTyp.setSelection(((SpinnerAdapter)spn_serviceTyp.getAdapter()).getPosition(liMovementRequest.getServiceType()));


                } else
                    commonClass.showToast("Failed to get the departure data");
                break;


        }
    }



    private boolean isValid() {
        if (et_to_sttn.getText().toString().trim().isEmpty()) {
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





    private void tiemPicker( String toDate){
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
                        toDateTime= toDate +" "+Hour + ":" + Minute;
                        tv_to_date.setText(toDateTime);

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
