package com.neurix.hris.master.payrollSkalaGajiPensiunRni.model;

import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.golonganDapen.model.ImGolonganDapenEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPayrollSkalaGajiPensiunRniEntity implements Serializable {
    private String skalaGajiPensiunId;
    private String golonganId;
    private int poin;
    private BigDecimal nilai;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImGolonganDapenEntity imGolonganDapenEntity;

    public ImGolonganDapenEntity getImGolonganDapenEntity() {
        return imGolonganDapenEntity;
    }

    public void setImGolonganDapenEntity(ImGolonganDapenEntity imGolonganDapenEntity) {
        this.imGolonganDapenEntity = imGolonganDapenEntity;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
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

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getSkalaGajiPensiunId() {
        return skalaGajiPensiunId;
    }

    public void setSkalaGajiPensiunId(String skalaGajiPensiunId) {
        this.skalaGajiPensiunId = skalaGajiPensiunId;
    }
}
