package com.neurix.simrs.transaksi.psikososial.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 06/02/20.
 */
public class ItSimrsDataPsikososialEntity implements Serializable {
    private String id;
    private String noCheckup;
    private String komunikasi;
    private String kemampuanBicara;
    private String tahuTentangSakitNya;
    private String konsepDiri;
    private String pernahDirawat;
    private String obatDariRumah;
    private String nyeri;
    private Integer intensitasNyeri;
    private String jenisIntensitasNyeri;
    private String numericRatingScale;
    private String wongBakerPainScale;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(String komunikasi) {
        this.komunikasi = komunikasi;
    }

    public String getKemampuanBicara() {
        return kemampuanBicara;
    }

    public void setKemampuanBicara(String kemampuanBicara) {
        this.kemampuanBicara = kemampuanBicara;
    }

    public String getTahuTentangSakitNya() {
        return tahuTentangSakitNya;
    }

    public void setTahuTentangSakitNya(String tahuTentangSakitNya) {
        this.tahuTentangSakitNya = tahuTentangSakitNya;
    }

    public String getKonsepDiri() {
        return konsepDiri;
    }

    public void setKonsepDiri(String konsepDiri) {
        this.konsepDiri = konsepDiri;
    }

    public String getPernahDirawat() {
        return pernahDirawat;
    }

    public void setPernahDirawat(String pernahDirawat) {
        this.pernahDirawat = pernahDirawat;
    }

    public String getObatDariRumah() {
        return obatDariRumah;
    }

    public void setObatDariRumah(String obatDariRumah) {
        this.obatDariRumah = obatDariRumah;
    }

    public String getNyeri() {
        return nyeri;
    }

    public void setNyeri(String nyeri) {
        this.nyeri = nyeri;
    }

    public Integer getIntensitasNyeri() {
        return intensitasNyeri;
    }

    public void setIntensitasNyeri(Integer intensitasNyeri) {
        this.intensitasNyeri = intensitasNyeri;
    }

    public String getJenisIntensitasNyeri() {
        return jenisIntensitasNyeri;
    }

    public void setJenisIntensitasNyeri(String jenisIntensitasNyeri) {
        this.jenisIntensitasNyeri = jenisIntensitasNyeri;
    }

    public String getNumericRatingScale() {
        return numericRatingScale;
    }

    public void setNumericRatingScale(String numericRatingScale) {
        this.numericRatingScale = numericRatingScale;
    }

    public String getWongBakerPainScale() {
        return wongBakerPainScale;
    }

    public void setWongBakerPainScale(String wongBakerPainScale) {
        this.wongBakerPainScale = wongBakerPainScale;
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
