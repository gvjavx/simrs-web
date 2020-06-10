package com.neurix.simrs.mobileapi.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author gondok
 * Wednesday, 10/06/20 11:26
 */
public class TelemedicineMobile {
    private String noAntrian;
    private String id;
    private String idPasien;
    private String idDokter;
    private String idPelayanan;
    private String flagResep;
    private String status;
    private String  biayaKonsultasi;
    private String flagBayarKonsultasi;
    private String flagBayarResep;
    private String flag;
    private String action;
    private String column16;
    private String noKartu;
    private String idJenisPeriksaPasien;
    private String idAsuransi;

    private String message;

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlagResep() {
        return flagResep;
    }

    public void setFlagResep(String flagResep) {
        this.flagResep = flagResep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBiayaKonsultasi() {
        return biayaKonsultasi;
    }

    public void setBiayaKonsultasi(String biayaKonsultasi) {
        this.biayaKonsultasi = biayaKonsultasi;
    }

    public String getFlagBayarKonsultasi() {
        return flagBayarKonsultasi;
    }

    public void setFlagBayarKonsultasi(String flagBayarKonsultasi) {
        this.flagBayarKonsultasi = flagBayarKonsultasi;
    }

    public String getFlagBayarResep() {
        return flagBayarResep;
    }

    public void setFlagBayarResep(String flagBayarResep) {
        this.flagBayarResep = flagBayarResep;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getColumn16() {
        return column16;
    }

    public void setColumn16(String column16) {
        this.column16 = column16;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }
}
