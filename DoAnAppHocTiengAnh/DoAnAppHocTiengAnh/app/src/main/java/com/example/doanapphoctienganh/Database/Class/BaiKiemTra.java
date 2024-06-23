package com.example.doanapphoctienganh.Database.Class;

public class BaiKiemTra {
    private int IDBaiKiemTra;
    private String TenBaiKiemTra;
    private String trangThai;

    public BaiKiemTra(){

    }
    public BaiKiemTra(int IDBaiKiemTra, String TenBaiKiemTra, String trangThai) {
        this.IDBaiKiemTra = IDBaiKiemTra;
        this.TenBaiKiemTra = TenBaiKiemTra;
        this.trangThai = trangThai;
    }

    public int getIDBaiKiemTra() {
        return IDBaiKiemTra;
    }

    public void setIDBaiKiemTra(int IDBaiKiemTra) {
        this.IDBaiKiemTra = IDBaiKiemTra;
    }

    public String getTenBaiKiemTra() {
        return TenBaiKiemTra;
    }

    public void setTenBaiKiemTra(String TenBaiKiemTra) {
        this.TenBaiKiemTra = TenBaiKiemTra;
    }

    public String gettrangThai() {
        return trangThai;
    }

    public void settrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
