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

    private String posisi;
    private String posisiName;

    //for view only
    private String tipeRekeningName;
    private boolean adaRekeningReport=false;

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
}