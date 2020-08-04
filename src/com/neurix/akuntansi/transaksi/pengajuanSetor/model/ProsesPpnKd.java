package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

public class ProsesPpnKd extends BaseModel {
    private Integer no;
    private String perhitunganPpnKdId;
    private String prosesPpnKdId;
    private String branchId;
    private String branchName;
    private BigDecimal keluaranUnit= BigDecimal.ZERO;
    private BigDecimal masukanUnit= BigDecimal.ZERO;
    private BigDecimal keluaranKoreksi= BigDecimal.ZERO;
    private BigDecimal masukanKoreksi= BigDecimal.ZERO;
    private BigDecimal keluaranDiambilKp= BigDecimal.ZERO;
    private BigDecimal masukanDiambilKp= BigDecimal.ZERO;
    private BigDecimal b4 = BigDecimal.ZERO;

    private String stKeluaranUnit;
    private String stMasukanUnit;
    private String stKeluaranKoreksi;
    private String stMasukanKoreksi;
    private String stKeluaranDiambilKp;
    private String stMasukanDiambilKp;

    public String getProsesPpnKdId() {
        return prosesPpnKdId;
    }

    public void setProsesPpnKdId(String prosesPpnKdId) {
        this.prosesPpnKdId = prosesPpnKdId;
    }

    public String getPerhitunganPpnKdId() {
        return perhitunganPpnKdId;
    }

    public void setPerhitunganPpnKdId(String perhitunganPpnKdId) {
        this.perhitunganPpnKdId = perhitunganPpnKdId;
    }

    public String getStKeluaranUnit() {
        return stKeluaranUnit;
    }

    public void setStKeluaranUnit(String stKeluaranUnit) {
        this.stKeluaranUnit = stKeluaranUnit;
    }

    public String getStMasukanUnit() {
        return stMasukanUnit;
    }

    public void setStMasukanUnit(String stMasukanUnit) {
        this.stMasukanUnit = stMasukanUnit;
    }

    public String getStKeluaranKoreksi() {
        return stKeluaranKoreksi;
    }

    public void setStKeluaranKoreksi(String stKeluaranKoreksi) {
        this.stKeluaranKoreksi = stKeluaranKoreksi;
    }

    public String getStMasukanKoreksi() {
        return stMasukanKoreksi;
    }

    public void setStMasukanKoreksi(String stMasukanKoreksi) {
        this.stMasukanKoreksi = stMasukanKoreksi;
    }

    public String getStKeluaranDiambilKp() {
        return stKeluaranDiambilKp;
    }

    public void setStKeluaranDiambilKp(String stKeluaranDiambilKp) {
        this.stKeluaranDiambilKp = stKeluaranDiambilKp;
    }

    public String getStMasukanDiambilKp() {
        return stMasukanDiambilKp;
    }

    public void setStMasukanDiambilKp(String stMasukanDiambilKp) {
        this.stMasukanDiambilKp = stMasukanDiambilKp;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
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
}
