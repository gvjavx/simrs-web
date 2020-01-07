package com.neurix.simrs.bpjs.model;

public class RujukanRequest {
    private String noSep;
    private String tglRujukan;
    private String ppkDirujuk;
    private String jnsPelayanan;
    private String catatan;
    private String diagRujukan;
    private String tipeRujukan;
    private String poliRujukan;
    private String userPembuatRujukan;

    //update
    private String noRujukan;

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getTglRujukan() {
        return tglRujukan;
    }

    public void setTglRujukan(String tglRujukan) {
        this.tglRujukan = tglRujukan;
    }

    public String getPpkDirujuk() {
        return ppkDirujuk;
    }

    public void setPpkDirujuk(String ppkDirujuk) {
        this.ppkDirujuk = ppkDirujuk;
    }

    public String getJnsPelayanan() {
        return jnsPelayanan;
    }

    public void setJnsPelayanan(String jnsPelayanan) {
        this.jnsPelayanan = jnsPelayanan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getDiagRujukan() {
        return diagRujukan;
    }

    public void setDiagRujukan(String diagRujukan) {
        this.diagRujukan = diagRujukan;
    }

    public String getTipeRujukan() {
        return tipeRujukan;
    }

    public void setTipeRujukan(String tipeRujukan) {
        this.tipeRujukan = tipeRujukan;
    }

    public String getPoliRujukan() {
        return poliRujukan;
    }

    public void setPoliRujukan(String poliRujukan) {
        this.poliRujukan = poliRujukan;
    }

    public String getUserPembuatRujukan() {
        return userPembuatRujukan;
    }

    public void setUserPembuatRujukan(String userPembuatRujukan) {
        this.userPembuatRujukan = userPembuatRujukan;
    }
}
