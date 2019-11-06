package com.neurix.hris.master.masaTanam.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class MasaTanam extends BaseModel {
    private String masaTanamId;
    private String masaTanamName;
    private Date awalGiling;
    private Date akhirGiling;
    private String closed;

    public Date getAkhirGiling() {
        return akhirGiling;
    }

    public void setAkhirGiling(Date akhirGiling) {
        this.akhirGiling = akhirGiling;
    }

    public Date getAwalGiling() {
        return awalGiling;
    }

    public void setAwalGiling(Date awalGiling) {
        this.awalGiling = awalGiling;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getMasaTanamId() {
        return masaTanamId;
    }

    public void setMasaTanamId(String masaTanamId) {
        this.masaTanamId = masaTanamId;
    }

    public String getMasaTanamName() {
        return masaTanamName;
    }

    public void setMasaTanamName(String masaTanamName) {
        this.masaTanamName = masaTanamName;
    }
}