package com.neurix.hris.transaksi.cutiPegawai.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItCutiPegawaiEntity implements Serializable {
    private String cutiPegawaiId;
    private String nip;
    private String pegawaiPenggantiSementara;
    private String cutiId;
    private String cutiName;
    private BigInteger lamaHariCuti;
    private BigInteger sisaCutiHari;
    private String approvalId;
    private Timestamp approvalDate;
    private String approvalFlag;
    private String note;
    private String noteApproval;
    private String cancelFlag;
    private Timestamp cancelDate;
    private String cancelPerson;
    private String cancelNote;
    private String alamatCuti;
    private String keterangan;

    private Date tanggalDari;
    private Date tanggalSelesai;
    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private BigInteger jumlahCuti;
    private String closed;
    private String divisiName;
    private String unitName;
    private String posisiName;

    private ImBiodataEntity imBiodataEntity;
    private ImCutiEntity imCutiEntity;
    private ImPosition imPosition;
    private ImBranches imBranches;
    private ImDepartmentEntity imDepartmentEntity;

    private String unitId;
    private String divisiId;
    private String posisiId;
    private String bagianId;
    private String profesiId;

    private String flagPerbaikan;

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public String getFlagPerbaikan() {
        return flagPerbaikan;
    }

    public void setFlagPerbaikan(String flagPerbaikan) {
        this.flagPerbaikan = flagPerbaikan;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getPosisiId() {
        return posisiId;
    }

    public void setPosisiId(String posisiId) {
        this.posisiId = posisiId;
    }

    public String getAlamatCuti() {
        return alamatCuti;
    }

    public void setAlamatCuti(String alamatCuti) {
        this.alamatCuti = alamatCuti;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCancelPerson() {
        return cancelPerson;
    }

    public void setCancelPerson(String cancelPerson) {
        this.cancelPerson = cancelPerson;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }
    public String getCutiName() {
        return cutiName;
    }

    public void setCutiName(String cutiName) {
        this.cutiName = cutiName;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPosisiName() {
        return posisiName;
    }

    public void setPosisiName(String posisiName) {
        this.posisiName = posisiName;
    }


    public ImCutiEntity getImCutiEntity() {
        return imCutiEntity;
    }

    public void setImCutiEntity(ImCutiEntity imCutiEntity) {
        this.imCutiEntity = imCutiEntity;
    }

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public BigInteger getJumlahCuti() {
        return jumlahCuti;
    }

    public void setJumlahCuti(BigInteger jumlahCuti) {
        this.jumlahCuti = jumlahCuti;
    }

    public String getCutiPegawaiId() {
        return cutiPegawaiId;
    }

    public void setCutiPegawaiId(String cutiPegawaiId) {
        this.cutiPegawaiId = cutiPegawaiId;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPegawaiPenggantiSementara() {
        return pegawaiPenggantiSementara;
    }

    public void setPegawaiPenggantiSementara(String pegawaiPenggantiSementara) {
        this.pegawaiPenggantiSementara = pegawaiPenggantiSementara;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public BigInteger getSisaCutiHari() {
        return sisaCutiHari;
    }

    public void setSisaCutiHari(BigInteger sisaCutiHari) {
        this.sisaCutiHari = sisaCutiHari;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public BigInteger getLamaHariCuti() {
        return lamaHariCuti;
    }

    public void setLamaHariCuti(BigInteger lamaHariCuti) {
        this.lamaHariCuti = lamaHariCuti;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteApproval() {
        return noteApproval;
    }

    public void setNoteApproval(String noteApproval) {
        this.noteApproval = noteApproval;
    }

    public Date getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(Date tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
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
