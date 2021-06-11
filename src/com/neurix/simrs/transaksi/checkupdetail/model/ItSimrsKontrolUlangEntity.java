package com.neurix.simrs.transaksi.checkupdetail.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsKontrolUlangEntity {
    private String idKontrolUlang;
    private String noCheckup;
    private String idDetailCheckup;
    private Date tglKontrol;
    private String idPelayanan;
    private String idDokter;
    private String statusKontrol;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdKontrolUlang() {
        return idKontrolUlang;
    }

    public void setIdKontrolUlang(String idKontrolUlang) {
        this.idKontrolUlang = idKontrolUlang;
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

    public Date getTglKontrol() {
        return tglKontrol;
    }

    public void setTglKontrol(Date tglKontrol) {
        this.tglKontrol = tglKontrol;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getStatusKontrol() {
        return statusKontrol;
    }

    public void setStatusKontrol(String statusKontrol) {
        this.statusKontrol = statusKontrol;
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
