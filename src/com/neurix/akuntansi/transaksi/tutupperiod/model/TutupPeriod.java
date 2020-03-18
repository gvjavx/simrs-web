package com.neurix.akuntansi.transaksi.tutupperiod.model;

/**
 * Created by reza on 18/03/20.
 */
public class TutupPeriod {
    private String id;
    private String tahun;
    private String bulanPeriod;
    private String stTglAkhirPeriod;
    private String stTglAwalPeriod;
    private String unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulanPeriod() {
        return bulanPeriod;
    }

    public void setBulanPeriod(String bulanPeriod) {
        this.bulanPeriod = bulanPeriod;
    }

    public String getStTglAkhirPeriod() {
        return stTglAkhirPeriod;
    }

    public void setStTglAkhirPeriod(String stTglAkhirPeriod) {
        this.stTglAkhirPeriod = stTglAkhirPeriod;
    }

    public String getStTglAwalPeriod() {
        return stTglAwalPeriod;
    }

    public void setStTglAwalPeriod(String stTglAwalPeriod) {
        this.stTglAwalPeriod = stTglAwalPeriod;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
