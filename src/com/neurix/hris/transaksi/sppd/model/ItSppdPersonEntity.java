package com.neurix.hris.transaksi.sppd.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;

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
public class ItSppdPersonEntity implements Serializable {

    private String sppdPersonId ;

    private String personId ;
    private String divisiId ;
    private String divisiName ;
    private String branchId ;


    private String branchName ;
    private String positionName ;

    private String approvalId;
    private String approvalFlag;
    private Timestamp approvalDate;
    private String approvalName ;
    private String notApprovalNote ;
    private String approvalSdmId ;
    private String approvalSdmName ;
    private String approvalSdmFlag ;
    private Timestamp approvalSdmDate ;
    private String notApprovalSdmNote;

    private BigDecimal tiketPergi;
    private BigDecimal biayaTambahan;
    private BigDecimal tiketKembali;
    private BigDecimal biayaLokalBerangkat;
    private BigDecimal biayaLokalKembali;
    private BigDecimal biayaTujuan;
    private BigDecimal biayaLain;
    private BigDecimal biayaMakan;
    private Date tanggalBerangkatRealisasi;
    private Date tanggalKembaliRealisasi;
    private String stTanggalBerangkatRealisasi;
    private String stTanggalKembaliRealisasi;
    private String berangkatViaRealisasi;
    private String pulangViaRealisasi;
    private String noteAtasanTransport;
    private String noteSdmTransport;
    private String penginapan;

    private String closed;
    private String personName ;
    private String tipePerson ;
    private String positionId ;
    private String golonganId ;
    private String kelompokId ;
    private String berangkatDari ;
    private String tujuanKe ;
    private String berangkatDariName ;
    private String tujuanKeName ;
    private Date tanggalBerangkat ;
    private Date tanggalKembali ;
    private String stTanggalBerangkat ;
    private String stTanggalKembali ;
    private String pejabatSementara ;
    private String keterangan ;
    private String flagReroute ;
    private String sppdId ;
    private String trainingFlag;
    private String trainingId;


    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String berangkatVia ;
    private String kembaliVia ;

    private ImKotaEntity imKotaEntity ;
    private ImKotaEntity imKotaEntity2 ;
    private ImSppdEntity imSppdEntity;
    private ImBranches imBranches;
    private ImDepartmentEntity imDepartmentEntity;
    private ImPosition imPosition;
    private ImBiodataEntity imBiodataEntity;

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getTrainingFlag() {
        return trainingFlag;
    }

    public void setTrainingFlag(String trainingFlag) {
        this.trainingFlag = trainingFlag;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public BigDecimal getBiayaLokalBerangkat() {
        return biayaLokalBerangkat;
    }

    public void setBiayaLokalBerangkat(BigDecimal biayaLokalBerangkat) {
        this.biayaLokalBerangkat = biayaLokalBerangkat;
    }

    public BigDecimal getBiayaLokalKembali() {
        return biayaLokalKembali;
    }

    public void setBiayaLokalKembali(BigDecimal biayaLokalKembali) {
        this.biayaLokalKembali = biayaLokalKembali;
    }

    public BigDecimal getBiayaLain() {
        return biayaLain;
    }

    public void setBiayaLain(BigDecimal biayaLain) {
        this.biayaLain = biayaLain;
    }

    public BigDecimal getBiayaMakan() {
        return biayaMakan;
    }

    public void setBiayaMakan(BigDecimal biayaMakan) {
        this.biayaMakan = biayaMakan;
    }

    public BigDecimal getBiayaTujuan() {
        return biayaTujuan;
    }

    public void setBiayaTujuan(BigDecimal biayaTujuan) {
        this.biayaTujuan = biayaTujuan;
    }

    public String getBerangkatDariName() {
        return berangkatDariName;
    }

    public void setBerangkatDariName(String berangkatDariName) {
        this.berangkatDariName = berangkatDariName;
    }

    public String getTujuanKeName() {
        return tujuanKeName;
    }

    public void setTujuanKeName(String tujuanKeName) {
        this.tujuanKeName = tujuanKeName;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getBerangkatViaRealisasi() {
        return berangkatViaRealisasi;
    }

    public void setBerangkatViaRealisasi(String berangkatViaRealisasi) {
        this.berangkatViaRealisasi = berangkatViaRealisasi;
    }

    public String getNoteAtasanTransport() {
        return noteAtasanTransport;
    }

    public void setNoteAtasanTransport(String noteAtasanTransport) {
        this.noteAtasanTransport = noteAtasanTransport;
    }

    public String getNoteSdmTransport() {
        return noteSdmTransport;
    }

    public void setNoteSdmTransport(String noteSdmTransport) {
        this.noteSdmTransport = noteSdmTransport;
    }

    public String getPenginapan() {
        return penginapan;
    }

    public void setPenginapan(String penginapan) {
        this.penginapan = penginapan;
    }

    public String getPulangViaRealisasi() {
        return pulangViaRealisasi;
    }

    public void setPulangViaRealisasi(String pulangViaRealisasi) {
        this.pulangViaRealisasi = pulangViaRealisasi;
    }

    public String getStTanggalBerangkatRealisasi() {
        return stTanggalBerangkatRealisasi;
    }

    public void setStTanggalBerangkatRealisasi(String stTanggalBerangkatRealisasi) {
        this.stTanggalBerangkatRealisasi = stTanggalBerangkatRealisasi;
    }

    public String getStTanggalKembaliRealisasi() {
        return stTanggalKembaliRealisasi;
    }

    public void setStTanggalKembaliRealisasi(String stTanggalKembaliRealisasi) {
        this.stTanggalKembaliRealisasi = stTanggalKembaliRealisasi;
    }

    public Date getTanggalBerangkatRealisasi() {
        return tanggalBerangkatRealisasi;
    }

    public void setTanggalBerangkatRealisasi(Date tanggalBerangkatRealisasi) {
        this.tanggalBerangkatRealisasi = tanggalBerangkatRealisasi;
    }

    public Date getTanggalKembaliRealisasi() {
        return tanggalKembaliRealisasi;
    }

    public void setTanggalKembaliRealisasi(Date tanggalKembaliRealisasi) {
        this.tanggalKembaliRealisasi = tanggalKembaliRealisasi;
    }

    public BigDecimal getBiayaTambahan() {
        return biayaTambahan;
    }

    public void setBiayaTambahan(BigDecimal biayaTambahan) {
        this.biayaTambahan = biayaTambahan;
    }

    public BigDecimal getTiketKembali() {
        return tiketKembali;
    }

    public void setTiketKembali(BigDecimal tiketKembali) {
        this.tiketKembali = tiketKembali;
    }

    public BigDecimal getTiketPergi() {
        return tiketPergi;
    }

    public void setTiketPergi(BigDecimal tiketPergi) {
        this.tiketPergi = tiketPergi;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
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

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
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

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getApprovalSdmId() {
        return approvalSdmId;
    }

    public void setApprovalSdmId(String approvalSdmId) {
        this.approvalSdmId = approvalSdmId;
    }

    public String getApprovalSdmName() {
        return approvalSdmName;
    }

    public void setApprovalSdmName(String approvalSdmName) {
        this.approvalSdmName = approvalSdmName;
    }

    public String getApprovalSdmFlag() {
        return approvalSdmFlag;
    }

    public void setApprovalSdmFlag(String approvalSdmFlag) {
        this.approvalSdmFlag = approvalSdmFlag;
    }

    public Timestamp getApprovalSdmDate() {
        return approvalSdmDate;
    }

    public void setApprovalSdmDate(Timestamp approvalSdmDate) {
        this.approvalSdmDate = approvalSdmDate;
    }

    public String getNotApprovalSdmNote() {
        return notApprovalSdmNote;
    }

    public void setNotApprovalSdmNote(String notApprovalSdmNote) {
        this.notApprovalSdmNote = notApprovalSdmNote;
    }

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImSppdEntity getImSppdEntity() {
        return imSppdEntity;
    }

    public void setImSppdEntity(ImSppdEntity imSppdEntity) {
        this.imSppdEntity = imSppdEntity;
    }

    public ImKotaEntity getImKotaEntity2() {
        return imKotaEntity2;
    }

    public void setImKotaEntity2(ImKotaEntity imKotaEntity2) {
        this.imKotaEntity2 = imKotaEntity2;
    }

    public ImKotaEntity getImKotaEntity() {
        return imKotaEntity;
    }

    public void setImKotaEntity(ImKotaEntity imKotaEntity) {
        this.imKotaEntity = imKotaEntity;
    }

    public String getBerangkatVia() {
        return berangkatVia;
    }

    public void setBerangkatVia(String berangkatVia) {
        this.berangkatVia = berangkatVia;
    }

    public String getKembaliVia() {
        return kembaliVia;
    }

    public void setKembaliVia(String kembaliVia) {
        this.kembaliVia = kembaliVia;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getSppdPersonId() {
        return sppdPersonId;
    }

    public void setSppdPersonId(String sppdPersonId) {
        this.sppdPersonId = sppdPersonId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTipePerson() {
        return tipePerson;
    }

    public void setTipePerson(String tipePerson) {
        this.tipePerson = tipePerson;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getBerangkatDari() {
        return berangkatDari;
    }

    public void setBerangkatDari(String berangkatDari) {
        this.berangkatDari = berangkatDari;
    }

    public String getTujuanKe() {
        return tujuanKe;
    }

    public void setTujuanKe(String tujuanKe) {
        this.tujuanKe = tujuanKe;
    }

    public Date getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(Date tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getStTanggalBerangkat() {
        return stTanggalBerangkat;
    }

    public void setStTanggalBerangkat(String stTanggalBerangkat) {
        this.stTanggalBerangkat = stTanggalBerangkat;
    }

    public String getStTanggalKembali() {
        return stTanggalKembali;
    }

    public void setStTanggalKembali(String stTanggalKembali) {
        this.stTanggalKembali = stTanggalKembali;
    }

    public String getPejabatSementara() {
        return pejabatSementara;
    }

    public void setPejabatSementara(String pejabatSementara) {
        this.pejabatSementara = pejabatSementara;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlagReroute() {
        return flagReroute;
    }

    public void setFlagReroute(String flagReroute) {
        this.flagReroute = flagReroute;
    }

    public String getSppdId() {
        return sppdId;
    }

    public void setSppdId(String sppdId) {
        this.sppdId = sppdId;
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
