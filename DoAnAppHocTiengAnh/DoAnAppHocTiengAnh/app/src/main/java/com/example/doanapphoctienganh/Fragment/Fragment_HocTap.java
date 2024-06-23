package com.example.doanapphoctienganh.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanapphoctienganh.Adapter.Adapter_DanhSachHocTap;
import com.example.doanapphoctienganh.Class.HocTap;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_HocTap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_HocTap extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<HocTap> arrayListHome=new ArrayList<>();
    int[] lsIdImg = new int[]{R.drawable.tuvung, R.drawable.nguphap};
    String[]lsName= new String[]{"Từ Vựng","Ngữ Pháp"};




    public Fragment_HocTap() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_HocTap.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_HocTap newInstance(String param1, String param2) {
        Fragment_HocTap fragment = new Fragment_HocTap();
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__hoc_tap, container, false);

        // Null check for lsName array
        if (lsName != null) {
            for (int i = 0; i < lsName.length; i++) {
                arrayListHome.add(new HocTap(lsIdImg[i], lsName[i]));
            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycleDanhSach);
        if (recyclerView != null) {
            // Initialize layout manager
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
            recyclerView.setLayoutManager(layoutManager);

            // Initialize and set adapter
            Adapter_DanhSachHocTap adapter = new Adapter_DanhSachHocTap(getActivity(), arrayListHome);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}