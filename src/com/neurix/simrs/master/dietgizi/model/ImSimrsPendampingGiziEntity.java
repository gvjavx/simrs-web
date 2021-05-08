package com.neurix.simrs.master.dietgizi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsPendampingGiziEntity {
    private String idPendampingGizi;
    private String nama;
    private String tipe;
    private String branchId;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdPendampingGizi() {
        return idPendampingGizi;
    }

    public void setIdPendampingGizi(String idPendampingGizi) {
        this.idPendampingGizi = idPendampingGizi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
        ImSimrsPendampingGiziEntity that = (ImSimrsPendampingGiziEntity) o;
        return Objects.equals(idPendampingGizi, that.idPendampingGizi) &&
                Objects.equals(nama, that.nama) &&
                Objects.equals(tipe, that.tipe) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPendampingGizi, nama, tipe, branchId, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho);
    }
}
