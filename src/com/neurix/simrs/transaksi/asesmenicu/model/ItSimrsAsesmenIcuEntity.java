package com.neurix.simrs.transaksi.asesmenicu.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsAsesmenIcuEntity {

    private String idAsesmenIcu;
    private String idDetailCheckup;
    private String parameter;
    private String jawaban;
    private String jenis;
    private String keterangan;
    private String tipe;
    private Integer score;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String informasi;
    private String namaTerang;
    private String sip;

    public String getNamaTerang() {
        return namaTerang;
    }

    public void setNamaTerang(String namaTerang) {
        this.namaTerang = namaTerang;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }

    public String getIdAsesmenIcu() {
        return idAsesmenIcu;
    }

    public void setIdAsesmenIcu(String idAsesmenIcu) {
        this.idAsesmenIcu = idAsesmenIcu;
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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItSimrsAsesmenIcuEntity that = (ItSimrsAsesmenIcuEntity) o;
        return Objects.equals(idAsesmenIcu, that.idAsesmenIcu) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(parameter, that.parameter) &&
                Objects.equals(jawaban, that.jawaban) &&
                Objects.equals(jenis, that.jenis) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(tipe, that.tipe) &&
                Objects.equals(score, that.score) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsesmenIcu, idDetailCheckup, parameter, jawaban, jenis, keterangan, tipe, score, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho);
    }
}
