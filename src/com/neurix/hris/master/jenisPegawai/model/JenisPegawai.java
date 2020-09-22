package com.neurix.hris.master.jenisPegawai.model;

import com.neurix.common.model.BaseModel;

public class JenisPegawai extends BaseModel {
    private String jenisPegawaiId;
    private String jenisPegawaiName;

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
