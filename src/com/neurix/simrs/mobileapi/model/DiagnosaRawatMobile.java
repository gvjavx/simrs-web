package com.neurix.simrs.mobileapi.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author gondok
 * Thursday, 13/02/20 12:40
 */
public class DiagnosaRawatMobile implements Serializable {

    private String idDiagnosaRawat;
    private String idDiagnosa;
    private String idDetailCheckup;
    private String keteranganDiagnosa;
    private String jenisDiagnosa;
    private String tipe;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
