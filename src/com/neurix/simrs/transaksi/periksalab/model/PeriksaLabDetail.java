package com.neurix.simrs.transaksi.periksalab.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class PeriksaLabDetail extends BaseModel {

    private String idPeriksaLabDetail;
    private String idPeriksaLab;
    private String idLabDetail;
    private String namaDetailPeriksa;
    private String satuan;
    private String keteranganAcuan;
    private String hasil;
    private String keteranganPeriksa;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String kategoriName;

    public String getKategoriName() {
        return kategoriName;
    }

    public void setKategoriName(String kategoriName) {
        this.kategoriName = kategoriName;
    }

    public String getIdPeriksaLabDetail() {
        return idPeriksaLabDetail;
    }

    public void setIdPeriksaLabDetail(String idPeriksaLabDetail) {
        this.idPeriksaLabDetail = idPeriksaLabDetail;
    }

    public String getIdPeriksaLab() {
        return idPeriksaLab;
    }

    public void setIdPeriksaLab(String idPeriksaLab) {
        this.idPeriksaLab = idPeriksaLab;
    }

    public String getIdLabDetail() {
        return idLabDetail;
    }

    public void setIdLabDetail(String idLabDetail) {
        this.idLabDetail = idLabDetail;
    }

    public String getNamaDetailPeriksa() {
        return namaDetailPeriksa;
    }

    public void setNamaDetailPeriksa(String namaDetailPeriksa) {
        this.namaDetailPeriksa = namaDetailPeriksa;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getKeteranganAcuan() {
        return keteranganAcuan;
    }

    public void setKeteranganAcuan(String keteranganAcuan) {
        this.keteranganAcuan = keteranganAcuan;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getKeteranganPeriksa() {
        return keteranganPeriksa;
    }

    public void setKeteranganPeriksa(String keteranganPeriksa) {
        this.keteranganPeriksa = keteranganPeriksa;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}