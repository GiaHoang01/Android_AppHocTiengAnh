package com.example.doanapphoctienganh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DangNhap extends AppCompatActivity {

    Button btnDangNhap;
    EditText mail, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        boolean daDangNhap = sharedPreferences.getBoolean("daDangNhap", false);

        if (daDangNhap) {
            // Người dùng đã đăng nhập, chuyển đến màn hình chính
            Intent intent = new Intent(DangNhap.this, TrangChu.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_dang_nhap);
        addControl();
        addEvent();
    }

    void addControl() {
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        mail = (EditText) findViewById(R.id.edtEmail);
        name = (EditText) findViewById(R.id.edtName);
    }

    void addEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy email và tên từ EditText
                String email = mail.getText().toString().trim();
                String userName = name.getText().toString().trim();

                // Kiểm tra email và tên
                if (!isValidEmail(email)) {
                    Toast.makeText(DangNhap.this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userName.isEmpty()) {
                    Toast.makeText(DangNhap.this, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lưu trạng thái đăng nhập
                SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("daDangNhap", true);
                editor.putString("userName", userName);
                editor.apply();

                // Chuyển đến màn hình chính
                Intent intent = new Intent(DangNhap.this, TrangChu.class);
                startActivity(intent);
                finish(); // Đóng Activity hiện tại
            }
        });
    }

    // Hàm kiểm tra email đúng định dạng
    boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}