package com.neurix.simrs.transaksi.transaksiobat.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;

public class ApprovalTransaksiObat extends BaseModel {

    private String idApprovalObat;
    private String idObat;
    private String idPelayanan;
    private BigInteger qty;
    private String tipePermintaan;
    private String approvalFlag;
    private String approvePerson;

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
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

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public String getTipePermintaan() {
        return tipePermintaan;
    }

    public void setTipePermintaan(String tipePermintaan) {
        this.tipePermintaan = tipePermintaan;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }
}