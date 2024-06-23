package com.example.doanapphoctienganh.LearnToRead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doanapphoctienganh.R;

import java.util.Locale;

public class ResultTestReadActivity extends AppCompatActivity {

    TextView tvCore,tvTime,tvhighcore;
    Button btnback;
    ListReadsHandle readsHandle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_test_read);
        Bundle bundle = getIntent().getBundleExtra("package");
        int core = bundle.getInt("core");
        int id = bundle.getInt("id");
        long time = bundle.getLong("time");
        int minutes=(int) ((time/1000)/60);
        int seconds=(int) ((time/1000)%60);

        addControl();
        readsHandle =new ListReadsHandle(getApplicationContext(),"Android.db", null, 1);
        tvCore.setText("Total Core : "+String.valueOf(core));
        tvTime.setText("Total Time: " + String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
        addEnvent();
        if(core >= readsHandle.getHighCore(id)){
            tvhighcore.setVisibility(View.VISIBLE);
            readsHandle.updateHighCore(id,core);
        }

    }
    void addControl(){
        tvCore = (TextView) findViewById(R.id.tvcoreR);
        tvTime = (TextView) findViewById(R.id.tvTimeR);
        tvhighcore = (TextView) findViewById(R.id.tvHighcore);
        btnback = (Button) findViewById(R.id.btnBackR);
    }
    void  addEnvent(){
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ReadListActivity.class);
                startActivity(intent);
            }
        });
    }
}