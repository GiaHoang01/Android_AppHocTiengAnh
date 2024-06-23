package com.example.doanapphoctienganh.Database.Handle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.Database.Class.ChuongViet;

import java.util.ArrayList;

public class BaiHocHandle extends SQLiteOpenHelper {
    public String DB_NAME;
    public int DB_VERSION;
    public String path;

    public BaiHocHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DB_NAME = name;
        this.DB_VERSION = version;
        this.path=context.getFilesDir()+"/DB/"+DB_NAME;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> loadCauVietDaLam()
    {
        SQLiteDatabase db;
        ArrayList<String> lsBai = new ArrayList<>();
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("select TenChuong from BaiHoc where TenBaiHoc='Viết' and TrangThai='Hoàn thành'", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String tenBai = cursor.getString(0);
                    lsBai.add(tenBai);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return lsBai;
    }

    public ArrayList<String> loadCauDocDaLam()
    {
        SQLiteDatabase db;
        ArrayList<String> lsBai = new ArrayList<>();
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("select TenChuong from BaiHoc where TenBaiHoc='Đọc' and TrangThai='Hoàn thành'", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String tenBai = cursor.getString(0);
                    lsBai.add(tenBai);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return lsBai;
    }


}
