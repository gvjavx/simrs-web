package com.neurix.hris.master.biodata.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItTunjLainPegawaiEntity implements Serializable {
    private String tunjLainId;
    private String nip;
    private String flagTunjSupervisi;
    private String flagTunjLokasi;
    private String flagTunjSiaga;
//    private String flagTunjProfesional;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagTunjLokasi() {
        return flagTunjLokasi;
    }

    public void setFlagTunjLokasi(String flagTunjLokasi) {
        this.flagTunjLokasi = flagTunjLokasi;
    }

    public String getFlagTunjSiaga() {
        return flagTunjSiaga;
    }

    public void setFlagTunjSiaga(String flagTunjSiaga) {
        this.flagTunjSiaga = flagTunjSiaga;
    }

    public String getFlagTunjSupervisi() {
        return flagTunjSupervisi;
    }

    public void setFlagTunjSupervisi(String flagTunjSupervisi) {
        this.flagTunjSupervisi = flagTunjSupervisi;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTunjLainId() {
        return tunjLainId;
    }

    public void setTunjLainId(String tunjLainId) {
        this.tunjLainId = tunjLainId;
    }
}