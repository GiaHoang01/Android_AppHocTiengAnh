package com.example.doanapphoctienganh.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.Database.Class.NguPhap;
import com.example.doanapphoctienganh.Interface.OnItemClickListener;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class Adapter_NguPhap extends RecyclerView.Adapter<Adapter_NguPhap.MyViewHolder> {

    private List<NguPhap> nguPhapList;
    private OnItemClickListener mListener;
    public class  MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tenNguPhap, sttNguPhap;

        public  MyViewHolder(View view, OnItemClickListener listener){
            super(view);
            tenNguPhap = (TextView) view.findViewById(R.id.txt_ten_nguphap);
            sttNguPhap = (TextView) view.findViewById(R.id.txt_stt_nguphap);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public Adapter_NguPhap(List<NguPhap> nguPhapList, OnItemClickListener listener) {
        this.nguPhapList = nguPhapList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_nguphap,parent,false);

        return new MyViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NguPhap nguPhap = nguPhapList.get(position);

        holder.tenNguPhap.setText(nguPhap.getTenNguPhap());
        holder.sttNguPhap.setText(String.valueOf(nguPhap.getSttNguPhap()));
    }


    @Override
    public int getItemCount() {
        return nguPhapList.size();
    }
}
