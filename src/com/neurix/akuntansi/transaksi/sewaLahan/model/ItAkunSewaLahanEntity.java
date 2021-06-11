package com.neurix.akuntansi.transaksi.sewaLahan.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Aji Noor on 04/03/2021
 */
public class ItAkunSewaLahanEntity {
    private String sewaLahanId;
    private String namaPenyewa;
    private String keterangan;
    private Date tanggalBayar;
    private BigDecimal nilai;
    private String cancelFlag;
    private String flag;
    private String cancelWho;
    private Timestamp cancelDate;
    private String noJurnal;
    private BigDecimal nilaiPpn;
    private BigDecimal nilaiNetto;
    private BigDecimal nilaiPph;
    private String branchId;
    private String noFaktur;
    private String bank;

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSewaLahanId() {
        return sewaLahanId;
    }

    public void setSewaLahanId(String sewaLahanId) {
        this.sewaLahanId = sewaLahanId;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getCancelWho() {
        return cancelWho;
    }

    public void setCancelWho(String cancelWho) {
        this.cancelWho = cancelWho;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigDecimal getNilaiPpn() {
        return nilaiPpn;
    }

    public void setNilaiPpn(BigDecimal nilaiPpn) {
        this.nilaiPpn = nilaiPpn;
    }

    public BigDecimal getNilaiNetto() {
        return nilaiNetto;
    }

    public void setNilaiNetto(BigDecimal nilaiNetto) {
        this.nilaiNetto = nilaiNetto;
    }

    public BigDecimal getNilaiPph() {
        return nilaiPph;
    }

    public void setNilaiPph(BigDecimal nilaiPph) {
        this.nilaiPph = nilaiPph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunSewaLahanEntity that = (ItAkunSewaLahanEntity) o;
        return Objects.equals(sewaLahanId, that.sewaLahanId) &&
                Objects.equals(namaPenyewa, that.namaPenyewa) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(tanggalBayar, that.tanggalBayar) &&
                Objects.equals(nilai, that.nilai) &&
                Objects.equals(cancelFlag, that.cancelFlag) &&
                Objects.equals(cancelWho, that.cancelWho) &&
                Objects.equals(cancelDate, that.cancelDate) &&
                Objects.equals(noJurnal, that.noJurnal) &&
                Objects.equals(nilaiPpn, that.nilaiPpn) &&
                Objects.equals(nilaiNetto, that.nilaiNetto) &&
                Objects.equals(nilaiPph, that.nilaiPph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sewaLahanId, namaPenyewa, keterangan, tanggalBayar, nilai, cancelFlag, cancelWho, cancelDate, noJurnal, nilaiPpn, nilaiNetto, nilaiPph);
    }
}
