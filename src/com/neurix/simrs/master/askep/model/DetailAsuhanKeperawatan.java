package com.neurix.simrs.master.askep.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class DetailAsuhanKeperawatan extends BaseModel {

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
