package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.ActivityViet.LuyenTapViet;
import com.example.doanapphoctienganh.Database.Class.ChuongViet;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class Adapter_BaiViet extends RecyclerView.Adapter<Adapter_BaiViet.HomeViewHolder> {
    private ArrayList<ChuongViet> danhsachBaiViet;
    private LayoutInflater inflater;

    public Adapter_BaiViet(Context context,ArrayList<ChuongViet> danhsachBaiViet) {
        this.danhsachBaiViet = danhsachBaiViet;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new Adapter_BaiViet.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        ChuongViet bai = danhsachBaiViet.get(position);
        holder.textView.setText(bai.getTenBai());
        holder.imageView.setImageResource(R.drawable.nguphap);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myintent = new Intent(holder.itemView.getContext(), LuyenTapViet.class);
                Bundle bundle= new Bundle();
                bundle.putString("tenBai", bai.getTenBai());
                myintent.putExtra("package",bundle);
                holder.itemView.getContext().startActivity(myintent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return danhsachBaiViet.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_Nghe);
            textView = itemView.findViewById(R.id.txt_TenChuDeNghe);
        }
    }

}
