package com.neurix.hris.mobileapi.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author gondok
 * Wednesday, 20/02/19 23:10
 */
public class PengajuanCuti implements Serializable {

    private String cutiPegawaiId;
    private String nip;
    private String namaPegawai;
    private String unitId;
    private String unit;
    private String jabatanId;
    private String jabatan;
    private String bidangId;
    private String bidang;
    private String cutiId;
    private String jenisCuti;
    private BigInteger sisaCuti;
    private Date tanggalAwalCuti;
    private String stTanggalAwalCuti;
    private Date tanggalSelesaiCuti;
    private String stTanggalSelesaiCuti;
    private String typeHariCuti;
    private BigInteger lamaCuti;
    private String alamatCuti;
    private String keterangan;
    private String nikPegawaiPengganti;

    private String statusApprove;
    private String golongan;

    private String channelId;
    private String os;

    public String getCutiPegawaiId() {
        return cutiPegawaiId;
    }

    public void setCutiPegawaiId(String cutiPegawaiId) {
        this.cutiPegawaiId = cutiPegawaiId;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getStatusApprove() {
        return statusApprove;
    }

    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }

    public String getStTanggalAwalCuti() {
        return stTanggalAwalCuti;
    }

    public void setStTanggalAwalCuti(String stTanggalAwalCuti) {
        this.stTanggalAwalCuti = stTanggalAwalCuti;
    }

    public String getStTanggalSelesaiCuti() {
        return stTanggalSelesaiCuti;
    }

    public void setStTanggalSelesaiCuti(String stTanggalSelesaiCuti) {
        this.stTanggalSelesaiCuti = stTanggalSelesaiCuti;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTypeHariCuti() {
        return typeHariCuti;
    }

    public void setTypeHariCuti(String typeHariCuti) {
        this.typeHariCuti = typeHariCuti;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getJenisCuti() {
        return jenisCuti;
    }

    public void setJenisCuti(String jenisCuti) {
        this.jenisCuti = jenisCuti;
    }

    public BigInteger getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(BigInteger sisaCuti) {
        this.sisaCuti = sisaCuti;
    }

    public Date getTanggalAwalCuti() {
        return tanggalAwalCuti;
    }

    public void setTanggalAwalCuti(Date tanggalAwalCuti) {
        this.tanggalAwalCuti = tanggalAwalCuti;
    }

    public Date getTanggalSelesaiCuti() {
        return tanggalSelesaiCuti;
    }

    public void setTanggalSelesaiCuti(Date tanggalSelesaiCuti) {
        this.tanggalSelesaiCuti = tanggalSelesaiCuti;
    }

    public BigInteger getLamaCuti() {
        return lamaCuti;
    }

    public void setLamaCuti(BigInteger lamaCuti) {
        this.lamaCuti = lamaCuti;
    }

    public String getAlamatCuti() {
        return alamatCuti;
    }

    public void setAlamatCuti(String alamatCuti) {
        this.alamatCuti = alamatCuti;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNikPegawaiPengganti() {
        return nikPegawaiPengganti;
    }

    public void setNikPegawaiPengganti(String nikPegawaiPengganti) {
        this.nikPegawaiPengganti = nikPegawaiPengganti;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(String jabatanId) {
        this.jabatanId = jabatanId;
    }

    public String getBidangId() {
        return bidangId;
    }

    public void setBidangId(String bidangId) {
        this.bidangId = bidangId;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
