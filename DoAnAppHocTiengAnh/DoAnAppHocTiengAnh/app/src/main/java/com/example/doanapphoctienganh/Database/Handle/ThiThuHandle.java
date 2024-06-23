package com.example.doanapphoctienganh.Database.Handle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.Database.Class.BaiKiemTra;
import com.example.doanapphoctienganh.Database.Class.CauHoi;

import java.util.ArrayList;
import java.util.List;

public class ThiThuHandle extends SQLiteOpenHelper{
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

    public ThiThuHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DB_NAME = name;
        this.DB_VERSION = version;
        this.path= context.getFilesDir()+"/DB/"+DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<BaiKiemTra> getAllBaiKiemTra() {
        List<BaiKiemTra> baiKiemTraList = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM BaiKiemTra", null);
        cursor.moveToFirst();
        do {
            BaiKiemTra baiKT = new BaiKiemTra();
            baiKT.setIDBaiKiemTra(cursor.getInt(0));
            baiKT.setTenBaiKiemTra(cursor.getString(1));
            baiKT.settrangThai(cursor.getString(2));
            baiKiemTraList.add(baiKT);
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return baiKiemTraList;
    }

    public ArrayList<CauHoi> getCauHoiByBaiKiemTraId(int idBaiKiemTra) {
        ArrayList<CauHoi> cauHoiList = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM CauHoi WHERE IDBaiKiemTra = ?", new String[]{String.valueOf(idBaiKiemTra)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CauHoi cauHoi = new CauHoi();
            cauHoi.setIDCauHoi(cursor.getInt(0));
            cauHoi.setCauHoi(cursor.getString(1));
            cauHoi.setDapAnDung(cursor.getInt(2));
            cauHoi.setDapAnA(cursor.getString(3));
            cauHoi.setDapAnB(cursor.getString(4));
            cauHoi.setDapAnC(cursor.getString(5));
            cauHoi.setDapAnD(cursor.getString(6));
            cauHoi.setIDBaiKiemTra(cursor.getInt(7));
            cauHoiList.add(cauHoi);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return cauHoiList;
    }


    public ArrayList<String> loadCauThiDaLam()
    {
        SQLiteDatabase db;
        ArrayList<String> lsBai = new ArrayList<>();
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("select TenBaiKiemTra from BaiKiemTra where TrangThai='Hoàn thành'", null);

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



    public boolean updateTrangThai(String tenBai, String newStatus) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        boolean isSuccess = false;

        try {
            // Câu lệnh SQL để cập nhật trạng thái
            String updateQuery = "update BaiKiemTra set TrangThai = ? where TenBaiKiemTra = ?";

            SQLiteStatement statement = db.compileStatement(updateQuery);
            statement.bindString(1, newStatus);
            statement.bindString(2, tenBai);

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