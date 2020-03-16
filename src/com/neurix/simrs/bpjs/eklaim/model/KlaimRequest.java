package com.neurix.simrs.bpjs.eklaim.model;

public class KlaimRequest {
    private String noKartu;
    private String noSep;
    private String noRm;
    private String namaPasien;
    private String tglLahir;
    private String gender;

    //delete
    private String coderNik;

    public String getCoderNik() {
        return coderNik;
    }

    public void setCoderNik(String coderNik) {
        this.coderNik = coderNik;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getNoRm() {
        return noRm;
    }

    public void setNoRm(String noRm) {
        this.noRm = noRm;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }
}
