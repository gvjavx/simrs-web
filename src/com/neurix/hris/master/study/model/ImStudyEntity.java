package com.neurix.hris.master.study.model;

import com.neurix.hris.master.studyJurusan.model.ImStudyJurusanEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImStudyEntity implements Serializable {

    private String studyId;
    private String nip;
    private String typeStudy;
    private String studyName;
    private String tahunAwal;
    private String programStudy;
    private String tahunAkhir;
    private String studyJurusanId;
    private String fakultasId;
    private String fakultasName;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImStudyJurusanEntity imStudyJurusanEntity;

    public ImStudyJurusanEntity getImStudyJurusanEntity() {
        return imStudyJurusanEntity;
    }

    public void setImStudyJurusanEntity(ImStudyJurusanEntity imStudyJurusanEntity) {
        this.imStudyJurusanEntity = imStudyJurusanEntity;
    }

    public String getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(String programStudy) {
        this.programStudy = programStudy;
    }

    public String getFakultasId() {
        return fakultasId;
    }

    public void setFakultasId(String fakultasId) {
        this.fakultasId = fakultasId;
    }

    public String getFakultasName() {
        return fakultasName;
    }

    public void setFakultasName(String fakultasName) {
        this.fakultasName = fakultasName;
    }

    public String getStudyJurusanId() {
        return studyJurusanId;
    }

    public void setStudyJurusanId(String studyJurusanId) {
        this.studyJurusanId = studyJurusanId;
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTypeStudy() {
        return typeStudy;
    }

    public void setTypeStudy(String typeStudy) {
        this.typeStudy = typeStudy;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getTahunAwal() {
        return tahunAwal;
    }

    public void setTahunAwal(String tahunAwal) {
        this.tahunAwal = tahunAwal;
    }

    public String getTahunAkhir() {
        return tahunAkhir;
    }

    public void setTahunAkhir(String tahunAkhir) {
        this.tahunAkhir = tahunAkhir;
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
