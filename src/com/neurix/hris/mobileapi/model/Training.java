package com.neurix.hris.mobileapi.model;

import java.io.Serializable;

public class Training implements Serializable {
    private String trainingId;
    private String trainingPersonId;
    private String trainingName;
    private String nip;
    private String userName;
    private String tanggalAwalSt;
    private String tanggalAkhirSt;
    private String unit;

    public String getTrainingPersonId() {
        return trainingPersonId;
    }

    public void setTrainingPersonId(String trainingPersonId) {
        this.trainingPersonId = trainingPersonId;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTanggalAwalSt() {
        return tanggalAwalSt;
    }

    public void setTanggalAwalSt(String tanggalAwalSt) {
        this.tanggalAwalSt = tanggalAwalSt;
    }

    public String getTanggalAkhirSt() {
        return tanggalAkhirSt;
    }

    public void setTanggalAkhirSt(String tanggalAkhirSt) {
        this.tanggalAkhirSt = tanggalAkhirSt;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
