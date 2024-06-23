package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.R;

import java.util.ArrayList;

public class Adapter_History_BaiDoc extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<String> dsBaiDoc = new ArrayList<>();
    public Adapter_History_BaiDoc(@NonNull Context context, int resource, @NonNull ArrayList dsBaiDoc) {
        super(context, resource, dsBaiDoc);
        this.context=context;
        this.layoutItem=resource;
        this.dsBaiDoc = dsBaiDoc;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String bai = dsBaiDoc.get(position);
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
