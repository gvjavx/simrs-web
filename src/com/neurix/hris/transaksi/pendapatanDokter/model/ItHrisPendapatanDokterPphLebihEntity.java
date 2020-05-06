package com.neurix.hris.transaksi.pendapatanDokter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ItHrisPendapatanDokterPphLebihEntity implements Serializable {
    String pendapatanDokterPphLebihId;
    String pendapatanDokterId;
    BigDecimal dppPph50;
    BigDecimal dppPph21Komulatif;
    BigDecimal dppPph21;
    BigDecimal tarif;
    BigDecimal pphDipungut;
    String flag;
    String action;
    Timestamp createdDate;
    Timestamp lastUpdate;
    String createdWho;
    String lastUpdateWho;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getPendapatanDokterId() {
        return pendapatanDokterId;
    }

    public void setPendapatanDokterId(String pendapatanDokterId) {
        this.pendapatanDokterId = pendapatanDokterId;
    }

    public String getPendapatanDokterPphLebihId() {
        return pendapatanDokterPphLebihId;
    }

    public void setPendapatanDokterPphLebihId(String pendapatanDokterPphLebihId) {
        this.pendapatanDokterPphLebihId = pendapatanDokterPphLebihId;
    }

    public BigDecimal getDppPph21() {
        return dppPph21;
    }

    public void setDppPph21(BigDecimal dppPph21) {
        this.dppPph21 = dppPph21;
    }

    public BigDecimal getDppPph21Komulatif() {
        return dppPph21Komulatif;
    }

    public void setDppPph21Komulatif(BigDecimal dppPph21Komulatif) {
        this.dppPph21Komulatif = dppPph21Komulatif;
    }

    public BigDecimal getDppPph50() {
        return dppPph50;
    }

    public void setDppPph50(BigDecimal dppPph50) {
        this.dppPph50 = dppPph50;
    }

    public BigDecimal getPphDipungut() {
        return pphDipungut;
    }

    public void setPphDipungut(BigDecimal pphDipungut) {
        this.pphDipungut = pphDipungut;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
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
}