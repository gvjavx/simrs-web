package com.neurix.simrs.master.obat.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 30/09/20.
 */
public class ImSimrsHeaderObatEntity {
    private String idObat;
    private String namaObat;
    private String idPabrik;
    private BigInteger lembarPerBox;
    private BigInteger bijiPerLembar;
    private String flagKronis;
    private String flagBpjs;
    private String flagGeneric;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idKategoriPersediaan;
    private BigInteger minStok;
    private String merk;
    private String idBentuk;

    public String getIdBentuk() {
        return idBentuk;
    }

    public void setIdBentuk(String idBentuk) {
        this.idBentuk = idBentuk;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public BigInteger getMinStok() {
        return minStok;
    }

    public void setMinStok(BigInteger minStok) {
        this.minStok = minStok;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public BigInteger getLembarPerBox() {
        return lembarPerBox;
    }

    public void setLembarPerBox(BigInteger lembarPerBox) {
        this.lembarPerBox = lembarPerBox;
    }

    public BigInteger getBijiPerLembar() {
        return bijiPerLembar;
    }

    public void setBijiPerLembar(BigInteger bijiPerLembar) {
        this.bijiPerLembar = bijiPerLembar;
    }

    public String getFlagKronis() {
        return flagKronis;
    }

    public void setFlagKronis(String flagKronis) {
        this.flagKronis = flagKronis;
    }

    public String getFlagBpjs() {
        return flagBpjs;
    }

    public void setFlagBpjs(String flagBpjs) {
        this.flagBpjs = flagBpjs;
    }

    public String getFlagGeneric() {
        return flagGeneric;
    }

    public void setFlagGeneric(String flagGeneric) {
        this.flagGeneric = flagGeneric;
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

    public String getIdKategoriPersediaan() {
        return idKategoriPersediaan;
    }

    public void setIdKategoriPersediaan(String idKategoriPersediaan) {
        this.idKategoriPersediaan = idKategoriPersediaan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImSimrsHeaderObatEntity that = (ImSimrsHeaderObatEntity) o;

        if (idObat != null ? !idObat.equals(that.idObat) : that.idObat != null) return false;
        if (namaObat != null ? !namaObat.equals(that.namaObat) : that.namaObat != null) return false;
        if (idPabrik != null ? !idPabrik.equals(that.idPabrik) : that.idPabrik != null) return false;
        if (lembarPerBox != null ? !lembarPerBox.equals(that.lembarPerBox) : that.lembarPerBox != null) return false;
        if (bijiPerLembar != null ? !bijiPerLembar.equals(that.bijiPerLembar) : that.bijiPerLembar != null)
            return false;
        if (flagKronis != null ? !flagKronis.equals(that.flagKronis) : that.flagKronis != null) return false;
        if (flagBpjs != null ? !flagBpjs.equals(that.flagBpjs) : that.flagBpjs != null) return false;
        if (flagGeneric != null ? !flagGeneric.equals(that.flagGeneric) : that.flagGeneric != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (idKategoriPersediaan != null ? !idKategoriPersediaan.equals(that.idKategoriPersediaan) : that.idKategoriPersediaan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idObat != null ? idObat.hashCode() : 0;
        result = 31 * result + (namaObat != null ? namaObat.hashCode() : 0);
        result = 31 * result + (idPabrik != null ? idPabrik.hashCode() : 0);
        result = 31 * result + (lembarPerBox != null ? lembarPerBox.hashCode() : 0);
        result = 31 * result + (bijiPerLembar != null ? bijiPerLembar.hashCode() : 0);
        result = 31 * result + (flagKronis != null ? flagKronis.hashCode() : 0);
        result = 31 * result + (flagBpjs != null ? flagBpjs.hashCode() : 0);
        result = 31 * result + (flagGeneric != null ? flagGeneric.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (idKategoriPersediaan != null ? idKategoriPersediaan.hashCode() : 0);
        return result;
    }
}
