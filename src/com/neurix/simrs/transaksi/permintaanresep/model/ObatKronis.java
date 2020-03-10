package com.neurix.simrs.transaksi.permintaanresep.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by reza on 10/03/20.
 */
public class ObatKronis {
    private String idPasien;
    private String idDetailCheckup;
    private String idApprovalObat;
    private Integer intervalHariKronis;
    private String flagKronisDiambil;
    private String msg;
    private Timestamp createdDate;
    private String flagPengambilan;
    private Date tglPengambilan;

    public Date getTglPengambilan() {
        return tglPengambilan;
    }

    public void setTglPengambilan(Date tglPengambilan) {
        this.tglPengambilan = tglPengambilan;
    }

    public String getFlagPengambilan() {
        return flagPengambilan;
    }

    public void setFlagPengambilan(String flagPengambilan) {
        this.flagPengambilan = flagPengambilan;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

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

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public Integer getIntervalHariKronis() {
        return intervalHariKronis;
    }

    public void setIntervalHariKronis(Integer intervalHariKronis) {
        this.intervalHariKronis = intervalHariKronis;
    }

    public String getFlagKronisDiambil() {
        return flagKronisDiambil;
    }

    public void setFlagKronisDiambil(String flagKronisDiambil) {
        this.flagKronisDiambil = flagKronisDiambil;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
