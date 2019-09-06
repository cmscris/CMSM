package com.cris.cmsm.models;

/**
 *
 */
public class BoardStateModel {
    public String getRlyCode() {
        return rlyCode;
    }

    public void setRlyCode(String rlyCode) {
        this.rlyCode = rlyCode;
    }

    public int getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(int selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public int getSelectYear() {
        return selectYear;
    }

    public void setSelectYear(int selectYear) {
        this.selectYear = selectYear;
    }

    public int getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(int selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    String rlyCode;
    int selectedMonth;
    int selectYear;
    int selectedDepartment;

}
