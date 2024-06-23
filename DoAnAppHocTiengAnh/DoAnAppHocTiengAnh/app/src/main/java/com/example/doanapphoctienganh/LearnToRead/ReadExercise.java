package com.example.doanapphoctienganh.LearnToRead;

public class ReadExercise {
    private int id;
    private String tenBaiDoc;
    private String noiDungBaiDoc;
    private int idBaiHoc;
    private String trangThai;

    public ReadExercise() {
    }

    public ReadExercise(int id, String tenBaiDoc, String noiDungBaiDoc, int idBaiHoc, String trangThai) {
        this.id = id;
        this.tenBaiDoc = tenBaiDoc;
        this.noiDungBaiDoc = noiDungBaiDoc;
        this.idBaiHoc = idBaiHoc;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBaiDoc() {
        return tenBaiDoc;
    }

    public void setTenBaiDoc(String tenBaiDoc) {
        this.tenBaiDoc = tenBaiDoc;
    }

    public String getNoiDungBaiDoc() {
        return noiDungBaiDoc;
    }

    public void setNoiDungBaiDoc(String noiDungBaiDoc) {
        this.noiDungBaiDoc = noiDungBaiDoc;
    }

    public int getIdBaiHoc() {
        return idBaiHoc;
    }

    public void setIdBaiHoc(int idBaiHoc) {
        this.idBaiHoc = idBaiHoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
