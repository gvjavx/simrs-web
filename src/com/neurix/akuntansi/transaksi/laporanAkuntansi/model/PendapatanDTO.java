package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import java.math.BigDecimal;

public class PendapatanDTO {
    private String dokterId;
    private String dokterName;
    private String keterangan;
    private String activityId;
    private String activityName;
    private BigDecimal biaya;
    private String noTrans;
    private String koderingDokter;
    private String idTindakan;

    public String getDokterId() {
        return dokterId;
    }

    public void setDokterId(String dokterId) {
        this.dokterId = dokterId;
    }

    public String getDokterName() {
        return dokterName;
    }

    public void setDokterName(String dokterName) {
        this.dokterName = dokterName;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public BigDecimal getBiaya() {
        return biaya;
    }

    public void setBiaya(BigDecimal biaya) {
        this.biaya = biaya;
    }

    public String getNoTrans() {
        return noTrans;
    }

    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }

    public String getKoderingDokter() {
        return koderingDokter;
    }

    public void setKoderingDokter(String koderingDokter) {
        this.koderingDokter = koderingDokter;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }
}
