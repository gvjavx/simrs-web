package com.neurix.hris.master.biodata.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PelatihanJabatanUser extends BaseModel {
    private String pelatihanUserId;
    private String pelatihanJabatanId;
    private String pelatihanJabatanName;
    private String nip;
    private String lembaga;
    private String angkatan;
    private String tahun;
    private String status;
    private double nilai;

    public String getPelatihanJabatanName() {
        return pelatihanJabatanName;
    }

    public void setPelatihanJabatanName(String pelatihanJabatanName) {
        this.pelatihanJabatanName = pelatihanJabatanName;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getLembaga() {
        return lembaga;
    }

    public void setLembaga(String lembaga) {
        this.lembaga = lembaga;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPelatihanJabatanId() {
        return pelatihanJabatanId;
    }

    public void setPelatihanJabatanId(String pelatihanJabatanId) {
        this.pelatihanJabatanId = pelatihanJabatanId;
    }

    public String getPelatihanUserId() {
        return pelatihanUserId;
    }

    public void setPelatihanUserId(String pelatihanUserId) {
        this.pelatihanUserId = pelatihanUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}