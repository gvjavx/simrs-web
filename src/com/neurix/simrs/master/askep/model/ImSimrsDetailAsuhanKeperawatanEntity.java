package com.neurix.simrs.master.askep.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsDetailAsuhanKeperawatanEntity {

    private String idDetailAsuhanKeperawatan;
    private String idDiagnosaAsuhanKeperawatan;
    private String diagnosis;
    private String keteranganDiagnosis;
    private String hasil;
    private String keteranganHasil;
    private String intervensi;
    private String keteranganIntervensi;
    private String implementasi;
    private String keteranganImplementasi;
    private String evaluasi;
    private String keteranganEvaluasi;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDetailAsuhanKeperawatan() {
        return idDetailAsuhanKeperawatan;
    }

    public void setIdDetailAsuhanKeperawatan(String idDetailAsuhanKeperawatan) {
        this.idDetailAsuhanKeperawatan = idDetailAsuhanKeperawatan;
    }

    public String getIdDiagnosaAsuhanKeperawatan() {
        return idDiagnosaAsuhanKeperawatan;
    }

    public void setIdDiagnosaAsuhanKeperawatan(String idDiagnosaAsuhanKeperawatan) {
        this.idDiagnosaAsuhanKeperawatan = idDiagnosaAsuhanKeperawatan;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getKeteranganDiagnosis() {
        return keteranganDiagnosis;
    }

    public void setKeteranganDiagnosis(String keteranganDiagnosis) {
        this.keteranganDiagnosis = keteranganDiagnosis;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getKeteranganHasil() {
        return keteranganHasil;
    }

    public void setKeteranganHasil(String keteranganHasil) {
        this.keteranganHasil = keteranganHasil;
    }

    public String getIntervensi() {
        return intervensi;
    }

    public void setIntervensi(String intervensi) {
        this.intervensi = intervensi;
    }

    public String getKeteranganIntervensi() {
        return keteranganIntervensi;
    }

    public void setKeteranganIntervensi(String keteranganIntervensi) {
        this.keteranganIntervensi = keteranganIntervensi;
    }

    public String getImplementasi() {
        return implementasi;
    }

    public void setImplementasi(String implementasi) {
        this.implementasi = implementasi;
    }

    public String getKeteranganImplementasi() {
        return keteranganImplementasi;
    }

    public void setKeteranganImplementasi(String keteranganImplementasi) {
        this.keteranganImplementasi = keteranganImplementasi;
    }

    public String getEvaluasi() {
        return evaluasi;
    }

    public void setEvaluasi(String evaluasi) {
        this.evaluasi = evaluasi;
    }

    public String getKeteranganEvaluasi() {
        return keteranganEvaluasi;
    }

    public void setKeteranganEvaluasi(String keteranganEvaluasi) {
        this.keteranganEvaluasi = keteranganEvaluasi;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImSimrsDetailAsuhanKeperawatanEntity that = (ImSimrsDetailAsuhanKeperawatanEntity) o;
        return Objects.equals(idDetailAsuhanKeperawatan, that.idDetailAsuhanKeperawatan) &&
                Objects.equals(idDiagnosaAsuhanKeperawatan, that.idDiagnosaAsuhanKeperawatan) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(keteranganDiagnosis, that.keteranganDiagnosis) &&
                Objects.equals(hasil, that.hasil) &&
                Objects.equals(keteranganHasil, that.keteranganHasil) &&
                Objects.equals(intervensi, that.intervensi) &&
                Objects.equals(keteranganIntervensi, that.keteranganIntervensi) &&
                Objects.equals(implementasi, that.implementasi) &&
                Objects.equals(keteranganImplementasi, that.keteranganImplementasi) &&
                Objects.equals(evaluasi, that.evaluasi) &&
                Objects.equals(keteranganEvaluasi, that.keteranganEvaluasi) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetailAsuhanKeperawatan, idDiagnosaAsuhanKeperawatan, diagnosis, keteranganDiagnosis, hasil, keteranganHasil, intervensi, keteranganIntervensi, implementasi, keteranganImplementasi, evaluasi, keteranganEvaluasi, flag, action, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
