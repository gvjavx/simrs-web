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