package com.neurix.simrs.transaksi.riwayattindakan.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RiwayatTindakan extends BaseModel {

    private String idRiwayatTindakan;
    private String idTindakan;
    private String namaTindakan;
    private String keterangan;
    private String jenisPasien;
    private BigDecimal totalTarif;
    private String kategoriTindakanBpjs;
    private String approveBpjsFlag;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idDetailCheckup;

    private String branchId;
    private String noCheckup;

    private String tglTo;
    private String tglForm;

    private String flagUpdateKlaim;

    private Timestamp tanggalTindakan;

    private String stTglTindakan;
    private String kategoriInaBpjs;
    private String rekanan;
    private String notResep;

    private BigDecimal ppnObat;
    private String isKamar;

    private String isPendingTindakan;
    private String msg;
    private String pasienBayar;

    private String idRuangan;
    private String namaPelayanan;
    private String tipePelayanan;
    private String jenisPelayanan;

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }

    public String getJenisPelayanan() {
        return jenisPelayanan;
    }

    public void setJenisPelayanan(String jenisPelayanan) {
        this.jenisPelayanan = jenisPelayanan;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getPasienBayar() {
        return pasienBayar;
    }

    public void setPasienBayar(String pasienBayar) {
        this.pasienBayar = pasienBayar;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsPendingTindakan() {
        return isPendingTindakan;
    }

    public void setIsPendingTindakan(String isPendingTindakan) {
        this.isPendingTindakan = isPendingTindakan;
    }

    public String getIsKamar() {
        return isKamar;
    }

    public void setIsKamar(String isKamar) {
        this.isKamar = isKamar;
    }

    public String getRekanan() {
        return rekanan;
    }

    public void setRekanan(String rekanan) {
        this.rekanan = rekanan;
    }

    public BigDecimal getPpnObat() {
        return ppnObat;
    }

    public void setPpnObat(BigDecimal ppnObat) {
        this.ppnObat = ppnObat;
    }

    public String getTglTo() {
        return tglTo;
    }

    public void setTglTo(String tglTo) {
        this.tglTo = tglTo;
    }

    public String getTglForm() {
        return tglForm;
    }

    public void setTglForm(String tglForm) {
        this.tglForm = tglForm;
    }

    public String getKategoriInaBpjs() {
        return kategoriInaBpjs;
    }

    public void setKategoriInaBpjs(String kategoriInaBpjs) {
        this.kategoriInaBpjs = kategoriInaBpjs;
    }

    public String getStTglTindakan() {
        return stTglTindakan;
    }

    public void setStTglTindakan(String stTglTindakan) {
        this.stTglTindakan = stTglTindakan;
    }

    public Timestamp getTanggalTindakan() {
        return tanggalTindakan;
    }

    public void setTanggalTindakan(Timestamp tanggalTindakan) {
        this.tanggalTindakan = tanggalTindakan;
    }

    public String getFlagUpdateKlaim() {
        return flagUpdateKlaim;
    }

    public void setFlagUpdateKlaim(String flagUpdateKlaim) {
        this.flagUpdateKlaim = flagUpdateKlaim;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdRiwayatTindakan() {
        return idRiwayatTindakan;
    }

    public void setIdRiwayatTindakan(String idRiwayatTindakan) {
        this.idRiwayatTindakan = idRiwayatTindakan;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenisPasien() {
        return jenisPasien;
    }

    public void setJenisPasien(String jenisPasien) {
        this.jenisPasien = jenisPasien;
    }

    public BigDecimal getTotalTarif() {
        return totalTarif;
    }

    public void setTotalTarif(BigDecimal totalTarif) {
        this.totalTarif = totalTarif;
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

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
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

    public String getNotResep() {
        return notResep;
    }

    public void setNotResep(String notResep) {
        this.notResep = notResep;
    }
}