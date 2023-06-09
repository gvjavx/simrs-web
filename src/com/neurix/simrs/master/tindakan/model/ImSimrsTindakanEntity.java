package com.neurix.simrs.master.tindakan.model;

import com.neurix.simrs.master.diagnosa.dao.DiagnosaDao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class ImSimrsTindakanEntity implements Serializable{
    private String idTindakan;
    private String tindakan;
    private String idKategoriTindakan;
    private BigInteger tarif;
    private BigInteger tarifBpjs;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String kategoriInaBpjs;

    private String branchId;
    private BigDecimal diskon;
    private String isIna;
    private String idHeaderTindakan;
    private String idPelayanan;
    private String isElektif;
    private String idKelasRuangan;
    private String flagIdKelasRuangan;

    public String getIdKelasRuangan() {
        return idKelasRuangan;
    }

    public void setIdKelasRuangan(String idKelasRuangan) {
        this.idKelasRuangan = idKelasRuangan;
    }

    public String getFlagIdKelasRuangan() {
        return flagIdKelasRuangan;
    }

    public void setFlagIdKelasRuangan(String flagIdKelasRuangan) {
        this.flagIdKelasRuangan = flagIdKelasRuangan;
    }

    public String getIsElektif() {
        return isElektif;
    }

    public void setIsElektif(String isElektif) {
        this.isElektif = isElektif;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdHeaderTindakan() {
        return idHeaderTindakan;
    }

    public void setIdHeaderTindakan(String idHeaderTindakan) {
        this.idHeaderTindakan = idHeaderTindakan;
    }

    public String getIsIna() {
        return isIna;
    }

    public void setIsIna(String isIna) {
        this.isIna = isIna;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getDiskon() {
        return diskon;
    }

    public void setDiskon(BigDecimal diskon) {
        this.diskon = diskon;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getIdKategoriTindakan() {
        return idKategoriTindakan;
    }

    public void setIdKategoriTindakan(String idKategoriTindakan) {
        this.idKategoriTindakan = idKategoriTindakan;
    }

    public BigInteger getTarif() {
        return tarif;
    }

    public void setTarif(BigInteger tarif) {
        this.tarif = tarif;
    }

    public BigInteger getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(BigInteger tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
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

    public String getKategoriInaBpjs() {
        return kategoriInaBpjs;
    }

    public void setKategoriInaBpjs(String kategoriInaBpjs) {
        this.kategoriInaBpjs = kategoriInaBpjs;
    }

    @Override
    public String toString() {
        return "ImSimrsTindakanEntity{" +
                "idTindakan='" + idTindakan + '\'' +
                ", tindakan='" + tindakan + '\'' +
                ", idKategoriTindakan='" + idKategoriTindakan + '\'' +
                ", tarif=" + tarif +
                ", tarifBpjs=" + tarifBpjs +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", kategoriInaBpjs='" + kategoriInaBpjs + '\'' +
                '}';
    }
}
