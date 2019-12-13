package com.neurix.simrs.mobileapi.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author gondok
 * Thursday, 05/12/19 15:55
 */
public class PelayananMobile implements Serializable {

    private String idPelayananApi;
    private String idDokter;
    private String namaDokter;
    private String idSpesialis;
    private String namaSpesialis;
    private String idPelayanan;
    private String namaPelayanan;
    private String jamAwal;
    private String jamAkhir;
    private String stTanggal;

    public String getIdPelayananApi() {
        return idPelayananApi;
    }

    public void setIdPelayananApi(String idPelayananApi) {
        this.idPelayananApi = idPelayananApi;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }

    public String getNamaSpesialis() {
        return namaSpesialis;
    }

    public void setNamaSpesialis(String namaSpesialis) {
        this.namaSpesialis = namaSpesialis;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }
}
