package com.neurix.hris.master.smkSkalaNilai.model;

import com.neurix.authorization.company.model.ImBranches;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSmkSkalaNilaiEntity implements Serializable {

    private String skalaNilaiId;
    private String skalaName;
    private String kodeSkala;
    private double nilaiAtas;
    private double nilaiBawah;
    private String branchId;
    private int poin;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBranches imBranches;

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
