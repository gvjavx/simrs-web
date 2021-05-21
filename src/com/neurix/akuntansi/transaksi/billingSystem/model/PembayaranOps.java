package com.neurix.akuntansi.transaksi.billingSystem.model;

import java.math.BigDecimal;

public class PembayaranOps {

    private String idPasien;
    private String noNota;
    private String withObat;
    private String metodeBayar;
    private String kodeBank;
    private String type;
    private String jenis;
    private String noRekening;
    private String noCheckup;
    private String jenisPasienFront;
    private BigDecimal lebihBiaya;

    public BigDecimal getLebihBiaya() {
        return lebihBiaya;
    }

    public void setLebihBiaya(BigDecimal lebihBiaya) {
        this.lebihBiaya = lebihBiaya;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public String getWithObat() {
        return withObat;
    }

    public void setWithObat(String withObat) {
        this.withObat = withObat;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getJenisPasienFront() {
        return jenisPasienFront;
    }

    public void setJenisPasienFront(String jenisPasienFront) {
        this.jenisPasienFront = jenisPasienFront;
    }
}
