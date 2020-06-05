package com.neurix.authorization.company.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class Branch extends BaseModel implements Serializable, Comparable<Branch> {
    private String branchId;
    private String areaId;
    private String alamatSurat;
    private String branchName;
    private String branchAddress;
    private String enabled;
    private String mt;
    private String statusPabrik;
    private String strBiayaJasprod;
    private BigDecimal biayaJasprod;
    private BigDecimal multifikator;
    private BigDecimal faktorJubileum;
    private BigDecimal faktorJasprod;
    private BigDecimal uangMakan;
    private int maxJamIjinKeluar;
    private String periodeGajiAktif;
    private String lemburGajiAwal;
    private String lemburGajiAkhir;

    private BigDecimal umr;
    private BigDecimal minBpjsKs;
    private BigDecimal maxBpjsKs;
    private BigDecimal minBpjsTk;
    private BigDecimal maxBpjsTk;
    private BigDecimal percentKsKary;
    private BigDecimal percentKsPers;
    private BigDecimal percentTkKary;
    private BigDecimal percentTkPers;
    private String logoName;
    private String logoBranch;

    private String ppkPelayanan;
    private String suratSkdp;
    private String kodeTarif;
    private String tarifPayorId;
    private String payorCd;
    private String coderNik;

    private BigDecimal maxPengajuanBiaya;
    private String coaRk;

    public String getCoaRk() {
        return coaRk;
    }

    public void setCoaRk(String coaRk) {
        this.coaRk = coaRk;
    }

    public BigDecimal getMaxPengajuanBiaya() {
        return maxPengajuanBiaya;
    }

    public void setMaxPengajuanBiaya(BigDecimal maxPengajuanBiaya) {
        this.maxPengajuanBiaya = maxPengajuanBiaya;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }


    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    private String stUmr;
    private String stMinBpjsKs;
    private String stMaxBpjsKs;
    private String stMinBpjsTk;
    private String stMaxBpjsTk;
    private String stPercentKsKary;
    private String stPercentKsPers;
    private String stPercentTkKary;
    private String stPercentTkPers;

    public String getStMaxBpjsKs() {
        return stMaxBpjsKs;
    }

    public void setStMaxBpjsKs(String stMaxBpjsKs) {
        this.stMaxBpjsKs = stMaxBpjsKs;
    }

    public String getStMaxBpjsTk() {
        return stMaxBpjsTk;
    }

    public void setStMaxBpjsTk(String stMaxBpjsTk) {
        this.stMaxBpjsTk = stMaxBpjsTk;
    }

    public String getStMinBpjsKs() {
        return stMinBpjsKs;
    }

    public void setStMinBpjsKs(String stMinBpjsKs) {
        this.stMinBpjsKs = stMinBpjsKs;
    }

    public String getStMinBpjsTk() {
        return stMinBpjsTk;
    }

    public void setStMinBpjsTk(String stMinBpjsTk) {
        this.stMinBpjsTk = stMinBpjsTk;
    }

    public String getStPercentKsKary() {
        return stPercentKsKary;
    }

    public void setStPercentKsKary(String stPercentKsKary) {
        this.stPercentKsKary = stPercentKsKary;
    }

    public String getStPercentKsPers() {
        return stPercentKsPers;
    }

    public void setStPercentKsPers(String stPercentKsPers) {
        this.stPercentKsPers = stPercentKsPers;
    }

    public String getStPercentTkKary() {
        return stPercentTkKary;
    }

    public void setStPercentTkKary(String stPercentTkKary) {
        this.stPercentTkKary = stPercentTkKary;
    }

    public String getStPercentTkPers() {
        return stPercentTkPers;
    }

    public void setStPercentTkPers(String stPercentTkPers) {
        this.stPercentTkPers = stPercentTkPers;
    }

    public String getStUmr() {
        return stUmr;
    }

    public void setStUmr(String stUmr) {
        this.stUmr = stUmr;
    }

    public String getAlamatSurat() {
        return alamatSurat;
    }


    public void setAlamatSurat(String alamatSurat) {
        this.alamatSurat = alamatSurat;
    }

    public String getPpkPelayanan() {
        return ppkPelayanan;
    }

    public void setPpkPelayanan(String ppkPelayanan) {
        this.ppkPelayanan = ppkPelayanan;
    }

    public String getSuratSkdp() {
        return suratSkdp;
    }

    public void setSuratSkdp(String suratSkdp) {
        this.suratSkdp = suratSkdp;
    }

    public String getKodeTarif() {
        return kodeTarif;
    }

    public void setKodeTarif(String kodeTarif) {
        this.kodeTarif = kodeTarif;
    }

    public String getTarifPayorId() {
        return tarifPayorId;
    }

    public void setTarifPayorId(String tarifPayorId) {
        this.tarifPayorId = tarifPayorId;
    }

    public String getPayorCd() {
        return payorCd;
    }

    public void setPayorCd(String payorCd) {
        this.payorCd = payorCd;
    }

    public String getCoderNik() {
        return coderNik;
    }

    public void setCoderNik(String coderNik) {
        this.coderNik = coderNik;
    }

    public String getLogoBranch() {
        return logoBranch;
    }

    public void setLogoBranch(String logoBranch) {
        this.logoBranch = logoBranch;
    }

    public String getAreaId() {
        return areaId;
    }

    public BigDecimal getMaxBpjsKs() {
        return maxBpjsKs;
    }

    public void setMaxBpjsKs(BigDecimal maxBpjsKs) {
        this.maxBpjsKs = maxBpjsKs;
    }

    public BigDecimal getMaxBpjsTk() {
        return maxBpjsTk;
    }

    public void setMaxBpjsTk(BigDecimal maxBpjsTk) {
        this.maxBpjsTk = maxBpjsTk;
    }

    public BigDecimal getMinBpjsKs() {
        return minBpjsKs;
    }

    public void setMinBpjsKs(BigDecimal minBpjsKs) {
        this.minBpjsKs = minBpjsKs;
    }

    public BigDecimal getMinBpjsTk() {
        return minBpjsTk;
    }

    public void setMinBpjsTk(BigDecimal minBpjsTk) {
        this.minBpjsTk = minBpjsTk;
    }

    public BigDecimal getPercentKsKary() {
        return percentKsKary;
    }

    public void setPercentKsKary(BigDecimal percentKsKary) {
        this.percentKsKary = percentKsKary;
    }

    public BigDecimal getPercentKsPers() {
        return percentKsPers;
    }

    public void setPercentKsPers(BigDecimal percentKsPers) {
        this.percentKsPers = percentKsPers;
    }

    public BigDecimal getPercentTkKary() {
        return percentTkKary;
    }

    public void setPercentTkKary(BigDecimal percentTkKary) {
        this.percentTkKary = percentTkKary;
    }

    public BigDecimal getPercentTkPers() {
        return percentTkPers;
    }

    public void setPercentTkPers(BigDecimal percentTkPers) {
        this.percentTkPers = percentTkPers;
    }

    public String getPeriodeGajiAktif() {
        return periodeGajiAktif;
    }

    public void setPeriodeGajiAktif(String periodeGajiAktif) {
        this.periodeGajiAktif = periodeGajiAktif;
    }

    public String getLemburGajiAwal() {
        return lemburGajiAwal;
    }

    public void setLemburGajiAwal(String lemburGajiAwal) {
        this.lemburGajiAwal = lemburGajiAwal;
    }

    public String getLemburGajiAkhir() {
        return lemburGajiAkhir;
    }

    public void setLemburGajiAkhir(String lemburGajiAkhir) {
        this.lemburGajiAkhir = lemburGajiAkhir;
    }

    public String getStrBiayaJasprod() {
        return strBiayaJasprod;
    }

    public void setStrBiayaJasprod(String strBiayaJasprod) {
        this.strBiayaJasprod = strBiayaJasprod;
    }

    public BigDecimal getBiayaJasprod() {
        return biayaJasprod;
    }

    public void setBiayaJasprod(BigDecimal biayaJasprod) {
        this.biayaJasprod = biayaJasprod;
    }

    public BigDecimal getUangMakan() {
        return uangMakan;
    }

    public void setUangMakan(BigDecimal uangMakan) {
        this.uangMakan = uangMakan;
    }

    public int getMaxJamIjinKeluar() {
        return maxJamIjinKeluar;
    }

    public void setMaxJamIjinKeluar(int maxJamIjinKeluar) {
        this.maxJamIjinKeluar = maxJamIjinKeluar;
    }

    public BigDecimal getMultifikator() {
        return multifikator;
    }

    public BigDecimal getFaktorJasprod() {
        return faktorJasprod;
    }

    public void setFaktorJasprod(BigDecimal faktorJasprod) {
        this.faktorJasprod = faktorJasprod;
    }

    public BigDecimal getFaktorJubileum() {
        return faktorJubileum;
    }

    public void setFaktorJubileum(BigDecimal faktorJubileum) {
        this.faktorJubileum = faktorJubileum;
    }

    public void setMultifikator(BigDecimal multifikator) {
        this.multifikator = multifikator;
    }

    public BigDecimal getUmr() {
        return umr;
    }

    public void setUmr(BigDecimal umr) {
        this.umr = umr;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getStatusPabrik() {
        return statusPabrik;
    }

    public void setStatusPabrik(String statusPabrik) {
        this.statusPabrik = statusPabrik;
    }
    private BigDecimal faktorKali;

    public BigDecimal getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(BigDecimal faktorKali) {
        this.faktorKali = faktorKali;

    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public int compareTo(Branch o) {
        return this.branchName.toLowerCase().compareTo(o.getBranchName().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;

        Branch branch = (Branch) o;

        if (branchAddress != null ? !branchAddress.equals(branch.branchAddress) : branch.branchAddress != null)
            return false;
        if (branchId != null ? !branchId.equals(branch.branchId) : branch.branchId != null) return false;
        if (branchName != null ? !branchName.equals(branch.branchName) : branch.branchName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = branchId != null ? branchId.hashCode() : 0;
        result = 31 * result + (branchName != null ? branchName.hashCode() : 0);
        result = 31 * result + (branchAddress != null ? branchAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                '}';
    }
}