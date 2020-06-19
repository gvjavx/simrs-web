package com.neurix.simrs.mobileapi.model;

import java.sql.Timestamp;

/**
 * @author gondok
 * Wednesday, 10/06/20 10:28
 */
public class KasirMobile {
    private String pembayaranId;
    private String pembayaranName;
    private String coa;
    private String flag;
    private String action;

    public String getPembayaranId() {
        return pembayaranId;
    }

    public void setPembayaranId(String pembayaranId) {
        this.pembayaranId = pembayaranId;
    }

    public String getPembayaranName() {
        return pembayaranName;
    }

    public void setPembayaranName(String pembayaranName) {
        this.pembayaranName = pembayaranName;
    }

    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
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
