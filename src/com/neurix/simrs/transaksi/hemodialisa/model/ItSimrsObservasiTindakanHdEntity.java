package com.neurix.simrs.transaksi.hemodialisa.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsObservasiTindakanHdEntity {
    private String idObservasiTindakanHd;
    private String idDetailCheckup;
    private String waktu;
    private String observasi;
    private String qb;
    private String tensi;
    private String nadi;
    private String suhu;
    private String rr;
    private String cairanMasuk;
    private String makanMinum;
    private String muntah;
    private String uf;
    private String jenis;
    private String keterangan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String ttd;
    private String namaTerang;
    private String sip;

    public String getTtd() {
        return ttd;
    }

    public void setTtd(String ttd) {
        this.ttd = ttd;
    }

    public String getNamaTerang() {
        return namaTerang;
    }

    public void setNamaTerang(String namaTerang) {
        this.namaTerang = namaTerang;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getIdObservasiTindakanHd() {
        return idObservasiTindakanHd;
    }

    public void setIdObservasiTindakanHd(String idObservasiTindakanHd) {
        this.idObservasiTindakanHd = idObservasiTindakanHd;
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

    public String getObservasi() {
        return observasi;
    }

    public void setObservasi(String observasi) {
        this.observasi = observasi;
    }

    public String getQb() {
        return qb;
    }

    public void setQb(String qb) {
        this.qb = qb;
    }

    public String getTensi() {
        return tensi;
    }

    public void setTensi(String tensi) {
        this.tensi = tensi;
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

    public String getCairanMasuk() {
        return cairanMasuk;
    }

    public void setCairanMasuk(String cairanMasuk) {
        this.cairanMasuk = cairanMasuk;
    }

    public String getMakanMinum() {
        return makanMinum;
    }

    public void setMakanMinum(String makanMinum) {
        this.makanMinum = makanMinum;
    }

    public String getMuntah() {
        return muntah;
    }

    public void setMuntah(String muntah) {
        this.muntah = muntah;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItSimrsObservasiTindakanHdEntity that = (ItSimrsObservasiTindakanHdEntity) o;
        return Objects.equals(idObservasiTindakanHd, that.idObservasiTindakanHd) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(waktu, that.waktu) &&
                Objects.equals(observasi, that.observasi) &&
                Objects.equals(qb, that.qb) &&
                Objects.equals(tensi, that.tensi) &&
                Objects.equals(nadi, that.nadi) &&
                Objects.equals(suhu, that.suhu) &&
                Objects.equals(rr, that.rr) &&
                Objects.equals(cairanMasuk, that.cairanMasuk) &&
                Objects.equals(makanMinum, that.makanMinum) &&
                Objects.equals(muntah, that.muntah) &&
                Objects.equals(uf, that.uf) &&
                Objects.equals(jenis, that.jenis) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idObservasiTindakanHd, idDetailCheckup, waktu, observasi, qb, tensi, nadi, suhu, rr, cairanMasuk, makanMinum, muntah, uf, jenis, keterangan, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho);
    }
}
