package com.neurix.hris.master.tunjangan.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class Tunjangan extends BaseModel implements Serializable {
    private String tunjanganId;
    private String tunjanganName;

    public String getTunjanganId() {
        return tunjanganId;
    }

    public void setTunjanganId(String tunjanganId) {
        this.tunjanganId = tunjanganId;
    }

    public String getTunjanganName() {
        return tunjanganName;
    }

    public void setTunjanganName(String tunjanganName) {
        this.tunjanganName = tunjanganName;
    }
}
