package com.example.doanapphoctienganh.Database.Class;

public class LoaiLuyen {

    private int idLoaiLuyen;
    private int idBaiHoc;
    private String tenLoaiLuyen;

    public int getIdLoaiLuyen() {
        return idLoaiLuyen;
    }

    public void setIdLoaiLuyen(int idLoaiLuyen) {
        this.idLoaiLuyen = idLoaiLuyen;
    }

    public int getIdBaiHoc() {
        return idBaiHoc;
    }

    public void setIdBaiHoc(int idBaiHoc) {
        this.idBaiHoc = idBaiHoc;
    }

    public String getTenLoaiLuyen() {
        return tenLoaiLuyen;
    }

    public void setTenLoaiLuyen(String tenLoaiLuyen) {
        this.tenLoaiLuyen = tenLoaiLuyen;
    }

    public LoaiLuyen() {
    }

    public LoaiLuyen(int idLoaiLuyen, int idBaiHoc, String tenLoaiLuyen) {
        this.idLoaiLuyen = idLoaiLuyen;
        this.idBaiHoc = idBaiHoc;
        this.tenLoaiLuyen = tenLoaiLuyen;
    }
}
