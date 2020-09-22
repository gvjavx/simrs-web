package com.neurix.akuntansi.transaksi.budgetingnilaidasar.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 07/08/20.
 */
public class BudgetingNilaiDasar {
    private String id;
    private String idNilaiDasar;
    private String tahun;
    private String branchId;
    private BigDecimal nilai;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private String lastUpdateWho;
    private Timestamp lastUpdate;
    private String keterangan;
    private String tipe;
    private BigDecimal nilaiTotal;

    public BigDecimal getNilaiTotal() {
        return nilaiTotal;
    }

    public void setNilaiTotal(BigDecimal nilaiTotal) {
        this.nilaiTotal = nilaiTotal;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNilaiDasar() {
        return idNilaiDasar;
    }

    public void setIdNilaiDasar(String idNilaiDasar) {
        this.idNilaiDasar = idNilaiDasar;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BudgetingNilaiDasar that = (BudgetingNilaiDasar) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idNilaiDasar != null ? !idNilaiDasar.equals(that.idNilaiDasar) : that.idNilaiDasar != null) return false;
        if (tahun != null ? !tahun.equals(that.tahun) : that.tahun != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (nilai != null ? !nilai.equals(that.nilai) : that.nilai != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idNilaiDasar != null ? idNilaiDasar.hashCode() : 0);
        result = 31 * result + (tahun != null ? tahun.hashCode() : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (nilai != null ? nilai.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
