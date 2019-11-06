package com.neurix.hris.master.payrollMasaKerjaPensiun.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPayrollMasaKerjaPensiunEntity implements Serializable {

    private String masaKerjaPensiunId;
    private int tahunDari;
    private int tahunSampai;
    private int faktorPensiun;
    private int faktorPenghargaan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getMasaKerjaPensiunId() {
        return masaKerjaPensiunId;
    }

    public void setMasaKerjaPensiunId(String masaKerjaPensiunId) {
        this.masaKerjaPensiunId = masaKerjaPensiunId;
    }

    public int getTahunDari() {
        return tahunDari;
    }

    public void setTahunDari(int tahunDari) {
        this.tahunDari = tahunDari;
    }

    public int getTahunSampai() {
        return tahunSampai;
    }

    public void setTahunSampai(int tahunSampai) {
        this.tahunSampai = tahunSampai;
    }

    public int getFaktorPenghargaan() {
        return faktorPenghargaan;
    }

    public void setFaktorPenghargaan(int faktorPenghargaan) {
        this.faktorPenghargaan = faktorPenghargaan;
    }

    public int getFaktorPensiun() {
        return faktorPensiun;
    }

    public void setFaktorPensiun(int faktorPensiun) {
        this.faktorPensiun = faktorPensiun;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
