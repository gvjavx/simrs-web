package com.neurix.simrs.transaksi.profilrekammedisrj.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class RekamMedisRawatJalan extends BaseModel {

    private String idProfilRekamMedisRj;
    private String idDetailCheckup;
    private Timestamp waktu;
    private String anamnese;
    private String pemeriksaanFisik;
    private String diagnosa;
    private String planing;
    private String keterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdProfilRekamMedisRj() {
        return idProfilRekamMedisRj;
    }

    public void setIdProfilRekamMedisRj(String idProfilRekamMedisRj) {
        this.idProfilRekamMedisRj = idProfilRekamMedisRj;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Timestamp getWaktu() {
        return waktu;
    }

    public void setWaktu(Timestamp waktu) {
        this.waktu = waktu;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getPemeriksaanFisik() {
        return pemeriksaanFisik;
    }

    public void setPemeriksaanFisik(String pemeriksaanFisik) {
        this.pemeriksaanFisik = pemeriksaanFisik;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getPlaning() {
        return planing;
    }

    public void setPlaning(String planing) {
        this.planing = planing;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
