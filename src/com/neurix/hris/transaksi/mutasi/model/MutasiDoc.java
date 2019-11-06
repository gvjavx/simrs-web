package com.neurix.hris.transaksi.mutasi.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class MutasiDoc extends BaseModel {
    private String docMutasiId ;
    private String fileUpload ;
    private String fileType ;
    private String mutasiId ;
    private String path ;

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
}