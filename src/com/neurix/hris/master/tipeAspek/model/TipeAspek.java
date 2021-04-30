package com.neurix.hris.master.tipeAspek.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class TipeAspek extends BaseModel {
    private String tipeAspekId;
    private String tipeAspekName;

    public String getTipeAspekId() {
        return tipeAspekId;
    }

    public void setTipeAspekId(String tipeAspekId) {
        this.tipeAspekId = tipeAspekId;
    }

    public String getTipeAspekName() {
        return tipeAspekName;
    }

    public void setTipeAspekName(String tipeAspekName) {
        this.tipeAspekName = tipeAspekName;
    }
}