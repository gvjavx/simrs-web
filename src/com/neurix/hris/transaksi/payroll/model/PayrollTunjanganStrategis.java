package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollTunjanganStrategis extends BaseModel {
    private String tunjStrategisId ;
    private String positionId ;
    private double nilai ;

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getTunjStrategisId() {
        return tunjStrategisId;
    }

    public void setTunjStrategisId(String tunjStrategisId) {
        this.tunjStrategisId = tunjStrategisId;
    }
}