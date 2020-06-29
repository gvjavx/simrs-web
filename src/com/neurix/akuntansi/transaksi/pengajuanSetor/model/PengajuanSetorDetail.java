package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class PengajuanSetorDetail extends BaseModel {
    private String pengajuanSetorDetailId;
    private String pengajuanSetorId;
    private String branchId;
    private String transaksiId;
    private String personId;
    private String koderingDivisi;
    private String tipe;
    private String note;
    private BigDecimal jumlah;

    //detail
    private String nama;
    private String positionId;
    private String posisiName;
    private String bagianName;
    private String divisiName;
    private String stJumlah;


    public String getPosisiName() {
        return posisiName;
    }

    public void setPosisiName(String posisiName) {
        this.posisiName = posisiName;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getStJumlah() {
        return stJumlah;
    }

    public void setStJumlah(String stJumlah) {
        this.stJumlah = stJumlah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPengajuanSetorDetailId() {
        return pengajuanSetorDetailId;
    }

    public void setPengajuanSetorDetailId(String pengajuanSetorDetailId) {
        this.pengajuanSetorDetailId = pengajuanSetorDetailId;
    }

    public String getPengajuanSetorId() {
        return pengajuanSetorId;
    }

    public void setPengajuanSetorId(String pengajuanSetorId) {
        this.pengajuanSetorId = pengajuanSetorId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getKoderingDivisi() {
        return koderingDivisi;
    }

    public void setKoderingDivisi(String koderingDivisi) {
        this.koderingDivisi = koderingDivisi;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }
}
