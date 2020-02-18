package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPembayaranUtangPiutangEntity implements Serializable {

    private String pembayaranUtangPiutangId;
    private String tipeTransaksi;
    private Date tanggal;
    private String kodeRekeningKas;
    private BigDecimal bayar;
    private String keterangan;
    private String noSlipBank;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getPembayaranUtangPiutangId() {
        return pembayaranUtangPiutangId;
    }

    public void setPembayaranUtangPiutangId(String pembayaranUtangPiutangId) {
        this.pembayaranUtangPiutangId = pembayaranUtangPiutangId;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodeRekeningKas() {
        return kodeRekeningKas;
    }

    public void setKodeRekeningKas(String kodeRekeningKas) {
        this.kodeRekeningKas = kodeRekeningKas;
    }

    public BigDecimal getBayar() {
        return bayar;
    }

    public void setBayar(BigDecimal bayar) {
        this.bayar = bayar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNoSlipBank() {
        return noSlipBank;
    }

    public void setNoSlipBank(String noSlipBank) {
        this.noSlipBank = noSlipBank;
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
