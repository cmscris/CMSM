package com.cris.cmsm;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.Sectionresponse;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.UnitValueFormatter;
import com.cris.cmsm.widget.TouchImageView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Abnormality_department extends AppCompatActivity implements ResponseView {
    ListView lv;
    CardView lvcardview;
    ImageView abnrimage;


    ImageButton camerabutton;
    EditText abnrfill;
    ImageView iv_left, iv_middle, iv_right, iv_title_icon;
    TextView abnrsubhead,abnrsubhaedentry,camera;
    Button Save,Reset2;
    LinearLayout listviewlayout;
    ArrayList<String> subheadList;
    String[]Abnormality={"OHE", "ENGG", "S&T", "C&W", "LOCO", "EMU", "DMU", "SECURITY", "TRAFFIC", "COMMERCIAL"};
    private static final int CAMERA_REQUEST = 1888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abnormality_department);
        camerabutton=(ImageButton)findViewById(R.id.camerabutton);
        camera=(TextView) findViewById(R.id.camera);
        abnrimage=(ImageView)findViewById(R.id.abnrimage);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        abnrsubhead=(TextView) findViewById(R.id.abnrsubhead);
        lvcardview=(CardView)findViewById(R.id.lvcardview);
        abnrsubhaedentry=(TextView)findViewById(R.id.abnrsubhaedentry);
        abnrfill=(EditText)findViewById(R.id.abnrfill);
        listviewlayout=(LinearLayout) findViewById(R.id.listviewlayout);
        Save=(Button)findViewById(R.id.Save);
        Reset2=(Button)findViewById(R.id.Reset2);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_title_icon=(ImageView) findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_title_icon.setVisibility(View.VISIBLE);
        RequestPresenter requestPresenter = new RequestPresenter(this);
        GraphAPIRequest request = new GraphAPIRequest();
        iv_right.setVisibility(View.VISIBLE);
        iv_left.setVisibility(View.GONE);
        listviewlayout.setVisibility(View.GONE);
        lvcardview.setVisibility(View.GONE);
       subheadList=new ArrayList <>();
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            final ArrayList<String> filleddata = extra.getStringArrayList("object");
            System.out.println("Shifted data from abnr fill to abnr dept" +filleddata);
        }

        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        iv_middle.setVisibility(View.GONE);
        lv=(ListView)findViewById(R.id.listviewabnr);
camerabutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
});

        lv.setVisibility(View.GONE);
        gridview.setAdapter(new abnormality_depart_adapter(this,Abnormality));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <?> parent,
                                    View v, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected" + position, Toast.LENGTH_SHORT).show();
                System.out.println(">>>>>>>>>>>>Enter OnItemClick" + position);
                //abnormality_depart_adapter test = (abnormality_depart_adapter) parent.getAdapter();
                String selecteditem = (String) parent.getItemAtPosition(position);
                v.setBackgroundColor(getResources().getColor(R.color.colorBlueGrey));

                // Send intent to SingleViewActivity
                ArrayList <String> abnrdepart = new ArrayList <>();
                if (selecteditem.equals("S&T")) {
                    subheadList.clear();
                    System.out.println(">>>>>>>>>>>>Enter selecteditem" +selecteditem);
                    abnrdepart.add("ST");


                }
                if (selecteditem.equals("C&W")){
                    subheadList.clear();
                    System.out.println(">>>>>>>>>>>>Enter selecteditem" +selecteditem);
                    abnrdepart.add("CW");


                }
                  //System.out.println(">>>>>>>>>>>>Enter in id==0" +(s));
                else
               System.out.println(">>>>>>>>>>>>Enter selecteditem" +selecteditem);
                abnrdepart.add(selecteditem);
                subheadList.clear();
                  request.setparamlist(abnrdepart);
                 requestPresenter.Request(request, "c", Constants.SUBHEADLIST);




                }

        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call webservice
                abnrfill.setFocusable(false);
            }
        });
        Reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abnrfill.setText("");
            }
        });
    }

    @Override
    public void ResponseOk(Object object, int position) {
        int i=0;
        if (object instanceof Sectionresponse) {
            System.out.println("Key sucess>>>>>>>>>>");
            Sectionresponse sectionresponse = (Sectionresponse) object;
            while (i < sectionresponse.getVosList().size()) {
             subheadList.add(sectionresponse.getVosList().get(i));
                System.out.println("Key sucess>>>>>>>>>>" + (sectionresponse.getVosList().get(i)));
                i++;
            }
        }
       ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subheadList);
        lv.setAdapter(adapter);
       lv.setVisibility(View.VISIBLE);
        listviewlayout.setVisibility(View.VISIBLE);
        lvcardview.setVisibility(View.VISIBLE);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                //Object o = lv.getItemAtPosition(position);
                System.out.println(">>>>>>>>>>>>Enter OnItemClick listview" +(lv.getItemAtPosition(position)));
                abnrsubhaedentry.setText(""+(lv.getItemAtPosition(position)));


            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            abnrimage.setImageBitmap(photo);

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

