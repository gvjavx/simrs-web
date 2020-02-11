package com.neurix.simrs.transaksi.permintaanvendor.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 06/01/20.
 */
public class ImSimrsTempObatGejalaEntity implements Serializable {

    private String idTempObatGejala;
    private String idTransObatDetail;
    private String idJenisObat;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdTempObatGejala() {
        return idTempObatGejala;
    }

    public void setIdTempObatGejala(String idTempObatGejala) {
        this.idTempObatGejala = idTempObatGejala;
    }

    public String getIdTransObatDetail() {
        return idTransObatDetail;
    }

    public void setIdTransObatDetail(String idTransObatDetail) {
        this.idTransObatDetail = idTransObatDetail;
    }

    public String getIdJenisObat() {
        return idJenisObat;
    }

    public void setIdJenisObat(String idJenisObat) {
        this.idJenisObat = idJenisObat;
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
