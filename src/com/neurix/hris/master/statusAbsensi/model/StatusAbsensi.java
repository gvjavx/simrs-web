package com.neurix.hris.master.statusAbsensi.model;

import com.neurix.common.model.BaseModel;

public class StatusAbsensi extends BaseModel {
    private String statusAbsensiId;
    private String statusAbsensiName;

    public String getStatusAbsensiId() {
        return statusAbsensiId;
    }

    public void setStatusAbsensiId(String statusAbsensiId) {
        this.statusAbsensiId = statusAbsensiId;
    }

    public String getStatusAbsensiName() {
        return statusAbsensiName;
    }

    public void setStatusAbsensiName(String statusAbsensiName) {
        this.statusAbsensiName = statusAbsensiName;
    }
}
