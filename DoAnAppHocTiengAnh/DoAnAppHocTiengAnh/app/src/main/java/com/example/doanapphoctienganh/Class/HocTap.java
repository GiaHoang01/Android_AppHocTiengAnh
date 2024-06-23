package com.example.doanapphoctienganh.Class;

public class HocTap {
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

    public HocTap(int imgID, String name) {
        this.imgID = imgID;
        Name = name;
    }

    public HocTap() {
    }
}
