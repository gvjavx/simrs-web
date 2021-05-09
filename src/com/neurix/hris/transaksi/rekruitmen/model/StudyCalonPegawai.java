package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class StudyCalonPegawai extends BaseModel {
    private String studyCalonPegawaiId;
    private String calonPegawaiId;
    private String tipeStudy;
    private String studyName;
    private Date tahunAwal;
    private Date tahunAkhir;
    private String stTahunAwal;
    private String stTahunAkhir;
    private String nilai;

    public String getStTahunAwal() {
        return stTahunAwal;
    }

    public void setStTahunAwal(String stTahunAwal) {
        this.stTahunAwal = stTahunAwal;
    }

    public String getStTahunAkhir() {
        return stTahunAkhir;
    }

    public void setStTahunAkhir(String stTahunAkhir) {
        this.stTahunAkhir = stTahunAkhir;
    }

    public String getStudyCalonPegawaiId() {
        return studyCalonPegawaiId;
    }

    public void setStudyCalonPegawaiId(String studyCalonPegawaiId) {
        this.studyCalonPegawaiId = studyCalonPegawaiId;
    }

    public String getCalonPegawaiId() {
        return calonPegawaiId;
    }

    public void setCalonPegawaiId(String calonPegawaiId) {
        this.calonPegawaiId = calonPegawaiId;
    }

    public String getTipeStudy() {
        return tipeStudy;
    }

    public void setTipeStudy(String tipeStudy) {
        this.tipeStudy = tipeStudy;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public Date getTahunAwal() {
        return tahunAwal;
    }

    public void setTahunAwal(Date tahunAwal) {
        this.tahunAwal = tahunAwal;
    }

    public Date getTahunAkhir() {
        return tahunAkhir;
    }

    public void setTahunAkhir(Date tahunAkhir) {
        this.tahunAkhir = tahunAkhir;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}