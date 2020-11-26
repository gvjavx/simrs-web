package com.neurix.simrs.master.dokter.model;

import java.io.Serializable;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class DokterSpesialisPK implements Serializable{
    private String idDokter;
    private String idSpesialis;

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }
}
