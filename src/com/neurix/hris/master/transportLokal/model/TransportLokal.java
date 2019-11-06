package com.neurix.hris.master.transportLokal.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class TransportLokal extends BaseModel {
    private String transportLokalId;
    private String transportLokalName;
    private String transportLokalKe;
    private BigDecimal jumlahBiaya ;

    public String getTransportLokalKe() {
        return transportLokalKe;
    }

    public void setTransportLokalKe(String transportLokalKe) {
        this.transportLokalKe = transportLokalKe;
    }

    public BigDecimal getJumlahBiaya() {
        return jumlahBiaya;
    }

    public void setJumlahBiaya(BigDecimal jumlahBiaya) {
        this.jumlahBiaya = jumlahBiaya;
    }

    public String getTransportLokalId() {
        return transportLokalId;
    }

    public void setTransportLokalId(String transportLokalId) {
        this.transportLokalId = transportLokalId;
    }

    public String getTransportLokalName() {
        return transportLokalName;
    }

    public void setTransportLokalName(String transportLokalName) {
        this.transportLokalName = transportLokalName;
    }
}