package com.neurix.simrs.transaksi.asesmenrawatjalan.model;

import java.sql.Timestamp;
import java.util.Objects;

public class KeperawatanRawatJalan {
    private String idKeperawatanRawatJalan;
    private String idDetailCheckup;
    private String parameter;
    private String jawaban;
    private String keterangan;
    private String jenis;
    private Integer score;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String tipe;
    private String namaTerang;
    private String sip;
    private String noCheckup;

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getNamaTerang() {
        return namaTerang;
    }

    public void setNamaTerang(String namaTerang) {
        this.namaTerang = namaTerang;
    }

    public String getIdKeperawatanRawatJalan() {
        return idKeperawatanRawatJalan;
    }

    public void setIdKeperawatanRawatJalan(String idKeperawatanRawatJalan) {
        this.idKeperawatanRawatJalan = idKeperawatanRawatJalan;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeperawatanRawatJalan that = (KeperawatanRawatJalan) o;
        return Objects.equals(idKeperawatanRawatJalan, that.idKeperawatanRawatJalan) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(parameter, that.parameter) &&
                Objects.equals(jawaban, that.jawaban) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(jenis, that.jenis) &&
                Objects.equals(score, that.score) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(tipe, that.tipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKeperawatanRawatJalan, idDetailCheckup, parameter, jawaban, keterangan, jenis, score, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho, tipe);
    }
}
