package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.model.BaseModel;

public class AbsensiTriwulanDTO extends BaseModel {
    private String no;
    private String nip;
    private String nama;
    private String jabatan;
    private String bagian;
    private String jatahCuti;
    private String hariKerja;
    private String masukKurangJadwal;
    private String terlambatKurang60;
    private String terlambatLebih60;
    private String masukKerjaKantor;
    private String keteranganTerlambat;
    private String tidakAbsenMasuk;
    private String tidakAbsenPulang;
    private String jmlSakit;
    private String jmlCuti;
    private String jmlDinas;
    private String jmlLain2;
    private String jmlTanpaKet;
    private String totalTidakMasuk;
    private String ketLain2;
    private String tanpaKeterangan;
    private String pulangTidakSesuai;
    private String bagianName;

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }


    public String getTanpaKeterangan() {
        return tanpaKeterangan;
    }

    public void setTanpaKeterangan(String tanpaKeterangan) {
        this.tanpaKeterangan = tanpaKeterangan;
    }

    public String getPulangTidakSesuai() {
        return pulangTidakSesuai;
    }

    public void setPulangTidakSesuai(String pulangTidakSesuai) {
        this.pulangTidakSesuai = pulangTidakSesuai;
    }

    public String getHariKerja() {
        return hariKerja;
    }

    public void setHariKerja(String hariKerja) {
        this.hariKerja = hariKerja;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getJatahCuti() {
        return jatahCuti;
    }

    public void setJatahCuti(String jatahCuti) {
        this.jatahCuti = jatahCuti;
    }

    public String getMasukKurangJadwal() {
        return masukKurangJadwal;
    }

    public void setMasukKurangJadwal(String masukKurangJadwal) {
        this.masukKurangJadwal = masukKurangJadwal;
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

    public String getMasukKerjaKantor() {
        return masukKerjaKantor;
    }

    public void setMasukKerjaKantor(String masukKerjaKantor) {
        this.masukKerjaKantor = masukKerjaKantor;
    }

    public String getKeteranganTerlambat() {
        return keteranganTerlambat;
    }

    public void setKeteranganTerlambat(String keteranganTerlambat) {
        this.keteranganTerlambat = keteranganTerlambat;
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

    public String getJmlSakit() {
        return jmlSakit;
    }

    public void setJmlSakit(String jmlSakit) {
        this.jmlSakit = jmlSakit;
    }

    public String getJmlCuti() {
        return jmlCuti;
    }

    public void setJmlCuti(String jmlCuti) {
        this.jmlCuti = jmlCuti;
    }

    public String getJmlDinas() {
        return jmlDinas;
    }

    public void setJmlDinas(String jmlDinas) {
        this.jmlDinas = jmlDinas;
    }

    public String getJmlLain2() {
        return jmlLain2;
    }

    public void setJmlLain2(String jmlLain2) {
        this.jmlLain2 = jmlLain2;
    }

    public String getJmlTanpaKet() {
        return jmlTanpaKet;
    }

    public void setJmlTanpaKet(String jmlTanpaKet) {
        this.jmlTanpaKet = jmlTanpaKet;
    }

    public String getTotalTidakMasuk() {
        return totalTidakMasuk;
    }

    public void setTotalTidakMasuk(String totalTidakMasuk) {
        this.totalTidakMasuk = totalTidakMasuk;
    }

    public String getKetLain2() {
        return ketLain2;
    }

    public void setKetLain2(String ketLain2) {
        this.ketLain2 = ketLain2;
    }
}