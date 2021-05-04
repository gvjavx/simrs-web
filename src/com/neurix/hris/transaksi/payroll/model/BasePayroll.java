/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;

public abstract class BasePayroll extends BaseModel {
    private String nip;
    private String nipLama;
    private String gelar;
    private String namaPegawai;
    private String branchId;
    private String branchName;
    private String tipePayroll;
    private String statusKeluarga;
    private int jumlahAnak;
    private String stJumlahAnak;
    private String noRekBank;
    private String namaBank;
    private Date tanggalAktif;
    private String stTanggalAktif;
    private Date tanggalPensiun;
    private String stTanggalPensiun;
    private Date tanggalKeluar;
    private String stTanggalKeluar;
    private Date tanggalAkhirKontrak;
    private String stTanggalAkhirKontrak;
    private String kodering;
    private String periodePayroll;
    private String tahunPayroll;
    private String gender;

    //gaji dan tunjangan
    private String gajiPokok;
    private BigDecimal gajiPokokNilai;
    private String gajiKotor;
    private BigDecimal gajiKotorNilai;
    private String gajiBersih;
    private BigDecimal gajiBersihNilai;

    //Take Home Pay
    private String thp;
    private BigDecimal thpNilai;

    //tunj PPH dari proses grossup
    private String pphGaji;
    private BigDecimal pphGajiNilai;
    private String ptkpPegawai;
    private BigDecimal ptkpPegawaiNilai;
    private String npwpPegawai;
    private String noBpjsKs;
    private String noBpjsTk;

    public String getNoBpjsKs() {
        return noBpjsKs;
    }

    public void setNoBpjsKs(String noBpjsKs) {
        this.noBpjsKs = noBpjsKs;
    }

    public String getNoBpjsTk() {
        return noBpjsTk;
    }

    public void setNoBpjsTk(String noBpjsTk) {
        this.noBpjsTk = noBpjsTk;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNpwpPegawai() {
        return npwpPegawai;
    }

    public void setNpwpPegawai(String npwpPegawai) {
        this.npwpPegawai = npwpPegawai;
    }

    public String getPtkpPegawai() {
        return ptkpPegawai;
    }

    public void setPtkpPegawai(String ptkpPegawai) {
        this.ptkpPegawai = ptkpPegawai;
    }

    public BigDecimal getPtkpPegawaiNilai() {
        return ptkpPegawaiNilai;
    }

    public void setPtkpPegawaiNilai(BigDecimal ptkpPegawaiNilai) {
        this.ptkpPegawaiNilai = ptkpPegawaiNilai;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNipLama() {
        return nipLama;
    }

    public void setNipLama(String nipLama) {
        this.nipLama = nipLama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getTipePayroll() {
        return tipePayroll;
    }

    public void setTipePayroll(String tipePayroll) {
        this.tipePayroll = tipePayroll;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getStJumlahAnak() {
        return stJumlahAnak;
    }

    public void setStJumlahAnak(String stJumlahAnak) {
        this.stJumlahAnak = stJumlahAnak;
    }

    public String getNoRekBank() {
        return noRekBank;
    }

    public void setNoRekBank(String noRekBank) {
        this.noRekBank = noRekBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getStTanggalAktif() {
        return stTanggalAktif;
    }

    public void setStTanggalAktif(String stTanggalAktif) {
        this.stTanggalAktif = stTanggalAktif;
    }

    public Date getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(Date tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public String getStTanggalPensiun() {
        return stTanggalPensiun;
    }

    public void setStTanggalPensiun(String stTanggalPensiun) {
        this.stTanggalPensiun = stTanggalPensiun;
    }

    public Date getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(Date tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getStTanggalKeluar() {
        return stTanggalKeluar;
    }

    public void setStTanggalKeluar(String stTanggalKeluar) {
        this.stTanggalKeluar = stTanggalKeluar;
    }

    public Date getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(Date tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public String getStTanggalAkhirKontrak() {
        return stTanggalAkhirKontrak;
    }

    public void setStTanggalAkhirKontrak(String stTanggalAkhirKontrak) {
        this.stTanggalAkhirKontrak = stTanggalAkhirKontrak;
    }

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
    }

    public String getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(String gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public BigDecimal getGajiPokokNilai() {
        return gajiPokokNilai;
    }

    public void setGajiPokokNilai(BigDecimal gajiPokokNilai) {
        this.gajiPokokNilai = gajiPokokNilai;
    }

    public String getGajiKotor() {
        return gajiKotor;
    }

    public void setGajiKotor(String gajiKotor) {
        this.gajiKotor = gajiKotor;
    }

    public BigDecimal getGajiKotorNilai() {
        return gajiKotorNilai;
    }

    public void setGajiKotorNilai(BigDecimal gajiKotorNilai) {
        this.gajiKotorNilai = gajiKotorNilai;
    }

    public String getGajiBersih() {
        return gajiBersih;
    }

    public void setGajiBersih(String gajiBersih) {
        this.gajiBersih = gajiBersih;
    }

    public BigDecimal getGajiBersihNilai() {
        return gajiBersihNilai;
    }

    public void setGajiBersihNilai(BigDecimal gajiBersihNilai) {
        this.gajiBersihNilai = gajiBersihNilai;
    }

    public String getThp() {
        return thp;
    }

    public void setThp(String thp) {
        this.thp = thp;
    }

    public BigDecimal getThpNilai() {
        return thpNilai;
    }

    public void setThpNilai(BigDecimal thpNilai) {
        this.thpNilai = thpNilai;
    }

    public String getPphGaji() {
        return pphGaji;
    }

    public void setPphGaji(String pphGaji) {
        this.pphGaji = pphGaji;
    }

    public BigDecimal getPphGajiNilai() {
        return pphGajiNilai;
    }

    public void setPphGajiNilai(BigDecimal pphGajiNilai) {
        this.pphGajiNilai = pphGajiNilai;
    }

    public String getPeriodePayroll() {
        return periodePayroll;
    }

    public void setPeriodePayroll(String periodePayroll) {
        this.periodePayroll = periodePayroll;
    }

    public String getTahunPayroll() {
        return tahunPayroll;
    }

    public void setTahunPayroll(String tahunPayroll) {
        this.tahunPayroll = tahunPayroll;
    }

    public abstract void calculatePrePayroll();
    public abstract void calculatePPH();
    public abstract void calculatePPHAnnual();
    public abstract void calculatePostPayroll();

    public abstract void calculatePreNonRutinPayroll();
    public abstract void calculateNonRutinPPH();
    public abstract void calculatePostNonRutinPayroll();

    public abstract void calculateBasedMultifikator();
    public abstract void recalculateDasarBpjs();
}
