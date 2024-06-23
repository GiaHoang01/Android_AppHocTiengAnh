package com.example.doanapphoctienganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doanapphoctienganh.Database.Class.Viet;
import com.example.doanapphoctienganh.R;

import java.util.List;

public class Adapter_lv_DSDapAnViet extends BaseAdapter {

    Context context;
    int layout;
    private List<Viet> list;
    private List<String> cauHoiDungSai;

    public Adapter_lv_DSDapAnViet(Context context, int layout, List<Viet> list, List<String> cauHoiDungSai) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.cauHoiDungSai = cauHoiDungSai;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viet viet=list.get(position);
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        TextView txt_CauHoi = convertView.findViewById(R.id.txt_CauHoiDungSai);
        String text="Câu Hỏi "+(position+1)+": "+cauHoiDungSai.get(position);
        txt_CauHoi.setText(text);

        TextView txt_idCauHoi = convertView.findViewById(R.id.txt_DapAn);
        txt_idCauHoi.setText(viet.getDapAn());
        return convertView;
    }
}
