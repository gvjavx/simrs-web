package com.neurix.hris.master.payrollTunjanganJabatanStruktural.model;

import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;

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
public class ImmPayrollTunjanganJabatanStrukturalEntity implements Serializable {

    private String tunjJabatanStrukturalId ;
    private String positionId ;
    private BigDecimal nilai ;
    private String branchId;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    private String positionName;
    private String branchName;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImPositionBagianEntity imPositionBagianEntity;

    public ImPositionBagianEntity getImPositionBagianEntity() {
        return imPositionBagianEntity;
    }

    public void setImPositionBagianEntity(ImPositionBagianEntity imPositionBagianEntity) {
        this.imPositionBagianEntity = imPositionBagianEntity;
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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getTunjJabatanStrukturalId() {
        return tunjJabatanStrukturalId;
    }

    public void setTunjJabatanStrukturalId(String tunjJabatanStrukturalId) {
        this.tunjJabatanStrukturalId = tunjJabatanStrukturalId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}