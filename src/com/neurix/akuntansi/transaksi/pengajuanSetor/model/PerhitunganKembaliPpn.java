package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import java.math.BigDecimal;

public class PerhitunganKembaliPpn {
    private BigDecimal dppTotalPendapatanRumahSakit = BigDecimal.ZERO;
    private BigDecimal dppPenyerahanTidakTerutangPpn=BigDecimal.ZERO;
    private BigDecimal dppPenyerahanTerutangPpn = BigDecimal.ZERO;
    private BigDecimal dppPpnMasukanYangTelahDikreditkan = BigDecimal.ZERO;

    private BigDecimal ppnTotalPendapatanRumahSakit = BigDecimal.ZERO;
    private BigDecimal ppnPenyerahanTidakTerutangPpn=BigDecimal.ZERO;
    private BigDecimal ppnPenyerahanTerutangPpn = BigDecimal.ZERO;
    private BigDecimal ppnPpnMasukanYangTelahDikreditkan = BigDecimal.ZERO;

    private BigDecimal ppnPpnMasukanYangTidakDapatDikreditkan = BigDecimal.ZERO;
    private BigDecimal ppnTelahDiperhitungkanKembaliPpnMasukan = BigDecimal.ZERO;
    private BigDecimal ppnHasilPerhitunganKembaliPpn = BigDecimal.ZERO;

    private String stDppTotalPendapatanRumahSakit;
    private String stDppPenyerahanTidakTerutangPpn;
    private String stDppPenyerahanTerutangPpn;
    private String stDppPpnMasukanYangTelahDikreditkan;

    private String stPpnTotalPendapatanRumahSakit;
    private String stPpnPenyerahanTidakTerutangPpn;
    private String stPpnPenyerahanTerutangPpn;
    private String stPpnPpnMasukanYangTelahDikreditkan;

    private String stPpnPpnMasukanYangTidakDapatDikreditkan;
    private String stPpnTelahDiperhitungkanKembaliPpnMasukan;
    private String stPpnHasilPerhitunganKembaliPpn;

    private String bulan;
    private String tahun;
    private String prosesPpnKdId;

    public String getStDppTotalPendapatanRumahSakit() {
        return stDppTotalPendapatanRumahSakit;
    }

    public void setStDppTotalPendapatanRumahSakit(String stDppTotalPendapatanRumahSakit) {
        this.stDppTotalPendapatanRumahSakit = stDppTotalPendapatanRumahSakit;
    }

    public String getStDppPenyerahanTidakTerutangPpn() {
        return stDppPenyerahanTidakTerutangPpn;
    }

    public void setStDppPenyerahanTidakTerutangPpn(String stDppPenyerahanTidakTerutangPpn) {
        this.stDppPenyerahanTidakTerutangPpn = stDppPenyerahanTidakTerutangPpn;
    }

    public String getStDppPenyerahanTerutangPpn() {
        return stDppPenyerahanTerutangPpn;
    }

    public void setStDppPenyerahanTerutangPpn(String stDppPenyerahanTerutangPpn) {
        this.stDppPenyerahanTerutangPpn = stDppPenyerahanTerutangPpn;
    }

    public String getStDppPpnMasukanYangTelahDikreditkan() {
        return stDppPpnMasukanYangTelahDikreditkan;
    }

    public void setStDppPpnMasukanYangTelahDikreditkan(String stDppPpnMasukanYangTelahDikreditkan) {
        this.stDppPpnMasukanYangTelahDikreditkan = stDppPpnMasukanYangTelahDikreditkan;
    }

    public String getStPpnTotalPendapatanRumahSakit() {
        return stPpnTotalPendapatanRumahSakit;
    }

    public void setStPpnTotalPendapatanRumahSakit(String stPpnTotalPendapatanRumahSakit) {
        this.stPpnTotalPendapatanRumahSakit = stPpnTotalPendapatanRumahSakit;
    }

    public String getStPpnPenyerahanTidakTerutangPpn() {
        return stPpnPenyerahanTidakTerutangPpn;
    }

    public void setStPpnPenyerahanTidakTerutangPpn(String stPpnPenyerahanTidakTerutangPpn) {
        this.stPpnPenyerahanTidakTerutangPpn = stPpnPenyerahanTidakTerutangPpn;
    }

    public String getStPpnPenyerahanTerutangPpn() {
        return stPpnPenyerahanTerutangPpn;
    }

    public void setStPpnPenyerahanTerutangPpn(String stPpnPenyerahanTerutangPpn) {
        this.stPpnPenyerahanTerutangPpn = stPpnPenyerahanTerutangPpn;
    }

    public String getStPpnPpnMasukanYangTelahDikreditkan() {
        return stPpnPpnMasukanYangTelahDikreditkan;
    }

    public void setStPpnPpnMasukanYangTelahDikreditkan(String stPpnPpnMasukanYangTelahDikreditkan) {
        this.stPpnPpnMasukanYangTelahDikreditkan = stPpnPpnMasukanYangTelahDikreditkan;
    }

    public String getStPpnPpnMasukanYangTidakDapatDikreditkan() {
        return stPpnPpnMasukanYangTidakDapatDikreditkan;
    }

    public void setStPpnPpnMasukanYangTidakDapatDikreditkan(String stPpnPpnMasukanYangTidakDapatDikreditkan) {
        this.stPpnPpnMasukanYangTidakDapatDikreditkan = stPpnPpnMasukanYangTidakDapatDikreditkan;
    }

    public String getStPpnTelahDiperhitungkanKembaliPpnMasukan() {
        return stPpnTelahDiperhitungkanKembaliPpnMasukan;
    }

    public void setStPpnTelahDiperhitungkanKembaliPpnMasukan(String stPpnTelahDiperhitungkanKembaliPpnMasukan) {
        this.stPpnTelahDiperhitungkanKembaliPpnMasukan = stPpnTelahDiperhitungkanKembaliPpnMasukan;
    }

    public String getStPpnHasilPerhitunganKembaliPpn() {
        return stPpnHasilPerhitunganKembaliPpn;
    }

    public void setStPpnHasilPerhitunganKembaliPpn(String stPpnHasilPerhitunganKembaliPpn) {
        this.stPpnHasilPerhitunganKembaliPpn = stPpnHasilPerhitunganKembaliPpn;
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

    public String getProsesPpnKdId() {
        return prosesPpnKdId;
    }

    public void setProsesPpnKdId(String prosesPpnKdId) {
        this.prosesPpnKdId = prosesPpnKdId;
    }

    public BigDecimal getDppTotalPendapatanRumahSakit() {
        return dppTotalPendapatanRumahSakit;
    }

    public void setDppTotalPendapatanRumahSakit(BigDecimal dppTotalPendapatanRumahSakit) {
        this.dppTotalPendapatanRumahSakit = dppTotalPendapatanRumahSakit;
    }

    public BigDecimal getDppPenyerahanTidakTerutangPpn() {
        return dppPenyerahanTidakTerutangPpn;
    }

    public void setDppPenyerahanTidakTerutangPpn(BigDecimal dppPenyerahanTidakTerutangPpn) {
        this.dppPenyerahanTidakTerutangPpn = dppPenyerahanTidakTerutangPpn;
    }

    public BigDecimal getDppPenyerahanTerutangPpn() {
        return dppPenyerahanTerutangPpn;
    }

    public void setDppPenyerahanTerutangPpn(BigDecimal dppPenyerahanTerutangPpn) {
        this.dppPenyerahanTerutangPpn = dppPenyerahanTerutangPpn;
    }

    public BigDecimal getDppPpnMasukanYangTelahDikreditkan() {
        return dppPpnMasukanYangTelahDikreditkan;
    }

    public void setDppPpnMasukanYangTelahDikreditkan(BigDecimal dppPpnMasukanYangTelahDikreditkan) {
        this.dppPpnMasukanYangTelahDikreditkan = dppPpnMasukanYangTelahDikreditkan;
    }

    public BigDecimal getPpnTotalPendapatanRumahSakit() {
        return ppnTotalPendapatanRumahSakit;
    }

    public void setPpnTotalPendapatanRumahSakit(BigDecimal ppnTotalPendapatanRumahSakit) {
        this.ppnTotalPendapatanRumahSakit = ppnTotalPendapatanRumahSakit;
    }

    public BigDecimal getPpnPenyerahanTidakTerutangPpn() {
        return ppnPenyerahanTidakTerutangPpn;
    }

    public void setPpnPenyerahanTidakTerutangPpn(BigDecimal ppnPenyerahanTidakTerutangPpn) {
        this.ppnPenyerahanTidakTerutangPpn = ppnPenyerahanTidakTerutangPpn;
    }

    public BigDecimal getPpnPenyerahanTerutangPpn() {
        return ppnPenyerahanTerutangPpn;
    }

    public void setPpnPenyerahanTerutangPpn(BigDecimal ppnPenyerahanTerutangPpn) {
        this.ppnPenyerahanTerutangPpn = ppnPenyerahanTerutangPpn;
    }

    public BigDecimal getPpnPpnMasukanYangTelahDikreditkan() {
        return ppnPpnMasukanYangTelahDikreditkan;
    }

    public void setPpnPpnMasukanYangTelahDikreditkan(BigDecimal ppnPpnMasukanYangTelahDikreditkan) {
        this.ppnPpnMasukanYangTelahDikreditkan = ppnPpnMasukanYangTelahDikreditkan;
    }

    public BigDecimal getPpnPpnMasukanYangTidakDapatDikreditkan() {
        return ppnPpnMasukanYangTidakDapatDikreditkan;
    }

    public void setPpnPpnMasukanYangTidakDapatDikreditkan(BigDecimal ppnPpnMasukanYangTidakDapatDikreditkan) {
        this.ppnPpnMasukanYangTidakDapatDikreditkan = ppnPpnMasukanYangTidakDapatDikreditkan;
    }

    public BigDecimal getPpnTelahDiperhitungkanKembaliPpnMasukan() {
        return ppnTelahDiperhitungkanKembaliPpnMasukan;
    }

    public void setPpnTelahDiperhitungkanKembaliPpnMasukan(BigDecimal ppnTelahDiperhitungkanKembaliPpnMasukan) {
        this.ppnTelahDiperhitungkanKembaliPpnMasukan = ppnTelahDiperhitungkanKembaliPpnMasukan;
    }

    public BigDecimal getPpnHasilPerhitunganKembaliPpn() {
        return ppnHasilPerhitunganKembaliPpn;
    }

    public void setPpnHasilPerhitunganKembaliPpn(BigDecimal ppnHasilPerhitunganKembaliPpn) {
        this.ppnHasilPerhitunganKembaliPpn = ppnHasilPerhitunganKembaliPpn;
    }
}
