package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ItAkunPerhitunganPpnKdEntity implements Serializable {
    private String perhitunganPpnKdId;
    private String tahun;
    private String bulan;
    private String approvalFlag;
    private String approvalId;
    private Timestamp approvalDate;
    private String cancelFlag;
    private String cancelId;
    private Timestamp cancelDate;
    private BigDecimal ppnMasukan;
    private BigDecimal totalPpnMasukan;
    private BigDecimal ppnKeluaran;
    private BigDecimal kurangBayar;
    private BigDecimal perhitunganKembali;
    private BigDecimal totalKurangBayar;
    private BigDecimal lbBulanYll;
    private BigDecimal ppnEkspor;
    private BigDecimal dipungutSendiri;
    private BigDecimal dipungutOlehPemungut;
    private BigDecimal tidakDipungut;
    private BigDecimal dibebaskan;
    private BigDecimal jumlahTerutangPpn;
    private BigDecimal jasaRs;
    private BigDecimal obatRawatInap;
    private BigDecimal jumlahTidakTerutang;
    private BigDecimal penyerahanBarangDanJasa;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;
    private String flag;
    private String tipe;
    private String statusB2;


    public String getStatusB2() {
        return statusB2;
    }

    public void setStatusB2(String statusB2) {
        this.statusB2 = statusB2;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getPerhitunganPpnKdId() {
        return perhitunganPpnKdId;
    }

    public void setPerhitunganPpnKdId(String perhitunganPpnKdId) {
        this.perhitunganPpnKdId = perhitunganPpnKdId;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public BigDecimal getPpnMasukan() {
        return ppnMasukan;
    }

    public void setPpnMasukan(BigDecimal ppnMasukan) {
        this.ppnMasukan = ppnMasukan;
    }

    public BigDecimal getTotalPpnMasukan() {
        return totalPpnMasukan;
    }

    public void setTotalPpnMasukan(BigDecimal totalPpnMasukan) {
        this.totalPpnMasukan = totalPpnMasukan;
    }

    public BigDecimal getPpnKeluaran() {
        return ppnKeluaran;
    }

    public void setPpnKeluaran(BigDecimal ppnKeluaran) {
        this.ppnKeluaran = ppnKeluaran;
    }

    public BigDecimal getKurangBayar() {
        return kurangBayar;
    }

    public void setKurangBayar(BigDecimal kurangBayar) {
        this.kurangBayar = kurangBayar;
    }

    public BigDecimal getPerhitunganKembali() {
        return perhitunganKembali;
    }

    public void setPerhitunganKembali(BigDecimal perhitunganKembali) {
        this.perhitunganKembali = perhitunganKembali;
    }

    public BigDecimal getTotalKurangBayar() {
        return totalKurangBayar;
    }

    public void setTotalKurangBayar(BigDecimal totalKurangBayar) {
        this.totalKurangBayar = totalKurangBayar;
    }

    public BigDecimal getLbBulanYll() {
        return lbBulanYll;
    }

    public void setLbBulanYll(BigDecimal lbBulanYll) {
        this.lbBulanYll = lbBulanYll;
    }

    public BigDecimal getPpnEkspor() {
        return ppnEkspor;
    }

    public void setPpnEkspor(BigDecimal ppnEkspor) {
        this.ppnEkspor = ppnEkspor;
    }

    public BigDecimal getDipungutSendiri() {
        return dipungutSendiri;
    }

    public void setDipungutSendiri(BigDecimal dipungutSendiri) {
        this.dipungutSendiri = dipungutSendiri;
    }

    public BigDecimal getDipungutOlehPemungut() {
        return dipungutOlehPemungut;
    }

    public void setDipungutOlehPemungut(BigDecimal dipungutOlehPemungut) {
        this.dipungutOlehPemungut = dipungutOlehPemungut;
    }

    public BigDecimal getTidakDipungut() {
        return tidakDipungut;
    }

    public void setTidakDipungut(BigDecimal tidakDipungut) {
        this.tidakDipungut = tidakDipungut;
    }

    public BigDecimal getDibebaskan() {
        return dibebaskan;
    }

    public void setDibebaskan(BigDecimal dibebaskan) {
        this.dibebaskan = dibebaskan;
    }

    public BigDecimal getJumlahTerutangPpn() {
        return jumlahTerutangPpn;
    }

    public void setJumlahTerutangPpn(BigDecimal jumlahTerutangPpn) {
        this.jumlahTerutangPpn = jumlahTerutangPpn;
    }

    public BigDecimal getJasaRs() {
        return jasaRs;
    }

    public void setJasaRs(BigDecimal jasaRs) {
        this.jasaRs = jasaRs;
    }

    public BigDecimal getObatRawatInap() {
        return obatRawatInap;
    }

    public void setObatRawatInap(BigDecimal obatRawatInap) {
        this.obatRawatInap = obatRawatInap;
    }

    public BigDecimal getJumlahTidakTerutang() {
        return jumlahTidakTerutang;
    }

    public void setJumlahTidakTerutang(BigDecimal jumlahTidakTerutang) {
        this.jumlahTidakTerutang = jumlahTidakTerutang;
    }

    public BigDecimal getPenyerahanBarangDanJasa() {
        return penyerahanBarangDanJasa;
    }

    public void setPenyerahanBarangDanJasa(BigDecimal penyerahanBarangDanJasa) {
        this.penyerahanBarangDanJasa = penyerahanBarangDanJasa;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunPerhitunganPpnKdEntity that = (ItAkunPerhitunganPpnKdEntity) o;
        return Objects.equals(perhitunganPpnKdId, that.perhitunganPpnKdId) &&
                Objects.equals(tahun, that.tahun) &&
                Objects.equals(bulan, that.bulan) &&
                Objects.equals(approvalFlag, that.approvalFlag) &&
                Objects.equals(approvalId, that.approvalId) &&
                Objects.equals(approvalDate, that.approvalDate) &&
                Objects.equals(cancelFlag, that.cancelFlag) &&
                Objects.equals(cancelId, that.cancelId) &&
                Objects.equals(cancelDate, that.cancelDate) &&
                Objects.equals(ppnMasukan, that.ppnMasukan) &&
                Objects.equals(totalPpnMasukan, that.totalPpnMasukan) &&
                Objects.equals(ppnKeluaran, that.ppnKeluaran) &&
                Objects.equals(kurangBayar, that.kurangBayar) &&
                Objects.equals(perhitunganKembali, that.perhitunganKembali) &&
                Objects.equals(totalKurangBayar, that.totalKurangBayar) &&
                Objects.equals(lbBulanYll, that.lbBulanYll) &&
                Objects.equals(ppnEkspor, that.ppnEkspor) &&
                Objects.equals(dipungutSendiri, that.dipungutSendiri) &&
                Objects.equals(dipungutOlehPemungut, that.dipungutOlehPemungut) &&
                Objects.equals(tidakDipungut, that.tidakDipungut) &&
                Objects.equals(dibebaskan, that.dibebaskan) &&
                Objects.equals(jumlahTerutangPpn, that.jumlahTerutangPpn) &&
                Objects.equals(jasaRs, that.jasaRs) &&
                Objects.equals(obatRawatInap, that.obatRawatInap) &&
                Objects.equals(jumlahTidakTerutang, that.jumlahTidakTerutang) &&
                Objects.equals(penyerahanBarangDanJasa, that.penyerahanBarangDanJasa) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perhitunganPpnKdId, tahun, bulan, approvalFlag, approvalId, approvalDate, cancelFlag, cancelId, cancelDate, ppnMasukan, totalPpnMasukan, ppnKeluaran, kurangBayar, perhitunganKembali, totalKurangBayar, lbBulanYll, ppnEkspor, dipungutSendiri, dipungutOlehPemungut, tidakDipungut, dibebaskan, jumlahTerutangPpn, jasaRs, obatRawatInap, jumlahTidakTerutang, penyerahanBarangDanJasa, createdDate, createdWho, lastUpdate, lastUpdateWho, action, flag);
    }
}
