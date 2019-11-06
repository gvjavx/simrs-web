package com.neurix.hris.master.biodata.model;

import com.neurix.hris.master.pelatihanJabatan.model.ImPelatihanJabatanEntitiy;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImtPelatihanJabatanUserEntity implements Serializable {
    private String pelatihanUserId;
    private String pelatihanJabatanId;
    private String nip;
    private String lembaga;
    private String angkatan;
    private String tahun;
    private String status;
    private double nilai;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImPelatihanJabatanEntitiy imPelatihanJabatanEntitiy;


    public ImPelatihanJabatanEntitiy getImPelatihanJabatanEntitiy() {
        return imPelatihanJabatanEntitiy;
    }

    public void setImPelatihanJabatanEntitiy(ImPelatihanJabatanEntitiy imPelatihanJabatanEntitiy) {
        this.imPelatihanJabatanEntitiy = imPelatihanJabatanEntitiy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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