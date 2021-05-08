package com.neurix.simrs.transaksi.pengkajian.model;

import com.neurix.simrs.master.dokter.model.Dokter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 21/02/20.
 */
public class RingkasanKeluarMasukRs {
    private String idDetailCheckup;
    private String masukMelalui;
    private String rujukan;
    private String ketRujukan;
    private String tglMasukRs;
    private String tglPindahRuang;
    private String ruang;
    private String tglKeluarRs;
    private String keadaanKeluar;
    private String caraKeluar;
    private String ketCheckupUlang;
    private String tujuanPulang;

    List<Dokter> dokterList = new ArrayList<>();

    public String getTujuanPulang() {
        return tujuanPulang;
    }

    public void setTujuanPulang(String tujuanPulang) {
        this.tujuanPulang = tujuanPulang;
    }

    public String getKetCheckupUlang() {
        return ketCheckupUlang;
    }

    public void setKetCheckupUlang(String ketCheckupUlang) {
        this.ketCheckupUlang = ketCheckupUlang;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getMasukMelalui() {
        return masukMelalui;
    }

    public void setMasukMelalui(String masukMelalui) {
        this.masukMelalui = masukMelalui;
    }

    public String getRujukan() {
        return rujukan;
    }

    public void setRujukan(String rujukan) {
        this.rujukan = rujukan;
    }

    public String getKetRujukan() {
        return ketRujukan;
    }

    public void setKetRujukan(String ketRujukan) {
        this.ketRujukan = ketRujukan;
    }

    public String getTglMasukRs() {
        return tglMasukRs;
    }

    public void setTglMasukRs(String tglMasukRs) {
        this.tglMasukRs = tglMasukRs;
    }

    public String getTglPindahRuang() {
        return tglPindahRuang;
    }

    public void setTglPindahRuang(String tglPindahRuang) {
        this.tglPindahRuang = tglPindahRuang;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getTglKeluarRs() {
        return tglKeluarRs;
    }

    public void setTglKeluarRs(String tglKeluarRs) {
        this.tglKeluarRs = tglKeluarRs;
    }

    public String getKeadaanKeluar() {
        return keadaanKeluar;
    }

    public void setKeadaanKeluar(String keadaanKeluar) {
        this.keadaanKeluar = keadaanKeluar;
    }

    public String getCaraKeluar() {
        return caraKeluar;
    }

    public void setCaraKeluar(String caraKeluar) {
        this.caraKeluar = caraKeluar;
    }

    public List<Dokter> getDokterList() {
        return dokterList;
    }

    public void setDokterList(List<Dokter> dokterList) {
        this.dokterList = dokterList;
    }
}
