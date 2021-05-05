package com.neurix.hris.transaksi.medicalrecord.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class MedicalRecord extends BaseModel implements Serializable {
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
    private String nama;
    private String note;
    private Timestamp approveDate;
    private String stTanggalBerobat;
    private String flagPayroll;
    private String flagKoreksi;
    private String keteranganKoreksi;

    private String iconApprove;
    private String tipePengobatanName;
    private String tipeBerobatName;
    private String flagApprove;

    private String namaPasien;
    private String namaRumasSakit;
    private String alamat;
    private String kelas;
    private String statusKeluarga;
    private String positionName;
    private String branchName;
    private String branchAddress;

    private boolean approve = false;
    private boolean print = false;
    private boolean edit = false;
    private boolean admin = false;
    private boolean koreksi=false;



    private String rsId;
    private String rsName;
    private String rsKelasId;
    private String rsKelasName;
    private String statusPegawai;
    private String jumlahBiaya;
    private String namaKeluarga;
    private String golonganId;
    private String sendiri;
    private String keluarga;
    private String divisiName;
    private String stTanggalDari;
    private String stTanggalSampai;
    private String bagian;
    private String kabagSdmName;
    private String no;

    private boolean printSuratMedical = true;

    public boolean isPrintSuratMedical() {
        return printSuratMedical;
    }

    public void setPrintSuratMedical(boolean printSuratMedical) {
        this.printSuratMedical = printSuratMedical;
    }

    private boolean delete = true;

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

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

    public boolean isKoreksi() {
        return koreksi;
    }

    public void setKoreksi(boolean koreksi) {
        this.koreksi = koreksi;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getKabagSdmName() {
        return kabagSdmName;
    }

    public void setKabagSdmName(String kabagSdmName) {
        this.kabagSdmName = kabagSdmName;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getStTanggalDari() {
        return stTanggalDari;
    }

    public void setStTanggalDari(String stTanggalDari) {
        this.stTanggalDari = stTanggalDari;
    }

    public String getStTanggalSampai() {
        return stTanggalSampai;
    }

    public void setStTanggalSampai(String stTanggalSampai) {
        this.stTanggalSampai = stTanggalSampai;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getSendiri() {
        return sendiri;
    }

    public void setSendiri(String sendiri) {
        this.sendiri = sendiri;
    }

    public String getKeluarga() {
        return keluarga;
    }

    public void setKeluarga(String keluarga) {
        this.keluarga = keluarga;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getNamaKeluarga() {
        return namaKeluarga;
    }

    public void setNamaKeluarga(String namaKeluarga) {
        this.namaKeluarga = namaKeluarga;
    }

    public String getJumlahBiaya() {
        return jumlahBiaya;
    }

    public void setJumlahBiaya(String jumlahBiaya) {
        this.jumlahBiaya = jumlahBiaya;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getRsKelasId() {
        return rsKelasId;
    }

    public void setRsKelasId(String rsKelasId) {
        this.rsKelasId = rsKelasId;
    }

    public String getRsKelasName() {
        return rsKelasName;
    }

    public void setRsKelasName(String rsKelasName) {
        this.rsKelasName = rsKelasName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaRumasSakit() {
        return namaRumasSakit;
    }

    public void setNamaRumasSakit(String namaRumasSakit) {
        this.namaRumasSakit = namaRumasSakit;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getStTanggalBerobat() {
        return stTanggalBerobat;
    }

    public void setStTanggalBerobat(String stTanggalBerobat) {
        this.stTanggalBerobat = stTanggalBerobat;
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

    public String getFlagApprove() {
        return flagApprove;
    }

    public void setFlagApprove(String flagApprove) {
        this.flagApprove = flagApprove;
    }

    public boolean isEdit() {
        return edit;
    }


    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public String getIconApprove() {
        return iconApprove;
    }

    public void setIconApprove(String iconApprove) {
        this.iconApprove = iconApprove;
    }

    public String getTipePengobatanName() {
        return tipePengobatanName;
    }

    public void setTipePengobatanName(String tipePengobatanName) {
        this.tipePengobatanName = tipePengobatanName;
    }

    public String getTipeBerobatName() {
        return tipeBerobatName;
    }

    public void setTipeBerobatName(String tipeBerobatName) {
        this.tipeBerobatName = tipeBerobatName;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
}
