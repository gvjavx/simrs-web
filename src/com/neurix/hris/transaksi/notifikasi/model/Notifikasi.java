package com.neurix.hris.transaksi.notifikasi.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Notifikasi extends BaseModel {
    private String notifId;
    private String nip;
//    private String notifName;
    private String tipeNotifId;
    private String tipeNotifName;
    private String note;
    private String read;
    private String fromPerson;
    private String noRequest;


    private String stSelisih;
    private String jmlPemberitahuan;
    private String jmlApproval;
    private String to;
    private String namaPegawai;
    private String stTanggalPensiun;
    private String stTanggalAktif;

    private String os;
    private String channelId;

    private String stTglJubilium;

    //for pengajuan biaya
    private String pengajuanBiayaDetailId;
    private String stTanggalRealisasi;
    private String divisiName;
    private String keperluan;

    //for terima RK
    private String rkId;

    public String getRkId() {
        return rkId;
    }

    public void setRkId(String rkId) {
        this.rkId = rkId;
    }

    public String getPengajuanBiayaDetailId() {
        return pengajuanBiayaDetailId;
    }

    public void setPengajuanBiayaDetailId(String pengajuanBiayaDetailId) {
        this.pengajuanBiayaDetailId = pengajuanBiayaDetailId;
    }

    public String getStTanggalRealisasi() {
        return stTanggalRealisasi;
    }

    public void setStTanggalRealisasi(String stTanggalRealisasi) {
        this.stTanggalRealisasi = stTanggalRealisasi;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getStTglJubilium() {
        return stTglJubilium;
    }

    public void setStTglJubilium(String stTglJubilium) {
        this.stTglJubilium = stTglJubilium;
    }
    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getStTanggalAktif() {
        return stTanggalAktif;
    }

    public void setStTanggalAktif(String stTanggalAktif) {
        this.stTanggalAktif = stTanggalAktif;
    }

    public String getStSelisih() {
        return stSelisih;
    }

    public void setStSelisih(String stSelisih) {
        this.stSelisih = stSelisih;
    }

    public String getStTanggalPensiun() {
        return stTanggalPensiun;
    }

    public void setStTanggalPensiun(String stTanggalPensiun) {
        this.stTanggalPensiun = stTanggalPensiun;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNotifId() {
        return notifId;
    }

    public void setNotifId(String notifId) {
        this.notifId = notifId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

//    public String getNotifName() {
//        return notifName;
//    }
//
//    public void setNotifName(String notifName) {
//        this.notifName = notifName;
//    }

    public String getTipeNotifId() {
        return tipeNotifId;
    }

    public void setTipeNotifId(String tipeNotifId) {
        this.tipeNotifId = tipeNotifId;
    }

    public String getTipeNotifName() {
        return tipeNotifName;
    }

    public void setTipeNotifName(String tipeNotifName) {
        this.tipeNotifName = tipeNotifName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getFromPerson() {
        return fromPerson;
    }

    public void setFromPerson(String fromPerson) {
        this.fromPerson = fromPerson;
    }

    public String getNoRequest() {
        return noRequest;
    }

    public void setNoRequest(String noRequest) {
        this.noRequest = noRequest;
    }

    public String getJmlPemberitahuan() {
        return jmlPemberitahuan;
    }

    public void setJmlPemberitahuan(String jmlPemberitahuan) {
        this.jmlPemberitahuan = jmlPemberitahuan;
    }

    public String getJmlApproval() {
        return jmlApproval;
    }

    public void setJmlApproval(String jmlApproval) {
        this.jmlApproval = jmlApproval;
    }
}