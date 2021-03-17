package com.neurix.simrs.master.rekananops.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsDetailRekananOpsEntity {
    private String idDetailRekananOps;
    private String idRekananOps;
    private BigInteger diskon;
    private String isBpjs;
    private String branchId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idItem;
    private String parentId;
    private String flagParent;
    private String jenisItem;
    private BigDecimal tarif;
    private BigDecimal tarifBpjs;
    private BigDecimal diskonNonBpjs;
    private BigDecimal diskonBpjs;

    public BigDecimal getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(BigDecimal tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
    }

    public BigDecimal getDiskonNonBpjs() {
        return diskonNonBpjs;
    }

    public void setDiskonNonBpjs(BigDecimal diskonNonBpjs) {
        this.diskonNonBpjs = diskonNonBpjs;
    }

    public BigDecimal getDiskonBpjs() {
        return diskonBpjs;
    }

    public void setDiskonBpjs(BigDecimal diskonBpjs) {
        this.diskonBpjs = diskonBpjs;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFlagParent() {
        return flagParent;
    }

    public void setFlagParent(String flagParent) {
        this.flagParent = flagParent;
    }

    public String getJenisItem() {
        return jenisItem;
    }

    public void setJenisItem(String jenisItem) {
        this.jenisItem = jenisItem;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public String getIdDetailRekananOps() {
        return idDetailRekananOps;
    }

    public void setIdDetailRekananOps(String idDetailRekananOps) {
        this.idDetailRekananOps = idDetailRekananOps;
    }

    public String getIdRekananOps() {
        return idRekananOps;
    }

    public void setIdRekananOps(String idRekananOps) {
        this.idRekananOps = idRekananOps;
    }

    public BigInteger getDiskon() {
        return diskon;
    }

    public void setDiskon(BigInteger diskon) {
        this.diskon = diskon;
    }

    public String getIsBpjs() {
        return isBpjs;
    }

    public void setIsBpjs(String isBpjs) {
        this.isBpjs = isBpjs;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImSimrsDetailRekananOpsEntity that = (ImSimrsDetailRekananOpsEntity) o;
        return Objects.equals(idDetailRekananOps, that.idDetailRekananOps) &&
                Objects.equals(idRekananOps, that.idRekananOps) &&
                Objects.equals(diskon, that.diskon) &&
                Objects.equals(isBpjs, that.isBpjs) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetailRekananOps, idRekananOps, diskon, isBpjs, branchId, flag, action, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
