package com.example.doanapphoctienganh.Database.Class;

import java.io.Serializable;

public class Nghe implements Serializable {
    String fileNghe;
    String chuDeNghe;
    String doanNghe;

    public Nghe(String fileNghe, String chuDeNghe, String doanNghe) {
        this.fileNghe = fileNghe;
        this.chuDeNghe = chuDeNghe;
        this.doanNghe = doanNghe;
    }

    public String getFileNghe() {
        return fileNghe;
    }

    public void setFileNghe(String fileNghe) {
        this.fileNghe = fileNghe;
    }

    public String getChuDeNghe() {
        return chuDeNghe;
    }

    public void setChuDeNghe(String chuDeNghe) {
        this.chuDeNghe = chuDeNghe;
    }

    public String getDoanNghe() {
        return doanNghe;
    }

    public void setDoanNghe(String doanNghe) {
        this.doanNghe = doanNghe;
    }

    public Nghe() {
    }


}
