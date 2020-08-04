package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ItAkunProsesPpnKdEntity implements Serializable {
    private Integer noUrut;
    private String prosesPpnKdId;
    private String perhitunganPpnKdId;
    private String branchId;
    private String branchName;
    private BigDecimal keluaranUnit;
    private BigDecimal masukanUnit;
    private BigDecimal keluaranKoreksi;
    private BigDecimal masukanKoreksi;
    private BigDecimal keluaranDiambilKp;
    private BigDecimal masukanDiambilKp;
    private BigDecimal b4;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;
    private String flag;

    public Integer getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(Integer noUrut) {
        this.noUrut = noUrut;
    }

    public String getPerhitunganPpnKdId() {
        return perhitunganPpnKdId;
    }

    public void setPerhitunganPpnKdId(String perhitunganPpnKdId) {
        this.perhitunganPpnKdId = perhitunganPpnKdId;
    }

    public String getProsesPpnKdId() {
        return prosesPpnKdId;
    }

    public void setProsesPpnKdId(String prosesPpnKdId) {
        this.prosesPpnKdId = prosesPpnKdId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public BigDecimal getKeluaranUnit() {
        return keluaranUnit;
    }

    public void setKeluaranUnit(BigDecimal keluaranUnit) {
        this.keluaranUnit = keluaranUnit;
    }

    public BigDecimal getMasukanUnit() {
        return masukanUnit;
    }

    public void setMasukanUnit(BigDecimal masukanUnit) {
        this.masukanUnit = masukanUnit;
    }

    public BigDecimal getKeluaranKoreksi() {
        return keluaranKoreksi;
    }

    public void setKeluaranKoreksi(BigDecimal keluaranKoreksi) {
        this.keluaranKoreksi = keluaranKoreksi;
    }

    public BigDecimal getMasukanKoreksi() {
        return masukanKoreksi;
    }

    public void setMasukanKoreksi(BigDecimal masukanKoreksi) {
        this.masukanKoreksi = masukanKoreksi;
    }

    public BigDecimal getKeluaranDiambilKp() {
        return keluaranDiambilKp;
    }

    public void setKeluaranDiambilKp(BigDecimal keluaranDiambilKp) {
        this.keluaranDiambilKp = keluaranDiambilKp;
    }

    public BigDecimal getMasukanDiambilKp() {
        return masukanDiambilKp;
    }

    public void setMasukanDiambilKp(BigDecimal masukanDiambilKp) {
        this.masukanDiambilKp = masukanDiambilKp;
    }

    public BigDecimal getB4() {
        return b4;
    }

    public void setB4(BigDecimal b4) {
        this.b4 = b4;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunProsesPpnKdEntity that = (ItAkunProsesPpnKdEntity) o;
        return Objects.equals(prosesPpnKdId, that.prosesPpnKdId) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(branchName, that.branchName) &&
                Objects.equals(keluaranUnit, that.keluaranUnit) &&
                Objects.equals(masukanUnit, that.masukanUnit) &&
                Objects.equals(keluaranKoreksi, that.keluaranKoreksi) &&
                Objects.equals(masukanKoreksi, that.masukanKoreksi) &&
                Objects.equals(keluaranDiambilKp, that.keluaranDiambilKp) &&
                Objects.equals(masukanDiambilKp, that.masukanDiambilKp) &&
                Objects.equals(b4, that.b4) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prosesPpnKdId, branchId, branchName, keluaranUnit, masukanUnit, keluaranKoreksi, masukanKoreksi, keluaranDiambilKp, masukanDiambilKp, b4, createdDate, createdWho, lastUpdate, lastUpdateWho, action, flag);
    }
}
