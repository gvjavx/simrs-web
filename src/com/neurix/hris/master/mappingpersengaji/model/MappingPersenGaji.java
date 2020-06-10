package com.neurix.hris.master.mappingpersengaji.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Timestamp;

public class MappingPersenGaji extends BaseModel implements Serializable {

    private String mappingPersenGajiId;
    private String namaMappingPersenGaji;
    private String strNamaMappingPersenGaji;
    private String jenisGaji;
    private String strJenisGaji;
    private Integer presentase;
    private Timestamp createdDate;
    private String createWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    private String stCreatedDate;
    private String stLastUpdate;

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateWho() {
        return createWho;
    }

    public void setCreateWho(String createWho) {
        this.createWho = createWho;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getJenisGaji() {
        return jenisGaji;
    }

    public void setJenisGaji(String jenisGaji) {
        this.jenisGaji = jenisGaji;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
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

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getStrJenisGaji() {
        return strJenisGaji;
    }

    public void setStrJenisGaji(String strJenisGaji) {
        this.strJenisGaji = strJenisGaji;
    }

    public String getStrNamaMappingPersenGaji() {
        return strNamaMappingPersenGaji;
    }

    public void setStrNamaMappingPersenGaji(String strNamaMappingPersenGaji) {
        this.strNamaMappingPersenGaji = strNamaMappingPersenGaji;
    }
}