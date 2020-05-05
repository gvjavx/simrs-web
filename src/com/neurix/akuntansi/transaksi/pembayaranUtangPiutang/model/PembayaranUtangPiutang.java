package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PembayaranUtangPiutang extends BaseModel {
    private String pembayaranUtangPiutangId;
    private String tipeTransaksi;
    private Date tanggal;
    private BigDecimal bayar;
    private String keterangan;
    private String noSlipBank;
    private String branchId;
    private String noJurnal;
    private String registeredFlag;
    private Date registeredDate;
    private String registeredWho;
    private String tipePembayaran;
    private String metodePembayaranName;

    private String stBayar;
    private String stTanggal;
    private String stTipeTransaksi;
    private boolean flagPosting=false;
    private String rekeningIdKas;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private String metodePembayaran;
    private String bank;

    public String getMetodePembayaranName() {
        return metodePembayaranName;
    }

    public void setMetodePembayaranName(String metodePembayaranName) {
        this.metodePembayaranName = metodePembayaranName;
    }

    public String getTipePembayaran() {
        return tipePembayaran;
    }

    public void setTipePembayaran(String tipePembayaran) {
        this.tipePembayaran = tipePembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getStTanggalSelesai() {
        return stTanggalSelesai;
    }

    public void setStTanggalSelesai(String stTanggalSelesai) {
        this.stTanggalSelesai = stTanggalSelesai;
    }

    public String getStTanggalDari() {
        return stTanggalDari;
    }

    public void setStTanggalDari(String stTanggalDari) {
        this.stTanggalDari = stTanggalDari;
    }

    public String getStTipeTransaksi() {
        return stTipeTransaksi;
    }

    public void setStTipeTransaksi(String stTipeTransaksi) {
        this.stTipeTransaksi = stTipeTransaksi;
    }

    public String getRekeningIdKas() {
        return rekeningIdKas;
    }

    public void setRekeningIdKas(String rekeningIdKas) {
        this.rekeningIdKas = rekeningIdKas;
    }

    public boolean isFlagPosting() {
        return flagPosting;
    }

    public void setFlagPosting(boolean flagPosting) {
        this.flagPosting = flagPosting;
    }

    public String getRegisteredFlag() {
        return registeredFlag;
    }

    public void setRegisteredFlag(String registeredFlag) {
        this.registeredFlag = registeredFlag;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRegisteredWho() {
        return registeredWho;
    }

    public void setRegisteredWho(String registeredWho) {
        this.registeredWho = registeredWho;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getStBayar() {
        return stBayar;
    }

    public void setStBayar(String stBayar) {
        this.stBayar = stBayar;
    }

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
}