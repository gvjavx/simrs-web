/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ItHrisPayrollPphTempEntity {
    private String pphTempId;
    private String payrollTempId;
    private BigDecimal pkp;
    private BigDecimal pphGaji;
    private BigDecimal bruto;
    private BigDecimal reduce;
    private String nip;
    private String bulan;
    private String tahun;
    private BigDecimal ptkp;
    private BigDecimal netto;
    private BigDecimal biayaJabatan;
    private BigDecimal hutangPphSetahun;
    private BigDecimal selisihPphSetahun;
    private BigDecimal tunjPph;
    private BigDecimal gaji;
    private BigDecimal sankhus;
    private BigDecimal tunjJabatan;
    private BigDecimal tunjStruktural;
    private BigDecimal tunjFungsional;
    private BigDecimal tunjPeralihan;
    private BigDecimal totalTunjLain;
    private BigDecimal tunjTambahan;
    private BigDecimal pemondokan;
    private BigDecimal komunikasi;
    private BigDecimal totalRlab;
    private BigDecimal iuranPegawai;
    private BigDecimal lembur;
    private BigDecimal tunjPensiun;
    private BigDecimal bpjsTk;
    private BigDecimal bpjsKs;
    private BigDecimal bonus;
    private String keterangan;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;
    private String flag;

    private ItHrisPayrollTempEntity itHrisPayrollTemp;

    public ItHrisPayrollTempEntity getItHrisPayrollTemp() {
        return itHrisPayrollTemp;
    }

    public void setItHrisPayrollTemp(ItHrisPayrollTempEntity itHrisPayrollTemp) {
        this.itHrisPayrollTemp = itHrisPayrollTemp;
    }

    public String getPphTempId() {
        return pphTempId;
    }

    public void setPphTempId(String pphTempId) {
        this.pphTempId = pphTempId;
    }

    public String getPayrollTempId() {
        return payrollTempId;
    }

    public void setPayrollTempId(String payrollTempId) {
        this.payrollTempId = payrollTempId;
    }

    public BigDecimal getPkp() {
        return pkp;
    }

    public void setPkp(BigDecimal pkp) {
        this.pkp = pkp;
    }

    public BigDecimal getPphGaji() {
        return pphGaji;
    }

    public void setPphGaji(BigDecimal pphGaji) {
        this.pphGaji = pphGaji;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public BigDecimal getPtkp() {
        return ptkp;
    }

    public void setPtkp(BigDecimal ptkp) {
        this.ptkp = ptkp;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    public void setNetto(BigDecimal netto) {
        this.netto = netto;
    }

    public BigDecimal getBiayaJabatan() {
        return biayaJabatan;
    }

    public void setBiayaJabatan(BigDecimal biayaJabatan) {
        this.biayaJabatan = biayaJabatan;
    }

    public BigDecimal getHutangPphSetahun() {
        return hutangPphSetahun;
    }

    public void setHutangPphSetahun(BigDecimal hutangPphSetahun) {
        this.hutangPphSetahun = hutangPphSetahun;
    }

    public BigDecimal getSelisihPphSetahun() {
        return selisihPphSetahun;
    }

    public void setSelisihPphSetahun(BigDecimal selisihPphSetahun) {
        this.selisihPphSetahun = selisihPphSetahun;
    }

    public BigDecimal getTunjPph() {
        return tunjPph;
    }

    public void setTunjPph(BigDecimal tunjPph) {
        this.tunjPph = tunjPph;
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

    public BigDecimal getTunjJabatan() {
        return tunjJabatan;
    }

    public void setTunjJabatan(BigDecimal tunjJabatan) {
        this.tunjJabatan = tunjJabatan;
    }

    public BigDecimal getTunjStruktural() {
        return tunjStruktural;
    }

    public void setTunjStruktural(BigDecimal tunjStruktural) {
        this.tunjStruktural = tunjStruktural;
    }

    public BigDecimal getTunjFungsional() {
        return tunjFungsional;
    }

    public void setTunjFungsional(BigDecimal tunjFungsional) {
        this.tunjFungsional = tunjFungsional;
    }

    public BigDecimal getTunjPeralihan() {
        return tunjPeralihan;
    }

    public void setTunjPeralihan(BigDecimal tunjPeralihan) {
        this.tunjPeralihan = tunjPeralihan;
    }

    public BigDecimal getTotalTunjLain() {
        return totalTunjLain;
    }

    public void setTotalTunjLain(BigDecimal totalTunjLain) {
        this.totalTunjLain = totalTunjLain;
    }

    public BigDecimal getTunjTambahan() {
        return tunjTambahan;
    }

    public void setTunjTambahan(BigDecimal tunjTambahan) {
        this.tunjTambahan = tunjTambahan;
    }

    public BigDecimal getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(BigDecimal pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(BigDecimal komunikasi) {
        this.komunikasi = komunikasi;
    }

    public BigDecimal getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(BigDecimal totalRlab) {
        this.totalRlab = totalRlab;
    }

    public BigDecimal getIuranPegawai() {
        return iuranPegawai;
    }

    public void setIuranPegawai(BigDecimal iuranPegawai) {
        this.iuranPegawai = iuranPegawai;
    }

    public BigDecimal getLembur() {
        return lembur;
    }

    public void setLembur(BigDecimal lembur) {
        this.lembur = lembur;
    }

    public BigDecimal getTunjPensiun() {
        return tunjPensiun;
    }

    public void setTunjPensiun(BigDecimal tunjPensiun) {
        this.tunjPensiun = tunjPensiun;
    }

    public BigDecimal getBpjsTk() {
        return bpjsTk;
    }

    public void setBpjsTk(BigDecimal bpjsTk) {
        this.bpjsTk = bpjsTk;
    }

    public BigDecimal getBpjsKs() {
        return bpjsKs;
    }

    public void setBpjsKs(BigDecimal bpjsKs) {
        this.bpjsKs = bpjsKs;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        ItHrisPayrollPphTempEntity that = (ItHrisPayrollPphTempEntity) o;
        return Objects.equals(pphTempId, that.pphTempId) &&
                Objects.equals(payrollTempId, that.payrollTempId) &&
                Objects.equals(pkp, that.pkp) &&
                Objects.equals(pphGaji, that.pphGaji) &&
                Objects.equals(bruto, that.bruto) &&
                Objects.equals(reduce, that.reduce) &&
                Objects.equals(nip, that.nip) &&
                Objects.equals(bulan, that.bulan) &&
                Objects.equals(tahun, that.tahun) &&
                Objects.equals(ptkp, that.ptkp) &&
                Objects.equals(netto, that.netto) &&
                Objects.equals(biayaJabatan, that.biayaJabatan) &&
                Objects.equals(hutangPphSetahun, that.hutangPphSetahun) &&
                Objects.equals(selisihPphSetahun, that.selisihPphSetahun) &&
                Objects.equals(tunjPph, that.tunjPph) &&
                Objects.equals(gaji, that.gaji) &&
                Objects.equals(sankhus, that.sankhus) &&
                Objects.equals(tunjJabatan, that.tunjJabatan) &&
                Objects.equals(tunjStruktural, that.tunjStruktural) &&
                Objects.equals(tunjFungsional, that.tunjFungsional) &&
                Objects.equals(tunjPeralihan, that.tunjPeralihan) &&
                Objects.equals(totalTunjLain, that.totalTunjLain) &&
                Objects.equals(tunjTambahan, that.tunjTambahan) &&
                Objects.equals(pemondokan, that.pemondokan) &&
                Objects.equals(komunikasi, that.komunikasi) &&
                Objects.equals(totalRlab, that.totalRlab) &&
                Objects.equals(iuranPegawai, that.iuranPegawai) &&
                Objects.equals(lembur, that.lembur) &&
                Objects.equals(tunjPensiun, that.tunjPensiun) &&
                Objects.equals(bpjsTk, that.bpjsTk) &&
                Objects.equals(bpjsKs, that.bpjsKs) &&
                Objects.equals(bonus, that.bonus) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pphTempId, payrollTempId, pkp, pphGaji, bruto, reduce, nip, bulan, tahun, ptkp, netto, biayaJabatan, hutangPphSetahun, selisihPphSetahun, tunjPph, gaji, sankhus, tunjJabatan, tunjStruktural, tunjFungsional, tunjPeralihan, totalTunjLain, tunjTambahan, pemondokan, komunikasi, totalRlab, iuranPegawai, lembur, tunjPensiun, bpjsTk, bpjsKs, bonus, keterangan, createdDate, createdWho, lastUpdate, lastUpdateWho, action, flag);
    }
}
