package com.neurix.hris.master.biodata.model;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.payrollDanaPensiun.model.ImPayrollDanaPensiunEntity;
import com.neurix.hris.master.provinsi.model.ImDesaEntity;
import com.neurix.hris.master.provinsi.model.ImKecamatanEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class ImBiodataEntity implements Serializable {

    private String branchId;
    private String divisiId;
    private String posisiId;
    private String profesiId;
    private int batih;
    private int noAnggota;

    private String nip;
    private String namaPegawai;
    private String gelarDepan;
    private String gelarBelakang;
    private String noKtp;
    private String alamat;
    private String rtRw;
    private String desa;
    private String kecamatan;
    private String noTelp;
    private String provinsi;
    private String stTanggalLahir;
    private java.util.Date tanggalLahir;
    private String tempatLahir;
    private Date TanggalAktif;
    private Date TanggalMasuk;
    private Date TanggalPensiun;
    private String statusPegawai;
    private String noAnggotaDapen;
    private String noBpjsKetenagakerjaan;
    private String noBpjsKetenagakerjaanPensiun;
    private String noBpjsKesehatan;
    private String namaBank;
    private String noRekBank;
    private String cabangBank;
    private String bagianId;
    private String bagianName;
    private BigDecimal persentasiHutangPph;
    private String umur;
    private String flagMess;
    private String golonganDapenId;
    private String stMasaKerjaGol;
    private Integer masaKerjaGolongan;
    private String branchIdTerakhir;
    private String positionIdTerakhir;

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public String getBranchIdTerakhir() {
        return branchIdTerakhir;
    }

    public void setBranchIdTerakhir(String branchIdTerakhir) {
        this.branchIdTerakhir = branchIdTerakhir;
    }

    public String getPositionIdTerakhir() {
        return positionIdTerakhir;
    }

    public void setPositionIdTerakhir(String positionIdTerakhir) {
        this.positionIdTerakhir = positionIdTerakhir;
    }

    public Integer getMasaKerjaGolongan() {
        return masaKerjaGolongan;
    }

    public void setMasaKerjaGolongan(Integer masaKerjaGolongan) {
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

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public Date getTanggalMasuk() {
        return TanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        TanggalMasuk = tanggalMasuk;
    }

    public String getFlagMess() {
        return flagMess;
    }

    public void setFlagMess(String flagMess) {
        this.flagMess = flagMess;
    }

    public int getNoAnggota() {
        return noAnggota;
    }

    public void setNoAnggota(int noAnggota) {
        this.noAnggota = noAnggota;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public int getBatih() {
        return batih;
    }

    public void setBatih(int batih) {
        this.batih = batih;
    }

    public String getCabangBank() {
        return cabangBank;
    }

    public void setCabangBank(String cabangBank) {
        this.cabangBank = cabangBank;
    }

    public BigDecimal getPersentasiHutangPph() {
        return persentasiHutangPph;
    }

    public void setPersentasiHutangPph(BigDecimal persentasiHutangPph) {
        this.persentasiHutangPph = persentasiHutangPph;
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

    private String masaGiling;
    private BigInteger jumlahAnak;
//    private String golonganId;

    private int point;
    private String gender;

    private String tipePegawai;
    private String tipePegawaiName;
    private String golongan;
    private String golonganName;
//    private String statusPegawai;
    private String statusPegawaiName;
    private String statusKeluarga;
    private String fotoUpload;
    private String statusCaption;
    private String keterangan;
    private String provinsiId;
    private String provinsiName;
    private String kotaId;
    private String kotaName;
    private String kecamatanId;
    private String kecamatanName;
    private String desaId;
    private String desaName;
    private String pin;
    private String danaPensiun;
    private String strukturGaji;
    private BigDecimal gaji;
    private String shift;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String statusGiling;
    private String noSkAktif;
    private String zakatProfesi;
    private String agama;
    private String npwp;
    private String mt;
    private String golonganDapen;
    private String golonganDapenNusindo;
    private String pjs;
    private Date tanggalAkhirKontrak;
    private Date tanggalMenikah;
    private int poinLebih;


    public String getPjs() {
        return pjs;
    }

    public void setPjs(String pjs) {
        this.pjs = pjs;
    }

    public String getGolonganDapenNusindo() {
        return golonganDapenNusindo;
    }

    public void setGolonganDapenNusindo(String golonganDapenNusindo) {
        this.golonganDapenNusindo = golonganDapenNusindo;
    }

    public String getGolonganDapen() {
        return golonganDapen;
    }

    public void setGolonganDapen(String golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    private ImPayrollDanaPensiunEntity imDanaPensiunEntity ;
    private ImProvinsiEntity imProvinsiEntity ;
    private ImPosition imPosition;
    private ImKotaEntity imKotaEntity;
    private ImKecamatanEntity imKecamatanEntity;
    private ImDesaEntity imDesaEntity ;
    private ImGolonganEntity imGolonganEntity;
    private ItPersonilPositionEntity itPersonilPositionEntity;

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public ImPayrollDanaPensiunEntity getImDanaPensiunEntity() {
        return imDanaPensiunEntity;
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

    public void setImDanaPensiunEntity(ImPayrollDanaPensiunEntity imDanaPensiunEntity) {
        this.imDanaPensiunEntity = imDanaPensiunEntity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public Date getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(Date tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public Date getTanggalMenikah() {
        return tanggalMenikah;
    }

    public void setTanggalMenikah(Date tanggalMenikah) {
        this.tanggalMenikah = tanggalMenikah;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getNoSkAktif() {
        return noSkAktif;
    }

    public void setNoSkAktif(String noSkAktif) {
        this.noSkAktif = noSkAktif;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getZakatProfesi() {
        return zakatProfesi;
    }

    public void setZakatProfesi(String zakatProfesi) {
        this.zakatProfesi = zakatProfesi;
    }

    public int getPoinLebih() {
        return poinLebih;
    }

    public void setPoinLebih(int poinLebih) {
        this.poinLebih = poinLebih;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public String getStrukturGaji() {
        return strukturGaji;
    }

    public void setStrukturGaji(String strukturGaji) {
        this.strukturGaji = strukturGaji;
    }

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigInteger getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(BigInteger jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getMasaGiling() {
        return masaGiling;
    }

    public void setMasaGiling(String masaGiling) {
        this.masaGiling = masaGiling;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ItPersonilPositionEntity getItPersonilPositionEntity() {
        return itPersonilPositionEntity;
    }

    public void setItPersonilPositionEntity(ItPersonilPositionEntity itPersonilPositionEntity) {
        this.itPersonilPositionEntity = itPersonilPositionEntity;
    }

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
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

    public String getStatusPegawaiName() {
        return statusPegawaiName;
    }

    public void setStatusPegawaiName(String statusPegawaiName) {
        this.statusPegawaiName = statusPegawaiName;
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
//
//    public String getStatusPegawai() {
//        return statusPegawai;
//    }
//
//    public void setStatusPegawai(String statusPegawai) {
//        this.statusPegawai = statusPegawai;
//    }

//    public String getGolonganId() {
//        return golonganId;
//    }
//
//    public void setGolonganId(String golonganId) {
//        this.golonganId = golonganId;
//    }

    private ImHrisTipePegawai imHrisTipePegawai ;

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImHrisTipePegawai getImHrisTipePegawai() {
        return imHrisTipePegawai;
    }

    public void setImHrisTipePegawai(ImHrisTipePegawai imHrisTipePegawai) {
        this.imHrisTipePegawai = imHrisTipePegawai;
    }

    public ImProvinsiEntity getImProvinsiEntity() {
        return imProvinsiEntity;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public void setImProvinsiEntity(ImProvinsiEntity imProvinsiEntity) {
        this.imProvinsiEntity = imProvinsiEntity;
    }

    public ImKotaEntity getImKotaEntity() {
        return imKotaEntity;
    }

    public void setImKotaEntity(ImKotaEntity imKotaEntity) {
        this.imKotaEntity = imKotaEntity;
    }

    public ImKecamatanEntity getImKecamatanEntity() {
        return imKecamatanEntity;
    }

    public void setImKecamatanEntity(ImKecamatanEntity imKecamatanEntity) {
        this.imKecamatanEntity = imKecamatanEntity;
    }

    public ImDesaEntity getImDesaEntity() {
        return imDesaEntity;
    }

    public void setImDesaEntity(ImDesaEntity imDesaEntity) {
        this.imDesaEntity = imDesaEntity;
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

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
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

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
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

    public Date getTanggalPensiun() {
        return TanggalPensiun;
    }

    public void setTanggalPensiun(Date tanggalPensiun) {
        TanggalPensiun = tanggalPensiun;
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