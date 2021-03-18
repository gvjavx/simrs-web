package com.neurix.simrs.transaksi.konsultasigizi.model;

import java.sql.Timestamp;

public class KonsultasiGizi {
    private String idKonsultasiGizi;
    private String noCheckup;
    private String idDetailCheckup;
    private String status;
    private String isRead;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String nama;
    private String idPasien;
    private String namaPelayanan;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getIdKonsultasiGizi() {
        return idKonsultasiGizi;
    }

    public void setIdKonsultasiGizi(String idKonsultasiGizi) {
        this.idKonsultasiGizi = idKonsultasiGizi;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
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
