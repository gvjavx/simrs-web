package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class RekruitmenStatus extends BaseModel {
    private String rekruitmenStatusId;
    private String calonPegawaiId;
    private String statusRekruitmen;
    private String keterangan;
    private Timestamp tanggalProses;
    private String stTanggalProses;
    private String statusRekruitmenName;
    public String getStTanggalProses() {
        return stTanggalProses;
    }

    public void setStTanggalProses(String stTanggalProses) {
        this.stTanggalProses = stTanggalProses;
    }

    public String getStatusRekruitmenName() {
        return statusRekruitmenName;
    }

    public void setStatusRekruitmenName(String statusRekruitmenName) {
        this.statusRekruitmenName = statusRekruitmenName;
    }

    public Timestamp getTanggalProses() {
        return tanggalProses;
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

    public Timestamp getTanggalProses(Timestamp timestamp) {
        return tanggalProses;
    }

    public void setTanggalProses(Timestamp tanggalProses) {
        this.tanggalProses = tanggalProses;
    }
}