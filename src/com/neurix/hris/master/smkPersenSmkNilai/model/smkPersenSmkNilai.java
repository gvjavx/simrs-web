package com.neurix.hris.master.smkPersenSmkNilai.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class smkPersenSmkNilai extends BaseModel {
    private String smkNilaiId ;
    private String nama ;
    private String branchId ;
    private String branchName ;
    private BigDecimal nilai ;
    private BigDecimal nilaiAtas ;
    private BigDecimal nilaiBawah ;

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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiAtas() {
        return nilaiAtas;
    }

    public void setNilaiAtas(BigDecimal nilaiAtas) {
        this.nilaiAtas = nilaiAtas;
    }

    public BigDecimal getNilaiBawah() {
        return nilaiBawah;
    }

    public void setNilaiBawah(BigDecimal nilaiBawah) {
        this.nilaiBawah = nilaiBawah;
    }

    public String getSmkNilaiId() {
        return smkNilaiId;
    }

    public void setSmkNilaiId(String smkNilaiId) {
        this.smkNilaiId = smkNilaiId;
    }
}