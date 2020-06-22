package com.neurix.simrs.master.askep.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsDiagnosaAsuhanKeperawatanEntity {

    private String idDiagnosaAsuhanKeperawatan;
    private String diagnosa;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDiagnosaAsuhanKeperawatan() {
        return idDiagnosaAsuhanKeperawatan;
    }

    public void setIdDiagnosaAsuhanKeperawatan(String idDiagnosaAsuhanKeperawatan) {
        this.idDiagnosaAsuhanKeperawatan = idDiagnosaAsuhanKeperawatan;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
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
        ImSimrsDiagnosaAsuhanKeperawatanEntity that = (ImSimrsDiagnosaAsuhanKeperawatanEntity) o;
        return Objects.equals(idDiagnosaAsuhanKeperawatan, that.idDiagnosaAsuhanKeperawatan) &&
                Objects.equals(diagnosa, that.diagnosa) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiagnosaAsuhanKeperawatan, diagnosa, flag, action, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
