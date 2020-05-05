package com.neurix.hris.transaksi.pendapatanDokter.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ImHrisPajakEntity {

    String idPajak;
    BigDecimal batasBawah;
    BigDecimal batasAtas;
    BigDecimal pajak;

    String flag;
    String action;
    String createdDate;
    String createdWho;
    Timestamp lastUpdate;
    Timestamp lastUpdateWho;

    public BigDecimal getBatasAtas() {
        return batasAtas;
    }

    public void setBatasAtas(BigDecimal batasAtas) {
        this.batasAtas = batasAtas;
    }

    public BigDecimal getBatasBawah() {
        return batasBawah;
    }

    public void setBatasBawah(BigDecimal batasBawah) {
        this.batasBawah = batasBawah;
    }

    public String getIdPajak() {
        return idPajak;
    }

    public void setIdPajak(String idPajak) {
        this.idPajak = idPajak;
    }

    public BigDecimal getPajak() {
        return pajak;
    }

    public void setPajak(BigDecimal pajak) {
        this.pajak = pajak;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Timestamp getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(Timestamp lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}