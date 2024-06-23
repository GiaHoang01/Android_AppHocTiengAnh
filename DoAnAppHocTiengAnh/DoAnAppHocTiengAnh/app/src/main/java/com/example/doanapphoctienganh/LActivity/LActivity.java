package com.example.doanapphoctienganh.LActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.doanapphoctienganh.Adapter.Adapter_BaiHocTuVung;
import com.example.doanapphoctienganh.Database.Class.BaiHoc;
import com.example.doanapphoctienganh.Database.Handle.LHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class LActivity extends AppCompatActivity {

    ArrayList<BaiHoc> arrayListBaiHoc=new ArrayList<>();
    RecyclerView recyclerView;
    LHandle lHandle;
    Adapter_BaiHocTuVung adapterBaiHocTuVung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lactivity);
        addControls();
        lHandle=new LHandle(getApplicationContext(), "Android.db", null, 1);
        arrayListBaiHoc=lHandle.loadBaiHocTuVung();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        adapterBaiHocTuVung = new Adapter_BaiHocTuVung(this, arrayListBaiHoc);
        recyclerView.setAdapter(adapterBaiHocTuVung);
    }
    public void addControls()
    {
        recyclerView=(RecyclerView) findViewById(R.id.recycleTuVung);
    }

}