package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class VAdapter_TuVung_DinhNghia extends RecyclerView.Adapter<VAdapter_TuVung_DinhNghia.ViewHolder> {

    private List<TuVung> list;

    public VAdapter_TuVung_DinhNghia( List<TuVung> list)
    {
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_koloda, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull VAdapter_TuVung_DinhNghia.ViewHolder holder, int position) {
        // Lấy từ vựng tại vị trí position
        TuVung tuVung = list.get(position);

        // Hiển thị từ vựng trong ViewHolder
        holder.textViewEnglish.setText(tuVung.getTiengAnh());
        holder.textViewVietnamese.setText(tuVung.getTiengViet());
    }


//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEnglish, textViewVietnamese;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            textViewVietnamese = itemView.findViewById(R.id.textViewVietnamese);
        }
    }
}
