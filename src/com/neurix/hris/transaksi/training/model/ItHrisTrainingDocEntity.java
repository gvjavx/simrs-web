package com.neurix.hris.transaksi.training.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ItHrisTrainingDocEntity implements Serializable {

    private String trainingDocId;
    private String trainingPersonId;
    private String fileUploadDoc;
    private String note;
    private String trainingId;
    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateDateWho() {
        return createDateWho;
    }

    public void setCreateDateWho(String createDateWho) {
        this.createDateWho = createDateWho;
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
}
