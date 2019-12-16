package com.neurix.simrs.transaksi.transaksiobat.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 14/12/2019.
 */
public class MtSimrsRiwayatPembelianObat implements Serializable {
    private String noPembelianObat;
    private BigInteger totalBayar;
    private BigInteger totalDibayar;
    private BigInteger nominal;
    private BigInteger kembalian;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;

    public String getNoPembelianObat() {
        return noPembelianObat;
    }

    public void setNoPembelianObat(String noPembelianObat) {
        this.noPembelianObat = noPembelianObat;
    }

    public BigInteger getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(BigInteger totalBayar) {
        this.totalBayar = totalBayar;
    }

    public BigInteger getTotalDibayar() {
        return totalDibayar;
    }

    public void setTotalDibayar(BigInteger totalDibayar) {
        this.totalDibayar = totalDibayar;
    }

    public BigInteger getNominal() {
        return nominal;
    }

    public void setNominal(BigInteger nominal) {
        this.nominal = nominal;
    }

    public BigInteger getKembalian() {
        return kembalian;
    }

    public void setKembalian(BigInteger kembalian) {
        this.kembalian = kembalian;
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
}
