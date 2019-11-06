package com.neurix.hris.transaksi.medicalrecord.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class Pengobatan extends BaseModel implements Serializable {
    private String pengobatanId;
    private String biayaPengobatanId;
    private String medicalRecordId;
    private BigDecimal jumlah;
    private String status;
    private String namaBiayaPengobatan;
    private String biaya;
    private String nomor;

    private boolean add;
    private boolean edit;
    private boolean flagYes=true;
    private String project;

    @Override
    public boolean isFlagYes() {
        return flagYes;
    }

    public void setFlagYes(boolean flagYes) {
        this.flagYes = flagYes;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getNamaBiayaPengobatan() {
        return namaBiayaPengobatan;
    }

    public void setNamaBiayaPengobatan(String namaBiayaPengobatan) {
        this.namaBiayaPengobatan = namaBiayaPengobatan;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public String getPengobatanId() {
        return pengobatanId;
    }

    public void setPengobatanId(String pengobatanId) {
        this.pengobatanId = pengobatanId;
    }

    public String getBiayaPengobatanId() {
        return biayaPengobatanId;
    }

    public void setBiayaPengobatanId(String biayaPengobatanId) {
        this.biayaPengobatanId = biayaPengobatanId;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }
}
