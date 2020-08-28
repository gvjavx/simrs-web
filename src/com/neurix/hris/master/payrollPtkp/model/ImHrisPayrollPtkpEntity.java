package com.neurix.hris.master.payrollPtkp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ImHrisPayrollPtkpEntity {
    private String idPtkp;
    private String statusKeluarga;
    private Integer jumlahTanggungan;
    private BigDecimal nilai;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String jenisKelamin;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImHrisPayrollPtkpEntity that = (ImHrisPayrollPtkpEntity) o;
        return Objects.equals(idPtkp, that.idPtkp) &&
                Objects.equals(statusKeluarga, that.statusKeluarga) &&
                Objects.equals(jumlahTanggungan, that.jumlahTanggungan) &&
                Objects.equals(nilai, that.nilai) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(jenisKelamin, that.jenisKelamin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPtkp, statusKeluarga, jumlahTanggungan, nilai, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho, jenisKelamin);
    }
}
