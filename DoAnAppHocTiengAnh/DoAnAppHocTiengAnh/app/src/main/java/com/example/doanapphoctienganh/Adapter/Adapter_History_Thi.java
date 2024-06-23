package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_History_Thi extends ArrayAdapter {

    Context context;
    int layoutItem;
    ArrayList<String> dsBaiThi = new ArrayList<>();
    public Adapter_History_Thi(@NonNull Context context, int resource, @NonNull ArrayList dsBaiThi) {
        super(context, resource, dsBaiThi);
        this.context=context;
        this.layoutItem=resource;
        this.dsBaiThi = dsBaiThi;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String bai = dsBaiThi.get(position);
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
