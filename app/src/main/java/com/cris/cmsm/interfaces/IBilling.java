package com.cris.cmsm.interfaces;

import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.response.BillingResponse;

/**
 *
 */
public interface IBilling {

    void showBillingResponse(BillingResponse response);

    void showSpinnerText(Division division);

    void disableControls();
}
