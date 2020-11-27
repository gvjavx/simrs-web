package com.neurix.simrs.master.rekananops.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class DetailRekananOps  {
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
    private String branchName;
    private String namaRekanan;
    private String nomorMaster;
    private String tipe;

    public String getNomorMaster() {
        return nomorMaster;
    }

    public void setNomorMaster(String nomorMaster) {
        this.nomorMaster = nomorMaster;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNamaRekanan() {
        return namaRekanan;
    }

    public void setNamaRekanan(String namaRekanan) {
        this.namaRekanan = namaRekanan;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
        DetailRekananOps that = (DetailRekananOps) o;
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
