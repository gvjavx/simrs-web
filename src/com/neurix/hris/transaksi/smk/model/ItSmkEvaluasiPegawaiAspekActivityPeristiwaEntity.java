package com.neurix.hris.transaksi.smk.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity implements Serializable {
    private String evaluasiActivityPeristiwaId;
    private String aspekActivityMonthlyId;
    private java.sql.Date tanggalKejadian ;
    private String kejadian;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ItSmkAspekActivityMonthlyEntity itSmkAspekActivityMonthlyEntity;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAspekActivityMonthlyId() {
        return aspekActivityMonthlyId;
    }

    public void setAspekActivityMonthlyId(String aspekActivityMonthlyId) {
        this.aspekActivityMonthlyId = aspekActivityMonthlyId;
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

    public String getEvaluasiActivityPeristiwaId() {
        return evaluasiActivityPeristiwaId;
    }

    public void setEvaluasiActivityPeristiwaId(String evaluasiActivityPeristiwaId) {
        this.evaluasiActivityPeristiwaId = evaluasiActivityPeristiwaId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ItSmkAspekActivityMonthlyEntity getItSmkAspekActivityMonthlyEntity() {
        return itSmkAspekActivityMonthlyEntity;
    }

    public void setItSmkAspekActivityMonthlyEntity(ItSmkAspekActivityMonthlyEntity itSmkAspekActivityMonthlyEntity) {
        this.itSmkAspekActivityMonthlyEntity = itSmkAspekActivityMonthlyEntity;
    }

    public String getKejadian() {
        return kejadian;
    }

    public void setKejadian(String kejadian) {
        this.kejadian = kejadian;
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

    public java.sql.Date getTanggalKejadian() {
        return tanggalKejadian;
    }

    public void setTanggalKejadian(java.sql.Date tanggalKejadian) {
        this.tanggalKejadian = tanggalKejadian;
    }
}
