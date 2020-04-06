package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollTunjanganLain extends BaseModel {
    //tambahan irfan
    //data pegawai
    private String tunjLainId;
    private String namaTunjangan;
    private String nilai;
    private BigDecimal nilaiNilai;
    private String bulan;
    private String tahun;
    private String payrollId;
    private String nip;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getNamaTunjangan() {
        return namaTunjangan;
    }

    public void setNamaTunjangan(String namaTunjangan) {
        this.namaTunjangan = namaTunjangan;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiNilai() {
        return nilaiNilai;
    }

    public void setNilaiNilai(BigDecimal nilaiNilai) {
        this.nilaiNilai = nilaiNilai;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getTunjLainId() {
        return tunjLainId;
    }

    public void setTunjLainId(String tunjLainId) {
        this.tunjLainId = tunjLainId;
    }
}