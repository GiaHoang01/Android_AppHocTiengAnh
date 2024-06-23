package com.example.doanapphoctienganh.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doanapphoctienganh.Adapter.Adapter_History_BaiDoc;
import com.example.doanapphoctienganh.Adapter.Adapter_History_BaiViet;
import com.example.doanapphoctienganh.Adapter.Adapter_History_Thi;
import com.example.doanapphoctienganh.Database.Handle.BaiHocHandle;
import com.example.doanapphoctienganh.Database.Handle.ThiThuHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;


public class Fragment_Tab_LuyenThi extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    ListView lv_DanhSachLuyenThiDaLam;
    ArrayList<String> danhSachCacBaiDaThi= new ArrayList<>();
    Adapter_History_Thi adapterHistoryThi;

    TextView txt_ChuaHoanThanhThi;

    public Fragment_Tab_LuyenThi() {
        // Required empty public constructor
    }

    public static Fragment_Tab_LuyenThi newInstance(String param1, String param2) {
        Fragment_Tab_LuyenThi fragment = new Fragment_Tab_LuyenThi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment__tab__luyen_thi, container, false);
        ThiThuHandle thiThuHandle= new ThiThuHandle(getContext(),"Android.db", null,1);

        txt_ChuaHoanThanhThi=rootView.findViewById(R.id.txt_ChuaHoanThanhThi);

        //doc
        lv_DanhSachLuyenThiDaLam= rootView.findViewById(R.id.lv_DanhSachLuyenThiDaLam);
        danhSachCacBaiDaThi=thiThuHandle.loadCauThiDaLam();
        if (danhSachCacBaiDaThi == null || danhSachCacBaiDaThi.isEmpty()) {
            txt_ChuaHoanThanhThi.setVisibility(View.VISIBLE);
            lv_DanhSachLuyenThiDaLam.setVisibility(View.GONE);
        } else {
            txt_ChuaHoanThanhThi.setVisibility(View.GONE);
            lv_DanhSachLuyenThiDaLam.setVisibility(View.VISIBLE);
            adapterHistoryThi = new Adapter_History_Thi(getContext(), R.layout.custom_history, danhSachCacBaiDaThi);
            lv_DanhSachLuyenThiDaLam.setAdapter(adapterHistoryThi);
        }
        return rootView;
    }
}