package com.neurix.simrs.transaksi.checkup.model;

import com.neurix.simrs.master.tindakan.model.Tindakan;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Fpk {

    private String idFpk;
    private String noFpk;
    private String noSep;
    private String idDetailCheckup;
    private String statusBayar;
    private Date tanggalFpk;
    private String noSlip;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idPasien;

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdFpk() {
        return idFpk;
    }

    public void setIdFpk(String idFpk) {
        this.idFpk = idFpk;
    }

    public String getNoFpk() {
        return noFpk;
    }

    public void setNoFpk(String noFpk) {
        this.noFpk = noFpk;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public Date getTanggalFpk() {
        return tanggalFpk;
    }

    public void setTanggalFpk(Date tanggalFpk) {
        this.tanggalFpk = tanggalFpk;
    }

    public String getNoSlip() {
        return noSlip;
    }

    public void setNoSlip(String noSlip) {
        this.noSlip = noSlip;
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
