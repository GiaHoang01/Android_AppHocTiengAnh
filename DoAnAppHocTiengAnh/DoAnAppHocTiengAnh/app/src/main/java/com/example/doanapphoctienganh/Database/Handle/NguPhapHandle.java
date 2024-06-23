package com.example.doanapphoctienganh.Database.Handle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.Database.Class.NguPhap;

import java.util.ArrayList;

public class NguPhapHandle extends SQLiteOpenHelper {

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

    public NguPhapHandle(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DB_NAME = name;
        this.DB_VERSION = version;
        assert context != null;
        this.path= context.getFilesDir()+"/DB/"+DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList<NguPhap> getAllNguPhap(String ten) {
        ArrayList<NguPhap> nguPhapList = new ArrayList<>();
        String selectQuery = "SELECT * FROM NguPhap\n" +
                "JOIN BaiHoc ON NguPhap.IDBaiHoc = BaiHoc.IDBaiHoc\n" +
                "WHERE BaiHoc.TenChuong = ?";

        SQLiteDatabase db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery(selectQuery, new String[]{ten});

        int stt= 0;
        if (cursor.moveToFirst()) {
            do {
                stt +=1;
                NguPhap nguPhap = new NguPhap();
                nguPhap.setTenNguPhap(cursor.getString(2));
                nguPhap.setCongThuc(cursor.getString(3));
                nguPhap.setViDu(cursor.getString(4));
                nguPhap.setMoTa(cursor.getString(5));
                nguPhap.setSttNguPhap(stt);
                nguPhapList.add(nguPhap);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return nguPhapList;
    }
}
