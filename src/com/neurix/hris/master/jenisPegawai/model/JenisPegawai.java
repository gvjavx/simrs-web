package com.neurix.hris.master.jenisPegawai.model;

import com.neurix.common.model.BaseModel;

public class JenisPegawai extends BaseModel {
    private String jenisPegawaiId;
    private String jenisPegawaiName;
    private String flagDefault;
    private String flag;

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagDefault() {
        return flagDefault;
    }

    public void setFlagDefault(String flagDefault) {
        this.flagDefault = flagDefault;
    }

    public String getJenisPegawaiId() {
        return jenisPegawaiId;
    }

    public void setJenisPegawaiId(String jenisPegawaiId) {
        this.jenisPegawaiId = jenisPegawaiId;
    }

    public String getJenisPegawaiName() {
        return jenisPegawaiName;
    }

    public void setJenisPegawaiName(String jenisPegawaiName) {
        this.jenisPegawaiName = jenisPegawaiName;
    }
}
