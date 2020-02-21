package com.neurix.simrs.transaksi.antrianonline.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

public class RegistrasiOnline extends BaseModel implements Serializable {

    private String noCheckupOnline;
    private String idPasien;
    private String nama;
    private String jenisKelamin;
    private String noKtp;
    private String tempatLahir;
    private Date tglLahir;
    private BigInteger desaId;
    private String jalan;
    private String suku;
    private String agama;
    private String profesi;
    private String noTelp;
    private String idJenisPeriksaPasien;
    private String branchId;
    private String urlKtp;
    private String stTglLahir;
    private String stDesaId;
    private Date tglCheckup;
    private String stTglCheckup;
    private String valid;

    private String namaPelayanan;
    private String namaDokter;
    private String tglDaftar;

    private String namaDesa;
    private String namaKecamatan;
    private String namaKota;
    private String namaProvinsi;

    private String kecamatanId;
    private String kotaId;
    private String provinsiId;

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getNamaDesa() {
        return namaDesa;
    }

    public void setNamaDesa(String namaDesa) {
        this.namaDesa = namaDesa;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public void setNamaProvinsi(String namaProvinsi) {
        this.namaProvinsi = namaProvinsi;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getTglDaftar() {
        return tglDaftar;
    }

    public void setTglDaftar(String tglDaftar) {
        this.tglDaftar = tglDaftar;
    }

    public String getStTglCheckup() {
        return stTglCheckup;
    }

    public void setStTglCheckup(String stTglCheckup) {
        this.stTglCheckup = stTglCheckup;
    }

    public Date getTglCheckup() {
        return tglCheckup;
    }

    public void setTglCheckup(Date tglCheckup) {
        this.tglCheckup = tglCheckup;
    }

    public String getStTglLahir() {
        return stTglLahir;
    }

    public void setStTglLahir(String stTglLahir) {
        this.stTglLahir = stTglLahir;
    }

    public String getStDesaId() {
        return stDesaId;
    }

    public void setStDesaId(String stDesaId) {
        this.stDesaId = stDesaId;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public BigInteger getDesaId() {
        return desaId;
    }

    public void setDesaId(BigInteger desaId) {
        this.desaId = desaId;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getSuku() {
        return suku;
    }

    public void setSuku(String suku) {
        this.suku = suku;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
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