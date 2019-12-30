package com.neurix.simrs.bpjs.model;

import com.neurix.common.model.BaseModel;

public class PoliResponse extends BaseModel {

    private String idPoliBpjs;
    private String kodePoliBpjs;
    private String namaPoliBpjs;

    public String getIdPoliBpjs() {
        return idPoliBpjs;
    }

    public void setIdPoliBpjs(String idPoliBpjs) {
        this.idPoliBpjs = idPoliBpjs;
    }

    public String getKodePoliBpjs() {
        return kodePoliBpjs;
    }

    public void setKodePoliBpjs(String kodePoliBpjs) {
        this.kodePoliBpjs = kodePoliBpjs;
    }

    public String getNamaPoliBpjs() {
        return namaPoliBpjs;
    }

    public void setNamaPoliBpjs(String namaPoliBpjs) {
        this.namaPoliBpjs = namaPoliBpjs;
    }
}