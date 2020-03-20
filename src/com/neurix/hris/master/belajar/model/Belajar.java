package com.neurix.hris.master.belajar.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Belajar extends BaseModel {
    private String belajarId;
    private String belajarName;

    public String getBelajarId() {
        return belajarId;
    }

    public void setBelajarId(String belajarId) {
        this.belajarId = belajarId;
    }

    public String getBelajarName() {
        return belajarName;
    }

    public void setBelajarName(String belajarName) {
        this.belajarName = belajarName;
    }
}