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
    private String idJenisPeriksaPasien;
    private String masterId;
    private String divisiId;
    private String saldoAkhirId;
    private String periode;
    private String pasienId;
    private String kdBarang;
    private String tipeJurnalId;
    private String transId;
    private String noJurnal;
    private String flagDesemberA;
    private String flagDesemberB;
    private String tipePeriode;
    private BigDecimal saldo;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipePeriode() {
        return tipePeriode;
    }

    public void setTipePeriode(String tipePeriode) {
        this.tipePeriode = tipePeriode;
    }

    public String getFlagDesemberA() {
        return flagDesemberA;
    }

    public void setFlagDesemberA(String flagDesemberA) {
        this.flagDesemberA = flagDesemberA;
    }

    public String getFlagDesemberB() {
        return flagDesemberB;
    }

    public void setFlagDesemberB(String flagDesemberB) {
        this.flagDesemberB = flagDesemberB;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTipeJurnalId() {
        return tipeJurnalId;
    }

    public void setTipeJurnalId(String tipeJurnalId) {
        this.tipeJurnalId = tipeJurnalId;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public String getPasienId() {
        return pasienId;
    }

    public void setPasienId(String pasienId) {
        this.pasienId = pasienId;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getSaldoAkhirId() {
        return saldoAkhirId;
    }

    public void setSaldoAkhirId(String saldoAkhirId) {
        this.saldoAkhirId = saldoAkhirId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

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
