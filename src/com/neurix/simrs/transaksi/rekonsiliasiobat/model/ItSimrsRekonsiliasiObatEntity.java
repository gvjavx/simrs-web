package com.neurix.simrs.transaksi.rekonsiliasiobat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 08/02/20.
 */
public class ItSimrsRekonsiliasiObatEntity implements Serializable {
    private String id;
    private String noCheckup;
    private String namaObat;
    private String bentuk;
    private String dosis;
    private String satuanDosis;
    private String frekuensi;
    private String rute;
    private String obatMasukFlag;
    private String obatDariRumahFlag;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getSatuanDosis() {
        return satuanDosis;
    }

    public void setSatuanDosis(String satuanDosis) {
        this.satuanDosis = satuanDosis;
    }

    public String getFrekuensi() {
        return frekuensi;
    }

    public void setFrekuensi(String frekuensi) {
        this.frekuensi = frekuensi;
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getObatMasukFlag() {
        return obatMasukFlag;
    }

    public void setObatMasukFlag(String obatMasukFlag) {
        this.obatMasukFlag = obatMasukFlag;
    }

    public String getObatDariRumahFlag() {
        return obatDariRumahFlag;
    }

    public void setObatDariRumahFlag(String obatDariRumahFlag) {
        this.obatDariRumahFlag = obatDariRumahFlag;
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
