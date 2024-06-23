package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanapphoctienganh.ActivityThiThu.ThiThuFragment;
import com.example.doanapphoctienganh.Class.LuyenThi;
import com.example.doanapphoctienganh.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Adapter_DanhSachLuyenThi extends RecyclerView.Adapter<Adapter_DanhSachLuyenThi.HomeViewHolder> {

    private ArrayList<LuyenThi> danhsachLuyenThi;
    private LayoutInflater inflater;

    public Adapter_DanhSachLuyenThi(Context context, ArrayList<LuyenThi> danhsachLuyenThi) {
        this.danhsachLuyenThi = danhsachLuyenThi;
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
    public Adapter_DanhSachLuyenThi.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new Adapter_DanhSachLuyenThi.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        LuyenThi LuyenThi = danhsachLuyenThi.get(position);
        holder.imageView.setImageResource(LuyenThi.getImgID());
        holder.textView.setText(LuyenThi.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LuyenThi.getName().equals("Thi Thử")) {
                    ThiThuFragment thiThuFragment = new ThiThuFragment();
                    FragmentTransaction transaction = ((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameFragmentbai2, thiThuFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    BottomNavigationView bottomNavigationView = ((AppCompatActivity) holder.itemView.getContext()).findViewById(R.id.bttnav2);
                    bottomNavigationView.setSelectedItemId(R.id.Thi);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return danhsachLuyenThi.size();
    }
}
