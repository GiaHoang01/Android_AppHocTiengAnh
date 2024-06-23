package com.example.doanapphoctienganh.ActivityViet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doanapphoctienganh.Adapter.Adapter_lv_DSDapAnViet;
import com.example.doanapphoctienganh.Adapter.Adapter_lv_LuyenViet;
import com.example.doanapphoctienganh.Database.Class.Viet;
import com.example.doanapphoctienganh.Database.Handle.VietHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

public class KetQuaViet extends AppCompatActivity {

    private List<Viet> mList=new ArrayList<>();

    VietHandle vietHandle;
    ListView lv_DapAn;
    Button btn_LamLai;
    TextView txt_Tong;
    private List<String> dungsai= new ArrayList<>();

    Adapter_lv_DSDapAnViet adapterLvDsDapAnViet;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua_viet);
        lv_DapAn=(ListView) findViewById(R.id.lv_DapAn);
        txt_Tong=(TextView)findViewById(R.id.txt_Tong);
        btn_LamLai=(Button) findViewById(R.id.btn_LamLai);
        List<String> receivedListData = getIntent().getStringArrayListExtra("listData");


        Bundle bundle = getIntent().getBundleExtra("package");
        String tenBai = bundle.getString("TenBai");
        dungsai.clear();
        vietHandle=new VietHandle(getApplicationContext(),"Android.db", null,1);
        mList=vietHandle.loadCauHoi(tenBai);


        for(int i=0;i<receivedListData.size();i++)
        {
            if(kiemTraCauDung(receivedListData.get(i))==1)
            {
                dungsai.add("Đúng");
            }
            else {
                dungsai.add("Sai");
            }
        }

        adapterLvDsDapAnViet = new Adapter_lv_DSDapAnViet(KetQuaViet.this,R.layout.custom_listview_dapan,mList,dungsai);
        lv_DapAn.setAdapter(adapterLvDsDapAnViet);
        adapterLvDsDapAnViet.notifyDataSetChanged();

        String Tong="Tổng :"+demTongSoCauDung()+"/"+dungsai.size();
        txt_Tong.setText(Tong);

        btn_LamLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public int demTongSoCauDung()
    {
        int tong=0;
        for(int i=0;i<dungsai.size();i++)
        {
            if(dungsai.get(i).equals("Đúng"))
            {
                tong++;
            }
        }
        return tong;
    }

    public int kiemTraCauDung(String temp)
    {
        for(int j=0;j<mList.size();j++)
        {
            if(temp.equals(mList.get(j).getDapAn()))
            {
                    return 1;
            }
        }
        return 0;
    }
}