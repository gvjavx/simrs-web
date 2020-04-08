package com.neurix.simrs.master.pelayanan.model;

import java.io.Serializable;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class poliSpesialisPK implements Serializable {

    private String idPelayanan;
    private String idSpesialis;

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }
}
