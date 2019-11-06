package com.neurix.hris.transaksi.absensi.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImMesinAbsensiEntity implements Serializable {
    private String mesinId;
    private String mesinName;
    private String mesinSn;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getMesinId() {
        return mesinId;
    }

    public void setMesinId(String mesinId) {
        this.mesinId = mesinId;
    }

    public String getMesinName() {
        return mesinName;
    }

    public void setMesinName(String mesinName) {
        this.mesinName = mesinName;
    }

    public String getMesinSn() {
        return mesinSn;
    }

    public void setMesinSn(String mesinSn) {
        this.mesinSn = mesinSn;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
