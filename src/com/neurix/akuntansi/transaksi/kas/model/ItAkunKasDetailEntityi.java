package com.neurix.akuntansi.transaksi.kas.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Aji Noor on 03/03/2021
 */
public class ItAkunKasDetailEntityi {
    private String kasDetailId;
    private String kasId;
    private String masterId;
    private String noNota;
    private BigDecimal jumlahPembayaran;
    private String flag;
    private String action;
    private String kodeCoa;
    private String divisiId;
    private String noFakturPajak;
    private String urlFakturImage;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public String getKasDetailId() {
        return kasDetailId;
    }

    public void setKasDetailId(String kasDetailId) {
        this.kasDetailId = kasDetailId;
    }

    public String getKasId() {
        return kasId;
    }

    public void setKasId(String kasId) {
        this.kasId = kasId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public BigDecimal getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(BigDecimal jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
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

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getNoFakturPajak() {
        return noFakturPajak;
    }

    public void setNoFakturPajak(String noFakturPajak) {
        this.noFakturPajak = noFakturPajak;
    }

    public String getUrlFakturImage() {
        return urlFakturImage;
    }

    public void setUrlFakturImage(String urlFakturImage) {
        this.urlFakturImage = urlFakturImage;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunKasDetailEntityi that = (ItAkunKasDetailEntityi) o;
        return Objects.equals(kasDetailId, that.kasDetailId) &&
                Objects.equals(kasId, that.kasId) &&
                Objects.equals(masterId, that.masterId) &&
                Objects.equals(noNota, that.noNota) &&
                Objects.equals(jumlahPembayaran, that.jumlahPembayaran) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(kodeCoa, that.kodeCoa) &&
                Objects.equals(divisiId, that.divisiId) &&
                Objects.equals(noFakturPajak, that.noFakturPajak) &&
                Objects.equals(urlFakturImage, that.urlFakturImage) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kasDetailId, kasId, masterId, noNota, jumlahPembayaran, flag, action, kodeCoa, divisiId, noFakturPajak, urlFakturImage, createdWho, lastUpdateWho, createdDate, lastUpdate);
    }
}
