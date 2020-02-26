package com.neurix.simrs.transaksi.skorrawatinap.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 10/02/20.
 */
public class ImSimrsSkorRanapEntity implements Serializable {

    private String idSkor;
    private String namaSkor;
    private Integer skor;
    private String idParameter;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String ketSkor;

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

    public Integer getSkor() {
        return skor;
    }

    public void setSkor(Integer skor) {
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

    public String getKetSkor() {
        return ketSkor;
    }

    public void setKetSkor(String ketSkor) {
        this.ketSkor = ketSkor;
    }
}
