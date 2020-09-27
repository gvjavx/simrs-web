package com.neurix.simrs.transaksi.checkup.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class PelayananPaket {
    private String idPelayananPaket;
    private String idPaket;
    private String noCheckup;
    private String idDetailCheckup;
    private String idPelayanan;
    private BigInteger urutan;
    private String isPeriksa;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idDokter;
    private String namaPelayanan;

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getIdPelayananPaket() {
        return idPelayananPaket;
    }

    public void setIdPelayananPaket(String idPelayananPaket) {
        this.idPelayananPaket = idPelayananPaket;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public BigInteger getUrutan() {
        return urutan;
    }

    public void setUrutan(BigInteger urutan) {
        this.urutan = urutan;
    }

    public String getIsPeriksa() {
        return isPeriksa;
    }

    public void setIsPeriksa(String isPeriksa) {
        this.isPeriksa = isPeriksa;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PelayananPaket that = (PelayananPaket) o;
        return Objects.equals(idPelayananPaket, that.idPelayananPaket) &&
                Objects.equals(noCheckup, that.noCheckup) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(idPelayanan, that.idPelayanan) &&
                Objects.equals(urutan, that.urutan) &&
                Objects.equals(isPeriksa, that.isPeriksa) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPelayananPaket, noCheckup, idDetailCheckup, idPelayanan, urutan, isPeriksa, flag, action, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
