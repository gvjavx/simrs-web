package com.neurix.hris.transaksi.training.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class TrainingPerson extends BaseModel implements Serializable {
    private String trainingPersonId;
    private String trainingId;
    private String personId;
    private String tipeTraining;
    private String personName;
    private String divisiId;
    private String approvalId;
    private String approvalName;
    private Timestamp approvalDate;
    private String approvalFlag;
    private String notApprovalNote;
    private String approvalBosId;
    private String approvalBosName;
    private Timestamp approvalBosDate;
    private String approvalBosFlag;
    private String notApprovalBosNote;
    private String divisiName;
    private String note;
    private String instansi;
    private String kota;

    private String iconApproveAtasan;
    private String iconApproveKepala;
    private String iconApproveSdm;

    private boolean add;
    private boolean edit;
    private String project;

    private String status;
    private String unitId;

    private String trainingName;
    private Date trainingStartdate;
    private String stTrainingStartdate;
    private Date trainingEndDate;
    private String stTrainingEndDate;
    private String flagApprove;

    private boolean approveAtasan = false;
    private boolean approveKepala = false;
    private boolean approveSdm = false;

    private Timestamp approvalSdmDate;
    private String approvalSdm;
    private String notApprovalSdmNote;

    private String personSppdName;
    private Date tanggalBerangkat;
    private Date tanggalKembali;
    private String strTanggalBerangkat;
    private String strTanggalKembali;
    private String sppdStatus;
    private String sppdBerangkatDari;
    private String sppdBerangkatDariName;
    private String sppdTujuanName;
    private String sppdBerangkatVia;
    private String sppdKembaliVia;
    private String sppdKeperluan;
    private String unitName;
    private String positionId;
    private String positionName;

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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSppdBerangkatDariName() {
        return sppdBerangkatDariName;
    }

    public void setSppdBerangkatDariName(String sppdBerangkatDariName) {
        this.sppdBerangkatDariName = sppdBerangkatDariName;
    }

    public String getSppdTujuanName() {
        return sppdTujuanName;
    }

    public void setSppdTujuanName(String sppdTujuanName) {
        this.sppdTujuanName = sppdTujuanName;
    }

    public String getSppdKeperluan() {
        return sppdKeperluan;
    }

    public void setSppdKeperluan(String sppdKeperluan) {
        this.sppdKeperluan = sppdKeperluan;
    }

    public String getSppdBerangkatVia() {
        return sppdBerangkatVia;
    }

    public void setSppdBerangkatVia(String sppdBerangkatVia) {
        this.sppdBerangkatVia = sppdBerangkatVia;
    }

    public String getSppdKembaliVia() {
        return sppdKembaliVia;
    }

    public void setSppdKembaliVia(String sppdKembaliVia) {
        this.sppdKembaliVia = sppdKembaliVia;
    }

    public String getSppdBerangkatDari() {
        return sppdBerangkatDari;
    }

    public void setSppdBerangkatDari(String sppdBerangkatDari) {
        this.sppdBerangkatDari = sppdBerangkatDari;
    }

    public String getSppdStatus() {
        return sppdStatus;
    }

    public void setSppdStatus(String sppdStatus) {
        this.sppdStatus = sppdStatus;
    }

    public String getStrTanggalBerangkat() {
        return strTanggalBerangkat;
    }

    public void setStrTanggalBerangkat(String strTanggalBerangkat) {
        this.strTanggalBerangkat = strTanggalBerangkat;
    }

    public String getStrTanggalKembali() {
        return strTanggalKembali;
    }

    public void setStrTanggalKembali(String strTanggalKembali) {
        this.strTanggalKembali = strTanggalKembali;
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

    public String getPersonSppdName() {
        return personSppdName;
    }

    public void setPersonSppdName(String personSppdName) {
        this.personSppdName = personSppdName;
    }

    public String getStTrainingEndDate() {
        return stTrainingEndDate;
    }

    public void setStTrainingEndDate(String stTrainingEndDate) {
        this.stTrainingEndDate = stTrainingEndDate;
    }

    public String getStTrainingStartdate() {
        return stTrainingStartdate;
    }

    public void setStTrainingStartdate(String stTrainingStartdate) {
        this.stTrainingStartdate = stTrainingStartdate;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTipeTraining() {
        return tipeTraining;
    }

    public void setTipeTraining(String tipeTraining) {
        this.tipeTraining = tipeTraining;
    }

    public String getIconApproveSdm() {
        return iconApproveSdm;
    }

    public void setIconApproveSdm(String iconApproveSdm) {
        this.iconApproveSdm = iconApproveSdm;
    }

    public boolean isApproveSdm() {
        return approveSdm;
    }

    public void setApproveSdm(boolean approveSdm) {
        this.approveSdm = approveSdm;
    }

    public Timestamp getApprovalSdmDate() {
        return approvalSdmDate;
    }

    public void setApprovalSdmDate(Timestamp approvalSdmDate) {
        this.approvalSdmDate = approvalSdmDate;
    }

    public String getApprovalSdm() {
        return approvalSdm;
    }

    public void setApprovalSdm(String approvalSdm) {
        this.approvalSdm = approvalSdm;
    }

    public String getNotApprovalSdmNote() {
        return notApprovalSdmNote;
    }

    public void setNotApprovalSdmNote(String notApprovalSdmNote) {
        this.notApprovalSdmNote = notApprovalSdmNote;
    }

    private String userIdActive;
    private String userNameActive;

    public String getUserIdActive() {
        return userIdActive;
    }

    public void setUserIdActive(String userIdActive) {
        this.userIdActive = userIdActive;
    }

    public String getUserNameActive() {
        return userNameActive;
    }

    public void setUserNameActive(String userNameActive) {
        this.userNameActive = userNameActive;
    }

    public String getIconApproveAtasan() {
        return iconApproveAtasan;
    }

    public void setIconApproveAtasan(String iconApproveAtasan) {
        this.iconApproveAtasan = iconApproveAtasan;
    }

    public String getIconApproveKepala() {
        return iconApproveKepala;
    }

    public void setIconApproveKepala(String iconApproveKepala) {
        this.iconApproveKepala = iconApproveKepala;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isApproveAtasan() {
        return approveAtasan;
    }

    public void setApproveAtasan(boolean approveAtasan) {
        this.approveAtasan = approveAtasan;
    }

    public boolean isApproveKepala() {
        return approveKepala;
    }

    public void setApproveKepala(boolean approveKepala) {
        this.approveKepala = approveKepala;
    }

    public String getFlagApprove() {
        return flagApprove;
    }

    public void setFlagApprove(String flagApprove) {
        this.flagApprove = flagApprove;
    }

    public String getTrainingPersonId() {
        return trainingPersonId;
    }

    public void setTrainingPersonId(String trainingPersonId) {
        this.trainingPersonId = trainingPersonId;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
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

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
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

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getApprovalBosId() {
        return approvalBosId;
    }

    public void setApprovalBosId(String approvalBosId) {
        this.approvalBosId = approvalBosId;
    }

    public String getApprovalBosName() {
        return approvalBosName;
    }

    public void setApprovalBosName(String approvalBosName) {
        this.approvalBosName = approvalBosName;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Timestamp getApprovalBosDate() {
        return approvalBosDate;
    }

    public void setApprovalBosDate(Timestamp approvalBosDate) {
        this.approvalBosDate = approvalBosDate;
    }

    public String getApprovalBosFlag() {
        return approvalBosFlag;
    }

    public void setApprovalBosFlag(String approvalBosFlag) {
        this.approvalBosFlag = approvalBosFlag;
    }

    public String getNotApprovalBosNote() {
        return notApprovalBosNote;
    }

    public void setNotApprovalBosNote(String notApprovalBosNote) {
        this.notApprovalBosNote = notApprovalBosNote;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public Date getTrainingStartdate() {
        return trainingStartdate;
    }

    public void setTrainingStartdate(Date trainingStartdate) {
        this.trainingStartdate = trainingStartdate;
    }

    public Date getTrainingEndDate() {
        return trainingEndDate;
    }

    public void setTrainingEndDate(Date trainingEndDate) {
        this.trainingEndDate = trainingEndDate;
    }
}
