package com.neurix.simrs.master.pasien.model;


import java.sql.Timestamp;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class Pasien {

    private String idPasien;
    private String nama;
    private String jenisKelamin;
    private String noKtp;
    private String noBpjs;
    private String tempatLahir;
    private String tglLahir;
    private String desaId;
    private String jalan;
    private String suku;
    private String agama;
    private String profesi;
    private String noTelp;
    private String urlKtp;
    private String alamat;
    private String provinsiId;
    private String kotaId;
    private String kecamatanId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String provinsi;
    private String kota;
    private String kecamatan;
    private String desa;

    private String password;
    private String email;

    private boolean isPasienLama = false;
    private String imgKtp;

    private Boolean disabledFingerData;

    private String status;
    private String msg;

    private String idPaket;
    private String idPelayanan;
    private String namaPaket;

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getDisabledFingerData() {
        return disabledFingerData;
    }

    public void setDisabledFingerData(Boolean disabledFingerData) {
        this.disabledFingerData = disabledFingerData;
    }

    public String getImgKtp() {
        return imgKtp;
    }

    public void setImgKtp(String imgKtp) {
        this.imgKtp = imgKtp;
    }

    public boolean getIsPasienLama() {
        return isPasienLama;
    }

    public void setIsPasienLama(boolean isPasienLama) {
        this.isPasienLama = isPasienLama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getNoBpjs() {
        return noBpjs;
    }

    public void setNoBpjs(String noBpjs) {
        this.noBpjs = noBpjs;
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

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
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

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    @Override
    public String toString() {
        return "Pasien{" +
                "idPasien='" + idPasien + '\'' +
                ", nama='" + nama + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                ", noKtp='" + noKtp + '\'' +
                ", noBpjs='" + noBpjs + '\'' +
                ", tempatLahir='" + tempatLahir + '\'' +
                ", tglLahir='" + tglLahir + '\'' +
                ", desaId='" + desaId + '\'' +
                ", jalan='" + jalan + '\'' +
                ", suku='" + suku + '\'' +
                ", agama='" + agama + '\'' +
                ", profesi='" + profesi + '\'' +
                ", noTelp='" + noTelp + '\'' +
                ", urlKtp='" + urlKtp + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", alamat='" + alamat + '\'' +
                ", provinsiId='" + provinsiId + '\'' +
                ", kotaId='" + kotaId + '\'' +
                ", kecamatanId='" + kecamatanId + '\'' +
                ", provinsi='" + provinsi + '\'' +
                ", kota='" + kota + '\'' +
                ", kecamatan='" + kecamatan + '\'' +
                ", desa='" + desa + '\'' +
                '}';
    }
}
