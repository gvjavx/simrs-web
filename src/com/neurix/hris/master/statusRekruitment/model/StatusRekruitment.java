package com.neurix.hris.master.statusRekruitment.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class StatusRekruitment extends BaseModel {
    private BigInteger statusRekruitmentId;
    private String statusRekruitmentName;

    public String getStatusRekruitmentName() {
        return statusRekruitmentName;
    }

    public void setStatusRekruitmentName(String statusRekruitmentName) {
        this.statusRekruitmentName = statusRekruitmentName;
    }

    public BigInteger getStatusRekruitmentId() {
        return statusRekruitmentId;
    }

    public void setStatusRekruitmentId(BigInteger statusRekruitmentId) {
        this.statusRekruitmentId = statusRekruitmentId;
    }
}