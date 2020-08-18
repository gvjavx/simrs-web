package com.neurix.hris.master.libur.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImLiburHistoryEntity implements Serializable {
//    private ImApbAlatPK primaryKey;
    private String liburHistoryId;
    private String liburId;
    private String liburTahun;
    private String tipeLiburId;
    private Timestamp tanggal;
    private String liburKeterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getLiburHistoryId() {
        return liburHistoryId;
    }

    public void setLiburHistoryId(String liburHistoryId) {
        this.liburHistoryId = liburHistoryId;
    }

    public String getLiburId() {
        return liburId;
    }

    public void setLiburId(String liburId) {
        this.liburId = liburId;
    }

    public String getLiburTahun() {
        return liburTahun;
    }

    public void setLiburTahun(String liburTahun) {
        this.liburTahun = liburTahun;
    }

    public String getTipeLiburId() {
        return tipeLiburId;
    }

    public void setTipeLiburId(String tipeLiburId) {
        this.tipeLiburId = tipeLiburId;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public String getLiburKeterangan() {
        return liburKeterangan;
    }

    public void setLiburKeterangan(String liburKeterangan) {
        this.liburKeterangan = liburKeterangan;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
