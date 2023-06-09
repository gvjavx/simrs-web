package com.neurix.hris.master.study.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Study extends BaseModel {
    private String studyId;
    private String nip;
    private String typeStudy;
    private String studyName;
    private String tahunAwal;
    private String tahunAkhir;
    private String programStudy;
    private String fakultasId;
    private String fakultasName;

    private String studyFakultas;
    private byte[] contentFile;
    private String fotoUpload;
    private String uploadFile;
    private String filePath;
    private String fileType;

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

    public byte[] getContentFile() {
        return contentFile;
    }

    public void setContentFile(byte[] contentFile) {
        this.contentFile = contentFile;
    }

    public String getFotoUpload() {
        return fotoUpload;
    }

    public void setFotoUpload(String fotoUpload) {
        this.fotoUpload = fotoUpload;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getStudyFakultas() {
        return studyFakultas;
    }

    public void setStudyFakultas(String studyFakultas) {
        this.studyFakultas = studyFakultas;
    }
}