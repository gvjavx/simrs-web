package com.neurix.simrs.bpjs.tindakan.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Timestamp;

public class TindakanBpjs extends BaseModel {

    private String idTindakanBpjs;
    private String kodeTindakanBpjs;
    private String namaTindakanBpjs;

    public String getIdTindakanBpjs() {
        return idTindakanBpjs;
    }

    public void setIdTindakanBpjs(String idTindakanBpjs) {
        this.idTindakanBpjs = idTindakanBpjs;
    }

    public String getKodeTindakanBpjs() {
        return kodeTindakanBpjs;
    }

    public void setKodeTindakanBpjs(String kodeTindakanBpjs) {
        this.kodeTindakanBpjs = kodeTindakanBpjs;
    }

    public String getNamaTindakanBpjs() {
        return namaTindakanBpjs;
    }

    public void setNamaTindakanBpjs(String namaTindakanBpjs) {
        this.namaTindakanBpjs = namaTindakanBpjs;
    }
}