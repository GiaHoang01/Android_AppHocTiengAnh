package com.example.doanapphoctienganh.ActivityNguPhap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Adapter.Adapter_NguPhap;
import com.example.doanapphoctienganh.Database.Class.NguPhap;
import com.example.doanapphoctienganh.Database.Handle.DanhSachVietHandle;
import com.example.doanapphoctienganh.Database.Handle.NguPhapHandle;
import com.example.doanapphoctienganh.Interface.OnItemClickListener;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class ActivityNguPhap extends AppCompatActivity implements OnItemClickListener {

    ImageButton btn_back;
    String[] items = {"Bài 1", "Bài 2", "Bài 3","Bài 4"};
    Spinner spinner;
    Adapter_NguPhap adapterNguPhap;
    RecyclerView rvNguPhap;
    ArrayList<NguPhap> nguPhapList;
    String tenBai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngu_phap);
        addControls();
        addEvents();
        layDuLieu("Bài 1");
    }
    void addControls()
    {
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        spinner = (Spinner) findViewById(R.id.spinner_nguphap);
        rvNguPhap = (RecyclerView) findViewById(R.id.recyclerview_NguPhap);

        // Set LayoutManager cho RecyclerView
        rvNguPhap.setLayoutManager(new LinearLayoutManager(this));
        // Khởi tạo Adapter và gán vào RecyclerView
        nguPhapList = new ArrayList<>();
        adapterNguPhap = new Adapter_NguPhap(nguPhapList,(OnItemClickListener) this);
        rvNguPhap.setAdapter(adapterNguPhap);
    }
    void addEvents()
    {
        //Thêm dữ liệu vào spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner_item_nguphap, items);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item_nguphap);
        spinner.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                layDuLieu(selectedItem);
                tenBai = selectedItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @SuppressLint("NotifyDataSetChanged")
    void layDuLieu(String ten) {
        NguPhapHandle nguPhapHandle = new NguPhapHandle(getApplicationContext(),"Android.db", null,1);
        ArrayList<NguPhap> nguPhapListMoi = nguPhapHandle.getAllNguPhap(ten);

        nguPhapList.clear();
        nguPhapList.addAll(nguPhapListMoi);
        adapterNguPhap.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(int position) {
        NguPhap selectnguphap = nguPhapList.get(position);
        Intent intent = new Intent(getApplicationContext(), NoiDungNguPhap.class);

        String ten = selectnguphap.getTenNguPhap();
        String congthuc = selectnguphap.getCongThuc();
        String vidu = selectnguphap.getViDu();
        String mota = selectnguphap.getMoTa();
        Bundle bundle = new Bundle();
        bundle.putString("ten",ten);
        bundle.putString("congthuc",congthuc);
        bundle.putString("vidu",vidu);
        bundle.putString("mota",mota);
        bundle.putString("tenbai",tenBai);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}