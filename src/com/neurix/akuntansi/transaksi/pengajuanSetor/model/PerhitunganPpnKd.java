package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PerhitunganPpnKd extends BaseModel {
    private String perhitunganPpnKdId;
    private String bulan;
    private String tahun;
    private String bulan1;
    private String tahun1;
    private String cancelFlag;
    private String cancelWho;
    private Timestamp cancelDate;
    private String approvalFlag;
    private String approvalWho;
    private Timestamp approvalDate;
    private String namaBulan;

    private BigDecimal ppnMasukan = BigDecimal.ZERO;
    private BigDecimal totalPpnMasukan = BigDecimal.ZERO;
    private BigDecimal ppnKeluaran= BigDecimal.ZERO;
    private BigDecimal kurangBayar= BigDecimal.ZERO;
    private BigDecimal perhitunganKembali= BigDecimal.ZERO;
    private BigDecimal totalKurangBayar= BigDecimal.ZERO;
    private BigDecimal lbBulanYll = BigDecimal.ZERO;

    //terutang ppn A
    private BigDecimal ppnEkspor= BigDecimal.ZERO;
    private BigDecimal dipungutSendiri= BigDecimal.ZERO;
    private BigDecimal dipungutOlehPemungut= BigDecimal.ZERO;
    private BigDecimal tidakDipungut= BigDecimal.ZERO;
    private BigDecimal dibebaskan= BigDecimal.ZERO;
    private BigDecimal jumlahTerutangPpn= BigDecimal.ZERO;

    //tidak terutang B
    private BigDecimal jasaRs= BigDecimal.ZERO;
    private BigDecimal obatRawatInap= BigDecimal.ZERO;
    private BigDecimal jumlahTidakTerutang= BigDecimal.ZERO;

    //total A + B
    private BigDecimal penyerahanBarangDanJasa= BigDecimal.ZERO;

    //string
    private String stPpnMasukan;
    private String stTotalPpnMasukan;
    private String stPpnKeluaran;
    private String stKurangBayar;
    private String stPerhitunganKembali;
    private String stTotalKurangBayar;
    private String stPpnEkspor;
    private String stDipungutSendiri;
    private String stDipungutOlehPemungut;
    private String stTidakDipungut;
    private String stDibebaskan;
    private String stJumlahTerutangPpn;
    private String stJasaRs;
    private String stObatRawatInap;
    private String stJumlahTidakTerutang;
    private String stPenyerahanBarangDanJasa;
    private String stLbBulanYll;
    private String tipe;
    private String statusB2;

    private String branchId;
    private BigDecimal piutangPpnKeluaran;
    private String buktiPiutangPpnKeluaran;

    public String getBuktiPiutangPpnKeluaran() {
        return buktiPiutangPpnKeluaran;
    }

    public void setBuktiPiutangPpnKeluaran(String buktiPiutangPpnKeluaran) {
        this.buktiPiutangPpnKeluaran = buktiPiutangPpnKeluaran;
    }

    public BigDecimal getPiutangPpnKeluaran() {
        return piutangPpnKeluaran;
    }

    public void setPiutangPpnKeluaran(BigDecimal piutangPpnKeluaran) {
        this.piutangPpnKeluaran = piutangPpnKeluaran;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStatusB2() {
        return statusB2;
    }

    public void setStatusB2(String statusB2) {
        this.statusB2 = statusB2;
    }

    public String getNamaBulan() {
        return namaBulan;
    }

    public void setNamaBulan(String namaBulan) {
        this.namaBulan = namaBulan;
    }

    public String getPerhitunganPpnKdId() {
        return perhitunganPpnKdId;
    }

    public void setPerhitunganPpnKdId(String perhitunganPpnKdId) {
        this.perhitunganPpnKdId = perhitunganPpnKdId;
    }

    public String getTipe() {
        return tipe;
    }

    public String getBulan1() {
        return bulan1;
    }

    public void setBulan1(String bulan1) {
        this.bulan1 = bulan1;
    }

    public String getTahun1() {
        return tahun1;
    }

    public void setTahun1(String tahun1) {
        this.tahun1 = tahun1;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getCancelWho() {
        return cancelWho;
    }

    public void setCancelWho(String cancelWho) {
        this.cancelWho = cancelWho;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovalWho() {
        return approvalWho;
    }

    public void setApprovalWho(String approvalWho) {
        this.approvalWho = approvalWho;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public BigDecimal getTotalPpnMasukan() {
        return totalPpnMasukan;
    }

    public void setTotalPpnMasukan(BigDecimal totalPpnMasukan) {
        this.totalPpnMasukan = totalPpnMasukan;
    }

    public String getStTotalPpnMasukan() {
        return stTotalPpnMasukan;
    }

    public void setStTotalPpnMasukan(String stTotalPpnMasukan) {
        this.stTotalPpnMasukan = stTotalPpnMasukan;
    }

    public BigDecimal getLbBulanYll() {
        return lbBulanYll;
    }

    public void setLbBulanYll(BigDecimal lbBulanYll) {
        this.lbBulanYll = lbBulanYll;
    }

    public String getStLbBulanYll() {
        return stLbBulanYll;
    }

    public void setStLbBulanYll(String stLbBulanYll) {
        this.stLbBulanYll = stLbBulanYll;
    }

    public String getStPpnMasukan() {
        return stPpnMasukan;
    }

    public void setStPpnMasukan(String stPpnMasukan) {
        this.stPpnMasukan = stPpnMasukan;
    }

    public String getStPpnKeluaran() {
        return stPpnKeluaran;
    }

    public void setStPpnKeluaran(String stPpnKeluaran) {
        this.stPpnKeluaran = stPpnKeluaran;
    }

    public String getStKurangBayar() {
        return stKurangBayar;
    }

    public void setStKurangBayar(String stKurangBayar) {
        this.stKurangBayar = stKurangBayar;
    }

    public String getStPerhitunganKembali() {
        return stPerhitunganKembali;
    }

    public void setStPerhitunganKembali(String stPerhitunganKembali) {
        this.stPerhitunganKembali = stPerhitunganKembali;
    }

    public String getStTotalKurangBayar() {
        return stTotalKurangBayar;
    }

    public void setStTotalKurangBayar(String stTotalKurangBayar) {
        this.stTotalKurangBayar = stTotalKurangBayar;
    }

    public String getStPpnEkspor() {
        return stPpnEkspor;
    }

    public void setStPpnEkspor(String stPpnEkspor) {
        this.stPpnEkspor = stPpnEkspor;
    }

    public String getStDipungutSendiri() {
        return stDipungutSendiri;
    }

    public void setStDipungutSendiri(String stDipungutSendiri) {
        this.stDipungutSendiri = stDipungutSendiri;
    }

    public String getStDipungutOlehPemungut() {
        return stDipungutOlehPemungut;
    }

    public void setStDipungutOlehPemungut(String stDipungutOlehPemungut) {
        this.stDipungutOlehPemungut = stDipungutOlehPemungut;
    }

    public String getStTidakDipungut() {
        return stTidakDipungut;
    }

    public void setStTidakDipungut(String stTidakDipungut) {
        this.stTidakDipungut = stTidakDipungut;
    }

    public String getStDibebaskan() {
        return stDibebaskan;
    }

    public void setStDibebaskan(String stDibebaskan) {
        this.stDibebaskan = stDibebaskan;
    }

    public String getStJumlahTerutangPpn() {
        return stJumlahTerutangPpn;
    }

    public void setStJumlahTerutangPpn(String stJumlahTerutangPpn) {
        this.stJumlahTerutangPpn = stJumlahTerutangPpn;
    }

    public String getStJasaRs() {
        return stJasaRs;
    }

    public void setStJasaRs(String stJasaRs) {
        this.stJasaRs = stJasaRs;
    }

    public String getStObatRawatInap() {
        return stObatRawatInap;
    }

    public void setStObatRawatInap(String stObatRawatInap) {
        this.stObatRawatInap = stObatRawatInap;
    }

    public String getStJumlahTidakTerutang() {
        return stJumlahTidakTerutang;
    }

    public void setStJumlahTidakTerutang(String stJumlahTidakTerutang) {
        this.stJumlahTidakTerutang = stJumlahTidakTerutang;
    }

    public String getStPenyerahanBarangDanJasa() {
        return stPenyerahanBarangDanJasa;
    }

    public void setStPenyerahanBarangDanJasa(String stPenyerahanBarangDanJasa) {
        this.stPenyerahanBarangDanJasa = stPenyerahanBarangDanJasa;
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

    public BigDecimal getPpnMasukan() {
        return ppnMasukan;
    }

    public void setPpnMasukan(BigDecimal ppnMasukan) {
        this.ppnMasukan = ppnMasukan;
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
}
