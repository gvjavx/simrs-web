package com.neurix.hris.master.golonganDapen.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class GolonganDapen extends BaseModel {
    private String golonganDapenId;
    private String golonganDapenName;

    public String getGolonganDapenId() {
        return golonganDapenId;
    }

    public void setGolonganDapenId(String golonganDapenId) {
        this.golonganDapenId = golonganDapenId;
    }

    public String getGolonganDapenName() {
        return golonganDapenName;
    }

    public void setGolonganDapenName(String golonganDapenName) {
        this.golonganDapenName = golonganDapenName;
    }
}