package com.neurix.hris.master.payrollDanaPensiun.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class payrollDanaPensiun extends BaseModel {
    private String danaPensiunId;
    private String danaPensiun;
    private BigDecimal persentase;
    private BigDecimal persentaseKary;
    private BigDecimal persentasePers;
    private String stPersentaseKary;
    private String stPersentasePers;

    public String getStPersentaseKary() {
        return stPersentaseKary;
    }

    public void setStPersentaseKary(String stPersentaseKary) {
        this.stPersentaseKary = stPersentaseKary;
    }

    public String getStPersentasePers() {
        return stPersentasePers;
    }

    public void setStPersentasePers(String stPersentasePers) {
        this.stPersentasePers = stPersentasePers;
    }

    public BigDecimal getPersentaseKary() {
        return persentaseKary;
    }

    public void setPersentaseKary(BigDecimal persentaseKary) {
        this.persentaseKary = persentaseKary;
    }

    public BigDecimal getPersentasePers() {
        return persentasePers;
    }

    public void setPersentasePers(BigDecimal persentasePers) {
        this.persentasePers = persentasePers;
    }

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public String getDanaPensiunId() {
        return danaPensiunId;
    }

    public void setDanaPensiunId(String danaPensiunId) {
        this.danaPensiunId = danaPensiunId;
    }

    public BigDecimal getPersentase() {
        return persentase;
    }

    public void setPersentase(BigDecimal persentase) {
        this.persentase = persentase;
    }
}