package com.example.doanapphoctienganh.Database.Class;

public class BaiHoc {
    private int idBaiHoc;
    private String tenBaiHoc;
    private String trangThai;
    private String tenChuong;

    public int getIdBaiHoc() {
        return idBaiHoc;
    }

    public void setIdBaiHoc(int idBaiHoc) {
        this.idBaiHoc = idBaiHoc;
    }

    public String getTenBaiHoc() {
        return tenBaiHoc;
    }

    public void setTenBaiHoc(String tenBaiHoc) {
        this.tenBaiHoc = tenBaiHoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public BaiHoc() {
    }

    public BaiHoc(int idBaiHoc, String tenBaiHoc, String trangThai, String tenChuong) {
        this.idBaiHoc = idBaiHoc;
        this.tenBaiHoc = tenBaiHoc;
        this.trangThai = trangThai;
        this.tenChuong = tenChuong;
    }
}
