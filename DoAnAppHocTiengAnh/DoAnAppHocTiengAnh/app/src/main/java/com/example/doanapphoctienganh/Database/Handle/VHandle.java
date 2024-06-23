package com.example.doanapphoctienganh.Database.Handle;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.doanapphoctienganh.Database.Class.TuVung;
import com.example.doanapphoctienganh.Database.Class.Viet;
import com.example.doanapphoctienganh.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VHandle extends SQLiteOpenHelper {
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
    private static final String TABLE_SAVED_VOCAB = "SavedVocabulary";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ENGLISH = "english";
    private static final String COLUMN_PHONETIC = "phonetic";
    private static final String COLUMN_VIETNAMESE = "vietnamese";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NOTE = "note";

    public VHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DB_NAME = name;
        this.DB_VERSION = version;
        this.path = context.getFilesDir()+"/DB/"+DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SAVED_VOCAB_TABLE = "CREATE TABLE " + TABLE_SAVED_VOCAB + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ENGLISH + " TEXT,"
                + COLUMN_PHONETIC + " TEXT,"
                + COLUMN_VIETNAMESE + " TEXT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_NOTE + " TEXT"
                + ")";
        db.execSQL(CREATE_SAVED_VOCAB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_VOCAB);
        onCreate(db);
    }
    public ArrayList<TuVung> loadTuVung(String tenChuong) {
        if (tenChuong == null) {

            return new ArrayList<>(); // Trả về danh sách rỗng
        }

        SQLiteDatabase db;
        ArrayList<TuVung> lsTuVung = new ArrayList<>();
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sqlQuery = "select TiengAnh, TiengViet, PhienAm, LoaiTu from TuVung t, BaiHoc b WHERE t.IDBaiHoc = b.IDBaiHoc AND b.TenBaiHoc = 'Từ Vựng' AND b.TenChuong=?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{tenChuong});
        cursor.moveToFirst();
        do {
            TuVung tuVung = new TuVung();
            tuVung.setTiengAnh(cursor.getString(0));
            tuVung.setTiengViet(cursor.getString(1));
            tuVung.setPhienAm(cursor.getString(2));
            tuVung.setLoaiTu(cursor.getString(3));
            lsTuVung.add(tuVung);
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return lsTuVung;
    }
    public List<TuVung> loadTuVungTiengAnh_Viet(String tenChuong) {
        if (tenChuong == null) {

            return new ArrayList<>(); // Trả về danh sách rỗng
        }
        SQLiteDatabase db;
        ArrayList<TuVung> lsTuVung = new ArrayList<>();
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sqlQuery = "select TiengAnh, TiengViet from TuVung t, BaiHoc b WHERE t.IDBaiHoc = b.IDBaiHoc AND b.TenBaiHoc = 'Từ Vựng' AND b.TenChuong=?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{tenChuong});
        cursor.moveToFirst();
        do {
            TuVung tuVung = new TuVung();
            tuVung.setTiengAnh(cursor.getString(0));
            tuVung.setTiengViet(cursor.getString(1));
            lsTuVung.add(tuVung);
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return lsTuVung;
    }
    public List<TuVung> loadTuVungTiengAnh(String tenChuong) {
        if (tenChuong == null) {

            return new ArrayList<>(); // Trả về danh sách rỗng
        }
        SQLiteDatabase db;
        ArrayList<TuVung> lsTuVung = new ArrayList<>();
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sqlQuery = "select IDTuVung, TiengAnh from TuVung t, BaiHoc b WHERE t.IDBaiHoc = b.IDBaiHoc AND b.TenBaiHoc = 'Từ Vựng' AND b.TenChuong=?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{tenChuong});
        cursor.moveToFirst();
        do {
            TuVung tuVung = new TuVung();
            tuVung.setIdTuVung(cursor.getInt(0));
            tuVung.setTiengAnh(cursor.getString(1));
            lsTuVung.add(tuVung);
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return lsTuVung;
    }
    public List<TuVung> loadTuVungTiengViet(String tenChuong) {
        if (tenChuong == null) {

            return new ArrayList<>(); // Trả về danh sách rỗng
        }
        SQLiteDatabase db;
        ArrayList<TuVung> lsTuVung = new ArrayList<>();
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sqlQuery = "select IDTuVung, TiengViet from TuVung t, BaiHoc b WHERE t.IDBaiHoc = b.IDBaiHoc AND b.TenBaiHoc = 'Từ Vựng' AND b.TenChuong=?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{tenChuong});
        cursor.moveToFirst();
        do {
            TuVung tuVung = new TuVung();
            tuVung.setIdTuVung(cursor.getInt(0));
            tuVung.setTiengViet(cursor.getString(1));
            lsTuVung.add(tuVung);
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        Collections.shuffle(lsTuVung);
        return lsTuVung;
    }
    public void saveVocabulary(TuVung vocab) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ENGLISH, vocab.getTiengAnh());
        values.put(COLUMN_PHONETIC, vocab.getPhienAm());
        values.put(COLUMN_VIETNAMESE, vocab.getTiengViet());
        values.put(COLUMN_TYPE, vocab.getLoaiTu());
        values.put(COLUMN_NOTE, vocab.getDangChuY());
        db.insert(TABLE_SAVED_VOCAB, null, values);
        db.close();
    }
    @SuppressLint("Range")
    //  Method to get all saved vocabulary
    public ArrayList<TuVung> getSavedVocabulary() {
        ArrayList<TuVung> vocabList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SAVED_VOCAB;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TuVung vocab = new TuVung(
                        cursor.getString(cursor.getColumnIndex(COLUMN_ENGLISH)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PHONETIC)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_VIETNAMESE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOTE))
                );
                vocabList.add(vocab);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return vocabList;
    }
}
