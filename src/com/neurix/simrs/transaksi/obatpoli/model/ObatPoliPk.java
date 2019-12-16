package com.neurix.simrs.transaksi.obatpoli.model;

import java.io.Serializable;

/**
 * Created by Toshiba on 10/12/2019.
 */
public class ObatPoliPk implements Serializable {

    private String idObat;
    private String idPelayanan;

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }
}
