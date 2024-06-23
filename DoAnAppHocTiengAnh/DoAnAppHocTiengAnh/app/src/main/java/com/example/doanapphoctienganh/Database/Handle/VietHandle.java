package com.example.doanapphoctienganh.Database.Handle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.Database.Class.Viet;

import java.util.ArrayList;

public class VietHandle extends SQLiteOpenHelper {
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

    public VietHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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



    public ArrayList<Viet> loadCauHoi(String tenChuong)
    {
        SQLiteDatabase db;
        ArrayList<Viet> lsCauHoi=new ArrayList<>();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("select Viet.IDViet,BaiHoc.IDBaiHoc,Viet.CauHoi,Viet.DapAn from Viet join BaiHoc on Viet.IDBaiHoc=BaiHoc.IDBaiHoc where BaiHoc.TenChuong='"+tenChuong+"'",null);
        cursor.moveToFirst();
        do {
            Viet cauhoi = new Viet();
            cauhoi.setIdViet(cursor.getInt(0));
            cauhoi.setIdBaiHoc(cursor.getInt(1));
            cauhoi.setCauhoi(cursor.getString(2));
            cauhoi.setDapAn(cursor.getString(3));
            lsCauHoi.add(cauhoi);
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return lsCauHoi;
    }

    public boolean updateTrangThai(String tenChuong, String newStatus) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        boolean isSuccess = false;

        try {
            // Câu lệnh SQL để cập nhật trạng thái
            String updateQuery = "update BaiHoc set TrangThai = ? where TenChuong = ? and TenBaiHoc = 'Viết'";

            SQLiteStatement statement = db.compileStatement(updateQuery);
            statement.bindString(1, newStatus);
            statement.bindString(2, tenChuong);

            // Thực hiện cập nhật và kiểm tra số hàng bị ảnh hưởng
            int affectedRows = statement.executeUpdateDelete();
            isSuccess = affectedRows > 0;  // Nếu có ít nhất một hàng bị ảnh hưởng, cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;  // Trong trường hợp có lỗi, cập nhật thất bại
        } finally {
            db.close();
        }

        return isSuccess;
    }


}
