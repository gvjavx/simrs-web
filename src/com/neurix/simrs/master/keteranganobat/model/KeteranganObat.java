package com.neurix.simrs.master.keteranganobat.model;

import java.sql.Timestamp;

public class KeteranganObat {

    private String id;
    private String idSubJenis;
    private String idParameterKeterangan;
    private String keterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String namaSubJenis;
    private String namaParameterKeterangan;
    private String warnaLabel;
    private String warnaBackground;
    private String idJenis;
    private String namaJenis;

    public String getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(String idJenis) {
        this.idJenis = idJenis;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    public String getWarnaLabel() {
        return warnaLabel;
    }

    public void setWarnaLabel(String warnaLabel) {
        this.warnaLabel = warnaLabel;
    }

    public String getWarnaBackground() {
        return warnaBackground;
    }

    public void setWarnaBackground(String warnaBackground) {
        this.warnaBackground = warnaBackground;
    }

    public String getNamaSubJenis() {
        return namaSubJenis;
    }

    public void setNamaSubJenis(String namaSubJenis) {
        this.namaSubJenis = namaSubJenis;
    }

    public String getNamaParameterKeterangan() {
        return namaParameterKeterangan;
    }

    public void setNamaParameterKeterangan(String namaParameterKeterangan) {
        this.namaParameterKeterangan = namaParameterKeterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSubJenis() {
        return idSubJenis;
    }

    public void setIdSubJenis(String idSubJenis) {
        this.idSubJenis = idSubJenis;
    }

    public String getIdParameterKeterangan() {
        return idParameterKeterangan;
    }

    public void setIdParameterKeterangan(String idParameterKeterangan) {
        this.idParameterKeterangan = idParameterKeterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
