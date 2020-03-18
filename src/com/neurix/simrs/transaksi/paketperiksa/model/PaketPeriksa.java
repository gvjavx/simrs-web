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

    public String getIdPelayanan() {
        return idPelayanan;
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
