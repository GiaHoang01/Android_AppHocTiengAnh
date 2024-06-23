package com.example.doanapphoctienganh.LearnToRead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanapphoctienganh.R;
import com.example.doanapphoctienganh.TrangChu;

public class ReadTestActivity extends AppCompatActivity {

    TextView tvNodung;
    ReadExercise read= new ReadExercise();
    Button btnStart;
    TextView core,name;
    ListReadsHandle readsHandle;
    ImageButton btn_QuayLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_test);

        Bundle bundle = getIntent().getBundleExtra("package");
        read.setId( bundle.getInt("id"));
        read.setTenBaiDoc(bundle.getString("tenBai"));
        read.setNoiDungBaiDoc(bundle.getString("noiDung"));
        read.setTrangThai(bundle.getString("trangThai"));
        read.setIdBaiHoc(bundle.getInt("idBaiHoc"));
        addControl();
        readsHandle =new ListReadsHandle(getApplicationContext(),"Android.db", null, 1);
        core.setText(String.valueOf(readsHandle.getHighCore(read.getId())));
        name.setText(read.getTenBaiDoc());
        tvNodung.setText(read.getNoiDungBaiDoc());
        if(read.getTrangThai()!=null){
            btnStart.setText("Thử Lại");
        }
        addEnvent();
    }
    void addControl(){
        tvNodung = (TextView) findViewById(R.id.tvContent);
        btnStart = (Button) findViewById(R.id.btnStart);
        core = (TextView) findViewById(R.id.highcore);
        name = (TextView)findViewById(R.id.name);
        btn_QuayLai=(ImageButton) findViewById(R.id.btn_backLR);
    }
    void addEnvent(){
        btn_QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(ReadTestActivity.this, ReadListActivity.class);
                startActivity(myintent);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), ReadTestDetailsActivity.class);
                Bundle bundle= new Bundle();
                bundle.putInt("id", read.getId());
                bundle.putInt("idBaiHoc", read.getIdBaiHoc());
                bundle.putString("tenBai", read.getTenBaiDoc());
                bundle.putString("noiDung", read.getNoiDungBaiDoc());
                myintent.putExtra("package",bundle);
                startActivity(myintent);
            }
        });
    }
}