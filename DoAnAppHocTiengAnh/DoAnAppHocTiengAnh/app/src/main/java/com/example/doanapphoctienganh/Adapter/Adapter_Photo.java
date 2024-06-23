package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.doanapphoctienganh.Class.Photo;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class Adapter_Photo extends PagerAdapter {

    private Context mContext;
    private List<Photo> mList;

    public Adapter_Photo(Context mContext, List<Photo> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.chuyenanh,container,false);
        ImageView img_Photo=view.findViewById(R.id.img_ChuyenAnh);
        Photo photo=mList.get(position);
        if(photo!=null)
        {
            Glide.with(mContext).load(photo.getResourceID()).into(img_Photo);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(mList!=null)
        {
            return mList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((View)object) ;
    }
}
