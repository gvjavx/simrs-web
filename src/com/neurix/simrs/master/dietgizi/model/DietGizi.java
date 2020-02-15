package com.neurix.simrs.master.dietgizi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DietGizi extends BaseModel {

    private String idDietGizi;
    private String namaDietGizi;
    private BigDecimal tarif;
    private String branchId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDietGizi() {
        return idDietGizi;
    }

    public void setIdDietGizi(String idDietGizi) {
        this.idDietGizi = idDietGizi;
    }

    public String getNamaDietGizi() {
        return namaDietGizi;
    }

    public void setNamaDietGizi(String namaDietGizi) {
        this.namaDietGizi = namaDietGizi;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
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
}
