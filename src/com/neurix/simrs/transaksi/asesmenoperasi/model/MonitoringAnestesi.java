package com.neurix.simrs.transaksi.asesmenoperasi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class MonitoringAnestesi {
    private String idMonitoringAnestesi;
    private String idDetailCheckup;
    private String waktu;
    private String rr;
    private String nadi;
    private String tensi;
    private String anest;
    private String o2;
    private String n2O;
    private String ethran;
    private String iso;
    private String sevo;
    private String infus;
    private String keterangan;
    private String flag;
    private String jenis;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

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

    public String getTensi() {
        return tensi;
    }

    public void setTensi(String tensi) {
        this.tensi = tensi;
    }

    public String getAnest() {
        return anest;
    }

    public void setAnest(String anest) {
        this.anest = anest;
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

    public String getEthran() {
        return ethran;
    }

    public void setEthran(String ethran) {
        this.ethran = ethran;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getSevo() {
        return sevo;
    }

    public void setSevo(String sevo) {
        this.sevo = sevo;
    }

    public String getInfus() {
        return infus;
    }

    public void setInfus(String infus) {
        this.infus = infus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitoringAnestesi that = (MonitoringAnestesi) o;
        return Objects.equals(idMonitoringAnestesi, that.idMonitoringAnestesi) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(waktu, that.waktu) &&
                Objects.equals(rr, that.rr) &&
                Objects.equals(nadi, that.nadi) &&
                Objects.equals(tensi, that.tensi) &&
                Objects.equals(anest, that.anest) &&
                Objects.equals(o2, that.o2) &&
                Objects.equals(n2O, that.n2O) &&
                Objects.equals(ethran, that.ethran) &&
                Objects.equals(iso, that.iso) &&
                Objects.equals(sevo, that.sevo) &&
                Objects.equals(infus, that.infus) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(jenis, that.jenis) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMonitoringAnestesi, idDetailCheckup, waktu, rr, nadi, tensi, anest, o2, n2O, ethran, iso, sevo, infus, keterangan, jenis, action, createdWho, createdDate, lastUpdate, lastUpdateWho);
    }
}
