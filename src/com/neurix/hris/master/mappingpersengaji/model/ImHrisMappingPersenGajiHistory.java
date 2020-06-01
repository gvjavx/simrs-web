package com.neurix.hris.master.mappingpersengaji.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImHrisMappingPersenGajiHistory implements Serializable{

    private String mappingPersenGajiHistoryId;
    private String mappingPersenGajiId;
    private String namaMappingPersenGaji;
    private String jenisGaji;
    private Integer presentase;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;


    public String getMappingPersenGajiHistoryId() {
        return mappingPersenGajiHistoryId;
    }

    public void setMappingPersenGajiHistoryId(String mappingPersenGajiHistoryId) {
        this.mappingPersenGajiHistoryId = mappingPersenGajiHistoryId;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getJenisGaji() {
        return jenisGaji;
    }

    public void setJenisGaji(String jenisGaji) {
        this.jenisGaji = jenisGaji;
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

    public String getMappingPersenGajiId() {
        return mappingPersenGajiId;
    }

    public void setMappingPersenGajiId(String mappingPersenGajiId) {
        this.mappingPersenGajiId = mappingPersenGajiId;
    }

    public String getNamaMappingPersenGaji() {
        return namaMappingPersenGaji;
    }

    public void setNamaMappingPersenGaji(String namaMappingPersenGaji) {
        this.namaMappingPersenGaji = namaMappingPersenGaji;
    }

    public Integer getPresentase() {
        return presentase;
    }

    public void setPresentase(Integer presentase) {
        this.presentase = presentase;
    }

}