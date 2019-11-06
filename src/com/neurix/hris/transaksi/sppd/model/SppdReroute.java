package com.neurix.hris.transaksi.sppd.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SppdReroute extends BaseModel {
    private String sppdRerouteId;
    private String sppdReroute;
    private String sppdPersonId;
    private String sppdPersonName;
    private String stTanggalRerouteDari;
    private Date tanggalRerouteDari;
    private String stTanggalRerouteKe;
    private Date tanggalRerouteKe;
    private String rerouteDari;
    private String rerouteDariName;
    private String rerouteKe;
    private String kotaDari;
    private String kota;
    private String rerouteKeName;
    private String keterangan;
    private String nip;
    private String nama;
    private String sppdId;
    private String berangkatVia;
    private String posisi;

    BigDecimal transportLokalBerangkat ;
    BigDecimal transportTujuan;
    BigDecimal transportLokalKembali;

    public String getKotaDari() {
        return kotaDari;
    }

    public void setKotaDari(String kotaDari) {
        this.kotaDari = kotaDari;
    }

    public BigDecimal getTransportLokalBerangkat() {
        return transportLokalBerangkat;
    }

    public void setTransportLokalBerangkat(BigDecimal transportLokalBerangkat) {
        this.transportLokalBerangkat = transportLokalBerangkat;
    }

    public BigDecimal getTransportTujuan() {
        return transportTujuan;
    }

    public void setTransportTujuan(BigDecimal transportTujuan) {
        this.transportTujuan = transportTujuan;
    }

    public BigDecimal getTransportLokalKembali() {
        return transportLokalKembali;
    }

    public void setTransportLokalKembali(BigDecimal transportLokalKembali) {
        this.transportLokalKembali = transportLokalKembali;
    }

    private BigDecimal tiketPergi;
    private BigDecimal tiketKembali;
    private BigDecimal biayaTambahan;
    private BigDecimal biayaLokal;
    private BigDecimal biayaTujuan;
    private BigDecimal biayaKembali;


    public BigDecimal getBiayaKembali() {
        return biayaKembali;
    }

    public void setBiayaKembali(BigDecimal biayaKembali) {
        this.biayaKembali = biayaKembali;
    }

    public BigDecimal getBiayaTambahan() {
        return biayaTambahan;
    }

    public void setBiayaTambahan(BigDecimal biayaTambahan) {
        this.biayaTambahan = biayaTambahan;
    }

    public BigDecimal getBiayaLokal() {
        return biayaLokal;
    }

    public void setBiayaLokal(BigDecimal biayaLokal) {
        this.biayaLokal = biayaLokal;
    }

    public BigDecimal getBiayaTujuan() {
        return biayaTujuan;
    }

    public void setBiayaTujuan(BigDecimal biayaTujuan) {
        this.biayaTujuan = biayaTujuan;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getBerangkatVia() {
        return berangkatVia;
    }

    public void setBerangkatVia(String berangkatVia) {
        this.berangkatVia = berangkatVia;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public BigDecimal getTiketPergi() {
        return tiketPergi;
    }

    public void setTiketPergi(BigDecimal tiketPergi) {
        this.tiketPergi = tiketPergi;
    }

    public BigDecimal getTiketKembali() {
        return tiketKembali;
    }

    public void setTiketKembali(BigDecimal tiketKembali) {
        this.tiketKembali = tiketKembali;
    }

    public String getSppdId() {
        return sppdId;
    }

    public void setSppdId(String sppdId) {
        this.sppdId = sppdId;
    }

    public String getSppdRerouteId() {
        return sppdRerouteId;
    }

    public void setSppdRerouteId(String sppdRerouteId) {
        this.sppdRerouteId = sppdRerouteId;
    }

    public String getRerouteDariName() {
        return rerouteDariName;
    }

    public void setRerouteDariName(String rerouteDariName) {
        this.rerouteDariName = rerouteDariName;
    }

    public String getRerouteKeName() {
        return rerouteKeName;
    }

    public void setRerouteKeName(String rerouteKeName) {
        this.rerouteKeName = rerouteKeName;
    }

    public String getStTanggalRerouteDari() {
        return stTanggalRerouteDari;
    }

    public void setStTanggalRerouteDari(String stTanggalRerouteDari) {
        this.stTanggalRerouteDari = stTanggalRerouteDari;
    }

    public String getStTanggalRerouteKe() {
        return stTanggalRerouteKe;
    }

    public void setStTanggalRerouteKe(String stTanggalRerouteKe) {
        this.stTanggalRerouteKe = stTanggalRerouteKe;
    }

    public String getSppdReroute() {
        return sppdReroute;
    }

    public void setSppdReroute(String sppdReroute) {
        this.sppdReroute = sppdReroute;
    }

    public String getSppdPersonId() {
        return sppdPersonId;
    }

    public void setSppdPersonId(String sppdPersonId) {
        this.sppdPersonId = sppdPersonId;
    }

    public String getSppdPersonName() {
        return sppdPersonName;
    }

    public void setSppdPersonName(String sppdPersonName) {
        this.sppdPersonName = sppdPersonName;
    }

    public Date getTanggalRerouteDari() {
        return tanggalRerouteDari;
    }

    public void setTanggalRerouteDari(Date tanggalRerouteDari) {
        this.tanggalRerouteDari = tanggalRerouteDari;
    }

    public Date getTanggalRerouteKe() {
        return tanggalRerouteKe;
    }

    public void setTanggalRerouteKe(Date tanggalRerouteKe) {
        this.tanggalRerouteKe = tanggalRerouteKe;
    }

    public String getRerouteDari() {
        return rerouteDari;
    }

    public void setRerouteDari(String rerouteDari) {
        this.rerouteDari = rerouteDari;
    }

    public String getRerouteKe() {
        return rerouteKe;
    }

    public void setRerouteKe(String rerouteKe) {
        this.rerouteKe = rerouteKe;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}