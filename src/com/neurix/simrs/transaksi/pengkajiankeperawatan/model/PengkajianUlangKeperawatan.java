package com.neurix.simrs.transaksi.pengkajiankeperawatan.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;
import java.sql.Timestamp;

public class PengkajianUlangKeperawatan extends BaseModel {

    private String idPengkajianUlangKeperawatan;
    private String idDetailCheckup;
    private Date tanggal;
    private String parameter;
    private String kodeParameter;
    private String pagi;
    private String siang;
    private String malam;
    private String keterangan;
    private String jenis;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String waktu;
    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKodeParameter() {
        return kodeParameter;
    }

    public void setKodeParameter(String kodeParameter) {
        this.kodeParameter = kodeParameter;
    }

    public String getIdPengkajianUlangKeperawatan() {
        return idPengkajianUlangKeperawatan;
    }

    public void setIdPengkajianUlangKeperawatan(String idPengkajianUlangKeperawatan) {
        this.idPengkajianUlangKeperawatan = idPengkajianUlangKeperawatan;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getPagi() {
        return pagi;
    }

    public void setPagi(String pagi) {
        this.pagi = pagi;
    }

    public String getSiang() {
        return siang;
    }

    public void setSiang(String siang) {
        this.siang = siang;
    }

    public String getMalam() {
        return malam;
    }

    public void setMalam(String malam) {
        this.malam = malam;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
