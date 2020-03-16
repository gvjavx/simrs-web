
package com.neurix.authorization.position.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by Ferdi on 02/02/2015.
 */
public class Position extends BaseModel implements Serializable, Comparable<Position> {
    private String branchId;
    private String positionId;
    private String positionName;
    private String departmentName;
    private String stPositionId;
    private String departmentId;
    private String kelompokId;
    private String bagianId;
    private String kelompokName;
    private String bagianName;
    private String flagDijabatSatuOrang;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStPositionId() {
        return stPositionId;
    }

    public void setStPositionId(String stPositionId) {
        this.stPositionId = stPositionId;
    }

    @Override
    public int compareTo(Position o) {
        return this.positionName.toLowerCase().compareTo(o.getPositionName().toLowerCase());
    }
}
