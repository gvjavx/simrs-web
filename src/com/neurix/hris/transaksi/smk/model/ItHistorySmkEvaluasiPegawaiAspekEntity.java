package com.neurix.hris.transaksi.smk.model;

import com.neurix.hris.master.tipeAspek.model.ImTipeAspekEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItHistorySmkEvaluasiPegawaiAspekEntity implements Serializable {
    private int id ;
    private String evaluasiPegawaiAspekId ;
    private String evaluasiPegawaiId;
    private String tipeAspekId;
    private double pointPrestasi;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPointPrestasi(double pointPrestasi) {
        this.pointPrestasi = pointPrestasi;
    }

    private ImTipeAspekEntity imTipeAspekEntity;

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

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }

    public String getEvaluasiPegawaiId() {
        return evaluasiPegawaiId;
    }

    public void setEvaluasiPegawaiId(String evaluasiPegawaiId) {
        this.evaluasiPegawaiId = evaluasiPegawaiId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ImTipeAspekEntity getImTipeAspekEntity() {
        return imTipeAspekEntity;
    }

    public void setImTipeAspekEntity(ImTipeAspekEntity imTipeAspekEntity) {
        this.imTipeAspekEntity = imTipeAspekEntity;
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

    public Double getPointPrestasi() {
        return pointPrestasi;
    }

    public void setPointPrestasi(Double pointPrestasi) {
        this.pointPrestasi = pointPrestasi;
    }

    public String getTipeAspekId() {
        return tipeAspekId;
    }

    public void setTipeAspekId(String tipeAspekId) {
        this.tipeAspekId = tipeAspekId;
    }
}
