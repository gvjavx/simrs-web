package com.neurix.hris.mobileapi.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class CutiPegawai implements Serializable {

    private String cutiPegawaiId;
    private String nip;
    private String userName;
    private String departementName;
    private String cutiName;
    private String positionName;
    private Date tanggalDari;
    private String tanggalDariSt;
    private Date tanggalSelesai;
    private String tanggalSelesaiSt;
    private BigInteger lamaHariCuti;
    private String idPenggantiSementara;
    private String unit;
    private String sisaCuti;
    private String keteranganReject;
    private String keteranganCuti;
    private String profesiId;

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public void setKeteranganReject(String keteranganReject) {
        this.keteranganReject = keteranganReject;
    }

    public String getKeteranganReject() {
        return keteranganReject;
    }

    public String getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(String sisaCuti) {
        this.sisaCuti = sisaCuti;
    }

    public String getCutiPegawaiId() {
        return cutiPegawaiId;
    }

    public void setCutiPegawaiId(String cutiPegawaiId) {
        this.cutiPegawaiId = cutiPegawaiId;
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

    public String getCutiName() {
        return cutiName;
    }

    public void setCutiName(String cutiName) {
        this.cutiName = cutiName;
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

    public BigInteger getLamaHariCuti() {
        return lamaHariCuti;
    }

    public void setLamaHariCuti(BigInteger lamaHariCuti) {
        this.lamaHariCuti = lamaHariCuti;
    }

    public String getIdPenggantiSementara() {
        return idPenggantiSementara;
    }

    public void setIdPenggantiSementara(String idPenggantiSementara) {
        this.idPenggantiSementara = idPenggantiSementara;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getKeteranganCuti() {
        return keteranganCuti;
    }

    public void setKeteranganCuti(String keteranganCuti) {
        this.keteranganCuti = keteranganCuti;
    }
}
