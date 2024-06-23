package com.example.doanapphoctienganh.Database.Class;

public class Viet {
    private int idViet;
    private int idBaiHoc;
    private String cauhoi;
    private String dapAn;

    public int getIdBaiHoc() {
        return idBaiHoc;
    }

    public void setIdBaiHoc(int idBaiHoc) {
        this.idBaiHoc = idBaiHoc;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }



    public int getIdViet() {
        return idViet;
    }

    public void setIdViet(int idViet) {
        this.idViet = idViet;
    }


    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public Viet(int idViet, int idBaiHoc, String cauhoi, String dapAn) {
        this.idViet = idViet;
        this.idBaiHoc = idBaiHoc;
        this.cauhoi = cauhoi;
        this.dapAn = dapAn;
    }

    public Viet() {
    }

}
