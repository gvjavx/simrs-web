package com.neurix.hris.transaksi.sppd.model;

import com.neurix.common.model.BaseModel;
import org.joda.time.DateTime;

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
public class SppdTanggal extends BaseModel {

    private String idSppdTanggal ;

    private String sppdPersonId ;
    private String stSppdTanggal ;
    private Date SppdTanggal ;
    private String sppdTanggalApprove ;
    private String hari ;

    private BigDecimal hargaTiketBerangkat;
    private BigDecimal hargaTiketKembali;
    private BigDecimal transportLokal;
    private BigDecimal transportTujuan;
    private BigDecimal transportKembali;
    private BigDecimal biayaTransportLainnya;
    private BigDecimal biayaMakan;
    private BigDecimal biayaLain;

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public BigDecimal getTransportKembali() {
        return transportKembali;
    }

    public void setTransportKembali(BigDecimal transportKembali) {
        this.transportKembali = transportKembali;
    }

    public BigDecimal getBiayaLain() {
        return biayaLain;
    }

    public void setBiayaLain(BigDecimal biayaLain) {
        this.biayaLain = biayaLain;
    }

    public BigDecimal getBiayaMakan() {
        return biayaMakan;
    }

    public void setBiayaMakan(BigDecimal biayaMakan) {
        this.biayaMakan = biayaMakan;
    }

    public BigDecimal getHargaTiketBerangkat() {
        return hargaTiketBerangkat;
    }

    public void setHargaTiketBerangkat(BigDecimal hargaTiketBerangkat) {
        this.hargaTiketBerangkat = hargaTiketBerangkat;
    }

    public BigDecimal getHargaTiketKembali() {
        return hargaTiketKembali;
    }

    public void setHargaTiketKembali(BigDecimal hargaTiketKembali) {
        this.hargaTiketKembali = hargaTiketKembali;
    }

    public BigDecimal getBiayaTransportLainnya() {
        return biayaTransportLainnya;
    }

    public void setBiayaTransportLainnya(BigDecimal biayaTransportLainnya) {
        this.biayaTransportLainnya = biayaTransportLainnya;
    }

    public BigDecimal getTransportLokal() {
        return transportLokal;
    }

    public void setTransportLokal(BigDecimal transportLokal) {
        this.transportLokal = transportLokal;
    }

    public BigDecimal getTransportTujuan() {
        return transportTujuan;
    }

    public void setTransportTujuan(BigDecimal transportTujuan) {
        this.transportTujuan = transportTujuan;
    }

    public String getIdSppdTanggal() {
        return idSppdTanggal;
    }

    public void setIdSppdTanggal(String idSppdTanggal) {
        this.idSppdTanggal = idSppdTanggal;
    }

    public String getSppdPersonId() {
        return sppdPersonId;
    }

    public void setSppdPersonId(String sppdPersonId) {
        this.sppdPersonId = sppdPersonId;
    }

    public Date getSppdTanggal() {
        return SppdTanggal;
    }

    public void setSppdTanggal(Date sppdTanggal) {
        SppdTanggal = sppdTanggal;
    }

    public String getSppdTanggalApprove() {
        return sppdTanggalApprove;
    }

    public void setSppdTanggalApprove(String sppdTanggalApprove) {
        this.sppdTanggalApprove = sppdTanggalApprove;
    }

    public String getStSppdTanggal() {
        return stSppdTanggal;
    }

    public void setStSppdTanggal(String stSppdTanggal) {
        this.stSppdTanggal = stSppdTanggal;
    }
}