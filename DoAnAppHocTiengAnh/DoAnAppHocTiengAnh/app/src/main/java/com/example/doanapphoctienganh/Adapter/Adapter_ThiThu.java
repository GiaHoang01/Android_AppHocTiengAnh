package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.ActivityThiThu.LoadCauHoi;
import com.example.doanapphoctienganh.Database.Class.BaiKiemTra;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class Adapter_ThiThu extends RecyclerView.Adapter<Adapter_ThiThu.ViewHolder>{
    private Context context;
    private List<BaiKiemTra> lessonList;

    public Adapter_ThiThu(Context context, List<BaiKiemTra> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thithu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiKiemTra baiKiemTra = lessonList.get(position);
        holder.txtTitleLesson.setText(baiKiemTra.getTenBaiKiemTra());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoadCauHoi.class);
                intent.putExtra("IDBaiKiemTra", baiKiemTra.getIDBaiKiemTra());
                intent.putExtra("EXAM_NAME", baiKiemTra.getTenBaiKiemTra());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Math.min(lessonList.size(), 8);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleLesson;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleLesson = itemView.findViewById(R.id.txt_TitleLession);
        }
    }
}
