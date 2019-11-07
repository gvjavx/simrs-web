package com.neurix.simrs.master.tarifdokter.model;

import java.sql.Timestamp;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class ImSimrsTarifDokterEntity {
    private String idTarifDokter;
    private String idDokter;
    private String idJenisPasien;
    private Long tarifKonsul;
    private Long tarifVisite;
    private Long tarifTindakan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

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

    public String getIdTarifDokter() {
        return idTarifDokter;
    }

    public void setIdTarifDokter(String idTarifDokter) {
        this.idTarifDokter = idTarifDokter;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdJenisPasien() {
        return idJenisPasien;
    }

    public void setIdJenisPasien(String idJenisPasien) {
        this.idJenisPasien = idJenisPasien;
    }

    public Long getTarifKonsul() {
        return tarifKonsul;
    }

    public void setTarifKonsul(Long tarifKonsul) {
        this.tarifKonsul = tarifKonsul;
    }

    public Long getTarifVisite() {
        return tarifVisite;
    }

    public void setTarifVisite(Long tarifVisite) {
        this.tarifVisite = tarifVisite;
    }

    public Long getTarifTindakan() {
        return tarifTindakan;
    }

    public void setTarifTindakan(Long tarifTindakan) {
        this.tarifTindakan = tarifTindakan;
    }

    @Override
    public String toString() {
        return "ImSimrsTarifDokterEntity{" +
                "idTarifDokter='" + idTarifDokter + '\'' +
                ", idDokter='" + idDokter + '\'' +
                ", idJenisPasien='" + idJenisPasien + '\'' +
                ", tarifKonsul=" + tarifKonsul +
                ", tarifVisite=" + tarifVisite +
                ", tarifTindakan=" + tarifTindakan +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                '}';
    }
}
