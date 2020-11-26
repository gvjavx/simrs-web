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
    private String metodeBayar;
    private String bank;
    private BigDecimal bayar;
    private String keterangan;
    private String noSlipBank;
    private String branchId;
    private String noJurnal;
    private String registeredFlag;
    private Timestamp registeredDate;
    private String registeredWho;
    private String tipePembayaran;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;


    //approval
    private String approvalKeuanganFlag;
    private String approvalKeuanganId;
    private String approvalKeuanganName;
    private Timestamp approvalKeuanganDate;

    private String approvalKasubKeuanganFlag;
    private String approvalKasubKeuanganId;
    private String approvalKasubKeuanganName;
    private Timestamp approvalKasubKeuanganDate;


    public String getApprovalKeuanganFlag() {
        return approvalKeuanganFlag;
    }

    public void setApprovalKeuanganFlag(String approvalKeuanganFlag) {
        this.approvalKeuanganFlag = approvalKeuanganFlag;
    }

    public String getApprovalKeuanganId() {
        return approvalKeuanganId;
    }

    public void setApprovalKeuanganId(String approvalKeuanganId) {
        this.approvalKeuanganId = approvalKeuanganId;
    }

    public String getApprovalKeuanganName() {
        return approvalKeuanganName;
    }

    public void setApprovalKeuanganName(String approvalKeuanganName) {
        this.approvalKeuanganName = approvalKeuanganName;
    }

    public Timestamp getApprovalKeuanganDate() {
        return approvalKeuanganDate;
    }

    public void setApprovalKeuanganDate(Timestamp approvalKeuanganDate) {
        this.approvalKeuanganDate = approvalKeuanganDate;
    }

    public String getApprovalKasubKeuanganFlag() {
        return approvalKasubKeuanganFlag;
    }

    public void setApprovalKasubKeuanganFlag(String approvalKasubKeuanganFlag) {
        this.approvalKasubKeuanganFlag = approvalKasubKeuanganFlag;
    }

    public String getApprovalKasubKeuanganId() {
        return approvalKasubKeuanganId;
    }

    public void setApprovalKasubKeuanganId(String approvalKasubKeuanganId) {
        this.approvalKasubKeuanganId = approvalKasubKeuanganId;
    }

    public String getApprovalKasubKeuanganName() {
        return approvalKasubKeuanganName;
    }

    public void setApprovalKasubKeuanganName(String approvalKasubKeuanganName) {
        this.approvalKasubKeuanganName = approvalKasubKeuanganName;
    }

    public Timestamp getApprovalKasubKeuanganDate() {
        return approvalKasubKeuanganDate;
    }

    public void setApprovalKasubKeuanganDate(Timestamp approvalKasubKeuanganDate) {
        this.approvalKasubKeuanganDate = approvalKasubKeuanganDate;
    }

    public String getTipePembayaran() {
        return tipePembayaran;
    }

    public void setTipePembayaran(String tipePembayaran) {
        this.tipePembayaran = tipePembayaran;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getRegisteredFlag() {
        return registeredFlag;
    }

    public void setRegisteredFlag(String registeredFlag) {
        this.registeredFlag = registeredFlag;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
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
