package com.neurix.simrs.transaksi.paketperiksa.model;

import java.sql.Timestamp;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPeriksa {

    private String idPaket;
    private String namaPaket;
    private String idKelasPaket;
    private String namaKelasPaket;
    private String idPerusahaan;
    private String namaPerusahaan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String idPelayanan;
    private String branchId;
    private String jumlah;

    private String idPasien;
    private String namaPasien;

    private String idKategoriItem;
    private String idItem;
    private String jenisItem;

    private String idPaketPasien;

    private String keterangan;

    private String idItemPaket;

    public String getIdItemPaket() {
        return idItemPaket;
    }

    public void setIdItemPaket(String idItemPaket) {
        this.idItemPaket = idItemPaket;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdPaketPasien() {
        return idPaketPasien;
    }

    public void setIdPaketPasien(String idPaketPasien) {
        this.idPaketPasien = idPaketPasien;
    }

    public String getIdKategoriItem() {
        return idKategoriItem;
    }

    public void setIdKategoriItem(String idKategoriItem) {
        this.idKategoriItem = idKategoriItem;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getJenisItem() {
        return jenisItem;
    }

    public void setJenisItem(String jenisItem) {
        this.jenisItem = jenisItem;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getIdKelasPaket() {
        return idKelasPaket;
    }

    public void setIdKelasPaket(String idKelasPaket) {
        this.idKelasPaket = idKelasPaket;
    }

    public String getNamaKelasPaket() {
        return namaKelasPaket;
    }

    public void setNamaKelasPaket(String namaKelasPaket) {
        this.namaKelasPaket = namaKelasPaket;
    }

    public String getIdPerusahaan() {
        return idPerusahaan;
    }

    public void setIdPerusahaan(String idPerusahaan) {
        this.idPerusahaan = idPerusahaan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
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
}
