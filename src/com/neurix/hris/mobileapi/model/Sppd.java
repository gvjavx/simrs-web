package com.neurix.hris.mobileapi.model;

import java.io.Serializable;

public class Sppd implements Serializable {
    private String sppdId;
    private String nip;
    private String userName;
    private String departementName;
    private String positionName;
    private String unit;
    private String kotaBerangkat;
    private String kotaTujuan;
    private String berangkatVia;
    private String kembaliVia;
    private String tugasSppd;
    private String penginapan;
    private String tanggalBerangkatSt;
    private String tanggalKembaliSt;

    public String getSppdId() {
        return sppdId;
    }

    public void setSppdId(String sppdId) {
        this.sppdId = sppdId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getKotaBerangkat() {
        return kotaBerangkat;
    }

    public void setKotaBerangkat(String kotaBerangkat) {
        this.kotaBerangkat = kotaBerangkat;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getBerangkatVia() {
        return berangkatVia;
    }

    public void setBerangkatVia(String berangkatVia) {
        this.berangkatVia = berangkatVia;
    }

    public String getKembaliVia() {
        return kembaliVia;
    }

    public void setKembaliVia(String kembaliVia) {
        this.kembaliVia = kembaliVia;
    }

    public String getTugasSppd() {
        return tugasSppd;
    }

    public void setTugasSppd(String tugasSppd) {
        this.tugasSppd = tugasSppd;
    }

    public String getPenginapan() {
        return penginapan;
    }

    public void setPenginapan(String penginapan) {
        this.penginapan = penginapan;
    }

    public String getTanggalBerangkatSt() {
        return tanggalBerangkatSt;
    }

    public void setTanggalBerangkatSt(String tanggalBerangkatSt) {
        this.tanggalBerangkatSt = tanggalBerangkatSt;
    }

    public String getTanggalKembaliSt() {
        return tanggalKembaliSt;
    }

    public void setTanggalKembaliSt(String tanggalKembaliSt) {
        this.tanggalKembaliSt = tanggalKembaliSt;
    }
}
