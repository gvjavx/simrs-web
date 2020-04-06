package com.neurix.simrs.transaksi.paketperiksa.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 12/03/20.
 */
public class ImSimrsKelasPaketEntity implements Serializable {

    private String idKelasPaket;
    private String namaKelasPaket;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdKelasPaket() {
        return idKelasPaket;
    }

    public void setIdKelasPaket(String idKelasPaket) {
        this.idKelasPaket = idKelasPaket;
    }

    public String getNamaKelasPaket() {
        return namaKelasPaket;
    }

    public void setNamaKelasPaket(String namaKelasPaket) {
        this.namaKelasPaket = namaKelasPaket;
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
