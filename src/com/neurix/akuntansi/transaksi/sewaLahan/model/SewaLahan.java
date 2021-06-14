package com.neurix.akuntansi.transaksi.sewaLahan.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class SewaLahan extends BaseModel {
    /*private String penyewaanLahanId;
    private String namaPenyewa;
    private String keterangan;
    private Date tanggalBayar;
    private BigDecimal nilai;
    private BigDecimal nilaiPpn;
    private BigDecimal nilaiPph;
    private BigDecimal nilaiNetto;
*/
    private String stNilai;
    private String stNilaiPpn;
    private String stNilaiPph;

    private String stNilaiNetto;
    private String stTanggalBayar;
    private String sewaLahanId;
    private String namaPenyewa;
    private String keterangan;
    private Date tanggalBayar;
    private BigDecimal nilai;
    private String cancelFlag;
    private String cancelWho;
    private Timestamp cancelDate;
    private String noJurnal;
    private BigDecimal nilaiNetto;
    private BigDecimal nilaiPpn;
    private BigDecimal nilaiPph;

    private String branchIdUser;

    private String branchId;
    private String branchName;
    private String stCancelDate;
    private String approvalFlag;
    private String approvalWho;
    private Timestamp approvalDate;
    private String stApprovalDate;
    private String metodeBayar;
    private String bank;
    private String bankName;

    private String stTanggalDari;
    private String stTanggalSelesai;

    private String namaPenyewaName;
    private String noFaktur;
    private String urlFakturImage;

    public String getBranchIdUser() {
        return branchIdUser;
    }

    public void setBranchIdUser(String branchIdUser) {
        this.branchIdUser = branchIdUser;
    }

    public String getStNilai() {
        return stNilai;
    }

    public void setStNilai(String stNilai) {
        this.stNilai = stNilai;
    }

    public String getStNilaiPpn() {
        return stNilaiPpn;
    }

    public void setStNilaiPpn(String stNilaiPpn) {
        this.stNilaiPpn = stNilaiPpn;
    }

    public String getStNilaiPph() {
        return stNilaiPph;
    }

    public void setStNilaiPph(String stNilaiPph) {
        this.stNilaiPph = stNilaiPph;
    }

    public String getSewaLahanId() {
        return sewaLahanId;
    }

    public void setSewaLahanId(String sewaLahanId) {
        this.sewaLahanId = sewaLahanId;
    }

    public String getStApprovalDate() {
        return stApprovalDate;
    }

    public void setStApprovalDate(String stApprovalDate) {
        this.stApprovalDate = stApprovalDate;
    }

    public String getStCancelDate() {
        return stCancelDate;
    }

    public void setStCancelDate(String stCancelDate) {
        this.stCancelDate = stCancelDate;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getUrlFakturImage() {
        return urlFakturImage;
    }

    public void setUrlFakturImage(String urlFakturImage) {
        this.urlFakturImage = urlFakturImage;
    }

    public BigDecimal getNilaiPph() {
        return nilaiPph;
    }

    public void setNilaiPph(BigDecimal nilaiPph) {
        this.nilaiPph = nilaiPph;
    }


    public String getNamaPenyewaName() {
        return namaPenyewaName;
    }

    public void setNamaPenyewaName(String namaPenyewaName) {
        this.namaPenyewaName = namaPenyewaName;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigDecimal getNilaiPpn() {
        return nilaiPpn;
    }

    public void setNilaiPpn(BigDecimal nilaiPpn) {
        this.nilaiPpn = nilaiPpn;
    }

    public BigDecimal getNilaiNetto() {
        return nilaiNetto;
    }

    public void setNilaiNetto(BigDecimal nilaiNetto) {
        this.nilaiNetto = nilaiNetto;
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

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getCancelWho() {
        return cancelWho;
    }

    public void setCancelWho(String cancelWho) {
        this.cancelWho = cancelWho;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovalWho() {
        return approvalWho;
    }

    public void setApprovalWho(String approvalWho) {
        this.approvalWho = approvalWho;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getStTanggalBayar() {
        return stTanggalBayar;
    }

    public void setStTanggalBayar(String stTanggalBayar) {
        this.stTanggalBayar = stTanggalBayar;
    }

    public String getStNilaiNetto() {
        return stNilaiNetto;
    }

    public void setStNilaiNetto(String stNilaiNetto) {
        this.stNilaiNetto = stNilaiNetto;
    }
}
