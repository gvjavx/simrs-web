package com.neurix.simrs.master.tindakan.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class KategoriTindakanPelayanan extends BaseModel {

    private String idKategoriPelayanan;
    private String idKategori;
    private String idPelayanan;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String stCreatedDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String stLastUpdate;
    private String lastUpdateWho;

    private String kategoriName;
    private String pelayananName;

    public String getIdKategoriPelayanan() {
        return idKategoriPelayanan;
    }

    public void setIdKategoriPelayanan(String idKategoriPelayanan) {
        this.idKategoriPelayanan = idKategoriPelayanan;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
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
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
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

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getKategoriName() {
        return kategoriName;
    }

    public void setKategoriName(String kategoriName) {
        this.kategoriName = kategoriName;
    }

    public String getPelayananName() {
        return pelayananName;
    }

    public void setPelayananName(String pelayananName) {
        this.pelayananName = pelayananName;
    }
}
