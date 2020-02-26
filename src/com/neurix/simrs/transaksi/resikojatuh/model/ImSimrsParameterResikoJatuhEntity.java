package com.neurix.simrs.transaksi.resikojatuh.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 05/02/20.
 */
public class ImSimrsParameterResikoJatuhEntity implements Serializable {
    private String idParameter;
    private String namaParameter;
    private String idKategori;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    public String getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(String idParameter) {
        this.idParameter = idParameter;
    }

    public String getNamaParameter() {
        return namaParameter;
    }

    public void setNamaParameter(String namaParameter) {
        this.namaParameter = namaParameter;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
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
}
