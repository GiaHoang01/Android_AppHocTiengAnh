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
import com.example.doanapphoctienganh.Database.Handle.BaiHocHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Tab_LuyenTap extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView txt_ChuaHoanThanBaiDoc,txt_ChuaHoanThanhBaiViet;


    //viet
    ListView lv_DanhSachBaiVietDaLam;
    ArrayList<String> danhSachCacBaiVietDaLam= new ArrayList<>();
    Adapter_History_BaiViet adapterHistoryBaiViet;
    //doc

    ListView lv_DanhSachBaiDocDaLam;
    ArrayList<String> danhSachCacBaiDocDaLam= new ArrayList<>();
    Adapter_History_BaiDoc adapterHistoryBaiDoc;



    public Fragment_Tab_LuyenTap() {
        // Required empty public constructor
    }

    public static Fragment_Tab_LuyenTap newInstance(String param1, String param2) {
        Fragment_Tab_LuyenTap fragment = new Fragment_Tab_LuyenTap();
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
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment__tab__luyen_tap, container, false);
        BaiHocHandle baiHocHandle= new BaiHocHandle(getContext(),"Android.db", null,1);

        txt_ChuaHoanThanBaiDoc=rootView.findViewById(R.id.txt_ChuaHoanThanhBaiDoc);
        txt_ChuaHoanThanhBaiViet=rootView.findViewById(R.id.txt_ChuaHoanThanhBaiViet);


        //viet
        lv_DanhSachBaiVietDaLam=rootView.findViewById(R.id.lv_DanhSachViet);
        danhSachCacBaiVietDaLam=baiHocHandle.loadCauVietDaLam();
        if (danhSachCacBaiVietDaLam == null || danhSachCacBaiVietDaLam.isEmpty()) {
            txt_ChuaHoanThanhBaiViet.setVisibility(View.VISIBLE);
            lv_DanhSachBaiVietDaLam.setVisibility(View.GONE);
        } else {
            txt_ChuaHoanThanhBaiViet.setVisibility(View.GONE);
            lv_DanhSachBaiVietDaLam.setVisibility(View.VISIBLE);
            adapterHistoryBaiViet = new Adapter_History_BaiViet(getContext(), R.layout.custom_history, danhSachCacBaiVietDaLam);
            lv_DanhSachBaiVietDaLam.setAdapter(adapterHistoryBaiViet);
        }

        //doc
        lv_DanhSachBaiDocDaLam= rootView.findViewById(R.id.lv_DanhSachDoc);
        danhSachCacBaiDocDaLam=baiHocHandle.loadCauDocDaLam();
        if (danhSachCacBaiDocDaLam == null || danhSachCacBaiDocDaLam.isEmpty()) {
            txt_ChuaHoanThanBaiDoc.setVisibility(View.VISIBLE);
            lv_DanhSachBaiDocDaLam.setVisibility(View.GONE);
        } else {
            txt_ChuaHoanThanBaiDoc.setVisibility(View.GONE);
            lv_DanhSachBaiDocDaLam.setVisibility(View.VISIBLE);
            adapterHistoryBaiDoc = new Adapter_History_BaiDoc(getContext(), R.layout.custom_history, danhSachCacBaiDocDaLam);
            lv_DanhSachBaiDocDaLam.setAdapter(adapterHistoryBaiDoc);
        }


        return rootView;
    }
}