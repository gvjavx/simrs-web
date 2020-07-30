package com.neurix.hris.transaksi.lembur.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
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
public class LemburEntity implements Serializable {
    private String lemburId;
    private String nip;
    private String pegawaiName;
    private String divisiId;
    private String divisiName;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private String tipePegawaiId;
    private String statusGiling;
    private Date tanggalAwal;
    private Date tanggalAkhir;
    private String jamAwal;
    private String jamAkhir;
    private Double lamaJam;
    private String approvalId;
    private String approvalName;
    private String approvalFlag;
    private Timestamp approvalDate;
    private String keterangan;
    private String tipeLembur;
    private Date tanggalAwalSetuju;
    private Date tanggalAkhirSetuju;
    private String notApprovalNote;
    private String flagDirubahAtasan;
    private String jamAwalLama;
    private String jamAkhirLama;
    private Double lamaJamLama;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBiodataEntity imBiodataEntity ;
    private ImDepartmentEntity imDepartmentEntity;
    private ImPosition imPosition ;

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public Date getTanggalAwalSetuju() {
        return tanggalAwalSetuju;
    }

    public void setTanggalAwalSetuju(Date tanggalAwalSetuju) {
        this.tanggalAwalSetuju = tanggalAwalSetuju;
    }

    public Date getTanggalAkhirSetuju() {
        return tanggalAkhirSetuju;
    }

    public void setTanggalAkhirSetuju(Date tanggalAkhirSetuju) {
        this.tanggalAkhirSetuju = tanggalAkhirSetuju;
    }

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getLemburId() {
        return lemburId;
    }

    public void setLemburId(String lemburId) {
        this.lemburId = lemburId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPegawaiName() {
        return pegawaiName;
    }

    public void setPegawaiName(String pegawaiName) {
        this.pegawaiName = pegawaiName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    public Double getLamaJam() {
        return lamaJam;
    }

    public void setLamaJam(Double lamaJam) {
        this.lamaJam = lamaJam;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipeLembur() {
        return tipeLembur;
    }

    public void setTipeLembur(String tipeLembur) {
        this.tipeLembur = tipeLembur;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public String getFlagDirubahAtasan() {
        return flagDirubahAtasan;
    }

    public void setFlagDirubahAtasan(String flagDirubahAtasan) {
        this.flagDirubahAtasan = flagDirubahAtasan;
    }

    public String getJamAkhirLama() {
        return jamAkhirLama;
    }

    public void setJamAkhirLama(String jamAkhirLama) {
        this.jamAkhirLama = jamAkhirLama;
    }

    public String getJamAwalLama() {
        return jamAwalLama;
    }

    public void setJamAwalLama(String jamAwalLama) {
        this.jamAwalLama = jamAwalLama;
    }

    public Double getLamaJamLama() {
        return lamaJamLama;
    }

    public void setLamaJamLama(Double lamaJamLama) {
        this.lamaJamLama = lamaJamLama;
    }
}
