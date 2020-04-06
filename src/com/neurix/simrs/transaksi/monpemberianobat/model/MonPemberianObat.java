package com.neurix.simrs.transaksi.monpemberianobat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 12/02/20.
 */
public class MonPemberianObat {
    private String id;
    private String namaObat;
    private String dosis;
    private String caraPemberian;
    private String skinTes;
    private String waktu;
    private String keterangan;
    private String noCheckup;
    private String idDetailCheckup;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String stDate;
    private String kategori;
    private String waktuPemberian;
    private String catatanDokter;

    public String getWaktuPemberian() {
        return waktuPemberian;
    }

    public void setWaktuPemberian(String waktuPemberian) {
        this.waktuPemberian = waktuPemberian;
    }

    public String getCatatanDokter() {
        return catatanDokter;
    }

    public void setCatatanDokter(String catatanDokter) {
        this.catatanDokter = catatanDokter;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getCaraPemberian() {
        return caraPemberian;
    }

    public void setCaraPemberian(String caraPemberian) {
        this.caraPemberian = caraPemberian;
    }

    public String getSkinTes() {
        return skinTes;
    }

    public void setSkinTes(String skinTes) {
        this.skinTes = skinTes;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
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
