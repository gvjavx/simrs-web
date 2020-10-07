package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class AbsensiOnCall extends BaseModel {
    private String absensiOnCallId;
    private String nip;
    private Date tanggal;
    private String jamMasuk;
    private String jamPulang;

    private Timestamp fingerAwal;
    private Timestamp fingerAkhir;
    private BigDecimal lamaLembur;

    public BigDecimal getLamaLembur() {
        return lamaLembur;
    }

    public void setLamaLembur(BigDecimal lamaLembur) {
        this.lamaLembur = lamaLembur;
    }

    public Timestamp getFingerAkhir() {
        return fingerAkhir;
    }

    public void setFingerAkhir(Timestamp fingerAkhir) {
        this.fingerAkhir = fingerAkhir;
    }

    public Timestamp getFingerAwal() {
        return fingerAwal;
    }

    public void setFingerAwal(Timestamp fingerAwal) {
        this.fingerAwal = fingerAwal;
    }

    public String getAbsensiOnCallId() {
        return absensiOnCallId;
    }

    public void setAbsensiOnCallId(String absensiOnCallId) {
        this.absensiOnCallId = absensiOnCallId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
    }
}
