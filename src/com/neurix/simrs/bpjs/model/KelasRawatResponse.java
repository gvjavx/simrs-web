package com.neurix.simrs.bpjs.model;

import com.neurix.common.model.BaseModel;

public class KelasRawatResponse extends BaseModel {
    private String idKelasRawatBpjs;
    private String kodeKelasRawatBpjs;
    private String namaKelasRawatBpjs;

    public String getIdKelasRawatBpjs() {
        return idKelasRawatBpjs;
    }

    public void setIdKelasRawatBpjs(String idKelasRawatBpjs) {
        this.idKelasRawatBpjs = idKelasRawatBpjs;
    }

    public String getKodeKelasRawatBpjs() {
        return kodeKelasRawatBpjs;
    }

    public void setKodeKelasRawatBpjs(String kodeKelasRawatBpjs) {
        this.kodeKelasRawatBpjs = kodeKelasRawatBpjs;
    }

    public String getNamaKelasRawatBpjs() {
        return namaKelasRawatBpjs;
    }

    public void setNamaKelasRawatBpjs(String namaKelasRawatBpjs) {
        this.namaKelasRawatBpjs = namaKelasRawatBpjs;
    }
}
