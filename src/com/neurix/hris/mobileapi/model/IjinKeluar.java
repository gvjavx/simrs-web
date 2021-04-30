package com.neurix.hris.mobileapi.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class IjinKeluar implements Serializable{
    private String ijinKeluarId;
    private String nip;
    private String userName;
    private String departementName;
    private String ijinName;
    private String positionName;
    private Date tanggalDari;
    private String tanggalDariSt;
    private Date tanggalSelesai;
    private String tanggalSelesaiSt;
    private BigInteger lamaIjin;
    private String unit;
    private String keterangan;


    public String getIjinKeluarId() {
        return ijinKeluarId;
    }

    public void setIjinKeluarId(String ijinKeluarId) {
        this.ijinKeluarId = ijinKeluarId;
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

    public String getIjinName() {
        return ijinName;
    }

    public void setIjinName(String ijinName) {
        this.ijinName = ijinName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(Date tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public String getTanggalDariSt() {
        return tanggalDariSt;
    }

    public void setTanggalDariSt(String tanggalDariSt) {
        this.tanggalDariSt = tanggalDariSt;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getTanggalSelesaiSt() {
        return tanggalSelesaiSt;
    }

    public void setTanggalSelesaiSt(String tanggalSelesaiSt) {
        this.tanggalSelesaiSt = tanggalSelesaiSt;
    }

    public BigInteger getLamaIjin() {
        return lamaIjin;
    }

    public void setLamaIjin(BigInteger lamaIjin) {
        this.lamaIjin = lamaIjin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
