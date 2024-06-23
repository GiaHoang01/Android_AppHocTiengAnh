package com.example.doanapphoctienganh.Database.Class;

public class NguPhap {

    int sttNguPhap;
    String tenNguPhap;
    String congThuc;
    String viDu;
    String moTa;


    public NguPhap() {
    }

    public NguPhap(int sttNguPhap, String tenNguPhap, String congThuc, String viDu, String moTa) {
        this.sttNguPhap = sttNguPhap;
        this.tenNguPhap = tenNguPhap;
        this.congThuc = congThuc;
        this.viDu = viDu;
        this.moTa = moTa;
    }

    public int getSttNguPhap() {
        return sttNguPhap;
    }

    public void setSttNguPhap(int sttNguPhap) {
        this.sttNguPhap = sttNguPhap;
    }

    public String getTenNguPhap() {
        return tenNguPhap;
    }

    public void setTenNguPhap(String tenNguPhap) {
        this.tenNguPhap = tenNguPhap;
    }

    public String getCongThuc() {
        return congThuc;
    }

    public void setCongThuc(String congThuc) {
        this.congThuc = congThuc;
    }

    public String getViDu() {
        return viDu;
    }

    public void setViDu(String viDu) {
        this.viDu = viDu;
    }

    public String getMoTa() { return moTa; }

    public void setMoTa(String moTa) { this.moTa = moTa; }
}
