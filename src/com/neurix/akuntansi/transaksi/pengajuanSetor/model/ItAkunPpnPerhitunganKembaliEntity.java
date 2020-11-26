package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ItAkunPpnPerhitunganKembaliEntity {
    private String perhitunganKembaliPpnId;
    private BigDecimal dppTotalPendapatanRumahSakit;
    private BigDecimal dppPenyerahanTidakTerutangPpn;
    private BigDecimal dppPpnMasukanYangTelahDireditkan;
    private BigDecimal ppnTotalPendapatanRumahSakit;
    private BigDecimal ppnPenyerahanTidakTerutangPpn;
    private BigDecimal ppnPenyerahanTerutangPpn;
    private BigDecimal ppnPpnMasukanYangTelahDikreditkan;
    private BigDecimal ppnPpnMasukanYangTidakDapatDikreditkan;
    private BigDecimal ppnTelahDiperhitungkanKembali;
    private BigDecimal ppnHasilPerhitunganKembaliPpn;
    private String bulan;
    private String tahun;
    private String prosesPpnKdId;

    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;
    private String flag;

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getPerhitunganKembaliPpnId() {
        return perhitunganKembaliPpnId;
    }

    public void setPerhitunganKembaliPpnId(String perhitunganKembaliPpnId) {
        this.perhitunganKembaliPpnId = perhitunganKembaliPpnId;
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

    public BigDecimal getDppPpnMasukanYangTelahDireditkan() {
        return dppPpnMasukanYangTelahDireditkan;
    }

    public void setDppPpnMasukanYangTelahDireditkan(BigDecimal dppPpnMasukanYangTelahDireditkan) {
        this.dppPpnMasukanYangTelahDireditkan = dppPpnMasukanYangTelahDireditkan;
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

    public BigDecimal getPpnTelahDiperhitungkanKembali() {
        return ppnTelahDiperhitungkanKembali;
    }

    public void setPpnTelahDiperhitungkanKembali(BigDecimal ppnTelahDiperhitungkanKembali) {
        this.ppnTelahDiperhitungkanKembali = ppnTelahDiperhitungkanKembali;
    }

    public BigDecimal getPpnHasilPerhitunganKembaliPpn() {
        return ppnHasilPerhitunganKembaliPpn;
    }

    public void setPpnHasilPerhitunganKembaliPpn(BigDecimal ppnHasilPerhitunganKembaliPpn) {
        this.ppnHasilPerhitunganKembaliPpn = ppnHasilPerhitunganKembaliPpn;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunPpnPerhitunganKembaliEntity that = (ItAkunPpnPerhitunganKembaliEntity) o;
        return Objects.equals(perhitunganKembaliPpnId, that.perhitunganKembaliPpnId) &&
                Objects.equals(dppTotalPendapatanRumahSakit, that.dppTotalPendapatanRumahSakit) &&
                Objects.equals(dppPenyerahanTidakTerutangPpn, that.dppPenyerahanTidakTerutangPpn) &&
                Objects.equals(dppPpnMasukanYangTelahDireditkan, that.dppPpnMasukanYangTelahDireditkan) &&
                Objects.equals(ppnTotalPendapatanRumahSakit, that.ppnTotalPendapatanRumahSakit) &&
                Objects.equals(ppnPenyerahanTidakTerutangPpn, that.ppnPenyerahanTidakTerutangPpn) &&
                Objects.equals(ppnPenyerahanTerutangPpn, that.ppnPenyerahanTerutangPpn) &&
                Objects.equals(ppnPpnMasukanYangTelahDikreditkan, that.ppnPpnMasukanYangTelahDikreditkan) &&
                Objects.equals(ppnPpnMasukanYangTidakDapatDikreditkan, that.ppnPpnMasukanYangTidakDapatDikreditkan) &&
                Objects.equals(ppnTelahDiperhitungkanKembali, that.ppnTelahDiperhitungkanKembali) &&
                Objects.equals(ppnHasilPerhitunganKembaliPpn, that.ppnHasilPerhitunganKembaliPpn) &&
                Objects.equals(bulan, that.bulan) &&
                Objects.equals(tahun, that.tahun) &&
                Objects.equals(prosesPpnKdId, that.prosesPpnKdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perhitunganKembaliPpnId, dppTotalPendapatanRumahSakit, dppPenyerahanTidakTerutangPpn, dppPpnMasukanYangTelahDireditkan, ppnTotalPendapatanRumahSakit, ppnPenyerahanTidakTerutangPpn, ppnPenyerahanTerutangPpn, ppnPpnMasukanYangTelahDikreditkan, ppnPpnMasukanYangTidakDapatDikreditkan, ppnTelahDiperhitungkanKembali, ppnHasilPerhitunganKembaliPpn, bulan, tahun, prosesPpnKdId);
    }
}
