package com.neurix.hris.transaksi.lembur.model;

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
public class Lembur extends BaseModel {
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
    private String stTanggalAwal;
    private String stTanggalAkhir;
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
    private String branchId;
    private String self;
    private Date tanggalAwalSetuju;
    private Date tanggalAkhirSetuju;
    private String notApprovalNote;
    private Double jamRealisasi;
    private Double lamaHitungan;

    //RAKA-10MAR2021
    private boolean hakLembur = true;
    //RAKA-end

    private boolean lemburApprove=false;
    private boolean lemburEdit=true;
    private boolean lemburDelete=true;
    private boolean lemburApproveStatus=false;
    private boolean notApprove = false;
    private boolean terRealisasi = false;
    private boolean cekAdmin = false;
    private boolean sesuai=true;
    private boolean adaAbsen=false;
    private String tmpApprove;
    private String statusGilingName;
    private String tipeLemburName;
    private String changeDate;
    private String bagian;
    private String bulan;
    private String tahun;

    private String os;
    private String channelId;

    private boolean isMobile = false;
    private String nipUserLogin;

    public Double getLamaHitungan() {
        return lamaHitungan;
    }

    public void setLamaHitungan(Double lamaHitungan) {
        this.lamaHitungan = lamaHitungan;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public boolean isAdaAbsen() {
        return adaAbsen;
    }

    public void setAdaAbsen(boolean adaAbsen) {
        this.adaAbsen = adaAbsen;
    }

    public boolean isCekAdmin() {
        return cekAdmin;
    }

    public void setCekAdmin(boolean cekAdmin) {
        this.cekAdmin = cekAdmin;
    }

    public boolean isSesuai() {
        return sesuai;
    }

    public void setSesuai(boolean sesuai) {
        this.sesuai = sesuai;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Double getJamRealisasi() {
        return jamRealisasi;
    }

    public void setJamRealisasi(Double jamRealisasi) {
        this.jamRealisasi = jamRealisasi;
    }

    public boolean isTerRealisasi() {
        return terRealisasi;
    }

    public void setTerRealisasi(boolean terRealisasi) {
        this.terRealisasi = terRealisasi;
    }

    public boolean isLemburDelete() {
        return lemburDelete;
    }

    public void setLemburDelete(boolean lemburDelete) {
        this.lemburDelete = lemburDelete;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

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

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public boolean isNotApprove() {
        return notApprove;
    }

    public void setNotApprove(boolean notApprove) {
        this.notApprove = notApprove;
    }

    public boolean isLemburApproveStatus() {
        return lemburApproveStatus;
    }

    public void setLemburApproveStatus(boolean lemburApproveStatus) {
        this.lemburApproveStatus = lemburApproveStatus;
    }

    public String getTipeLemburName() {
        return tipeLemburName;
    }

    public void setTipeLemburName(String tipeLemburName) {
        this.tipeLemburName = tipeLemburName;
    }

    public String getStatusGilingName() {
        return statusGilingName;
    }

    public void setStatusGilingName(String statusGilingName) {
        this.statusGilingName = statusGilingName;
    }

    public String getTmpApprove() {
        return tmpApprove;
    }

    public void setTmpApprove(String tmpApprove) {
        this.tmpApprove = tmpApprove;
    }

    public boolean isLemburApprove() {
        return lemburApprove;
    }

    public void setLemburApprove(boolean lemburApprove) {
        this.lemburApprove = lemburApprove;
    }

    public boolean isLemburEdit() {
        return lemburEdit;
    }

    public void setLemburEdit(boolean lemburEdit) {
        this.lemburEdit = lemburEdit;
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

    public Boolean getMobile() {
        return isMobile;
    }

    public void setMobile(Boolean mobile) {
        isMobile = mobile;
    }

    public String getNipUserLogin() {
        return nipUserLogin;
    }

    public void setNipUserLogin(String nipUserLogin) {
        this.nipUserLogin = nipUserLogin;
    }

    public boolean isHakLembur() {
        return hakLembur;
    }

    public void setHakLembur(boolean hakLembur) {
        this.hakLembur = hakLembur;
    }
}
