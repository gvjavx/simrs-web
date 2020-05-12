package com.neurix.hris.transaksi.pendapatanDokter.model;

import java.math.BigDecimal;

public class BillingPendapatanDokter {
    private BigDecimal nilai;
    private String masterId;
    private String divisiId;
    private String jenisRawat;

    public String getJenisRawat() {
        return jenisRawat;
    }

    public void setJenisRawat(String jenisRawat) {
        this.jenisRawat = jenisRawat;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }
}
