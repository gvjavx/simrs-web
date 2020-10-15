package com.neurix.simrs.transaksi.paketperiksa.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class MtSimrsDetailPaketEntity {
    private String idDetailPaket;
    private String idPaket;
    private String idPelayanan;
    private BigInteger urutan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDetailPaket() {
        return idDetailPaket;
    }

    public void setIdDetailPaket(String idDetailPaket) {
        this.idDetailPaket = idDetailPaket;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
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
        MtSimrsDetailPaketEntity that = (MtSimrsDetailPaketEntity) o;
        return Objects.equals(idDetailPaket, that.idDetailPaket) &&
                Objects.equals(idPaket, that.idPaket) &&
                Objects.equals(idPelayanan, that.idPelayanan) &&
                Objects.equals(urutan, that.urutan) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetailPaket, idPaket, idPelayanan, urutan, flag, action, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
