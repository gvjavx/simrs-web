package com.neurix.hris.master.tipePayroll.model;

import com.neurix.common.model.BaseModel;

public class TipePayroll extends BaseModel {
    private String tipePayrollId;
    private String tipePayrollName;

    public String getTipePayrollId() {
        return tipePayrollId;
    }

    public void setTipePayrollId(String tipePayrollId) {
        this.tipePayrollId = tipePayrollId;
    }

    public String getTipePayrollName() {
        return tipePayrollName;
    }

    public void setTipePayrollName(String tipePayrollName) {
        this.tipePayrollName = tipePayrollName;
    }
}
