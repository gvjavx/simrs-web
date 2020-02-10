package com.neurix.simrs.transaksi.skorrawatinap.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 10/02/20.
 */
public class ImSimrsSkorRanapEntity implements Serializable {

    private String idSkor;
    private String namaSkor;
    private String skor;
    private String idParameter;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdSkor() {
        return idSkor;
    }

    public void setIdSkor(String idSkor) {
        this.idSkor = idSkor;
    }

    public String getNamaSkor() {
        return namaSkor;
    }

    public void setNamaSkor(String namaSkor) {
        this.namaSkor = namaSkor;
    }

    public String getSkor() {
        return skor;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }

    public String getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(String idParameter) {
        this.idParameter = idParameter;
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

    public String getCreateWho() {
        return createWho;
    }

    public void setCreateWho(String createWho) {
        this.createWho = createWho;
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
