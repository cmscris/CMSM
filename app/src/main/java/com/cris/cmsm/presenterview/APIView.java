package com.cris.cmsm.presenterview;

import com.cris.cmsm.models.KeyValue;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.request.AssetManagementModel;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.CrewAvailabilityDetailRequest;
import com.cris.cmsm.models.request.EnergyConsumptionRequest;
import com.cris.cmsm.models.request.FeedbackRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.KeyValueRequest;
import com.cris.cmsm.models.request.LTConnectionRequest;
import com.cris.cmsm.models.request.LoginRequest;
import com.cris.cmsm.models.request.MISReportRequest;
import com.cris.cmsm.models.request.MonthlyConsModel;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SEBRequest;
import com.cris.cmsm.models.request.SSConsumptionRequest;
import com.cris.cmsm.models.request.SaveConsumption;
import com.cris.cmsm.models.request.SubStationConsumption;
import com.cris.cmsm.models.request.SubStationRequest;
import com.cris.cmsm.models.request.SubmitLTConnection;
import com.cris.cmsm.models.response.AbnormalityResponse;
import com.cris.cmsm.models.response.CrewAvailabilityDetailResponse;
import com.cris.cmsm.models.response.KeyValueResponse;
import com.cris.cmsm.models.response.LimMovementSubmitResponse;
import com.cris.cmsm.models.response.Limovementresponse;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.Passwordresponse;
import com.cris.cmsm.models.response.Remarksresponse;
import com.cris.cmsm.models.response.Sectionresponse;
import com.cris.cmsm.models.response.ValidateFromToLocoResponse;
import com.cris.cmsm.models.response.VcdStatusResponse;
import com.cris.cmsm.models.response.Annexure14CRes;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.ConSummaryResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.CrewDetailsResponse;
import com.cris.cmsm.models.response.CrewPositionSummaryResponse;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.cris.cmsm.models.response.FeedbackResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.LoginResponse;
import com.cris.cmsm.models.response.MISReportResponse;
import com.cris.cmsm.models.response.MasterData;
import com.cris.cmsm.models.response.RB1Response;
import com.cris.cmsm.models.response.ResAnnexure1;
import com.cris.cmsm.models.response.ResAnnexure10;
import com.cris.cmsm.models.response.ResAnnexure11;
import com.cris.cmsm.models.response.ResAnnexure14A;
import com.cris.cmsm.models.response.ResAnnexure14B;
import com.cris.cmsm.models.response.ResAnnexure14D;
import com.cris.cmsm.models.response.ResAnnexure3;
import com.cris.cmsm.models.response.ResAnnexure5;
import com.cris.cmsm.models.response.ResAnnexure6;
import com.cris.cmsm.models.response.ResAnnexure7;
import com.cris.cmsm.models.response.ResAnnexure8;
import com.cris.cmsm.models.response.ResAnnexure9;
import com.cris.cmsm.models.response.ResAnnexureRB2;
import com.cris.cmsm.models.response.ResAnnexureRB3;
import com.cris.cmsm.models.response.ResAnnexureRB4;
import com.cris.cmsm.models.response.ResAnnexureRB5;
import com.cris.cmsm.models.response.ResAnnexureRB6;
import com.cris.cmsm.models.response.ResAnnexureRB7;
import com.cris.cmsm.models.response.ResAssetReport;
import com.cris.cmsm.models.response.ResLTConnection;
import com.cris.cmsm.models.response.ResMakeAsset;
import com.cris.cmsm.models.response.ResMonthlyBill;
import com.cris.cmsm.models.response.ResMonthlyCons;
import com.cris.cmsm.models.response.ResSSConsumption;
import com.cris.cmsm.models.response.ResSubStationSummartVO;
import com.cris.cmsm.models.response.ResSubmitLt;
import com.cris.cmsm.models.response.ResTabularReport;
import com.cris.cmsm.models.response.ResponseAssetDetails;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.models.response.SEBResponse;
import com.cris.cmsm.models.response.SaveConsumptionResponse;
import com.cris.cmsm.models.response.SubStation;
import com.cris.cmsm.models.response.SubStationRes;
import com.cris.cmsm.models.response.SubStationResponse;
import com.cris.cmsm.models.response.ThreeYearData;
import com.cris.cmsm.util.URLS;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *
 */
public interface APIView {
    @POST(URLS.LOGIN)
    Call<LoginResponse> userLogin(@Body LoginRequest userLogin);


    @POST(URLS.CREW_UTILIZATION)
    Call<CrewUtilResponse> getCrewUtilization(@Body GraphAPIRequest request);

    @POST(URLS.CREW_UTILIZATION_FORTNIGHT)
    Call<CrewUtilResponse> getFortnightCrewUtilization(@Body GraphAPIRequest request);

    @POST(URLS.ABNORMALITY)
    Call<AbnormalityResponse> getAbnormality(@Body GraphAPIRequest request);

    @POST(URLS.IRREGULAR_CREW)
    Call<KeyValueResponse> getIrregularCrew(@Body KeyValue request);


    @POST(URLS.VCD_STATUS)
    Call<VcdStatusResponse> getVcdStatus(@Body GraphAPIRequest request);

    @POST(URLS.CREW_POSITION)
    Call<List<CrewPositionSummaryResponse>> getCrewPosition(@Body ConSummaryRequest request);

    @POST(URLS.ENERGY_CONSUMPTION)
    Call<ConsumptionResponse> getEnergyConsumption(@Body EnergyConsumptionRequest request);

    @POST(URLS.ENERGY_REGENERATION)
    Call<ConsumptionResponse> getEnergyRegeneration(@Body EnergyConsumptionRequest request);

    @POST(URLS.SEC_SFC)
    Call<ConsumptionResponse> getSecSfc(@Body EnergyConsumptionRequest request);


    @POST(URLS.LOBBY_LIST)
    Call<List<Lobby>> getLobbyList(@Body ConSummaryRequest request);

    @GET(URLS.MASTER_ZONE_DIVISION)
    Call<MasterData> getMasterData();

    @POST(URLS.GET_CONSUMPTION_DASHBOARD)
    Call<ConsumptionResponse> getConsumption(@Body GraphAPIRequest request);

    @POST(URLS.CREW_AVAILABILITY)
    Call<ConsumptionResponse> getCrewAvailability(@Body ConSummaryRequest request);

    @POST(URLS.CREW_AVAILABILITY_DETAIL)
    Call<CrewAvailabilityDetailResponse> getCrewAvailabilityDetail(@Body CrewAvailabilityDetailRequest request);

    @POST(URLS.LI_CREW_MONITORED)
    Call<LICrewMonitoredResponse> liCrewMonitored(@Body LoginRequest userLogin);

    @POST(URLS.KEY_VALUE)
    Call<List<KeyValue>> getKeyValue(@Body KeyValueRequest userLogin);

    @POST(URLS.CREW_BIODATA)
    Call<CrewDetailsResponse> getCrewBiodata(@Body KeyValueRequest userLogin);



    @POST(URLS.TRAINING_DETAILS)
    Call<CrewDetailsResponse> getTrainingDetails(@Body KeyValueRequest userLogin);

    @POST(URLS.LOCO_COMPETENCY)
    Call<CrewDetailsResponse> getLocoCompetencyDetails(@Body KeyValueRequest userLogin);

    @POST(URLS.ROAD_LEARNING)
    Call<CrewDetailsResponse> getRoadLearningDetails(@Body KeyValueRequest userLogin);


    @POST(URLS.MILEAGE_DETAILS)
    Call<CrewDetailsResponse> getMileageDetails(@Body KeyValueRequest userLogin);


    @POST(URLS.OVERTIME_DETAILS)
    Call<CrewDetailsResponse> getOvertimeDetails(@Body KeyValueRequest userLogin);



    @POST(URLS.SLOT_MILEAGE_DETAILS)
    Call<CrewDetailsResponse> getSlotMileageDetails(@Body KeyValueRequest userLogin);



    @POST(URLS.SLOT_OVERTIME_DETAILS)
    Call<CrewDetailsResponse> getSlotOvertimeDetails(@Body KeyValueRequest userLogin);


    @POST(URLS.GET_BILL_DASHBOARD)
    Call<BillingResponse> getBilling(@Body GraphAPIRequest request);


    @POST(URLS.ANNEXURE_14)
    Call<MISReportResponse> getMISReport(@Body MISReportRequest request);

    @POST(URLS.ANNEXURE_5)
    Call<ResAnnexure5> getAnnexure5(@Body MISReportRequest request);

    @POST(URLS.ANNEXURE_6)
    Call<ResAnnexure6> getAnnexure6(@Body MISReportRequest request);

    @POST(URLS.ANNEXURE_14A)
    Call<ResAnnexure14A> getAnnexure14A(@Body MISReportRequest request);

    @POST(URLS.ANNEXURE_14B)
    Call<ResAnnexure14B> getAnnexure14B(@Body MISReportRequest request);


    @POST(URLS.REPORT_ANNEXURE_14C)
    Call<Annexure14CRes> getAnnexure14C(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_14D)
    Call<ResAnnexure14D> getAnnexure14D(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_1)
    Call<ResAnnexure1> getAnnexure1(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_3)
    Call<ResAnnexure3> getAnnexure3(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_8)
    Call<ResAnnexure8> getAnnexure8(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_9)
    Call<ResAnnexure9> getAnnexure9(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_10)
    Call<ResAnnexure10> getAnnexure10(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_11)
    Call<ResAnnexure11> getAnnexure11(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_7)
    Call<ResAnnexure7> getAnnexure7(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_RB)
    Call<RB1Response> getRB1Response(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_RB2)
    Call<ResAnnexureRB2> getRB2Response(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_RB3)
    Call<ResAnnexureRB3> getRB3Response(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_RB4)
    Call<ResAnnexureRB4> getRB4Response(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_RB5)
    Call<ResAnnexureRB5> getRB5Response(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_RB6)
    Call<ResAnnexureRB6> getRB6Response(@Body MISReportRequest request);

    @POST(URLS.REPORT_ANNEXURE_RB7)
    Call<ResAnnexureRB7> getRB7Response(@Body MISReportRequest request);

    @POST(URLS.FEED_BACK)
    Call<FeedbackResponse> saveFeedback(@Body FeedbackRequest request);

    @POST(URLS.COMPARISION_CONSUMPTION)
    Call<List<ConSummaryResponse>> compareConsumption(@Body ConSummaryRequest request);


    @POST(URLS.COMPARISION_DETAIL_CONSUMPTION)
    Call<ThreeYearData> compareDetailsConsumption(@Body ConSummaryRequest request);


    @POST(URLS.SSE_ROLE)
    Call<List<ResponseSSERole>> getSSERoles(@Body RequestSSERole request);


    @POST(URLS.SUB_STATION)
    Call<List<SubStation>> getSubStation(@Body SubStationRequest request);

    @POST(URLS.GRAPH_CONS_MONTHLY)
    Call<ResMonthlyCons> getMonthlyConsumption(@Body MonthlyConsModel request);

    @POST(URLS.GRAPH_BILL_MONTHLY)
    Call<ResMonthlyBill> getMonthlyBilling(@Body MonthlyConsModel request);

    @POST(URLS.SS_CONSUMPTION)
    Call<ResSSConsumption> getSSConsumption(@Body SSConsumptionRequest request);

    @POST(URLS.SEB_STATION)
    Call<SEBResponse> getSebList(@Body SEBRequest request);

    @POST(URLS.GET_SUBSTATION_CONSUMPTION)
    Call<SubStationResponse> subStationConsumption(@Body SubStationConsumption request);

    @POST(URLS.SUBMIT_CONSUMPTION)
    Call<SaveConsumptionResponse> saveSubStationConsumption(@Body SaveConsumption request);


    @POST(URLS.GET_LT_CONNECTION)
    Call<ResLTConnection> getLTConnectionDetails(@Body LTConnectionRequest request);


    @POST(URLS.SAVE_LT_CONNECTION)
    Call<ResSubmitLt> saveLTConnection(@Body SubmitLTConnection request);


    @GET(URLS.GET_SUBSTATION_DETAILS)
    Call<SubStationRes> getSubStationDetails(@Query("ssid") String ssid);

    @GET(URLS.MAKE_ASSETS)
    Call<ResMakeAsset> getMakeAssets(@Query("assetType") String assetType);


    @POST(URLS.SUBSTATION_ASSETS)
    Call<ResTabularReport> getSubstationTabularAsset(@Body RequestSSAssets requestSSAssets);


    @POST(URLS.SUBSTATION_ASSETS_SUMMARY)
    Call<ResSubStationSummartVO> getAssetsSummary(@Body AssetManagementModel requestSSAssets);


    @POST(URLS.ASSETS_REPORT_SUMMARY)
    Call<ResAssetReport> getAssetReport(@Body RequestSSAssets requestSSAssets);

    @POST(URLS.ASSETS_REPORT_DETAILS)
    Call<ResponseAssetDetails> getAssetReportDetails(@Body RequestSSAssets requestSSAssets);

    @POST(URLS.ABNORMALITYLIST)
    Call<Paramresponse> getparam(@Body GraphAPIRequest graphAPIRequest);

    @POST(URLS.Loginot)
    Call<LoginResponse> user(@Body LoginRequest loguser);

    @POST(URLS.ABNORMALITY_DETAILS)
    Call<Paramresponse> getparamlist(@Body GraphAPIRequest graphAPI);

    @POST(URLS.GETREMARKS)
    Call<Remarksresponse> getparams(@Body GraphAPIRequest graphrequest);
    @POST(URLS.GETABNORMALITYCOUNT)
    Call<Paramresponse> getpara(@Body GraphAPIRequest APIreq);
    @POST(URLS.GETABNORMALITYLISTSUBMITTED)
    Call<Paramresponse> getpar(@Body GraphAPIRequest req);
    @POST(URLS.CHANGEPASSWORD)
    Call<Passwordresponse> getparm(@Body GraphAPIRequest graphreq);
    @POST(URLS.SECTION)
    Call<Sectionresponse> getparms(@Body GraphAPIRequest graphreq);
    @POST(URLS.SUBHEADLIST)
    Call<Sectionresponse> getpa(@Body GraphAPIRequest graphreq);
    @POST(URLS.SAVEABNORMALITY)
    Call<Paramresponse> getparas(@Body GraphAPIRequest graphreq);
    @POST(URLS.ABNORMALITYANALYSISCOUNT)
    Call<Paramresponse> getparanaly(@Body GraphAPIRequest graphreq);
    @POST(URLS.ABNORMALITYANALYSISCOUNTWITHPARAM)
    Call<Remarksresponse> getparanal(@Body GraphAPIRequest graphreq);

    @POST(URLS.GET_SECTIONS)
    Call<Sectionresponse> getparana(@Body GraphAPIRequest graphreq);

    @POST(URLS.GET_CREWLIST)
    Call<Paramresponse> getcrewgrade(@Body GraphAPIRequest graphreq2);

    @POST(URLS.SAVE_LI_GRADING)
    Call<Remarksresponse> getsavermk(@Body GraphAPIRequest saveli);

    @POST(URLS.SAVE_LI_COUNSELLING)
    Call<Remarksresponse> getsaveconsrmk(@Body GraphAPIRequest savelicons);

    @POST(URLS.LICREWSTATUS)
    Call<LICrewMonitoredResponse> getlicrewstts(@Body GraphAPIRequest lireq);

    @POST(URLS.VALIDATE_FROM_TO_STTN_LOCO)
    Call<ValidateFromToLocoResponse> getmsg(@Body GraphAPIRequest req);

  @POST(URLS.LIMOVEMENTDETAILS)
    Call<Limovementresponse> getmssg(@Body GraphAPIRequest req);

    @POST(URLS.SAVE_LI_MOVEMENT_DETAIL)
    Call<LimMovementSubmitResponse> getres(@Body GraphAPIRequest req);

    @POST(URLS.LIMOVEMENT_DETAIL_MONTHLY)
    Call<Limovementresponse> getresp(@Body GraphAPIRequest req);


}
