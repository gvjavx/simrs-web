package com.neurix.simrs.transaksi.asesmenoperasi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsMonitoringAnestesiEntity {
    private String idMonitoringAnestesi;
    private String idDetailCheckup;
    private String waktu;
    private String rr;
    private String nadi;
    private String sistole;
    private String diastole;
    private String inhalasi;
    private String o2;
    private String n2O;
    private String keterangan;
    private String jenis;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getSistole() {
        return sistole;
    }

    public void setSistole(String sistole) {
        this.sistole = sistole;
    }

    public String getDiastole() {
        return diastole;
    }

    public void setDiastole(String diastole) {
        this.diastole = diastole;
    }

    public String getInhalasi() {
        return inhalasi;
    }

    public void setInhalasi(String inhalasi) {
        this.inhalasi = inhalasi;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIdMonitoringAnestesi() {
        return idMonitoringAnestesi;
    }

    public void setIdMonitoringAnestesi(String idMonitoringAnestesi) {
        this.idMonitoringAnestesi = idMonitoringAnestesi;
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

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getNadi() {
        return nadi;
    }

    public void setNadi(String nadi) {
        this.nadi = nadi;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public String getN2O() {
        return n2O;
    }

    public void setN2O(String n2O) {
        this.n2O = n2O;
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
