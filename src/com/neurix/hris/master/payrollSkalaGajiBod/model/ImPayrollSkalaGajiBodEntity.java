package com.neurix.hris.master.payrollSkalaGajiBod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPayrollSkalaGajiBodEntity implements Serializable {

    private String payrollSkalaGajiBodId;
    private String positionId;
    private BigDecimal gajiBod;
    private BigDecimal tunjRumah;
    private BigDecimal tunjTelekomunikasi;
    private BigDecimal jumlahPengasilanBulan;
    private String tahun;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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
}
