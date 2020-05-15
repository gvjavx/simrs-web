package com.neurix.authorization.position.model;

import com.neurix.authorization.user.model.ImUsers;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianPK;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class ImPosition implements Serializable {

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

    private ImDepartmentEntity imDepartmentEntity;
    private ImKelompokPositionEntity imKelompokPositionEntity;
    private ImPositionBagianEntity imPositionBagianEntity;

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
    }

    public ImPositionBagianEntity getImPositionBagianEntity() {
        return imPositionBagianEntity;
    }

    public void setImPositionBagianEntity(ImPositionBagianEntity imPositionBagianEntity) {
        this.imPositionBagianEntity = imPositionBagianEntity;
    }

    public String getFlagDijabatSatuOrang() {
        return flagDijabatSatuOrang;
    }

    public void setFlagDijabatSatuOrang(String flagDijabatSatuOrang) {
        this.flagDijabatSatuOrang = flagDijabatSatuOrang;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public ImKelompokPositionEntity getImKelompokPositionEntity() {
        return imKelompokPositionEntity;
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

    public void setImKelompokPositionEntity(ImKelompokPositionEntity imKelompokPositionEntity) {
        this.imKelompokPositionEntity = imKelompokPositionEntity;
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

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    private String positionName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    private Timestamp createdDate;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    private String createdWho;

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    private Timestamp lastUpdate;

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String lastUpdateWho;

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStPositionId() {
        return stPositionId;
    }

    public void setStPositionId(String stPositionId) {
        this.stPositionId = stPositionId;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    private Set<ImUsers> imUserses;

    public Set<ImUsers> getImUserses() {
        return imUserses;
    }

    public void setImUserses(Set<ImUsers> imUserses) {
        this.imUserses = imUserses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImPosition)) return false;

        ImPosition that = (ImPosition) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (imUserses != null ? !imUserses.equals(that.imUserses) : that.imUserses != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (positionName != null ? !positionName.equals(that.positionName) : that.positionName != null) return false;
        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = positionId != null ? positionId.hashCode() : 0;
        result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (imUserses != null ? imUserses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImPosition{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", kelompokId='" + kelompokId + '\'' +
                ", kelompokName='" + kelompokName + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", action='" + action + '\'' +
                ", flag='" + flag + '\'' +
                ", imUserses=" + imUserses +
                '}';
    }
}
