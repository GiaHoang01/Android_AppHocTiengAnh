package com.example.doanapphoctienganh.LearnToRead;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.doanapphoctienganh.ActivityViet.DanhSachChonChuongViet;
import com.example.doanapphoctienganh.Database.Class.ChuongViet;
import com.example.doanapphoctienganh.Fragment.Fragment_ListViet;
import com.example.doanapphoctienganh.R;
import com.example.doanapphoctienganh.TrangChu;

import java.util.ArrayList;
import java.util.List;
public class ReadListActivity extends AppCompatActivity {


    EditText txt_TimKiem;
    ImageButton btn_TimKiem,btn_QuayLai;
    ListReadsHandle readsHandle;
    private List<String> mList=new ArrayList<>();
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_list);
        loadFragment(new FragmentListRead());

        readsHandle =new ListReadsHandle(getApplicationContext(),"Android.db", null, 1);
        txt_TimKiem=(EditText) findViewById(R.id.txt_searchRead);
        btn_TimKiem=(ImageButton) findViewById(R.id.btn_findread);
        btn_QuayLai=(ImageButton) findViewById(R.id.btn_back);
        addEnvent();
    }
    void addEnvent(){

        btn_QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(ReadListActivity.this, TrangChu.class);
                startActivity(myintent);
            }
        });
        btn_TimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = txt_TimKiem.getText().toString();
                if (inputText != null) {
                    List<ReadExercise> newData = readsHandle.loadCauHoi(inputText);
                    FragmentListRead fragment = (FragmentListRead) getSupportFragmentManager().findFragmentById(R.id.FL_ListRead);
                    if (fragment != null) {
                        fragment.updateData(newData);
                    }
                    loadFragment(fragment);
                }
                else
                {
                    loadFragment(new Fragment_ListViet());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFragment(new FragmentListRead());
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FL_ListRead,fragment);
        ft.commit();
    }
}