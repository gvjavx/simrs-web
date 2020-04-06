package com.neurix.hris.master.payrollDanaPensiun.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;

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
public class ImPayrollDanaPensiunEntity implements Serializable {
    private String danaPensiunId;
    private String danaPensiun;
    private BigDecimal persentase;
    private BigDecimal persentaseKary;
    private BigDecimal persentasePers;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImGolonganEntity imGolonganEntity;
    private ImBranches imBranches;

    public BigDecimal getPersentaseKary() {
        return persentaseKary;
    }

    public void setPersentaseKary(BigDecimal persentaseKary) {
        this.persentaseKary = persentaseKary;
    }

    public BigDecimal getPersentasePers() {
        return persentasePers;
    }

    public void setPersentasePers(BigDecimal persentasePers) {
        this.persentasePers = persentasePers;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
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

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public String getDanaPensiunId() {
        return danaPensiunId;
    }

    public void setDanaPensiunId(String danaPensiunId) {
        this.danaPensiunId = danaPensiunId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
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

    public BigDecimal getPersentase() {
        return persentase;
    }

    public void setPersentase(BigDecimal persentase) {
        this.persentase = persentase;
    }
}