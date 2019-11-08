package com.neurix.simrs.transaksi.periksaradiologi.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Timestamp;

public class PeriksaRadiologi extends BaseModel implements Serializable {

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

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}