package com.neurix.simrs.transaksi.periksalab.model;

import com.neurix.common.model.BaseModel;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class PeriksaLab extends BaseModel implements Serializable{

    private String idPeriksaLab;
    private String idDetailCheckup;
    private Date tanggalMasukLab;
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

    public Date getTanggalMasukLab() {
        return tanggalMasukLab;
    }

    public void setTanggalMasukLab(Date tanggalMasukLab) {
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