package com.example.doanapphoctienganh.Class;

public class LuyenThi {
    private int imgID;
    private String Name;

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LuyenThi(int imgID, String name) {
        this.imgID = imgID;
        Name = name;
    }

    public LuyenThi() {
    }
}
