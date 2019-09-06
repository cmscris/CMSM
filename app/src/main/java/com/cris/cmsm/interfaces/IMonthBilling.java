package com.cris.cmsm.interfaces;

import com.cris.cmsm.models.response.ResMonthlyBill;

/**
 * Created by  on 29/10/2016.
 */
public interface IMonthBilling {

    void showBillingResponse(ResMonthlyBill response);

    void disableControls();

}
