package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class VApdapter_TuVung_ChonTu extends RecyclerView.Adapter<VApdapter_TuVung_ChonTu.TuVungChonTuViewHolder> {
    private ArrayList<TuVung> arrayListTuVung;
    private LayoutInflater inflater;
    private int selectedPosition = RecyclerView.NO_POSITION;
    public VApdapter_TuVung_ChonTu(Context context, ArrayList<TuVung> arrayListTuVung) {
        this.inflater = LayoutInflater.from(context);
        this.arrayListTuVung=arrayListTuVung;
    }

    @NonNull
    @Override
    public VApdapter_TuVung_ChonTu.TuVungChonTuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vcustom_listview_answer, parent, false);
        return new VApdapter_TuVung_ChonTu.TuVungChonTuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VApdapter_TuVung_ChonTu.TuVungChonTuViewHolder holder, int position) {
        TuVung vocab=arrayListTuVung.get(position);
        holder.btn_answer.setText(vocab.getTiengAnh());
        holder.btn_answer.setSelected(selectedPosition == position);
        holder.btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListTuVung.size();
    }
    public static class TuVungChonTuViewHolder extends RecyclerView.ViewHolder {

        Button btn_answer;

        public TuVungChonTuViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_answer=itemView.findViewById(R.id.btn_answer);
        }
    }
}
