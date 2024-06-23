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

public class Adapter_Nghe extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<Nghe> lsNghes = new ArrayList<>();
    public Adapter_Nghe(@NonNull Context context, int resource, @NonNull ArrayList lsNghes) {
        super(context, resource, lsNghes);
        this.context=context;
        this.layoutItem=resource;
        this.lsNghes = lsNghes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Nghe nghe = lsNghes.get(position);
        View rowView;
        rowView =  LayoutInflater.from(context).inflate(layoutItem, null, true);
        TextView txt_TenBaiNghe = (TextView) rowView.findViewById(R.id.txt_TenChuDeNghe);
        txt_TenBaiNghe.setText(String.valueOf(nghe.getChuDeNghe()));
        ImageView img_anh = (ImageView) rowView.findViewById(R.id.img_Nghe);
        img_anh.setImageResource(R.drawable.nghe);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(rowView.getContext(), HienThiBaiNghe.class);
                intent.putExtra("Nghe", nghe);
                rowView.getContext().startActivity(intent);
            }
        });
        return rowView;
    }
}
