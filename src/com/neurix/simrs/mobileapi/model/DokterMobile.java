package com.neurix.simrs.mobileapi.model;

/**
 * @author gondok
 * Thursday, 23/01/20 14:40
 */
public class DokterMobile {
    private String idDokter;
    private String namaDokter;


    private String idSpesialis;
    private String namaSpesialis;

    private String kuota;

    private String lat;
    private String lon;

    private String flagCall;
    private String flagTele;

    private String foto;

    private String jumlahAntrian;
    private String kuotaTele;

    private String idPelayanan;
    private String namaPelayanan;

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

    public String getKuotaTele() {
        return kuotaTele;
    }

    public void setKuotaTele(String kuotaTele) {
        this.kuotaTele = kuotaTele;
    }

    public String getJumlahAntrian() {
        return jumlahAntrian;
    }

    public void setJumlahAntrian(String jumlahAntrian) {
        this.jumlahAntrian = jumlahAntrian;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFlagTele() {
        return flagTele;
    }

    public void setFlagTele(String flagTele) {
        this.flagTele = flagTele;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
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

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }
}
