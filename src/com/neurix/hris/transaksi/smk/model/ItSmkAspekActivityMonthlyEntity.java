package com.neurix.hris.transaksi.smk.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItSmkAspekActivityMonthlyEntity implements Serializable {
    private String aspekActivityMonthly;
    private String evaluasiPegawaiAspekId;
    private int bulan;
    private double nilai;
    private double rataRata;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public double getRataRata() {
        return rataRata;
    }

    public void setRataRata(double rataRata) {
        this.rataRata = rataRata;
    }

    private ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAspekActivityMonthly() {
        return aspekActivityMonthly;
    }

    public void setAspekActivityMonthly(String aspekActivityMonthly) {
        this.aspekActivityMonthly = aspekActivityMonthly;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
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

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ItSmkEvaluasiPegawaiAspekEntity getItSmkEvaluasiPegawaiAspekEntity() {
        return itSmkEvaluasiPegawaiAspekEntity;
    }

    public void setItSmkEvaluasiPegawaiAspekEntity(ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity) {
        this.itSmkEvaluasiPegawaiAspekEntity = itSmkEvaluasiPegawaiAspekEntity;
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

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }
}
