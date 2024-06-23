package com.example.doanapphoctienganh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.doanapphoctienganh.ActivityThiThu.ThiThuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TrangChu extends AppCompatActivity {

    ActionBar actionBar_trangchu;
    FrameLayout frameFragment_trangchu;
    BottomNavigationView bttnav_trangchu;
    private int selectedMenuItemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu);
        actionBar_trangchu=getSupportActionBar();


        frameFragment_trangchu=(FrameLayout) findViewById(R.id.frameFragmentbai2);
        bttnav_trangchu = (BottomNavigationView) findViewById(R.id.bttnav2);

        selectedMenuItemId = R.id.trangchu;
        loadFragment(new Home());

        bttnav_trangchu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                selectedMenuItemId = id;
                if (id == R.id.trangchu) {
                    if (actionBar_trangchu != null) {
                        actionBar_trangchu.setTitle("Home");
                    }

                    loadFragment(new Home());
                   return true;
                }
                else if (id==R.id.Thi) {
                    if (actionBar_trangchu != null) {
                        actionBar_trangchu.setTitle("Exams");

                    }
                    loadFragment(new ThiThuFragment());
                    return true;
                }
                else if (id==R.id.CaiDat) {
                    if (actionBar_trangchu != null) {
                        actionBar_trangchu.setTitle("Setting");
                    }

                    loadFragment(new Setting());
                    return true;
                }
                else if (id==R.id.history) {
                    if (actionBar_trangchu != null) {
                        actionBar_trangchu.setTitle("History");
                    }
                    loadFragment(new History());
                    return true;
                }

                return false;
            }
        });
    }
    public void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragmentbai2,fragment);
        ft.commit();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedMenuItemId", selectedMenuItemId); // Lưu trạng thái của ID menu được chọn
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedMenuItemId = savedInstanceState.getInt("selectedMenuItemId"); // Khôi phục trạng thái của ID menu được chọn
        bttnav_trangchu.setSelectedItemId(selectedMenuItemId); // Chọn lại menu với ID đã lưu
    }
}