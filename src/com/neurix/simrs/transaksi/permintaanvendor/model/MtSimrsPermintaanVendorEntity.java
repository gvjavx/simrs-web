package com.neurix.simrs.transaksi.permintaanvendor.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class MtSimrsPermintaanVendorEntity implements Serializable {
    private String idPermintaanVendor;
    private String idVendor;
    private String idApprovalObat;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private String createdWho;
    private String urlDocPo;
    private String branchId;
    private String notaVendor;
    private Date tglCair;

    public Date getTglCair() {
        return tglCair;
    }

    public void setTglCair(Date tglCair) {
        this.tglCair = tglCair;
    }

    public String getNotaVendor() {
        return notaVendor;
    }

    public void setNotaVendor(String notaVendor) {
        this.notaVendor = notaVendor;
    }


    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdPermintaanVendor() {
        return idPermintaanVendor;
    }

    public void setIdPermintaanVendor(String idPermintaanVendor) {
        this.idPermintaanVendor = idPermintaanVendor;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
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

    public String getUrlDocPo() {
        return urlDocPo;
    }

    public void setUrlDocPo(String urlDocPo) {
        this.urlDocPo = urlDocPo;
    }
}
