package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollCutiEntity extends BaseModel {

    private String payrollCutiId;
    private String nip;
    private String payrollId;
    private String bulan;
    private String tahun;

    private BigDecimal totalCuti;
    private BigDecimal pphCuti;
    private BigDecimal totalBersihCuti;
    private BigDecimal totalThp;

    private BigDecimal gaji;
    private BigDecimal sankhus;
    private BigDecimal tunjJabatan;
    private BigDecimal tunjStruktural;
    private BigDecimal tunjFungsional;
    private BigDecimal tunjPeralihan;
    private BigDecimal tunjRumah;
    private BigDecimal tunjListrik;
    private BigDecimal tunjAir;
    private BigDecimal tunjBbm;

    private String keterangan;
    private String tahunCutiPanjang;


    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public String getTahunCutiPanjang() {
        return tahunCutiPanjang;
    }

    public void setTahunCutiPanjang(String tahunCutiPanjang) {
        this.tahunCutiPanjang = tahunCutiPanjang;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getSankhus() {
        return sankhus;
    }

    public void setSankhus(BigDecimal sankhus) {
        this.sankhus = sankhus;
    }

    public BigDecimal getTunjAir() {
        return tunjAir;
    }

    public void setTunjAir(BigDecimal tunjAir) {
        this.tunjAir = tunjAir;
    }

    public BigDecimal getTunjBbm() {
        return tunjBbm;
    }

    public void setTunjBbm(BigDecimal tunjBbm) {
        this.tunjBbm = tunjBbm;
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

    public BigDecimal getTunjListrik() {
        return tunjListrik;
    }

    public void setTunjListrik(BigDecimal tunjListrik) {
        this.tunjListrik = tunjListrik;
    }

    public BigDecimal getTunjPeralihan() {
        return tunjPeralihan;
    }

    public void setTunjPeralihan(BigDecimal tunjPeralihan) {
        this.tunjPeralihan = tunjPeralihan;
    }

    public BigDecimal getTunjRumah() {
        return tunjRumah;
    }

    public void setTunjRumah(BigDecimal tunjRumah) {
        this.tunjRumah = tunjRumah;
    }

    public BigDecimal getTunjStruktural() {
        return tunjStruktural;
    }

    public void setTunjStruktural(BigDecimal tunjStruktural) {
        this.tunjStruktural = tunjStruktural;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
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

    public BigDecimal getPphCuti() {
        return pphCuti;
    }

    public void setPphCuti(BigDecimal pphCuti) {
        this.pphCuti = pphCuti;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getTotalBersihCuti() {
        return totalBersihCuti;
    }

    public void setTotalBersihCuti(BigDecimal totalBersihCuti) {
        this.totalBersihCuti = totalBersihCuti;
    }

    public BigDecimal getTotalCuti() {
        return totalCuti;
    }

    public void setTotalCuti(BigDecimal totalCuti) {
        this.totalCuti = totalCuti;
    }

    public BigDecimal getTotalThp() {
        return totalThp;
    }

    public void setTotalThp(BigDecimal totalThp) {
        this.totalThp = totalThp;
    }
}