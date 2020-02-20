package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollJubileumEntity implements Serializable {
    private String nip;
    private String jubileumId;
    private String payrollId;

    private BigDecimal total;
    private BigDecimal pphJubileum;
    private BigDecimal nettoJubileum;
    private Date tanggalJubileum;

    private BigDecimal gaji;
    private BigDecimal sankhus;
    private BigDecimal tunjanganjabatan;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganFungsional;
    private BigDecimal tunjanganPeralihan;
    private BigDecimal tunjanganTambahan;
    private BigDecimal pemondokan;
    private BigDecimal komunikasi;
    private String keteranganEmas;
    private String keteranganjubileum;


    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getKeteranganjubileum() {
        return keteranganjubileum;
    }

    public void setKeteranganjubileum(String keteranganjubileum) {
        this.keteranganjubileum = keteranganjubileum;
    }

    public BigDecimal getTunjanganTambahan() {
        return tunjanganTambahan;
    }

    public void setTunjanganTambahan(BigDecimal tunjanganTambahan) {
        this.tunjanganTambahan = tunjanganTambahan;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(BigDecimal komunikasi) {
        this.komunikasi = komunikasi;
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

    public BigDecimal getTunjanganFungsional() {
        return tunjanganFungsional;
    }

    public void setTunjanganFungsional(BigDecimal tunjanganFungsional) {
        this.tunjanganFungsional = tunjanganFungsional;
    }

    public BigDecimal getTunjanganjabatan() {
        return tunjanganjabatan;
    }

    public void setTunjanganjabatan(BigDecimal tunjanganjabatan) {
        this.tunjanganjabatan = tunjanganjabatan;
    }
    private BigDecimal tunjanganJabStruktural;
    private BigDecimal gajiGolongan;
    private BigDecimal tunjanganUmk;
    private BigDecimal peralihan;
    private BigDecimal grandTotal;
    public BigDecimal getPeralihan() {
        return peralihan;
    }

    public void setPeralihan(BigDecimal peralihan) {
        this.peralihan = peralihan;
    }

    public String getKeteranganEmas() {
        return keteranganEmas;
    }

    public void setKeteranganEmas(String keteranganEmas) {
        this.keteranganEmas = keteranganEmas;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggalJubileum() {
        return tanggalJubileum;
    }

    public void setTanggalJubileum(Date tanggalJubileum) {
        this.tanggalJubileum = tanggalJubileum;
    }

    public BigDecimal getNettoJubileum() {
        return nettoJubileum;
    }

    public void setNettoJubileum(BigDecimal nettoJubileum) {
        this.nettoJubileum = nettoJubileum;
    }

    public BigDecimal getPphJubileum() {
        return pphJubileum;
    }

    public void setPphJubileum(BigDecimal pphJubileum) {
        this.pphJubileum = pphJubileum;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getJubileumId() {
        return jubileumId;
    }

    public void setJubileumId(String jubileumId) {
        this.jubileumId = jubileumId;
    }

    public BigDecimal getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(BigDecimal gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTunjanganJabStruktural() {
        return tunjanganJabStruktural;
    }

    public void setTunjanganJabStruktural(BigDecimal tunjanganJabStruktural) {
        this.tunjanganJabStruktural = tunjanganJabStruktural;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
