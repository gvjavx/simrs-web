package com.neurix.hris.master.pelatihanJabatan.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PelatihanJabatan extends BaseModel {
    private String pelatihanId;
    private String pelatihanName;

    public String getPelatihanId() {
        return pelatihanId;
    }

    public void setPelatihanId(String pelatihanId) {
        this.pelatihanId = pelatihanId;
    }

    public String getPelatihanName() {
        return pelatihanName;
    }

    public void setPelatihanName(String pelatihanName) {
        this.pelatihanName = pelatihanName;
    }
}