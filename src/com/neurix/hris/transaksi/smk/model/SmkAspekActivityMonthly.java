package com.neurix.hris.transaksi.smk.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SmkAspekActivityMonthly extends BaseModel {
    private String idAspekDetail;
    private String aspekActivityMonthly;
    private String evaluasiPegawaiAspekId;
    private int bulan;
    private double nilai;
    private double bobot;
    private double rataRata;

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
    }

    public String getIdAspekDetail() {
        return idAspekDetail;
    }

    public void setIdAspekDetail(String idAspekDetail) {
        this.idAspekDetail = idAspekDetail;
    }

    public double getRataRata() {
        return rataRata;
    }

    public void setRataRata(double rataRata) {
        this.rataRata = rataRata;
    }

    public String getAspekActivityMonthly() {
        return aspekActivityMonthly;
    }

    public void setAspekActivityMonthly(String aspekActivityMonthly) {
        this.aspekActivityMonthly = aspekActivityMonthly;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }
}