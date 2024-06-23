package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.ActivityNghe.HienThiBaiNghe;
import com.example.doanapphoctienganh.Database.Class.Nghe;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_History_BaiViet extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<String> dsBaiViet = new ArrayList<>();
    public Adapter_History_BaiViet(@NonNull Context context, int resource, @NonNull ArrayList dsBaiViet) {
        super(context, resource, dsBaiViet);
        this.context=context;
        this.layoutItem=resource;
        this.dsBaiViet = dsBaiViet;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String bai = dsBaiViet.get(position);
        View rowView;
        rowView =  LayoutInflater.from(context).inflate(layoutItem, null, true);
        TextView txt_BaiDaLam = (TextView) rowView.findViewById(R.id.txt_TenBai);
        txt_BaiDaLam.setText(bai);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rowView;
    }
}
