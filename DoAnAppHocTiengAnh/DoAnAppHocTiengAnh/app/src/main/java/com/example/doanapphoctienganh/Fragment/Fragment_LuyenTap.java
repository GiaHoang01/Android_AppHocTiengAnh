package com.example.doanapphoctienganh.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanapphoctienganh.Adapter.Adapter_DanhSachLuyenTap;
import com.example.doanapphoctienganh.Class.LuyenTap;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_LuyenTap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_LuyenTap extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<LuyenTap> arrayListHome=new ArrayList<>();
    int[] lsIdImg = new int[]{R.drawable.nghe, R.drawable.doc, R.drawable.viet};
    String[]lsName= new String[]{"Nghe","Đọc","Viết"};


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_LuyenTap() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_LuyenTap.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_LuyenTap newInstance(String param1, String param2) {
        Fragment_LuyenTap fragment = new Fragment_LuyenTap();
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
        View view = inflater.inflate(R.layout.fragment__luyen_tap, container, false);

        // Null check for lsName array
        if (lsName != null) {
            for (int i = 0; i < lsName.length; i++) {
                arrayListHome.add(new LuyenTap(lsIdImg[i], lsName[i]));
            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycleDanhSach);
        if (recyclerView != null) {
            // Initialize layout manager
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
            recyclerView.setLayoutManager(layoutManager);

            // Initialize and set adapter
            Adapter_DanhSachLuyenTap adapter = new Adapter_DanhSachLuyenTap(getActivity(), arrayListHome);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}