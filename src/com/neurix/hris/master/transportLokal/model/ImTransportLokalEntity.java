package com.neurix.hris.master.transportLokal.model;

import com.neurix.hris.master.provinsi.model.ImKotaEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImTransportLokalEntity implements Serializable {

    private String transportLokalId;
    private String transportLokalName;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String transportLokalKe;
    private BigDecimal jumlahBiaya ;

    private ImKotaEntity imKotaEntity;

    public ImKotaEntity getImKotaEntity() {
        return imKotaEntity;
    }

    public void setImKotaEntity(ImKotaEntity imKotaEntity) {
        this.imKotaEntity = imKotaEntity;
    }

    public String getTransportLokalKe() {
        return transportLokalKe;
    }

    public void setTransportLokalKe(String transportLokalKe) {
        this.transportLokalKe = transportLokalKe;
    }

    public BigDecimal getJumlahBiaya() {
        return jumlahBiaya;
    }

    public void setJumlahBiaya(BigDecimal jumlahBiaya) {
        this.jumlahBiaya = jumlahBiaya;
    }

    public String getTransportLokalId() {
        return transportLokalId;
    }

    public void setTransportLokalId(String transportLokalId) {
        this.transportLokalId = transportLokalId;
    }

    public String getTransportLokalName() {
        return transportLokalName;
    }

    public void setTransportLokalName(String transportLokalName) {
        this.transportLokalName = transportLokalName;
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
