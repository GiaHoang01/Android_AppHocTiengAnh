package com.example.doanapphoctienganh.ActivityViet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.doanapphoctienganh.Database.Class.ChuongViet;
import com.example.doanapphoctienganh.Database.Handle.DanhSachVietHandle;
import com.example.doanapphoctienganh.Fragment.Fragment_ListViet;
import com.example.doanapphoctienganh.R;
import com.example.doanapphoctienganh.TrangChu;

import java.util.ArrayList;
import java.util.List;

public class DanhSachChonChuongViet extends AppCompatActivity {

    FrameLayout FL_DSBaiViet;
    DanhSachVietHandle vietHandle;
    EditText txt_TimKiem;
    ImageButton btn_TimKiem,btn_QuayLai;
    private List<ChuongViet> mList=new ArrayList<>();
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chon_chuong_viet);

        txt_TimKiem=(EditText) findViewById(R.id.txt_TimKiem);
        btn_TimKiem=(ImageButton) findViewById(R.id.btn_TimKiem);
        btn_QuayLai=(ImageButton) findViewById(R.id.btn_QuayLai);


        vietHandle=new DanhSachVietHandle(getApplicationContext(),"Android.db", null,1);
        mList=vietHandle.loadCauHoi();

        FL_DSBaiViet=(FrameLayout) findViewById(R.id.FL_ListNghe);
        loadFragment(new Fragment_ListViet());


        btn_QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(DanhSachChonChuongViet.this, TrangChu.class);
                startActivity(myintent);
            }
        });
        btn_TimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = txt_TimKiem.getText().toString();
                // Kiểm tra xem inputText có null hay không trước khi sử dụng
                if (inputText != null) {
                    // Load danh sách các câu hỏi dựa trên inputText
                    List<ChuongViet> newData = vietHandle.loadCauHoiTimKiem(inputText);
                    // Cập nhật dữ liệu trong Fragment_ListViet
                    Fragment_ListViet fragment = (Fragment_ListViet) getSupportFragmentManager().findFragmentById(R.id.FL_ListNghe);
                    if (fragment != null) {
                        fragment.updateData(newData);
                    }
                    loadFragment(fragment);
                }
                else
                {
                    mList=vietHandle.loadCauHoi();
                    FL_DSBaiViet=(FrameLayout) findViewById(R.id.FL_ListNghe);
                    loadFragment(new Fragment_ListViet());
                }
            }
        });

    }
    public void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FL_ListNghe,fragment);
        ft.commit();
    }
}