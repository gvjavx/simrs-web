package com.neurix.akuntansi.transaksi.koreksi.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class ItAkunKoreksiEntity implements Serializable {
    private String koreksiId;
    private String tipeTransaksi;
    private Date tanggalKoreksi;
    private String keterangan;
    private String flag;
    private String branchId;
    private String noJurnal;

    private String action;
    private String approvalKeuanganFlag;
    private String approvalKeuanganId;
    private String approvalKeuanganName;
    private Timestamp approvalKeuanganDate;
    private String approvalKasubKeuanganFlag;
    private String approvalKasubKeuanganId;
    private String approvalKasubKeuanganName;
    private Timestamp approvalKasubKeuanganDate;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String registeredFlag;
    private Timestamp registeredDate;
    private String registeredWho;

    public ItAkunKoreksiEntity() {
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


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
}
