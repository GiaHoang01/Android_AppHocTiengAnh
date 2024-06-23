package com.example.doanapphoctienganh.Database.Handle;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.Database.Class.BaiHoc;
import com.example.doanapphoctienganh.Database.Class.BaiTuVungTheoChuong;
import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.Database.Class.Viet;

import java.util.ArrayList;

public class LHandle extends SQLiteOpenHelper {
    public String DB_NAME;
    public int DB_VERSION;
    public String path;

    public String getDB_NAME() {
        return DB_NAME;
    }

    public void setDB_NAME(String DB_NAME) {
        this.DB_NAME = DB_NAME;
    }

    public int getDB_VERSION() {
        return DB_VERSION;
    }

    public void setDB_VERSION(int DB_VERSION) {
        this.DB_VERSION = DB_VERSION;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DB_NAME = name;
        this.DB_VERSION = version;
        this.path = context.getFilesDir()+"/DB/"+DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList<BaiHoc> loadBaiHocTuVung()
    {
        SQLiteDatabase db;
        ArrayList<BaiHoc> lsBaiHocTuVung=new ArrayList<>();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("select TenChuong from BaiHoc where TenBaiHoc='Từ Vựng' ORDER by TenChuong",null);
        cursor.moveToFirst();
        do {
            BaiHoc baiHoc = new BaiHoc();
            baiHoc.setTenChuong(cursor.getString(0));
            lsBaiHocTuVung.add(baiHoc);
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return lsBaiHocTuVung;
    }
    public ArrayList<BaiHoc> loadBaiHocNguPhap()
    {
        SQLiteDatabase db;
        ArrayList<BaiHoc> lsBaiHocTuVung=new ArrayList<>();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("select * from BaiHoc where TenBaiHoc='Ngữ Pháp'",null);
        cursor.moveToFirst();
        do {
            BaiHoc baiHoc = new BaiHoc();
            baiHoc.setIdBaiHoc(cursor.getInt(0));
            baiHoc.setTenBaiHoc(cursor.getString(1));
            baiHoc.setTrangThai(cursor.getString(2));
            baiHoc.setTenChuong(cursor.getString(3));
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return lsBaiHocTuVung;
    }
}
