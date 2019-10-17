package com.cris.cmsm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.cris.cmsm.adapter.Licrewstatusadapter;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.InsertRequestPresenter;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Crew_Current_Status extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private ArrayList<LICrewMonitoredResponse> LIRESignon,LIREScall,LIRESrest,LIRESabsent;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private LICrewMonitoredResponseVO liCrewMonitoredResponseVO;
    private LoginIfoVO loginInfoModel;
    GraphAPIRequest request;
    ArrayList<String> reqloginid;
    private UserLoginPreferences userLoginPreferences;
    private RequestPresenter requestPresenter;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew__current__status);
        requestPresenter = new RequestPresenter(Crew_Current_Status.this);
        commonClass = new CommonClass(Crew_Current_Status.this);
        recyclerView = (PinchRecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userLoginPreferences = new UserLoginPreferences(Crew_Current_Status.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        LIRESabsent=new ArrayList <>();
        LIRESrest=new ArrayList <>();
        LIREScall=new ArrayList <>();
        LIRESignon=new ArrayList <>();
        reqloginid=new ArrayList <>();
        request = new GraphAPIRequest();

                reqloginid.add(loginInfoModel.getLoginid());
                request.setparamlist(reqloginid);
                requestPresenter.Request(request, "Getting Crew Monitored", Constants.LICREWSTATUS);
    }

    @Override
    public void OnItemClick(Object model) {

    }




    @Override
    public void ResponseOk(Object object, int position) {
        if(object instanceof LICrewMonitoredResponse){


            LICrewMonitoredResponse liCrewResponse = (LICrewMonitoredResponse) object;

                    System.out.println("Response is " + new Gson().toJson(liCrewResponse));

                    if (liCrewResponse != null && liCrewResponse.getLiCrewMonitoredResponseVO() != null) {
                        //GraphAPIRequest graphAPIRequest = DataHolder.getInstance().getGraphAPIRequest();
                        ReportHeaderView reportHeaderView = new ReportHeaderView();
                        String header_str = "LI : " + loginInfoModel.getLoginid();

                        header_str += "\n Crew Current Status";
                        reportHeaderView.setEnergyConsume(header_str);
                        LICrewMonitoredResponse cvo = new LICrewMonitoredResponse("", "", "", "", "", "", "", "", "", "", "", "");
                        cvo.setSn("SN");
                        cvo.setCrewid("Crew ID");
                        cvo.setCrewname("Name");
                        cvo.setDesig("Desig");
                        cvo.setStatus("Status");
                        cvo.setAvlsttn("Available Sttn");
                        cvo.setFrmsttn("From Sttn");
                        cvo.setTosttn("To Sttn");
                        cvo.setCalltime("SIGNON Time");
                        cvo.setBooktime("SIGNOFF Time");
                        cvo.setAvailtym("Available Time");
                        cvo.setLastsignofftym("Last Signoff Time");
                        LICrewMonitoredResponse cvo2 = new LICrewMonitoredResponse("", "", "", "", "", "", "", "", "", "", "", "");
                        cvo2.setSn("SN");
                        cvo2.setCrewid("Crew ID");
                        cvo2.setCrewname("Name");
                        cvo2.setDesig("Desig");
                        cvo2.setStatus("Status");
                        cvo2.setAvlsttn("Available Sttn");
                        cvo2.setFrmsttn("From Sttn");
                        cvo2.setTosttn("To Sttn");
                        cvo2.setCalltime("Call Time");
                        cvo2.setBooktime(" Book Time");
                        cvo2.setAvailtym("Available Time");
                        cvo2.setLastsignofftym("Last Signoff Time");
                        LICrewMonitoredResponse cvo3 = new LICrewMonitoredResponse("", "", "", "", "", "", "", "", "", "", "", "");
                        cvo3.setSn("SN");
                        cvo3.setCrewid("Crew ID");
                        cvo3.setCrewname("Name");
                        cvo3.setDesig("Desig");
                        cvo3.setStatus("Status");
                        cvo3.setAvlsttn("Available Sttn");
                        cvo3.setFrmsttn("From Sttn");
                        cvo3.setTosttn("To Sttn");
                        cvo3.setCalltime("Start Time");
                        cvo3.setBooktime(" END Time");
                        cvo3.setAvailtym("Available Time");
                        cvo3.setLastsignofftym("Last Signoff Time");

                        while (i < liCrewResponse.getLiCrewMonitoredResponseVO().size()) {
                            if (liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("SIGNON") || liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("SGNOFF")) {


                                String sn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getSno();
                                String crewid = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewid();
                                String crewname = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewname();
                                String desig = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getDesignation();
                                String status = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus();
                                String avlsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailablesttn();
                                String frmsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getFromsttn();
                                String tosttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getTosttn();
                                String calltime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCalltime();
                                String booktime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getBooktime();
                                String availtym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailabletime();
                                String lastsignofftym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getLastsignofftime();
                                LICrewMonitoredResponse lires = new LICrewMonitoredResponse(sn, crewid, crewname, desig, status, avlsttn, frmsttn, tosttn, calltime, booktime, availtym, lastsignofftym);

                                LIRESignon.add(lires);
                                //int size1=LIRES.size();


                            }
                            if (liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("CALLED") || liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("BOOKED")) {
                                String sn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getSno();
                                String crewid = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewid();
                                String crewname = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewname();
                                String desig = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getDesignation();
                                String status = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus();
                                String avlsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailablesttn();
                                String frmsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getFromsttn();
                                String tosttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getTosttn();
                                String calltime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCalltime();
                                String booktime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getBooktime();
                                String availtym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailabletime();
                                String lastsignofftym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getLastsignofftime();
                                LICrewMonitoredResponse lires = new LICrewMonitoredResponse(sn, crewid, crewname, desig, status, avlsttn, frmsttn, tosttn, calltime, booktime, availtym, lastsignofftym);

                                LIREScall.add(lires);

                            }

                            if (liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("REST") || liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("SYSLT") || liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("SYSNE") || liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("SYSRE")) {
                                String sn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getSno();
                                String crewid = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewid();
                                String crewname = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewname();
                                String desig = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getDesignation();
                                String status = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus();
                                String avlsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailablesttn();
                                String frmsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getFromsttn();
                                String tosttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getTosttn();
                                String calltime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCalltime();
                                String booktime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getBooktime();
                                String availtym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailabletime();
                                String lastsignofftym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getLastsignofftime();
                                LICrewMonitoredResponse lires = new LICrewMonitoredResponse(sn, crewid, crewname, desig, status, avlsttn, frmsttn, tosttn, calltime, booktime, availtym, lastsignofftym);
                                LIRESrest.add(lires);
                                //LIRES2.size();
                            }
                            if (liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("ABSENT") || liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("LEAVE") || liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus().equals("NONRUN")) {
                                String sn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getSno();
                                String crewid = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewid();
                                String crewname = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCrewname();
                                String desig = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getDesignation();
                                String status = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getStatus();
                                String avlsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailablesttn();
                                String frmsttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getFromsttn();
                                String tosttn = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getTosttn();
                                String calltime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getCalltime();
                                String booktime = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getBooktime();
                                String availtym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getAvailabletime();
                                String lastsignofftym = liCrewResponse.getLiCrewMonitoredResponseVO().get(i).getLastsignofftime();
                                LICrewMonitoredResponse lires = new LICrewMonitoredResponse(sn, crewid, crewname, desig, status, avlsttn, frmsttn, tosttn, calltime, booktime, availtym, lastsignofftym);
                                LIRESabsent.add(lires);


                            }
                            i++;
                        }


                        int j = 0;
                        if (LIRESrest.size() == 0) {
                            System.out.println("No Data Available");
                        } else {
                            LIRESrest.add(0, cvo);
                            while (j < LIRESrest.size()) {
                                j++;
                            }
                        }
                        j = 0;
                        if (LIREScall.size() == 0) {
                            System.out.println("No Data Available");
                        } else {
                            LIRESrest.add(cvo2);
                            while (j < LIREScall.size()) {
                                LIRESrest.add(LIREScall.get(j));
                                j++;

                            }

                        }
                        if (LIRESignon.size() == 0) {
                            System.out.println("No Data Available");

                        } else {
                            j = 0;
                            LIRESrest.add(cvo);
                            while (j < LIRESignon.size()) {
                                LIRESrest.add(LIRESignon.get(j));
                                j++;

                            }
                        }
                        if (LIRESabsent.size() == 0) {
                            System.out.println("No Data Available");
                        } else {
                            LIRESrest.add(cvo3);
                            j = 0;
                            while (j < LIRESabsent.size()) {
                                LIRESrest.add(LIRESabsent.get(j));
                                j++;

                            }
                        }


                        recyclerView.setAdapter(new Licrewstatusadapter(Crew_Current_Status.this, reportHeaderView, Crew_Current_Status.this, LIRESrest));
                             }
            }
        }



                @Override
    public void Error() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showProgress(String msg) {

    }
}
