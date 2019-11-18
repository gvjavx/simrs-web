package com.neurix.simrs.transaksi.diagnosarawat.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItSimrsDiagnosaRawatEntity implements Serializable {

    private String idDiagnosaRawat;
    private String idDiagnosa;
    private String idDetailCheckup;
    private String keteranganDiagnosa;
    private String jenisDiagnosa;
    private String tipe;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDiagnosaRawat() {
        return idDiagnosaRawat;
    }

    public void setIdDiagnosaRawat(String idDiagnosaRawat) {
        this.idDiagnosaRawat = idDiagnosaRawat;
    }

    public String getIdDiagnosa() {
        return idDiagnosa;
    }

    public void setIdDiagnosa(String idDiagnosa) {
        this.idDiagnosa = idDiagnosa;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getKeteranganDiagnosa() {
        return keteranganDiagnosa;
    }

    public void setKeteranganDiagnosa(String keteranganDiagnosa) {
        this.keteranganDiagnosa = keteranganDiagnosa;
    }

    public String getJenisDiagnosa() {
        return jenisDiagnosa;
    }

    public void setJenisDiagnosa(String jenisDiagnosa) {
        this.jenisDiagnosa = jenisDiagnosa;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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