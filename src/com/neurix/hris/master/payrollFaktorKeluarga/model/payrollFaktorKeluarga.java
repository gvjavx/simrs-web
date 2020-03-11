package com.neurix.hris.master.payrollFaktorKeluarga.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class payrollFaktorKeluarga extends BaseModel {
    private String faktorKeluargaId;
    private String statusKeluarga;
    private String statusKeluargaName;
    private int jumlahAnak;
    private BigDecimal nilai;
    private BigDecimal ptkp;

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getStatusKeluargaName() {
        return statusKeluargaName;
    }

    public void setStatusKeluargaName(String statusKeluargaName) {
        this.statusKeluargaName = statusKeluargaName;
    }

    public BigDecimal getPtkp() {
        return ptkp;
    }

    public void setPtkp(BigDecimal ptkp) {
        this.ptkp = ptkp;
    }

    public String getFaktorKeluargaId() {
        return faktorKeluargaId;
    }

    public void setFaktorKeluargaId(String faktorKeluargaId) {
        this.faktorKeluargaId = faktorKeluargaId;
    }

    public Integer getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(Integer jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }
}