/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.akuntansi.transaksi.billingSystem.model;

import java.math.BigDecimal;
import java.util.List;

//created by ferdi, 01-12-2020, to handle detail mapping jurnal

public class MappingDetail {
    String coa;
    String divisiId;
    String masterId;
    String pasienId;
    String kodeBarang;
    String bukti;
    String metodeBayar;
    List<Activity> listOfActivity;
    BigDecimal nilai;

    public String getPasienId() {
        return pasienId;
    }

    public void setPasienId(String pasienId) {
        this.pasienId = pasienId;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public List<Activity> getListOfActivity() {
        return listOfActivity;
    }

    public void setListOfActivity(List<Activity> listOfActivity) {
        this.listOfActivity = listOfActivity;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}
