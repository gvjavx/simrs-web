package com.neurix.simrs.master.tindakan.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 13/08/20.
 */
public class ImSimrsHeaderTindakanEntity {
    private String id;
    private String namaTindakan;
    private String idKategoriTindakan;
    private BigInteger standardCost;
    private BigInteger diskon;
    private BigInteger hargaDiskon;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
    }

    public String getIdKategoriTindakan() {
        return idKategoriTindakan;
    }

    public void setIdKategoriTindakan(String idKategoriTindakan) {
        this.idKategoriTindakan = idKategoriTindakan;
    }

    public BigInteger getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigInteger standardCost) {
        this.standardCost = standardCost;
    }

    public BigInteger getDiskon() {
        return diskon;
    }

    public void setDiskon(BigInteger diskon) {
        this.diskon = diskon;
    }

    public BigInteger getHargaDiskon() {
        return hargaDiskon;
    }

    public void setHargaDiskon(BigInteger hargaDiskon) {
        this.hargaDiskon = hargaDiskon;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImSimrsHeaderTindakanEntity that = (ImSimrsHeaderTindakanEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (namaTindakan != null ? !namaTindakan.equals(that.namaTindakan) : that.namaTindakan != null) return false;
        if (idKategoriTindakan != null ? !idKategoriTindakan.equals(that.idKategoriTindakan) : that.idKategoriTindakan != null)
            return false;
        if (standardCost != null ? !standardCost.equals(that.standardCost) : that.standardCost != null) return false;
        if (diskon != null ? !diskon.equals(that.diskon) : that.diskon != null) return false;
        if (hargaDiskon != null ? !hargaDiskon.equals(that.hargaDiskon) : that.hargaDiskon != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
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
        result = 31 * result + (namaTindakan != null ? namaTindakan.hashCode() : 0);
        result = 31 * result + (idKategoriTindakan != null ? idKategoriTindakan.hashCode() : 0);
        result = 31 * result + (standardCost != null ? standardCost.hashCode() : 0);
        result = 31 * result + (diskon != null ? diskon.hashCode() : 0);
        result = 31 * result + (hargaDiskon != null ? hargaDiskon.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
