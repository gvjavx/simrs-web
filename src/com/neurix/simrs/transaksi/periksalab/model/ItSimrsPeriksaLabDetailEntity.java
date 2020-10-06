package com.neurix.simrs.transaksi.periksalab.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ItSimrsPeriksaLabDetailEntity implements Serializable{

    private String idPeriksaLabDetail;
    private String idPeriksaLab;
    private String idLabDetail;
    private String namaDetailPeriksa;
    private String satuan;
    private String keteranganAcuanL;
    private String keteranganAcuanP;
    private String hasil;
    private String keteranganPeriksa;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private BigDecimal tarif;

    public String getKeteranganAcuanL() {
        return keteranganAcuanL;
    }

    public void setKeteranganAcuanL(String keteranganAcuanL) {
        this.keteranganAcuanL = keteranganAcuanL;
    }

    public String getKeteranganAcuanP() {
        return keteranganAcuanP;
    }

    public void setKeteranganAcuanP(String keteranganAcuanP) {
        this.keteranganAcuanP = keteranganAcuanP;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public String getIdPeriksaLabDetail() {
        return idPeriksaLabDetail;
    }

    public void setIdPeriksaLabDetail(String idPeriksaLabDetail) {
        this.idPeriksaLabDetail = idPeriksaLabDetail;
    }

    public String getIdPeriksaLab() {
        return idPeriksaLab;
    }

    public void setIdPeriksaLab(String idPeriksaLab) {
        this.idPeriksaLab = idPeriksaLab;
    }

    public String getIdLabDetail() {
        return idLabDetail;
    }

    public void setIdLabDetail(String idLabDetail) {
        this.idLabDetail = idLabDetail;
    }

    public String getNamaDetailPeriksa() {
        return namaDetailPeriksa;
    }

    public void setNamaDetailPeriksa(String namaDetailPeriksa) {
        this.namaDetailPeriksa = namaDetailPeriksa;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getKeteranganPeriksa() {
        return keteranganPeriksa;
    }

    public void setKeteranganPeriksa(String keteranganPeriksa) {
        this.keteranganPeriksa = keteranganPeriksa;
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