package com.neurix.akuntansi.master.mappingJurnal.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class MappingJurnal extends BaseModel {
    private String mappingJurnalId;
    private String tipeJurnalId;
    private String kodeRekening;
    private String posisi;
    private String transId;
    private String masterId;
    private String bukti;
    private String kodeBarang;
    private String keterangan;
    private String tipeJurnalName;
    private String transName;
    private String kirimList;

    private String kodeRekeningName;

    public String getKodeRekeningName() {
        return kodeRekeningName;
    }

    public void setKodeRekeningName(String kodeRekeningName) {
        this.kodeRekeningName = kodeRekeningName;
    }

    public String getKirimList() {
        return kirimList;
    }

    public void setKirimList(String kirimList) {
        this.kirimList = kirimList;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getTipeJurnalName() {
        return tipeJurnalName;
    }

    public void setTipeJurnalName(String tipeJurnalName) {
        this.tipeJurnalName = tipeJurnalName;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getMappingJurnalId() {
        return mappingJurnalId;
    }

    public void setMappingJurnalId(String mappingJurnalId) {
        this.mappingJurnalId = mappingJurnalId;
    }

    public String getTipeJurnalId() {
        return tipeJurnalId;
    }

    public void setTipeJurnalId(String tipeJurnalId) {
        this.tipeJurnalId = tipeJurnalId;
    }

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
}