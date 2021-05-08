package com.neurix.simrs.mobileapi.model;

import java.sql.Timestamp;

/**
 * @author gondok
 * Monday, 03/08/20 11:29
 */
public class RekeningTelemedicMobile {
    private String idRekening;
    private String pembayaranId;
    private String pembayaranName;
    private String noRekening;
    private String namaRekening;
    private String coa;
    private String branchId;
    private String flag;
    private String action;
    private String createdDate;
    private String lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String clientId;
    private String keterangan;
    private String tipeRekening;

    private String message;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipeRekening() {
        return tipeRekening;
    }

    public void setTipeRekening(String tipeRekening) {
        this.tipeRekening = tipeRekening;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdRekening() {
        return idRekening;
    }

    public void setIdRekening(String idRekening) {
        this.idRekening = idRekening;
    }

    public String getPembayaranId() {
        return pembayaranId;
    }

    public void setPembayaranId(String pembayaranId) {
        this.pembayaranId = pembayaranId;
    }

    public String getPembayaranName() {
        return pembayaranName;
    }

    public void setPembayaranName(String pembayaranName) {
        this.pembayaranName = pembayaranName;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
