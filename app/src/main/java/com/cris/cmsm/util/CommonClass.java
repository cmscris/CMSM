package com.cris.cmsm.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.cmsm.R;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Designation;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.KeyValue;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.models.response.SebMaster;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class CommonClass {
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    Context context;
    private ProgressDialog progressDialog;

    public CommonClass(Context context) {
        this.context = context;
    }



    public void showProgressBar(String msg) {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    public boolean checkProgressState() {
        boolean isShowing = false;
        if (progressDialog != null && progressDialog.isShowing())
            isShowing = true;
        return isShowing;
    }


    public static void goToNextScreen(Activity activity, Class<?> destination, boolean isAnim, boolean isFinish) {
        activity.startActivity(new Intent(activity, destination));
        if (isAnim)
            activity.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        if (isFinish)
            activity.finish();

    }


    public static void goToNextScreen(Activity activity, Class<?> destination, boolean isAnim, int value) {
        Intent i = new Intent(activity, destination);
        i.putExtra(Constants.KEY, value);
        activity.startActivity(i);
        if (isAnim)
            activity.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);

    }

    public static void goToNextScreen(Activity activity, Class<?> destination, boolean isAnim, String value) {
        Intent i = new Intent(activity, destination);
        i.putExtra(Constants.STR_PARAM, value);
        activity.startActivity(i);
        if (isAnim)
            activity.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);

    }

    public static void goToNextScreen(Activity activity, Class<?> destination, boolean isAnim, String pageTitle, String value) {
        Intent i = new Intent(activity, destination);
        i.putExtra(Constants.PAGE_TITLE, pageTitle);
        i.putExtra(Constants.PAGE_URL, value);
        activity.startActivity(i);
        if (isAnim)
            activity.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);

    }


    public static void goToHome(Activity activity, Class<?> destination, boolean isAnim) {
        Intent i = new Intent(activity, destination);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
        activity.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        activity.finish();

    }

    public void showToast(String msg) {
        try {
            Typeface font = new FontFamily(context).getRegular();
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.toast, null);
            TextView text = layout.findViewById(R.id.text);
            text.setText(msg);
            text.setTypeface(font);
            Toast toast = new Toast(context);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        } catch (Exception e) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }


    public static List<Designation> getDesignationList() {
        List<Designation> desigList = new ArrayList<>();
        desigList.add(new Designation("All Crew", ""));
        desigList.add(new Designation("LP", "LP"));
        desigList.add(new Designation("ALP", "ALP"));
        desigList.add(new Designation("GUARD", "GUARD"));

        return desigList;
    }



    public static List<Month> getMonthList() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month("All Months", ""));
        monthList.add(new Month("April", "04"));
        monthList.add(new Month("May", "05"));
        monthList.add(new Month("June", "06"));
        monthList.add(new Month("July", "07"));
        monthList.add(new Month("August", "08"));
        monthList.add(new Month("September", "09"));
        monthList.add(new Month("October", "10"));
        monthList.add(new Month("November", "11"));
        monthList.add(new Month("December", "12"));
        monthList.add(new Month("January", "01"));
        monthList.add(new Month("February", "02"));
        monthList.add(new Month("March", "03"));
        return monthList;
    }

    public static String currentMonth(String month) {
        List<Month> monthList = getMonthList();
        monthList.remove(0);
        String selectedMonth = "";
        for (Month mon :
                monthList) {
            if (mon.getMonthCode().equals(month)) {
                selectedMonth = mon.getMonthName();
                break;
            }
        }
        return selectedMonth;
    }


    public static List<Month> getMonthsList() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month("April", "04"));
        monthList.add(new Month("May", "05"));
        monthList.add(new Month("June", "06"));
        monthList.add(new Month("July", "07"));
        monthList.add(new Month("August", "08"));
        monthList.add(new Month("September", "09"));
        monthList.add(new Month("October", "10"));
        monthList.add(new Month("November", "11"));
        monthList.add(new Month("December", "12"));
        monthList.add(new Month("January", "01"));
        monthList.add(new Month("February", "02"));
        monthList.add(new Month("March", "03"));
        return monthList;
    }

    public static List<KeyValue> getNoOfYear() {
        List<KeyValue> keyValueList = new ArrayList<>();
        keyValueList.add(new KeyValue("No. of year", ""));
        keyValueList.add(new KeyValue("One", "1"));
        keyValueList.add(new KeyValue("Two", "2"));
        keyValueList.add(new KeyValue("Three", "3"));
        keyValueList.add(new KeyValue("Four", "4"));
        keyValueList.add(new KeyValue("Five", "5"));
        keyValueList.add(new KeyValue("Six", "6"));
        keyValueList.add(new KeyValue("Seven", "7"));
        keyValueList.add(new KeyValue("Eight", "8"));
        keyValueList.add(new KeyValue("Nine", "9"));
        keyValueList.add(new KeyValue("Ten", "10"));
        return keyValueList;
    }


    public static List<Department> getDepartmentList(String authId, String categoryId, boolean isBothVisible) {
        List<Department> departmentList = new ArrayList<>();
        if (categoryId != null) {
            if (categoryId.equals(Constants.CATEGORY_BOTH) || (authId.equals(Constants.AUTH_BOARD) && categoryId.equals(Constants.CATEGORY_BOARD))) {
                if (isBothVisible)
                    departmentList.add(new Department("BOTH SERVICES", ""));
                departmentList.add(new Department("GENERAL SERVICES", "GEN"));
                departmentList.add(new Department("TRACTION", "TRD"));
            } else if (categoryId.equals(Constants.CATEGORY_GENERAL_SERVICE) || categoryId.equals(Constants.CATEGORY_WORKSHOP)) {
                departmentList.add(new Department("GENERAL SERVICES", "GEN"));
            } else if (categoryId.equals(Constants.CATEGORY_TRANSACTION)) {
                departmentList.add(new Department("TRACTION", "TRD"));
            }
        }
        return departmentList;
    }


    public static List<Department> getAssetsReportList() {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(new Department("Select Assets Report Type", ""));
        departmentList.add(new Department("Position of Escalators", "A21"));
        departmentList.add(new Department("Position of Lift", "A22"));
        departmentList.add(new Department("Position of Solar PV Panel at Office Building", "A26"));
        departmentList.add(new Department("Position of Solar PV Panel at Stations", "A31"));
        return departmentList;
    }


    public static List<String> getTractionType() {
        List<String> tractionTypeList = new ArrayList<>();
        tractionTypeList.add("ELEC");
        tractionTypeList.add("DSL");
        return tractionTypeList;
    }


    public static List<String> getFinancialYear() {
        List<String> fYearList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = 2012; i < year + 1; i++) {
            fYearList.add(0, String.valueOf(i) + "-" + String.valueOf(i + 1));
        }
        int month = c.get(Calendar.MONTH);
        if (month + 1 < 4) {
            fYearList.remove(0);
        }
        return fYearList;
    }

    public static List<String> getYear() {
        List<String> fYearList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = 2012; i <= year; i++) {
            fYearList.add(0, String.valueOf(i));
        }

        int month = c.get(Calendar.MONTH);
        if (month + 1 < 4) {
            fYearList.remove(0);
        }
        fYearList.add(0, "Select Year");
        return fYearList;
    }

/*

    public static List<String> getReportYear() {
        List<String> fYearList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = 2012; i < year + 1; i++) {
            fYearList.add(0, String.valueOf(i));
        }
        return fYearList;
    }
*/


    public static List<String> getSubYear() {
        List<String> fYearList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = 2012; i < year + 1; i++) {
            fYearList.add(0, String.valueOf(i));
        }

        fYearList.add(0, "Select Year");
        return fYearList;
    }

    public int getYearIndex(String year) {
        String currentYear = "";
        if (!TextUtils.isEmpty(year)) {
            currentYear = "" + Integer.valueOf(year);
        }
        int index = 0;
        List<String> fYearList = getSubYear();
        for (int i = index; i < fYearList.size(); i++) {
            if (fYearList.get(i).equalsIgnoreCase(currentYear)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String encodeURL(String url) {
        return Uri.encode(url,
                ALLOWED_URI_CHARS);
    }


    public int getUserRailwayIndex(List<Railway> list, String ryCode) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRlycode().trim().equals(ryCode)) {
                index = i;
                break;
            }
        }
        return index;
    }


    public int getDivisionIndex(List<Division> list, String divCode) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            Log.d("Division ----", list.get(i).getDivcode() + " ---- " + divCode);
            if (list.get(i).getDivcode().trim().equals(divCode.trim())) {
                Log.d("MATCHED ----", list.get(i).getDivcode());
                index = i;
                break;
            }
        }
        return index;
    }

    public int getLobbyIndex(List<Lobby> list, String divCode) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLobbycode().equals(divCode)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }
        NetworkInfo mobileNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }


    public static void hideKeyBoard(View view, Context context) {
        InputMethodManager inputManager = (InputMethodManager) context.
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static double getRoundOff(String consumption, String billing) {
        double value = Double.valueOf(billing) / Double.valueOf(consumption);
        value = (Math.round(value * 100d) / 100d);
        return value;
    }

    public static double getRoundOff(double value) {
        value = (Math.round(value * 100d) / 100d);
        return value;
    }


    public static String getRoundOff(Object object) {
        String pattern = "###.##";
        String value = "";
        if (object == null) {
            value = "";

        } else if (object instanceof String) {
            try {
                value = (String) object;
                DecimalFormat decimalFormat = new DecimalFormat(pattern);
                decimalFormat.setParseBigDecimal(true);
                value = decimalFormat.parse(value).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (object instanceof Double) {
            Double d = (Double) object;
            DecimalFormat formatter = new DecimalFormat(pattern);
            value = formatter.format(d.doubleValue());
        }
        return value;
    }

    public static final int[] COLORFUL_COLORS = {
            Color.rgb(193, 37, 82),
            Color.rgb(255, 102, 0),
            Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31),
            Color.rgb(179, 100, 53),
            Color.rgb(184, 134, 11),
            Color.rgb(218, 165, 32),
            Color.rgb(189, 183, 107),
            Color.rgb(128, 128, 0),
            Color.rgb(0, 255, 255),
            Color.rgb(0, 139, 139),
            Color.rgb(0, 0, 139),
            Color.rgb(65, 105, 225),
            Color.rgb(128, 0, 128),
            Color.rgb(199, 21, 133),
            Color.rgb(255, 105, 180),
            Color.rgb(205, 133, 63),
            Color.rgb(112, 128, 144),
            Color.rgb(0, 128, 128),
            Color.rgb(139, 0, 0),
            Color.rgb(128, 0, 0),
            Color.rgb(0, 191, 255),
            Color.rgb(0, 139, 139),
            Color.rgb(255, 99, 71),
            Color.rgb(75, 0, 130)
    };

    public static List<String> getComparisonYear() {
        List<String> list = new ArrayList<>();
        list.add("No Of Year.");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        return list;
    }

    public int getSSEIndex(List<ResponseSSERole> sseMasterList, String sseId) {
        int index = 0;
        if (TextUtils.isEmpty(sseId)) {
            return index;
        } else {
            for (int i = 0; i < sseMasterList.size(); i++) {
                if (sseMasterList.get(i).getRoleid().equalsIgnoreCase(sseId)) {
                    index = i;
                    break;
                }
            }
            return index;
        }
    }


    public static int getSEBIndex(List<SebMaster> sseMasterList, String sseId) {
        int index = 0;
        if (TextUtils.isEmpty(sseId)) {
            return index;
        } else {
            for (int i = 0; i < sseMasterList.size(); i++) {
                if (sseMasterList.get(i).getSebCode().equalsIgnoreCase(sseId)) {
                    index = i;
                    break;
                }
            }
            return index;
        }
    }




    public static List<KeyValue> getCrewAvailabilityType() {

        List<KeyValue> keyValueList = new ArrayList<>();
        keyValueList.add(new KeyValue("HQ Crew FIFO", "CrewAvailableFIFO"));
        keyValueList.add(new KeyValue("HQ Crew FAFO", "CrewAvailableFAFO"));
        keyValueList.add(new KeyValue("HQ Crew Prgs Hrs", "CrewAvailablePrg"));
        //keyValueList.add(new KeyValue("HQ Crew at OS", "HQCREWATOS"));
        //keyValueList.add(new KeyValue("OS Crew at HQ", "OSCREWATHQ"));
        keyValueList.add(new KeyValue("All Crew at HQ", "AllCrewatHQ"));


        return keyValueList;
    }


    public static List<KeyValue> getCadre() {

        List<KeyValue> keyValueList = new ArrayList<>();
        keyValueList.add(new KeyValue("All", "E','M','B"));
        keyValueList.add(new KeyValue("Electrical", "E"));
        keyValueList.add(new KeyValue("Mechnical", "M"));

        return keyValueList;
    }


    public static List<KeyValue> getTraction() {

        List<KeyValue> keyValueList = new ArrayList<>();
        keyValueList.add(new KeyValue("All", "ALL"));
        keyValueList.add(new KeyValue("Elec", "ELEC"));
        keyValueList.add(new KeyValue("Dsl", "DSL"));


        return keyValueList;
    }


    public static List<String> getDemoValues(String firstValue) {

        List<String> demoList = new ArrayList<>();
        demoList.add(firstValue);
        demoList.add("Option1");
        demoList.add("Option2");
        demoList.add("Option3");
        demoList.add("Option4");
        demoList.add("Option5");



        return demoList;
    }


    public static List<String> getDemoValid(String firstValue) {

        List<String> demoList = new ArrayList<>();
        demoList.add(firstValue);
        demoList.add("Valid");
        demoList.add("InValid");



        return demoList;
    }

    public static List<String> getDemoWorking(String firstValue) {

        List<String> demoList = new ArrayList<>();
        demoList.add(firstValue);
        demoList.add("Working");
        demoList.add("Not Working");

        return demoList;
    }

    public static List<String> getMovementAuthprity(String firstValue) {

        List<String> demoList = new ArrayList<>();
        demoList.add(firstValue);
        demoList.add("SELF");
        demoList.add("DIV CONTROL");

        return demoList;
    }

    public static List<String> getLiDeparturePurpose(String firstValue) {

        List<String> demoList = new ArrayList<>();
        demoList.add(firstValue);
        demoList.add("Footplate with alloted");
        demoList.add("Not alloted staff");
        demoList.add("Accompany with officers");
        demoList.add("Ambush check");
        demoList.add("Inspection");


        return demoList;
    }

    public static List<String> getServiceType(String firstValue) {

        List<String> demoList = new ArrayList<>();
        demoList.add(firstValue);
        demoList.add("Coach");
        demoList.add("Goods");
        return demoList;
    }



    public static List<String> getDutyType(String firstValue) {

        List<String> demoList = new ArrayList<>();
        demoList.add(firstValue);
        demoList.add("Foot Plate");
        demoList.add("Lobby Duty");
        demoList.add("Ambush Check");
        demoList.add("Class");
        demoList.add("Counselling");
        demoList.add("Yard Duty");
        demoList.add("Joint Duty");
        demoList.add("Enquiry");
        demoList.add("Safety Seminar");
        demoList.add("Inspection");
        demoList.add("Interview With Officer");





        return demoList;
    }



}
