package com.neurix.hris.transaksi.medicalrecord.model;

import com.neurix.common.model.BaseModel;

import java.io.File;
import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class BuktiPengobatan extends BaseModel implements Serializable {
    private String buktiId;
    private String pengobatanId;
    private String fileName;
    private String tipeUpload;
    private String keterangan;

    private File fileUploadA;
    private String fileUploadAContentType;
    private String FileUploadAFileName;

    private String filePath;
    private String tipe;
    private String status;
    private String project;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    private boolean add;
    private boolean edit;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getFileUploadA() {
        return fileUploadA;
    }

    public void setFileUploadA(File fileUploadA) {
        this.fileUploadA = fileUploadA;
    }

    public String getFileUploadAContentType() {
        return fileUploadAContentType;
    }

    public void setFileUploadAContentType(String fileUploadAContentType) {
        this.fileUploadAContentType = fileUploadAContentType;
    }

    public String getFileUploadAFileName() {
        return FileUploadAFileName;
    }

    public void setFileUploadAFileName(String fileUploadAFileName) {
        FileUploadAFileName = fileUploadAFileName;
    }

    public String getBuktiId() {
        return buktiId;
    }

    public void setBuktiId(String buktiId) {
        this.buktiId = buktiId;
    }

    public String getPengobatanId() {
        return pengobatanId;
    }

    public void setPengobatanId(String pengobatanId) {
        this.pengobatanId = pengobatanId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTipeUpload() {
        return tipeUpload;
    }

    public void setTipeUpload(String tipeUpload) {
        this.tipeUpload = tipeUpload;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
