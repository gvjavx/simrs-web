package com.neurix.hris.master.libur.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Libur extends BaseModel{
    private String liburId;
    private String liburTahun;
    private String stTanggal;
    private String tipeLiburId;
    private Timestamp tanggal;
    private String liburKeterangan;
    private String tipeLiburName;

    private String stCreatedDate;
    private String stLastUpdate;

    public String getLiburId() {
        return liburId;
    }

    public void setLiburId(String liburId) {
        this.liburId = liburId;
    }

    public String getLiburTahun() {
        return liburTahun;
    }

    public void setLiburTahun(String liburTahun) {
        this.liburTahun = liburTahun;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getTipeLiburId() {
        return tipeLiburId;
    }

    public void setTipeLiburId(String tipeLiburId) {
        this.tipeLiburId = tipeLiburId;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public String getLiburKeterangan() {
        return liburKeterangan;
    }

    public void setLiburKeterangan(String liburKeterangan) {
        this.liburKeterangan = liburKeterangan;
    }

    public String getTipeLiburName() {
        return tipeLiburName;
    }

    public void setTipeLiburName(String tipeLiburName) {
        this.tipeLiburName = tipeLiburName;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }
}
