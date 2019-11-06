package com.neurix.hris.transaksi.sppd.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItSppdDocEntity implements Serializable {
    private String docSppdId;
    private String sppdId;
    private String sppdPersonId;
    private String fileUploadDoc;
    private String fileType;
    private String note;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImSppdEntity imSppdEntity;

    public ImSppdEntity getImSppdEntity() {
        return imSppdEntity;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSppdPersonId() {
        return sppdPersonId;
    }

    public void setSppdPersonId(String sppdPersonId) {
        this.sppdPersonId = sppdPersonId;
    }

    public void setImSppdEntity(ImSppdEntity imSppdEntity) {
        this.imSppdEntity = imSppdEntity;
    }

    public String getDocSppdId() {
        return docSppdId;
    }

    public void setDocSppdId(String docSppdId) {
        this.docSppdId = docSppdId;
    }

    public String getSppdId() {
        return sppdId;
    }

    public void setSppdId(String sppdId) {
        this.sppdId = sppdId;
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
