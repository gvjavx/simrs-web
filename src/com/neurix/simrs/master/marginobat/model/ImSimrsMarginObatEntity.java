package com.neurix.simrs.master.marginobat.model;

import java.sql.Timestamp;

/**
 * Created by reza on 28/09/20.
 */
public class ImSimrsMarginObatEntity {
    private String idMarginObat;
    private String idObat;
    private Integer standarMargin;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdMarginObat() {
        return idMarginObat;
    }

    public void setIdMarginObat(String idMarginObat) {
        this.idMarginObat = idMarginObat;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public Integer getStandarMargin() {
        return standarMargin;
    }

    public void setStandarMargin(Integer standarMargin) {
        this.standarMargin = standarMargin;
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

    public String getCreatedDateWho() {
        return createdDateWho;
    }

    public void setCreatedDateWho(String createdDateWho) {
        this.createdDateWho = createdDateWho;
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

        ImSimrsMarginObatEntity that = (ImSimrsMarginObatEntity) o;

        if (idMarginObat != null ? !idMarginObat.equals(that.idMarginObat) : that.idMarginObat != null) return false;
        if (idObat != null ? !idObat.equals(that.idObat) : that.idObat != null) return false;
        if (standarMargin != null ? !standarMargin.equals(that.standarMargin) : that.standarMargin != null)
            return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdDateWho != null ? !createdDateWho.equals(that.createdDateWho) : that.createdDateWho != null)
            return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMarginObat != null ? idMarginObat.hashCode() : 0;
        result = 31 * result + (idObat != null ? idObat.hashCode() : 0);
        result = 31 * result + (standarMargin != null ? standarMargin.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdDateWho != null ? createdDateWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
