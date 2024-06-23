package com.example.doanapphoctienganh.ActivityViet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanapphoctienganh.Adapter.Adapter_lv_LuyenViet;
import com.example.doanapphoctienganh.Database.Class.Viet;
import com.example.doanapphoctienganh.Database.Handle.VietHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

public class LuyenTapViet extends AppCompatActivity {

   ListView lv_CauHoiViet;

    private List<Viet> mList=new ArrayList<>();
    private Adapter_lv_LuyenViet adapterLvLuyenViet;
    VietHandle vietHandle;
    Button btn_Submit;

    String tenBai="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_tap_viet);
        addControls();

        Bundle bundle = getIntent().getBundleExtra("package");
        tenBai = bundle.getString("tenBai");


        vietHandle=new VietHandle(getApplicationContext(),"Android.db", null,1);
        mList=vietHandle.loadCauHoi(tenBai);
        adapterLvLuyenViet = new Adapter_lv_LuyenViet(LuyenTapViet.this,R.layout.custom_listview_cauhoiviet,mList);
        lv_CauHoiViet.setAdapter(adapterLvLuyenViet);
        adapterLvLuyenViet.notifyDataSetChanged();
        addEvent();
    }

    public void addControls()
    {
        lv_CauHoiViet=(ListView) findViewById(R.id.lv_CauHoiViet);
        btn_Submit=(Button) findViewById(R.id.btn_NopBai);
    }

    public void addEvent()
    {
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vietHandle.updateTrangThai(tenBai, "Hoàn thành")) {
                    // Hiển thị thông báo đã hoàn thành bài làm
                    Toast.makeText(getApplicationContext(), "Đã hoàn thành bài làm", Toast.LENGTH_SHORT).show();
                } else {
                    // Hiển thị thông báo cập nhật không thành công
                    Toast.makeText(getApplicationContext(), "Cập nhật trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }


                List<String> editTextDataList = adapterLvLuyenViet.getEditTextData();
                Intent intent = new Intent(LuyenTapViet.this, KetQuaViet.class);
                intent.putStringArrayListExtra("listData", (ArrayList<String>) editTextDataList);
                Bundle mybundle= new Bundle();
                mybundle.putString("TenBai",tenBai);
                intent.putExtra("package",mybundle);
                startActivity(intent);
            }
        });
    }
}