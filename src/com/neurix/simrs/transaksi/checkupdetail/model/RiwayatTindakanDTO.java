package com.neurix.simrs.transaksi.checkupdetail.model;

import java.math.BigInteger;

public class RiwayatTindakanDTO {
    private String idDokter;
    private String namaDokter;
    private String idTindakan;
    private String namaTindakan;
    private String ketTindakan;
    private BigInteger totalTarif;
    private String stTotalTarif;

    public String getStTotalTarif() {
        return stTotalTarif;
    }

    public void setStTotalTarif(String stTotalTarif) {
        this.stTotalTarif = stTotalTarif;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
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

    public String getKetTindakan() {
        return ketTindakan;
    }

    public void setKetTindakan(String ketTindakan) {
        this.ketTindakan = ketTindakan;
    }

    public BigInteger getTotalTarif() {
        return totalTarif;
    }

    public void setTotalTarif(BigInteger totalTarif) {
        this.totalTarif = totalTarif;
    }
}
