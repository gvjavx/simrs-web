package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollUpahHarian extends BaseModel {
    private String upahHarianId;
    private String payrollId;
    private Date tanggal;
    private String stTnggal;

    private String stTanggalAwal;
    private String stTanggalAkhir;
    private String nip;
    private String branchId;
    private String hari;
    private String absenMasuk;
    private String absenKeluar;
    private String status;
    private String checked;
    private BigDecimal gajiNilai;
    private String gaji;
    private BigDecimal totalGaji;
    private String totalGajiNilai;

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getGajiNilai() {
        return gajiNilai;
    }

    public void setGajiNilai(BigDecimal gajiNilai) {
        this.gajiNilai = gajiNilai;
    }

    public String getTotalGajiNilai() {
        return totalGajiNilai;
    }

    public void setTotalGajiNilai(String totalGajiNilai) {
        this.totalGajiNilai = totalGajiNilai;
    }

    public BigDecimal getTotalGaji() {
        return totalGaji;
    }

    public void setTotalGaji(BigDecimal totalGaji) {
        this.totalGaji = totalGaji;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAbsenKeluar() {
        return absenKeluar;
    }

    public void setAbsenKeluar(String absenKaluar) {
        this.absenKeluar = absenKaluar;
    }

    public String getAbsenMasuk() {
        return absenMasuk;
    }

    public void setAbsenMasuk(String absenMasuk) {
        this.absenMasuk = absenMasuk;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getStTanggalAkhir() {
        return stTanggalAkhir;
    }

    public void setStTanggalAkhir(String stTanggalAkhir) {
        this.stTanggalAkhir = stTanggalAkhir;
    }

    public String getStTanggalAwal() {
        return stTanggalAwal;
    }

    public void setStTanggalAwal(String stTanggalAwal) {
        this.stTanggalAwal = stTanggalAwal;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getStTnggal() {
        return stTnggal;
    }

    public void setStTnggal(String stTnggal) {
        this.stTnggal = stTnggal;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getUpahHarianId() {
        return upahHarianId;
    }

    public void setUpahHarianId(String upahHarianId) {
        this.upahHarianId = upahHarianId;
    }
}