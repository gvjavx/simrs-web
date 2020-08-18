package com.neurix.akuntansi.master.kodeRekening.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class KodeRekening extends BaseModel {
    private String rekeningId;
    private String kodeRekening;
    private String namaKodeRekening;
    private String parentId;
    private String tipeRekeningId;
    private Long level;
    private String postCoa;

    private String tampilanCoa;
    private String posisi;
    private String posisiName;

    //for view only
    private String tipeRekeningName;
    private boolean adaRekeningReport=false;
    private boolean bisaCek=false;
    private String flagMaster;
    private String flagDivisi;
    private String tipeBudgeting;

    public String getTipeBudgeting() {
        return tipeBudgeting;
    }

    public void setTipeBudgeting(String tipeBudgeting) {
        this.tipeBudgeting = tipeBudgeting;
    }

    public String getTampilanCoa() {
        return tampilanCoa;
    }

    public void setTampilanCoa(String tampilanCoa) {
        this.tampilanCoa = tampilanCoa;
    }

    public String getPostCoa() {
        return postCoa;
    }

    public void setPostCoa(String postCoa) {
        this.postCoa = postCoa;
    }

    public boolean isBisaCek() {
        return bisaCek;
    }

    public void setBisaCek(boolean bisaCek) {
        this.bisaCek = bisaCek;
    }

    public boolean isAdaRekeningReport() {
        return adaRekeningReport;
    }

    public void setAdaRekeningReport(boolean adaRekeningReport) {
        this.adaRekeningReport = adaRekeningReport;
    }

    public String getTipeRekeningName() {
        return tipeRekeningName;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getPosisiName() {
        return posisiName;
    }

    public void setPosisiName(String posisiName) {
        this.posisiName = posisiName;
    }

    public void setTipeRekeningName(String tipeRekeningName) {
        this.tipeRekeningName = tipeRekeningName;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getNamaKodeRekening() {
        return namaKodeRekening;
    }

    public void setNamaKodeRekening(String namaKodeRekening) {
        this.namaKodeRekening = namaKodeRekening;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTipeRekeningId() {
        return tipeRekeningId;
    }

    public void setTipeRekeningId(String tipeRekeningId) {
        this.tipeRekeningId = tipeRekeningId;
    }

    public String getFlagMaster() {
        return flagMaster;
    }

    public void setFlagMaster(String flagMaster) {
        this.flagMaster = flagMaster;
    }

    public String getFlagDivisi() {
        return flagDivisi;
    }

    public void setFlagDivisi(String flagDivisi) {
        this.flagDivisi = flagDivisi;
    }
}