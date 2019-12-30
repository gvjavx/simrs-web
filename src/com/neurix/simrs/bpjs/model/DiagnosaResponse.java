package com.neurix.simrs.bpjs.model;

import com.neurix.common.model.BaseModel;

public class DiagnosaResponse extends BaseModel {

    private String idDiagnosaBpjs;
    private String kodeDiagnosaBpjs;
    private String namaDiagnosaBpjs;

    public String getIdDiagnosaBpjs() {
        return idDiagnosaBpjs;
    }

    public void setIdDiagnosaBpjs(String idDiagnosaBpjs) {
        this.idDiagnosaBpjs = idDiagnosaBpjs;
    }

    public String getKodeDiagnosaBpjs() {
        return kodeDiagnosaBpjs;
    }

    public void setKodeDiagnosaBpjs(String kodeDiagnosaBpjs) {
        this.kodeDiagnosaBpjs = kodeDiagnosaBpjs;
    }

    public String getNamaDiagnosaBpjs() {
        return namaDiagnosaBpjs;
    }

    public void setNamaDiagnosaBpjs(String namaDiagnosaBpjs) {
        this.namaDiagnosaBpjs = namaDiagnosaBpjs;
    }
}