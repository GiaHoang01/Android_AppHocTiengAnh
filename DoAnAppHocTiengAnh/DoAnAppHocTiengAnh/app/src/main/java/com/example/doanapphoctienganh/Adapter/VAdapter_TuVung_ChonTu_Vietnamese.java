package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.R;
import com.example.doanapphoctienganh.VActivity.VChonTuActivity;

import java.util.ArrayList;

public class VAdapter_TuVung_ChonTu_Vietnamese extends RecyclerView.Adapter<VAdapter_TuVung_ChonTu_Vietnamese.TuVungChonTuViewHolder>{
    private ArrayList<TuVung> arrayListTuVung;
    private TuVung selectedTuVungEnglish = null;
    private TuVung selectedTuVungVietnamese = null;
    private Context mContext;
    private VChonTuActivity mActivity;
    public VAdapter_TuVung_ChonTu_Vietnamese(Context context, ArrayList<TuVung> arrayListTuVung,VChonTuActivity activity) {
        this.mContext = context;
        this.arrayListTuVung=arrayListTuVung;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public VAdapter_TuVung_ChonTu_Vietnamese.TuVungChonTuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.flashcard_item, parent, false);
        return new VAdapter_TuVung_ChonTu_Vietnamese.TuVungChonTuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VAdapter_TuVung_ChonTu_Vietnamese.TuVungChonTuViewHolder holder, int position) {
        TuVung vocab=arrayListTuVung.get(position);
        holder.textViewkey.setText(vocab.getTiengViet());

        if (vocab.isCorrect()) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            if (vocab.isSelected()) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
            } else {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.transparent));
            }
            holder.itemView.setOnClickListener(v -> mActivity.onVietnameseCardClicked(position));
        }

    }

    @Override
    public int getItemCount() {
        return arrayListTuVung.size();
    }
    public static class TuVungChonTuViewHolder extends RecyclerView.ViewHolder {

        TextView textViewkey;
        public TuVungChonTuViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewkey=itemView.findViewById(R.id.textViewKey);

        }
    }
}
