package com.neurix.akuntansi.master.tipeLaporan.model;

import com.neurix.common.model.BaseModel;

public class TipeLaporan extends BaseModel {
    private String tipeLaporanId;
    private String tipeLaporanName;

    public String getTipeLaporanId() {
        return tipeLaporanId;
    }

    public void setTipeLaporanId(String tipeLaporanId) {
        this.tipeLaporanId = tipeLaporanId;
    }

    public String getTipeLaporanName() {
        return tipeLaporanName;
    }

    public void setTipeLaporanName(String tipeLaporanName) {
        this.tipeLaporanName = tipeLaporanName;
    }
}
