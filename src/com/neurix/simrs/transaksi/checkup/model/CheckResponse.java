package com.neurix.simrs.transaksi.checkup.model;

import java.math.BigDecimal;

public class CheckResponse {

    private String status;
    private String message;
    private String noSep;
    private BigDecimal jumlahCover;
    private String kodeCbg;
    private String idPelayananBpjs;

    public String getIdPelayananBpjs() {
        return idPelayananBpjs;
    }

    public void setIdPelayananBpjs(String idPelayananBpjs) {
        this.idPelayananBpjs = idPelayananBpjs;
    }

    public String getKodeCbg() {
        return kodeCbg;
    }

    public void setKodeCbg(String kodeCbg) {
        this.kodeCbg = kodeCbg;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public BigDecimal getJumlahCover() {
        return jumlahCover;
    }

    public void setJumlahCover(BigDecimal jumlahCover) {
        this.jumlahCover = jumlahCover;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}