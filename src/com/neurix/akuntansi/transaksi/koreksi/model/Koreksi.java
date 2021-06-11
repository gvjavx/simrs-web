package com.neurix.akuntansi.transaksi.koreksi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class Koreksi extends BaseModel {

    private String koreksiId;
    private String tipeTransaksi;
    private Date tanggalKoreksi;
    private String keterangan;

    private String branchId;
    private String noJurnal;
    private String registeredFlag;
    private Timestamp registeredDate;

    //tampilan UI list
    private String stTanggalKoreksi;
    private String stJumlahPembayaran;
    private String masterId;
    private String masterName;
    private String rekeningId;
    private String divisiName;
    private String posisiCoa;
    private String branchIdUser;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private String branchName;
    private String jabatan;
    private String stRegisteredDate;
    private String stTipeTransaksi;
    private String registeredWho;
    private boolean flagPosting=false;

    //Jurnal Report
    private String noSlipBank;
    private String tipeKas;
    private BigDecimal bayar;


    //approval
    private String approvalKeuanganFlag;
    private String approvalKeuanganId;
    private String approvalKeuanganName;
    private Timestamp approvalKeuanganDate;
    private String stApprovalKeuanganDate;

    private String approvalKasubKeuanganFlag;
    private String approvalKasubKeuanganId;
    private String approvalKasubKeuanganName;
    private Timestamp approvalKasubKeuanganDate;
    private String stApprovalKasubKeuanganDate;

    public Koreksi() {
    }

    public String getNoSlipBank() {
        return noSlipBank;
    }

    public void setNoSlipBank(String noSlipBank) {
        this.noSlipBank = noSlipBank;
    }

    public String getTipeKas() {
        return tipeKas;
    }

    public void setTipeKas(String tipeKas) {
        this.tipeKas = tipeKas;
    }

    public BigDecimal getBayar() {
        return bayar;
    }

    public void setBayar(BigDecimal bayar) {
        this.bayar = bayar;
    }

    public String getRegisteredWho() {
        return registeredWho;
    }

    public void setRegisteredWho(String registeredWho) {
        this.registeredWho = registeredWho;
    }

    public String getStTipeTransaksi() {
        return stTipeTransaksi;
    }

    public void setStTipeTransaksi(String stTipeTransaksi) {
        this.stTipeTransaksi = stTipeTransaksi;
    }

    public String getStRegisteredDate() {
        return stRegisteredDate;
    }

    public void setStRegisteredDate(String stRegisteredDate) {
        this.stRegisteredDate = stRegisteredDate;
    }

    public boolean isFlagPosting() {
        return flagPosting;
    }

    public void setFlagPosting(boolean flagPosting) {
        this.flagPosting = flagPosting;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStTanggalDari() {
        return stTanggalDari;
    }

    public void setStTanggalDari(String stTanggalDari) {
        this.stTanggalDari = stTanggalDari;
    }

    public String getStTanggalSelesai() {
        return stTanggalSelesai;
    }

    public void setStTanggalSelesai(String stTanggalSelesai) {
        this.stTanggalSelesai = stTanggalSelesai;
    }

    public String getStTanggalKoreksi() {
        return stTanggalKoreksi;
    }

    public void setStTanggalKoreksi(String stTanggalKoreksi) {
        this.stTanggalKoreksi = stTanggalKoreksi;
    }

    public String getBranchIdUser() {
        return branchIdUser;
    }

    public void setBranchIdUser(String branchIdUser) {
        this.branchIdUser = branchIdUser;
    }

    public String getStJumlahPembayaran() {
        return stJumlahPembayaran;
    }

    public void setStJumlahPembayaran(String stJumlahPembayaran) {
        this.stJumlahPembayaran = stJumlahPembayaran;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getPosisiCoa() {
        return posisiCoa;
    }

    public void setPosisiCoa(String posisiCoa) {
        this.posisiCoa = posisiCoa;
    }

    public String getKoreksiId() {
        return koreksiId;
    }

    public void setKoreksiId(String koreksiId) {
        this.koreksiId = koreksiId;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public Date getTanggalKoreksi() {
        return tanggalKoreksi;
    }

    public void setTanggalKoreksi(Date tanggalKoreksi) {
        this.tanggalKoreksi = tanggalKoreksi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
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

    public String getStApprovalKeuanganDate() {
        return stApprovalKeuanganDate;
    }

    public void setStApprovalKeuanganDate(String stApprovalKeuanganDate) {
        this.stApprovalKeuanganDate = stApprovalKeuanganDate;
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

    public String getStApprovalKasubKeuanganDate() {
        return stApprovalKasubKeuanganDate;
    }

    public void setStApprovalKasubKeuanganDate(String stApprovalKasubKeuanganDate) {
        this.stApprovalKasubKeuanganDate = stApprovalKasubKeuanganDate;
    }

}
