package com.neurix.simrs.bpjs.model;

/**
 * Created by Toshiba on 24/10/2019.
 */
public class ReferensiResponse {
    private String kode;
    private String nama;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "ReferensiResponse{" +
                "kode='" + kode + '\'' +
                ", nama='" + nama + '\'' +
                '}';
    }
}
