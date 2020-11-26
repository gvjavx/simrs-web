package com.neurix.akuntansi.transaksi.saldoakhir.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 20/03/20.
 */
public class SaldoAkhir {

    private String saldoAkhirId;
    private String rekeningId;
    private String periode;
    private BigDecimal jumlahDebit;
    private BigDecimal jumlahKredit;
    private BigDecimal saldo;
    private String posisi;
    private String branchId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String masterId;
    private String pasienId;
    private String divisiId;
    private Integer bulan;
    private String kdBarang;
    private String namaKodeRekening;
    private String flagMaster;
    private String flagDivisi;
    private String tipeCoa;

    public String getTipeCoa() {
        return tipeCoa;
    }

    public void setTipeCoa(String tipeCoa) {
        this.tipeCoa = tipeCoa;
    }

    public String getFlagMaster() {
        return flagMaster;
    }

    public void setFlagMaster(String flagMaster) {
        this.flagMaster = flagMaster;
    }

    public String getFlagDivisi() {
        return flagDivisi;
    }

    public void setFlagDivisi(String flagDivisi) {
        this.flagDivisi = flagDivisi;
    }

    public String getNamaKodeRekening() {
        return namaKodeRekening;
    }

    public void setNamaKodeRekening(String namaKodeRekening) {
        this.namaKodeRekening = namaKodeRekening;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public Integer getBulan() {
        return bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getSaldoAkhirId() {
        return saldoAkhirId;
    }

    public void setSaldoAkhirId(String saldoAkhirId) {
        this.saldoAkhirId = saldoAkhirId;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getPasienId() {
        return pasienId;
    }

    public void setPasienId(String pasienId) {
        this.pasienId = pasienId;
    }
}
