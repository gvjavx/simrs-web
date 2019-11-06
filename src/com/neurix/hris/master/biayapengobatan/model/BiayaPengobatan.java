package com.neurix.hris.master.biayapengobatan.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class BiayaPengobatan extends BaseModel implements Serializable {
    private String biayaPengobatanId;
    private String biayaPengobatanName;

    public String getBiayaPengobatanId() {
        return biayaPengobatanId;
    }

    public void setBiayaPengobatanId(String biayaPengobatanId) {
        this.biayaPengobatanId = biayaPengobatanId;
    }

    public String getBiayaPengobatanName() {
        return biayaPengobatanName;
    }

    public void setBiayaPengobatanName(String biayaPengobatanName) {
        this.biayaPengobatanName = biayaPengobatanName;
    }
}
