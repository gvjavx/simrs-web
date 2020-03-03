package com.neurix.hris.master.golongan.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Golongan extends BaseModel {
    private String golonganId;
    private String golonganName;
    private String stLevel;
    private Integer level;

    public String getStLevel() {
        return stLevel;
    }

    public void setStLevel(String stLevel) {
        this.stLevel = stLevel;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }
}