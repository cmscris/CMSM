package com.cris.cmsm.models;

/**
 *
 */
public class Designation {


    String designationName;
    String designationCode;

    public Designation(String designationName, String designationCode) {
        this.designationName = designationName;
        this.designationCode = designationCode;
    }



    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public String getDesignationCode() {
        return designationCode;
    }

    public void setDesignationCode(String designationCode) {
        this.designationCode = designationCode;
    }


}
