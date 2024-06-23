package com.example.doanapphoctienganh.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bumptech.glide.manager.Lifecycle;
import com.example.doanapphoctienganh.Fragment.Fragment_Tab_LuyenTap;
import com.example.doanapphoctienganh.Fragment.Fragment_Tab_LuyenThi;
import com.example.doanapphoctienganh.History;

public class Adapter_LichSu extends FragmentStateAdapter {
    public Adapter_LichSu(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:return new Fragment_Tab_LuyenTap();
            case 1: return new Fragment_Tab_LuyenThi();
            default:return new Fragment_Tab_LuyenTap();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
