package com.neurix.akuntansi.transaksi.budgeting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingPengadaan{

    private String idPengadaan;
    private String noBudgeting;
    private String namPengadaan;
    private BigDecimal nilai;
    private BigInteger qty;
    private BigDecimal subTotal;
    private String tipe;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String rekeningId;
    private String idBudgetingDetail;
    private String noKontrak;
    private BigDecimal nilaiKontrak;
    private String namaKontrak;
    private BigDecimal selisih;
    private BigDecimal realisasi;
    private String noPengadaan;

    private String pembayaran;
    private BigDecimal realisasiKontrak;
    private BigDecimal sisaNilaiKontrak;

    private BigDecimal nilaiAdendum1;
    private BigDecimal nilaiAdendum2;
    private BigDecimal nilaiAdendum3;

    private String noKontrakAdendum1;
    private String noKontrakAdendum2;
    private String noKontrakAdendum3;
    private String idNilaiParam;


    public String getIdNilaiParam() {
        return idNilaiParam;
    }

    public void setIdNilaiParam(String idNilaiParam) {
        this.idNilaiParam = idNilaiParam;
    }

    public BigDecimal getNilaiAdendum1() {
        return nilaiAdendum1;
    }

    public void setNilaiAdendum1(BigDecimal nilaiAdendum1) {
        this.nilaiAdendum1 = nilaiAdendum1;
    }

    public BigDecimal getNilaiAdendum2() {
        return nilaiAdendum2;
    }

    public void setNilaiAdendum2(BigDecimal nilaiAdendum2) {
        this.nilaiAdendum2 = nilaiAdendum2;
    }

    public BigDecimal getNilaiAdendum3() {
        return nilaiAdendum3;
    }

    public void setNilaiAdendum3(BigDecimal nilaiAdendum3) {
        this.nilaiAdendum3 = nilaiAdendum3;
    }

    public String getNoKontrakAdendum1() {
        return noKontrakAdendum1;
    }

    public void setNoKontrakAdendum1(String noKontrakAdendum1) {
        this.noKontrakAdendum1 = noKontrakAdendum1;
    }

    public String getNoKontrakAdendum2() {
        return noKontrakAdendum2;
    }

    public void setNoKontrakAdendum2(String noKontrakAdendum2) {
        this.noKontrakAdendum2 = noKontrakAdendum2;
    }

    public String getNoKontrakAdendum3() {
        return noKontrakAdendum3;
    }

    public void setNoKontrakAdendum3(String noKontrakAdendum3) {
        this.noKontrakAdendum3 = noKontrakAdendum3;
    }

    public BigDecimal getRealisasiKontrak() {
        return realisasiKontrak;
    }

    public void setRealisasiKontrak(BigDecimal realisasiKontrak) {
        this.realisasiKontrak = realisasiKontrak;
    }

    public BigDecimal getSisaNilaiKontrak() {
        return sisaNilaiKontrak;
    }

    public void setSisaNilaiKontrak(BigDecimal sisaNilaiKontrak) {
        this.sisaNilaiKontrak = sisaNilaiKontrak;
    }

    public String getNamaKontrak() {
        return namaKontrak;
    }

    public void setNamaKontrak(String namaKontrak) {
        this.namaKontrak = namaKontrak;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getNoPengadaan() {
        return noPengadaan;
    }

    public void setNoPengadaan(String noPengadaan) {
        this.noPengadaan = noPengadaan;
    }

    public BigDecimal getSelisih() {
        return selisih;
    }

    public void setSelisih(BigDecimal selisih) {
        this.selisih = selisih;
    }

    public BigDecimal getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(BigDecimal realisasi) {
        this.realisasi = realisasi;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public BigDecimal getNilaiKontrak() {
        return nilaiKontrak;
    }

    public void setNilaiKontrak(BigDecimal nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
    }

    public String getIdBudgetingDetail() {
        return idBudgetingDetail;
    }

    public void setIdBudgetingDetail(String idBudgetingDetail) {
        this.idBudgetingDetail = idBudgetingDetail;
    }

    public String getIdPengadaan() {
        return idPengadaan;
    }

    public void setIdPengadaan(String idPengadaan) {
        this.idPengadaan = idPengadaan;
    }

    public String getNamPengadaan() {
        return namPengadaan;
    }

    public void setNamPengadaan(String namPengadaan) {
        this.namPengadaan = namPengadaan;
    }

    public String getNoBudgeting() {
        return noBudgeting;
    }

    public void setNoBudgeting(String noBudgeting) {
        this.noBudgeting = noBudgeting;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }
}
