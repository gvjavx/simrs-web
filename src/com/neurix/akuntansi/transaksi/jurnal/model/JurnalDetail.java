package com.neurix.akuntansi.transaksi.jurnal.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class JurnalDetail extends BaseModel {
    private String jurnalDetailId;
    private String noJurnal;
    private String rekeningId;
    private String masterId;
    private String noNota;
    private BigDecimal jumlahDebit;
    private BigDecimal jumlahKredit;
    private String biaya;
    private String pasienId;
    private String nomorRekening;
    private String divisiId;

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getPasienId() {
        return pasienId;
    }

    public void setPasienId(String pasienId) {
        this.pasienId = pasienId;
    }

    public String getJurnalDetailId() {
        return jurnalDetailId;
    }

    public void setJurnalDetailId(String jurnalDetailId) {
        this.jurnalDetailId = jurnalDetailId;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public BigDecimal getJumlahDebit() {
        return jumlahDebit;
    }

    public void setJumlahDebit(BigDecimal jumlahDebit) {
        this.jumlahDebit = jumlahDebit;
    }

    public BigDecimal getJumlahKredit() {
        return jumlahKredit;
    }

    public void setJumlahKredit(BigDecimal jumlahKredit) {
        this.jumlahKredit = jumlahKredit;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }
}