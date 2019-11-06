package com.neurix.hris.transaksi.sppd.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SppdDoc extends BaseModel {
    private String docSppdId;
    private String sppdId;
    private String sppdPersonId;
    private String sppdPerson;
    private String fileUploadDoc;
    private String fileType;
    private String path;
    private String note;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getSppdPerson() {
        return sppdPerson;
    }

    public void setSppdPerson(String sppdPerson) {
        this.sppdPerson = sppdPerson;
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
}