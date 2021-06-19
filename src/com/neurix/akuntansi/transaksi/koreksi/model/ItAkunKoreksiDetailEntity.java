package com.neurix.akuntansi.transaksi.koreksi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class ItAkunKoreksiDetailEntity implements Serializable {
    private String koreksiDetailId;
    private String koreksiId;
    private String kodeCoa;
    private String kodeVendor;
    private String divisiId;
    private String noNota;
    private String posisi;
    private BigDecimal jumlahPembayaran;

    private String flag;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;


    public ItAkunKoreksiDetailEntity() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
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

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
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


}
