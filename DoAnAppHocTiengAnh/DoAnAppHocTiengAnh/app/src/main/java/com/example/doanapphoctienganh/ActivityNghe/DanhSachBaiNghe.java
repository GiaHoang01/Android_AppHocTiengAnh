package com.example.doanapphoctienganh.ActivityNghe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.doanapphoctienganh.Adapter.Adapter_Nghe;
import com.example.doanapphoctienganh.Database.Class.Nghe;
import com.example.doanapphoctienganh.R;
import com.example.doanapphoctienganh.TrangChu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DanhSachBaiNghe extends AppCompatActivity {

    ListView lv_BaiNghe;

    EditText txt_TimKiem;
    ImageButton btn_TimKiem,btn_QuayLai;
    Adapter_Nghe adapterNghe;


    private ArrayList<Nghe> mList=new ArrayList<>();
    private ArrayList<Nghe> mFilteredList = new ArrayList<>();

    String url="http://192.168.1.170:3000/danhsachnghe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_sach_bai_nghe);
        txt_TimKiem=(EditText) findViewById(R.id.txt_TimKiem);
        btn_TimKiem=(ImageButton) findViewById(R.id.btn_TimKiem);
        btn_QuayLai=(ImageButton) findViewById(R.id.btn_QuayLai);
        lv_BaiNghe=(ListView) findViewById(R.id.lv_BaiNghe);



        getAllDataArtist(url);

        btn_QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(DanhSachBaiNghe.this, TrangChu.class);
                startActivity(myintent);
            }
        });
        btn_TimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = txt_TimKiem.getText().toString();
                filterList(inputText);
            }
        });
    }
    private void filterList(String query) {
        mFilteredList.clear();
        if (query.isEmpty()) {
            mFilteredList.addAll(mList);
        } else {
            for (Nghe nghe : mList) {
                if (nghe.getChuDeNghe().toLowerCase().contains(query.toLowerCase())) {
                    mFilteredList.add(nghe);
                }
            }
        }
        adapterNghe.notifyDataSetChanged();
    }

    public void getAllDataArtist(String url)
    {

        RequestQueue requestQueue= Volley.newRequestQueue(DanhSachBaiNghe.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseJsonData(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }
    public void  parseJsonData(String response) throws JSONException {

        JSONArray artistArray  = new JSONArray(response);
        for(int i=0;i<artistArray.length();i++)
        {
            JSONObject artist= artistArray.getJSONObject(i);
            Nghe a  =new Nghe();
            a.setFileNghe(artist.getString("filenghe"));
            a.setDoanNghe(artist.getString("doannghe"));
            a.setChuDeNghe(artist.getString("chudenghe"));
            mList.add(a);
        }
        mFilteredList.addAll(mList);
        adapterNghe = new Adapter_Nghe(getApplicationContext(), R.layout.custom_nghe, mFilteredList);
        lv_BaiNghe.setAdapter(adapterNghe);
    }
}