package com.neurix.simrs.transaksi.checkupdetail.model;

import java.sql.Timestamp;
import java.util.Objects;

public class UploadPendukungPemeriksaan {
    private String idUpload;
    private String idDetailCheckup;
    private String keterangan;
    private String urlImg;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private String lastUpdateWho;
    private Timestamp lastUpdate;

    public String getIdUpload() {
        return idUpload;
    }

    public void setIdUpload(String idUpload) {
        this.idUpload = idUpload;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadPendukungPemeriksaan that = (UploadPendukungPemeriksaan) o;
        return Objects.equals(idUpload, that.idUpload) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(urlImg, that.urlImg) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUpload, idDetailCheckup, keterangan, urlImg, flag, action, createdWho, createdDate, lastUpdateWho, lastUpdate);
    }
}
