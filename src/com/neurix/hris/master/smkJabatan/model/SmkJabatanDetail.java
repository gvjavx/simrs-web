package com.neurix.hris.master.smkJabatan.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkJabatanDetail extends BaseModel {
    private String jabatanSmkDetailId;
    private String jabatanSmkId;
    private String indikatorKinerja;
    private String subAspekA;
    private String subAspekANama;

    private String nip;
    private String satuan;
    private BigDecimal bobot;
    private BigDecimal nilai;
    private BigDecimal nilaiPrestasi;

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiPrestasi() {
        return nilaiPrestasi;
    }

    public void setNilaiPrestasi(BigDecimal nilaiPrestasi) {
        this.nilaiPrestasi = nilaiPrestasi;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getSubAspekANama() {
        return subAspekANama;
    }

    public void setSubAspekANama(String subAspekANama) {
        this.subAspekANama = subAspekANama;
    }

    public String getJabatanSmkDetailId() {
        return jabatanSmkDetailId;
    }

    public void setJabatanSmkDetailId(String jabatanSmkDetailId) {
        this.jabatanSmkDetailId = jabatanSmkDetailId;
    }

    public String getJabatanSmkId() {
        return jabatanSmkId;
    }

    public void setJabatanSmkId(String jabatanSmkId) {
        this.jabatanSmkId = jabatanSmkId;
    }

    public String getIndikatorKinerja() {
        return indikatorKinerja;
    }

    public void setIndikatorKinerja(String indikatorKinerja) {
        this.indikatorKinerja = indikatorKinerja;
    }

    public BigDecimal getBobot() {
        return bobot;
    }

    public void setBobot(BigDecimal bobot) {
        this.bobot = bobot;
    }

    public String getSubAspekA() {
        return subAspekA;
    }

    public void setSubAspekA(String subAspekA) {
        this.subAspekA = subAspekA;
    }
}