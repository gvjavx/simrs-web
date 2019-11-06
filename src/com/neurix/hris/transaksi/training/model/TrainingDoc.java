package com.neurix.hris.transaksi.training.model;

import com.neurix.common.model.BaseModel;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class TrainingDoc extends BaseModel implements Serializable {

    private String trainingDocId;
    private String trainingPersonId;
    private String fileUploadDoc;
    private String note;
    private String trainingId;

    private File fileUploadA;
    private String fileUploadAContentType;
    private String FileUploadAFileName;

    private String filePath;
    private String tipe;
    private String status;
    private String project;

    private boolean add = false;

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public String getTrainingDocId() {
        return trainingDocId;
    }

    public void setTrainingDocId(String trainingDocId) {
        this.trainingDocId = trainingDocId;
    }

    public String getTrainingPersonId() {
        return trainingPersonId;
    }

    public void setTrainingPersonId(String trainingPersonId) {
        this.trainingPersonId = trainingPersonId;
    }

    public String getFileUploadDoc() {
        return fileUploadDoc;
    }

    public void setFileUploadDoc(String fileUploadDoc) {
        this.fileUploadDoc = fileUploadDoc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
