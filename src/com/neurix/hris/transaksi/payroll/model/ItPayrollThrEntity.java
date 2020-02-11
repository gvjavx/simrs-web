package com.neurix.hris.transaksi.payroll.model;


import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

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

public class ItPayrollThrEntity implements Serializable {
    private String thrId;
    private String payrollId;
    private BigDecimal gajiGolongan;
    private BigDecimal tunjanganUmk;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganJabatanStruktural;
    private BigDecimal tunjanganStrategis;
    private BigDecimal tunjanganPph;
    private BigDecimal totalThr;
    private BigDecimal totalThrBersih;
    private BigDecimal tunjanganPeralihan;
    private int bulanAktif;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    //tambahan irfan
    //tambahan irfan
    private BigDecimal tunjanganLain;
    private BigDecimal tunjanganTambahan;
    private BigDecimal pemondokan;
    private BigDecimal komunikasi;
    private BigDecimal totalRlab;

    public BigDecimal getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(BigDecimal totalRlab) {
        this.totalRlab = totalRlab;
    }

    public BigDecimal getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(BigDecimal komunikasi) {
        this.komunikasi = komunikasi;
    }

    public BigDecimal getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(BigDecimal pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getTunjanganLain() {
        return tunjanganLain;
    }

    public void setTunjanganLain(BigDecimal tunjanganLain) {
        this.tunjanganLain = tunjanganLain;
    }

    public BigDecimal getTunjanganTambahan() {
        return tunjanganTambahan;
    }

    public void setTunjanganTambahan(BigDecimal tunjanganTambahan) {
        this.tunjanganTambahan = tunjanganTambahan;
    }

    public BigDecimal getTotalThrBersih() {
        return totalThrBersih;
    }

    public void setTotalThrBersih(BigDecimal totalThrBersih) {
        this.totalThrBersih = totalThrBersih;
    }

    public int getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(int bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTotalThr() {
        return totalThr;
    }

    public void setTotalThr(BigDecimal totalThr) {
        this.totalThr = totalThr;
    }

    public String getThrId() {
        return thrId;
    }

    public void setThrId(String thrId) {
        this.thrId = thrId;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public BigDecimal getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(BigDecimal gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(BigDecimal tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(BigDecimal tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public BigDecimal getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(BigDecimal tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
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