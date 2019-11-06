package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.common.model.BaseModel;
import com.neurix.hris.master.statusRekruitment.model.ImStatusRekruitmentEntity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class ItRekruitmenStatusEntity extends BaseModel {
    private String rekruitmenStatusId;
    private String calonPegawaiId;
    private String statusRekruitmen;
    private String keterangan;
    private Timestamp tanggalProses;
    private String statusRekruitmenName;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImStatusRekruitmentEntity imStatusRekruitmentEntity;
    public String getStatusRekruitmenName() {
        return statusRekruitmenName;
    }

    public ImStatusRekruitmentEntity getImStatusRekruitmentEntity() {
        return imStatusRekruitmentEntity;
    }

    public void setImStatusRekruitmentEntity(ImStatusRekruitmentEntity imStatusRekruitmentEntity) {
        this.imStatusRekruitmentEntity = imStatusRekruitmentEntity;
    }

    public void setStatusRekruitmenName(String statusRekruitmenName) {
        this.statusRekruitmenName = statusRekruitmenName;
    }

    public String getRekruitmenStatusId() {
        return rekruitmenStatusId;
    }

    public void setRekruitmenStatusId(String rekruitmenStatusId) {
        this.rekruitmenStatusId = rekruitmenStatusId;
    }

    public String getCalonPegawaiId() {
        return calonPegawaiId;
    }

    public void setCalonPegawaiId(String calonPegawaiId) {
        this.calonPegawaiId = calonPegawaiId;
    }

    public String getStatusRekruitmen() {
        return statusRekruitmen;
    }

    public void setStatusRekruitmen(String statusRekruitmen) {
        this.statusRekruitmen = statusRekruitmen;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Timestamp getTanggalProses() {
        return tanggalProses;
    }

    public void setTanggalProses(Timestamp tanggalProses) {
        this.tanggalProses = tanggalProses;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}