package com.neurix.hris.master.biayaPerjalananDinas.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class BiayaPerjalananDinas extends BaseModel {
    private String biayaId;
    private String biayaName;

    public String getBiayaId() {

        return biayaId;
    }

    public void setBiayaId(String biayaId) {
        this.biayaId = biayaId;
    }

    public String getBiayaName() {
        return biayaName;
    }

    public void setBiayaName(String biayaName) {
        this.biayaName = biayaName;
    }
}