package com.cris.cmsm.models;

/**
 *
 */
public class Month {

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getMonthCode() {
        return monthCode;
    }

    public void setMonthCode(String monthCode) {
        this.monthCode = monthCode;
    }

    private String monthName;
    private String monthCode;


    public Month(String monthName, String monthCode) {
        this.monthName = monthName;
        this.monthCode = monthCode;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private boolean isCheck = true;
}
