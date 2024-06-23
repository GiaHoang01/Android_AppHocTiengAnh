package com.example.doanapphoctienganh.LearnToRead;
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

import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class AdapterExerciseRead extends  RecyclerView.Adapter<AdapterExerciseRead.HomeViewHolder> {
    private ArrayList<ReadExercise> danhsachBaiDoc;
    private LayoutInflater inflater;

    public AdapterExerciseRead(Context context, ArrayList<ReadExercise> danhsachBaiDoc) {
        this.danhsachBaiDoc = danhsachBaiDoc;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layoutitem_read, parent, false);
        return new AdapterExerciseRead.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        ReadExercise bai = danhsachBaiDoc.get(position);
        holder.textView.setText(bai.getTenBaiDoc());
        holder.imageView.setImageResource(R.drawable.nguphap);
        if(bai.getTrangThai()==null){
            holder.tvStatus.setText("Chưa Hoàn Thành");
        }
        else {
            holder.tvStatus.setText(bai.getTrangThai());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(holder.itemView.getContext(), ReadTestActivity.class);
                Bundle bundle= new Bundle();
                bundle.putString("tenBai", bai.getTenBaiDoc());
                bundle.putString("noiDung", bai.getNoiDungBaiDoc());
                bundle.putInt("id", bai.getId());

                bundle.putInt("idBaiHoc", bai.getIdBaiHoc());
                myintent.putExtra("package",bundle);
                holder.itemView.getContext().startActivity(myintent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return danhsachBaiDoc.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView,tvStatus;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_DaiDien);
            textView = itemView.findViewById(R.id.txt_Title);
            tvStatus = itemView.findViewById(R.id.txt_Status);
        }
    }
}
