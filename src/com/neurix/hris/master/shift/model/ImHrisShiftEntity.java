package com.neurix.hris.master.shift.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 22/03/2018.
 */
public class ImHrisShiftEntity implements Serializable {
    private String shiftId;
    private String shiftName;
    private String jamAwal;
    private String jamAkhir;
    private Timestamp createdDate;
    private String createDateWho;
    private java.sql.Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String IdBranch;
    private String kelompokPositionId;

    public String getKelompokPositionId() {
        return kelompokPositionId;
    }

    public void setKelompokPositionId(String kelompokPositionId) {
        this.kelompokPositionId = kelompokPositionId;
    }

    public String getIdBranch() {
        return IdBranch;
    }

    public void setIdBranch(String idBranch) {
        IdBranch = idBranch;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateDateWho() {
        return createDateWho;
    }

    public void setCreateDateWho(String createDateWho) {
        this.createDateWho = createDateWho;
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
}
