package com.example.doanapphoctienganh;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.doanapphoctienganh.Adapter.Adapter_Photo;
import com.example.doanapphoctienganh.Class.Photo;
import com.example.doanapphoctienganh.Fragment.Fragment_HocTap;
import com.example.doanapphoctienganh.Fragment.Fragment_LuyenTap;
import com.example.doanapphoctienganh.Fragment.Fragment_LuyenThi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    FrameLayout FL_HocTap;
    FrameLayout FL_LuyenTap;
    FrameLayout FL_LuyenThi;
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Tìm kiếm các thành phần trong layout của fragment
        ViewPager viewPager = rootView.findViewById(R.id.viewpager_Home);
        CircleIndicator circleIndicator = rootView.findViewById(R.id.circle_indicator_Home);

        if (viewPager != null) {
            Adapter_Photo adapterPhoto = new Adapter_Photo(getContext(), getListPhoto());
            viewPager.setAdapter(adapterPhoto);
            circleIndicator.setViewPager(viewPager);
            adapterPhoto.registerDataSetObserver(circleIndicator.getDataSetObserver());
        }
        autoImageSlide(viewPager,getListPhoto());

        //fragment layout trang chu
        FL_HocTap=(FrameLayout) rootView.findViewById(R.id.FL_HocTap);
        loadFragment_HocTap(new Fragment_HocTap());
        FL_LuyenTap=(FrameLayout) rootView.findViewById(R.id.FL_LuyenTap);
        loadFragment_LuyenTap(new Fragment_LuyenTap());
        FL_LuyenThi=(FrameLayout) rootView.findViewById(R.id.FL_LuyenThi);
        loadFragment_LuyenThi(new Fragment_LuyenThi());

        return rootView;
    }
    public void loadFragment_LuyenTap(Fragment fragment)
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FL_LuyenTap,fragment);
        ft.commit();
    }
    public void loadFragment_LuyenThi(Fragment fragment)
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FL_LuyenThi,fragment);
        ft.commit();
    }
    public void loadFragment_HocTap(Fragment fragment)
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FL_HocTap,fragment);
        ft.commit();
    }


    public void autoImageSlide(ViewPager viewPager,List<Photo> list)
    {
        final long Delay_MS=3000;
        final long PERIOD_MS=3000;
        final Handler handler= new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if(viewPager.getCurrentItem()==list.size()-1)
                {
                    viewPager.setCurrentItem(0);
                }
                else
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
                }
            }
        };

        Timer timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }

        },Delay_MS,PERIOD_MS);
    }
    public List<Photo> getListPhoto()
    {
        List<Photo> list= new ArrayList<>();
        list.add(new Photo(R.drawable.anh2));
        list.add(new Photo(R.drawable.anh3));
        list.add(new Photo(R.drawable.anh4));
        list.add(new Photo(R.drawable.anh5));
        return list;
    }
}