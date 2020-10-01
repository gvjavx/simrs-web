package com.neurix.hris.transaksi.absensi.model;

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
public class AbsensiPegawaiEntity implements Serializable {
    private String absensiPegawaiId;
    private String nip;
    private Date tanggal;
    private String jamMasuk;
    private String jamKeluar;
    private String statusAbsensi;
    private String lembur;
    private String ijin;
    private String branchId;
    private String jenisLembur;
    private Double lamaLembur;
    private Double jamLembur;
    private Double biayaLembur;
    private String tipeHari;
    private Double realisasiJamLembur;
    private String keterangan;
    private String flagUangMakan;
    private String approvalFlag;
    private Timestamp approvalDate;
    private String approvalId;
    private String approvalName;
    private String notApprovalNote;
    private String keteranganSesuaikan;
    private String sesuaikanFlag;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String jamMasukDb;
    private String jamPulangDb;
    private String stTanggal;
    private String nama;
    private String pin;
    private String statusName;
    private String tipePegawai;
    private String statusGiling;
    private String jenisLemburName;
    private String biayaLemburName;
    private String mulaiIzin;
    private String pulangIzin;
    private String awalLembur;
    private String selesaiLembur;
    private String jamPulang;
    private String tipeHariName;
    private String divisi;
    private String saved;
    private Double pengajuanLembur;
    private Integer point;
    private String golongan;

    private String statusAbsensi2;
    private String jamMasuk2;
    private String jamPulang2;
    private String statusName2;
    private String statusAbsensiOnCall;
    private String jamMasukOnCall;
    private String jamPulangOnCall;
    private String statusNameOnCall;
    private String flagPanggil;
    private String flagCutiGantiHari;

    private BigInteger telat;

    public BigInteger getTelat() {
        return telat;
    }

    public void setTelat(BigInteger telat) {
        this.telat = telat;
    }

    public String getFlagCutiGantiHari() {
        return flagCutiGantiHari;
    }

    public void setFlagCutiGantiHari(String flagCutiGantiHari) {
        this.flagCutiGantiHari = flagCutiGantiHari;
    }

    public String getStatusAbsensi2() {
        return statusAbsensi2;
    }

    public void setStatusAbsensi2(String statusAbsensi2) {
        this.statusAbsensi2 = statusAbsensi2;
    }

    public String getJamMasuk2() {
        return jamMasuk2;
    }

    public void setJamMasuk2(String jamMasuk2) {
        this.jamMasuk2 = jamMasuk2;
    }

    public String getJamPulang2() {
        return jamPulang2;
    }

    public void setJamPulang2(String jamPulang2) {
        this.jamPulang2 = jamPulang2;
    }

    public String getStatusName2() {
        return statusName2;
    }

    public void setStatusName2(String statusName2) {
        this.statusName2 = statusName2;
    }

    public String getStatusAbsensiOnCall() {
        return statusAbsensiOnCall;
    }

    public void setStatusAbsensiOnCall(String statusAbsensiOnCall) {
        this.statusAbsensiOnCall = statusAbsensiOnCall;
    }

    public String getJamMasukOnCall() {
        return jamMasukOnCall;
    }

    public void setJamMasukOnCall(String jamMasukOnCall) {
        this.jamMasukOnCall = jamMasukOnCall;
    }

    public String getJamPulangOnCall() {
        return jamPulangOnCall;
    }

    public void setJamPulangOnCall(String jamPulangOnCall) {
        this.jamPulangOnCall = jamPulangOnCall;
    }

    public String getStatusNameOnCall() {
        return statusNameOnCall;
    }

    public void setStatusNameOnCall(String statusNameOnCall) {
        this.statusNameOnCall = statusNameOnCall;
    }

    public String getFlagPanggil() {
        return flagPanggil;
    }

    public void setFlagPanggil(String flagPanggil) {
        this.flagPanggil = flagPanggil;
    }

    public String getKeteranganSesuaikan() {
        return keteranganSesuaikan;
    }

    public void setKeteranganSesuaikan(String keteranganSesuaikan) {
        this.keteranganSesuaikan = keteranganSesuaikan;
    }

    public String getSesuaikanFlag() {
        return sesuaikanFlag;
    }

    public void setSesuaikanFlag(String sesuaikanFlag) {
        this.sesuaikanFlag = sesuaikanFlag;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public Double getPengajuanLembur() {
        return pengajuanLembur;
    }

    public void setPengajuanLembur(Double pengajuanLembur) {
        this.pengajuanLembur = pengajuanLembur;
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

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlagUangMakan() {
        return flagUangMakan;
    }

    public void setFlagUangMakan(String flagUangMakan) {
        this.flagUangMakan = flagUangMakan;
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getTipeHariName() {
        return tipeHariName;
    }

    public void setTipeHariName(String tipeHariName) {
        this.tipeHariName = tipeHariName;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
    }

    public String getJamMasukDb() {
        return jamMasukDb;
    }

    public void setJamMasukDb(String jamMasukDb) {
        this.jamMasukDb = jamMasukDb;
    }

    public String getJamPulangDb() {
        return jamPulangDb;
    }

    public void setJamPulangDb(String jamPulangDb) {
        this.jamPulangDb = jamPulangDb;
    }

    public String getMulaiIzin() {
        return mulaiIzin;
    }

    public void setMulaiIzin(String mulaiIzin) {
        this.mulaiIzin = mulaiIzin;
    }

    public String getPulangIzin() {
        return pulangIzin;
    }

    public void setPulangIzin(String pulangIzin) {
        this.pulangIzin = pulangIzin;
    }

    public String getAwalLembur() {
        return awalLembur;
    }

    public void setAwalLembur(String awalLembur) {
        this.awalLembur = awalLembur;
    }

    public String getSelesaiLembur() {
        return selesaiLembur;
    }

    public void setSelesaiLembur(String selesaiLembur) {
        this.selesaiLembur = selesaiLembur;
    }

    public Double getRealisasiJamLembur() {
        return realisasiJamLembur;
    }

    public void setRealisasiJamLembur(Double realisasiJamLembur) {
        this.realisasiJamLembur = realisasiJamLembur;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }

    public String getBiayaLemburName() {
        return biayaLemburName;
    }

    public void setBiayaLemburName(String biayaLemburName) {
        this.biayaLemburName = biayaLemburName;
    }

    public String getJenisLemburName() {
        return jenisLemburName;
    }

    public void setJenisLemburName(String jenisLemburName) {
        this.jenisLemburName = jenisLemburName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getJenisLembur() {
        return jenisLembur;
    }

    public void setJenisLembur(String jenisLembur) {
        this.jenisLembur = jenisLembur;
    }

    public Double getLamaLembur() {
        return lamaLembur;
    }

    public void setLamaLembur(Double lamaLembur) {
        this.lamaLembur = lamaLembur;
    }

    public Double getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(Double jamLembur) {
        this.jamLembur = jamLembur;
    }

    public Double getBiayaLembur() {
        return biayaLembur;
    }

    public void setBiayaLembur(Double biayaLembur) {
        this.biayaLembur = biayaLembur;
    }

    public String getAbsensiPegawaiId() {
        return absensiPegawaiId;
    }

    public void setAbsensiPegawaiId(String absensiPegawaiId) {
        this.absensiPegawaiId = absensiPegawaiId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getStatusAbsensi() {
        return statusAbsensi;
    }

    public void setStatusAbsensi(String statusAbsensi) {
        this.statusAbsensi = statusAbsensi;
    }

    public String getLembur() {
        return lembur;
    }

    public void setLembur(String lembur) {
        this.lembur = lembur;
    }

    public String getIjin() {
        return ijin;
    }

    public void setIjin(String ijin) {
        this.ijin = ijin;
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
}
