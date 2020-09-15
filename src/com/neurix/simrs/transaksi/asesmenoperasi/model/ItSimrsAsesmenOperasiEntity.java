package com.neurix.simrs.transaksi.asesmenoperasi.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItSimrsAsesmenOperasiEntity implements Serializable {

    private String idAsesmenOperasi;
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
    private String tipe;
    private String namaterang;
    private String sip;

    public String getNamaterang() {
        return namaterang;
    }

    public void setNamaterang(String namaterang) {
        this.namaterang = namaterang;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getIdAsesmenOperasi() {
        return idAsesmenOperasi;
    }

    public void setIdAsesmenOperasi(String idAsesmenOperasi) {
        this.idAsesmenOperasi = idAsesmenOperasi;
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
