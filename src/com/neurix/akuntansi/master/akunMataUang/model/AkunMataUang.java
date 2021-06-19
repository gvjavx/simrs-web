package com.neurix.akuntansi.master.akunMataUang.model;

import com.neurix.common.model.BaseModel;


/**
 * Created by Aji Noor on 05/02/2021.
 */

public class AkunMataUang extends BaseModel {
    private String mataUangId;
    private String kodeMataUang;
    private String namaMataUang;
    private String userName;
    private String workStation;
    private String cabangId;


    public String getMataUangId() {
        return mataUangId;
    }

    public void setMataUangId(String mataUangId) {
        this.mataUangId = mataUangId;
    }

    public String getKodeMataUang() {
        return kodeMataUang;
    }

    public void setKodeMataUang(String kodeMataUang) {
        this.kodeMataUang = kodeMataUang;
    }

    public String getNamaMataUang() {
        return namaMataUang;
    }

    public void setNamaMataUang(String namaMataUang) {
        this.namaMataUang = namaMataUang;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

    public String getCabangId() {
        return cabangId;
    }

    public void setCabangId(String cabangId) {
        this.cabangId = cabangId;
    }

}