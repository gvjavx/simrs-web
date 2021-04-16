package com.neurix.simrs.transaksi.periksalab.model;

import com.neurix.common.model.BaseModel;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PeriksaLab extends BaseModel implements Serializable{

    private String idPeriksaLab;
    private String idDetailCheckup;
    private Timestamp tanggalMasukLab;
    private Timestamp tanggalSelesaiPeriksa;
    private String idDokterPengirim;
    private String idDokter;
    private String idPemeriksa;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String idLab;
    private String labName;
    private String statusPeriksa;
    private String statusPeriksaName;
    private String idKategoriLab;
    private String kategoriLabName;

    private String noCheckup;
    private String idPasien;
    private String namaPasien;
    private String idPelayanan;
    private String namaPelayanan;
    private String alamat;
    private String desaId;
    private String desa;
    private String kecamatan;
    private String kota;
    private String provinsi;

    private String jenisKelamin;
    private String tempatLahir;
    private String tglLahir;

    private String tempatTglLahir;
    private String idJenisPeriksa;
    private String jenisPeriksaPasien;
    private String nik;

    private String stTglFrom;
    private String stTglTo;

    private Boolean forRadiology = false;

    private String stDateFrom;
    private String stDateTo;

    private String urlKtp;

    private String approveFlag;

    private String stCreatedDate;

    private BigDecimal tarif;
    private String branchId;
    private String namaDokter;
    private String ttdDokter;
    private String ttdPetugas;
    private String namaPetugas;

    private String keterangan;
    private String metodePembayaran;

    private String urlImg;
    private String ttdPengirim;
    private String kategori;
    private String sipDokter;
    private String sipPengirim;
    private String idPengirim;
    private String dokterPengirim;

    private String isPending;
    private String statusBayar;
    private String diagnosa;

    private String idPetugas;
    private String idValidator;
    private String namaValidator;
    private String ttdValidator;
    private String isLuar;
    private String namaLabLuar;
    private BigDecimal tarifLabLuar;

    private String idPeriksaLabDetail;
    private String hasil;
    private String catatan;
    private String idKategoriPemeriksaan;

    private List<PeriksaLab> listLab = new ArrayList<>();
    private List<PeriksaLabDetail> detailLab = new ArrayList<>();

    private String namaLab;
    private String namaDokterPengirim;
    private String namaPemeriksaan;
    private String idHeaderPemeriksaan;
    private String idPemeriksaan;
    private String isJustLab;
    private String isPeriksaLuar;
    private String idDetailPemeriksaan;
    private String namaDetailPemeriksaan;

    private String keteranganAcuanL;
    private String keteranganAcuanP;
    private String keteranganHasil;
    private String satuan;
    private String isCatatan;
    private List<UploadHasilPemeriksaan> uploadDalam = new ArrayList<>();
    private List<UploadHasilPemeriksaan> uploadLuar = new ArrayList<>();
    private String umur;

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getIsCatatan() {
        return isCatatan;
    }

    public void setIsCatatan(String isCatatan) {
        this.isCatatan = isCatatan;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getKeteranganAcuanP() {
        return keteranganAcuanP;
    }

    public void setKeteranganAcuanP(String keteranganAcuanP) {
        this.keteranganAcuanP = keteranganAcuanP;
    }

    public String getKeteranganHasil() {
        return keteranganHasil;
    }

    public void setKeteranganHasil(String keteranganHasil) {
        this.keteranganHasil = keteranganHasil;
    }

    public String getKeteranganAcuanL() {
        return keteranganAcuanL;
    }

    public void setKeteranganAcuanL(String keteranganAcuanL) {
        this.keteranganAcuanL = keteranganAcuanL;
    }

    public String getIdDetailPemeriksaan() {
        return idDetailPemeriksaan;
    }

    public void setIdDetailPemeriksaan(String idDetailPemeriksaan) {
        this.idDetailPemeriksaan = idDetailPemeriksaan;
    }

    public String getNamaDetailPemeriksaan() {
        return namaDetailPemeriksaan;
    }

    public void setNamaDetailPemeriksaan(String namaDetailPemeriksaan) {
        this.namaDetailPemeriksaan = namaDetailPemeriksaan;
    }

    public String getIsPeriksaLuar() {
        return isPeriksaLuar;
    }

    public void setIsPeriksaLuar(String isPeriksaLuar) {
        this.isPeriksaLuar = isPeriksaLuar;
    }

    public String getIsJustLab() {
        return isJustLab;
    }

    public void setIsJustLab(String isJustLab) {
        this.isJustLab = isJustLab;
    }

    public String getIdHeaderPemeriksaan() {
        return idHeaderPemeriksaan;
    }

    public void setIdHeaderPemeriksaan(String idHeaderPemeriksaan) {
        this.idHeaderPemeriksaan = idHeaderPemeriksaan;
    }

    public String getIdPemeriksaan() {
        return idPemeriksaan;
    }

    public void setIdPemeriksaan(String idPemeriksaan) {
        this.idPemeriksaan = idPemeriksaan;
    }

    public String getNamaPemeriksaan() {
        return namaPemeriksaan;
    }

    public void setNamaPemeriksaan(String namaPemeriksaan) {
        this.namaPemeriksaan = namaPemeriksaan;
    }

    public String getNamaDokterPengirim() {
        return namaDokterPengirim;
    }

    public void setNamaDokterPengirim(String namaDokterPengirim) {
        this.namaDokterPengirim = namaDokterPengirim;
    }

    public String getNamaLab() {
        return namaLab;
    }

    public void setNamaLab(String namaLab) {
        this.namaLab = namaLab;
    }

    public List<PeriksaLab> getListLab() {
        return listLab;
    }

    public void setListLab(List<PeriksaLab> listLab) {
        this.listLab = listLab;
    }

    public List<PeriksaLabDetail> getDetailLab() {
        return detailLab;
    }

    public void setDetailLab(List<PeriksaLabDetail> detailLab) {
        this.detailLab = detailLab;
    }

    public String getIdKategoriPemeriksaan() {
        return idKategoriPemeriksaan;
    }

    public void setIdKategoriPemeriksaan(String idKategoriPemeriksaan) {
        this.idKategoriPemeriksaan = idKategoriPemeriksaan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getIdPeriksaLabDetail() {
        return idPeriksaLabDetail;
    }

    public void setIdPeriksaLabDetail(String idPeriksaLabDetail) {
        this.idPeriksaLabDetail = idPeriksaLabDetail;
    }

    public BigDecimal getTarifLabLuar() {
        return tarifLabLuar;
    }

    public void setTarifLabLuar(BigDecimal tarifLabLuar) {
        this.tarifLabLuar = tarifLabLuar;
    }

    public String getNamaLabLuar() {
        return namaLabLuar;
    }

    public void setNamaLabLuar(String namaLabLuar) {
        this.namaLabLuar = namaLabLuar;
    }

    public String getIsLuar() {
        return isLuar;
    }

    public void setIsLuar(String isLuar) {
        this.isLuar = isLuar;
    }

    public List<UploadHasilPemeriksaan> getUploadDalam() {
        return uploadDalam;
    }

    public void setUploadDalam(List<UploadHasilPemeriksaan> uploadDalam) {
        this.uploadDalam = uploadDalam;
    }

    public List<UploadHasilPemeriksaan> getUploadLuar() {
        return uploadLuar;
    }

    public void setUploadLuar(List<UploadHasilPemeriksaan> uploadLuar) {
        this.uploadLuar = uploadLuar;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getIdValidator() {
        return idValidator;
    }

    public void setIdValidator(String idValidator) {
        this.idValidator = idValidator;
    }

    public String getNamaValidator() {
        return namaValidator;
    }

    public void setNamaValidator(String namaValidator) {
        this.namaValidator = namaValidator;
    }

    public String getTtdValidator() {
        return ttdValidator;
    }

    public void setTtdValidator(String ttdValidator) {
        this.ttdValidator = ttdValidator;
    }

    private String isRead;
    private List<ItSimrsUploadHasilPemeriksaanEntity> uploadHasil = new ArrayList<>();

    public List<ItSimrsUploadHasilPemeriksaanEntity> getUploadHasil() {
        return uploadHasil;
    }

    public void setUploadHasil(List<ItSimrsUploadHasilPemeriksaanEntity> uploadHasil) {
        this.uploadHasil = uploadHasil;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public String getIsPending() {
        return isPending;
    }

    public void setIsPending(String isPending) {
        this.isPending = isPending;
    }

    public String getDokterPengirim() {
        return dokterPengirim;
    }

    public void setDokterPengirim(String dokterPengirim) {
        this.dokterPengirim = dokterPengirim;
    }

    public String getIdPengirim() {
        return idPengirim;
    }

    public void setIdPengirim(String idPengirim) {
        this.idPengirim = idPengirim;
    }

    public String getSipDokter() {
        return sipDokter;
    }

    public void setSipDokter(String sipDokter) {
        this.sipDokter = sipDokter;
    }

    public String getSipPengirim() {
        return sipPengirim;
    }

    public void setSipPengirim(String sipPengirim) {
        this.sipPengirim = sipPengirim;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTtdPengirim() {
        return ttdPengirim;
    }

    public void setTtdPengirim(String ttdPengirim) {
        this.ttdPengirim = ttdPengirim;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getTtdDokter() {
        return ttdDokter;
    }

    public void setTtdDokter(String ttdDokter) {
        this.ttdDokter = ttdDokter;
    }

    public String getTtdPetugas() {
        return ttdPetugas;
    }

    public void setTtdPetugas(String ttdPetugas) {
        this.ttdPetugas = ttdPetugas;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
    }

    public String getStDateFrom() {
        return stDateFrom;
    }

    public void setStDateFrom(String stDateFrom) {
        this.stDateFrom = stDateFrom;
    }

    public String getStDateTo() {
        return stDateTo;
    }

    public void setStDateTo(String stDateTo) {
        this.stDateTo = stDateTo;
    }

    public Boolean getForRadiology() {
        return forRadiology;
    }

    public void setForRadiology(Boolean forRadiology) {
        this.forRadiology = forRadiology;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
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

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getTempatTglLahir() {
        return tempatTglLahir;
    }

    public void setTempatTglLahir(String tempatTglLahir) {
        this.tempatTglLahir = tempatTglLahir;
    }

    public String getIdJenisPeriksa() {
        return idJenisPeriksa;
    }

    public void setIdJenisPeriksa(String idJenisPeriksa) {
        this.idJenisPeriksa = idJenisPeriksa;
    }

    public String getJenisPeriksaPasien() {
        return jenisPeriksaPasien;
    }

    public void setJenisPeriksaPasien(String jenisPeriksaPasien) {
        this.jenisPeriksaPasien = jenisPeriksaPasien;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getStTglFrom() {
        return stTglFrom;
    }

    public void setStTglFrom(String stTglFrom) {
        this.stTglFrom = stTglFrom;
    }

    public String getStTglTo() {
        return stTglTo;
    }

    public void setStTglTo(String stTglTo) {
        this.stTglTo = stTglTo;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }


    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getStatusPeriksa() {
        return statusPeriksa;
    }

    public void setStatusPeriksa(String statusPeriksa) {
        this.statusPeriksa = statusPeriksa;
    }

    public String getStatusPeriksaName() {
        return statusPeriksaName;
    }

    public void setStatusPeriksaName(String statusPeriksaName) {
        this.statusPeriksaName = statusPeriksaName;
    }

    public String getIdKategoriLab() {
        return idKategoriLab;
    }

    public void setIdKategoriLab(String idKategoriLab) {
        this.idKategoriLab = idKategoriLab;
    }

    public String getKategoriLabName() {
        return kategoriLabName;
    }

    public void setKategoriLabName(String kategoriLabName) {
        this.kategoriLabName = kategoriLabName;
    }

    public String getIdPeriksaLab() {
        return idPeriksaLab;
    }

    public void setIdPeriksaLab(String idPeriksaLab) {
        this.idPeriksaLab = idPeriksaLab;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Timestamp getTanggalMasukLab() {
        return tanggalMasukLab;
    }

    public void setTanggalMasukLab(Timestamp tanggalMasukLab) {
        this.tanggalMasukLab = tanggalMasukLab;
    }

    public Timestamp getTanggalSelesaiPeriksa() {
        return tanggalSelesaiPeriksa;
    }

    public void setTanggalSelesaiPeriksa(Timestamp tanggalSelesaiPeriksa) {
        this.tanggalSelesaiPeriksa = tanggalSelesaiPeriksa;
    }

    public String getIdDokterPengirim() {
        return idDokterPengirim;
    }

    public void setIdDokterPengirim(String idDokterPengirim) {
        this.idDokterPengirim = idDokterPengirim;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPemeriksa() {
        return idPemeriksa;
    }

    public void setIdPemeriksa(String idPemeriksa) {
        this.idPemeriksa = idPemeriksa;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}