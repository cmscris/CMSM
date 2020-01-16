package com.cris.cmsm.presenter;

import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.models.KeyValue;
import com.cris.cmsm.models.LiInspectionRecord;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.CrewAvailabilityDetailRequest;
import com.cris.cmsm.models.request.EnergyConsumptionRequest;
import com.cris.cmsm.models.request.FeedbackRequest;
import com.cris.cmsm.models.request.GPSRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.KeyValueRequest;
import com.cris.cmsm.models.request.LiMovementRequest;
import com.cris.cmsm.models.request.LoginRequest;
import com.cris.cmsm.models.request.MISReportRequest;
import com.cris.cmsm.models.response.Annexure14CRes;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.ConSummaryResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.CrewAvailabilityDetailResponse;
import com.cris.cmsm.models.response.CrewDetailsResponse;
import com.cris.cmsm.models.response.CrewPositionSummaryResponse;
import com.cris.cmsm.models.response.FeedbackResponse;
import com.cris.cmsm.models.response.KeyValueResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.LiInspectionResponse;
import com.cris.cmsm.models.response.LiMovementVOsResponseNew;
import com.cris.cmsm.models.response.LimMovementSubmitResponse;
import com.cris.cmsm.models.response.Limovementresponse;
import com.cris.cmsm.models.response.LoginResponse;
import com.cris.cmsm.models.response.MISReportResponse;
import com.cris.cmsm.models.response.MasterData;
import com.cris.cmsm.models.response.NearestStationResponse;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.Passwordresponse;
import com.cris.cmsm.models.response.RB1Response;
import com.cris.cmsm.models.response.Remarksresponse;
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
import com.cris.cmsm.models.response.Sectionresponse;
import com.cris.cmsm.models.response.ThreeYearData;
import com.cris.cmsm.models.response.ValidateFromToLocoResponse;
import com.cris.cmsm.presenterview.RequestView;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.Constants;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class RequestPresenter implements RequestView {
    ResponseView view;

    public RequestPresenter(ResponseView view) {
        this.view = view;
    }


    @Override
    public void Request(Object object, String msg, final int position) {
        switch (position) {


            case Constants.LOGIN:
                showProgress(msg);
                LoginRequest userLogin = (LoginRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().userLogin(userLogin).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>" + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;
            case Constants.Loginot:
                showProgress(msg);
                LoginRequest loguser= (LoginRequest) object;
                System.out.println("Request is " + new Gson().toJson(loguser));
                WebServices.getInstance().getService().user(loguser).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>" + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);


                    }

                    @Override
                    public void onFailure(Call <LoginResponse> call, Throwable t) {
                        System.out.println("Response can not fetched");

                    }
                });
                break;
            case Constants.ABNORMALITYLIST:
                showProgress(msg);
                GraphAPIRequest graphAPIRequest = (GraphAPIRequest) object;
                System.out.println("Request is " + new Gson().toJson(graphAPIRequest));
                WebServices.getInstance().getService().getparam(graphAPIRequest).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call, Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.ABNORMALITY_DETAILS:
                showProgress(msg);
                GraphAPIRequest graphAPI = (GraphAPIRequest) object;
                System.out.println("Request is " + new Gson().toJson(graphAPI));
                WebServices.getInstance().getService().getparamlist(graphAPI).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call, Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response.body()));
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.GETREMARKS:
                showProgress(msg);
                GraphAPIRequest graphrequest = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(graphrequest));
                WebServices.getInstance().getService().getparams(graphrequest).enqueue(new Callback<Remarksresponse>() {
                    @Override
                    public void onResponse(Call<Remarksresponse> call,Response<Remarksresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Remarksresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.GETABNORMALITYCOUNT:
                showProgress(msg);
                GraphAPIRequest APIreq = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(APIreq));
                WebServices.getInstance().getService().getpara(APIreq).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call,Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.GET_SECTIONS:
                showProgress(msg);
                GraphAPIRequest reqs = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(reqs));
                WebServices.getInstance().getService().getparana(reqs).enqueue(new Callback<Sectionresponse>() {
                    @Override
                    public void onResponse(Call<Sectionresponse> call,Response<Sectionresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Sectionresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;

            case Constants.GETABNORMALITYLISTSUBMITTED:
                showProgress(msg);
                GraphAPIRequest req = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(req));
                WebServices.getInstance().getService().getpar(req).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call,Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;

            case Constants.GET_CREWLIST:
                showProgress(msg);
                GraphAPIRequest graphreq2 = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(graphreq2));
                WebServices.getInstance().getService().getcrewgrade(graphreq2).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call,Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.GET_CREWLIST_LI_GRADING:
                showProgress(msg);
                GraphAPIRequest ligradingreq = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(ligradingreq));
                WebServices.getInstance().getService().getcrewgrades(ligradingreq).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call,Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.GET_CREWLIST_LI_COUNSELLING:
                showProgress(msg);
                GraphAPIRequest liconslingreq= (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(liconslingreq));
                WebServices.getInstance().getService().getcrewconsel(liconslingreq).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call,Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;


            case Constants.SAVE_LI_GRADING:
                showProgress(msg);
                GraphAPIRequest saveli = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(saveli));
                WebServices.getInstance().getService().getsavermk(saveli).enqueue(new Callback<Remarksresponse>() {
                    @Override
                    public void onResponse(Call<Remarksresponse> call,Response<Remarksresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Remarksresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.SAVE_LI_COUNSELLING:
                showProgress(msg);
                GraphAPIRequest savelicons = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(savelicons));
                WebServices.getInstance().getService().getsaveconsrmk(savelicons).enqueue(new Callback<Remarksresponse>() {
                    @Override
                    public void onResponse(Call<Remarksresponse> call,Response<Remarksresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Remarksresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;

            case Constants.LICREWSTATUS:
                showProgress(msg);
                GraphAPIRequest lireq = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(lireq));
                WebServices.getInstance().getService().getlicrewstts(lireq).enqueue(new Callback<LICrewMonitoredResponse>() {
                    @Override
                    public void onResponse(Call<LICrewMonitoredResponse> call,Response<LICrewMonitoredResponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <LICrewMonitoredResponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.VALIDATE_FROM_TO_STTN_LOCO:
                showProgress(msg);
                GraphAPIRequest reqst = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(reqst));
                WebServices.getInstance().getService().getmsg(reqst).enqueue(new Callback<ValidateFromToLocoResponse>() {
                    @Override
                    public void onResponse(Call<ValidateFromToLocoResponse> call,Response<ValidateFromToLocoResponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <ValidateFromToLocoResponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
           case Constants.LIMOVEMENTDETAILS:
                showProgress(msg);
                GraphAPIRequest reqst1 = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(reqst1));
                WebServices.getInstance().getService().getmssg(reqst1).enqueue(new Callback<Limovementresponse>() {
                    @Override
                    public void onResponse(Call<Limovementresponse> call,Response<Limovementresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Limovementresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.LIMOVEMENT_DETAIL_DATEWISE:
                showProgress(msg);
                GraphAPIRequest lireqdatewise = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(lireqdatewise));
                WebServices.getInstance().getService().getLidetailDatewise(lireqdatewise).enqueue(new Callback<Limovementresponse>() {
                    @Override
                    public void onResponse(Call<Limovementresponse> call,Response<Limovementresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Limovementresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;

            case Constants.SAVE_LI_MOVEMENT_DETAIL:
                showProgress(msg);
                GraphAPIRequest reqlimov = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(reqlimov));
                WebServices.getInstance().getService().getres(reqlimov).enqueue(new Callback<LimMovementSubmitResponse>() {
                    @Override
                    public void onResponse(Call<LimMovementSubmitResponse> call,Response<LimMovementSubmitResponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <LimMovementSubmitResponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.LIMOVEMENT_DETAIL_MONTHLY:
                showProgress(msg);
                GraphAPIRequest reqlimonth = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(reqlimonth));
                WebServices.getInstance().getService().getresp(reqlimonth).enqueue(new Callback<Limovementresponse>() {
                    @Override
                    public void onResponse(Call<Limovementresponse> call,Response<Limovementresponse> response) {
                        dismissProgress();
                        System.out.println("LIMOVEMENT_DETAIL_MONTHLY Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Limovementresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;




            case Constants.CHANGEPASSWORD:
                showProgress(msg);
                GraphAPIRequest graphreq = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(graphreq));
                WebServices.getInstance().getService().getparm(graphreq).enqueue(new Callback<Passwordresponse>() {
                    @Override
                    public void onResponse(Call<Passwordresponse> call,Response<Passwordresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Passwordresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;

            case Constants.SECTION:
                showProgress(msg);
                GraphAPIRequest sectionrequest = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(sectionrequest));
                WebServices.getInstance().getService().getparms(sectionrequest).enqueue(new Callback<Sectionresponse>() {
                    @Override
                    public void onResponse(Call<Sectionresponse> call,Response<Sectionresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Sectionresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.SUBHEADLIST:
                showProgress(msg);
                GraphAPIRequest abnrdepartrequest = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(abnrdepartrequest));
                WebServices.getInstance().getService().getpa(abnrdepartrequest).enqueue(new Callback<Sectionresponse>() {
                    @Override
                    public void onResponse(Call<Sectionresponse> call,Response<Sectionresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Sectionresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.SAVEABNORMALITY:
                showProgress(msg);
                GraphAPIRequest abnrfillrequest = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(abnrfillrequest));
                WebServices.getInstance().getService().getparas(abnrfillrequest).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call,Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.ABNORMALITYANALYSISCOUNT:
                showProgress(msg);
                GraphAPIRequest abnranalyisrequest = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(abnranalyisrequest));
                WebServices.getInstance().getService().getparanaly(abnranalyisrequest).enqueue(new Callback<Paramresponse>() {
                    @Override
                    public void onResponse(Call<Paramresponse> call,Response<Paramresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Paramresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;
            case Constants.ABNORMALITYANALYSISCOUNTWITHPARAM:
                showProgress(msg);
                GraphAPIRequest abnranalysisrequest = (GraphAPIRequest) object;
                System.out.println("Request is >>" + new Gson().toJson(abnranalysisrequest));
                WebServices.getInstance().getService().getparanal(abnranalysisrequest).enqueue(new Callback<Remarksresponse>() {
                    @Override
                    public void onResponse(Call<Remarksresponse> call,Response<Remarksresponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call <Remarksresponse> call, Throwable t) {
                        System.out.println("Error in Response");
                    }


                });
                break;




            case Constants.KEY_VALUE:
                showProgress(msg);
                KeyValueRequest kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getKeyValue(kvrequest).enqueue(new Callback<List<KeyValue>>() {
                    @Override
                    public void onResponse(Call<List<KeyValue>> call, Response<List<KeyValue>> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<List<KeyValue>> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            // Key Value Report
            case Constants.CREW_BIODATA:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getCrewBiodata(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            // Key Value Report
            case Constants.IRREGULAR_CREW:

                showProgress(msg);
                KeyValue keyValueRequest = (KeyValue) object;
                System.out.println("Request is " + new Gson().toJson(keyValueRequest));
                WebServices.getInstance().getService().getIrregularCrew(keyValueRequest).enqueue(new Callback<KeyValueResponse>() {
                    @Override
                    public void onResponse(Call<KeyValueResponse> call, Response<KeyValueResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<KeyValueResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.TRAINING_DETAILS:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getTrainingDetails(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.LOCO_COMPETENCY_DETAILS:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getLocoCompetencyDetails(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.MILEAGE_DETAILS:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getMileageDetails(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.OVERTIME:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getOvertimeDetails(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.SLOT_MILEAGE_DETAILS:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getSlotMileageDetails(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.SLOT_OVERTIME_DETAILS:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getSlotOvertimeDetails(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.ROAD_LEARNING_DETAILS:
                showProgress(msg);
                kvrequest = (KeyValueRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().getRoadLearningDetails(kvrequest).enqueue(new Callback<CrewDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CrewDetailsResponse> call, Response<CrewDetailsResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewDetailsResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;



            case Constants.MASTERS:
                showProgress(msg);
                WebServices.getInstance().getService().getMasterData().enqueue(new Callback<MasterData>() {
                    @Override
                    public void onResponse(Call<MasterData> call, Response<MasterData> response) {
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<MasterData> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.CREW_UTILIZATION:
                showProgress(msg);
                GraphAPIRequest utilizationRequest = (GraphAPIRequest) object;
                // System.out.println("Request is " + new Gson().toJson(consumptionRequest));
                WebServices.getInstance().getService().getConsumption(utilizationRequest).enqueue(new Callback<ConsumptionResponse>() {
                    @Override
                    public void onResponse(Call<ConsumptionResponse> call, Response<ConsumptionResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ConsumptionResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.CREW_AVAILABILITY:
                showProgress(msg);
                ConSummaryRequest conSummaryRequest = (ConSummaryRequest) object;

                //System.out.println("Request is " + new Gson().toJson(conSummaryRequest));

                WebServices.getInstance().getService().getCrewAvailability(conSummaryRequest).enqueue(new Callback<ConsumptionResponse>() {
                    @Override
                    public void onResponse(Call<ConsumptionResponse> call, Response<ConsumptionResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ConsumptionResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;



            case Constants.CREW_AVAILABILITY_DETAIL:
                showProgress(msg);
                CrewAvailabilityDetailRequest conSummaryRequestNew = (CrewAvailabilityDetailRequest) object;

                //System.out.println("Request is " + new Gson().toJson(conSummaryRequest));

                WebServices.getInstance().getService().getCrewAvailabilityDetail(conSummaryRequestNew).enqueue(new Callback<CrewAvailabilityDetailResponse>() {
                    @Override
                    public void onResponse(Call<CrewAvailabilityDetailResponse> call, Response<CrewAvailabilityDetailResponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewAvailabilityDetailResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;



            case Constants.CREW_POSITION:
                if (DataHolder.getInstance().getCrewPositionSummaryResponses() != null) {
                    view.ResponseOk(DataHolder.getInstance().getCrewPositionSummaryResponses(), position);
                } else {
                    showProgress(msg);
                    conSummaryRequest = (ConSummaryRequest) object;
                    // System.out.println("Request is " + new Gson().toJson(conSummaryRequest));
                    WebServices.getInstance().getService().getCrewPosition(conSummaryRequest).enqueue(new Callback<List<CrewPositionSummaryResponse>>() {
                        @Override
                        public void onResponse(Call<List<CrewPositionSummaryResponse>> call, Response<List<CrewPositionSummaryResponse>> response) {
                            dismissProgress();
                            // System.out.println("Response is " + new Gson().toJson(response.body()));
                            view.ResponseOk(response.body(), position);
                        }

                        @Override
                        public void onFailure(Call<List<CrewPositionSummaryResponse>> call, Throwable t) {
                            dismissProgress();
                            view.Error();
                        }
                    });
                }
                break;


            case Constants.ENERGY_CONSUMPTION:
                showProgress(msg);
                EnergyConsumptionRequest energyConsumptionRequest = (EnergyConsumptionRequest) object;
                //System.out.println("Request is " + new Gson().toJson(energyConsumptionRequest));
                WebServices.getInstance().getService().getEnergyConsumption(energyConsumptionRequest).enqueue(new Callback<ConsumptionResponse>() {
                    @Override
                    public void onResponse(Call<ConsumptionResponse> call, Response<ConsumptionResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ConsumptionResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ENERGY_REGENERATION:
                showProgress(msg);
                EnergyConsumptionRequest energyRegenerationRequest = (EnergyConsumptionRequest) object;
                // System.out.println("Request is " + new Gson().toJson(consumptionRequest));
                WebServices.getInstance().getService().getEnergyRegeneration(energyRegenerationRequest).enqueue(new Callback<ConsumptionResponse>() {
                    @Override
                    public void onResponse(Call<ConsumptionResponse> call, Response<ConsumptionResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ConsumptionResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.SEC_SFC:
                showProgress(msg);
                EnergyConsumptionRequest secsfcRequest = (EnergyConsumptionRequest) object;
                // System.out.println("Request is " + new Gson().toJson(consumptionRequest));
                WebServices.getInstance().getService().getSecSfc(secsfcRequest).enqueue(new Callback<ConsumptionResponse>() {
                    @Override
                    public void onResponse(Call<ConsumptionResponse> call, Response<ConsumptionResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ConsumptionResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.LOBBY_LIST:

                showProgress(msg);
                ConSummaryRequest request = (ConSummaryRequest) object;
                // System.out.println("Request is " + new Gson().toJson(conSummaryRequest));
                WebServices.getInstance().getService().getLobbyList(request).enqueue(new Callback<List<Lobby>>() {
                    @Override
                    public void onResponse(Call<List<Lobby>> call, Response<List<Lobby>> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<List<Lobby>> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });

                break;


            case Constants.LI_CREW_MONITORED:
                showProgress(msg);
                userLogin = (LoginRequest) object;
                // System.out.println("Request is " + new Gson().toJson(userLogin));
                WebServices.getInstance().getService().liCrewMonitored(userLogin).enqueue(new Callback<LICrewMonitoredResponse>() {
                    @Override
                    public void onResponse(Call<LICrewMonitoredResponse> call, Response<LICrewMonitoredResponse> response) {
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<LICrewMonitoredResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.FILTER_CONSUMPTION:
                if (DataHolder.getInstance().getConsumptionResponse() != null) {
                    view.ResponseOk(DataHolder.getInstance().getConsumptionResponse(), position);
                } else {
                    showProgress(msg);
                    GraphAPIRequest consumptionRequest = (GraphAPIRequest) object;
                    // System.out.println("Request is " + new Gson().toJson(consumptionRequest));
                    WebServices.getInstance().getService().getConsumption(consumptionRequest).enqueue(new Callback<ConsumptionResponse>() {
                        @Override
                        public void onResponse(Call<ConsumptionResponse> call, Response<ConsumptionResponse> response) {
                            dismissProgress();
                            //  System.out.println("Response is " + new Gson().toJson(response.body()));
                            view.ResponseOk(response.body(), position);
                        }

                        @Override
                        public void onFailure(Call<ConsumptionResponse> call, Throwable t) {
                            dismissProgress();
                            view.Error();
                        }
                    });
                }
                break;
            case Constants.BOARD_CONSUMPTION_RAILWAY:
                showProgress(msg);
                GraphAPIRequest consumptionRequest = (GraphAPIRequest) object;
                // System.out.println("Request is " + new Gson().toJson(consumptionRequest));
                WebServices.getInstance().getService().getConsumption(consumptionRequest).enqueue(new Callback<ConsumptionResponse>() {
                    @Override
                    public void onResponse(Call<ConsumptionResponse> call, Response<ConsumptionResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ConsumptionResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.DASH_BILLING:
                if (DataHolder.getInstance().getBillingResponse() != null) {
                    view.ResponseOk(DataHolder.getInstance().getBillingResponse(), position);
                } else {
                    showProgress(msg);
                    GraphAPIRequest billingRequest = (GraphAPIRequest) object;
                    //   System.out.println("Request is " + new Gson().toJson(billingRequest));
                    WebServices.getInstance().getService().getBilling(billingRequest).enqueue(new Callback<BillingResponse>() {
                        @Override
                        public void onResponse(Call<BillingResponse> call, Response<BillingResponse> response) {
                            dismissProgress();
                            //System.out.println("Response is " + new Gson().toJson(response.body()));
                            view.ResponseOk(response.body(), position);
                        }

                        @Override
                        public void onFailure(Call<BillingResponse> call, Throwable t) {
                            dismissProgress();
                            view.Error();
                        }
                    });
                }
                break;
            case Constants.BOARD_BILLING_RAILWAY:
                showProgress(msg);
                GraphAPIRequest billingRequest = (GraphAPIRequest) object;
                // System.out.println("Request is " + new Gson().toJson(billingRequest));
                WebServices.getInstance().getService().getBilling(billingRequest).enqueue(new Callback<BillingResponse>() {
                    @Override
                    public void onResponse(Call<BillingResponse> call, Response<BillingResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<BillingResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;
            case Constants.ANNEXURE_5:
                showProgress(msg);
                MISReportRequest annexure5 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure5));
                WebServices.getInstance().getService().getAnnexure5(annexure5).enqueue(new Callback<ResAnnexure5>() {
                    @Override
                    public void onResponse(Call<ResAnnexure5> call, Response<ResAnnexure5> response) {
                        dismissProgress();
                        //System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure5> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_6:
                showProgress(msg);
                MISReportRequest annexure6 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure6));
                WebServices.getInstance().getService().getAnnexure6(annexure6).enqueue(new Callback<ResAnnexure6>() {
                    @Override
                    public void onResponse(Call<ResAnnexure6> call, Response<ResAnnexure6> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure6> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_14:
                showProgress(msg);
                MISReportRequest misReportRequest = (MISReportRequest) object;
                //   System.out.println("Request is " + new Gson().toJson(misReportRequest));
                WebServices.getInstance().getService().getMISReport(misReportRequest).enqueue(new Callback<MISReportResponse>() {
                    @Override
                    public void onResponse(Call<MISReportResponse> call, Response<MISReportResponse> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<MISReportResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;
            case Constants.ANNEXURE_14A:
                showProgress(msg);
                MISReportRequest annexure14A = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure14A));
                WebServices.getInstance().getService().getAnnexure14A(annexure14A).enqueue(new Callback<ResAnnexure14A>() {
                    @Override
                    public void onResponse(Call<ResAnnexure14A> call, Response<ResAnnexure14A> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure14A> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_14B:
                showProgress(msg);
                MISReportRequest annexure14B = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure14B));
                WebServices.getInstance().getService().getAnnexure14B(annexure14B).enqueue(new Callback<ResAnnexure14B>() {
                    @Override
                    public void onResponse(Call<ResAnnexure14B> call, Response<ResAnnexure14B> response) {
                        dismissProgress();
                        //System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure14B> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_14C:
                showProgress(msg);
                MISReportRequest annexure14C = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure14C));
                WebServices.getInstance().getService().getAnnexure14C(annexure14C).enqueue(new Callback<Annexure14CRes>() {
                    @Override
                    public void onResponse(Call<Annexure14CRes> call, Response<Annexure14CRes> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<Annexure14CRes> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_14D:
                showProgress(msg);
                MISReportRequest annexure14D = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure14D));
                WebServices.getInstance().getService().getAnnexure14D(annexure14D).enqueue(new Callback<ResAnnexure14D>() {
                    @Override
                    public void onResponse(Call<ResAnnexure14D> call, Response<ResAnnexure14D> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure14D> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_1:
                showProgress(msg);
                MISReportRequest annexure1 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure1));
                WebServices.getInstance().getService().getAnnexure1(annexure1).enqueue(new Callback<ResAnnexure1>() {
                    @Override
                    public void onResponse(Call<ResAnnexure1> call, Response<ResAnnexure1> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure1> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_3:
                showProgress(msg);
                MISReportRequest annexure3 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure3));
                WebServices.getInstance().getService().getAnnexure3(annexure3).enqueue(new Callback<ResAnnexure3>() {
                    @Override
                    public void onResponse(Call<ResAnnexure3> call, Response<ResAnnexure3> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure3> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_8:
                showProgress(msg);
                MISReportRequest annexur8 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexur8));
                WebServices.getInstance().getService().getAnnexure8(annexur8).enqueue(new Callback<ResAnnexure8>() {
                    @Override
                    public void onResponse(Call<ResAnnexure8> call, Response<ResAnnexure8> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure8> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_9:
                showProgress(msg);
                MISReportRequest annexure9 = (MISReportRequest) object;
                //  System.out.println("Request is " + new Gson().toJson(annexure9));
                WebServices.getInstance().getService().getAnnexure9(annexure9).enqueue(new Callback<ResAnnexure9>() {
                    @Override
                    public void onResponse(Call<ResAnnexure9> call, Response<ResAnnexure9> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure9> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_11:
                showProgress(msg);
                MISReportRequest annexure11 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure11));
                WebServices.getInstance().getService().getAnnexure11(annexure11).enqueue(new Callback<ResAnnexure11>() {
                    @Override
                    public void onResponse(Call<ResAnnexure11> call, Response<ResAnnexure11> response) {
                        dismissProgress();
                        //System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure11> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_7:
                showProgress(msg);
                MISReportRequest annexure7 = (MISReportRequest) object;
                //  System.out.println("Request is " + new Gson().toJson(annexure7));
                WebServices.getInstance().getService().getAnnexure7(annexure7).enqueue(new Callback<ResAnnexure7>() {
                    @Override
                    public void onResponse(Call<ResAnnexure7> call, Response<ResAnnexure7> response) {
                        dismissProgress();
                        //   System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure7> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_RB1:
                showProgress(msg);
                MISReportRequest annexure_rb1 = (MISReportRequest) object;
                //  System.out.println("Request is " + new Gson().toJson(annexure_rb1));
                WebServices.getInstance().getService().getRB1Response(annexure_rb1).enqueue(new Callback<RB1Response>() {
                    @Override
                    public void onResponse(Call<RB1Response> call, Response<RB1Response> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<RB1Response> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_RB2:
                showProgress(msg);
                MISReportRequest annexure_rb2 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure_rb2));
                WebServices.getInstance().getService().getRB2Response(annexure_rb2).enqueue(new Callback<ResAnnexureRB2>() {
                    @Override
                    public void onResponse(Call<ResAnnexureRB2> call, Response<ResAnnexureRB2> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexureRB2> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_RB3:
                showProgress(msg);
                MISReportRequest annexure_rb3 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure_rb3));
                WebServices.getInstance().getService().getRB3Response(annexure_rb3).enqueue(new Callback<ResAnnexureRB3>() {
                    @Override
                    public void onResponse(Call<ResAnnexureRB3> call, Response<ResAnnexureRB3> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexureRB3> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_RB4:
                showProgress(msg);
                MISReportRequest annexure_rb4 = (MISReportRequest) object;
                //System.out.println("Request is " + new Gson().toJson(annexure_rb4));
                WebServices.getInstance().getService().getRB4Response(annexure_rb4).enqueue(new Callback<ResAnnexureRB4>() {
                    @Override
                    public void onResponse(Call<ResAnnexureRB4> call, Response<ResAnnexureRB4> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexureRB4> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_RB5:
                showProgress(msg);
                MISReportRequest annexure_rb5 = (MISReportRequest) object;
                //     System.out.println("Request is " + new Gson().toJson(annexure_rb5));
                WebServices.getInstance().getService().getRB5Response(annexure_rb5).enqueue(new Callback<ResAnnexureRB5>() {
                    @Override
                    public void onResponse(Call<ResAnnexureRB5> call, Response<ResAnnexureRB5> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexureRB5> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_RB6:
                showProgress(msg);
                MISReportRequest annexure_rb6 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure_rb6));
                WebServices.getInstance().getService().getRB6Response(annexure_rb6).enqueue(new Callback<ResAnnexureRB6>() {
                    @Override
                    public void onResponse(Call<ResAnnexureRB6> call, Response<ResAnnexureRB6> response) {
                        dismissProgress();
                        //   System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexureRB6> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_RB7:
                showProgress(msg);
                MISReportRequest annexure_rb7 = (MISReportRequest) object;
                // System.out.println("Request is " + new Gson().toJson(annexure_rb7));
                WebServices.getInstance().getService().getRB7Response(annexure_rb7).enqueue(new Callback<ResAnnexureRB7>() {
                    @Override
                    public void onResponse(Call<ResAnnexureRB7> call, Response<ResAnnexureRB7> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexureRB7> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ANNEXURE_10:
                showProgress(msg);
                MISReportRequest annexure10 = (MISReportRequest) object;
                //System.out.println("Request is " + new Gson().toJson(annexure10));
                WebServices.getInstance().getService().getAnnexure10(annexure10).enqueue(new Callback<ResAnnexure10>() {
                    @Override
                    public void onResponse(Call<ResAnnexure10> call, Response<ResAnnexure10> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAnnexure10> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;
            case Constants.FEEDBACK_SERVICE:
                showProgress(msg);
                FeedbackRequest feedbackRequest = (FeedbackRequest) object;
                // System.out.println("Request is " + new Gson().toJson(feedbackRequest));
                WebServices.getInstance().getService().saveFeedback(feedbackRequest).enqueue(new Callback<FeedbackResponse>() {
                    @Override
                    public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.THREE_YEAR_CONSUMPTION:
                if (DataHolder.getInstance().getConSummaryResponses() != null) {
                    view.ResponseOk(DataHolder.getInstance().getConSummaryResponses(), position);
                } else {
                    showProgress(msg);
                    conSummaryRequest = (ConSummaryRequest) object;
                    // System.out.println("Request is " + new Gson().toJson(conSummaryRequest));
                    WebServices.getInstance().getService().compareConsumption(conSummaryRequest).enqueue(new Callback<List<ConSummaryResponse>>() {
                        @Override
                        public void onResponse(Call<List<ConSummaryResponse>> call, Response<List<ConSummaryResponse>> response) {
                            dismissProgress();
                            // System.out.println("Response is " + new Gson().toJson(response.body()));
                            view.ResponseOk(response.body(), position);
                        }

                        @Override
                        public void onFailure(Call<List<ConSummaryResponse>> call, Throwable t) {
                            dismissProgress();
                            view.Error();
                        }
                    });
                }
                break;
            case Constants.THREE_YEAR_DETAIL:
                showProgress(msg);
                conSummaryRequest = (ConSummaryRequest) object;
                //   System.out.println("Request is " + new Gson().toJson(conSummaryRequest));
                WebServices.getInstance().getService().compareDetailsConsumption(conSummaryRequest).enqueue(new Callback<ThreeYearData>() {
                    @Override
                    public void onResponse(Call<ThreeYearData> call, Response<ThreeYearData> response) {
                        dismissProgress();
                        //   System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ThreeYearData> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.GET_NEAREST_STATION_GPS:
                showProgress(msg);
                GPSRequest gpsRequest = (GPSRequest) object;

                System.out.println("Request is " + new Gson().toJson(gpsRequest));
                WebServices.getInstance().getService().getNearestStationGPS(gpsRequest).enqueue(new Callback<List<NearestStationResponse>>() {
                    @Override
                    public void onResponse(Call<List<NearestStationResponse>> call, Response<List<NearestStationResponse>> response) {
                        dismissProgress();
                        //   System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<List<NearestStationResponse>> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.LI_DEPARTURE_DATA:
                showProgress(msg);
                LiMovementRequest liMovementRequest = (LiMovementRequest) object;

                System.out.println("Request is " + new Gson().toJson(liMovementRequest));
                WebServices.getInstance().getService().saveLiDeparture(liMovementRequest).enqueue(new Callback<LiMovementVOsResponseNew>() {
                    @Override
                    public void onResponse(Call<LiMovementVOsResponseNew> call, Response<LiMovementVOsResponseNew> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<LiMovementVOsResponseNew> call, Throwable t) {
                        dismissProgress();

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERROR");
                        view.Error();
                    }
                });
                break;

            case Constants.LI_ARRIVAL_DATA:
                showProgress(msg);
                liMovementRequest = (LiMovementRequest) object;

                System.out.println("Request is " + new Gson().toJson(liMovementRequest));
                WebServices.getInstance().getService().saveLiArrival(liMovementRequest).enqueue(new Callback<LiMovementVOsResponseNew>() {
                    @Override
                    public void onResponse(Call<LiMovementVOsResponseNew> call, Response<LiMovementVOsResponseNew> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<LiMovementVOsResponseNew> call, Throwable t) {
                        dismissProgress();

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERROR");
                        view.Error();
                    }
                });
                break;

            case Constants.GET_LI_MOVEMENT_DEPARTURE_DATA:
                showProgress(msg);
                liMovementRequest = (LiMovementRequest) object;

                System.out.println("Request is " + new Gson().toJson(liMovementRequest));
                WebServices.getInstance().getService().getLiMovementDepartureData(liMovementRequest).enqueue(new Callback<LiMovementVOsResponseNew>() {
                    @Override
                    public void onResponse(Call<LiMovementVOsResponseNew> call, Response<LiMovementVOsResponseNew> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<LiMovementVOsResponseNew> call, Throwable t) {
                        dismissProgress();

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERROR");
                        view.Error();
                    }
                });
                break;


            case Constants.SAVE_INSPECTION_RECORD:
                showProgress(msg);
                LiInspectionRecord liInspectionRecord = (LiInspectionRecord) object;

                System.out.println("Request is " + new Gson().toJson(liInspectionRecord));
                WebServices.getInstance().getService().saveLiInspection(liInspectionRecord).enqueue(new Callback<LiInspectionResponse>() {
                    @Override
                    public void onResponse(Call<LiInspectionResponse> call, Response<LiInspectionResponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<LiInspectionResponse> call, Throwable t) {
                        dismissProgress();

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERROR");
                        view.Error();
                    }
                });
                break;
        }
    }

    @Override
    public void Error() {
        view.Error();
    }

    @Override
    public void dismissProgress() {
        view.dismissProgress();
    }

    @Override
    public void showProgress(String msg) {
        view.showProgress(msg);
    }


}
