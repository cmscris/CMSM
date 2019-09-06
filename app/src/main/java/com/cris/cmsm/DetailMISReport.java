package com.cris.cmsm;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.cris.cmsm.adapter.Annexure11Adapter;
import com.cris.cmsm.adapter.Annexure14AAdapter;
import com.cris.cmsm.adapter.Annexure14Adapter;
import com.cris.cmsm.adapter.Annexure14BAdapter;
import com.cris.cmsm.adapter.Annexure14CAdapter;
import com.cris.cmsm.adapter.Annexure14DAdapter;
import com.cris.cmsm.adapter.Annexure1Adapter;
import com.cris.cmsm.adapter.Annexure3Adapter;
import com.cris.cmsm.adapter.Annexure5Adapter;
import com.cris.cmsm.adapter.Annexure6Adapter;
import com.cris.cmsm.adapter.Annexure7Adapter;
import com.cris.cmsm.adapter.Annexure8Adapter;
import com.cris.cmsm.adapter.Annexure9Adapter;
import com.cris.cmsm.adapter.RB1Adapter;
import com.cris.cmsm.adapter.RB2Adapter;
import com.cris.cmsm.adapter.RB3Adapter;
import com.cris.cmsm.adapter.RB4Adapter;
import com.cris.cmsm.adapter.RB5Adapter;
import com.cris.cmsm.adapter.RB6Adapter;
import com.cris.cmsm.adapter.RB7Adapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.MISReportRequest;
import com.cris.cmsm.models.response.Annexure11vo;
import com.cris.cmsm.models.response.Annexure14AReport;
import com.cris.cmsm.models.response.Annexure14CRes;
import com.cris.cmsm.models.response.Annexure14bvo;
import com.cris.cmsm.models.response.Annexure14cvo;
import com.cris.cmsm.models.response.Annexure14dvo;
import com.cris.cmsm.models.response.Annexure1vo;
import com.cris.cmsm.models.response.Annexure3vo;
import com.cris.cmsm.models.response.Annexure6Report;
import com.cris.cmsm.models.response.Annexure7vo;
import com.cris.cmsm.models.response.Annexure8vo;
import com.cris.cmsm.models.response.Annexure9vo;
import com.cris.cmsm.models.response.AnnexureRB2vo;
import com.cris.cmsm.models.response.AnnexureRB3vo;
import com.cris.cmsm.models.response.AnnexureRB4vo;
import com.cris.cmsm.models.response.AnnexureRB5vo;
import com.cris.cmsm.models.response.AnnexureRB6vo;
import com.cris.cmsm.models.response.AnnexureRB7vo;
import com.cris.cmsm.models.response.AnnexureReport;
import com.cris.cmsm.models.response.AnnexureReportVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.MISReportResponse;
import com.cris.cmsm.models.response.RB1Response;
import com.cris.cmsm.models.response.Rb1vo;
import com.cris.cmsm.models.response.ResAnnexure1;
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
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DetailMISReport extends Activity {

    private DataBaseManager manager;
    private PinchRecyclerView recyclerView;
    private LoginIfoVO loginInfoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_report);
        loginInfoModel = new UserLoginPreferences(DetailMISReport.this).getLoginUser();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        manager = new DataBaseManager(DetailMISReport.this);
        manager.createDatabase();
        Object object = DataHolder.getInstance().getMisResponse();
        MISReportRequest misRequest = DataHolder.getInstance().getMisReportRequest();
        String selectedYear = DataHolder.getInstance().getSelectedMistYear();
        ReportHeaderView headerView = new ReportHeaderView();
        List<Railway> railwayList = manager.getRailwayListByCode(misRequest.getRailway());
        List<Division> divisionList = manager.getDivisionListByDivCode(misRequest.getDivision());
        if (railwayList.size() > 0)
            headerView.setRailway(railwayList.get(0).getFname());
        else
            headerView.setRailway(getResources().getString(R.string.indian_railway));
        if (railwayList.size() == 0)
            headerView.setZon_div("");
        else if (railwayList.size() > 0 && divisionList.size() > 0)
            headerView.setZon_div(divisionList.get(0).getFname().toUpperCase() + " DIVISION");
        else {
            if (loginInfoModel.getAuthlevel().equalsIgnoreCase(Constants.AUTH_PU) &&
                    !TextUtils.isEmpty(loginInfoModel.getRlytype()) &&
                    loginInfoModel.getRlytype().equalsIgnoreCase("P")) {
                headerView.setZon_div("");
            } else
                headerView.setZon_div("ALL DIVISIONS");
        }
        if (object instanceof MISReportResponse) {
            MISReportResponse reportResponse = (MISReportResponse) object;
            List<AnnexureReportVO> list = new ArrayList<>(reportResponse.getAnnexureReportVOs());
            Resources res = getResources();
            if (list != null && list.size() > 0) {
                headerView.setEnergyConsume(getResources().getString(R.string.energy_consumed_railway));
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                if (TextUtils.isEmpty(reportResponse.getSummary())) {
                    headerView.setSummary("");
                } else if (reportResponse.getSummary().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(reportResponse.getSummary());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + reportResponse.getSummary());
                }

                AnnexureReportVO titleModel = new AnnexureReportVO();
                titleModel.setRailway(res.getString(R.string.mis_railway));
                titleModel.setConnectedLoad(res.getString(R.string.mis_connected_load));
                titleModel.setConsumption(res.getString(R.string.mis_consumed_energy) + " " + selectedYear);
                titleModel.setBill(res.getString(R.string.mis_bill_paid) + " " + selectedYear);
                titleModel.setAvCost(res.getString(R.string.mis_avg_cost) + " " + selectedYear);
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure14Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof RB1Response) {
            RB1Response reportResponse = (RB1Response) object;
            if (reportResponse.getRb1vos().size() > 0) {
                headerView.setEnergyConsume("Traction/Non-Traction Energy Consumption \n for the year " + selectedYear + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "(ALL MONTHS)" : " (Up to " + CommonClass.currentMonth(misRequest.getMonth()) + ")"));
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                Rb1vo titleModel = new Rb1vo();
                if (DataHolder.getInstance().getMisReportRequest().getRailway().equals("")) {
                    titleModel.setRailOrDivCode("Rly");
                } else {
                    titleModel.setRailOrDivCode("Div");
                }
                titleModel.setConsumptionTrd("Traction");
                titleModel.setConsumptionGen("Non-Traction");
                titleModel.setConsumptionTotal("Total");
                titleModel.setBillingTrd("Traction");
                titleModel.setBillingGen("Non-Traction");
                titleModel.setBillingTotal("Total");
                titleModel.setAverageTrd("Traction");
                titleModel.setAverageGen("Non-Traction");
                titleModel.setAverageTotal("Overall");
                List<Rb1vo> list = new ArrayList<>(reportResponse.getRb1vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new RB1Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure14A) {
            ResAnnexure14A annexure14A = (ResAnnexure14A) object;
            Resources res = getResources();
            if (annexure14A.getAnnexure14AReports() != null && annexure14A.getAnnexure14AReports().size() > 0) {
                headerView.setEnergyConsume("Energy Consumed SEB Wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                if (TextUtils.isEmpty(annexure14A.getSummary())) {
                    headerView.setSummary("");
                } else if (annexure14A.getSummary().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(annexure14A.getSummary());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + annexure14A.getSummary());
                }
                Annexure14AReport titleModel = new Annexure14AReport();
                titleModel.setRailway("Railway");
                titleModel.setSebName("Name of SEBs");
                titleModel.setConsumption(res.getString(R.string.mis_consumed_energy) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setBill(res.getString(R.string.mis_bill_paid) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setAvCost(res.getString(R.string.mis_avg_cost) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                List<Annexure14AReport> list = new ArrayList<>(annexure14A.getAnnexure14AReports());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure14AAdapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure14B) {
            ResAnnexure14B annexure14B = (ResAnnexure14B) object;
            Resources res = getResources();
            if (annexure14B.getAnnexure14bvos() != null && annexure14B.getAnnexure14bvos().size() > 0) {
                headerView.setEnergyConsume("Energy Consumed Division & SEB Wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                if (TextUtils.isEmpty(annexure14B.getSummary())) {
                    headerView.setSummary("");
                } else if (annexure14B.getSummary().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(annexure14B.getSummary());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + annexure14B.getSummary());
                }

                Annexure14bvo titleModel = new Annexure14bvo();
                titleModel.setRailCode("Railway");
                titleModel.setDivCode("Division");
                titleModel.setSebName("Name of SEBs");
                titleModel.setConsumption(res.getString(R.string.mis_consumed_energy) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setBilling(res.getString(R.string.mis_bill_paid) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setAverage(res.getString(R.string.mis_avg_cost) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                List<Annexure14bvo> list = new ArrayList<>(annexure14B.getAnnexure14bvos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure14BAdapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure5) {
            ResAnnexure5 annexure5 = (ResAnnexure5) object;
            Resources res = getResources();
            if (annexure5.getAnnexureReports() != null && annexure5.getAnnexureReports().size() > 0) {

                AnnexureReport titleModel = new AnnexureReport();
                titleModel.setRailway("Railway");
                titleModel.setConsumption(res.getString(R.string.mis_consumed_energy) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setBill(res.getString(R.string.mis_bill_paid) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setAvCost(res.getString(R.string.mis_avg_cost) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                headerView.setEnergyConsume(getResources().getString(R.string.energy_consumed_railway));
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));

                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(annexure5.getAppsummary())) {
                    stringBuilder.append(annexure5.getAppsummary());
                }
                if (!TextUtils.isEmpty(annexure5.getNAppsummary())) {
                    stringBuilder.append(annexure5.getNAppsummary());
                }

                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }
                List<AnnexureReport> list = new ArrayList<>(annexure5.getAnnexureReports());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure5Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure6) {
            ResAnnexure6 annexure6 = (ResAnnexure6) object;
            Resources res = getResources();
            if (annexure6.getAnnexureReports() != null && annexure6.getAnnexureReports().size() > 0) {

                Annexure6Report titleModel = new Annexure6Report();
                titleModel.setRailway("Railway");
                titleModel.setSebName("Name of SEBs");
                titleModel.setConsumption(res.getString(R.string.mis_consumed_energy) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setBill(res.getString(R.string.mis_bill_paid) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");
                titleModel.setAvCost(res.getString(R.string.mis_avg_cost) + "\n" + CommonClass.currentMonth(misRequest.getMonth()) + "(" + selectedYear + ")");

                headerView.setEnergyConsume("Energy Charges Railway-SEB wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(annexure6.getAppsummary())) {
                    stringBuilder.append(annexure6.getAppsummary());
                }
                if (!TextUtils.isEmpty(annexure6.getNAppsummary())) {
                    stringBuilder.append(annexure6.getNAppsummary());
                }
                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }

                List<Annexure6Report> list = new ArrayList<>(annexure6.getAnnexureReports());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure6Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof Annexure14CRes) {
            Annexure14CRes annexure14CRes = (Annexure14CRes) object;
            Resources res = getResources();
            if (annexure14CRes.getAnnexure14cvos() != null && annexure14CRes.getAnnexure14cvos().size() > 0) {
                headerView.setEnergyConsume("Energy Consumed Year wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));

                if (TextUtils.isEmpty(annexure14CRes.getSummary())) {
                    headerView.setSummary("");
                } else if (annexure14CRes.getSummary().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(annexure14CRes.getSummary());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + annexure14CRes.getSummary());
                }
                Annexure14cvo titleModel = new Annexure14cvo();
                titleModel.setFinYear("Year");
                titleModel.setConsumption(res.getString(R.string.mis_consumed_energy));
                titleModel.setBilling(res.getString(R.string.mis_bill_paid));
                titleModel.setAverage(res.getString(R.string.mis_avg_cost));

                List<Annexure14cvo> list = new ArrayList<>(annexure14CRes.getAnnexure14cvos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure14CAdapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure14D) {
            ResAnnexure14D resAnnexure14D = (ResAnnexure14D) object;
            Resources res = getResources();
            if (resAnnexure14D.getAnnexure14dvos() != null && resAnnexure14D.getAnnexure14dvos().size() > 0) {
                Annexure14dvo titleModel = new Annexure14dvo();
                titleModel.setSebname("Name of SEBs");
                String[] year = selectedYear.split("-");
                int secYear = Integer.valueOf(year[1]);
                titleModel.setYear9(secYear - 4 + "-" + (secYear - 3));
                titleModel.setYear10(secYear - 3 + "-" + (secYear - 2));
                titleModel.setYear11(secYear - 2 + "-" + (secYear - 1));
                titleModel.setYear12(secYear - 1 + "-" + secYear);
                headerView.setEnergyConsume("Yearly Average Cost Comparison- SEB wise from " + titleModel.getYear9() + " to " + titleModel.getYear12());
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                if (TextUtils.isEmpty(resAnnexure14D.getSummary())) {
                    headerView.setSummary("");
                } else if (resAnnexure14D.getSummary().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(resAnnexure14D.getSummary());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + resAnnexure14D.getSummary());
                }
                List<Annexure14dvo> list = new ArrayList<>(resAnnexure14D.getAnnexure14dvos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure14DAdapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure1) {
            ResAnnexure1 resAnnexure1 = (ResAnnexure1) object;
            if (resAnnexure1.getAnnexure1vos() != null && resAnnexure1.getAnnexure1vos().size() > 0) {
                Annexure1vo titleModel = new Annexure1vo();
                titleModel.setSebname("Name of SEBs");
                String[] year = selectedYear.split("-");
                int secYear = Integer.valueOf(year[1]);
                titleModel.setYear9(secYear - 4 + "-" + (secYear - 3));
                titleModel.setYear10(secYear - 3 + "-" + (secYear - 2));
                titleModel.setYear11(secYear - 2 + "-" + (secYear - 1));
                titleModel.setYear12(secYear - 1 + "-" + secYear);

                headerView.setEnergyConsume("Average Cost/Unit of Traction Energy for various SEBs/Power Supply authorities from " + titleModel.getYear9() + " to " + titleModel.getYear12());
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                if (TextUtils.isEmpty(resAnnexure1.getSummary())) {
                    headerView.setSummary("");
                } else if (resAnnexure1.getSummary().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(resAnnexure1.getSummary());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + resAnnexure1.getSummary());
                }
                List<Annexure1vo> list = new ArrayList<>(resAnnexure1.getAnnexure1vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure1Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure3) {
            ResAnnexure3 resAnnexure3 = (ResAnnexure3) object;
            if (resAnnexure3.getAnnexure3vos() != null && resAnnexure3.getAnnexure3vos().size() > 0) {
                Annexure3vo titleModel = new Annexure3vo();
                titleModel.setRlycode("Railway");
                titleModel.setConsumption("Energy consumed in million units kWh\n" + "(" + selectedYear + ")");
                titleModel.setBilling("Bill paid in million Rs.\n" + "(" + selectedYear + ")");
                titleModel.setExcessSMD("Total excess MDI surcharge in million Rs.");
                titleModel.setLowpf("Total low PF surcharge in million Rs.");
                titleModel.setDelayedPayment("Total delayed payment surcharge in million Rs.");
                titleModel.setExcessConsumption("Total excess energy consumed charge in million Rs.");
                titleModel.setExcessFCA("Total excess FCA surcharge in million Rs.");
                titleModel.setAverage("Average cost of energy in Rs/kWh.");
                titleModel.setPercentage("Percentage of total surcharge to total bill paid.");
                headerView.setEnergyConsume("Energy charges along with surcharge paid Raillway wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(resAnnexure3.getAppsummary())) {
                    stringBuilder.append(resAnnexure3.getAppsummary());
                }
                if (!TextUtils.isEmpty(resAnnexure3.getNAppsummary())) {
                    stringBuilder.append(resAnnexure3.getNAppsummary());
                }

                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }

                List<Annexure3vo> list = new ArrayList<>(resAnnexure3.getAnnexure3vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure3Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure8) {
            ResAnnexure8 resAnnexure8 = (ResAnnexure8) object;
            if (resAnnexure8.getAnnexure8vos() != null && resAnnexure8.getAnnexure8vos().size() > 0) {
                Annexure8vo titleModel = new Annexure8vo();
                titleModel.setRlycode("Railway");
                titleModel.setSebs("Name of SEBs");
                titleModel.setConsumption("Energy consumed in million units kWh\n" + "(" + selectedYear + ")");
                titleModel.setBilling("Bill paid in million Rs.\n" + "(" + selectedYear + ")");
                titleModel.setExcessSMD("Total excess MD surcharge in million Rs.");
                titleModel.setLowpf("Total low PF surcharge in million Rs.");
                titleModel.setDelayedPayment("Total delayed payment surcharge in million Rs.");
                titleModel.setExcessConsumption("Total excess energy consumed charge in million Rs.");
                titleModel.setExcessFCA("Total excess FCA surcharge in million Rs.");
                titleModel.setAverage("Average cost of energy in Rs/kWh.");
                titleModel.setPercentage("Percentage of total surcharge to total bill paid.");

                headerView.setEnergyConsume("Energy charges alongwith surcharge paid Railway-SEB wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(resAnnexure8.getAppsummary())) {
                    stringBuilder.append(resAnnexure8.getAppsummary());
                }
                if (!TextUtils.isEmpty(resAnnexure8.getNAppsummary())) {
                    stringBuilder.append(resAnnexure8.getNAppsummary());
                }
                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }
                List<Annexure8vo> list = new ArrayList<>(resAnnexure8.getAnnexure8vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure8Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure9) {
            ResAnnexure9 resAnnexure9 = (ResAnnexure9) object;
            if (resAnnexure9.getAnnexure9vos() != null && resAnnexure9.getAnnexure9vos().size() > 0) {
                Annexure9vo titleModel = new Annexure9vo();
                titleModel.setState("State");
                titleModel.setRlycode("Railway");
                titleModel.setConsumption("Energy consumed in million units kWh\n" + "(" + selectedYear + ")");
                titleModel.setBilling("Bill paid in million Rs.\n" + "(" + selectedYear + ")");
                titleModel.setExcessSMD("Total excess MD surcharge in million Rs.");
                titleModel.setLowpf("Total low PF surcharge in million Rs.");
                titleModel.setDelayedPayment("Total delayed payment surcharge in million Rs.");
                titleModel.setExcessConsumption("Total excess energy consumed charge in million Rs.");
                titleModel.setExcessFCA("Total excess FCA surcharge in million Rs.");
                titleModel.setAverage("Average cost of energy in Rs/kWh.");
                titleModel.setPercentage("Percentage of total surcharge to total bill paid.");
                headerView.setEnergyConsume("Energy charges alongwith surcharge paid State-Railway wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(resAnnexure9.getAppsummary())) {
                    stringBuilder.append(resAnnexure9.getAppsummary());
                }
                if (!TextUtils.isEmpty(resAnnexure9.getNAppsummary())) {
                    stringBuilder.append(resAnnexure9.getNAppsummary());
                }
                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }
                List<Annexure9vo> list = new ArrayList<>(resAnnexure9.getAnnexure9vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure9Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure11) {
            ResAnnexure11 resAnnexure11 = (ResAnnexure11) object;
            if (resAnnexure11.getAnnexure11vos() != null && resAnnexure11.getAnnexure11vos().size() > 0) {
                Annexure11vo titleModel = new Annexure11vo();
                titleModel.setFinyear("Year");
                titleModel.setBilling("Total Bill paid in Crores Rs.");
                titleModel.setConsumption("Total Energy consumed in million units kWh");
                titleModel.setLowpf("Total low PF surcharge in Lakh Rs.");
                titleModel.setExcessSMD("Total excess MD surcharge in Lakh Rs.");
                titleModel.setExcessConsumption("Total excess energy consumed charge in Lakh Rs.");
                titleModel.setExcessFCA("Total excess FCA surcharge in million Rs.");
                titleModel.setDelayedPayment("Total delayed payment surcharge in Lakh Rs.");
                titleModel.setSurcharge("Total surcharge in Lakh Rs.");
                titleModel.setAverage("Average cost of energy in Rs/kWh.");
                titleModel.setPercentage("Percentage of total surcharge to total bill paid.");

                headerView.setEnergyConsume("DETAILS OF ENERGY CHARGES (TRACTION) PAID BY RAILWAY");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(resAnnexure11.getAppsummary())) {
                    stringBuilder.append(resAnnexure11.getAppsummary());
                }
                if (!TextUtils.isEmpty(resAnnexure11.getNAppsummary())) {
                    stringBuilder.append(resAnnexure11.getNAppsummary());
                }
                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }

                List<Annexure11vo> list = new ArrayList<>(resAnnexure11.getAnnexure11vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure11Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexure7) {
            ResAnnexure7 resAnnexure7 = (ResAnnexure7) object;
            if (resAnnexure7.getAnnexure7vos() != null && resAnnexure7.getAnnexure7vos().size() > 0) {
                Annexure7vo titleModel = new Annexure7vo();
                titleModel.setSebs("Name of SEBs");
                titleModel.setConsumption("Energy consumed in million units kWh\n" + "(" + selectedYear + ")");
                titleModel.setBilling("Bill paid in million Rs.\n" + "(" + selectedYear + ")");
                titleModel.setExcessSMD("Total excess MD surcharge in million Rs.");
                titleModel.setLowpf("Total low PF surcharge in million Rs.");
                titleModel.setDelayedPayment("Total delayed payment surcharge in million Rs.");
                titleModel.setExcessConsumption("Total excess energy consumed charge in million Rs.");
                titleModel.setExcessFCA("Total excess FCA surcharge in million Rs.");
                titleModel.setAverage("Average cost of energy in Rs/kWh.");
                titleModel.setPercentage("Percentage of total surcharge to total bill paid.");

                headerView.setEnergyConsume("Energy Charges alongwith surcharge paid SEB wise");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(resAnnexure7.getAppsummary())) {
                    stringBuilder.append(resAnnexure7.getAppsummary());
                }
                if (!TextUtils.isEmpty(resAnnexure7.getNAppsummary())) {
                    stringBuilder.append(resAnnexure7.getNAppsummary());
                }
                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }

                List<Annexure7vo> list = new ArrayList<>(resAnnexure7.getAnnexure7vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new Annexure7Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexureRB2) {
            ResAnnexureRB2 resAnnexureRB2 = (ResAnnexureRB2) object;
            if (resAnnexureRB2.getAnnexureRB2vos() != null && resAnnexureRB2.getAnnexureRB2vos().size() > 0) {
                AnnexureRB2vo titleModel = new AnnexureRB2vo();
                titleModel.setRlycode("Rly.");
                titleModel.setPconsumption("Total energy consumed in million kWh");
                titleModel.setPbilling("Total energy bill paid in million Rs.");
                titleModel.setPaverage("Avg. cost of energy in Rs/kWh.");
                titleModel.setConsumption("Total energy consumed in million kWh");
                titleModel.setBilling("Total energy bill paid in million Rs.");
                titleModel.setAverage("Avg. cost of energy in Rs/kWh.");
                titleModel.setInccon("% increase in energy consumed");
                titleModel.setInccost("% increase in energy cost");
                titleModel.setIncrate("% increase in unit rate");

                /************ Setting Header View*****************/
                headerView.setEnergyConsume("Traction/Non-Traction Electrical Energy charges & unit rate in Last Two Years");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                String[] year = selectedYear.split("-");
                int secYear = Integer.valueOf(year[1]);
                headerView.setTitleOne(secYear - 2 + "-" + (secYear - 1));
                headerView.setTitleTwo(secYear - 1 + "-" + secYear);
                if (TextUtils.isEmpty(misRequest.getMonth()))
                    headerView.setTitleThree("Increase wrt for all month of " + selectedYear);
                else
                    headerView.setTitleThree("Increase wrt for first " + Integer.valueOf(misRequest.getMonth()) + " of " + selectedYear);
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(resAnnexureRB2.getAppsummary())) {
                    stringBuilder.append(resAnnexureRB2.getAppsummary());
                }
                if (!TextUtils.isEmpty(resAnnexureRB2.getNAppsummary())) {
                    stringBuilder.append(resAnnexureRB2.getNAppsummary());
                }

                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }
                /************ Close Header View*****************/
                List<AnnexureRB2vo> list = new ArrayList<>(resAnnexureRB2.getAnnexureRB2vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new RB2Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexureRB3) {
            ResAnnexureRB3 resAnnexureRB3 = (ResAnnexureRB3) object;
            if (resAnnexureRB3.getAnnexureRB3vos() != null && resAnnexureRB3.getAnnexureRB3vos().size() > 0) {
                headerView.setEnergyConsume("Summary of Electricity Consumption (Traction and Non-Traction) in Last "
                        + (misRequest.getNoofyear() == null ? "0" : misRequest.getNoofyear()) + " Years");
                AnnexureRB3vo titleModel = new AnnexureRB3vo();
                titleModel.setFinyear("Year");
                titleModel.setConsumptionTrd("Traction");
                titleModel.setConsumptionGen("Non-Traction");
                titleModel.setConsumptionTotal("Total");
                titleModel.setBillingTrd("Traction");
                titleModel.setBillingGen("Non-Traction");
                titleModel.setBillingTotal("Total");
                titleModel.setAverageTrd("Traction");
                titleModel.setAverageGen("Non-Traction");
                titleModel.setOverAll("Overall");

                /************ Setting Header View*****************/
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                headerView.setTitleOne("Total energy consumed in million kWh");
                headerView.setTitleTwo("Total energy bill paid in million Rs.");
                headerView.setTitleThree("Average cost of energy in Rs/kWh.");
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(resAnnexureRB3.getAppsummary())) {
                    stringBuilder.append(resAnnexureRB3.getAppsummary());
                }
                if (!TextUtils.isEmpty(resAnnexureRB3.getNAppsummary())) {
                    stringBuilder.append(resAnnexureRB3.getNAppsummary());
                }

                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }
                /************ Close Header View*****************/
                List<AnnexureRB3vo> list = new ArrayList<>(resAnnexureRB3.getAnnexureRB3vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new RB3Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexureRB4) {
            ResAnnexureRB4 resAnnexureRB4 = (ResAnnexureRB4) object;
            if (resAnnexureRB4.getAnnexureRB4vos() != null && resAnnexureRB4.getAnnexureRB4vos().size() > 0) {

                double cons = 0, cons1 = 0, bill = 0, bill1 = 0;
                for (AnnexureRB4vo model :
                        resAnnexureRB4.getAnnexureRB4vos()) {
                    cons = cons + Double.valueOf(model.getConsumption());
                    cons1 = cons1 + Double.valueOf(model.getConsumption1());
                    bill = bill + Double.valueOf(model.getBilling());
                    bill1 = bill1 + Double.valueOf(model.getBilling1());
                }
                AnnexureRB4vo totalModel = new AnnexureRB4vo();
                totalModel.setSebs("GRAND TOTAL");
                totalModel.setConsumption("" + CommonClass.getRoundOff(cons));
                totalModel.setBilling("" + CommonClass.getRoundOff(bill));
                totalModel.setAverage("" + CommonClass.getRoundOff("" + cons, "" + bill * 10));
                totalModel.setConsumption1("" + CommonClass.getRoundOff(cons1));
                totalModel.setBilling1("" + CommonClass.getRoundOff(bill1));
                totalModel.setAverage1("" + CommonClass.getRoundOff("" + cons1, "" + bill1 * 10));
                resAnnexureRB4.getAnnexureRB4vos().add(totalModel);

                AnnexureRB4vo titleModel = new AnnexureRB4vo();
                titleModel.setSebs("Name of SEBs");
                titleModel.setConsumption("Total Energy Consumed in million units kWh");
                titleModel.setBilling("Total Energy Bill Paid in Crores");
                titleModel.setAverage("Average Cost of Energy in Rs/kWh");
                titleModel.setConsumption1("Total Energy Consumed in million units kWh");
                titleModel.setBilling1("Total Energy Bill Paid in Crores");
                titleModel.setAverage1("Average Cost of Energy in Rs/kWh");

                /************ Setting Header View*****************/
                headerView.setEnergyConsume("Details of Energy Charges paid SEB wise (Traction)");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                String[] year = selectedYear.split("-");
                int secYear = Integer.valueOf(year[1]);
                headerView.setTitleOne(secYear - 1 + "-" + secYear);
                headerView.setTitleTwo(secYear - 2 + "-" + (secYear - 1));

                /************ Close Header View*****************/
                List<AnnexureRB4vo> list = new ArrayList<>(resAnnexureRB4.getAnnexureRB4vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new RB4Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexureRB5) {
            ResAnnexureRB5 resAnnexureRB5 = (ResAnnexureRB5) object;
            if (resAnnexureRB5.getAnnexureRB5vos() != null && resAnnexureRB5.getAnnexureRB5vos().size() > 0) {
                double ec1 = 0, ec2 = 0, ec3 = 0, ec4 = 0, ec5 = 0;
                double eb1 = 0, eb2 = 0, eb3 = 0, eb4 = 0, eb5 = 0;
                for (AnnexureRB5vo total :
                        resAnnexureRB5.getAnnexureRB5vos()) {
                    ec1 = ec1 + Double.valueOf(total.getEngconsumed());
                    ec2 = ec2 + Double.valueOf(total.getEngconsumed1());
                    ec3 = ec3 + Double.valueOf(total.getEngconsumed2());
                    ec4 = ec4 + Double.valueOf(total.getEngconsumed3());
                    ec5 = ec5 + Double.valueOf(total.getEngconsumed4());
                    eb1 = eb1 + Double.valueOf(total.getBillpaid());
                    eb2 = eb2 + Double.valueOf(total.getBillpaid1());
                    eb3 = eb3 + Double.valueOf(total.getBillpaid2());
                    eb4 = eb4 + Double.valueOf(total.getBillpaid3());
                    eb5 = eb5 + Double.valueOf(total.getBillpaid4());
                }
                AnnexureRB5vo totalModel = new AnnexureRB5vo();
                totalModel.setRlycode("TOTAL");
                totalModel.setEngconsumed("" + CommonClass.getRoundOff(ec1));
                totalModel.setEngconsumed1("" + CommonClass.getRoundOff(ec2));
                totalModel.setEngconsumed2("" + CommonClass.getRoundOff(ec3));
                totalModel.setEngconsumed3("" + CommonClass.getRoundOff(ec4));
                totalModel.setEngconsumed4("" + CommonClass.getRoundOff(ec5));
                totalModel.setBillpaid("" + CommonClass.getRoundOff(eb1));
                totalModel.setBillpaid1("" + CommonClass.getRoundOff(eb2));
                totalModel.setBillpaid2("" + CommonClass.getRoundOff(eb3));
                totalModel.setBillpaid3("" + CommonClass.getRoundOff(eb4));
                totalModel.setBillpaid4("" + CommonClass.getRoundOff(eb5));
                totalModel.setAvgcost("" + CommonClass.getRoundOff(eb1 / ec1));
                resAnnexureRB5.getAnnexureRB5vos().add(totalModel);

                AnnexureRB5vo titleModel = new AnnexureRB5vo();
                titleModel.setRlycode("RLY");
                titleModel.setEngconsumed("EC in million kWh");
                titleModel.setEngconsumed1("EC in million kWh");
                titleModel.setEngconsumed2("EC in million kWh");
                titleModel.setEngconsumed3("EC in million kWh");
                titleModel.setEngconsumed4("EC in million kWh");
                titleModel.setBillpaid("EB paid in million Rs.");
                titleModel.setBillpaid1("EB paid in million Rs.");
                titleModel.setBillpaid2("EB paid in million Rs.");
                titleModel.setBillpaid3("EB paid in million Rs.");
                titleModel.setBillpaid4("EB paid in million Rs.");
                titleModel.setAvgcost("Average  Cost of  energy in  Rs/kWh");

                /************ Setting Header View*****************/
                headerView.setEnergyConsume("RLY WISE(TRACTION) ENERGY CONSUMPTION IN LAST 5 YEARS");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                String[] year = selectedYear.split("-");
                int secYear = Integer.valueOf(year[1]);
                headerView.setTitleOne(secYear - 1 + "-" + secYear);
                headerView.setTitleTwo(secYear - 2 + "-" + (secYear - 1));
                headerView.setTitleThree(secYear - 3 + "-" + (secYear - 2));
                headerView.setTitleFour(secYear - 4 + "-" + (secYear - 3));
                headerView.setTitleFive(secYear - 5 + "-" + (secYear - 4));
                /************ Close Header View*****************/
                List<AnnexureRB5vo> list = new ArrayList<>(resAnnexureRB5.getAnnexureRB5vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new RB5Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexureRB6) {
            ResAnnexureRB6 resAnnexureRB6 = (ResAnnexureRB6) object;
            if (resAnnexureRB6.getAnnexureRB6vos() != null && resAnnexureRB6.getAnnexureRB6vos().size() > 0) {
                double ec1 = 0, ec2 = 0, ec3 = 0, ec4 = 0, ec5 = 0;
                double eb1 = 0, eb2 = 0, eb3 = 0, eb4 = 0, eb5 = 0;
                for (AnnexureRB6vo total :
                        resAnnexureRB6.getAnnexureRB6vos()) {
                    ec1 = ec1 + Double.valueOf(total.getEngconsumed());
                    ec2 = ec2 + Double.valueOf(total.getEngconsumed1());
                    ec3 = ec3 + Double.valueOf(total.getEngconsumed2());
                    ec4 = ec4 + Double.valueOf(total.getEngconsumed3());
                    ec5 = ec5 + Double.valueOf(total.getEngconsumed4());
                    eb1 = eb1 + Double.valueOf(total.getBillpaid());
                    eb2 = eb2 + Double.valueOf(total.getBillpaid1());
                    eb3 = eb3 + Double.valueOf(total.getBillpaid2());
                    eb4 = eb4 + Double.valueOf(total.getBillpaid3());
                    eb5 = eb5 + Double.valueOf(total.getBillpaid4());
                }
                AnnexureRB6vo totalModel = new AnnexureRB6vo();
                totalModel.setRlycode("TOTAL");
                totalModel.setEngconsumed("" + CommonClass.getRoundOff(ec1));
                totalModel.setEngconsumed1("" + CommonClass.getRoundOff(ec2));
                totalModel.setEngconsumed2("" + CommonClass.getRoundOff(ec3));
                totalModel.setEngconsumed3("" + CommonClass.getRoundOff(ec4));
                totalModel.setEngconsumed4("" + CommonClass.getRoundOff(ec5));
                totalModel.setBillpaid("" + CommonClass.getRoundOff(eb1));
                totalModel.setBillpaid1("" + CommonClass.getRoundOff(eb2));
                totalModel.setBillpaid2("" + CommonClass.getRoundOff(eb3));
                totalModel.setBillpaid3("" + CommonClass.getRoundOff(eb4));
                totalModel.setBillpaid4("" + CommonClass.getRoundOff(eb5));
                totalModel.setAvgcost("" + CommonClass.getRoundOff(eb1 / ec1));
                resAnnexureRB6.getAnnexureRB6vos().add(totalModel);

                AnnexureRB6vo titleModel = new AnnexureRB6vo();
                titleModel.setRlycode("RLY");
                titleModel.setEngconsumed("EC in million kWh");
                titleModel.setEngconsumed1("EC in million kWh");
                titleModel.setEngconsumed2("EC in million kWh");
                titleModel.setEngconsumed3("EC in million kWh");
                titleModel.setEngconsumed4("EC in million kWh");
                titleModel.setBillpaid("EB paid in million Rs.");
                titleModel.setBillpaid1("EB paid in million Rs.");
                titleModel.setBillpaid2("EB paid in million Rs.");
                titleModel.setBillpaid3("EB paid in million Rs.");
                titleModel.setBillpaid4("EB paid in million Rs.");
                titleModel.setAvgcost("Average  Cost of  energy in  Rs/kWh");

                /************ Setting Header View*****************/
                headerView.setEnergyConsume("RAILWAY WISE(NON-TRACTION) ENERGY CONSUMPTION IN LAST 5 YEARS");
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                String[] year = selectedYear.split("-");
                int secYear = Integer.valueOf(year[1]);
                headerView.setTitleOne(secYear - 1 + "-" + secYear);
                headerView.setTitleTwo(secYear - 2 + "-" + (secYear - 1));
                headerView.setTitleThree(secYear - 3 + "-" + (secYear - 2));
                headerView.setTitleFour(secYear - 4 + "-" + (secYear - 3));
                headerView.setTitleFive(secYear - 5 + "-" + (secYear - 4));
                /************ Close Header View*****************/
                List<AnnexureRB6vo> list = new ArrayList<>(resAnnexureRB6.getAnnexureRB6vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new RB6Adapter(DetailMISReport.this, headerView, list));
            }
        } else if (object instanceof ResAnnexureRB7) {
            ResAnnexureRB7 reportResponse = (ResAnnexureRB7) object;
            if (reportResponse.getAnnexureRB7vos().size() > 0 && reportResponse.getIsSuccess()) {
                headerView.setEnergyConsume("Traction/Non-Traction Energy Consumption \n for the year " + selectedYear + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "(ALL MONTHS)" : " (Up to " + CommonClass.currentMonth(misRequest.getMonth()) + ")"));
                headerView.setMonth("Fin. Year " + selectedYear + "\n" + "Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                for (AnnexureRB7vo model :
                        reportResponse.getAnnexureRB7vos()) {
                    model.setAverageGen("" + CommonClass.getRoundOff(model.getConsumptionGen(), model.getBillingGen()));
                    model.setAverageTrd("" + CommonClass.getRoundOff(model.getConsumptionTrd(), model.getBillingTrd()));
                    model.setOverall("" + CommonClass.getRoundOff(model.getConsumptionTotal(), model.getBillingTotal()));
                }
                AnnexureRB7vo titleModel = new AnnexureRB7vo();
                titleModel.setRlycode("Rly.");
                titleModel.setDiv("Div.");
                titleModel.setConsumptionTrd("Traction");
                titleModel.setConsumptionGen("Non-Traction");
                titleModel.setConsumptionTotal("Total");
                titleModel.setBillingTrd("Traction");
                titleModel.setBillingGen("Non-Traction");
                titleModel.setBillingTotal("Total");
                titleModel.setAverageTrd("Traction");
                titleModel.setAverageGen("Non-Traction");
                titleModel.setOverall("Overall");
                List<AnnexureRB7vo> list = new ArrayList<>(reportResponse.getAnnexureRB7vos());
                list.add(0, titleModel);
                recyclerView.setAdapter(new RB7Adapter(DetailMISReport.this, headerView, list));
            }
        }
    }
}
