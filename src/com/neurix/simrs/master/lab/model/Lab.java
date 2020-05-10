package com.neurix.simrs.master.lab.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Lab extends BaseModel {

    private String idLab;
    private String namaLab;
    private String idOperatorLab;
    private String namaOperatorLab;
    private String idDokter;
    private String namaDokter;
    private String idKategoriLab;
    private String namaKategoriLab;
    private String flag;
    private String action;
    private String stCreatedDate;
    private Timestamp createdDate;
    private String createdWho;
    private String stLastUpdate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private BigDecimal tarif;
    private String stTarif;

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    private String kategoriLabName;

    public String getKategoriLabName() {
        return kategoriLabName;
    }

    public void setKategoriLabName(String kategoriLabName) {
        this.kategoriLabName = kategoriLabName;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getNamaLab() {
        return namaLab;
    }

    public void setNamaLab(String namaLab) {
        this.namaLab = namaLab;
    }

    public String getIdOperatorLab() {
        return idOperatorLab;
    }

    public void setIdOperatorLab(String idOperatorLab) {
        this.idOperatorLab = idOperatorLab;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdKategoriLab() {
        return idKategoriLab;
    }

    public void setIdKategoriLab(String idKategoriLab) {
        this.idKategoriLab = idKategoriLab;
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

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNamaKategoriLab() {
        return namaKategoriLab;
    }

    public void setNamaKategoriLab(String namaKategoriLab) {
        this.namaKategoriLab = namaKategoriLab;
    }

    public String getNamaOperatorLab() {
        return namaOperatorLab;
    }

    public void setNamaOperatorLab(String namaOperatorLab) {
        this.namaOperatorLab = namaOperatorLab;
    }

    public String getStTarif() {
        return stTarif;
    }

    public void setStTarif(String stTarif) {
        this.stTarif = stTarif;
    }
}