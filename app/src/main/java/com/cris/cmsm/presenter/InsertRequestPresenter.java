package com.cris.cmsm.presenter;

import com.google.gson.Gson;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.models.request.LTConnectionRequest;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SEBRequest;
import com.cris.cmsm.models.request.SaveConsumption;
import com.cris.cmsm.models.request.SubStationConsumption;
import com.cris.cmsm.models.request.SubStationRequest;
import com.cris.cmsm.models.request.SubmitLTConnection;
import com.cris.cmsm.models.response.ResLTConnection;
import com.cris.cmsm.models.response.ResSubmitLt;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.models.response.SEBResponse;
import com.cris.cmsm.models.response.SaveConsumptionResponse;
import com.cris.cmsm.models.response.SubStation;
import com.cris.cmsm.models.response.SubStationRes;
import com.cris.cmsm.models.response.SubStationResponse;
import com.cris.cmsm.presenterview.RequestView;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class InsertRequestPresenter implements RequestView {
    ResponseView view;

    public InsertRequestPresenter(ResponseView view) {
        this.view = view;
    }


    @Override
    public void Request(Object object, String msg, final int position) {
        switch (position) {
            case Constants.Substation:
                SubStationConsumption subStationConsumption = (SubStationConsumption) object;
                System.out.println("Request is " + new Gson().toJson(subStationConsumption));
                showProgress(msg);
                WebServices.getInstance().getService().subStationConsumption(subStationConsumption).enqueue(new Callback<SubStationResponse>() {
                    @Override
                    public void onResponse(Call<SubStationResponse> call, Response<SubStationResponse> response) {
                        dismissProgress();
                        System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<SubStationResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.GET_SSE:
                RequestSSERole requestSSERole = (RequestSSERole) object;
                showProgress(msg);
                //System.out.println("Request is " + new Gson().toJson(requestSSERole));
                WebServices.getInstance().getService().getSSERoles(requestSSERole).enqueue(new Callback<List<ResponseSSERole>>() {
                    @Override
                    public void onResponse(Call<List<ResponseSSERole>> call, Response<List<ResponseSSERole>> response) {
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<List<ResponseSSERole>> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.GET_SUBSTATION:
                SubStationRequest subStationRequest = (SubStationRequest) object;
                showProgress(msg);
                WebServices.getInstance().getService().getSubStation(subStationRequest).enqueue(new Callback<List<SubStation>>() {
                    @Override
                    public void onResponse(Call<List<SubStation>> call, Response<List<SubStation>> response) {
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<List<SubStation>> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.GET_SEB:
                SEBRequest sebRequest = (SEBRequest) object;
                showProgress(msg);
                // System.out.println("Request is " + new Gson().toJson(sebRequest));
                WebServices.getInstance().getService().getSebList(sebRequest).enqueue(new Callback<SEBResponse>() {
                    @Override
                    public void onResponse(Call<SEBResponse> call, Response<SEBResponse> response) {
                        dismissProgress();
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<SEBResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;
            case Constants.GET_LT_CONNECTION:
                LTConnectionRequest getLTConnection = (LTConnectionRequest) object;
                showProgress(msg);
                // System.out.println("Request is " + new Gson().toJson(getLTConnection));
                WebServices.getInstance().getService().getLTConnectionDetails(getLTConnection).enqueue(new Callback<ResLTConnection>() {
                    @Override
                    public void onResponse(Call<ResLTConnection> call, Response<ResLTConnection> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResLTConnection> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;
            case Constants.SAVE_CONSUMPTION:
                SaveConsumption saveConsumption = (SaveConsumption) object;
                // System.out.println("Request is " + new Gson().toJson(saveConsumption));
                showProgress(msg);
                WebServices.getInstance().getService().saveSubStationConsumption(saveConsumption).enqueue(new Callback<SaveConsumptionResponse>() {
                    @Override
                    public void onResponse(Call<SaveConsumptionResponse> call, Response<SaveConsumptionResponse> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<SaveConsumptionResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.SAVE_LT_CONNECTION:
                SubmitLTConnection submitLTConnection = (SubmitLTConnection) object;
                // System.out.println("Request is " + new Gson().toJson(submitLTConnection));
                showProgress(msg);
                WebServices.getInstance().getService().saveLTConnection(submitLTConnection).enqueue(new Callback<ResSubmitLt>() {
                    @Override
                    public void onResponse(Call<ResSubmitLt> call, Response<ResSubmitLt> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResSubmitLt> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.SS_DETAILS:
                String ssid = (String) object;
                showProgress(msg);
                //  System.out.println("Request is " + ssid);
                WebServices.getInstance().getService().getSubStationDetails(ssid).enqueue(new Callback<SubStationRes>() {
                    @Override
                    public void onResponse(Call<SubStationRes> call, Response<SubStationRes> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<SubStationRes> call, Throwable t) {
                        dismissProgress();
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
