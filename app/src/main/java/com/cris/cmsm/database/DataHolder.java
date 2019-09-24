package com.cris.cmsm.database;

import com.cris.cmsm.models.BoardStateModel;
import com.cris.cmsm.models.request.AssetManagementModel;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.EnergyConsumptionRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.LTConnectionRequest;
import com.cris.cmsm.models.request.MISReportRequest;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.request.SSConsumptionRequest;
import com.cris.cmsm.models.request.SaveConsumption;
import com.cris.cmsm.models.request.SubStationConsumption;
import com.cris.cmsm.models.response.AbnormalityResponse;
import com.cris.cmsm.models.response.CrewAvailabilityDetailResponse;
import com.cris.cmsm.models.response.IrregularCrewResponse;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.VcdStatusResponse;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.ConSummaryResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.CrewMileageDetailsVO;
import com.cris.cmsm.models.response.CrewPositionSummaryResponse;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.ResAssetReport;
import com.cris.cmsm.models.response.ResLTConnection;
import com.cris.cmsm.models.response.ResMonthlyBill;
import com.cris.cmsm.models.response.ResMonthlyCons;
import com.cris.cmsm.models.response.ResSSConsumption;
import com.cris.cmsm.models.response.ResSubStationSummartVO;
import com.cris.cmsm.models.response.ResTabularReport;
import com.cris.cmsm.models.response.ResponseAssetDetails;
import com.cris.cmsm.models.response.SubStationRes;
import com.cris.cmsm.models.response.SubStationResponse;
import com.cris.cmsm.models.response.SubstationResponseVO;
import com.cris.cmsm.models.response.TabularReportVO;
import com.cris.cmsm.models.response.ThreeYearData;
import com.cris.cmsm.models.response.VosList;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DataHolder {


    private static int level = 0;





    private static int global_level = 0;
    private static String zone = "";
    private static String division = "";
    private static String lobby = "";
    private static int type = 0;
    private static String login_type="";
    private static String crewid="";
    private static String traction="";
    private static String userid="";
    public static String password="";
    public static String crewId="";
    public static String abnrNumber="";
    public static String roleid="";
    public static String rlycode="";
    public static String fname="";
    public static String AbnrType="";
    public static ArrayList<String> limovlist;
    public static ArrayList<ArrayList<String>> limovmainlist;

    public static ArrayList <ArrayList <String>> getLimovmainlist() {
        return limovmainlist;
    }

    public static void setLimovmainlist(ArrayList <ArrayList <String>> limovmainlist) {
        DataHolder.limovmainlist = limovmainlist;
    }



    public static ArrayList <String> getLimovlist() {
        return limovlist;
    }

    public static void setLimovlist(ArrayList <String> limovlist) {
        DataHolder.limovlist = limovlist;
    }


    public static String getLoginid() {
        return loginid;
    }

    public static void setLoginid(String loginid) {
        DataHolder.loginid = loginid;
    }

    public static String loginid="";


    public static String Designation="";

    public static String getDesignation() {
        return Designation;
    }

    public static void setDesignation(String designation) {
        Designation = designation;
    }


    public static String getAbnrType() {
        return AbnrType;
    }

    public static void setAbnrType(String abnrType) {
        AbnrType = abnrType;
    }



    public static String getFname() {
        return fname;
    }

    public static void setFname(String fname) {
        DataHolder.fname = fname;
    }



    private static DataHolder instance = null;
    private ConsumptionResponse railWayConsumption;
    private BillingResponse railWayBilling;
    private ResMonthlyCons resMonthlyCons;
    private ResMonthlyBill resMonthlyBill;
    private Object misResponse;
    private ThreeYearData threeYearData;
    private BoardStateModel boardStateModel;
    private ConSummaryRequest conSummaryRequest;
    private SubStationResponse subStationResponse;
    private SaveConsumption saveConsumption;
    private SubStationConsumption consumption;
    private MISReportRequest misReportRequest;
    private BillingResponse billingResponse;
    private ConsumptionResponse consumptionResponse;
    private SubStationRes subStationRes;
    private TabularReportVO tabularReportVO;
    private ResponseAssetDetails summaryResponse;
    private RequestSSAssets summaryRequest;
    private List<CrewPositionSummaryResponse> CrewPositionSummaryResponses;
    private Paramresponse paramresponse;



    public List <VosList> getVosLists() {
        return vosLists;
    }

    public void setVosLists(List <VosList> vosLists) {
        this.vosLists = vosLists;
    }

    private List<VosList> vosLists;


    private CrewUtilResponse crewUtilResponse;
    private GraphAPIRequest graphAPIRequest;
    private AbnormalityResponse abnormalityResponse;
    private VcdStatusResponse vcdStatusResponse;
    private LICrewMonitoredResponse liCrewMonitoredResponse;
    private CrewMileageDetailsVO crewMileageDetailsVO;
    private EnergyConsumptionRequest energyConsumptionRequest;
    private CrewAvailabilityDetailResponse crewAvailabilityDetailResponse;
    private IrregularCrewResponse irregularCrewResponse;

    public static String getUserid(){ return userid;}
    public static void setUserid(String userid){DataHolder.userid=userid;}
    public static String getPassword(){return password;}
    public static void setPassword(String password){DataHolder.password=password;}
    public Paramresponse getParamresponse() {
        return paramresponse;
    }
    public static String getCrewId(){return crewId;}
    public static void setCrewId(String crewId){DataHolder.crewId=crewId;}
    public static String getAbnrNumber(){return abnrNumber;}
    public static void setAbnrNumber(String abnrNumber){DataHolder.abnrNumber=abnrNumber;}
    public static String getRoleid(){return roleid;}
    public static void setRoleid(String roleid){DataHolder.roleid=roleid;}
    public static String getRlycode(){return rlycode;}
    public static void setRlycode(String rlycode){DataHolder.rlycode=rlycode;}

    public static String getTraction() {
        return traction;
    }

    public static void setTraction(String traction) {
        DataHolder.traction = traction;
    }

    public EnergyConsumptionRequest getEnergyConsumptionRequest() {
        return energyConsumptionRequest;
    }

    public void setEnergyConsumptionRequest(EnergyConsumptionRequest energyConsumptionRequest) {
        this.energyConsumptionRequest = energyConsumptionRequest;
    }

    public CrewMileageDetailsVO getCrewMileageDetailsVO() {
        return crewMileageDetailsVO;
    }

    public void setCrewMileageDetailsVO(CrewMileageDetailsVO crewMileageDetailsVO) {
        this.crewMileageDetailsVO = crewMileageDetailsVO;
    }

    public static String getCrewid() {
        return crewid;
    }

    public static void setCrewid(String crewid) {
        DataHolder.crewid = crewid;
    }

    public LICrewMonitoredResponse getLiCrewMonitoredResponse() {
        return liCrewMonitoredResponse;
    }

    public void setLiCrewMonitoredResponse(LICrewMonitoredResponse liCrewMonitoredResponse) {
        this.liCrewMonitoredResponse = liCrewMonitoredResponse;
    }

    public static void setInstance(DataHolder instance) {
        DataHolder.instance = instance;
    }

    public AbnormalityResponse getAbnormalityResponse() {
        return abnormalityResponse;
    }

    public void setAbnormalityResponse(AbnormalityResponse abnormalityResponse) {
        this.abnormalityResponse = abnormalityResponse;
    }

    public IrregularCrewResponse getIrregularCrewResponse() {
        return irregularCrewResponse;
    }

    public void setIrregularCrewResponse(IrregularCrewResponse irregularCrewResponse) {
        this.irregularCrewResponse = irregularCrewResponse;
    }

    public VcdStatusResponse getVcdStatusResponse() {
        return vcdStatusResponse;
    }

    public void setVcdStatusResponse(VcdStatusResponse vcdStatusResponse) {
        this.vcdStatusResponse = vcdStatusResponse;
    }

    public static String getLogin_type() {
        return login_type;
    }

    public static void setLogin_type(String login_type) {
        DataHolder.login_type = login_type;
    }

    public GraphAPIRequest getGraphAPIRequest() {
        return graphAPIRequest;
    }

    public void setGraphAPIRequest(GraphAPIRequest graphAPIRequest) {
        this.graphAPIRequest = graphAPIRequest;
    }

    public CrewUtilResponse getCrewUtilResponse() {
        return crewUtilResponse;
    }

    public void setCrewUtilResponse(CrewUtilResponse crewUtilResponse) {
        this.crewUtilResponse = crewUtilResponse;
    }

    public List<CrewPositionSummaryResponse> getCrewPositionSummaryResponses() {
        return CrewPositionSummaryResponses;
    }

    public void setCrewPositionSummaryResponses(List<CrewPositionSummaryResponse> crewPositionSummaryResponses) {
        CrewPositionSummaryResponses = crewPositionSummaryResponses;
    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        DataHolder.type = type;
    }


    public static int getGlobal_level() {
        return global_level;
    }

    public static void setGlobal_level(int global_level) {
        DataHolder.global_level = global_level;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        DataHolder.level = level;
    }


    public static String getZone() {
        return zone;
    }

    public static void setZone(String zone) {
        DataHolder.zone = zone;
    }

    public static String getDivision() {
        return division;
    }

    public static void setDivision(String division) {
        DataHolder.division = division;
    }

    public static String getLobby() {
        return lobby;
    }

    public static void setLobby(String lobby) {
        DataHolder.lobby = lobby;
    }

    public SubstationResponseVO getSubstationResponseVO() {
        return substationResponseVO;
    }

    public void setSubstationResponseVO(SubstationResponseVO substationResponseVO) {
        this.substationResponseVO = substationResponseVO;
    }

    private SubstationResponseVO substationResponseVO;

    public RequestSSAssets getSummaryRequest() {
        return summaryRequest;
    }

    public void setSummaryRequest(RequestSSAssets summaryRequest) {
        this.summaryRequest = summaryRequest;
    }

    public CrewAvailabilityDetailResponse getCrewAvailabilityDetailResponse() {
        return crewAvailabilityDetailResponse;
    }

    public void setCrewAvailabilityDetailResponse(CrewAvailabilityDetailResponse crewAvailabilityDetailResponse) {
        this.crewAvailabilityDetailResponse = crewAvailabilityDetailResponse;
    }
    public ResponseAssetDetails getSummaryResponse() {
        return summaryResponse;
    }

    public void setSummaryResponse(ResponseAssetDetails summaryResponse) {
        this.summaryResponse = summaryResponse;
    }

    public ResAssetReport getResAssetReport() {
        return resAssetReport;
    }

    public void setResAssetReport(ResAssetReport resAssetReport) {
        this.resAssetReport = resAssetReport;
    }

    private ResAssetReport resAssetReport;

   /* public boolean isHTPanel() {
        return isHTPanel;
    }

    public void setHTPanel(boolean HTPanel) {
        isHTPanel = HTPanel;
    }*/

    //private boolean isHTPanel;

    public TabularReportVO getTabularReportVO() {
        return tabularReportVO;
    }

    public void setTabularReportVO(TabularReportVO tabularReportVO) {
        this.tabularReportVO = tabularReportVO;
    }

    public ResSubStationSummartVO getResSubStationSummartVO() {
        return resSubStationSummartVO;
    }

    public void setResSubStationSummartVO(ResSubStationSummartVO resSubStationSummartVO) {
        this.resSubStationSummartVO = resSubStationSummartVO;
    }

    private ResSubStationSummartVO resSubStationSummartVO;

    public ResTabularReport getTabularReport() {
        return tabularReport;
    }

    public void setTabularReport(ResTabularReport tabularReport) {
        this.tabularReport = tabularReport;
    }

    private ResTabularReport tabularReport;

    public RequestSSAssets getRequestSSAssets() {
        return requestSSAssets;
    }

    public void setRequestSSAssets(RequestSSAssets requestSSAssets) {
        this.requestSSAssets = requestSSAssets;
    }


    public AssetManagementModel getAssetManagementModel() {
        return assetManagementModel;
    }

    public void setAssetManagementModel(AssetManagementModel assetManagementModel) {
        this.assetManagementModel = assetManagementModel;
    }

    private AssetManagementModel assetManagementModel;

    private RequestSSAssets requestSSAssets;

    public SubStationRes getSubStationRes() {
        return subStationRes;
    }

    public void setSubStationRes(SubStationRes subStationRes) {
        this.subStationRes = subStationRes;
    }

    public SSConsumptionRequest getSsConsumptionRequest() {
        return ssConsumptionRequest;
    }

    public void setSsConsumptionRequest(SSConsumptionRequest ssConsumptionRequest) {
        this.ssConsumptionRequest = ssConsumptionRequest;
    }

    private SSConsumptionRequest ssConsumptionRequest;

    public ResSSConsumption getResSSConsumption() {
        return resSSConsumption;
    }

    public void setResSSConsumption(ResSSConsumption resSSConsumption) {
        this.resSSConsumption = resSSConsumption;
    }

    private ResSSConsumption resSSConsumption;

    public ResLTConnection getResLTConnection() {
        return resLTConnection;
    }

    public void setResLTConnection(ResLTConnection resLTConnection) {
        this.resLTConnection = resLTConnection;
    }

    private ResLTConnection resLTConnection;

    public LTConnectionRequest getLtConnectionRequest() {
        return ltConnectionRequest;
    }

    public void setLtConnectionRequest(LTConnectionRequest ltConnectionRequest) {
        this.ltConnectionRequest = ltConnectionRequest;
    }

    private LTConnectionRequest ltConnectionRequest;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (instance == null)
            instance = new DataHolder();
        return instance;
    }

    public Object getMisResponse() {
        return misResponse;
    }

    public void setMisResponse(Object misResponse) {
        this.misResponse = misResponse;
    }

    public ConsumptionResponse getConsumptionResponse() {
        return consumptionResponse;
    }

    public void setConsumptionResponse(ConsumptionResponse consumptionResponse) {
        this.consumptionResponse = consumptionResponse;
    }

    public BillingResponse getBillingResponse() {
        return billingResponse;
    }

    public void setBillingResponse(BillingResponse billingResponse) {
        this.billingResponse = billingResponse;
    }

    public String getSelectedMistYear() {
        return selectedMistYear;
    }

    public void setSelectedMistYear(String selectedMistYear) {
        this.selectedMistYear = selectedMistYear;
    }

    String selectedMistYear;


    public ConSummaryRequest getConSummaryRequest() {
        return conSummaryRequest;
    }

    public void setConSummaryRequest(ConSummaryRequest conSummaryRequest) {
        this.conSummaryRequest = conSummaryRequest;
    }


    public SubStationResponse getSubStationResponse() {
        return subStationResponse;
    }

    public void setSubStationResponse(SubStationResponse subStationResponse) {
        this.subStationResponse = subStationResponse;
    }


    public SaveConsumption getSaveConsumption() {
        return saveConsumption;
    }

    public void setSaveConsumption(SaveConsumption saveConsumption) {
        this.saveConsumption = saveConsumption;
    }

    public SubStationConsumption getConsumption() {
        return consumption;
    }

    public void setConsumption(SubStationConsumption consumption) {
        this.consumption = consumption;
    }

    public String getSebDiv() {
        return sebDiv;
    }

    public void setSebDiv(String sebDiv) {
        this.sebDiv = sebDiv;
    }

    String sebDiv = "";

    public String getZoomGraphTitle() {
        return zoomGraphTitle;
    }

    public void setZoomGraphTitle(String zoomGraphTitle) {
        this.zoomGraphTitle = zoomGraphTitle;
    }

    String zoomGraphTitle = "";

    public MISReportRequest getMisReportRequest() {
        return misReportRequest;
    }

    public void setMisReportRequest(MISReportRequest misReportRequest) {
        this.misReportRequest = misReportRequest;
    }

    public List<ConSummaryResponse> getConSummaryResponses() {
        return conSummaryResponses;
    }

    public void setConSummaryResponses(List<ConSummaryResponse> conSummaryResponses) {
        this.conSummaryResponses = conSummaryResponses;
    }

    private List<ConSummaryResponse> conSummaryResponses;

    public String getZoomThreeYearTitle() {
        return zoomThreeYearTitle;
    }

    public void setZoomThreeYearTitle(String zoomThreeYearTitle) {
        this.zoomThreeYearTitle = zoomThreeYearTitle;
    }

    String zoomThreeYearTitle = "";

    public ThreeYearData getThreeYearData() {
        return threeYearData;
    }

    public void setThreeYearData(ThreeYearData threeYearData) {
        this.threeYearData = threeYearData;
    }

    public BoardStateModel getBoardStateModel() {
        return boardStateModel;
    }

    public void setBoardStateModel(BoardStateModel boardStateModel) {
        this.boardStateModel = boardStateModel;
    }

    public BillingResponse getRailWayBilling() {
        return railWayBilling;
    }

    public void setRailWayBilling(BillingResponse railWayBilling) {
        this.railWayBilling = railWayBilling;
    }


    public ConsumptionResponse getRailWayConsumption() {
        return railWayConsumption;
    }

    public void setRailWayConsumption(ConsumptionResponse railWayConsumption) {
        this.railWayConsumption = railWayConsumption;
    }


    public ResMonthlyBill getResMonthlyBill() {
        return resMonthlyBill;
    }

    public void setResMonthlyBill(ResMonthlyBill resMonthlyBill) {
        this.resMonthlyBill = resMonthlyBill;
    }

    public ResMonthlyCons getResMonthlyCons() {
        return resMonthlyCons;
    }

    public void setResMonthlyCons(ResMonthlyCons resMonthlyCons) {
        this.resMonthlyCons = resMonthlyCons;
    }


    public void AssignNull() {
        DataHolder.getInstance().setConsumptionResponse(null);
        DataHolder.getInstance().setBillingResponse(null);
        DataHolder.getInstance().setConSummaryRequest(null);
        DataHolder.getInstance().setConSummaryResponses(null);
        DataHolder.getInstance().setZoomGraphTitle(null);
        DataHolder.getInstance().setRailWayConsumption(null);
        DataHolder.getInstance().setRailWayBilling(null);
        DataHolder.getInstance().setResMonthlyCons(null);
        DataHolder.getInstance().setResMonthlyBill(null);
    }
}
