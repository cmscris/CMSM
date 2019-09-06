package com.cris.cmsm.interfaces;

import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.response.ConsumptionResponse;

/**
 *
 */
public interface IConsumption {

    void showConsumptionResponse(ConsumptionResponse response);

    void showSpinnerText(Division division);

    void disableControls();

}
