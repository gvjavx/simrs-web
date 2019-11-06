package com.neurix.hris.transaksi.sppd.model;

import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItNotifEntity implements Serializable {

    private String notifId;
    private String tipeNotifId;
    private String nip;
    private String tipeNotifNama;
    private String note;
    private String raed;
    private String fromPerson;
    private String noRequest;


    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getNotifId() {
        return notifId;
    }

    public void setNotifId(String notifId) {
        this.notifId = notifId;
    }

    public String getTipeNotifId() {
        return tipeNotifId;
    }

    public void setTipeNotifId(String tipeNotifId) {
        this.tipeNotifId = tipeNotifId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTipeNotifNama() {
        return tipeNotifNama;
    }

    public void setTipeNotifNama(String tipeNotifNama) {
        this.tipeNotifNama = tipeNotifNama;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRaed() {
        return raed;
    }

    public void setRaed(String raed) {
        this.raed = raed;
    }

    public String getFromPerson() {
        return fromPerson;
    }

    public void setFromPerson(String fromPerson) {
        this.fromPerson = fromPerson;
    }

    public String getNoRequest() {
        return noRequest;
    }

    public void setNoRequest(String noRequest) {
        this.noRequest = noRequest;
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
