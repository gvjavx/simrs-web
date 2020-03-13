package com.neurix.simrs.mobileapi.model;

import java.io.Serializable;

/**
 * @author gondok
 * Tuesday, 10/03/20 16:23
 */
public class ResepModel implements Serializable {
    private String id;
    private String idObat;
    private String namaObat;
    private String qty;
    private String keterangan;
    private String flagKronis;
    private String flagRacik;
    private String hariKronis;
    private String jenisSatuan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlagKronis() {
        return flagKronis;
    }

    public void setFlagKronis(String flagKronis) {
        this.flagKronis = flagKronis;
    }

    public String getFlagRacik() {
        return flagRacik;
    }

    public void setFlagRacik(String flagRacik) {
        this.flagRacik = flagRacik;
    }

    public String getHariKronis() {
        return hariKronis;
    }

    public void setHariKronis(String hariKronis) {
        this.hariKronis = hariKronis;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }
}
