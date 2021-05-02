package com.neurix.hris.master.smkIndikatorKinerja.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkIndikatorKinerja extends BaseModel {
    private String indikatorKinerjaId ;
    private String positionId;
    private String indikatorKinerja;
    private Long bobot;
    private Long target ;
    private String satuanTarget;

    public String getIndikatorKinerjaId() {
        return indikatorKinerjaId;
    }

    public void setIndikatorKinerjaId(String indikatorKinerjaId) {
        this.indikatorKinerjaId = indikatorKinerjaId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getIndikatorKinerja() {
        return indikatorKinerja;
    }

    public void setIndikatorKinerja(String indikatorKinerja) {
        this.indikatorKinerja = indikatorKinerja;
    }

    public Long getBobot() {
        return bobot;
    }

    public void setBobot(Long bobot) {
        this.bobot = bobot;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getSatuanTarget() {
        return satuanTarget;
    }

    public void setSatuanTarget(String satuanTarget) {
        this.satuanTarget = satuanTarget;
    }
}