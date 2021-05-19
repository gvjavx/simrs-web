package com.neurix.simrs.transaksi.catatanterintegrasi.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItSimrsCatatanTerintegrasiEntity implements Serializable {

    private String idCatatanTerintegrasi;
    private String idDetailCheckup;
    private Timestamp waktu;
    private String ppa;
    private String subjective;
    private String intruksi;
    private String ttdPetugas;
    private String ttdDpjp;
    private String keterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String nadi;
    private String suhu;
    private String rr;
    private String tensi;
    private String objective;
    private String assesment;
    private String planning;
    private String namaDokter;
    private String namaPetugas;
    private String sipDokter;
    private String sipPetugas;
    private String kesadaran;
    private String spo2;
    private String o2;
    private String ews;
    private String kesimpulan;
    private String monitoring;
    private String dataEws;
    private String namaPemberi;
    private String sipPemberi;
    private String ttdPemberi;
    private String namaPenerima;
    private String sipPenerima;
    private String ttdPenerima;
    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNamaPemberi() {
        return namaPemberi;
    }

    public void setNamaPemberi(String namaPemberi) {
        this.namaPemberi = namaPemberi;
    }

    public String getSipPemberi() {
        return sipPemberi;
    }

    public void setSipPemberi(String sipPemberi) {
        this.sipPemberi = sipPemberi;
    }

    public String getTtdPemberi() {
        return ttdPemberi;
    }

    public void setTtdPemberi(String ttdPemberi) {
        this.ttdPemberi = ttdPemberi;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getSipPenerima() {
        return sipPenerima;
    }

    public void setSipPenerima(String sipPenerima) {
        this.sipPenerima = sipPenerima;
    }

    public String getTtdPenerima() {
        return ttdPenerima;
    }

    public void setTtdPenerima(String ttdPenerima) {
        this.ttdPenerima = ttdPenerima;
    }

    public String getDataEws() {
        return dataEws;
    }

    public void setDataEws(String dataEws) {
        this.dataEws = dataEws;
    }

    public String getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
    }

    public String getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(String monitoring) {
        this.monitoring = monitoring;
    }

    public String getKesadaran() {
        return kesadaran;
    }

    public void setKesadaran(String kesadaran) {
        this.kesadaran = kesadaran;
    }

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public String getEws() {
        return ews;
    }

    public void setEws(String ews) {
        this.ews = ews;
    }

    public String getNadi() {
        return nadi;
    }

    public void setNadi(String nadi) {
        this.nadi = nadi;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getTensi() {
        return tensi;
    }

    public void setTensi(String tensi) {
        this.tensi = tensi;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getAssesment() {
        return assesment;
    }

    public void setAssesment(String assesment) {
        this.assesment = assesment;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    public String getSipDokter() {
        return sipDokter;
    }

    public void setSipDokter(String sipDokter) {
        this.sipDokter = sipDokter;
    }

    public String getSipPetugas() {
        return sipPetugas;
    }

    public void setSipPetugas(String sipPetugas) {
        this.sipPetugas = sipPetugas;
    }

    public String getIdCatatanTerintegrasi() {
        return idCatatanTerintegrasi;
    }

    public void setIdCatatanTerintegrasi(String idCatatanTerintegrasi) {
        this.idCatatanTerintegrasi = idCatatanTerintegrasi;
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

    public String getPpa() {
        return ppa;
    }

    public void setPpa(String ppa) {
        this.ppa = ppa;
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public String getIntruksi() {
        return intruksi;
    }

    public void setIntruksi(String intruksi) {
        this.intruksi = intruksi;
    }

    public String getTtdPetugas() {
        return ttdPetugas;
    }

    public void setTtdPetugas(String ttdPetugas) {
        this.ttdPetugas = ttdPetugas;
    }

    public String getTtdDpjp() {
        return ttdDpjp;
    }

    public void setTtdDpjp(String ttdDpjp) {
        this.ttdDpjp = ttdDpjp;
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
