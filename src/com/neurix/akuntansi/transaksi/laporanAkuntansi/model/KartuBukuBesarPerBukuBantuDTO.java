package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import java.math.BigDecimal;
import java.sql.Date;

public class KartuBukuBesarPerBukuBantuDTO {
    private Date tanggal_jurnal;
    private String no_jurnal;
    private String spum_id;
    private String keterangan;
    private String kode_rekening;
    private String nama_kode_rekening;
    private String nomormaster;
    private String namamaster;
    private String nomor_nota;
    private BigDecimal jumlahdebit;
    private BigDecimal jumlahkredit;
    private String periode;


    public Date getTanggal_jurnal() {
        return tanggal_jurnal;
    }

    public void setTanggal_jurnal(Date tanggal_jurnal) {
        this.tanggal_jurnal = tanggal_jurnal;
    }

    public String getNo_jurnal() {
        return no_jurnal;
    }

    public void setNo_jurnal(String no_jurnal) {
        this.no_jurnal = no_jurnal;
    }

    public String getSpum_id() {
        return spum_id;
    }

    public void setSpum_id(String spum_id) {
        this.spum_id = spum_id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKode_rekening() {
        return kode_rekening;
    }

    public void setKode_rekening(String kode_rekening) {
        this.kode_rekening = kode_rekening;
    }

    public String getNama_kode_rekening() {
        return nama_kode_rekening;
    }

    public void setNama_kode_rekening(String nama_kode_rekening) {
        this.nama_kode_rekening = nama_kode_rekening;
    }

    public String getNomormaster() {
        return nomormaster;
    }

    public void setNomormaster(String nomormaster) {
        this.nomormaster = nomormaster;
    }

    public String getNamamaster() {
        return namamaster;
    }

    public void setNamamaster(String namamaster) {
        this.namamaster = namamaster;
    }

    public String getNomor_nota() {
        return nomor_nota;
    }

    public void setNomor_nota(String nomor_nota) {
        this.nomor_nota = nomor_nota;
    }

    public BigDecimal getJumlahdebit() {
        return jumlahdebit;
    }

    public void setJumlahdebit(BigDecimal jumlahdebit) {
        this.jumlahdebit = jumlahdebit;
    }

    public BigDecimal getJumlahkredit() {
        return jumlahkredit;
    }

    public void setJumlahkredit(BigDecimal jumlahkredit) {
        this.jumlahkredit = jumlahkredit;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
}
