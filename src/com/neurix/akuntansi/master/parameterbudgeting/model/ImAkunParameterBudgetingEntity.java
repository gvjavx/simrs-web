package com.neurix.akuntansi.master.parameterbudgeting.model;

import java.sql.Timestamp;

/**
 * Created by reza on 14/08/20.
 */
public class ImAkunParameterBudgetingEntity {
    private String id;
    private String nama;
    private String rekeningId;
    private String idJenisBudgeting;
    private String tipe;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idKategoriBudgeting;
    private String masterId;
    private String divisiId;
    private String idParamRekening;

    public String getIdParamRekening() {
        return idParamRekening;
    }

    public void setIdParamRekening(String idParamRekening) {
        this.idParamRekening = idParamRekening;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getIdKategoriBudgeting() {
        return idKategoriBudgeting;
    }

    public void setIdKategoriBudgeting(String idKategoriBudgeting) {
        this.idKategoriBudgeting = idKategoriBudgeting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getIdJenisBudgeting() {
        return idJenisBudgeting;
    }

    public void setIdJenisBudgeting(String idJenisBudgeting) {
        this.idJenisBudgeting = idJenisBudgeting;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

        ImAkunParameterBudgetingEntity that = (ImAkunParameterBudgetingEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nama != null ? !nama.equals(that.nama) : that.nama != null) return false;
        if (rekeningId != null ? !rekeningId.equals(that.rekeningId) : that.rekeningId != null) return false;
        if (idJenisBudgeting != null ? !idJenisBudgeting.equals(that.idJenisBudgeting) : that.idJenisBudgeting != null)
            return false;
        if (tipe != null ? !tipe.equals(that.tipe) : that.tipe != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nama != null ? nama.hashCode() : 0);
        result = 31 * result + (rekeningId != null ? rekeningId.hashCode() : 0);
        result = 31 * result + (idJenisBudgeting != null ? idJenisBudgeting.hashCode() : 0);
        result = 31 * result + (tipe != null ? tipe.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}