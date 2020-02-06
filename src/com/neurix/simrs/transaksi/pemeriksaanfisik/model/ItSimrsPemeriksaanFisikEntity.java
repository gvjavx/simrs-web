package com.neurix.simrs.transaksi.pemeriksaanfisik.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 05/02/20.
 */
public class ItSimrsPemeriksaanFisikEntity implements Serializable {
    private String id;
    private String noCheckup;
    private String kepala;
    private String mata;
    private String leher;
    private String thorak;
    private String thorakChor;
    private String thorakPulmo;
    private String abdoman;
    private String extrimitas;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String tinggiBadan;
    private String beratBadan;
    private String nadi;
    private String respirationRate;
    private String tekananDarah;
    private String suhu;
    private String triase;

    public String getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(String tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public String getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(String beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getNadi() {
        return nadi;
    }

    public void setNadi(String nadi) {
        this.nadi = nadi;
    }

    public String getRespirationRate() {
        return respirationRate;
    }

    public void setRespirationRate(String respirationRate) {
        this.respirationRate = respirationRate;
    }

    public String getTekananDarah() {
        return tekananDarah;
    }

    public void setTekananDarah(String tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getTriase() {
        return triase;
    }

    public void setTriase(String triase) {
        this.triase = triase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getKepala() {
        return kepala;
    }

    public void setKepala(String kepala) {
        this.kepala = kepala;
    }

    public String getMata() {
        return mata;
    }

    public void setMata(String mata) {
        this.mata = mata;
    }

    public String getLeher() {
        return leher;
    }

    public void setLeher(String leher) {
        this.leher = leher;
    }

    public String getThorak() {
        return thorak;
    }

    public void setThorak(String thorak) {
        this.thorak = thorak;
    }

    public String getThorakChor() {
        return thorakChor;
    }

    public void setThorakChor(String thorakChor) {
        this.thorakChor = thorakChor;
    }

    public String getThorakPulmo() {
        return thorakPulmo;
    }

    public void setThorakPulmo(String thorakPulmo) {
        this.thorakPulmo = thorakPulmo;
    }

    public String getAbdoman() {
        return abdoman;
    }

    public void setAbdoman(String abdoman) {
        this.abdoman = abdoman;
    }

    public String getExtrimitas() {
        return extrimitas;
    }

    public void setExtrimitas(String extrimitas) {
        this.extrimitas = extrimitas;
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
}
