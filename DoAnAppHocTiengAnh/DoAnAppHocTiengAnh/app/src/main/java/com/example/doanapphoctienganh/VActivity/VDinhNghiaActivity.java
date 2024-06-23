package com.example.doanapphoctienganh.VActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Adapter.VAdapter_TuVung_DinhNghia;
import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.Database.Handle.VHandle;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class VDinhNghiaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VAdapter_TuVung_DinhNghia adapter;
    private List<TuVung> vocabularyList;
    private VHandle vHandle;
    String tenChuong;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vdinh_nghia);
        Intent intent = getIntent();
        tenChuong = intent.getStringExtra("TenChuong");
        recyclerView = findViewById(R.id.RecycleTuVung);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        vHandle = new VHandle(getApplicationContext(), "Android.db", null, 1);
        vocabularyList = vHandle.loadTuVungTiengAnh_Viet(tenChuong);

        adapter = new VAdapter_TuVung_DinhNghia(vocabularyList);
        recyclerView.setAdapter(adapter);
    }
}
