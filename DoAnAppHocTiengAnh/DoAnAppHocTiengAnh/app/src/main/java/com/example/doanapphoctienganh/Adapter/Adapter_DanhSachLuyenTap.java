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

import com.example.doanapphoctienganh.ActivityNghe.DanhSachBaiNghe;
import com.example.doanapphoctienganh.ActivityViet.DanhSachChonChuongViet;
import com.example.doanapphoctienganh.Class.LuyenTap;
import com.example.doanapphoctienganh.LearnToRead.ReadListActivity;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class Adapter_DanhSachLuyenTap extends RecyclerView.Adapter<Adapter_DanhSachLuyenTap.HomeViewHolder> {
    private ArrayList<LuyenTap> danhsachLuyenTap;
    private LayoutInflater inflater;

    public Adapter_DanhSachLuyenTap(Context context, ArrayList<LuyenTap> danhsachLuyenTap) {
        this.danhsachLuyenTap = danhsachLuyenTap;
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
    public Adapter_DanhSachLuyenTap.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new Adapter_DanhSachLuyenTap.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        LuyenTap luyentap = danhsachLuyenTap.get(position);
        holder.imageView.setImageResource(luyentap.getImgID());
        holder.textView.setText(luyentap.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (luyentap.getName().equals("Viết")) {
                    Intent myintent = new Intent(holder.itemView.getContext(), DanhSachChonChuongViet.class);
                    holder.itemView.getContext().startActivity(myintent);
                }
                else if (luyentap.getName().equals("Nghe")) {
                    Intent myintent = new Intent(holder.itemView.getContext(), DanhSachBaiNghe.class);
                    holder.itemView.getContext().startActivity(myintent);
                }
                else if (luyentap.getName().equals("Đọc")) {
                    Intent myintent = new Intent(holder.itemView.getContext(), ReadListActivity.class);
                    holder.itemView.getContext().startActivity(myintent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return danhsachLuyenTap.size();
    }

}
