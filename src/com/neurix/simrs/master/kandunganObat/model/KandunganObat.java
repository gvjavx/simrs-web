package com.neurix.simrs.master.kandunganObat.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 08/07/20.
 */
public class KandunganObat {
    private String id;
    private String idObat;
    private String idKandungan;
    private String bentuk;
    private BigDecimal sediaan;
    private String satuanSediaan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String kandungan;
    private String namaObat;
    private String namaBentuk;

    private String isKp;

    private String branchId;

    public String getNamaBentuk() {
        return namaBentuk;
    }

    public void setNamaBentuk(String namaBentuk) {
        this.namaBentuk = namaBentuk;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }

    public BigDecimal getSediaan() {
        return sediaan;
    }

    public void setSediaan(BigDecimal sediaan) {
        this.sediaan = sediaan;
    }

    public String getSatuanSediaan() {
        return satuanSediaan;
    }

    public void setSatuanSediaan(String satuanSediaan) {
        this.satuanSediaan = satuanSediaan;
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

    public String getIsKp() {
        return isKp;
    }

    public void setIsKp(String isKp) {
        this.isKp = isKp;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
