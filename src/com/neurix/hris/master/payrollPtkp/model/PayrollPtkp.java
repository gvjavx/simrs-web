package com.neurix.hris.master.payrollPtkp.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

public class PayrollPtkp extends BaseModel {
    private String idPtkp;
    private String statusKeluarga;
    private Integer jumlahTanggungan;
    private BigDecimal nilai;

    private String statusKeluargaName;

    public String getStatusKeluargaName() {
        return statusKeluargaName;
    }

    public void setStatusKeluargaName(String statusKeluargaName) {
        this.statusKeluargaName = statusKeluargaName;
    }

    public String getIdPtkp() {
        return idPtkp;
    }

    public void setIdPtkp(String idPtkp) {
        this.idPtkp = idPtkp;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public Integer getJumlahTanggungan() {
        return jumlahTanggungan;
    }

    public void setJumlahTanggungan(Integer jumlahTanggungan) {
        this.jumlahTanggungan = jumlahTanggungan;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

}
