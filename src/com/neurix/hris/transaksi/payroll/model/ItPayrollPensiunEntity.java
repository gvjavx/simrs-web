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

public class ItPayrollPensiunEntity implements Serializable {
    private String nip;
    private String pensiunId;
    private String payrollId;
    private Date tanggalAktif;
    private Date tanggalPensiun;
    private BigDecimal gajiGolongan;
    private BigDecimal tunjanganUmk;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganJabatanStruktural;
    private BigDecimal tunjanganPeralihan;
    private BigDecimal jumlahTunjangan;
    private BigDecimal tunjanganPensiun;
    private BigDecimal tunjanganPenghargaan;
    private BigDecimal penggantianPerumahan;

    private BigDecimal faktorPensiun;
    private BigDecimal faktorPenghargaan;
    private BigDecimal jumlahBiayaPensiun;
    private BigDecimal pphPensiun;

    private BigDecimal pph1Nilai;
    private BigDecimal pph2Nilai;
    private BigDecimal pph3Nilai;
    private BigDecimal pph4Nilai;
    private BigDecimal nettoPensiun;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getNettoPensiun() {
        return nettoPensiun;
    }

    public void setNettoPensiun(BigDecimal nettoPensiun) {
        this.nettoPensiun = nettoPensiun;
    }

    public BigDecimal getPph1Nilai() {
        return pph1Nilai;
    }

    public void setPph1Nilai(BigDecimal pph1Nilai) {
        this.pph1Nilai = pph1Nilai;
    }

    public BigDecimal getPph2Nilai() {
        return pph2Nilai;
    }

    public void setPph2Nilai(BigDecimal pph2Nilai) {
        this.pph2Nilai = pph2Nilai;
    }

    public BigDecimal getPph3Nilai() {
        return pph3Nilai;
    }

    public void setPph3Nilai(BigDecimal pph3Nilai) {
        this.pph3Nilai = pph3Nilai;
    }

    public BigDecimal getPph4Nilai() {
        return pph4Nilai;
    }

    public void setPph4Nilai(BigDecimal pph4Nilai) {
        this.pph4Nilai = pph4Nilai;
    }

    public BigDecimal getPenggantianPerumahan() {
        return penggantianPerumahan;
    }

    public void setPenggantianPerumahan(BigDecimal penggantianPerumahan) {
        this.penggantianPerumahan = penggantianPerumahan;
    }

    public BigDecimal getJumlahTunjangan() {
        return jumlahTunjangan;
    }

    public void setJumlahTunjangan(BigDecimal jumlahTunjangan) {
        this.jumlahTunjangan = jumlahTunjangan;
    }

    public BigDecimal getTunjanganPenghargaan() {
        return tunjanganPenghargaan;
    }

    public void setTunjanganPenghargaan(BigDecimal tunjanganPenghargaan) {
        this.tunjanganPenghargaan = tunjanganPenghargaan;
    }

    public BigDecimal getTunjanganPensiun() {
        return tunjanganPensiun;
    }

    public void setTunjanganPensiun(BigDecimal tunjanganPensiun) {
        this.tunjanganPensiun = tunjanganPensiun;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getFaktorPenghargaan() {
        return faktorPenghargaan;
    }

    public void setFaktorPenghargaan(BigDecimal faktorPenghargaan) {
        this.faktorPenghargaan = faktorPenghargaan;
    }

    public BigDecimal getFaktorPensiun() {
        return faktorPensiun;
    }

    public void setFaktorPensiun(BigDecimal faktorPensiun) {
        this.faktorPensiun = faktorPensiun;
    }

    public BigDecimal getPphPensiun() {
        return pphPensiun;
    }

    public void setPphPensiun(BigDecimal pphPensiun) {
        this.pphPensiun = pphPensiun;
    }

    public BigDecimal getJumlahBiayaPensiun() {
        return jumlahBiayaPensiun;
    }

    public void setJumlahBiayaPensiun(BigDecimal jumlahBiayaPensiun) {
        this.jumlahBiayaPensiun = jumlahBiayaPensiun;
    }

    public BigDecimal getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(BigDecimal gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public String getPensiunId() {
        return pensiunId;
    }

    public void setPensiunId(String pensiunId) {
        this.pensiunId = pensiunId;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public Date getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(Date tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(BigDecimal tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
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