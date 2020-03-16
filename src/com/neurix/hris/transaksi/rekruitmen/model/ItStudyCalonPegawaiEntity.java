package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class ItStudyCalonPegawaiEntity extends BaseModel {
    private String studyCalonPegawaiId;
    private String calonPegawaiId;
    private String tipeStudy;
    private String studyName;
    private Date tahunAwal;
    private Date tahunAkhir;
    private String nilai;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}