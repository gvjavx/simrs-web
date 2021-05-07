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

public class ItSmkEvaluasiPegawaiAspekActivityEntity implements Serializable {
    private String evaluasiPegawaiAspekActivityId;
    private String evaluasiPegawaiAspekDetailId;
    private double rataRata;
    private double nilaiActivity;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity;

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

    public String getEvaluasiPegawaiAspekActivityId() {
        return evaluasiPegawaiAspekActivityId;
    }

    public void setEvaluasiPegawaiAspekActivityId(String evaluasiPegawaiAspekActivityId) {
        this.evaluasiPegawaiAspekActivityId = evaluasiPegawaiAspekActivityId;
    }

    public String getEvaluasiPegawaiAspekDetailId() {
        return evaluasiPegawaiAspekDetailId;
    }

    public void setEvaluasiPegawaiAspekDetailId(String evaluasiPegawaiAspekDetailId) {
        this.evaluasiPegawaiAspekDetailId = evaluasiPegawaiAspekDetailId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ItSmkEvaluasiPegawaiAspekDetailEntity getItSmkEvaluasiPegawaiAspekDetailEntity() {
        return itSmkEvaluasiPegawaiAspekDetailEntity;
    }

    public void setItSmkEvaluasiPegawaiAspekDetailEntity(ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity) {
        this.itSmkEvaluasiPegawaiAspekDetailEntity = itSmkEvaluasiPegawaiAspekDetailEntity;
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

    public double getNilaiActivity() {
        return nilaiActivity;
    }

    public void setNilaiActivity(double nilaiActivity) {
        this.nilaiActivity = nilaiActivity;
    }

    public double getRataRata() {
        return rataRata;
    }

    public void setRataRata(double rataRata) {
        this.rataRata = rataRata;
    }
}
