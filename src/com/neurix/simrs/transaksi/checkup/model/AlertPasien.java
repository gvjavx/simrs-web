package com.neurix.simrs.transaksi.checkup.model;

import java.util.List;

/**
 * Created by Toshiba on 23/12/2019.
 */
public class AlertPasien
{
    private String noCheckup;
    private String namaPasien;
    private String stTglKeluar;
    private String diagnosa;
    private String stTglMasuk;
    private String idDiagnosa;
    private List<String> listOfAlergi;

    public String getIdDiagnosa() {
        return idDiagnosa;
    }

    public void setIdDiagnosa(String idDiagnosa) {
        this.idDiagnosa = idDiagnosa;
    }

    public String getStTglMasuk() {
        return stTglMasuk;
    }

    public void setStTglMasuk(String stTglMasuk) {
        this.stTglMasuk = stTglMasuk;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getStTglKeluar() {
        return stTglKeluar;
    }

    public void setStTglKeluar(String stTglKeluar) {
        this.stTglKeluar = stTglKeluar;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public List<String> getListOfAlergi() {
        return listOfAlergi;
    }

    public void setListOfAlergi(List<String> listOfAlergi) {
        this.listOfAlergi = listOfAlergi;
    }
}
