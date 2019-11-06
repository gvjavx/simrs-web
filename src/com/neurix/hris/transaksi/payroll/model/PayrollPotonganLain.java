package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollPotonganLain extends BaseModel {
    private String potonganLainId;
    private String payrollId;
    private String keterangan1;
    private BigDecimal nilai1;
    private String keterangan2;
    private BigDecimal nilai2;
    private String keterangan3;
    private BigDecimal nilai3;
    private String keterangan4;
    private BigDecimal nilai4;
    private String keterangan5;
    private BigDecimal nilai5;
    private BigDecimal total;

    private String nip;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPotonganLainId() {
        return potonganLainId;
    }

    public void setPotonganLainId(String potonganLainId) {
        this.potonganLainId = potonganLainId;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getKeterangan1() {
        return keterangan1;
    }

    public void setKeterangan1(String keterangan1) {
        this.keterangan1 = keterangan1;
    }

    public BigDecimal getNilai1() {
        return nilai1;
    }

    public void setNilai1(BigDecimal nilai1) {
        this.nilai1 = nilai1;
    }

    public String getKeterangan2() {
        return keterangan2;
    }

    public void setKeterangan2(String keterangan2) {
        this.keterangan2 = keterangan2;
    }

    public BigDecimal getNilai2() {
        return nilai2;
    }

    public void setNilai2(BigDecimal nilai2) {
        this.nilai2 = nilai2;
    }

    public String getKeterangan3() {
        return keterangan3;
    }

    public void setKeterangan3(String keterangan3) {
        this.keterangan3 = keterangan3;
    }

    public BigDecimal getNilai3() {
        return nilai3;
    }

    public void setNilai3(BigDecimal nilai3) {
        this.nilai3 = nilai3;
    }

    public String getKeterangan4() {
        return keterangan4;
    }

    public void setKeterangan4(String keterangan4) {
        this.keterangan4 = keterangan4;
    }

    public BigDecimal getNilai4() {
        return nilai4;
    }

    public void setNilai4(BigDecimal nilai4) {
        this.nilai4 = nilai4;
    }

    public String getKeterangan5() {
        return keterangan5;
    }

    public void setKeterangan5(String keterangan5) {
        this.keterangan5 = keterangan5;
    }

    public BigDecimal getNilai5() {
        return nilai5;
    }

    public void setNilai5(BigDecimal nilai5) {
        this.nilai5 = nilai5;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}