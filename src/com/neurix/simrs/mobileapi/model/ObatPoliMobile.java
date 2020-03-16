package com.neurix.simrs.mobileapi.model;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author gondok
 * Tuesday, 10/03/20 8:00
 */
public class ObatPoliMobile {
    private String idObat;
    private String idPelayanan;
    private String qty;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    private String branchId;

    private String namaPelayanan;

    private String qtyBox;
    private String qtyLembar;
    private String qtyBiji;
    private String lembarPerBox;
    private String bijiPerLembar;
    private String idBarang;
    private String exp;
    private String idPabrik;
    private String expiredDate;
    private String flagKronis;

    private String namaObat;

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getQtyBox() {
        return qtyBox;
    }

    public void setQtyBox(String qtyBox) {
        this.qtyBox = qtyBox;
    }

    public String getQtyLembar() {
        return qtyLembar;
    }

    public void setQtyLembar(String qtyLembar) {
        this.qtyLembar = qtyLembar;
    }

    public String getQtyBiji() {
        return qtyBiji;
    }

    public void setQtyBiji(String qtyBiji) {
        this.qtyBiji = qtyBiji;
    }

    public String getLembarPerBox() {
        return lembarPerBox;
    }

    public void setLembarPerBox(String lembarPerBox) {
        this.lembarPerBox = lembarPerBox;
    }

    public String getBijiPerLembar() {
        return bijiPerLembar;
    }

    public void setBijiPerLembar(String bijiPerLembar) {
        this.bijiPerLembar = bijiPerLembar;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getFlagKronis() {
        return flagKronis;
    }

    public void setFlagKronis(String flagKronis) {
        this.flagKronis = flagKronis;
    }
}
