package com.example.doanapphoctienganh.ActivityThiThu;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Adapter.Adapter_ThiThu;
import com.example.doanapphoctienganh.Database.Class.BaiKiemTra;
import com.example.doanapphoctienganh.Database.Handle.ThiThuHandle;
import com.example.doanapphoctienganh.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThiThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThiThuFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tvSeeMore;
    private RecyclerView recyclerView;
    private Adapter_ThiThu adapter;
    private List<BaiKiemTra> baiKiemTraList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThiThuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThiThuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThiThuFragment newInstance(String param1, String param2) {
        ThiThuFragment fragment = new ThiThuFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thi_thu, container, false);

        ThiThuHandle thiThuHandle = new ThiThuHandle(requireContext(), "Android.db", null, 1);
        baiKiemTraList = thiThuHandle.getAllBaiKiemTra();

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new Adapter_ThiThu(requireContext(), baiKiemTraList);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        recyclerView.setAdapter(adapter);

        tvSeeMore = view.findViewById(R.id.tvSeeMore);
        tvSeeMore.setPaintFlags(tvSeeMore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AllExams.class);
                startActivity(myIntent);
            }
        });

        return view;
    }
}