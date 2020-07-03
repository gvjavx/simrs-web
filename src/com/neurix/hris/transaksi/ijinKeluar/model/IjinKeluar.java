package com.neurix.hris.transaksi.ijinKeluar.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class IjinKeluar extends BaseModel {
    private String ijinKeluarId;
    private String ijinId;
    private String ijinName;
    private BigInteger lamaIjin;
    private String nip;
    private String namaPegawai;
    private String keterangan;
    private String approvalId;
    private String approvalName;
    private Timestamp approvalDate;
    private String notApprovalNote;
    private String approvalFlag;
    private String approvalSdmId;
    private String approvalSdmName;
    private Timestamp approvalSdmDate;
    private String notApprovalSdmNote;
    private String approvalSdmFlag;
    private String jamKeluar;
    private String jamKembali;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private String cancelFlag;
    private Timestamp cancelDate;
    private String cancelPerson;
    private String cancelNote;
    private boolean isMobile;

    private String from;
    private String userIdActive;
    private String userNameActive;
    private String unitId;
    private Date tanggalAwal;
    private Date tanggalAkhir;
    private String closed;
    private String tmpApprove;
    private String stTanggalAwal;
    private String stTanggalAkhir;
    private String divisiId;
    private boolean cekatasan = false;
    private boolean ceksdm = false;
    private String unitName;
    private String divisiName;
    private String self;
    private String keperluan;

    private boolean ijinKeluarApprove = false;
    private boolean ijinKeluarClosed = false;
    private boolean ijinKeluarApproveStatus = false;
    private boolean ijinKeluarApproveStatusSdm = false;
    private boolean ijinKeluarApproveSdm = false;
    private boolean steptwo = false;
    private boolean finish = false;
    private boolean notApprove = false;
    private boolean notApproveSdm = false;
    private boolean canCancel = false;
    private boolean cancel = false;
    private String bagian;
    private String keperluanName;

    private String os;
    private String checkedValue;
    private String stTglMelahirkan;
    private boolean dispenLahir = false;
    private String uploadFile;
    private String fileType;
    private String filePath;
    private Date tglAkhirBaru;
    private String tanggalAkhirBaru;
    private BigInteger lamaIjinBaru;

    private Boolean pengajuanBatal;
    private String flagPengajuanBatal;
    private String roleId;
    private String nipUserLogin;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCheckedValue() {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }

    public String getKeperluanName() {
        return keperluanName;
    }

    public void setKeperluanName(String keperluanName) {
        this.keperluanName = keperluanName;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public boolean isCanCancel() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
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

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public boolean isNotApproveSdm() {
        return notApproveSdm;
    }

    public void setNotApproveSdm(boolean notApproveSdm) {
        this.notApproveSdm = notApproveSdm;
    }

    public boolean isNotApprove() {
        return notApprove;
    }

    public void setNotApprove(boolean notApprove) {
        this.notApprove = notApprove;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getJamKembali() {
        return jamKembali;
    }

    public void setJamKembali(String jamKembali) {
        this.jamKembali = jamKembali;
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

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isSteptwo() {
        return steptwo;
    }

    public void setSteptwo(boolean steptwo) {
        this.steptwo = steptwo;
    }

    public boolean isCeksdm() {
        return ceksdm;
    }

    public void setCeksdm(boolean ceksdm) {
        this.ceksdm = ceksdm;
    }

    public boolean isIjinKeluarApproveStatusSdm() {
        return ijinKeluarApproveStatusSdm;
    }

    public void setIjinKeluarApproveStatusSdm(boolean ijinKeluarApproveStatusSdm) {
        this.ijinKeluarApproveStatusSdm = ijinKeluarApproveStatusSdm;
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

    public String getApprovalSdmFlag() {
        return approvalSdmFlag;
    }

    public void setApprovalSdmFlag(String approvalSdmFlag) {
        this.approvalSdmFlag = approvalSdmFlag;
    }

    public boolean isIjinKeluarApproveSdm() {
        return ijinKeluarApproveSdm;
    }

    public void setIjinKeluarApproveSdm(boolean ijinKeluarApproveSdm) {
        this.ijinKeluarApproveSdm = ijinKeluarApproveSdm;
    }

    public boolean isCekatasan() {
        return cekatasan;
    }

    public void setCekatasan(boolean cekatasan) {
        this.cekatasan = cekatasan;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getTmpApprove() {
        return tmpApprove;
    }

    public void setTmpApprove(String tmpApprove) {
        this.tmpApprove = tmpApprove;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public boolean isIjinKeluarClosed() {
        return ijinKeluarClosed;
    }
    public void setIjinKeluarClosed(boolean ijinKeluarClosed) {
        this.ijinKeluarClosed = ijinKeluarClosed;
    }

    public boolean isIjinKeluarApproveStatus() {
        return ijinKeluarApproveStatus;
    }

    public void setIjinKeluarApproveStatus(boolean ijinKeluarApproveStatus) {
        this.ijinKeluarApproveStatus = ijinKeluarApproveStatus;
    }

    public boolean isIjinKeluarApprove() {
        return ijinKeluarApprove;
    }

    public void setIjinKeluarApprove(boolean ijinKeluarApprove) {
        this.ijinKeluarApprove = ijinKeluarApprove;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getIjinKeluarId() {
        return ijinKeluarId;
    }

    public void setIjinKeluarId(String ijinKeluarId) {
        this.ijinKeluarId = ijinKeluarId;
    }

    public String getIjinId() {
        return ijinId;
    }

    public void setIjinId(String ijinId) {
        this.ijinId = ijinId;
    }

    public String getIjinName() {
        return ijinName;
    }

    public void setIjinName(String ijinName) {
        this.ijinName = ijinName;
    }

    public BigInteger getLamaIjin() {
        return lamaIjin;
    }

    public void setLamaIjin(BigInteger lamaIjin) {
        this.lamaIjin = lamaIjin;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
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

    public String getStTanggalAwal() {
        return stTanggalAwal;
    }

    public void setStTanggalAwal(String stTanggalAwal) {
        this.stTanggalAwal = stTanggalAwal;
    }

    public String getStTanggalAkhir() {
        return stTanggalAkhir;
    }

    public void setStTanggalAkhir(String stTanggalAkhir) {
        this.stTanggalAkhir = stTanggalAkhir;
    }

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

    public String getStTglMelahirkan() {
        return stTglMelahirkan;
    }

    public void setStTglMelahirkan(String stTglMelahirkan) {
        this.stTglMelahirkan = stTglMelahirkan;
    }

    public boolean isDispenLahir() {
        return dispenLahir;
    }

    public void setDispenLahir(boolean dispenLahir) {
        this.dispenLahir = dispenLahir;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BigInteger getLamaIjinBaru() {
        return lamaIjinBaru;
    }

    public void setLamaIjinBaru(BigInteger lamaIjinBaru) {
        this.lamaIjinBaru = lamaIjinBaru;
    }

    public String getTanggalAkhirBaru() {
        return tanggalAkhirBaru;
    }

    public void setTanggalAkhirBaru(String tanggalAkhirBaru) {
        this.tanggalAkhirBaru = tanggalAkhirBaru;
    }

    public Date getTglAkhirBaru() {
        return tglAkhirBaru;
    }

    public void setTglAkhirBaru(Date tglAkhirBaru) {
        this.tglAkhirBaru = tglAkhirBaru;
    }

    public String getFlagPengajuanBatal() {
        return flagPengajuanBatal;
    }

    public void setFlagPengajuanBatal(String flagPengajuanBatal) {
        this.flagPengajuanBatal = flagPengajuanBatal;
    }

    public Boolean getPengajuanBatal() {
        return pengajuanBatal;
    }

    public void setPengajuanBatal(Boolean pengajuanBatal) {
        this.pengajuanBatal = pengajuanBatal;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getNipUserLogin() {
        return nipUserLogin;
    }

    public void setNipUserLogin(String nipUserLogin) {
        this.nipUserLogin = nipUserLogin;
    }
}
