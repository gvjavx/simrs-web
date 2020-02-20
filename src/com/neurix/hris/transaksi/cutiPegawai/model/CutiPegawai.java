package com.neurix.hris.transaksi.cutiPegawai.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class CutiPegawai extends BaseModel {
    private String cutiPegawaiId;
    private String nip;
    private String pegawaiPenggantiSementara;
    private String cutiId;
    private BigInteger lamaHariCuti;
    private BigInteger sisaCutiHari;
    private String approvalId;
    private Timestamp approvalDate;
    private String approvalFlag;
    private String note;
    private String noteApproval;
    private Date tanggalDari;
    private Date tanggalSelesai;
    private String strTanggalDari;
    private String strTanggalSelesai;
    private String posisiName;
    private String divisiName;
    private String unitName;
    private String self;
    private String cancelFlag;
    private Timestamp cancelDate;
    private String cancelPerson;
    private String cancelNote;
    private String checkedValue;

    private BigInteger jumlahCutihari;
    private BigInteger sisaHariCuti;

    private String divisiId;
    private String unitId;
    private String posisiId;
    private String bagianId;


    private String cutiName;
    private String closed;
    private String tmpApprove;
    private String namaPegawai;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private boolean cutiPegawaiApprove = false;
    private boolean cutiPegawaiClosed = false;
    private boolean cutiPegawaiApproveStatus = false;
    private boolean steptwo = false;
    private boolean finish = false;

    private boolean cekatasan = false;
    private boolean notApprove = false;
    private Date tanggalMasuk;
    private boolean statusTanggal = false;
    private boolean canCancel = false;
    private boolean cancel = false;
    private boolean forReset = false;
    private boolean forMobile;

    private String userIdActive;
    private String userNameActive;
    private String bagian;
    private String sisaCutiPanjang;
    private String sisaCutiTahunan;
    private String alamatCuti;
    private String keterangan;
    private BigInteger setelahResetCutiTahunan;
    private BigInteger setelahResetCutiPanjang;
    private String stSetelahResetCutiTahunan;
    private String stSetelahResetCutiPanjang;
    private java.sql.Date tanggal;
    private Integer nomor;
    private String no;
    private Date tanggalAktif;
    private String stTanggalAktif;
    private String flagPerbaikan;

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

    private String channelId;
    private String os;

    public String getStrTanggalDari() {
        return strTanggalDari;
    }

    public void setStrTanggalDari(String strTanggalDari) {
        this.strTanggalDari = strTanggalDari;
    }

    public String getStrTanggalSelesai() {
        return strTanggalSelesai;
    }

    public void setStrTanggalSelesai(String strTanggalSelesai) {
        this.strTanggalSelesai = strTanggalSelesai;
    }

    public String getStSetelahResetCutiTahunan() {
        return stSetelahResetCutiTahunan;
    }

    public void setStSetelahResetCutiTahunan(String stSetelahResetCutiTahunan) {
        this.stSetelahResetCutiTahunan = stSetelahResetCutiTahunan;
    }

    public String getStSetelahResetCutiPanjang() {
        return stSetelahResetCutiPanjang;
    }

    public void setStSetelahResetCutiPanjang(String stSetelahResetCutiPanjang) {
        this.stSetelahResetCutiPanjang = stSetelahResetCutiPanjang;
    }

    public BigInteger getSetelahResetCutiTahunan() {
        return setelahResetCutiTahunan;
    }

    public void setSetelahResetCutiTahunan(BigInteger setelahResetCutiTahunan) {
        this.setelahResetCutiTahunan = setelahResetCutiTahunan;
    }

    public BigInteger getSetelahResetCutiPanjang() {
        return setelahResetCutiPanjang;
    }

    public void setSetelahResetCutiPanjang(BigInteger setelahResetCutiPanjang) {
        this.setelahResetCutiPanjang = setelahResetCutiPanjang;
    }

    public boolean isForMobile() {
        return forMobile;
    }

    public void setForMobile(boolean forMobile) {
        this.forMobile = forMobile;
    }

    public String getStTanggalAktif() {
        return stTanggalAktif;
    }

    public void setStTanggalAktif(String stTanggalAktif) {
        this.stTanggalAktif = stTanggalAktif;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }

    public java.sql.Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(java.sql.Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getCheckedValue() {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getSisaCutiPanjang() {
        return sisaCutiPanjang;
    }

    public void setSisaCutiPanjang(String sisaCutiPanjang) {
        this.sisaCutiPanjang = sisaCutiPanjang;
    }

    public String getSisaCutiTahunan() {
        return sisaCutiTahunan;
    }

    public void setSisaCutiTahunan(String sisaCutiTahunan) {
        this.sisaCutiTahunan = sisaCutiTahunan;
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

    public boolean isForReset() {
        return forReset;
    }

    public void setForReset(boolean forReset) {
        this.forReset = forReset;
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

    public boolean isCanCancel() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
    public BigInteger getJumlahCutihari() {
        return jumlahCutihari;
    }

    public void setJumlahCutihari(BigInteger jumlahCutihari) {
        this.jumlahCutihari = jumlahCutihari;
    }

    public BigInteger getSisaHariCuti() {
        return sisaHariCuti;
    }

    public void setSisaHariCuti(BigInteger sisaHariCuti) {
        this.sisaHariCuti = sisaHariCuti;
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
    public boolean isNotApprove() {
        return notApprove;
    }

    public void setNotApprove(boolean notApprove) {
        this.notApprove = notApprove;
    }

    public String getPosisiName() {
        return posisiName;
    }

    public void setPosisiName(String posisiName) {
        this.posisiName = posisiName;
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

    public boolean isSteptwo() {
        return steptwo;
    }

    public void setSteptwo(boolean steptwo) {
        this.steptwo = steptwo;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isStatusTanggal() {
        return statusTanggal;
    }

    public void setStatusTanggal(boolean statusTanggal) {
        this.statusTanggal = statusTanggal;
    }

    public boolean isCekatasan() {
        return cekatasan;
    }

    public void setCekatasan(boolean cekatasan) {
        this.cekatasan = cekatasan;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
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

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public boolean isCutiPegawaiClosed() {
        return cutiPegawaiClosed;
    }

    public void setCutiPegawaiClosed(boolean cutiPegawaiClosed) {
        this.cutiPegawaiClosed = cutiPegawaiClosed;
    }

    public boolean isCutiPegawaiApproveStatus() {
        return cutiPegawaiApproveStatus;
    }

    public void setCutiPegawaiApproveStatus(boolean cutiPegawaiApproveStatus) {
        this.cutiPegawaiApproveStatus = cutiPegawaiApproveStatus;
    }

    public String getTmpApprove() {
        return tmpApprove;
    }

    public void setTmpApprove(String tmpApprove) {
        this.tmpApprove = tmpApprove;
    }

    public String getCutiName() {
        return cutiName;
    }

    public void setCutiName(String cutiName) {
        this.cutiName = cutiName;
    }

    private BigInteger hasil;

    public BigInteger getHasil() {
        return hasil;
    }

    public void setHasil(BigInteger hasil) {
        this.hasil = hasil;
    }

    public boolean isCutiPegawaiApprove() {
        return cutiPegawaiApprove;
    }

    public void setCutiPegawaiApprove(boolean cutiPegawaiApprove) {
        this.cutiPegawaiApprove = cutiPegawaiApprove;
    }

    public String getPosisiId() {
        return posisiId;
    }

    public void setPosisiId(String posisiId) {
        this.posisiId = posisiId;
    }

    public String getCutiPegawaiId() {
        return cutiPegawaiId;
    }

    public void setCutiPegawaiId(String cutiPegawaiId) {
        this.cutiPegawaiId = cutiPegawaiId;
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

    public BigInteger getLamaHariCuti() {
        return lamaHariCuti;
    }

    public void setLamaHariCuti(BigInteger lamaHariCuti) {
        this.lamaHariCuti = lamaHariCuti;
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

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}