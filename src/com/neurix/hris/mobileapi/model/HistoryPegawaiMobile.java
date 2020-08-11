package com.neurix.hris.mobileapi.model;

/**
 * @author gondok
 * Tuesday, 11/08/20 13:33
 */
public class HistoryPegawaiMobile {

    private String jumlahHadir;
    private String jumlahDispen;
    private String sisaCuti;
    private String bulan;
    private String tahun;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJumlahHadir() {
        return jumlahHadir;
    }

    public void setJumlahHadir(String jumlahHadir) {
        this.jumlahHadir = jumlahHadir;
    }

    public String getJumlahDispen() {
        return jumlahDispen;
    }

    public void setJumlahDispen(String jumlahDispen) {
        this.jumlahDispen = jumlahDispen;
    }

    public String getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(String sisaCuti) {
        this.sisaCuti = sisaCuti;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
