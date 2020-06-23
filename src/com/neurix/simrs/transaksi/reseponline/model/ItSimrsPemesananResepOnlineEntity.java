package com.neurix.simrs.transaksi.reseponline.model;

import java.sql.Timestamp;

/**
 * Created by reza on 22/06/20.
 */
public class ItSimrsPemesananResepOnlineEntity {
    private String id;
    private String idPasien;
    private String urlFotoResep;
    private String idResep;
    private String idApoteker;
    private String flag;
    private Integer action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idTransaksiOnline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getUrlFotoResep() {
        return urlFotoResep;
    }

    public void setUrlFotoResep(String urlFotoResep) {
        this.urlFotoResep = urlFotoResep;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public String getIdApoteker() {
        return idApoteker;
    }

    public void setIdApoteker(String idApoteker) {
        this.idApoteker = idApoteker;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
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

    public String getIdTransaksiOnline() {
        return idTransaksiOnline;
    }

    public void setIdTransaksiOnline(String idTransaksiOnline) {
        this.idTransaksiOnline = idTransaksiOnline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItSimrsPemesananResepOnlineEntity that = (ItSimrsPemesananResepOnlineEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idPasien != null ? !idPasien.equals(that.idPasien) : that.idPasien != null) return false;
        if (urlFotoResep != null ? !urlFotoResep.equals(that.urlFotoResep) : that.urlFotoResep != null) return false;
        if (idResep != null ? !idResep.equals(that.idResep) : that.idResep != null) return false;
        if (idApoteker != null ? !idApoteker.equals(that.idApoteker) : that.idApoteker != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (idTransaksiOnline != null ? !idTransaksiOnline.equals(that.idTransaksiOnline) : that.idTransaksiOnline != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idPasien != null ? idPasien.hashCode() : 0);
        result = 31 * result + (urlFotoResep != null ? urlFotoResep.hashCode() : 0);
        result = 31 * result + (idResep != null ? idResep.hashCode() : 0);
        result = 31 * result + (idApoteker != null ? idApoteker.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (idTransaksiOnline != null ? idTransaksiOnline.hashCode() : 0);
        return result;
    }
}
