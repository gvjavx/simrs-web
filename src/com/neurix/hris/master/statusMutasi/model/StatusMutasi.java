package com.neurix.hris.master.statusMutasi.model;

import com.neurix.common.model.BaseModel;

public class StatusMutasi extends BaseModel {
    private String statusMutasiId;
    private String statusMutasiName;

    public String getStatusMutasiId() {
        return statusMutasiId;
    }

    public void setStatusMutasiId(String statusMutasiId) {
        this.statusMutasiId = statusMutasiId;
    }

    public String getStatusMutasiName() {
        return statusMutasiName;
    }

    public void setStatusMutasiName(String statusMutasiName) {
        this.statusMutasiName = statusMutasiName;
    }
}
