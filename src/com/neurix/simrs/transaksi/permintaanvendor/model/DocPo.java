package com.neurix.simrs.transaksi.permintaanvendor.model;

import java.sql.Timestamp;

/**
 * Created by reza on 03/09/20.
 */
public class DocPo {
    private String id;
    private String idItem;
    private String idPermintaanObatVendor;
    private String jenisNomor;
    private String tipe;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String urlImg;
    private Integer noBatch;

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getIdPermintaanObatVendor() {
        return idPermintaanObatVendor;
    }

    public void setIdPermintaanObatVendor(String idPermintaanObatVendor) {
        this.idPermintaanObatVendor = idPermintaanObatVendor;
    }

    public String getJenisNomor() {
        return jenisNomor;
    }

    public void setJenisNomor(String jenisNomor) {
        this.jenisNomor = jenisNomor;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

        DocPo that = (DocPo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idItem != null ? !idItem.equals(that.idItem) : that.idItem != null) return false;
        if (idPermintaanObatVendor != null ? !idPermintaanObatVendor.equals(that.idPermintaanObatVendor) : that.idPermintaanObatVendor != null)
            return false;
        if (jenisNomor != null ? !jenisNomor.equals(that.jenisNomor) : that.jenisNomor != null) return false;
        if (tipe != null ? !tipe.equals(that.tipe) : that.tipe != null) return false;
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
        result = 31 * result + (idItem != null ? idItem.hashCode() : 0);
        result = 31 * result + (idPermintaanObatVendor != null ? idPermintaanObatVendor.hashCode() : 0);
        result = 31 * result + (jenisNomor != null ? jenisNomor.hashCode() : 0);
        result = 31 * result + (tipe != null ? tipe.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
