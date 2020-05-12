package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import java.math.BigDecimal;
import java.sql.Date;

public class ArusKasDTO {
    private String kodeRekening;
    private String grup;
    private String namaRekening;
    private BigDecimal penerimaan;
    private BigDecimal pengeluaran;
    private BigDecimal lastSaldo;
    private BigDecimal saldoSekarang;

    //for detail
    private String noJurnal;
    private String keterangan;
    private Date tanggalJurnal;

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public BigDecimal getPenerimaan() {
        return penerimaan;
    }

    public void setPenerimaan(BigDecimal penerimaan) {
        this.penerimaan = penerimaan;
    }

    public BigDecimal getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(BigDecimal pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public BigDecimal getLastSaldo() {
        return lastSaldo;
    }

    public void setLastSaldo(BigDecimal lastSaldo) {
        this.lastSaldo = lastSaldo;
    }

    public BigDecimal getSaldoSekarang() {
        return saldoSekarang;
    }

    public void setSaldoSekarang(BigDecimal saldoSekarang) {
        this.saldoSekarang = saldoSekarang;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTanggalJurnal() {
        return tanggalJurnal;
    }

    public void setTanggalJurnal(Date tanggalJurnal) {
        this.tanggalJurnal = tanggalJurnal;
    }
}