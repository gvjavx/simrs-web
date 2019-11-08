package com.neurix.simrs.transaksi.rawatinap.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class ItSimrsRawatInapEntity {
    private String idRawatInap;
    private String idDetailCheckup;
    private String noCheckup;
    private String idRuangan;
    private String namaRangan;
    private String noRuangan;
    private String keterangan;
    private BigInteger tarif;
    private Timestamp tglMasuk;
    private Timestamp tglKeluar;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdRawatInap() {
        return idRawatInap;
    }

    public void setIdRawatInap(String idRawatInap) {
        this.idRawatInap = idRawatInap;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRangan() {
        return namaRangan;
    }

    public void setNamaRangan(String namaRangan) {
        this.namaRangan = namaRangan;
    }

    public String getNoRuangan() {
        return noRuangan;
    }

    public void setNoRuangan(String noRuangan) {
        this.noRuangan = noRuangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigInteger getTarif() {
        return tarif;
    }

    public void setTarif(BigInteger tarif) {
        this.tarif = tarif;
    }

    public Timestamp getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(Timestamp tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public Timestamp getTglKeluar() {
        return tglKeluar;
    }

    public void setTglKeluar(Timestamp tglKeluar) {
        this.tglKeluar = tglKeluar;
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

    @Override
    public String toString() {
        return "ItSimrsRawatInapEntity{" +
                "idRawatInap='" + idRawatInap + '\'' +
                ", idDetailCheckup='" + idDetailCheckup + '\'' +
                ", noCheckup='" + noCheckup + '\'' +
                ", idRuangan='" + idRuangan + '\'' +
                ", namaRangan='" + namaRangan + '\'' +
                ", noRuangan='" + noRuangan + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", tarif=" + tarif +
                ", tglMasuk=" + tglMasuk +
                ", tglKeluar=" + tglKeluar +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                '}';
    }
}
