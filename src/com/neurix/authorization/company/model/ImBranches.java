package com.neurix.authorization.company.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 5:16
 * To change this template use File | Settings | File Templates.
 */
public class ImBranches implements Serializable {
    private ImBranchesPK primaryKey;
    private String branchName;
    private String alamatSurat;

    private String statusPabrik;
    private String mt;
    private BigDecimal faktorJubileum;
    private BigDecimal faktorJasprod;
    private String masaTanam;
    private BigDecimal umr;
    private BigDecimal uangMakan;
    private int maxJamIjinKeluar;
    private String strBiayaJasprod;
    private BigDecimal biayaJasprod;
    private String areaId;
    private String constId;
    private String secretKey;
    private String username;
    private String password;
    private String kdAplikasi;
    private String eklaimAddress; //example : "http://192.168.1.1/"
    private String keyEklaim;
    private String logoName;

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }


    private String ppkPelayanan;
    private String suratSkdp;
    private String kodeTarif;
    private String tarifPayorId;
    private String payorCd;
    private String coderNik;

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

    private BigDecimal faktorKali;
    private BigDecimal multifikator;
    private String branchAddress;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String enabled;
    private String action;


    private String periodeGajiAktif;
    private String lemburGajiAwal;
    private String lemburGajiAkhir;
    private BigDecimal minBpjsKs;
    private BigDecimal maxBpjsKs;
    private BigDecimal minBpjsTk;
    private BigDecimal maxBpjsTk;
    private BigDecimal percentKsKary;
    private BigDecimal percentKsPers;
    private BigDecimal percentTkKary;
    private BigDecimal percentTkPers;

    public String getAlamatSurat() {
        return alamatSurat;
    }

    public void setAlamatSurat(String alamatSurat) {
        this.alamatSurat = alamatSurat;
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
    public String getKeyEklaim() {
        return keyEklaim;
    }

    public void setKeyEklaim(String keyEklaim) {
        this.keyEklaim = keyEklaim;
    }

    public String getEklaimAddress() {
        return eklaimAddress;
    }

    public void setEklaimAddress(String eklaimAddress) {
        this.eklaimAddress = eklaimAddress;
    }

    public String getConstId() {
        return constId;
    }

    public void setConstId(String constId) {
        this.constId = constId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKdAplikasi() {
        return kdAplikasi;
    }

    public void setKdAplikasi(String kdAplikasi) {
        this.kdAplikasi = kdAplikasi;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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

    public BigDecimal getUmr() {
        return umr;
    }

    public void setUmr(BigDecimal umr) {
        this.umr = umr;
    }

    public String getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(String masaTanam) {
        this.masaTanam = masaTanam;
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


    public BigDecimal getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(BigDecimal faktorKali) {
        this.faktorKali = faktorKali;
    }


    public BigDecimal getMultifikator() {
        return multifikator;
    }

    public void setMultifikator(BigDecimal multifikator) {
        this.multifikator = multifikator;
    }


    public ImBranchesPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ImBranchesPK primaryKey) {
        this.primaryKey = primaryKey;
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


    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
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

    private Set<ImAreasBranchesUsers> imAreasBranchesUsers;

    public Set<ImAreasBranchesUsers> getImAreasBranchesUsers() {
        return imAreasBranchesUsers;
    }

    public void setImAreasBranchesUsers(Set<ImAreasBranchesUsers> imAreasBranchesUsers) {
        this.imAreasBranchesUsers = imAreasBranchesUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImBranches)) return false;

        ImBranches that = (ImBranches) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (branchAddress != null ? !branchAddress.equals(that.branchAddress) : that.branchAddress != null)
            return false;
        if (branchName != null ? !branchName.equals(that.branchName) : that.branchName != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (imAreasBranchesUsers != null ? !imAreasBranchesUsers.equals(that.imAreasBranchesUsers) : that.imAreasBranchesUsers != null)
            return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryKey != null ? primaryKey.hashCode() : 0;
        result = 31 * result + (branchName != null ? branchName.hashCode() : 0);
        result = 31 * result + (branchAddress != null ? branchAddress.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (imAreasBranchesUsers != null ? imAreasBranchesUsers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImBranches{" +
                "primaryKey=" + primaryKey +
                ", branchName='" + branchName + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", action='" + action + '\'' +
                ", flag='" + flag + '\'' +
                ", imAreasBranchesUsers=" + imAreasBranchesUsers +
                '}';
    }
}