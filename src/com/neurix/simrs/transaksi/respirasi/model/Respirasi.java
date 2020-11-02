package com.neurix.simrs.transaksi.respirasi.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;
import java.sql.Timestamp;

public class Respirasi extends BaseModel {
    private String idRespirasi;
    private String idDetailCheckup;
    private String waktu;
    private Date tanggal;
    private String gcs;
    private String diameterPupil;
    private String reflekCahaya;
    private String tk;
    private String kk;
    private String o2;
    private String tipeVentilasi;
    private String peep;
    private String frekwensi;
    private String tv;
    private String mv;
    private String pSupportPAsb;
    private String pInsPCon;
    private String triger;
    private String insTime;
    private String flow;
    private String fioKon;
    private String ukuranEtt;
    private String kedalamanEtt;
    private String spo2;
    private String secret;
    private String keterangan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdRespirasi() {
        return idRespirasi;
    }

    public void setIdRespirasi(String idRespirasi) {
        this.idRespirasi = idRespirasi;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getGcs() {
        return gcs;
    }

    public void setGcs(String gcs) {
        this.gcs = gcs;
    }

    public String getDiameterPupil() {
        return diameterPupil;
    }

    public void setDiameterPupil(String diameterPupil) {
        this.diameterPupil = diameterPupil;
    }

    public String getReflekCahaya() {
        return reflekCahaya;
    }

    public void setReflekCahaya(String reflekCahaya) {
        this.reflekCahaya = reflekCahaya;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public String getTipeVentilasi() {
        return tipeVentilasi;
    }

    public void setTipeVentilasi(String tipeVentilasi) {
        this.tipeVentilasi = tipeVentilasi;
    }

    public String getPeep() {
        return peep;
    }

    public void setPeep(String peep) {
        this.peep = peep;
    }

    public String getFrekwensi() {
        return frekwensi;
    }

    public void setFrekwensi(String frekwensi) {
        this.frekwensi = frekwensi;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getMv() {
        return mv;
    }

    public void setMv(String mv) {
        this.mv = mv;
    }

    public String getpSupportPAsb() {
        return pSupportPAsb;
    }

    public void setpSupportPAsb(String pSupportPAsb) {
        this.pSupportPAsb = pSupportPAsb;
    }

    public String getpInsPCon() {
        return pInsPCon;
    }

    public void setpInsPCon(String pInsPCon) {
        this.pInsPCon = pInsPCon;
    }

    public String getTriger() {
        return triger;
    }

    public void setTriger(String triger) {
        this.triger = triger;
    }

    public String getInsTime() {
        return insTime;
    }

    public void setInsTime(String insTime) {
        this.insTime = insTime;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getFioKon() {
        return fioKon;
    }

    public void setFioKon(String fioKon) {
        this.fioKon = fioKon;
    }

    public String getUkuranEtt() {
        return ukuranEtt;
    }

    public void setUkuranEtt(String ukuranEtt) {
        this.ukuranEtt = ukuranEtt;
    }

    public String getKedalamanEtt() {
        return kedalamanEtt;
    }

    public void setKedalamanEtt(String kedalamanEtt) {
        this.kedalamanEtt = kedalamanEtt;
    }

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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
