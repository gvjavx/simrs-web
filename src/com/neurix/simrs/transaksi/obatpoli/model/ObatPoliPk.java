package com.neurix.simrs.transaksi.obatpoli.model;

import java.io.Serializable;

/**
 * Created by Toshiba on 10/12/2019.
 */
public class ObatPoliPk implements Serializable {

    private String idBarang;
    private String idPelayanan;

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }
}
