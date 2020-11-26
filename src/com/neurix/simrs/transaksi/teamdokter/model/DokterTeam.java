package com.neurix.simrs.transaksi.teamdokter.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class DokterTeam extends BaseModel {

    private String idTeamDokter;
    private String idDokter;
    private String idDetailCheckup;
    private String kategori;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String namaDokter;
    private String namaSpesialis;

    private String idPelayanan;
    private String namaPelayanan;
    private String jenisDpjp;
    private String sip;
    private String flagApprove;
    private String keterangan;

    public String getFlagApprove() {
        return flagApprove;
    }

    public void setFlagApprove(String flagApprove) {
        this.flagApprove = flagApprove;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getJenisDpjp() {
        return jenisDpjp;
    }

    public void setJenisDpjp(String jenisDpjp) {
        this.jenisDpjp = jenisDpjp;
    }
    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNamaSpesialis() {
        return namaSpesialis;
    }

    public void setNamaSpesialis(String namaSpesialis) {
        this.namaSpesialis = namaSpesialis;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getIdTeamDokter() {
        return idTeamDokter;
    }

    public void setIdTeamDokter(String idTeamDokter) {
        this.idTeamDokter = idTeamDokter;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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