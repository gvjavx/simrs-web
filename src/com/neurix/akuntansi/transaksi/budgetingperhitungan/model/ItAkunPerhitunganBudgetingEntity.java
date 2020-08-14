package com.neurix.akuntansi.transaksi.budgetingperhitungan.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 14/08/20.
 */
public class ItAkunPerhitunganBudgetingEntity {
    private String id;
    private String idParameterBudgeting;
    private BigInteger nilai;
    private String operator;
    private String branchId;
    private String tahun;
    private Integer urutan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdParameterBudgeting() {
        return idParameterBudgeting;
    }

    public void setIdParameterBudgeting(String idParameterBudgeting) {
        this.idParameterBudgeting = idParameterBudgeting;
    }

    public BigInteger getNilai() {
        return nilai;
    }

    public void setNilai(BigInteger nilai) {
        this.nilai = nilai;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
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

        ItAkunPerhitunganBudgetingEntity that = (ItAkunPerhitunganBudgetingEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idParameterBudgeting != null ? !idParameterBudgeting.equals(that.idParameterBudgeting) : that.idParameterBudgeting != null)
            return false;
        if (nilai != null ? !nilai.equals(that.nilai) : that.nilai != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (urutan != null ? !urutan.equals(that.urutan) : that.urutan != null) return false;
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
        result = 31 * result + (idParameterBudgeting != null ? idParameterBudgeting.hashCode() : 0);
        result = 31 * result + (nilai != null ? nilai.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (urutan != null ? urutan.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
