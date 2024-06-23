package com.example.doanapphoctienganh.ActivityNguPhap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Adapter.Adapter_NguPhap;
import com.example.doanapphoctienganh.Database.Class.NguPhap;
import com.example.doanapphoctienganh.Database.Handle.NguPhapHandle;
import com.example.doanapphoctienganh.Interface.OnItemClickListener;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class NoiDungNguPhap extends AppCompatActivity implements OnItemClickListener {

    TextView txt_framentnguphap, txtCongThucNP, txtViDuNP, txtMoTaNP;
    ImageButton btn_cancel;
    TextView txtNguPhap1, txtNguPhap2, txtNguPhap3, txtNguPhap4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_ngu_phap);
        addControl();
        loadDuLieu();
        addEvent();
    }

    void addControl() {
        txt_framentnguphap = findViewById(R.id.txt_framentnguphap);
        txtCongThucNP = findViewById(R.id.txtCongThucNP);
        txtViDuNP = findViewById(R.id.txtViDuNP);
        txtMoTaNP = findViewById(R.id.txtMotaNP);
        btn_cancel = findViewById(R.id.btn_cancel);

        txtNguPhap1 = findViewById(R.id.txtNguPhap1);
        txtNguPhap2 = findViewById(R.id.txtNguPhap2);
        txtNguPhap3 = findViewById(R.id.txtNguPhap3);
        txtNguPhap4 = findViewById(R.id.txtNguPhap4);
    }

    void addEvent() {
        btn_cancel.setOnClickListener(v -> finish());
    }

    void loadDuLieu() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String ten = bundle.getString("ten");
            String congthuc = bundle.getString("congthuc");
            String vd = bundle.getString("vidu");
            String mota = bundle.getString("mota");
            String tenBai = bundle.getString("tenbai");

            txt_framentnguphap.setText(ten);
            txtCongThucNP.setText("✱ " + congthuc);

            String[] viduArray = vd != null ? vd.split(";;") : new String[0];
            StringBuilder vdBuilder = new StringBuilder();
            for (String v : viduArray) {
                vdBuilder.append("● ").append(v).append("\n");
            }
            txtViDuNP.setText(vdBuilder.toString());

            txtMoTaNP.setText(mota);

            // Load các mục ngữ pháp liên quan
            loadNguPhapLienQuan(ten,tenBai);
        }
    }

    private void loadNguPhapLienQuan(String currentGrammar,String tenBai) {
        NguPhapHandle nguPhapHandle = new NguPhapHandle(getApplicationContext(), "Android.db", null, 1);
        ArrayList<NguPhap> allNguPhap = nguPhapHandle.getAllNguPhap(tenBai);

        int count = 0; // Biến đếm số mục ngữ pháp liên quan

        for (NguPhap nguPhap : allNguPhap) {
            if (!nguPhap.getTenNguPhap().equals(currentGrammar)) {
                switch (count) {
                    case 0:
                        txtNguPhap1.setText(nguPhap.getTenNguPhap());
                        txtNguPhap1.setTag(nguPhap); // Lưu ngữ pháp vào tag để có thể lấy lại khi click
                        txtNguPhap1.setOnClickListener(v -> onItemClick(0));
                        break;
                    case 1:
                        txtNguPhap2.setText(nguPhap.getTenNguPhap());
                        txtNguPhap2.setTag(nguPhap);
                        txtNguPhap2.setOnClickListener(v -> onItemClick(1));
                        break;
                    case 2:
                        txtNguPhap3.setText(nguPhap.getTenNguPhap());
                        txtNguPhap3.setTag(nguPhap);
                        txtNguPhap3.setOnClickListener(v -> onItemClick(2));
                        break;
                    case 3:
                        txtNguPhap4.setText(nguPhap.getTenNguPhap());
                        txtNguPhap4.setTag(nguPhap);
                        txtNguPhap4.setOnClickListener(v -> onItemClick(3));
                        break;
                    default:
                        break;
                }
                count++;
                if (count == 4) {
                    break; // Dừng lại sau khi đã gán 4 ngữ pháp liên quan
                }
            }
        }
    }

    // Phương thức xử lý sự kiện khi click vào một ngữ pháp liên quan
    @Override
    public void onItemClick(int position) {
        NguPhap nguPhap = null;

        // Dựa vào vị trí, lấy dữ liệu ngữ pháp từ các TextView tương ứng
        switch (position) {
            case 0:
                nguPhap = (NguPhap) txtNguPhap1.getTag();
                break;
            case 1:
                nguPhap = (NguPhap) txtNguPhap2.getTag();
                break;
            case 2:
                nguPhap = (NguPhap) txtNguPhap3.getTag();
                break;
            case 3:
                nguPhap = (NguPhap) txtNguPhap4.getTag();
                break;
            default:
                break;
        }

        if (nguPhap != null) {
            // Chuyển sang màn hình NoiDungNguPhap với ngữ pháp được chọn
            Intent intent = new Intent(this, NoiDungNguPhap.class);
            Bundle bundle = new Bundle();
            bundle.putString("ten", nguPhap.getTenNguPhap());
            bundle.putString("congthuc", nguPhap.getCongThuc());
            bundle.putString("vidu", nguPhap.getViDu());
            bundle.putString("mota", nguPhap.getMoTa());
            intent.putExtras(bundle);

            finish();
            startActivity(intent);
        }
    }

}