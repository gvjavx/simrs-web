package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PembayaranUtangPiutangDetail extends BaseModel {
    private String pembayaranUtangPiutangDetailId;
    private String pembayaranUtangPiutangId;
    private String masterId;
    private String noNota;
    private BigDecimal debit;
    private BigDecimal kredit;

    private String stJumlahPembayaran;
    private String masterName;
    private String rekeningId;
    private String kodeRekening;

    public BigDecimal getKredit() {
        return kredit;
    }

    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getStJumlahPembayaran() {
        return stJumlahPembayaran;
    }

    public void setStJumlahPembayaran(String stJumlahPembayaran) {
        this.stJumlahPembayaran = stJumlahPembayaran;
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

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }
}