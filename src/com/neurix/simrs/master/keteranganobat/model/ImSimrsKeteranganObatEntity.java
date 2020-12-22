package com.neurix.simrs.master.keteranganobat.model;

import java.sql.Timestamp;

public class ImSimrsKeteranganObatEntity {
    private String id;
    private String idSubJenis;
    private String idParameterKeterangan;
    private String keterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String warnaLabel;
    private String warnaBackground;

    public String getWarnaLabel() {
        return warnaLabel;
    }

    public void setWarnaLabel(String warnaLabel) {
        this.warnaLabel = warnaLabel;
    }

    public String getWarnaBackground() {
        return warnaBackground;
    }

    public void setWarnaBackground(String warnaBackground) {
        this.warnaBackground = warnaBackground;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSubJenis() {
        return idSubJenis;
    }

    public void setIdSubJenis(String idSubJenis) {
        this.idSubJenis = idSubJenis;
    }

    public String getIdParameterKeterangan() {
        return idParameterKeterangan;
    }

    public void setIdParameterKeterangan(String idParameterKeterangan) {
        this.idParameterKeterangan = idParameterKeterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

        ImSimrsKeteranganObatEntity that = (ImSimrsKeteranganObatEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idSubJenis != null ? !idSubJenis.equals(that.idSubJenis) : that.idSubJenis != null) return false;
        if (idParameterKeterangan != null ? !idParameterKeterangan.equals(that.idParameterKeterangan) : that.idParameterKeterangan != null)
            return false;
        if (keterangan != null ? !keterangan.equals(that.keterangan) : that.keterangan != null) return false;
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
        result = 31 * result + (idSubJenis != null ? idSubJenis.hashCode() : 0);
        result = 31 * result + (idParameterKeterangan != null ? idParameterKeterangan.hashCode() : 0);
        result = 31 * result + (keterangan != null ? keterangan.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
