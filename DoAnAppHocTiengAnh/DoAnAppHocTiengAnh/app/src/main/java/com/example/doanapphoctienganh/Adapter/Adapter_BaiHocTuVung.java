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

import com.example.doanapphoctienganh.Database.Class.BaiHoc;
import com.example.doanapphoctienganh.R;
import com.example.doanapphoctienganh.VActivity.VActivity;

import java.util.ArrayList;

public class Adapter_BaiHocTuVung extends RecyclerView.Adapter<Adapter_BaiHocTuVung.BaiHocViewHolder> {
    private ArrayList<BaiHoc> arrayListBaiHoc;
    private LayoutInflater inflater;

    public Adapter_BaiHocTuVung(Context context,ArrayList<BaiHoc> arrayListBaiHoc) {
        this.inflater = LayoutInflater.from(context);
        this.arrayListBaiHoc=arrayListBaiHoc;
    }

    @NonNull
    @Override
    public Adapter_BaiHocTuVung.BaiHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new BaiHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_BaiHocTuVung.BaiHocViewHolder holder, int position) {
        BaiHoc lesson = arrayListBaiHoc.get(position);
        holder.textView.setText(lesson.getTenChuong());
        holder.imageView.setImageResource(R.drawable.tuvung);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(holder.itemView.getContext(), VActivity.class);
                myintent.putExtra("TenChuong", lesson.getTenChuong());
                holder.itemView.getContext().startActivity(myintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListBaiHoc.size();
    }
    public static class BaiHocViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public BaiHocViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_Nghe);
            textView = itemView.findViewById(R.id.txt_TenChuDeNghe);
        }
    }
}
