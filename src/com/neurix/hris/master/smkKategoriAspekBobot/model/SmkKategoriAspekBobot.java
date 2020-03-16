package com.neurix.hris.master.smkKategoriAspekBobot.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkKategoriAspekBobot extends BaseModel {
    private String kategoriAspekBoobotId;
    private String kategoriAspekId;
    private String jabatanSmkId;
    private double bobot;

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
    }

    public String getJabatanSmkId() {
        return jabatanSmkId;
    }

    public void setJabatanSmkId(String jabatanSmkId) {
        this.jabatanSmkId = jabatanSmkId;
    }

    public String getKategoriAspekBoobotId() {
        return kategoriAspekBoobotId;
    }

    public void setKategoriAspekBoobotId(String kategoriAspekBoobotId) {
        this.kategoriAspekBoobotId = kategoriAspekBoobotId;
    }

    public String getKategoriAspekId() {
        return kategoriAspekId;
    }

    public void setKategoriAspekId(String kategoriAspekId) {
        this.kategoriAspekId = kategoriAspekId;
    }
}