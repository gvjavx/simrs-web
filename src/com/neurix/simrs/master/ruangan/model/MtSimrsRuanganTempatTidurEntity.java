package com.neurix.simrs.master.ruangan.model;

import java.sql.Timestamp;
import java.util.Objects;

public class MtSimrsRuanganTempatTidurEntity {
    private String idTempatTidur;
    private String idRuangan;
    private String namaTempatTidur;
    private String status;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;

    public String getIdTempatTidur() {
        return idTempatTidur;
    }

    public void setIdTempatTidur(String idTempatTidur) {
        this.idTempatTidur = idTempatTidur;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaTempatTidur() {
        return namaTempatTidur;
    }

    public void setNamaTempatTidur(String namaTempatTidur) {
        this.namaTempatTidur = namaTempatTidur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
