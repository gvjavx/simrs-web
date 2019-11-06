package com.neurix.hris.transaksi.sppd.model;

import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItSppdRerouteEntity implements Serializable {
    private String sppdReroute;
    private String sppdRerouteId;
    private String sppdPersonId;
    private String nama;
    private String posisiName;
    private Date tanggalRerouteDari;
    private Date tanggalRerouteKe;
    private String rerouteDari;
    private String rerouteKe;
    private String kota;
    private String kotaDari;
    private String keterangan;
    private String berangkatVia;
    private String nip;
    private String sppdId;
    private String kelompokId;
    private BigDecimal biayaTambahan;
    private BigDecimal biayaMakan;
    private BigDecimal biayaLain;
    private BigDecimal tiketPergi;
    private BigDecimal tiketKembali;
    private BigDecimal transportLokalBerangkat;
    private BigDecimal transportLokalKembali;
    private BigDecimal transportTujuan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getKotaDari() {
        return kotaDari;
    }

    public void setKotaDari(String kotaDari) {
        this.kotaDari = kotaDari;
    }

    private ItSppdPersonEntity itSppdPersonEntity;
    private ImKotaEntity imKotaEntity ;

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getBerangkatVia() {
        return berangkatVia;
    }

    public void setBerangkatVia(String berangkatVia) {
        this.berangkatVia = berangkatVia;
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

    public ImKotaEntity getImKotaEntity() {
        return imKotaEntity;
    }

    public void setImKotaEntity(ImKotaEntity imKotaEntity) {
        this.imKotaEntity = imKotaEntity;
    }

    public ItSppdPersonEntity getItSppdPersonEntity() {
        return itSppdPersonEntity;
    }

    public void setItSppdPersonEntity(ItSppdPersonEntity itSppdPersonEntity) {
        this.itSppdPersonEntity = itSppdPersonEntity;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getPosisiName() {
        return posisiName;
    }

    public void setPosisiName(String posisiName) {
        this.posisiName = posisiName;
    }

    public Date getTanggalRerouteDari() {
        return tanggalRerouteDari;
    }

    public void setTanggalRerouteDari(Date tanggalRerouteDari) {
        this.tanggalRerouteDari = tanggalRerouteDari;
    }

    public BigDecimal getBiayaTambahan() {
        return biayaTambahan;
    }

    public void setBiayaTambahan(BigDecimal biayaTambahan) {
        this.biayaTambahan = biayaTambahan;
    }

    public BigDecimal getBiayaMakan() {
        return biayaMakan;
    }

    public void setBiayaMakan(BigDecimal biayaMakan) {
        this.biayaMakan = biayaMakan;
    }

    public BigDecimal getBiayaLain() {
        return biayaLain;
    }

    public void setBiayaLain(BigDecimal biayaLain) {
        this.biayaLain = biayaLain;
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

    public BigDecimal getTransportLokalBerangkat() {
        return transportLokalBerangkat;
    }

    public void setTransportLokalBerangkat(BigDecimal transportLokalBerangkat) {
        this.transportLokalBerangkat = transportLokalBerangkat;
    }

    public BigDecimal getTransportLokalKembali() {
        return transportLokalKembali;
    }

    public void setTransportLokalKembali(BigDecimal transportLokalKembali) {
        this.transportLokalKembali = transportLokalKembali;
    }

    public BigDecimal getTransportTujuan() {
        return transportTujuan;
    }

    public void setTransportTujuan(BigDecimal transportTujuan) {
        this.transportTujuan = transportTujuan;
    }
}
