package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollCuti extends BaseModel {

    private String payrollCutiId;
    private String nip;
    private String payrollId;
    private String bulan;
    private String tahun;

    private String totalCuti;
    private String pphCuti;
    private String totalBersihCuti;
    private String totalThp;
    private String gaji;
    private String sankhus;
    private String tunjJabatan;
    private String tunjStruktural;
    private String tunjFungsional;
    private String tunjPeralihan;
    private String tunjRumah;
    private String tunjListrik;
    private String tunjAir;
    private String tunjBbm;
    private String tunjTambahan;
    private String tunjPph;

    private BigDecimal totalCutiNilai;
    private BigDecimal pphCutiNilai;
    private BigDecimal totalBersihCutiNilai;
    private BigDecimal totalThpNilai;
    private BigDecimal gajiNilai;
    private BigDecimal sankhusNilai;
    private BigDecimal tunjJabatanNilai;
    private BigDecimal tunjStrukturalNilai;
    private BigDecimal tunjFungsionalNilai;
    private BigDecimal tunjPeralihanNilai;
    private BigDecimal tunjRumahNilai;
    private BigDecimal tunjListrikNilai;
    private BigDecimal tunjAirNilai;
    private BigDecimal tunjBbmNilai;
    private BigDecimal tunjPphNilai;
    private String keterangan;
    private String tahunCutiPanjang;

    public String getTunjTambahan() {
        return tunjTambahan;
    }

    public void setTunjTambahan(String tunjTambahan) {
        this.tunjTambahan = tunjTambahan;
    }

    public String getTunjPph() {
        return tunjPph;
    }

    public void setTunjPph(String tunjPph) {
        this.tunjPph = tunjPph;
    }

    public BigDecimal getTunjPphNilai() {
        return tunjPphNilai;
    }

    public void setTunjPphNilai(BigDecimal tunjPphNilai) {
        this.tunjPphNilai = tunjPphNilai;
    }

    public String getTahunCutiPanjang() {
        return tahunCutiPanjang;
    }

    public void setTahunCutiPanjang(String tahunCutiPanjang) {
        this.tahunCutiPanjang = tahunCutiPanjang;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getGajiNilai() {
        return gajiNilai;
    }

    public void setGajiNilai(BigDecimal gajiNilai) {
        this.gajiNilai = gajiNilai;
    }

    public String getSankhus() {
        return sankhus;
    }

    public void setSankhus(String sankhus) {
        this.sankhus = sankhus;
    }

    public BigDecimal getSankhusNilai() {
        return sankhusNilai;
    }

    public void setSankhusNilai(BigDecimal sankhusNilai) {
        this.sankhusNilai = sankhusNilai;
    }

    public String getTunjAir() {
        return tunjAir;
    }

    public void setTunjAir(String tunjAir) {
        this.tunjAir = tunjAir;
    }

    public BigDecimal getTunjAirNilai() {
        return tunjAirNilai;
    }

    public void setTunjAirNilai(BigDecimal tunjAirNilai) {
        this.tunjAirNilai = tunjAirNilai;
    }

    public String getTunjBbm() {
        return tunjBbm;
    }

    public void setTunjBbm(String tunjBbm) {
        this.tunjBbm = tunjBbm;
    }

    public BigDecimal getTunjBbmNilai() {
        return tunjBbmNilai;
    }

    public void setTunjBbmNilai(BigDecimal tunjBbmNilai) {
        this.tunjBbmNilai = tunjBbmNilai;
    }

    public String getTunjFungsional() {
        return tunjFungsional;
    }

    public void setTunjFungsional(String tunjFungsional) {
        this.tunjFungsional = tunjFungsional;
    }

    public BigDecimal getTunjFungsionalNilai() {
        return tunjFungsionalNilai;
    }

    public void setTunjFungsionalNilai(BigDecimal tunjFungsionalNilai) {
        this.tunjFungsionalNilai = tunjFungsionalNilai;
    }

    public String getTunjJabatan() {
        return tunjJabatan;
    }

    public void setTunjJabatan(String tunjJabatan) {
        this.tunjJabatan = tunjJabatan;
    }

    public BigDecimal getTunjJabatanNilai() {
        return tunjJabatanNilai;
    }

    public void setTunjJabatanNilai(BigDecimal tunjJabatanNilai) {
        this.tunjJabatanNilai = tunjJabatanNilai;
    }

    public String getTunjListrik() {
        return tunjListrik;
    }

    public void setTunjListrik(String tunjListrik) {
        this.tunjListrik = tunjListrik;
    }

    public BigDecimal getTunjListrikNilai() {
        return tunjListrikNilai;
    }

    public void setTunjListrikNilai(BigDecimal tunjListrikNilai) {
        this.tunjListrikNilai = tunjListrikNilai;
    }

    public String getTunjPeralihan() {
        return tunjPeralihan;
    }

    public void setTunjPeralihan(String tunjPeralihan) {
        this.tunjPeralihan = tunjPeralihan;
    }

    public BigDecimal getTunjPeralihanNilai() {
        return tunjPeralihanNilai;
    }

    public void setTunjPeralihanNilai(BigDecimal tunjPeralihanNilai) {
        this.tunjPeralihanNilai = tunjPeralihanNilai;
    }

    public String getTunjRumah() {
        return tunjRumah;
    }

    public void setTunjRumah(String tunjRumah) {
        this.tunjRumah = tunjRumah;
    }

    public BigDecimal getTunjRumahNilai() {
        return tunjRumahNilai;
    }

    public void setTunjRumahNilai(BigDecimal tunjRumahNilai) {
        this.tunjRumahNilai = tunjRumahNilai;
    }

    public String getTunjStruktural() {
        return tunjStruktural;
    }

    public void setTunjStruktural(String tunjStruktural) {
        this.tunjStruktural = tunjStruktural;
    }

    public BigDecimal getTunjStrukturalNilai() {
        return tunjStrukturalNilai;
    }

    public void setTunjStrukturalNilai(BigDecimal tunjStrukturalNilai) {
        this.tunjStrukturalNilai = tunjStrukturalNilai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPayrollCutiId() {
        return payrollCutiId;
    }

    public void setPayrollCutiId(String payrollCutiId) {
        this.payrollCutiId = payrollCutiId;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getPphCuti() {
        return pphCuti;
    }

    public void setPphCuti(String pphCuti) {
        this.pphCuti = pphCuti;
    }

    public BigDecimal getPphCutiNilai() {
        return pphCutiNilai;
    }

    public void setPphCutiNilai(BigDecimal pphCutiNilai) {
        this.pphCutiNilai = pphCutiNilai;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getTotalBersihCuti() {
        return totalBersihCuti;
    }

    public void setTotalBersihCuti(String totalBersihCuti) {
        this.totalBersihCuti = totalBersihCuti;
    }

    public BigDecimal getTotalBersihCutiNilai() {
        return totalBersihCutiNilai;
    }

    public void setTotalBersihCutiNilai(BigDecimal totalBersihCutiNilai) {
        this.totalBersihCutiNilai = totalBersihCutiNilai;
    }

    public String getTotalCuti() {
        return totalCuti;
    }

    public void setTotalCuti(String totalCuti) {
        this.totalCuti = totalCuti;
    }

    public BigDecimal getTotalCutiNilai() {
        return totalCutiNilai;
    }

    public void setTotalCutiNilai(BigDecimal totalCutiNilai) {
        this.totalCutiNilai = totalCutiNilai;
    }

    public String getTotalThp() {
        return totalThp;
    }

    public void setTotalThp(String totalThp) {
        this.totalThp = totalThp;
    }

    public BigDecimal getTotalThpNilai() {
        return totalThpNilai;
    }

    public void setTotalThpNilai(BigDecimal totalThpNilai) {
        this.totalThpNilai = totalThpNilai;
    }
}