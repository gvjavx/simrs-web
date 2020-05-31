package com.neurix.hris.mobileapi.model;

import java.math.BigInteger;

/**
 * @author gondok
 * Thursday, 28/02/19 9:24
 */
public class Dispensasi {
    private String namaIjin;
    private Long maksimalIjin;

    private String divisi;
    private String divisiName;
    private String ijinKeluarId;
    private String ijinId;
    private String ijinName;
    private BigInteger lamaIjin;
    private String nip;
    private String namaPegawai;
    private String keterangan;
    private String tanggalAwal;
    private String tanggalAkhir;
    private String unitName;
    private String unit;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private String gender;
    private String tanggalKelahiran;

    private String actionError;

    public String getTanggalKelahiran() {
        return tanggalKelahiran;
    }

    public void setTanggalKelahiran(String tanggalKelahiran) {
        this.tanggalKelahiran = tanggalKelahiran;
    }

    public String getActionError() {
        return actionError;
    }

    public void setActionError(String actionError) {
        this.actionError = actionError;
    }

    private String os;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getIjinKeluarId() {
        return ijinKeluarId;
    }

    public void setIjinKeluarId(String ijinKeluarId) {
        this.ijinKeluarId = ijinKeluarId;
    }

    public String getIjinId() {
        return ijinId;
    }

    public void setIjinId(String ijinId) {
        this.ijinId = ijinId;
    }

    public String getIjinName() {
        return ijinName;
    }

    public void setIjinName(String ijinName) {
        this.ijinName = ijinName;
    }

    public BigInteger getLamaIjin() {
        return lamaIjin;
    }

    public void setLamaIjin(BigInteger lamaIjin) {
        this.lamaIjin = lamaIjin;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(String tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getNamaIjin() {
        return namaIjin;
    }

    public void setNamaIjin(String namaIjin) {
        this.namaIjin = namaIjin;
    }

    public Long getMaksimalIjin() {
        return maksimalIjin;
    }

    public void setMaksimalIjin(Long maksimalIjin) {
        this.maksimalIjin = maksimalIjin;
    }
}
