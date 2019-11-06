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

public class ItPayrollPendidikanEntity implements Serializable {
    private String pendidikanId;
    private String payrollId;
    private BigDecimal gajiGolongan;
    private BigDecimal tunjanganPeralihan;
    private BigDecimal tunjanganUmk;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganJabatanStruktural;
    private BigDecimal tunjanganStrategis;
    private BigDecimal tunjanganKompensasi;
    private BigDecimal tunjanganAirListrik;
    private BigDecimal tunjanganPph;
    private BigDecimal totalPendidikan;
    private BigDecimal totalPendidikanBersih;

    private int bulanAktif;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTotalPendidikanBersih() {
        return totalPendidikanBersih;
    }

    public void setTotalPendidikanBersih(BigDecimal totalPendidikanBersih) {
        this.totalPendidikanBersih = totalPendidikanBersih;
    }

    public int getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(int bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public BigDecimal getTotalPendidikan() {
        return totalPendidikan;
    }

    public void setTotalPendidikan(BigDecimal totalPendidikan) {
        this.totalPendidikan = totalPendidikan;
    }

    public BigDecimal getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(BigDecimal gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getPendidikanId() {
        return pendidikanId;
    }

    public void setPendidikanId(String pendidikanId) {
        this.pendidikanId = pendidikanId;
    }

    public BigDecimal getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public void setTunjanganAirListrik(BigDecimal tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
    }

    public BigDecimal getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(BigDecimal tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganKompensasi() {
        return tunjanganKompensasi;
    }

    public void setTunjanganKompensasi(BigDecimal tunjanganKompensasi) {
        this.tunjanganKompensasi = tunjanganKompensasi;
    }

    public BigDecimal getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(BigDecimal tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public BigDecimal getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(BigDecimal tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }
}