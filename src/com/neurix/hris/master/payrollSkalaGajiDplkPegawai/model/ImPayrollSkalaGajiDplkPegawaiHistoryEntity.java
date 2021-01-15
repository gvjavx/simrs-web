package com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model;

import com.neurix.hris.master.golongan.model.ImGolonganEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ImPayrollSkalaGajiDplkPegawaiHistoryEntity {
    private String skalaGajiPensiunDplkPegawaiHistoryId;
    private String skalaGajiPensiunDplkPegawaiId;
    private String golonganId;
    private BigDecimal nilai;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImGolonganEntity imGolonganEntity;

    public String getSkalaGajiPensiunDplkPegawaiHistoryId() {
        return skalaGajiPensiunDplkPegawaiHistoryId;
    }

    public void setSkalaGajiPensiunDplkPegawaiHistoryId(String skalaGajiPensiunDplkPegawaiHistoryId) {
        this.skalaGajiPensiunDplkPegawaiHistoryId = skalaGajiPensiunDplkPegawaiHistoryId;
    }

    public String getSkalaGajiPensiunDplkPegawaiId() {
        return skalaGajiPensiunDplkPegawaiId;
    }

    public void setSkalaGajiPensiunDplkPegawaiId(String skalaGajiPensiunDplkPegawaiId) {
        this.skalaGajiPensiunDplkPegawaiId = skalaGajiPensiunDplkPegawaiId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
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

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
    }
}