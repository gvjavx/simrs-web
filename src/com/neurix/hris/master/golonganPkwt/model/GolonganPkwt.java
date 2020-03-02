package com.neurix.hris.master.golonganPkwt.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class GolonganPkwt extends BaseModel {
    private String golonganPkwtId;
    private String golonganPkwtName;

    public String getGolonganPkwtId() {
        return golonganPkwtId;
    }

    public void setGolonganPkwtId(String golonganPkwtId) {
        this.golonganPkwtId = golonganPkwtId;
    }

    public String getGolonganPkwtName() {
        return golonganPkwtName;
    }

    public void setGolonganPkwtName(String golonganPkwtName) {
        this.golonganPkwtName = golonganPkwtName;
    }
}