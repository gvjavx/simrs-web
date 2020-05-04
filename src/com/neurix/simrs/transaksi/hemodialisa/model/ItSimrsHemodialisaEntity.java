package com.neurix.simrs.transaksi.hemodialisa.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItSimrsHemodialisaEntity implements Serializable {

    private String idHemodialisa;
    private String idDetailCheckup;
    private String parameter;
    private String jawaban1;
    private String jawaban2;
    private String keterangan;
    private String jenis;
    private Integer skor;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdHemodialisa() {
        return idHemodialisa;
    }

    public void setIdHemodialisa(String idHemodialisa) {
        this.idHemodialisa = idHemodialisa;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getJawaban1() {
        return jawaban1;
    }

    public void setJawaban1(String jawaban1) {
        this.jawaban1 = jawaban1;
    }

    public String getJawaban2() {
        return jawaban2;
    }

    public void setJawaban2(String jawaban2) {
        this.jawaban2 = jawaban2;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Integer getSkor() {
        return skor;
    }

    public void setSkor(Integer skor) {
        this.skor = skor;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
