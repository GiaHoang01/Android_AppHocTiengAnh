package com.example.doanapphoctienganh.VActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.doanapphoctienganh.Adapter.VAdapter_TuVung_ChonTu_English;
import com.example.doanapphoctienganh.Adapter.VAdapter_TuVung_ChonTu_Vietnamese;
import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.Database.Handle.VHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

public class VChonTuActivity extends AppCompatActivity {
    ArrayList<TuVung> arrayListTuVung=new ArrayList<>();
    ArrayList<TuVung> arrayListTuVung1=new ArrayList<>();
    RecyclerView recyclerView,recyclerView1;
    VHandle vHandle,vHandle1;
    VAdapter_TuVung_ChonTu_English adapterTuVungEnglish;
    VAdapter_TuVung_ChonTu_Vietnamese adapterTuVungVietnamese;
    String tenChuong;

    private TuVung selectedTuVungEnglish;
    private TuVung selectedTuVungVietnamese;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vchon_tu);
        addControls();
        Intent intent = getIntent();
        tenChuong = intent.getStringExtra("TenChuong");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        vHandle=new VHandle(getApplicationContext(),"Android.db",null,1);
        arrayListTuVung= (ArrayList<TuVung>) vHandle.loadTuVungTiengAnh(tenChuong);
        adapterTuVungEnglish=new VAdapter_TuVung_ChonTu_English(this,arrayListTuVung,this);
        recyclerView.setAdapter(adapterTuVungEnglish);


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        vHandle1=new VHandle(getApplicationContext(),"Android.db",null,1);
        arrayListTuVung1= (ArrayList<TuVung>) vHandle1.loadTuVungTiengViet(tenChuong);
        adapterTuVungVietnamese=new VAdapter_TuVung_ChonTu_Vietnamese(this,arrayListTuVung1,this);
        recyclerView1.setAdapter(adapterTuVungVietnamese);

    }
    public void addControls()
    {
        recyclerView=(RecyclerView) findViewById(R.id.recycleviewEnglish);
        recyclerView1=(RecyclerView) findViewById(R.id.recycleviewVietnamese);
    }
    // Xử lý khi click vào flashcard tiếng Anh
    // Xử lý khi click vào flashcard tiếng Anh
    public void onEnglishCardClicked(int position) {
        TuVung vocab = arrayListTuVung.get(position);
        resetSelectedEnglish();
        vocab.setSelected(true);
        vocab.setCorrect(false);
        selectedTuVungEnglish = vocab;
        checkMatchingCards();
        adapterTuVungEnglish.notifyDataSetChanged();
    }

    // Xử lý khi click vào flashcard tiếng Việt
    public void onVietnameseCardClicked(int position) {
        TuVung vocab = arrayListTuVung1.get(position);
        resetSelectedVietnamese();
        vocab.setSelected(true);
        vocab.setCorrect(false);
        selectedTuVungVietnamese = vocab;
        checkMatchingCards();
        adapterTuVungVietnamese.notifyDataSetChanged();
    }

    private void resetSelectedEnglish() {
        for (TuVung vocab : arrayListTuVung) {
            vocab.setSelected(false);
        }
    }

    private void resetSelectedVietnamese() {
        for (TuVung vocab : arrayListTuVung1) {
            vocab.setSelected(false);
        }
    }

    private void checkMatchingCards() {
        if (selectedTuVungEnglish != null && selectedTuVungVietnamese != null) {
            if (selectedTuVungEnglish.getIdTuVung() == selectedTuVungVietnamese.getIdTuVung()) {
                selectedTuVungEnglish.setCorrect(true);
                selectedTuVungVietnamese.setCorrect(true);
            } else {
                selectedTuVungEnglish.setCorrect(false);
                selectedTuVungVietnamese.setCorrect(false);
                selectedTuVungEnglish.setSelected(false);
                selectedTuVungVietnamese.setSelected(false);
            }
            adapterTuVungEnglish.notifyDataSetChanged();
            adapterTuVungVietnamese.notifyDataSetChanged();

            // Reset selected items
            selectedTuVungEnglish = null;
            selectedTuVungVietnamese = null;
        }
    }

}