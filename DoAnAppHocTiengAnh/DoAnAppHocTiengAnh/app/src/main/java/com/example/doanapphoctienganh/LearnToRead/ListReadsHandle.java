package com.example.doanapphoctienganh.LearnToRead;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.Database.Class.Viet;

import java.util.ArrayList;

public class ListReadsHandle extends SQLiteOpenHelper {
    public String DB_NAME;
    public int DB_VERSION;
    public String path;

    public ListReadsHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
    public ArrayList<ReadExercise> getAllBaiDoc()
    {
        SQLiteDatabase db;
        ArrayList<ReadExercise> lsBai=new ArrayList<>();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT IDDoc,TenChuong,NoiDungBaiDoc,Doc.IDBaiHoc,BaiHoc.TrangThai FROM Doc JOIN BaiHoc on Doc.IDBaiHoc = BaiHoc.IDBaiHoc",null);
        cursor.moveToFirst();
        do {
            ReadExercise baiDoc= new ReadExercise();
            baiDoc.setId(cursor.getInt(0));
            baiDoc.setTenBaiDoc(cursor.getString(1));
            baiDoc.setNoiDungBaiDoc(cursor.getString(2));
            baiDoc.setIdBaiHoc(Integer.parseInt(cursor.getString(3)));
            baiDoc.setTrangThai(cursor.getString(4));
            lsBai.add(baiDoc);
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return lsBai;
    }
    public Integer getHighCore(int idDoc) {
        int core = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = null;
        try {
            String query = "SELECT Highcore FROM Doc WHERE IDDoc = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(idDoc)});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    ReadExercise baiDoc = new ReadExercise();
                    core = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return core;
    }

    public ArrayList<ReadTest> getAllQuestion(int idRead)
    {
        SQLiteDatabase db;
        ArrayList<ReadTest> lsBai=new ArrayList<>();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String[] selectionArgs = { String.valueOf(idRead) };
        Cursor cursor = db.rawQuery("SELECT * FROM CauHoiBaiDoc WHERE IDDoc = ?;", selectionArgs);
        cursor.moveToFirst();
        do {
            ReadTest baiDoc= new ReadTest();
            baiDoc.setId(cursor.getInt(0));
            baiDoc.setIdReads(cursor.getInt(7));
            baiDoc.setQuestion(cursor.getString(1));
            baiDoc.setOption1(cursor.getString(2));
            baiDoc.setOption2(cursor.getString(3));
            baiDoc.setOption3(cursor.getString(4));
            baiDoc.setOption4(cursor.getString(5));
            baiDoc.setAnswer(cursor.getInt(6));
            lsBai.add(baiDoc);
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return lsBai;
    }
    public boolean updateBaiHocTrangThai(int idBaiHoc, String newTrangThai) {
        SQLiteDatabase db = null;

        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues contentValues = new ContentValues();
            contentValues.put("TrangThai", newTrangThai);
            int rowsAffected = db.update("BaiHoc", contentValues, "IDBaiHoc = ?", new String[]{String.valueOf(idBaiHoc)});
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
    public ArrayList<ReadExercise> loadCauHoi(String tenChuong)
    {
        SQLiteDatabase db;
        ArrayList<ReadExercise> lsBai=new ArrayList<>();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT IDDoc,TenChuong,NoiDungBaiDoc,Doc.IDBaiHoc,BaiHoc.TrangThai FROM Doc JOIN BaiHoc on Doc.IDBaiHoc = BaiHoc.IDBaiHoc where BaiHoc.TenChuong='"+tenChuong+"'",null);
        cursor.moveToFirst();
        do {
            ReadExercise baiDoc= new ReadExercise();
            baiDoc.setId(cursor.getInt(0));
            baiDoc.setTenBaiDoc(cursor.getString(1));
            baiDoc.setNoiDungBaiDoc(cursor.getString(2));
            baiDoc.setIdBaiHoc(Integer.parseInt(cursor.getString(3)));
            baiDoc.setTrangThai(cursor.getString(4));
            lsBai.add(baiDoc);
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return lsBai;
    }
    public boolean updateHighCore(int idBaiHoc, int highcore) {
        SQLiteDatabase db = null;

        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues contentValues = new ContentValues();
            contentValues.put("Highcore", highcore);
            int rowsAffected = db.update("Doc", contentValues, "IDDoc = ?", new String[]{String.valueOf(idBaiHoc)});
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
