package com.neurix.simrs.bpjs.tindakan.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ImSimrsTindakanBpjsEntity implements Serializable{

    private String idTindakanBpjs;
    private String kodeTindakanBpjs;
    private String namaTindakanBpjs;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdTindakanBpjs() {
        return idTindakanBpjs;
    }

    public void setIdTindakanBpjs(String idTindakanBpjs) {
        this.idTindakanBpjs = idTindakanBpjs;
    }

    public String getKodeTindakanBpjs() {
        return kodeTindakanBpjs;
    }

    public void setKodeTindakanBpjs(String kodeTindakanBpjs) {
        this.kodeTindakanBpjs = kodeTindakanBpjs;
    }

    public String getNamaTindakanBpjs() {
        return namaTindakanBpjs;
    }

    public void setNamaTindakanBpjs(String namaTindakanBpjs) {
        this.namaTindakanBpjs = namaTindakanBpjs;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
}