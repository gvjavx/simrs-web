package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ItAkunPerhitunganKembaliUnitEntity {
    private String perhitunganKembaliUnitId;
    private String perhitunganPpnKdId;
    private String branchId;
    private BigDecimal pajakMasukan;
    private BigDecimal perhitunganKembali1;
    private BigDecimal perhitunganKembali2;
    private BigDecimal perhitunganPmSudahDibuku;
    private BigDecimal perhitunganPmYmhDibuku;
    private String bulan;
    private String tahun;
    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public String getPerhitunganKembaliUnitId() {
        return perhitunganKembaliUnitId;
    }

    public void setPerhitunganKembaliUnitId(String perhitunganKembaliUnitId) {
        this.perhitunganKembaliUnitId = perhitunganKembaliUnitId;
    }

    public String getPerhitunganPpnKdId() {
        return perhitunganPpnKdId;
    }

    public void setPerhitunganPpnKdId(String perhitunganPpnKdId) {
        this.perhitunganPpnKdId = perhitunganPpnKdId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getPajakMasukan() {
        return pajakMasukan;
    }

    public void setPajakMasukan(BigDecimal pajakMasukan) {
        this.pajakMasukan = pajakMasukan;
    }

    public BigDecimal getPerhitunganKembali1() {
        return perhitunganKembali1;
    }

    public void setPerhitunganKembali1(BigDecimal perhitunganKembali1) {
        this.perhitunganKembali1 = perhitunganKembali1;
    }

    public BigDecimal getPerhitunganKembali2() {
        return perhitunganKembali2;
    }

    public void setPerhitunganKembali2(BigDecimal perhitunganKembali2) {
        this.perhitunganKembali2 = perhitunganKembali2;
    }

    public BigDecimal getPerhitunganPmSudahDibuku() {
        return perhitunganPmSudahDibuku;
    }

    public void setPerhitunganPmSudahDibuku(BigDecimal perhitunganPmSudahDibuku) {
        this.perhitunganPmSudahDibuku = perhitunganPmSudahDibuku;
    }

    public BigDecimal getPerhitunganPmYmhDibuku() {
        return perhitunganPmYmhDibuku;
    }

    public void setPerhitunganPmYmhDibuku(BigDecimal perhitunganPmYmhDibuku) {
        this.perhitunganPmYmhDibuku = perhitunganPmYmhDibuku;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunPerhitunganKembaliUnitEntity that = (ItAkunPerhitunganKembaliUnitEntity) o;
        return Objects.equals(perhitunganKembaliUnitId, that.perhitunganKembaliUnitId) &&
                Objects.equals(perhitunganPpnKdId, that.perhitunganPpnKdId) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(pajakMasukan, that.pajakMasukan) &&
                Objects.equals(perhitunganKembali1, that.perhitunganKembali1) &&
                Objects.equals(perhitunganKembali2, that.perhitunganKembali2) &&
                Objects.equals(perhitunganPmSudahDibuku, that.perhitunganPmSudahDibuku) &&
                Objects.equals(perhitunganPmYmhDibuku, that.perhitunganPmYmhDibuku) &&
                Objects.equals(bulan, that.bulan) &&
                Objects.equals(tahun, that.tahun) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perhitunganKembaliUnitId, perhitunganPpnKdId, branchId, pajakMasukan, perhitunganKembali1, perhitunganKembali2, perhitunganPmSudahDibuku, perhitunganPmYmhDibuku, bulan, tahun, flag, action, createdWho, lastUpdateWho, createdDate, lastUpdate);
    }
}
