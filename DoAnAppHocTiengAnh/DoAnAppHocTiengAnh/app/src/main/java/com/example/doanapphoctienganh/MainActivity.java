package com.example.doanapphoctienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent newIntent = new Intent(this, DangNhap.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(newIntent);
                finish();
            }
        }, 3000);
    }
}