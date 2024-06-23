package com.example.doanapphoctienganh.ActivityThiThu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanapphoctienganh.R;

public class LoadCauHoi extends AppCompatActivity {

    private TextView textTenBaiKiemTra, txt_Diem;
    private int idBaiKiemTra, score;
    private ImageButton btn_back;
    private Button start_button;
    private String tenBaiKiemTra, tenBaiKiemTraGanNhat;
    private static final int REQUEST_CODE_QUESTION=1;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_KEY_SCORE = "score";
    private static final String PREF_KEY_EXAM_NAME = "TenBaiKiemTraGanNhat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_cau_hoi);
        addControls();
        addEvents();

        textTenBaiKiemTra = findViewById(R.id.text_ten_bai_kiem_tra);
        idBaiKiemTra = getIntent().getIntExtra("IDBaiKiemTra", 0);
        tenBaiKiemTra = getIntent().getStringExtra("EXAM_NAME");

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        score = settings.getInt(PREF_KEY_SCORE, 0);
        tenBaiKiemTraGanNhat = settings.getString(PREF_KEY_EXAM_NAME, null);

        textTenBaiKiemTra.setText(tenBaiKiemTra);
        if(tenBaiKiemTraGanNhat != null){
            txt_Diem.setText("Điểm " + tenBaiKiemTraGanNhat + ": " + score);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_QUESTION)
        {
            if(resultCode==RESULT_OK){
                score=data.getIntExtra("score",0);
                tenBaiKiemTraGanNhat = data.getStringExtra("TenBaiKiemTra");

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt(PREF_KEY_SCORE, score);
                editor.putString(PREF_KEY_EXAM_NAME, tenBaiKiemTraGanNhat);
                editor.apply();

                txt_Diem.setText("Điểm " + tenBaiKiemTraGanNhat + ": " + score);
            }
        }
    }

    public void addControls()
    {
        btn_back = findViewById(R.id.btn_back);
        start_button = findViewById(R.id.start_button);
        textTenBaiKiemTra = findViewById(R.id.text_ten_bai_kiem_tra);
        txt_Diem = findViewById(R.id.text_diem);
    }
    public void addEvents()
    {
        btn_back.setOnClickListener(v -> {
            finish();
        });

        start_button.setOnClickListener(v -> {
            Intent myintent= new Intent(LoadCauHoi.this, CauHoiKT.class);
            myintent.putExtra("IDBaiKiemTra", idBaiKiemTra);
            myintent.putExtra("EXAM_NAME", tenBaiKiemTra);
            startActivityForResult(myintent,REQUEST_CODE_QUESTION);
        });
    }
}