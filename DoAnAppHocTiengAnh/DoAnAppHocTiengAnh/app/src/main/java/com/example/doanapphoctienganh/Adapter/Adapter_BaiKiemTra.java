package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.ActivityThiThu.LoadCauHoi;
import com.example.doanapphoctienganh.Database.Class.BaiKiemTra;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class Adapter_BaiKiemTra extends RecyclerView.Adapter<Adapter_BaiKiemTra.ViewHolder>{
    private Context context;
    private List<BaiKiemTra> baiKiemTraList;

    public Adapter_BaiKiemTra(Context context, List<BaiKiemTra> baiKiemTraList) {
        this.context = context;
        this.baiKiemTraList = baiKiemTraList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thi_thu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiKiemTra baiKiemTra = baiKiemTraList.get(position);
        holder.txtTitleLesson.setText(baiKiemTra.getTenBaiKiemTra());
        holder.btnStartExam.setOnClickListener(new View.OnClickListener() {
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
        return baiKiemTraList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleLesson;
        TextView txtLessonDes;
        Button btnStartExam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleLesson = itemView.findViewById(R.id.txtTitleLesson);
            txtLessonDes = itemView.findViewById(R.id.txtLessonDes);
            btnStartExam = itemView.findViewById(R.id.btnStartExam);
        }
    }
}
