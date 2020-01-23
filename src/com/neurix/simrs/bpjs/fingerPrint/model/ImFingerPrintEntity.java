package com.neurix.simrs.bpjs.fingerPrint.model;

import java.io.Serializable;

public class ImFingerPrintEntity implements Serializable{
    private String pasienId;
    private String fingerId;
    private String fingerData;

    public String getPasienId() {
        return pasienId;
    }

    public void setPasienId(String pasienId) {
        this.pasienId = pasienId;
    }

    public String getFingerId() {
        return fingerId;
    }

    public void setFingerId(String fingerId) {
        this.fingerId = fingerId;
    }

    public String getFingerData() {
        return fingerData;
    }

    public void setFingerData(String fingerData) {
        this.fingerData = fingerData;
    }
}