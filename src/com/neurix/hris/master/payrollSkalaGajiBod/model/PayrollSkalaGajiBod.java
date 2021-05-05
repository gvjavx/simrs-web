package com.neurix.hris.master.payrollSkalaGajiBod.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollSkalaGajiBod extends BaseModel {
   private String payrollSkalaGajiBodId;
   private String positionId;
   private BigDecimal gajiBod;
   private BigDecimal tunjRumah;
   private BigDecimal tunjTelekomunikasi;
   private BigDecimal jumlahPengasilanBulan;
   private String tahun;

   private String positionName;
   private String stGajiBod;
   private String stTunjRumah;
   private String stTunjTelekomunikasi;
   private String stJumlahPenghasilanBulan;

    private String stCreatedDate;
    private String stLastUpdate;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getPayrollSkalaGajiBodId() {
        return payrollSkalaGajiBodId;
    }

    public void setPayrollSkalaGajiBodId(String payrollSkalaGajiBodId) {
        this.payrollSkalaGajiBodId = payrollSkalaGajiBodId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public BigDecimal getGajiBod() {
        return gajiBod;
    }

    public void setGajiBod(BigDecimal gajiBod) {
        this.gajiBod = gajiBod;
    }

    public BigDecimal getTunjRumah() {
        return tunjRumah;
    }

    public void setTunjRumah(BigDecimal tunjRumah) {
        this.tunjRumah = tunjRumah;
    }

    public BigDecimal getTunjTelekomunikasi() {
        return tunjTelekomunikasi;
    }

    public void setTunjTelekomunikasi(BigDecimal tunjTelekomunikasi) {
        this.tunjTelekomunikasi = tunjTelekomunikasi;
    }

    public BigDecimal getJumlahPengasilanBulan() {
        return jumlahPengasilanBulan;
    }

    public void setJumlahPengasilanBulan(BigDecimal jumlahPengasilanBulan) {
        this.jumlahPengasilanBulan = jumlahPengasilanBulan;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStGajiBod() {
        return stGajiBod;
    }

    public void setStGajiBod(String stGajiBod) {
        this.stGajiBod = stGajiBod;
    }

    public String getStTunjRumah() {
        return stTunjRumah;
    }

    public void setStTunjRumah(String stTunjRumah) {
        this.stTunjRumah = stTunjRumah;
    }

    public String getStTunjTelekomunikasi() {
        return stTunjTelekomunikasi;
    }

    public void setStTunjTelekomunikasi(String stTunjTelekomunikasi) {
        this.stTunjTelekomunikasi = stTunjTelekomunikasi;
    }

    public String getStJumlahPenghasilanBulan() {
        return stJumlahPenghasilanBulan;
    }

    public void setStJumlahPenghasilanBulan(String stJumlahPenghasilanBulan) {
        this.stJumlahPenghasilanBulan = stJumlahPenghasilanBulan;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }
}