package com.example.doanapphoctienganh.Database.Class;

public class TuVung {
    private int idTuVung;
    private int idBaiHoc;
    private String tiengAnh;
    private String tiengViet;
    private String phienAm;
    private String loaiTu;
    private String dangChuY;
    //Ms thêm
    private boolean isFavorite;
    private boolean correct;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public TuVung(String tiengAnh, String tiengViet, String phienAm, String loaiTu, String dangChuY) {
        this.tiengAnh = tiengAnh;
        this.tiengViet = tiengViet;
        this.phienAm = phienAm;
        this.loaiTu = loaiTu;
        this.dangChuY = dangChuY;
    }

    public TuVung(int idTuVung, String tiengAnh, String tiengViet) {
        this.idTuVung = idTuVung;
        this.tiengAnh = tiengAnh;
        this.tiengViet = tiengViet;
        this.correct = false;
        this.isSelected=false;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public TuVung(String tiengAnh, String tiengViet) {
        this.tiengAnh = tiengAnh;
        this.tiengViet = tiengViet;
    }

    public int getIdTuVung() {
        return idTuVung;
    }

    public void setIdTuVung(int idTuVung) {
        this.idTuVung = idTuVung;
    }

    public int getIdBaiHoc() {
        return idBaiHoc;
    }

    public void setIdBaiHoc(int idBaiHoc) {
        this.idBaiHoc = idBaiHoc;
    }

    public String getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(String tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public String getTiengViet() {
        return tiengViet;
    }

    public void setTiengViet(String tiengViet) {
        this.tiengViet = tiengViet;
    }

    public String getPhienAm() {
        return phienAm;
    }

    public void setPhienAm(String phienAm) {
        this.phienAm = phienAm;
    }

    public String getLoaiTu() {
        return loaiTu;
    }

    public void setLoaiTu(String loaiTu) {
        this.loaiTu = loaiTu;
    }

    public String getDangChuY() {
        return dangChuY;
    }

    public void setDangChuY(String dangChuY) {
        this.dangChuY = dangChuY;
    }

    public TuVung() {
    }

    public TuVung(int idTuVung, int idBaiHoc, String tiengAnh, String tiengViet, String phienAm, String loaiTu, String dangChuY) {
        this.idTuVung = idTuVung;
        this.idBaiHoc = idBaiHoc;
        this.tiengAnh = tiengAnh;
        this.tiengViet = tiengViet;
        this.phienAm = phienAm;
        this.loaiTu = loaiTu;
        this.dangChuY = dangChuY;
    }
}
