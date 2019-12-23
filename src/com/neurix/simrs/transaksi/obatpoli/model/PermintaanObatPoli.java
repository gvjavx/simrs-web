package com.neurix.simrs.transaksi.obatpoli.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanObatPoli{

    private String idPermintaanObatPoli;
    private String idObat;
    private String namaObat;
    private String idPelayanan;
    private String namaPelayanan;
    private String diterimaFlag;
    private String retureFlag;
    private String idApprovalObat;
    private BigInteger qty;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String approvalFlag;
    private String approvePerson;

    private Timestamp approvalLastUpdate;
    private String approvalLastUpdateWho;

    private String tipePermintaan;
    private Boolean isRequest = false;
    private String branchId;
    private String idJenisObat;

    private String keterangan;
    private BigInteger qtyGudang;

    private String tujuanPelayanan;

    private String qtyApprove;

    private String qtyPoli;

    public String getQtyPoli() {
        return qtyPoli;
    }

    public void setQtyPoli(String qtyPoli) {
        this.qtyPoli = qtyPoli;
    }

    public String getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(String qtyApprove) {
        this.qtyApprove = qtyApprove;
    }

    public String getTujuanPelayanan() {
        return tujuanPelayanan;
    }

    public void setTujuanPelayanan(String tujuanPelayanan) {
        this.tujuanPelayanan = tujuanPelayanan;
    }

    public BigInteger getQtyGudang() {
        return qtyGudang;
    }

    public void setQtyGudang(BigInteger qtyGudang) {
        this.qtyGudang = qtyGudang;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdJenisObat() {
        return idJenisObat;
    }

    public void setIdJenisObat(String idJenisObat) {
        this.idJenisObat = idJenisObat;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Boolean getRequest() {
        return isRequest;
    }

    public void setRequest(Boolean request) {
        isRequest = request;
    }

    public String getTipePermintaan() {
        return tipePermintaan;
    }

    public void setTipePermintaan(String tipePermintaan) {
        this.tipePermintaan = tipePermintaan;
    }

    public String getRetureFlag() {
        return retureFlag;
    }

    public void setRetureFlag(String retureFlag) {
        this.retureFlag = retureFlag;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public Timestamp getApprovalLastUpdate() {
        return approvalLastUpdate;
    }

    public void setApprovalLastUpdate(Timestamp approvalLastUpdate) {
        this.approvalLastUpdate = approvalLastUpdate;
    }

    public String getApprovalLastUpdateWho() {
        return approvalLastUpdateWho;
    }

    public void setApprovalLastUpdateWho(String approvalLastUpdateWho) {
        this.approvalLastUpdateWho = approvalLastUpdateWho;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getDiterimaFlag() {
        return diterimaFlag;
    }

    public void setDiterimaFlag(String diterimaFlag) {
        this.diterimaFlag = diterimaFlag;
    }

    public String getIdPermintaanObatPoli() {
        return idPermintaanObatPoli;
    }

    public void setIdPermintaanObatPoli(String idPermintaanObatPoli) {
        this.idPermintaanObatPoli = idPermintaanObatPoli;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
}
