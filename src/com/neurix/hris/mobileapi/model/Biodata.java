package com.neurix.hris.mobileapi.model;

import java.io.Serializable;

/**
 * @author gondok
 * Saturday, 23/02/19 16:30
 */
public class Biodata implements Serializable {

    private String nip;
    private String nipLama;
    private String namaPegawai;
    private String unitId;
    private String unit;
    private String jabatanId;
    private String jabatan;
    private String bidangId;
    private String bidang;
    private String golonganId;
    private String golongan;
    private String tipePegawaiId;
    private String tipePegawai;
    private String agama;
    private String flagShift;

    public String getFlagShift() {
        return flagShift;
    }

    public void setFlagShift(String flagShift) {
        this.flagShift = flagShift;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getNipLama() {
        return nipLama;
    }

    public void setNipLama(String nipLama) {
        this.nipLama = nipLama;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(String jabatanId) {
        this.jabatanId = jabatanId;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getBidangId() {
        return bidangId;
    }

    public void setBidangId(String bidangId) {
        this.bidangId = bidangId;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }
}
