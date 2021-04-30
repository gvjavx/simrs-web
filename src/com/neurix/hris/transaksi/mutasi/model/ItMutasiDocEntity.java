package com.neurix.hris.transaksi.mutasi.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItMutasiDocEntity implements Serializable {
    private String docMutasiId ;
    private String fileUpload ;
    private String fileType ;
    private String mutasiId ;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDocMutasiId() {
        return docMutasiId;
    }

    public void setDocMutasiId(String docMutasiId) {
        this.docMutasiId = docMutasiId;
    }

    public String getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(String fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getMutasiId() {
        return mutasiId;
    }

    public void setMutasiId(String mutasiId) {
        this.mutasiId = mutasiId;
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
