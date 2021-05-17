package com.neurix.akuntansi.transaksi.kas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/*
 *
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */


public class ItAkunKasDetailEntity implements Serializable {

//    private String posisiCoa;

    private String kasDetailId;
    private String kasId;
    private String masterId;
    private String noNota;
    private BigDecimal jumlahPembayaran;
    private String kodeCoa;
    private String divisiId;
    private String noFakturPajak;
    private String urlFakturImage;
    private BigDecimal pph;
    private BigDecimal ppn;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

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

    public String getKasId() {
        return kasId;
    }

    public void setKasId(String kasId) {
        this.kasId = kasId;
    }

    public BigDecimal getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(BigDecimal jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
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

    public String getKasDetailId() {
        return kasDetailId;
    }

    public void setKasDetailId(String kasDetailId) {
        this.kasDetailId = kasDetailId;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public BigDecimal getPph() {
        return pph;
    }

    public void setPph(BigDecimal pph) {
        this.pph = pph;
    }

    public BigDecimal getPpn() {
        return ppn;
    }

    public void setPpn(BigDecimal ppn) {
        this.ppn = ppn;
    }
}
