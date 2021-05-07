package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollJasoprEntity implements Serializable {
    private String jasoprId;
    private String payrollId;
    private String bulan;
    private String tahun;
    private String nip;

    private BigDecimal totalThp;
    private BigDecimal gaji;
    private BigDecimal sankhus;
    private BigDecimal tunjJabatan;
    private BigDecimal tunjStruktural;
    private BigDecimal tunjFungsional;
    private BigDecimal tunjPeralihan;
    private BigDecimal tunjLain;
    private BigDecimal tunjTambahan;
    private BigDecimal pemondokan;
    private BigDecimal komunikasi;
    private BigDecimal totalRlab;
    private BigDecimal totaljasopr;
    private BigDecimal pphJasopr;
    private BigDecimal nettojasopr;
    private BigDecimal tunjPph;

    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public BigDecimal getTunjPph() {
        return tunjPph;
    }

    public void setTunjPph(BigDecimal tunjPph) {
        this.tunjPph = tunjPph;
    }

    public BigDecimal getNettojasopr() {
        return nettojasopr;
    }

    public void setNettojasopr(BigDecimal nettojasopr) {
        this.nettojasopr = nettojasopr;
    }

    public BigDecimal getPphJasopr() {
        return pphJasopr;
    }

    public void setPphJasopr(BigDecimal pphJasopr) {
        this.pphJasopr = pphJasopr;
    }

    public BigDecimal getTotaljasopr() {
        return totaljasopr;
    }

    public void setTotaljasopr(BigDecimal totaljasopr) {
        this.totaljasopr = totaljasopr;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public String getJasoprId() {
        return jasoprId;
    }

    public void setJasoprId(String jasoprId) {
        this.jasoprId = jasoprId;
    }

    public BigDecimal getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(BigDecimal komunikasi) {
        this.komunikasi = komunikasi;
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

    public BigDecimal getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(BigDecimal pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getSankhus() {
        return sankhus;
    }

    public void setSankhus(BigDecimal sankhus) {
        this.sankhus = sankhus;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getTotalThp() {
        return totalThp;
    }

    public void setTotalThp(BigDecimal totalThp) {
        this.totalThp = totalThp;
    }

    public BigDecimal getTunjFungsional() {
        return tunjFungsional;
    }

    public void setTunjFungsional(BigDecimal tunjFungsional) {
        this.tunjFungsional = tunjFungsional;
    }

    public BigDecimal getTunjJabatan() {
        return tunjJabatan;
    }

    public void setTunjJabatan(BigDecimal tunjJabatan) {
        this.tunjJabatan = tunjJabatan;
    }

    public BigDecimal getTunjLain() {
        return tunjLain;
    }

    public void setTunjLain(BigDecimal tunjLain) {
        this.tunjLain = tunjLain;
    }

    public BigDecimal getTunjPeralihan() {
        return tunjPeralihan;
    }

    public void setTunjPeralihan(BigDecimal tunjPeralihan) {
        this.tunjPeralihan = tunjPeralihan;
    }

    public BigDecimal getTunjStruktural() {
        return tunjStruktural;
    }

    public void setTunjStruktural(BigDecimal tunjStruktural) {
        this.tunjStruktural = tunjStruktural;
    }

    public BigDecimal getTunjTambahan() {
        return tunjTambahan;
    }

    public void setTunjTambahan(BigDecimal tunjTambahan) {
        this.tunjTambahan = tunjTambahan;
    }

    public BigDecimal getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(BigDecimal totalRlab) {
        this.totalRlab = totalRlab;
    }
}
