package com.neurix.simrs.transaksi.reseponline.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 18/06/20.
 */
public class ItSimrsPengirimanObatEntity {
    private String id;
    private String idKurir;
    private String idResep;
    private String flagPickup;
    private String flagDiterimaPasien;
    private String idPasien;
    private String flag;
    private String action;
    private String idPelayanan;
    private String branchId;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String desaId;
    private String alamat;
    private String noTelp;
    private String lat;
    private String lon;
    private String flagTerkirim;
    private String jenisPembayaran;

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getFlagTerkirim() {
        return flagTerkirim;
    }

    public void setFlagTerkirim(String flagTerkirim) {
        this.flagTerkirim = flagTerkirim;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public String getFlagPickup() {
        return flagPickup;
    }

    public void setFlagPickup(String flagPickup) {
        this.flagPickup = flagPickup;
    }

    public String getFlagDiterimaPasien() {
        return flagDiterimaPasien;
    }

    public void setFlagDiterimaPasien(String flagDiterimaPasien) {
        this.flagDiterimaPasien = flagDiterimaPasien;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
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

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItSimrsPengirimanObatEntity that = (ItSimrsPengirimanObatEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idKurir != null ? !idKurir.equals(that.idKurir) : that.idKurir != null) return false;
        if (idResep != null ? !idResep.equals(that.idResep) : that.idResep != null) return false;
        if (flagPickup != null ? !flagPickup.equals(that.flagPickup) : that.flagPickup != null) return false;
        if (flagDiterimaPasien != null ? !flagDiterimaPasien.equals(that.flagDiterimaPasien) : that.flagDiterimaPasien != null)
            return false;
        if (idPasien != null ? !idPasien.equals(that.idPasien) : that.idPasien != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (idPelayanan != null ? !idPelayanan.equals(that.idPelayanan) : that.idPelayanan != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idKurir != null ? idKurir.hashCode() : 0);
        result = 31 * result + (idResep != null ? idResep.hashCode() : 0);
        result = 31 * result + (flagPickup != null ? flagPickup.hashCode() : 0);
        result = 31 * result + (flagDiterimaPasien != null ? flagDiterimaPasien.hashCode() : 0);
        result = 31 * result + (idPasien != null ? idPasien.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (idPelayanan != null ? idPelayanan.hashCode() : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
