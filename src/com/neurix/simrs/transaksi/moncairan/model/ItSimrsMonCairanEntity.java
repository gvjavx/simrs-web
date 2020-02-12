package com.neurix.simrs.transaksi.moncairan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 12/02/20.
 */
public class ItSimrsMonCairanEntity implements Serializable {

    private String id;
    private String macamCairan;
    private String melalui;
    private String jumlah;
    private String jamMulai;
    private String jamSelesai;
    private String cekTambahanObat;
    private String jamUkurBuang;
    private String dari;
    private String balanceCairan;
    private String keterangan;
    private String noCheckup;
    private String idDetailCheckup;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMacamCairan() {
        return macamCairan;
    }

    public void setMacamCairan(String macamCairan) {
        this.macamCairan = macamCairan;
    }

    public String getMelalui() {
        return melalui;
    }

    public void setMelalui(String melalui) {
        this.melalui = melalui;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getCekTambahanObat() {
        return cekTambahanObat;
    }

    public void setCekTambahanObat(String cekTambahanObat) {
        this.cekTambahanObat = cekTambahanObat;
    }

    public String getJamUkurBuang() {
        return jamUkurBuang;
    }

    public void setJamUkurBuang(String jamUkurBuang) {
        this.jamUkurBuang = jamUkurBuang;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getBalanceCairan() {
        return balanceCairan;
    }

    public void setBalanceCairan(String balanceCairan) {
        this.balanceCairan = balanceCairan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
}
