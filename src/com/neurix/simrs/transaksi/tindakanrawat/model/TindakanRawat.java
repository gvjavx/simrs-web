package com.neurix.simrs.transaksi.tindakanrawat.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Timestamp;

public class TindakanRawat extends BaseModel {

    private String idTindakanRawat;
    private String idDetailCheckup;
    private String idTindakan;
    private String namaTindakan;
    private String idDokter;
    private String idPerawat;
    private BigInteger tarif;
    private BigInteger qty;
    private BigInteger tarifTotal;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String namaDokter;
    private String namaPerawat;

    private String idKategoriTindakan;

    private String approveBpjsFlag;
    private String kategoriTindakanBpjs;
    private String idRuangan;

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getKategoriTindakanBpjs() {
        return kategoriTindakanBpjs;
    }

    public void setKategoriTindakanBpjs(String kategoriTindakanBpjs) {
        this.kategoriTindakanBpjs = kategoriTindakanBpjs;
    }

    public String getApproveBpjsFlag() {
        return approveBpjsFlag;
    }

    public void setApproveBpjsFlag(String approveBpjsFlag) {
        this.approveBpjsFlag = approveBpjsFlag;
    }

    public String getIdKategoriTindakan() {
        return idKategoriTindakan;
    }

    public void setIdKategoriTindakan(String idKategoriTindakan) {
        this.idKategoriTindakan = idKategoriTindakan;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNamaPerawat() {
        return namaPerawat;
    }

    public void setNamaPerawat(String namaPerawat) {
        this.namaPerawat = namaPerawat;
    }

    public String getIdTindakanRawat() {
        return idTindakanRawat;
    }

    public void setIdTindakanRawat(String idTindakanRawat) {
        this.idTindakanRawat = idTindakanRawat;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPerawat() {
        return idPerawat;
    }

    public void setIdPerawat(String idPerawat) {
        this.idPerawat = idPerawat;
    }

    public BigInteger getTarif() {
        return tarif;
    }

    public void setTarif(BigInteger tarif) {
        this.tarif = tarif;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigInteger getTarifTotal() {
        return tarifTotal;
    }

    public void setTarifTotal(BigInteger tarifTotal) {
        this.tarifTotal = tarifTotal;
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