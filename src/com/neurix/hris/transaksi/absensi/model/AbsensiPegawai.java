package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
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
public class AbsensiPegawai extends BaseModel {
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
    private String stBiayaLembur;
    private String tipeHari;
    private String tipeHariName;
    private Double realisasiJamLembur;
    private String stRealisasiJamLembur;
    private Double pengajuanLembur;

    private String keterangan;
    private String flagUangMakan;
    private String approvalFlag;
    private Timestamp approvalDate;
    private String approvalId;
    private String approvalName;
    private String notapprovalNote;
    private String keteranganSesuaikan;
    private String sesuaikanFlag;

    private String userIdActive;
    private String userNameActive;
    private String jamMasukDb;
    private String jamPulangDb;
    private String pin;
    private String jabatan;
    private String divisi;

    private String unit;
    private String stTanggal;
    private String stTanggalAkhir;
    private String nama;
    private String statusName;
    private String tipePegawai;
    private String statusGiling;
    private String jumlahLembur;
    private String mulaiIzin;
    private String pulangIzin;
    private String awalLembur;
    private String selesaiLembur;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private String divisiId;
    private String posisiId;
    private String jamPulang;
    private String absensi;
    private String stUangmakan;
    private String checkedValue;
    private Double lemburPerJam;
    private boolean clear;
    private boolean noted;
    private String positionName;
    private boolean absensiApprove = false;
    private boolean notApprove = false;
    private boolean cekAdmin = false;
    private String bagian;
    private Integer noUrutBagian;
    private Double hariKerja15;
    private Double hariKerja20;
    private Double hariLibur20;
    private Double hariLibur30;
    private Double hariLibur40;
    private Double biayaLemburPerjam;
    private Date tanggalAwal;
    private Date tanggalAkhir;
    private String stJamLembur;
    private String stBiayaLemburPerjam;
    private String stLamaLembur;
    private String stHariKerja15;
    private String stHariKerja20;
    private String stHariLibur20;
    private String stHariLibur30;
    private String stHariLibur40;
    private String no;
    private String sJumlahHariKerja;
    private String sJumlahHariLibur;
    private String terlambatKurang60;
    private String terlambatLebih60;
    private String tidakAbsenMasuk;
    private String tidakAbsenPulang;
    private String cekPegawaiStatus;

    private String statusAbsensi2;
    private String jamMasuk2;
    private String jamPulang2;
    private String statusName2;
    private String statusAbsensiOnCall;
    private String jamMasukOnCall;
    private String jamPulangOnCall;
    private String statusNameOnCall;
    private String flagPanggil;
    private String branchIdUser;
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

    public String getBranchIdUser() {
        return branchIdUser;
    }

    public void setBranchIdUser(String branchIdUser) {
        this.branchIdUser = branchIdUser;
    }

    public String getFlagPanggil() {
        return flagPanggil;
    }

    public void setFlagPanggil(String flagPanggil) {
        this.flagPanggil = flagPanggil;
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

    public String getStatusName2() {
        return statusName2;
    }

    public void setStatusName2(String statusName2) {
        this.statusName2 = statusName2;
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

    public Double getPengajuanLembur() {
        return pengajuanLembur;
    }

    public void setPengajuanLembur(Double pengajuanLembur) {
        this.pengajuanLembur = pengajuanLembur;
    }

    private java.util.Date tanggalUtil;

    public String getTipeHariName() {
        return tipeHariName;
    }

    public void setTipeHariName(String tipeHariName) {
        this.tipeHariName = tipeHariName;
    }

    public java.util.Date getTanggalUtil() {
        return tanggalUtil;
    }

    public void setTanggalUtil(java.util.Date tanggalUtil) {
        this.tanggalUtil = tanggalUtil;
    }
    private boolean isMobile = false;

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public String getCekPegawaiStatus() {
        return cekPegawaiStatus;
    }

    public void setCekPegawaiStatus(String cekPegawaiStatus) {
        this.cekPegawaiStatus = cekPegawaiStatus;
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

    public String getStRealisasiJamLembur() {
        return stRealisasiJamLembur;
    }

    public void setStRealisasiJamLembur(String stRealisasiJamLembur) {
        this.stRealisasiJamLembur = stRealisasiJamLembur;
    }

    public String getTerlambatKurang60() {
        return terlambatKurang60;
    }

    public void setTerlambatKurang60(String terlambatKurang60) {
        this.terlambatKurang60 = terlambatKurang60;
    }

    public String getTerlambatLebih60() {
        return terlambatLebih60;
    }

    public void setTerlambatLebih60(String terlambatLebih60) {
        this.terlambatLebih60 = terlambatLebih60;
    }

    public String getTidakAbsenMasuk() {
        return tidakAbsenMasuk;
    }

    public void setTidakAbsenMasuk(String tidakAbsenMasuk) {
        this.tidakAbsenMasuk = tidakAbsenMasuk;
    }

    public String getTidakAbsenPulang() {
        return tidakAbsenPulang;
    }

    public void setTidakAbsenPulang(String tidakAbsenPulang) {
        this.tidakAbsenPulang = tidakAbsenPulang;
    }

    private boolean cekMangkir=false;

    public boolean isCekAdmin() {
        return cekAdmin;
    }

    public void setCekAdmin(boolean cekAdmin) {
        this.cekAdmin = cekAdmin;
    }


    public boolean isCekMangkir() {
        return cekMangkir;
    }

    public void setCekMangkir(boolean cekMangkir) {
        this.cekMangkir = cekMangkir;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getsJumlahHariKerja() {
        return sJumlahHariKerja;
    }

    public void setsJumlahHariKerja(String sJumlahHariKerja) {
        this.sJumlahHariKerja = sJumlahHariKerja;
    }

    public String getsJumlahHariLibur() {
        return sJumlahHariLibur;
    }

    public void setsJumlahHariLibur(String sJumlahHariLibur) {
        this.sJumlahHariLibur = sJumlahHariLibur;
    }

    public String getStHariKerja15() {
        return stHariKerja15;
    }

    public void setStHariKerja15(String stHariKerja15) {
        this.stHariKerja15 = stHariKerja15;
    }

    public String getStHariKerja20() {
        return stHariKerja20;
    }

    public void setStHariKerja20(String stHariKerja20) {
        this.stHariKerja20 = stHariKerja20;
    }

    public String getStHariLibur20() {
        return stHariLibur20;
    }

    public void setStHariLibur20(String stHariLibur20) {
        this.stHariLibur20 = stHariLibur20;
    }

    public String getStHariLibur30() {
        return stHariLibur30;
    }

    public void setStHariLibur30(String stHariLibur30) {
        this.stHariLibur30 = stHariLibur30;
    }

    public String getStHariLibur40() {
        return stHariLibur40;
    }

    public void setStHariLibur40(String stHariLibur40) {
        this.stHariLibur40 = stHariLibur40;
    }

    public String getStLamaLembur() {
        return stLamaLembur;
    }

    public void setStLamaLembur(String stLamaLembur) {
        this.stLamaLembur = stLamaLembur;
    }

    public Double getHariKerja15() {
        return hariKerja15;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }
    public String getStJamLembur() {
        return stJamLembur;
    }

    public void setStJamLembur(String stJamLembur) {
        this.stJamLembur = stJamLembur;
    }

    public String getStBiayaLemburPerjam() {
        return stBiayaLemburPerjam;
    }

    public void setStBiayaLemburPerjam(String stBiayaLemburPerjam) {
        this.stBiayaLemburPerjam = stBiayaLemburPerjam;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public void setHariKerja15(Double hariKerja15) {
        this.hariKerja15 = hariKerja15;
    }

    public Double getHariKerja20() {
        return hariKerja20;
    }

    public void setHariKerja20(Double hariKerja20) {
        this.hariKerja20 = hariKerja20;
    }

    public Double getHariLibur20() {
        return hariLibur20;
    }

    public void setHariLibur20(Double hariLibur20) {
        this.hariLibur20 = hariLibur20;
    }

    public Double getHariLibur30() {
        return hariLibur30;
    }

    public void setHariLibur30(Double hariLibur30) {
        this.hariLibur30 = hariLibur30;
    }

    public Double getHariLibur40() {
        return hariLibur40;
    }

    public void setHariLibur40(Double hariLibur40) {
        this.hariLibur40 = hariLibur40;
    }

    public Double getBiayaLemburPerjam() {
        return biayaLemburPerjam;
    }

    public void setBiayaLemburPerjam(Double biayaLemburPerjam) {
        this.biayaLemburPerjam = biayaLemburPerjam;
    }

    public Integer getNoUrutBagian() {
        return noUrutBagian;
    }

    public void setNoUrutBagian(Integer noUrutBagian) {
        this.noUrutBagian = noUrutBagian;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
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

    public String getNotapprovalNote() {
        return notapprovalNote;
    }

    public void setNotapprovalNote(String notapprovalNote) {
        this.notapprovalNote = notapprovalNote;
    }

    public boolean isAbsensiApprove() {
        return absensiApprove;
    }

    public void setAbsensiApprove(boolean absensiApprove) {
        this.absensiApprove = absensiApprove;
    }

    public boolean isNotApprove() {
        return notApprove;
    }

    public void setNotApprove(boolean notApprove) {
        this.notApprove = notApprove;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isNoted() {
        return noted;
    }

    public void setNoted(boolean noted) {
        this.noted = noted;
    }

    public String getFlagUangMakan() {
        return flagUangMakan;
    }

    public void setFlagUangMakan(String flagUangMakan) {
        this.flagUangMakan = flagUangMakan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCheckedValue() {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }

    public String getStUangmakan() {
        return stUangmakan;
    }

    public void setStUangmakan(String stUangmakan) {
        this.stUangmakan = stUangmakan;
    }

    public String getAbsensi() {
        return absensi;
    }

    public void setAbsensi(String absensi) {
        this.absensi = absensi;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
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

    public String getJumlahLembur() {
        return jumlahLembur;
    }

    public void setJumlahLembur(String jumlahLembur) {
        this.jumlahLembur = jumlahLembur;
    }

    public String getStBiayaLembur() {
        return stBiayaLembur;
    }

    public void setStBiayaLembur(String stBiayaLembur) {
        this.stBiayaLembur = stBiayaLembur;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getStTanggalAkhir() {
        return stTanggalAkhir;
    }

    public void setStTanggalAkhir(String stTanggalAkhir) {
        this.stTanggalAkhir = stTanggalAkhir;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
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

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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

    public Double getLemburPerJam() {
        return lemburPerJam;
    }

    public void setLemburPerJam(Double lemburPerJam) {
        this.lemburPerJam = lemburPerJam;
    }
}