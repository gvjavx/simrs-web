package com.neurix.simrs.transaksi.reseponline.model;

import java.sql.Timestamp;

/**
 * Created by reza on 18/06/20.
 */
public class PengirimanObat {

    private String id;
    private String idKurir;
    private String idResep;
    private String flagPickup;
    private String flagDiterimaPasien;
    private String idPasien;
    private String flag;
    private String action;
    private String idPelayanan;
    private String branchId;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String kurirName;
    private String branchName;
    private String pasienName;
    private String pelayananName;
    private String desaId;
    private String descOfLocation;
    private String alamat;
    private String noTelp;

    private String noPolisi;
    private String noTelpKurir;

    private String lat;
    private String lon;
    private String status;
    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String flagTerkirim;

    public String getFlagTerkirim() {
        return flagTerkirim;
    }

    public void setFlagTerkirim(String flagTerkirim) {
        this.flagTerkirim = flagTerkirim;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getNoTelpKurir() {
        return noTelpKurir;
    }

    public void setNoTelpKurir(String noTelpKurir) {
        this.noTelpKurir = noTelpKurir;
    }

    private boolean isPasien;

    public boolean isPasien() {
        return isPasien;
    }

    public void setPasien(boolean pasien) {
        isPasien = pasien;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getDescOfLocation() {
        return descOfLocation;
    }

    public void setDescOfLocation(String descOfLocation) {
        this.descOfLocation = descOfLocation;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public String getFlagPickup() {
        return flagPickup;
    }

    public void setFlagPickup(String flagPickup) {
        this.flagPickup = flagPickup;
    }

    public String getFlagDiterimaPasien() {
        return flagDiterimaPasien;
    }

    public void setFlagDiterimaPasien(String flagDiterimaPasien) {
        this.flagDiterimaPasien = flagDiterimaPasien;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
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

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getKurirName() {
        return kurirName;
    }

    public void setKurirName(String kurirName) {
        this.kurirName = kurirName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPasienName() {
        return pasienName;
    }

    public void setPasienName(String pasienName) {
        this.pasienName = pasienName;
    }

    public String getPelayananName() {
        return pelayananName;
    }

    public void setPelayananName(String pelayananName) {
        this.pelayananName = pelayananName;
    }
}
