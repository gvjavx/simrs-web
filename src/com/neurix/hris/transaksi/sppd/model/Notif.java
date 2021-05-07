package com.neurix.hris.transaksi.sppd.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Notif extends BaseModel {
   private String notifId;
   private String tipeNotifId;
   private String nip;
   private String tipeNotifNama;
   private String note;
   private String raed;
   private String fromPerson;
   private String noRequest;

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
}