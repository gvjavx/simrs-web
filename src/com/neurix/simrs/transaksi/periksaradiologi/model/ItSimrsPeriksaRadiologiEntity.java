package com.neurix.simrs.transaksi.periksaradiologi.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItSimrsPeriksaRadiologiEntity implements Serializable{

    private String idPeriksaRadiologi;
    private String idDokterRadiologi;
    private String pemeriksaan;
    private String kesimpulan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdPeriksaRadiologi() {
        return idPeriksaRadiologi;
    }

    public void setIdPeriksaRadiologi(String idPeriksaRadiologi) {
        this.idPeriksaRadiologi = idPeriksaRadiologi;
    }

    public String getIdDokterRadiologi() {
        return idDokterRadiologi;
    }

    public void setIdDokterRadiologi(String idDokterRadiologi) {
        this.idDokterRadiologi = idDokterRadiologi;
    }

    public String getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(String pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }

    public String getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
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