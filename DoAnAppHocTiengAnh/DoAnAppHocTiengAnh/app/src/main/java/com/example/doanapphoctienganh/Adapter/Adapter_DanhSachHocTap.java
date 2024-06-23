package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.ActivityNguPhap.ActivityNguPhap;
import com.example.doanapphoctienganh.Class.HocTap;
import com.example.doanapphoctienganh.LActivity.LActivity;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class Adapter_DanhSachHocTap extends RecyclerView.Adapter<Adapter_DanhSachHocTap.HomeViewHolder> {
    private ArrayList<HocTap> danhsachHocTap;
    private LayoutInflater inflater;


    public Adapter_DanhSachHocTap(Context context, ArrayList<HocTap> danhsachHocTap) {
        this.danhsachHocTap = danhsachHocTap;
        this.inflater = LayoutInflater.from(context);
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

    @NonNull
    @Override
    public Adapter_DanhSachHocTap.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new Adapter_DanhSachHocTap.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HocTap hocTap = danhsachHocTap.get(position);
        holder.imageView.setImageResource(hocTap.getImgID());
        holder.textView.setText(hocTap.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hocTap.getName().equals("Từ Vựng"))
                {
                    Intent myintent = new Intent(holder.itemView.getContext(), LActivity.class);
                    holder.itemView.getContext().startActivity(myintent);
                }
                if(hocTap.getName().equals("Ngữ Pháp"))
                {
                    Intent myintent = new Intent(holder.itemView.getContext(), ActivityNguPhap.class);
                    holder.itemView.getContext().startActivity(myintent);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return danhsachHocTap.size();
    }

}
