package com.neurix.hris.master.biodata.model;

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
public class Biodata extends BaseModel {
    private String nip;
    private String namaPegawai;
    private String gelarDepan;
    private String gelarBelakang;
    private String noKtp;
    private String alamat;
    private String rtRw;
    private String kecamatan;
    private String noTelp;
    private String kabupaten;
    private String provinsi;
    private String stTanggalLahir;
    private java.util.Date tanggalLahir;
    private Date tanggalPensiun;
    private Date tanggalAktif;
    private Date tanggalMasuk;
    private Date tanggalAkhirKontrak;
    private String tempatLahir;
    private Date TanggalAktif;
    private String stTanggalAktif;
    private String stTanggalMasuk;
    private String stTanggalPensiun;
    private String tipePegawai;
    private String statusPegawai;
    private String statusPegawaiName;
    private String pathFoto;
    private String golongan;
    private String golonganName;
    private String fotoUpload;
    private String statusCaption;
    private String statusKeluarga;
    private String statusKeluargaName;
    private String keterangan;
    private String divisi;
    private String branch;
    private String branchName;
    private String tipePegawaiName;
    private String positionId;
    private String profesiId;
    private String positionId2;
    private String positionName;
    private String profesiName;
    private String divisiName;
    private String masaGiling;
    private String masaKerja;
    private String mt;
    private String from;
    private String pin;
    private String pjs;
    private String noAnggotaDapen;
    private String noBpjsKetenagakerjaan;
    private String noBpjsKetenagakerjaanPensiun;
    private String noBpjsKesehatan;
    private String namaBank;
    private String noRekBank;
    private String cabangBank;

    private String provinsiId;
    private String provinsiName;
    private String kabupatenId;
    private String kotaName;
    private String kecamatanId;
    private String kecamatanName;
    private String desaId;
    private String desaName;
    private int point;
    private int poinLebih;
    private String golonganId;

    private String rsKerjaSama;
    private String rsName;
    private String rsKelas;
    private String rsKelasName;
    private String flagZakat;

    private String gender;
    private String genderName;
    private String npwp;
    private String strukturGaji;
    private String strukturGaji2;
    private String gaji;
    private String statusGiling;
    private String statusGilingName;
    private String zakatName;
    private BigInteger jumlahAnak;
    private String danaPensiun;
    private String danaPensiunName;
    private String agama;
    private String shift;
    private String bagianId;
    private String bagianName;
    private String umur;
    private String flagMess;
    private String golonganDapen;
    private String golonganDapenNusindo;
    private String golonganDapenId;
    private String stMasaKerjaGol;
    private int masaKerjaGolongan;

    private String flagTunjSupervisi;
    private String flagTunjLokasi;
    private String flagTunjSiaga;
    private String flagTunjProfesional;

    private Date tanggalPraPensiun;
    private String stTanggalPraPensiun;

    public String getProfesiName() {
        return profesiName;
    }

    public void setProfesiName(String profesiName) {
        this.profesiName = profesiName;
    }

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public String getFlagTunjLokasi() {
        return flagTunjLokasi;
    }

    public void setFlagTunjLokasi(String flagTunjLokasi) {
        this.flagTunjLokasi = flagTunjLokasi;
    }

    public String getFlagTunjProfesional() {
        return flagTunjProfesional;
    }

    public void setFlagTunjProfesional(String flagTunjProfesional) {
        this.flagTunjProfesional = flagTunjProfesional;
    }

    public String getFlagTunjSiaga() {
        return flagTunjSiaga;
    }

    public void setFlagTunjSiaga(String flagTunjSiaga) {
        this.flagTunjSiaga = flagTunjSiaga;
    }

    public String getFlagTunjSupervisi() {
        return flagTunjSupervisi;
    }

    public void setFlagTunjSupervisi(String flagTunjSupervisi) {
        this.flagTunjSupervisi = flagTunjSupervisi;
    }

    public Date getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(Date tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public int getMasaKerjaGolongan() {
        return masaKerjaGolongan;
    }

    public void setMasaKerjaGolongan(int masaKerjaGolongan) {
        this.masaKerjaGolongan = masaKerjaGolongan;
    }

    public String getStMasaKerjaGol() {
        return stMasaKerjaGol;
    }

    public void setStMasaKerjaGol(String stMasaKerjaGol) {
        this.stMasaKerjaGol = stMasaKerjaGol;
    }

    public String getGolonganDapenId() {
        return golonganDapenId;
    }

    public void setGolonganDapenId(String golonganDapenId) {
        this.golonganDapenId = golonganDapenId;
    }

    public String getGolonganDapen() {
        return golonganDapen;
    }

    public void setGolonganDapen(String golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    public String getGolonganDapenNusindo() {
        return golonganDapenNusindo;
    }

    public void setGolonganDapenNusindo(String golonganDapenNusindo) {
        this.golonganDapenNusindo = golonganDapenNusindo;
    }

    public int getPoinLebih() {
        return poinLebih;
    }

    public void setPoinLebih(int poinLebih) {
        this.poinLebih = poinLebih;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getStTanggalMasuk() {
        return stTanggalMasuk;
    }

    public void setStTanggalMasuk(String stTanggalMasuk) {
        this.stTanggalMasuk = stTanggalMasuk;
    }

    public String getFlagMess() {
        return flagMess;
    }

    public void setFlagMess(String flagMess) {
        this.flagMess = flagMess;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }
    private String kelompokId;
    public String getKelompokId() {
        return kelompokId;
    }
    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }
    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getCabangBank() {
        return cabangBank;
    }

    public void setCabangBank(String cabangBank) {
        this.cabangBank = cabangBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNoRekBank() {
        return noRekBank;
    }

    public void setNoRekBank(String noRekBank) {
        this.noRekBank = noRekBank;
    }

    public String getNoAnggotaDapen() {
        return noAnggotaDapen;
    }

    public void setNoAnggotaDapen(String noAnggotaDapen) {
        this.noAnggotaDapen = noAnggotaDapen;
    }

    public String getNoBpjsKesehatan() {
        return noBpjsKesehatan;
    }

    public void setNoBpjsKesehatan(String noBpjsKesehatan) {
        this.noBpjsKesehatan = noBpjsKesehatan;
    }

    public String getNoBpjsKetenagakerjaan() {
        return noBpjsKetenagakerjaan;
    }

    public void setNoBpjsKetenagakerjaan(String noBpjsKetenagakerjaan) {
        this.noBpjsKetenagakerjaan = noBpjsKetenagakerjaan;
    }

    public String getNoBpjsKetenagakerjaanPensiun() {
        return noBpjsKetenagakerjaanPensiun;
    }

    public void setNoBpjsKetenagakerjaanPensiun(String noBpjsKetenagakerjaanPensiun) {
        this.noBpjsKetenagakerjaanPensiun = noBpjsKetenagakerjaanPensiun;
    }

    public Date getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(Date tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public String getStrukturGaji2() {
        return strukturGaji2;
    }

    public void setStrukturGaji2(String strukturGaji2) {
        this.strukturGaji2 = strukturGaji2;
    }

    public String getPositionId2() {
        return positionId2;
    }

    public void setPositionId2(String positionId2) {
        this.positionId2 = positionId2;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getStatusGilingName() {
        return statusGilingName;
    }

    public void setStatusGilingName(String statusGilingName) {
        this.statusGilingName = statusGilingName;
    }

    public String getStatusKeluargaName() {
        return statusKeluargaName;
    }

    public void setStatusKeluargaName(String statusKeluargaName) {
        this.statusKeluargaName = statusKeluargaName;
    }

    public String getStatusPegawaiName() {
        return statusPegawaiName;
    }

    public void setStatusPegawaiName(String statusPegawaiName) {
        this.statusPegawaiName = statusPegawaiName;
    }

    public String getZakatName() {
        return zakatName;
    }

    public void setZakatName(String zakatName) {
        this.zakatName = zakatName;
    }

    public String getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(String masaKerja) {
        this.masaKerja = masaKerja;
    }

    public String getDanaPensiunName() {
        return danaPensiunName;
    }

    public void setDanaPensiunName(String danaPensiunName) {
        this.danaPensiunName = danaPensiunName;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStTanggalPensiun() {
        return stTanggalPensiun;
    }

    public void setStTanggalPensiun(String stTanggalPensiun) {
        this.stTanggalPensiun = stTanggalPensiun;
    }

    public String getPjs() {
        return pjs;
    }

    public void setPjs(String pjs) {
        this.pjs = pjs;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getFlagZakat() {
        return flagZakat;
    }

    public void setFlagZakat(String flagZakat) {
        this.flagZakat = flagZakat;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getStrukturGaji() {
        return strukturGaji;
    }

    public void setStrukturGaji(String strukturGaji) {
        this.strukturGaji = strukturGaji;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public BigInteger getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(BigInteger jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMasaGiling() {
        return masaGiling;
    }

    public void setMasaGiling(String masaGiling) {
        this.masaGiling = masaGiling;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getRsKelasName() {
        return rsKelasName;
    }

    public void setRsKelasName(String rsKelasName) {
        this.rsKelasName = rsKelasName;
    }

    public String getRsKerjaSama() {
        return rsKerjaSama;
    }

    public void setRsKerjaSama(String rsKerjaSama) {
        this.rsKerjaSama = rsKerjaSama;
    }

    public String getRsKelas() {
        return rsKelas;
    }

    public void setRsKelas(String rsKelas) {
        this.rsKelas = rsKelas;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    private byte[] contentFile;

    public byte[] getContentFile() {
        return contentFile;
    }

    public void setContentFile(byte[] contentFile) {
        this.contentFile = contentFile;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getProvinsiName() {
        return provinsiName;
    }

    public void setProvinsiName(String provinsiName) {
        this.provinsiName = provinsiName;
    }

    public String getKabupatenId() {
        return kabupatenId;
    }

    public void setKabupatenId(String kabupatenId) {
        this.kabupatenId = kabupatenId;
    }

    public String getKotaName() {
        return kotaName;
    }

    public void setKotaName(String kotaName) {
        this.kotaName = kotaName;
    }

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getKecamatanName() {
        return kecamatanName;
    }

    public void setKecamatanName(String kecamatanName) {
        this.kecamatanName = kecamatanName;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getDesaName() {
        return desaName;
    }

    public void setDesaName(String desaName) {
        this.desaName = desaName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
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

    public String getGelarDepan() {
        return gelarDepan;
    }

    public void setGelarDepan(String gelarDepan) {
        this.gelarDepan = gelarDepan;
    }

    public String getGelarBelakang() {
        return gelarBelakang;
    }

    public void setGelarBelakang(String gelarBelakang) {
        this.gelarBelakang = gelarBelakang;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRtRw() {
        return rtRw;
    }

    public void setRtRw(String rtRw) {
        this.rtRw = rtRw;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getStTanggalLahir() {
        return stTanggalLahir;
    }

    public void setStTanggalLahir(String stTanggalLahir) {
        this.stTanggalLahir = stTanggalLahir;
    }

    public java.util.Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(java.util.Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalAktif() {
        return TanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        TanggalAktif = tanggalAktif;
    }

    public String getStTanggalAktif() {
        return stTanggalAktif;
    }

    public void setStTanggalAktif(String stTanggalAktif) {
        this.stTanggalAktif = stTanggalAktif;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getFotoUpload() {
        return fotoUpload;
    }

    public void setFotoUpload(String fotoUpload) {
        this.fotoUpload = fotoUpload;
    }

    public String getStatusCaption() {
        return statusCaption;
    }

    public void setStatusCaption(String statusCaption) {
        this.statusCaption = statusCaption;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStTanggalPraPensiun() {
        return stTanggalPraPensiun;
    }

    public void setStTanggalPraPensiun(String stTanggalPraPensiun) {
        this.stTanggalPraPensiun = stTanggalPraPensiun;
    }

    public Date getTanggalPraPensiun() {
        return tanggalPraPensiun;
    }

    public void setTanggalPraPensiun(Date tanggalPraPensiun) {
        this.tanggalPraPensiun = tanggalPraPensiun;
    }
}