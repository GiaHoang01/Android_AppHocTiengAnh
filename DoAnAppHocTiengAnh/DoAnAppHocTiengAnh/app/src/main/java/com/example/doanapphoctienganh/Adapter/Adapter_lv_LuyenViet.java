package com.example.doanapphoctienganh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanapphoctienganh.Database.Class.Viet;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_lv_LuyenViet extends BaseAdapter {

    Context context;
    int layout;
    private List<Viet> list;
    private List<String> editTextDataList;

    public Adapter_lv_LuyenViet(Context context, int layout, List<Viet> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.editTextDataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            editTextDataList.add("");
        }
    }

    public Adapter_lv_LuyenViet() {
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
        TextView txt_CauHoi = convertView.findViewById(R.id.txt_CauHoi);
        txt_CauHoi.setText(viet.getCauhoi());
        TextView txt_idCauHoi = convertView.findViewById(R.id.txt_IDCauHoi);
        String idcauhoi="Câu "+(position+1)+":";
        txt_idCauHoi.setText(idcauhoi);


        final EditText editText = convertView.findViewById(R.id.etxt_CauTraLoi);

        // Xử lý sự kiện khi người dùng thay đổi nội dung của EditText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Cập nhật dữ liệu trong editTextDataList khi người dùng thay đổi EditText
                editTextDataList.set(position, s.toString());
            }
        });

        return convertView;
    }
    public List<String> getEditTextData() {
        return editTextDataList;
    }
}
