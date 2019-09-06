package com.cris.cmsm.models;

/**
 *
 */
public class Department {

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    String departmentName;
    String departmentCode;

    public Department(String departmentName, String departmentCode) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }
}
