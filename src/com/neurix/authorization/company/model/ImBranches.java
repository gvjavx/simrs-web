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
    private String alamatSurat;

    public String getAlamatSurat() {
        return alamatSurat;
    }

    public void setAlamatSurat(String alamatSurat) {
        this.alamatSurat = alamatSurat;
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

    private BigDecimal faktorKali;

    public BigDecimal getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(BigDecimal faktorKali) {
        this.faktorKali = faktorKali;
    }

    private BigDecimal multifikator;

    public BigDecimal getMultifikator() {
        return multifikator;
    }

    public void setMultifikator(BigDecimal multifikator) {
        this.multifikator = multifikator;
    }

    private ImBranchesPK primaryKey;

    public ImBranchesPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ImBranchesPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private String branchAddress;

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    private Timestamp createdDate;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    private String createdWho;

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    private Timestamp lastUpdate;

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String lastUpdateWho;

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    private String flag;
    private String enabled;

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

    private String action;

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