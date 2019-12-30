package com.neurix.simrs.bpjs.model;

import com.neurix.common.model.BaseModel;

public class FaskesResponse extends BaseModel {
    private String idFaskesBpjs;
    private String kodeFaskesBpjs;
    private String namaFaskesBpjs;

    public String getIdFaskesBpjs() {
        return idFaskesBpjs;
    }

    public void setIdFaskesBpjs(String idFaskesBpjs) {
        this.idFaskesBpjs = idFaskesBpjs;
    }

    public String getKodeFaskesBpjs() {
        return kodeFaskesBpjs;
    }

    public void setKodeFaskesBpjs(String kodeFaskesBpjs) {
        this.kodeFaskesBpjs = kodeFaskesBpjs;
    }

    public String getNamaFaskesBpjs() {
        return namaFaskesBpjs;
    }

    public void setNamaFaskesBpjs(String namaFaskesBpjs) {
        this.namaFaskesBpjs = namaFaskesBpjs;
    }
}
