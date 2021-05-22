package com.neurix.simrs.transaksi.permintaanvendor.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by reza on 15/01/20.
 */
public class BatchPermintaanObat {
    private Integer noBatch;
    private String idApproval;
    private Integer jmlhObat;
    private String status;
    private String statusName;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String stLastUpdateWho;

    private String isApprove;
    private String urlDoc;
    private String noFaktur;
    private Date tanggalFaktur;
    private Date tglInvoice;
    private Date tglDo;
    private String noInvoice;
    private String noDo;
    private String stTanggakFaktur;
    private String jenis;
    private String stTglInvoice;
    private String stTglDo;
    private String idPermintaan;
    private String stNoBatch;

    public Date getTglInvoice() {
        return tglInvoice;
    }

    public void setTglInvoice(Date tglInvoice) {
        this.tglInvoice = tglInvoice;
    }

    public Date getTglDo() {
        return tglDo;
    }

    public void setTglDo(Date tglDo) {
        this.tglDo = tglDo;
    }

    public String getStNoBatch() {
        return stNoBatch;
    }

    public void setStNoBatch(String stNoBatch) {
        this.stNoBatch = stNoBatch;
    }

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    public String getStTglInvoice() {
        return stTglInvoice;
    }

    public void setStTglInvoice(String stTglInvoice) {
        this.stTglInvoice = stTglInvoice;
    }

    public String getStTglDo() {
        return stTglDo;
    }

    public void setStTglDo(String stTglDo) {
        this.stTglDo = stTglDo;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getStTanggakFaktur() {
        return stTanggakFaktur;
    }

    public void setStTanggakFaktur(String stTanggakFaktur) {
        this.stTanggakFaktur = stTanggakFaktur;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public Date getTanggalFaktur() {
        return tanggalFaktur;
    }

    public void setTanggalFaktur(Date tanggalFaktur) {
        this.tanggalFaktur = tanggalFaktur;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public String getNoDo() {
        return noDo;
    }

    public void setNoDo(String noDo) {
        this.noDo = noDo;
    }

    public String getUrlDoc() {
        return urlDoc;
    }

    public void setUrlDoc(String urlDoc) {
        this.urlDoc = urlDoc;
    }

    public String getStLastUpdateWho() {
        return stLastUpdateWho;
    }

    public void setStLastUpdateWho(String stLastUpdateWho) {
        this.stLastUpdateWho = stLastUpdateWho;
    }

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }

    public String getIdApproval() {
        return idApproval;
    }

    public void setIdApproval(String idApproval) {
        this.idApproval = idApproval;
    }

    public Integer getJmlhObat() {
        return jmlhObat;
    }

    public void setJmlhObat(Integer jmlhObat) {
        this.jmlhObat = jmlhObat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }
}
