package com.neurix.simrs.mobileapi.model;

/**
 * @author gondok
 * Friday, 24/01/20 10:21
 */
public class StatusPasienMobile   {
    private String idStatusPasien;
    private String keterangan;
    private String flag;
    private String action;

    public String getIdStatusPasien() {
        return idStatusPasien;
    }

    public void setIdStatusPasien(String idStatusPasien) {
        this.idStatusPasien = idStatusPasien;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
