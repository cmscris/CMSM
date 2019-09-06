package com.cris.cmsm.presenter;

import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.AbnormalityResponse;
import com.cris.cmsm.models.response.IrregularCrewResponse;
import com.cris.cmsm.models.response.VcdStatusResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.google.gson.Gson;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.models.request.AssetManagementModel;
import com.cris.cmsm.models.request.MonthlyConsModel;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SSConsumptionRequest;
import com.cris.cmsm.models.request.SubStationRequest;
import com.cris.cmsm.models.response.ResAssetReport;
import com.cris.cmsm.models.response.ResMakeAsset;
import com.cris.cmsm.models.response.ResMonthlyBill;
import com.cris.cmsm.models.response.ResMonthlyCons;
import com.cris.cmsm.models.response.ResSSConsumption;
import com.cris.cmsm.models.response.ResSubStationSummartVO;
import com.cris.cmsm.models.response.ResTabularReport;
import com.cris.cmsm.models.response.ResponseAssetDetails;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.models.response.SubStation;
import com.cris.cmsm.presenterview.MonthResponseView;
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

public class MonthlyRequestPresenter implements RequestView {
    private ResponseView view;

    public MonthlyRequestPresenter(ResponseView view) {
        this.view = view;
    }

    @Override
    public void Request(Object object, String msg, final int position) {
        switch (position) {




            case Constants.CREW_UTILIZATION:
                GraphAPIRequest graphAPIRequest = (GraphAPIRequest) object;
                showProgress(msg);
                System.out.println("Request is " + new Gson().toJson(graphAPIRequest));
                WebServices.getInstance().getService().getCrewUtilization(graphAPIRequest).enqueue(new Callback<CrewUtilResponse>() {
                    @Override
                    public void onResponse(Call<CrewUtilResponse> call, Response<CrewUtilResponse> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewUtilResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.CREW_UTILIZATION_FORTNIGHT:
                graphAPIRequest = (GraphAPIRequest) object;
                showProgress(msg);
                System.out.println("Request is " + new Gson().toJson(graphAPIRequest));
                WebServices.getInstance().getService().getFortnightCrewUtilization(graphAPIRequest).enqueue(new Callback<CrewUtilResponse>() {
                    @Override
                    public void onResponse(Call<CrewUtilResponse> call, Response<CrewUtilResponse> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<CrewUtilResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;



            case Constants.ABNORMALITY:
                graphAPIRequest = (GraphAPIRequest) object;
                showProgress(msg);
                System.out.println("Request is " + new Gson().toJson(graphAPIRequest));
                WebServices.getInstance().getService().getAbnormality(graphAPIRequest).enqueue(new Callback<AbnormalityResponse>() {
                    @Override
                    public void onResponse(Call<AbnormalityResponse> call, Response<AbnormalityResponse> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<AbnormalityResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.IRREGULAR_CREW:
                graphAPIRequest = (GraphAPIRequest) object;
                showProgress(msg);
                System.out.println("Request is " + new Gson().toJson(graphAPIRequest));
                WebServices.getInstance().getService().getIrregularCrew(graphAPIRequest).enqueue(new Callback<IrregularCrewResponse>() {
                    @Override
                    public void onResponse(Call<IrregularCrewResponse> call, Response<IrregularCrewResponse> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<IrregularCrewResponse> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.VCD_STATUS:
                graphAPIRequest = (GraphAPIRequest) object;
                showProgress(msg);
                System.out.println("Request is " + new Gson().toJson(graphAPIRequest));
                WebServices.getInstance().getService().getVcdStatus(graphAPIRequest).enqueue(new Callback<VcdStatusResponse>() {
                    @Override
                    public void onResponse(Call<VcdStatusResponse> call, Response<VcdStatusResponse> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<VcdStatusResponse> call, Throwable t) {
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
                        //System.out.println("Response is " + new Gson().toJson(response.body()));
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
                // System.out.println("Request is " + new Gson().toJson(subStationRequest));
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

            case Constants.MONTHLY_CONSUMPTION:
                if (DataHolder.getInstance().getResMonthlyCons() == null) {
                    MonthlyConsModel monthlyConsModel = (MonthlyConsModel) object;
                    final MonthResponseView monthResponseView = (MonthResponseView) view;
                    monthResponseView.showMonthProgress();
                    //System.out.println("Request is " + new Gson().toJson(monthlyConsModel));
                    WebServices.getInstance().getService().getMonthlyConsumption(monthlyConsModel).enqueue(new Callback<ResMonthlyCons>() {
                        @Override
                        public void onResponse(Call<ResMonthlyCons> call, Response<ResMonthlyCons> response) {
                            monthResponseView.dismissMonthReq();
                            // System.out.println("Response is " + new Gson().toJson(response.body()));
                            view.ResponseOk(response.body(), position);
                        }

                        @Override
                        public void onFailure(Call<ResMonthlyCons> call, Throwable t) {
                            monthResponseView.dismissMonthReq();
                            view.Error();
                        }
                    });
                } else
                    view.ResponseOk(DataHolder.getInstance().getResMonthlyCons(), position);
                break;
            case Constants.MONTHLY_BILLING:
                if (DataHolder.getInstance().getResMonthlyBill() == null) {
                    MonthlyConsModel mothlyBilling = (MonthlyConsModel) object;
                    final MonthResponseView monthResponseView = (MonthResponseView) view;
                    monthResponseView.showMonthProgress();
                    WebServices.getInstance().getService().getMonthlyBilling(mothlyBilling).enqueue(new Callback<ResMonthlyBill>() {
                        @Override
                        public void onResponse(Call<ResMonthlyBill> call, Response<ResMonthlyBill> response) {
                            monthResponseView.dismissMonthReq();
                            view.ResponseOk(response.body(), position);
                        }

                        @Override
                        public void onFailure(Call<ResMonthlyBill> call, Throwable t) {
                            monthResponseView.dismissMonthReq();
                            view.Error();
                        }
                    });
                } else
                    view.ResponseOk(DataHolder.getInstance().getResMonthlyBill(), position);
                break;

            case Constants.SS_CONSUMPTION:
                SSConsumptionRequest request = (SSConsumptionRequest) object;
                showProgress(msg);
                // System.out.println("Request is " + new Gson().toJson(request));
                WebServices.getInstance().getService().getSSConsumption(request).enqueue(new Callback<ResSSConsumption>() {
                    @Override
                    public void onResponse(Call<ResSSConsumption> call, Response<ResSSConsumption> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResSSConsumption> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.MAKE_ASSETS:
                String assetType = (String) object;
                showProgress(msg);
                //System.out.println("Request is " + new Gson().toJson(assetType));
                WebServices.getInstance().getService().getMakeAssets(assetType).enqueue(new Callback<ResMakeAsset>() {
                    @Override
                    public void onResponse(Call<ResMakeAsset> call, Response<ResMakeAsset> response) {
                        dismissProgress();
                        //  System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResMakeAsset> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.TABULAR_SUMMARY_REPORT:
                AssetManagementModel assetManagementModel = (AssetManagementModel) object;
                showProgress(msg);
                // System.out.println("Request is " + new Gson().toJson(assetManagementModel));
                WebServices.getInstance().getService().getAssetsSummary(assetManagementModel).enqueue(new Callback<ResSubStationSummartVO>() {
                    @Override
                    public void onResponse(Call<ResSubStationSummartVO> call, Response<ResSubStationSummartVO> response) {
                        dismissProgress();
                        //   System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResSubStationSummartVO> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;
            case Constants.TABULAR_REPORT:
                RequestSSAssets requestSSAssets = (RequestSSAssets) object;
                showProgress(msg);
                //System.out.println("Request is " + new Gson().toJson(requestSSAssets));
                WebServices.getInstance().getService().getSubstationTabularAsset(requestSSAssets).enqueue(new Callback<ResTabularReport>() {
                    @Override
                    public void onResponse(Call<ResTabularReport> call, Response<ResTabularReport> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response.body()));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResTabularReport> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;

            case Constants.ASSETS_REPORT_SUMMARY:
                RequestSSAssets assets = (RequestSSAssets) object;
                showProgress(msg);
                System.out.println("Request is " + new Gson().toJson(assets));
                WebServices.getInstance().getService().getAssetReport(assets).enqueue(new Callback<ResAssetReport>() {
                    @Override
                    public void onResponse(Call<ResAssetReport> call, Response<ResAssetReport> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResAssetReport> call, Throwable t) {
                        dismissProgress();
                        view.Error();
                    }
                });
                break;


            case Constants.ASSETS_REPORT_DETAILS:
                RequestSSAssets detail = (RequestSSAssets) object;
                showProgress(msg);
                //System.out.println("Request is " + new Gson().toJson(detail));
                WebServices.getInstance().getService().getAssetReportDetails(detail).enqueue(new Callback<ResponseAssetDetails>() {
                    @Override
                    public void onResponse(Call<ResponseAssetDetails> call, Response<ResponseAssetDetails> response) {
                        dismissProgress();
                        // System.out.println("Response is " + new Gson().toJson(response));
                        view.ResponseOk(response.body(), position);
                    }

                    @Override
                    public void onFailure(Call<ResponseAssetDetails> call, Throwable t) {
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
