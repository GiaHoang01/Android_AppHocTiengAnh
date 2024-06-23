package com.example.doanapphoctienganh.Fragment;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanapphoctienganh.Adapter.Adapter_BaiViet;
import com.example.doanapphoctienganh.Adapter.Adapter_DanhSachHocTap;
import com.example.doanapphoctienganh.Class.HocTap;
import com.example.doanapphoctienganh.Database.Class.ChuongViet;
import com.example.doanapphoctienganh.Database.Handle.DanhSachVietHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ListViet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ListViet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_ListViet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_ListViet.
     */
    // TODO: Rename and change types and number of parameters
    ArrayList<ChuongViet> arrayListBaiViet=new ArrayList<>();
    String[]lsName= new String[]{};
    DanhSachVietHandle vietHandle;
    private RecyclerView recyclerView;


    public static Fragment_ListViet newInstance(String param1, String param2) {
        Fragment_ListViet fragment = new Fragment_ListViet();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__hoc_tap, container, false);
        recyclerView = view.findViewById(R.id.recycleDanhSach);
        vietHandle = new DanhSachVietHandle(getActivity().getApplicationContext(), "Android.db", null, 1);
        arrayListBaiViet = vietHandle.loadCauHoi();
        if (recyclerView != null) {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
            recyclerView.setLayoutManager(layoutManager);

            int spacingInPixels = (int) (10 * getResources().getDisplayMetrics().density); // chuyển đổi 10dp thành pixels
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    int position = parent.getChildAdapterPosition(view); // item position
                    int column = position % 4; // item column

                    outRect.left = spacingInPixels - column * spacingInPixels / 4;
                    outRect.right = (column + 1) * spacingInPixels / 4;

                    if (position < 4) { // top edge
                        outRect.top = spacingInPixels;
                    }
                    outRect.bottom = spacingInPixels; // item bottom
                }
            });



            Adapter_BaiViet adapter = new Adapter_BaiViet(getActivity(), arrayListBaiViet);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
    public void updateData(List<ChuongViet> newData) {
        arrayListBaiViet.clear();
        arrayListBaiViet.addAll(newData);
        Adapter_BaiViet adapter = new Adapter_BaiViet(getActivity(), arrayListBaiViet);
        recyclerView.setAdapter(adapter);
    }


}