package com.neurix.hris.master.payrollPtkp.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImHrisPayrollPtkpHistoryEntity {
    private String id;
    private String idPtkp;
    private String statusKeluarga;
    private Integer jumlahTanggungan;
    private Integer nilai;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getNilai() {
        return nilai;
    }

    public void setNilai(Integer nilai) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImHrisPayrollPtkpHistoryEntity that = (ImHrisPayrollPtkpHistoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idPtkp, that.idPtkp) &&
                Objects.equals(statusKeluarga, that.statusKeluarga) &&
                Objects.equals(jumlahTanggungan, that.jumlahTanggungan) &&
                Objects.equals(nilai, that.nilai) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPtkp, statusKeluarga, jumlahTanggungan, nilai, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
