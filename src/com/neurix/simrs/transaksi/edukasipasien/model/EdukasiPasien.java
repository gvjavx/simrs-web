package com.neurix.simrs.transaksi.edukasipasien.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class EdukasiPasien extends BaseModel {

    private String idEdukasiPasien;
    private String idDetailCheckup;
    private String durasi;
    private String edukator;
    private String edukasi;
    private String pemahamanAwal;
    private String metodeEdukasi;
    private String mediaEdukasi;
    private String evaluasi;
    private String ttdPasien;
    private String ttdStaf;
    private String keterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String tipe;
    private String namaTerang;
    private String sip;
    private String namaStaf;

    public String getNamaStaf() {
        return namaStaf;
    }

    public void setNamaStaf(String namaStaf) {
        this.namaStaf = namaStaf;
    }

    public String getNamaTerang() {
        return namaTerang;
    }

    public void setNamaTerang(String namaTerang) {
        this.namaTerang = namaTerang;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getIdEdukasiPasien() {
        return idEdukasiPasien;
    }

    public void setIdEdukasiPasien(String idEdukasiPasien) {
        this.idEdukasiPasien = idEdukasiPasien;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getEdukator() {
        return edukator;
    }

    public void setEdukator(String edukator) {
        this.edukator = edukator;
    }

    public String getEdukasi() {
        return edukasi;
    }

    public void setEdukasi(String edukasi) {
        this.edukasi = edukasi;
    }

    public String getPemahamanAwal() {
        return pemahamanAwal;
    }

    public void setPemahamanAwal(String pemahamanAwal) {
        this.pemahamanAwal = pemahamanAwal;
    }

    public String getMetodeEdukasi() {
        return metodeEdukasi;
    }

    public void setMetodeEdukasi(String metodeEdukasi) {
        this.metodeEdukasi = metodeEdukasi;
    }

    public String getMediaEdukasi() {
        return mediaEdukasi;
    }

    public void setMediaEdukasi(String mediaEdukasi) {
        this.mediaEdukasi = mediaEdukasi;
    }

    public String getEvaluasi() {
        return evaluasi;
    }

    public void setEvaluasi(String evaluasi) {
        this.evaluasi = evaluasi;
    }

    public String getTtdPasien() {
        return ttdPasien;
    }

    public void setTtdPasien(String ttdPasien) {
        this.ttdPasien = ttdPasien;
    }

    public String getTtdStaf() {
        return ttdStaf;
    }

    public void setTtdStaf(String ttdStaf) {
        this.ttdStaf = ttdStaf;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
