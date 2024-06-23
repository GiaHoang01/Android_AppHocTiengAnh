package com.example.doanapphoctienganh.ActivityThiThu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Adapter.Adapter_BaiKiemTra;
import com.example.doanapphoctienganh.Database.Class.BaiKiemTra;
import com.example.doanapphoctienganh.Database.Handle.ThiThuHandle;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class AllExams extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_BaiKiemTra adapter;
    private List<BaiKiemTra> baiKiemTraList;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_exams);
        recyclerView = findViewById(R.id.recyclerViewAllExams);
        btn_back = findViewById(R.id.btn_back);

        ThiThuHandle thiThuHandle = new ThiThuHandle(this, "Android.db", null, 1);

        baiKiemTraList = thiThuHandle.getAllBaiKiemTra();

        adapter = new Adapter_BaiKiemTra(this, baiKiemTraList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}