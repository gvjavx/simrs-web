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

public class PayrollJasopr extends BaseModel {

    private String jasoprId;
    private String payrollId;
    private String bulan;
    private String tahun;
    private String nip;

    private BigDecimal totalThpNilai;
    private BigDecimal gajiNilai;
    private BigDecimal sankhusNilai;
    private BigDecimal tunjJabatanNilai;
    private BigDecimal tunjStrukturalNilai;
    private BigDecimal tunjFungsionalNilai;
    private BigDecimal tunjPeralihanNilai;
    private BigDecimal tunjLainNilai;
    private BigDecimal tunjTambahanNilai;
    private BigDecimal pemondokanNilai;
    private BigDecimal komunikasiNilai;
    private BigDecimal totalRlabNilai;
    private BigDecimal totaljasoprNilai;
    private BigDecimal pphJasoprNilai;
    private BigDecimal nettojasoprNilai;
    private BigDecimal tunjPphNilai ;


    private String totalThp;
    private String gaji;
    private String sankhus;
    private String tunjJabatan;
    private String tunjStruktural;
    private String tunjFungsional;
    private String tunjPeralihan;
    private String tunjLain;
    private String tunjTambahan;
    private String pemondokan;
    private String komunikasi;
    private String totalRlab;
    private String totaljasopr;
    private String pphJasopr;
    private String nettojasopr;
    private String tunjPph ;

    public BigDecimal getTunjPphNilai() {
        return tunjPphNilai;
    }

    public void setTunjPphNilai(BigDecimal tunjPphNilai) {
        this.tunjPphNilai = tunjPphNilai;
    }

    public String getTunjPph() {
        return tunjPph;
    }

    public void setTunjPph(String tunjPph) {
        this.tunjPph = tunjPph;
    }

    public String getNettojasopr() {
        return nettojasopr;
    }

    public void setNettojasopr(String nettojasopr) {
        this.nettojasopr = nettojasopr;
    }

    public BigDecimal getNettojasoprNilai() {
        return nettojasoprNilai;
    }

    public void setNettojasoprNilai(BigDecimal nettojasoprNilai) {
        this.nettojasoprNilai = nettojasoprNilai;
    }

    public String getPphJasopr() {
        return pphJasopr;
    }

    public void setPphJasopr(String pphJasopr) {
        this.pphJasopr = pphJasopr;
    }

    public BigDecimal getPphJasoprNilai() {
        return pphJasoprNilai;
    }

    public void setPphJasoprNilai(BigDecimal pphJasoprNilai) {
        this.pphJasoprNilai = pphJasoprNilai;
    }

    public String getTotaljasopr() {
        return totaljasopr;
    }

    public void setTotaljasopr(String totaljasopr) {
        this.totaljasopr = totaljasopr;
    }

    public BigDecimal getTotaljasoprNilai() {
        return totaljasoprNilai;
    }

    public void setTotaljasoprNilai(BigDecimal totaljasoprNilai) {
        this.totaljasoprNilai = totaljasoprNilai;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
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

    public String getJasoprId() {
        return jasoprId;
    }

    public void setJasoprId(String jasoprId) {
        this.jasoprId = jasoprId;
    }

    public String getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(String komunikasi) {
        this.komunikasi = komunikasi;
    }

    public BigDecimal getKomunikasiNilai() {
        return komunikasiNilai;
    }

    public void setKomunikasiNilai(BigDecimal komunikasiNilai) {
        this.komunikasiNilai = komunikasiNilai;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(String pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getPemondokanNilai() {
        return pemondokanNilai;
    }

    public void setPemondokanNilai(BigDecimal pemondokanNilai) {
        this.pemondokanNilai = pemondokanNilai;
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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    public String getTunjLain() {
        return tunjLain;
    }

    public void setTunjLain(String tunjLain) {
        this.tunjLain = tunjLain;
    }

    public BigDecimal getTunjLainNilai() {
        return tunjLainNilai;
    }

    public void setTunjLainNilai(BigDecimal tunjLainNilai) {
        this.tunjLainNilai = tunjLainNilai;
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

    public String getTunjTambahan() {
        return tunjTambahan;
    }

    public void setTunjTambahan(String tunjTambahan) {
        this.tunjTambahan = tunjTambahan;
    }

    public BigDecimal getTunjTambahanNilai() {
        return tunjTambahanNilai;
    }

    public void setTunjTambahanNilai(BigDecimal tunjTambahanNilai) {
        this.tunjTambahanNilai = tunjTambahanNilai;
    }

    public BigDecimal getTotalRlabNilai() {
        return totalRlabNilai;
    }

    public void setTotalRlabNilai(BigDecimal totalRlabNilai) {
        this.totalRlabNilai = totalRlabNilai;
    }

    public String getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(String totalRlab) {
        this.totalRlab = totalRlab;
    }
}