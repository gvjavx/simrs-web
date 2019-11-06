package com.neurix.hris.transaksi.lembur.model;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class JamLemburEntity implements Serializable {
    private String jamLemburId;
    private String tipeHari;
    private Integer jamLembur;
    private Double pengaliJamLembur;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getJamLemburId() {
        return jamLemburId;
    }

    public void setJamLemburId(String jamLemburId) {
        this.jamLemburId = jamLemburId;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }

    public Integer getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(Integer jamLembur) {
        this.jamLembur = jamLembur;
    }

    public Double getPengaliJamLembur() {
        return pengaliJamLembur;
    }

    public void setPengaliJamLembur(Double pengaliJamLembur) {
        this.pengaliJamLembur = pengaliJamLembur;
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
