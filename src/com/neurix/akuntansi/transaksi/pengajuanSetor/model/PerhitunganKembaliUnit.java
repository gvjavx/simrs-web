package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

public class PerhitunganKembaliUnit extends BaseModel {
    private String perhitunganKembaliUnitId;
    private String perhitunganPpnKdId;
    private String branchId;
    private BigDecimal pajakMasukan;
    private BigDecimal perhitunganKembali1;
    private BigDecimal perhitunganKembali2;
    private BigDecimal perhitunganPmSudahDibuku;
    private BigDecimal perhitunganPmYmhDibuku;
    private String stPajakMasukan;
    private String stPerhitunganKembali1;
    private String stPerhitunganKembali2;
    private String stPerhitunganPmSudahDibuku;
    private String stPerhitunganPmYmhDibuku;
    private String tahun;
    private String bulan;

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

    public String getStPajakMasukan() {
        return stPajakMasukan;
    }

    public void setStPajakMasukan(String stPajakMasukan) {
        this.stPajakMasukan = stPajakMasukan;
    }

    public String getStPerhitunganKembali1() {
        return stPerhitunganKembali1;
    }

    public void setStPerhitunganKembali1(String stPerhitunganKembali1) {
        this.stPerhitunganKembali1 = stPerhitunganKembali1;
    }

    public String getStPerhitunganKembali2() {
        return stPerhitunganKembali2;
    }

    public void setStPerhitunganKembali2(String stPerhitunganKembali2) {
        this.stPerhitunganKembali2 = stPerhitunganKembali2;
    }

    public String getStPerhitunganPmSudahDibuku() {
        return stPerhitunganPmSudahDibuku;
    }

    public void setStPerhitunganPmSudahDibuku(String stPerhitunganPmSudahDibuku) {
        this.stPerhitunganPmSudahDibuku = stPerhitunganPmSudahDibuku;
    }

    public String getStPerhitunganPmYmhDibuku() {
        return stPerhitunganPmYmhDibuku;
    }

    public void setStPerhitunganPmYmhDibuku(String stPerhitunganPmYmhDibuku) {
        this.stPerhitunganPmYmhDibuku = stPerhitunganPmYmhDibuku;
    }
}
