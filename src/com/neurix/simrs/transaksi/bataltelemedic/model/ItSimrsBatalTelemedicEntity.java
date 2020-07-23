package com.neurix.simrs.transaksi.bataltelemedic.model;

import java.sql.Timestamp;

/**
 * Created by reza on 23/07/20.
 */
public class ItSimrsBatalTelemedicEntity {
    private String id;
    private String idAntrianTelemedic;
    private String idDokterBatal;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String noJurnal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAntrianTelemedic() {
        return idAntrianTelemedic;
    }

    public void setIdAntrianTelemedic(String idAntrianTelemedic) {
        this.idAntrianTelemedic = idAntrianTelemedic;
    }

    public String getIdDokterBatal() {
        return idDokterBatal;
    }

    public void setIdDokterBatal(String idDokterBatal) {
        this.idDokterBatal = idDokterBatal;
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

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItSimrsBatalTelemedicEntity that = (ItSimrsBatalTelemedicEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idAntrianTelemedic != null ? !idAntrianTelemedic.equals(that.idAntrianTelemedic) : that.idAntrianTelemedic != null)
            return false;
        if (idDokterBatal != null ? !idDokterBatal.equals(that.idDokterBatal) : that.idDokterBatal != null)
            return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (noJurnal != null ? !noJurnal.equals(that.noJurnal) : that.noJurnal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idAntrianTelemedic != null ? idAntrianTelemedic.hashCode() : 0);
        result = 31 * result + (idDokterBatal != null ? idDokterBatal.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (noJurnal != null ? noJurnal.hashCode() : 0);
        return result;
    }
}
