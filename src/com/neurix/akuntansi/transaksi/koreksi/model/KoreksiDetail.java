package com.neurix.akuntansi.transaksi.koreksi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class KoreksiDetail extends BaseModel {

    private String koreksiId;
    private String koreksiDetailId;
    private String kodeCoa;
    private String kodeVendor;
    private String divisiId;
    private String noNota;
    private BigDecimal jumlahPembayaran;


    //tampilan UI list
    private String posisiCoa;
    private String stTanggalKoreksi;
    private String stJumlahPembayaran;
    private String masterId; //hapus
    private String masterName;
    private String divisiName;
    private String namaCoa;


    public KoreksiDetail() {
    }

    public String getNamaCoa() {
        return namaCoa;
    }

    public void setNamaCoa(String namaCoa) {
        this.namaCoa = namaCoa;
    }

    public String getKoreksiId() {
        return koreksiId;
    }

    public void setKoreksiId(String koreksiId) {
        this.koreksiId = koreksiId;
    }

    public String getKoreksiDetailId() {
        return koreksiDetailId;
    }

    public void setKoreksiDetailId(String koreksiDetailId) {
        this.koreksiDetailId = koreksiDetailId;
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getKodeVendor() {
        return kodeVendor;
    }

    public void setKodeVendor(String kodeVendor) {
        this.kodeVendor = kodeVendor;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public BigDecimal getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(BigDecimal jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getPosisiCoa() {
        return posisiCoa;
    }

    public void setPosisiCoa(String posisiCoa) {
        this.posisiCoa = posisiCoa;
    }

    public String getStTanggalKoreksi() {
        return stTanggalKoreksi;
    }

    public void setStTanggalKoreksi(String stTanggalKoreksi) {
        this.stTanggalKoreksi = stTanggalKoreksi;
    }

    public String getStJumlahPembayaran() {
        return stJumlahPembayaran;
    }

    public void setStJumlahPembayaran(String stJumlahPembayaran) {
        this.stJumlahPembayaran = stJumlahPembayaran;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}
