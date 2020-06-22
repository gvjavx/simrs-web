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
    private String jenisNkRmNrm;
    private String nkRmNrm;
    private String jenisTPieceJRise;
    private String tPieceJRise;
    private String tipeVentilasi;
    private String jenisPeepCpapEt;
    private String peepCpapEt;
    private String frekwensi;
    private String tv;
    private String mv;
    private String jenisPSupportPAsb;
    private String pSupportPAsb;
    private String jenisPInsPCon;
    private String pInsPCon;
    private String triger;
    private String insTime;
    private String flow;
    private String jenisFioKon;
    private String fioKon;
    private String jenisUkuranKedalamaanEtt;
    private String ukuranKedalamaanEtt;
    private String spo;
    private String secret;
    private String keterangan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

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

    public String getJenisNkRmNrm() {
        return jenisNkRmNrm;
    }

    public void setJenisNkRmNrm(String jenisNkRmNrm) {
        this.jenisNkRmNrm = jenisNkRmNrm;
    }

    public String getNkRmNrm() {
        return nkRmNrm;
    }

    public void setNkRmNrm(String nkRmNrm) {
        this.nkRmNrm = nkRmNrm;
    }

    public String getJenisTPieceJRise() {
        return jenisTPieceJRise;
    }

    public void setJenisTPieceJRise(String jenisTPieceJRise) {
        this.jenisTPieceJRise = jenisTPieceJRise;
    }

    public String gettPieceJRise() {
        return tPieceJRise;
    }

    public void settPieceJRise(String tPieceJRise) {
        this.tPieceJRise = tPieceJRise;
    }

    public String getTipeVentilasi() {
        return tipeVentilasi;
    }

    public void setTipeVentilasi(String tipeVentilasi) {
        this.tipeVentilasi = tipeVentilasi;
    }

    public String getJenisPeepCpapEt() {
        return jenisPeepCpapEt;
    }

    public void setJenisPeepCpapEt(String jenisPeepCpapEt) {
        this.jenisPeepCpapEt = jenisPeepCpapEt;
    }

    public String getPeepCpapEt() {
        return peepCpapEt;
    }

    public void setPeepCpapEt(String peepCpapEt) {
        this.peepCpapEt = peepCpapEt;
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

    public String getJenisPSupportPAsb() {
        return jenisPSupportPAsb;
    }

    public void setJenisPSupportPAsb(String jenisPSupportPAsb) {
        this.jenisPSupportPAsb = jenisPSupportPAsb;
    }

    public String getpSupportPAsb() {
        return pSupportPAsb;
    }

    public void setpSupportPAsb(String pSupportPAsb) {
        this.pSupportPAsb = pSupportPAsb;
    }

    public String getJenisPInsPCon() {
        return jenisPInsPCon;
    }

    public void setJenisPInsPCon(String jenisPInsPCon) {
        this.jenisPInsPCon = jenisPInsPCon;
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

    public String getJenisFioKon() {
        return jenisFioKon;
    }

    public void setJenisFioKon(String jenisFioKon) {
        this.jenisFioKon = jenisFioKon;
    }

    public String getFioKon() {
        return fioKon;
    }

    public void setFioKon(String fioKon) {
        this.fioKon = fioKon;
    }

    public String getJenisUkuranKedalamaanEtt() {
        return jenisUkuranKedalamaanEtt;
    }

    public void setJenisUkuranKedalamaanEtt(String jenisUkuranKedalamaanEtt) {
        this.jenisUkuranKedalamaanEtt = jenisUkuranKedalamaanEtt;
    }

    public String getUkuranKedalamaanEtt() {
        return ukuranKedalamaanEtt;
    }

    public void setUkuranKedalamaanEtt(String ukuranKedalamaanEtt) {
        this.ukuranKedalamaanEtt = ukuranKedalamaanEtt;
    }

    public String getSpo() {
        return spo;
    }

    public void setSpo(String spo) {
        this.spo = spo;
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
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
