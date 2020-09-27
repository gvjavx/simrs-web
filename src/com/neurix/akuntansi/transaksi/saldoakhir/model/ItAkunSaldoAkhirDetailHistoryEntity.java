package com.neurix.akuntansi.transaksi.saldoakhir.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 17/09/20.
 */
public class ItAkunSaldoAkhirDetailHistoryEntity {
    private String id;
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
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String masterId;
    private String pasienId;
    private String divisiId;
    private String kdBarang;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
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

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItAkunSaldoAkhirDetailHistoryEntity that = (ItAkunSaldoAkhirDetailHistoryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (saldoAkhirId != null ? !saldoAkhirId.equals(that.saldoAkhirId) : that.saldoAkhirId != null) return false;
        if (rekeningId != null ? !rekeningId.equals(that.rekeningId) : that.rekeningId != null) return false;
        if (periode != null ? !periode.equals(that.periode) : that.periode != null) return false;
        if (jumlahDebit != null ? !jumlahDebit.equals(that.jumlahDebit) : that.jumlahDebit != null) return false;
        if (jumlahKredit != null ? !jumlahKredit.equals(that.jumlahKredit) : that.jumlahKredit != null) return false;
        if (saldo != null ? !saldo.equals(that.saldo) : that.saldo != null) return false;
        if (posisi != null ? !posisi.equals(that.posisi) : that.posisi != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (masterId != null ? !masterId.equals(that.masterId) : that.masterId != null) return false;
        if (pasienId != null ? !pasienId.equals(that.pasienId) : that.pasienId != null) return false;
        if (divisiId != null ? !divisiId.equals(that.divisiId) : that.divisiId != null) return false;
        if (kdBarang != null ? !kdBarang.equals(that.kdBarang) : that.kdBarang != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (saldoAkhirId != null ? saldoAkhirId.hashCode() : 0);
        result = 31 * result + (rekeningId != null ? rekeningId.hashCode() : 0);
        result = 31 * result + (periode != null ? periode.hashCode() : 0);
        result = 31 * result + (jumlahDebit != null ? jumlahDebit.hashCode() : 0);
        result = 31 * result + (jumlahKredit != null ? jumlahKredit.hashCode() : 0);
        result = 31 * result + (saldo != null ? saldo.hashCode() : 0);
        result = 31 * result + (posisi != null ? posisi.hashCode() : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (masterId != null ? masterId.hashCode() : 0);
        result = 31 * result + (pasienId != null ? pasienId.hashCode() : 0);
        result = 31 * result + (divisiId != null ? divisiId.hashCode() : 0);
        result = 31 * result + (kdBarang != null ? kdBarang.hashCode() : 0);
        return result;
    }
}
