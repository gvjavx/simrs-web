package com.neurix.akuntansi.transaksi.tutupperiod.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 18/03/20.
 */
public class TutupPeriod {
    private String id;
    private String tahun;
    private String bulan;
    private String stTglAkhirPeriod;
    private String stTglAwalPeriod;
    private String unit;
    private String idTutupPeriod;
    private String rekeningId;
    private String parentId;
    private String kodeRekening;
    private String namaKodeRekening;
    private BigDecimal jumlahDebit;
    private BigDecimal jumlahKredit;
    private String posisi;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flagTutup;
    private String idDetailCheckup;

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getStTglAkhirPeriod() {
        return stTglAkhirPeriod;
    }

    public void setStTglAkhirPeriod(String stTglAkhirPeriod) {
        this.stTglAkhirPeriod = stTglAkhirPeriod;
    }

    public String getStTglAwalPeriod() {
        return stTglAwalPeriod;
    }

    public void setStTglAwalPeriod(String stTglAwalPeriod) {
        this.stTglAwalPeriod = stTglAwalPeriod;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getFlagTutup() {
        return flagTutup;
    }

    public void setFlagTutup(String flagTutup) {
        this.flagTutup = flagTutup;
    }

    public String getIdTutupPeriod() {
        return idTutupPeriod;
    }

    public void setIdTutupPeriod(String idTutupPeriod) {
        this.idTutupPeriod = idTutupPeriod;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public BigDecimal getJumlahDebit() {
        return jumlahDebit;
    }

    public void setJumlahDebit(BigDecimal jumlahDebit) {
        this.jumlahDebit = jumlahDebit;
    }

    public BigDecimal getJumlahKredit() {
        return jumlahKredit;
    }

    public void setJumlahKredit(BigDecimal jumlahKredit) {
        this.jumlahKredit = jumlahKredit;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNamaKodeRekening() {
        return namaKodeRekening;
    }

    public void setNamaKodeRekening(String namaKodeRekening) {
        this.namaKodeRekening = namaKodeRekening;
    }
}
