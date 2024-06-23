package com.example.doanapphoctienganh.VActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doanapphoctienganh.Adapter.VAdapter_TuVung;
import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.Database.Handle.VHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class VActivity extends AppCompatActivity {

    ArrayList<TuVung> arrayListTuVung=new ArrayList<>();
    RecyclerView recyclerView;
    VHandle vHandle;
    VAdapter_TuVung adapterTuVung;
    String tenChuong;
    Button btn_ChonTu,btn_DinhNghia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vactivity);
        addControls();
        Intent intent = getIntent();
        tenChuong = intent.getStringExtra("TenChuong");
        vHandle=new VHandle(getApplicationContext(),"Android.db",null,1);
        arrayListTuVung=vHandle.loadTuVung(tenChuong);
        loadFavoriteStatus();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapterTuVung=new VAdapter_TuVung(this,arrayListTuVung);
        recyclerView.setAdapter(adapterTuVung);
        addEvents();
    }
    public void addControls()
    {
        btn_ChonTu=(Button) findViewById(R.id.btn_ChonTu);
        recyclerView=(RecyclerView) findViewById(R.id.recycleTuVung);
        btn_DinhNghia=(Button) findViewById(R.id.btn_DinhNghia);
    }
    private void loadFavoriteStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("FAVORITES", Context.MODE_PRIVATE);
        for (TuVung vocab : arrayListTuVung) {
            boolean isFavorite = sharedPreferences.getBoolean(vocab.getTiengAnh(), false);
            vocab.setFavorite(isFavorite);
        }
    }
    public void addEvents()
    {
        btn_ChonTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), VChonTuActivity.class);
                intent.putExtra("TenChuong",tenChuong);
                startActivity(intent);
            }
        });
        btn_DinhNghia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), VDinhNghiaActivity.class);
                intent.putExtra("TenChuong",tenChuong);
                startActivity(intent);
            }
        });
    }
}