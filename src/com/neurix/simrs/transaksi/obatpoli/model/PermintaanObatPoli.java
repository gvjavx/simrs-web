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
