package com.neurix.hris.master.statusRekruitment.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImStatusRekruitmentEntity implements Serializable {

    private BigInteger statusRekruitmentId;
    private String statusRekruitmentName;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;


    public BigInteger getStatusRekruitmentId() {
        return statusRekruitmentId;
    }

    public void setStatusRekruitmentId(BigInteger statusRekruitmentId) {
        this.statusRekruitmentId = statusRekruitmentId;
    }

    public String getStatusRekruitmentName() {
        return statusRekruitmentName;
    }

    public void setStatusRekruitmentName(String statusRekruitmentName) {
        this.statusRekruitmentName = statusRekruitmentName;
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
