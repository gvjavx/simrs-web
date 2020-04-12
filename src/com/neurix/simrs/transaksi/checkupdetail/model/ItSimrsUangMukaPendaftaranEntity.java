package com.neurix.simrs.transaksi.checkupdetail.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 25/02/20.
 */
public class ItSimrsUangMukaPendaftaranEntity implements Serializable {

    private String id;
    private String idDetailCheckup;
    private BigInteger jumlah;
    private BigInteger jumlahDibayar;
    private String statusBayar;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String noNota;
    private String noJurnal;
    private String noJurnalRefund;

    private String flagRefund;

    public String getNoJurnalRefund() {
        return noJurnalRefund;
    }

    public void setNoJurnalRefund(String noJurnalRefund) {
        this.noJurnalRefund = noJurnalRefund;
    }

    public String getFlagRefund() {
        return flagRefund;
    }

    public void setFlagRefund(String flagRefund) {
        this.flagRefund = flagRefund;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigInteger getJumlahDibayar() {
        return jumlahDibayar;
    }

    public void setJumlahDibayar(BigInteger jumlahDibayar) {
        this.jumlahDibayar = jumlahDibayar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public BigInteger getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigInteger jumlah) {
        this.jumlah = jumlah;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
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

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }
}
