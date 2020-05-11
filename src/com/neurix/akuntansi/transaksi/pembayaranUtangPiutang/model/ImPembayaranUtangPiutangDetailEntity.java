package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPembayaranUtangPiutangDetailEntity implements Serializable {

    private String pembayaranUtangPiutangDetailId;
    private String pembayaranUtangPiutangId;
    private String masterId;
    private String noNota;
    private BigDecimal jumlahPembayaran;
    private String rekeningId;
    private String divisiId;
    private String posisiCoa;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getPosisiCoa() {
        return posisiCoa;
    }

    public void setPosisiCoa(String posisiCoa) {
        this.posisiCoa = posisiCoa;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public BigDecimal getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(BigDecimal jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getPembayaranUtangPiutangDetailId() {
        return pembayaranUtangPiutangDetailId;
    }

    public void setPembayaranUtangPiutangDetailId(String pembayaranUtangPiutangDetailId) {
        this.pembayaranUtangPiutangDetailId = pembayaranUtangPiutangDetailId;
    }

    public String getPembayaranUtangPiutangId() {
        return pembayaranUtangPiutangId;
    }

    public void setPembayaranUtangPiutangId(String pembayaranUtangPiutangId) {
        this.pembayaranUtangPiutangId = pembayaranUtangPiutangId;
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
}
