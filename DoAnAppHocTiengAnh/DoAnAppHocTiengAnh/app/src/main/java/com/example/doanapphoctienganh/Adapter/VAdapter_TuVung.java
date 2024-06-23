package com.example.doanapphoctienganh.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.Database.Handle.VHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class VAdapter_TuVung extends RecyclerView.Adapter<VAdapter_TuVung.TuVungViewHolder>{
    private ArrayList<TuVung> arrayListTuVung;
    private LayoutInflater inflater;
    private VHandle vHandle;
    private SharedPreferences sharedPreferences;

    public VAdapter_TuVung(Context context, ArrayList<TuVung> arrayListTuVung) {
        this.inflater = LayoutInflater.from(context);
        this.arrayListTuVung=arrayListTuVung;
        this.vHandle = new VHandle(context, "Android.db", null, 1);
        this.sharedPreferences = context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public VAdapter_TuVung.TuVungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vocab_item, parent, false);
        return new VAdapter_TuVung.TuVungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VAdapter_TuVung.TuVungViewHolder holder, int position) {
        TuVung vocab=arrayListTuVung.get(position);
        holder.textView.setText(vocab.getTiengAnh());
        holder.textView1.setText(vocab.getPhienAm());
        holder.textView2.setText(vocab.getTiengViet());
        holder.textView3.setText('('+vocab.getLoaiTu()+')');
        holder.textView4.setText(vocab.getDangChuY());

    }
    private void saveFavoriteStatus(TuVung vocab) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (vocab.isFavorite()) {
            editor.putBoolean(vocab.getTiengAnh(), true);
        } else {
            editor.remove(vocab.getTiengAnh());
        }
        editor.apply();
    }
    private void updateVocabulary(TuVung vocab) {
        vHandle.saveVocabulary(vocab); // Lưu vào cơ sở dữ liệu SQLite
        notifyDataSetChanged(); // Cập nhật lại RecyclerView
    }
    @Override
    public int getItemCount() {
        return arrayListTuVung.size();
    }
    public static class TuVungViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView1,textView2,textView3,textView4;
        ImageButton imageButton;


        public TuVungViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_English);
            textView1=itemView.findViewById(R.id.txt_PhienAm);
            textView2=itemView.findViewById(R.id.txt_Vietnamese);
            textView3=itemView.findViewById(R.id.txt_LoaiTu);
            textView4=itemView.findViewById(R.id.txt_GhiChu);
        }
    }

}