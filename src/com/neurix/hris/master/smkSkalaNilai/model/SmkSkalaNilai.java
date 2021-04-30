package com.neurix.hris.master.smkSkalaNilai.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkSkalaNilai extends BaseModel {
    private String skalaNilaiId;
    private String skalaName;
    private String kodeSkala;
    private double nilaiAtas;
    private double nilaiBawah;
    private String branchId;
    private String branchName;
    private int poin;

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSkalaNilaiId() {
        return skalaNilaiId;
    }

    public void setSkalaNilaiId(String skalaNilaiId) {
        this.skalaNilaiId = skalaNilaiId;
    }

    public String getSkalaName() {
        return skalaName;
    }

    public void setSkalaName(String skalaName) {
        this.skalaName = skalaName;
    }

    public String getKodeSkala() {
        return kodeSkala;
    }

    public void setKodeSkala(String kodeSkala) {
        this.kodeSkala = kodeSkala;
    }

    public double getNilaiAtas() {
        return nilaiAtas;
    }

    public void setNilaiAtas(double nilaiAtas) {
        this.nilaiAtas = nilaiAtas;
    }

    public double getNilaiBawah() {
        return nilaiBawah;
    }

    public void setNilaiBawah(double nilaiBawah) {
        this.nilaiBawah = nilaiBawah;
    }

    public void setNilaiBawah(Long nilaiBawah) {
        this.nilaiBawah = nilaiBawah;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}