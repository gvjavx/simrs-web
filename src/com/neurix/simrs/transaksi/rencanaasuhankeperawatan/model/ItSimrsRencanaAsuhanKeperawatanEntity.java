package com.neurix.simrs.transaksi.rencanaasuhankeperawatan.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ItSimrsRencanaAsuhanKeperawatanEntity implements Serializable {

    private String idRencanaAsuhanKeperawatan;
    private String idDetailCheckup;
    private Timestamp waktu;
    private String diagnosa;
    private String tujuan;
    private String intervensi;
    private String ttdPerawat;
    private String keterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdRencanaAsuhanKeperawatan() {
        return idRencanaAsuhanKeperawatan;
    }

    public void setIdRencanaAsuhanKeperawatan(String idRencanaAsuhanKeperawatan) {
        this.idRencanaAsuhanKeperawatan = idRencanaAsuhanKeperawatan;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Timestamp getWaktu() {
        return waktu;
    }

    public void setWaktu(Timestamp waktu) {
        this.waktu = waktu;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getIntervensi() {
        return intervensi;
    }

    public void setIntervensi(String intervensi) {
        this.intervensi = intervensi;
    }

    public String getTtdPerawat() {
        return ttdPerawat;
    }

    public void setTtdPerawat(String ttdPerawat) {
        this.ttdPerawat = ttdPerawat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
}
