package com.neurix.simrs.transaksi.verifikatorasuransi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 17/07/20.
 */
public class StrukAsuransi {
    private String id;
    private String idDetailCheckup;
    private String jenis;
    private String urlFotoStruk;
    private String approveFlag;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String approveWho;
    private String noAsuransi;
    private String idPasien;
    private String namaPasien;
    private String namaAsuransi;
    private BigDecimal jumlahCover;
    private BigDecimal coverDitanggung;
    private String ApproveFlagNull;
    private String branchId;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getApproveFlagNull() {
        return ApproveFlagNull;
    }

    public void setApproveFlagNull(String approveFlagNull) {
        ApproveFlagNull = approveFlagNull;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getUrlFotoStruk() {
        return urlFotoStruk;
    }

    public void setUrlFotoStruk(String urlFotoStruk) {
        this.urlFotoStruk = urlFotoStruk;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
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

    public String getApproveWho() {
        return approveWho;
    }

    public void setApproveWho(String approveWho) {
        this.approveWho = approveWho;
    }

    public String getNoAsuransi() {
        return noAsuransi;
    }

    public void setNoAsuransi(String noAsuransi) {
        this.noAsuransi = noAsuransi;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public BigDecimal getJumlahCover() {
        return jumlahCover;
    }

    public void setJumlahCover(BigDecimal jumlahCover) {
        this.jumlahCover = jumlahCover;
    }

    public BigDecimal getCoverDitanggung() {
        return coverDitanggung;
    }

    public void setCoverDitanggung(BigDecimal coverDitanggung) {
        this.coverDitanggung = coverDitanggung;
    }
}
