package com.neurix.simrs.transaksi.checkup.model;

import java.util.List;

/**
 * Created by Toshiba on 23/12/2019.
 */
public class AlertPasien
{
    private String noCheckup;
    private String namaPasien;
    private String stTgl;
    private String diagnosa;
    private List<String> listOfAlergi;

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

    public String getStTgl() {
        return stTgl;
    }

    public void setStTgl(String stTgl) {
        this.stTgl = stTgl;
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
