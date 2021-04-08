package com.neurix.simrs.transaksi.asesmengizi.model;

import java.sql.Timestamp;

public class AsesmenAsuhanGizi {
    private String idAsuhanGizi;
    private String noCheckup;
    private String idDetailCheckup;
    private String asesmen;
    private String diagnosa;
    private String intervensi;
    private String rencana;
    private String edukasi;
    private String ttdPasien;
    private String ttdDokter;
    private String namaPasien;
    private String namaDokter;
    private String sipDokter;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdAsuhanGizi() {
        return idAsuhanGizi;
    }

    public void setIdAsuhanGizi(String idAsuhanGizi) {
        this.idAsuhanGizi = idAsuhanGizi;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getAsesmen() {
        return asesmen;
    }

    public void setAsesmen(String asesmen) {
        this.asesmen = asesmen;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getIntervensi() {
        return intervensi;
    }

    public void setIntervensi(String intervensi) {
        this.intervensi = intervensi;
    }

    public String getRencana() {
        return rencana;
    }

    public void setRencana(String rencana) {
        this.rencana = rencana;
    }

    public String getEdukasi() {
        return edukasi;
    }

    public void setEdukasi(String edukasi) {
        this.edukasi = edukasi;
    }

    public String getTtdPasien() {
        return ttdPasien;
    }

    public void setTtdPasien(String ttdPasien) {
        this.ttdPasien = ttdPasien;
    }

    public String getTtdDokter() {
        return ttdDokter;
    }

    public void setTtdDokter(String ttdDokter) {
        this.ttdDokter = ttdDokter;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getSipDokter() {
        return sipDokter;
    }

    public void setSipDokter(String sipDokter) {
        this.sipDokter = sipDokter;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
