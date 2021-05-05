package com.neurix.hris.transaksi.sppd.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
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
public class ItSppdTanggalEntity implements Serializable {

    private String idSppdTanggal ;

    private String sppdPersonId ;
    private String stSppdTanggal ;
    private Date sppdTanggal ;
    private String sppdTanggalApprove ;
    private BigDecimal hargaTiketBerangkat;
    private BigDecimal hargaTiketKembali;
    private BigDecimal transportLokal;
    private BigDecimal transportTujuan;
    private BigDecimal transportKembali;
    private BigDecimal biayaTransportLainnya;
    private BigDecimal biayaMakan;
    private BigDecimal biayaLain;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String berangkatVia ;
    private String kembaliVia ;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBerangkatVia() {
        return berangkatVia;
    }

    public void setBerangkatVia(String berangkatVia) {
        this.berangkatVia = berangkatVia;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIdSppdTanggal() {
        return idSppdTanggal;
    }

    public void setIdSppdTanggal(String idSppdTanggal) {
        this.idSppdTanggal = idSppdTanggal;
    }

    public String getKembaliVia() {
        return kembaliVia;
    }

    public void setKembaliVia(String kembaliVia) {
        this.kembaliVia = kembaliVia;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getSppdPersonId() {
        return sppdPersonId;
    }

    public void setSppdPersonId(String sppdPersonId) {
        this.sppdPersonId = sppdPersonId;
    }

    public Date getSppdTanggal() {
        return sppdTanggal;
    }

    public void setSppdTanggal(Date sppdTanggal) {
        this.sppdTanggal = sppdTanggal;
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
