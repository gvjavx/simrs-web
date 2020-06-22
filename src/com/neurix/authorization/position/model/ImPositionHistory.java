package com.neurix.authorization.position.model;
import com.neurix.common.model.TableHistory;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Ferdi on 03/10/2014.
 */

public class ImPositionHistory implements Serializable {
    private String positionId;
    private String departmentId;
    private String kelompokId;
    private String strataId;
    private String kelompokName;
    private String strataName;
    private String bagianId;
    private String flagDijabatSatuOrang;
    private String branchId;
    private String departmentName;
    private String stPositionId;
    private String bagianName;
    private String kodering;
    private String kategori;
    private String positionIdHistory;
    private String positionName;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFlagDijabatSatuOrang() {
        return flagDijabatSatuOrang;
    }

    public void setFlagDijabatSatuOrang(String flagDijabatSatuOrang) {
        this.flagDijabatSatuOrang = flagDijabatSatuOrang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getKelompokName() {
        return kelompokName;
    }

    public void setKelompokName(String kelompokName) {
        this.kelompokName = kelompokName;
    }

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionIdHistory() {
        return positionIdHistory;
    }

    public void setPositionIdHistory(String positionIdHistory) {
        this.positionIdHistory = positionIdHistory;
    }

    public String getStPositionId() {
        return stPositionId;
    }

    public void setStPositionId(String stPositionId) {
        this.stPositionId = stPositionId;
    }

    public String getStrataId() {
        return strataId;
    }

    public void setStrataId(String strataId) {
        this.strataId = strataId;
    }

    public String getStrataName() {
        return strataName;
    }

    public void setStrataName(String strataName) {
        this.strataName = strataName;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
//@Entity
//@Table(name = "im_position_history", schema = "public")
//public class ImPositionHistory extends TableHistory implements Serializable {
//
//    private String positionId;
//    private String positionName;
//    private Timestamp createdDate;
//    private String createdWho;
//    private Timestamp lastUpdate;
//    private String lastUpdateWho;
//    private String flag;
//    private String action;
//    private String positionIdHistory;
//
//
//    @Basic
//    @Column(name = "position_id", nullable = false, insertable = true, updatable = true)
//    public Long getPositionId() {
//        return positionId;
//    }
//
//    public void setPositionId(Long positionId) {
//        this.positionId = positionId;
//    }
//
//    @Basic
//    @Column(name = "position_name", nullable = true, insertable = true, updatable = true, length = 20)
//    public String getPositionName() {
//        return positionName;
//    }
//
//    public void setPositionName(String positionName) {
//        this.positionName = positionName;
//    }
//
//    @Basic
//    @Column(name = "created_date", nullable = true, insertable = true, updatable = true)
//    public Timestamp getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Timestamp createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    @Basic
//    @Column(name = "created_who", nullable = true, insertable = true, updatable = true, length = 100)
//    public String getCreatedWho() {
//        return createdWho;
//    }
//
//    public void setCreatedWho(String createdWho) {
//        this.createdWho = createdWho;
//    }
//
//    @Basic
//    @Column(name = "last_update", nullable = true, insertable = true, updatable = true)
//    public Timestamp getLastUpdate() {
//        return lastUpdate;
//    }
//
//    public void setLastUpdate(Timestamp lastUpdate) {
//        this.lastUpdate = lastUpdate;
//    }
//
//    @Basic
//    @Column(name = "last_update_who", nullable = true, insertable = true, updatable = true, length = 100)
//    public String getLastUpdateWho() {
//        return lastUpdateWho;
//    }
//
//    public void setLastUpdateWho(String lastUpdateWho) {
//        this.lastUpdateWho = lastUpdateWho;
//    }
//
//    @Basic
//    @Column(name = "flag", nullable = false, insertable = true, updatable = true, length = 1)
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
//
//    @Basic
//    @Column(name = "action", nullable = true, insertable = true, updatable = true, length = 1)
//    public String getAction() {
//        return action;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ImPositionHistory)) return false;
//
//        ImPositionHistory that = (ImPositionHistory) o;
//
//        if (action != null ? !action.equals(that.action) : that.action != null) return false;
//        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
//        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
//        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
//        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
//        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
//            return false;
//        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;
//        if (positionName != null ? !positionName.equals(that.positionName) : that.positionName != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = positionId != null ? positionId.hashCode() : 0;
//        result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
//        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
//        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
//        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
//        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
//        result = 31 * result + (flag != null ? flag.hashCode() : 0);
//        result = 31 * result + (action != null ? action.hashCode() : 0);
//        return result;
//    }
//}
