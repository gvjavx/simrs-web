package com.neurix.hris.master.dokterKso.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ImSimrsDokterKso implements Serializable{

    String dokterKsoId;
    String nip;
    String jenisKso;
    String masterId;
    String tarifIna;
    String branchId;
    BigDecimal persenKso;
    BigDecimal persenKs;

    public String getDokterKsoId() {
        return dokterKsoId;
    }

    public void setDokterKsoId(String dokterKsoId) {
        this.dokterKsoId = dokterKsoId;
    }

    public String getJenisKso() {
        return jenisKso;
    }

    public void setJenisKso(String jenisKso) {
        this.jenisKso = jenisKso;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getPersenKs() {
        return persenKs;
    }

    public void setPersenKs(BigDecimal persenKs) {
        this.persenKs = persenKs;
    }

    public BigDecimal getPersenKso() {
        return persenKso;
    }

    public void setPersenKso(BigDecimal persenKso) {
        this.persenKso = persenKso;
    }

    public String getTarifIna() {
        return tarifIna;
    }

    public void setTarifIna(String tarifIna) {
        this.tarifIna = tarifIna;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}