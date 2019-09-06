package com.cris.cmsm.interfaces;

import com.cris.cmsm.models.response.ResMonthlyCons;

/**
 *
 */
public interface IMonthConsumption {

    void showConsumptionResponse(ResMonthlyCons resMonthlyCons);

    void disableControls();

}
