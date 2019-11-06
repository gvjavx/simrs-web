package com.neurix.hris.transaksi.medicalrecord.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ItHrisMedicalRecordEntity implements Serializable {
    private String medicalRecordId;
    private String nip;
    private Date tanggalBerobat;
    private String namaBerobat;
    private String tipeOrangBerobat;
    private String keluargaId;
    private String tipePengobatan;
    private String flagSuratJaminan;
    private String noSuratJaminan;
    private String approved;
    private String approvedName;
    private String approvedId;
    private String branchId;
    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private Timestamp approveDate;
    private String note;
    private String rsKelasId;
    private String rsId;
    private String jumlahBiaya;
    private String flagPayroll;

    private String payrollId;

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    private String flagKoreksi;
    private String keteranganKoreksi;

    public String getFlagPayroll() {
        return flagPayroll;
    }

    public void setFlagPayroll(String flagPayroll) {
        this.flagPayroll = flagPayroll;
    }

    public String getFlagKoreksi() {
        return flagKoreksi;
    }

    public void setFlagKoreksi(String flagKoreksi) {
        this.flagKoreksi = flagKoreksi;
    }

    public String getKeteranganKoreksi() {
        return keteranganKoreksi;
    }

    public void setKeteranganKoreksi(String keteranganKoreksi) {
        this.keteranganKoreksi = keteranganKoreksi;
    }

    public String getJumlahBiaya() {
        return jumlahBiaya;
    }

    public void setJumlahBiaya(String jumlahBiaya) {
        this.jumlahBiaya = jumlahBiaya;
    }

    public String getRsKelasId() {
        return rsKelasId;
    }

    public void setRsKelasId(String rsKelasId) {
        this.rsKelasId = rsKelasId;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public Timestamp getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Timestamp approveDate) {
        this.approveDate = approveDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggalBerobat() {
        return tanggalBerobat;
    }

    public void setTanggalBerobat(Date tanggalBerobat) {
        this.tanggalBerobat = tanggalBerobat;
    }

    public String getNamaBerobat() {
        return namaBerobat;
    }

    public void setNamaBerobat(String namaBerobat) {
        this.namaBerobat = namaBerobat;
    }

    public String getTipeOrangBerobat() {
        return tipeOrangBerobat;
    }

    public void setTipeOrangBerobat(String tipeOrangBerobat) {
        this.tipeOrangBerobat = tipeOrangBerobat;
    }

    public String getKeluargaId() {
        return keluargaId;
    }

    public void setKeluargaId(String keluargaId) {
        this.keluargaId = keluargaId;
    }

    public String getTipePengobatan() {
        return tipePengobatan;
    }

    public void setTipePengobatan(String tipePengobatan) {
        this.tipePengobatan = tipePengobatan;
    }

    public String getFlagSuratJaminan() {
        return flagSuratJaminan;
    }

    public void setFlagSuratJaminan(String flagSuratJaminan) {
        this.flagSuratJaminan = flagSuratJaminan;
    }

    public String getNoSuratJaminan() {
        return noSuratJaminan;
    }

    public void setNoSuratJaminan(String noSuratJaminan) {
        this.noSuratJaminan = noSuratJaminan;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApprovedName() {
        return approvedName;
    }

    public void setApprovedName(String approvedName) {
        this.approvedName = approvedName;
    }

    public String getApprovedId() {
        return approvedId;
    }

    public void setApprovedId(String approvedId) {
        this.approvedId = approvedId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateDateWho() {
        return createDateWho;
    }

    public void setCreateDateWho(String createDateWho) {
        this.createDateWho = createDateWho;
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
}
