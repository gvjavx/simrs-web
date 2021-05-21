package com.neurix.akuntansi.master.kodeRekening.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImKodeRekeningEntity implements Serializable {

    private String rekeningId;
    private String kodeRekening;
    private String namaKodeRekening;
    private String parentId;
    private String tipeRekeningId;
    private Long level;

    private String flag;
    private String action;

    private String tipeCoa;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String flagMaster;
    private String flagDivisi;
    private String tipeBudgeting;

    public String getTipeBudgeting() {
        return tipeBudgeting;
    }

    public void setTipeBudgeting(String tipeBudgeting) {
        this.tipeBudgeting = tipeBudgeting;
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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getTipeCoa() {
        return tipeCoa;
    }

    public void setTipeCoa(String tipeCoa) {
        this.tipeCoa = tipeCoa;
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
