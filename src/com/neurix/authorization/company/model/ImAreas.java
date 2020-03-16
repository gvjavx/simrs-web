package com.neurix.authorization.company.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 5:16
 * To change this template use File | Settings | File Templates.
 */
public class ImAreas implements Serializable {

    private String areaId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    private ImAreasPK primaryKey;

    public ImAreasPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ImAreasPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    private Set<ImAreasBranchesUsers> imAreasBranchesUsers;

    public Set<ImAreasBranchesUsers> getImAreasBranchesUsers() {
        return imAreasBranchesUsers;
    }

    public void setImAreasBranchesUsers(Set<ImAreasBranchesUsers> imAreasBranchesUsers) {
        this.imAreasBranchesUsers = imAreasBranchesUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImAreas)) return false;

        ImAreas imAreas = (ImAreas) o;

        if (action != null ? !action.equals(imAreas.action) : imAreas.action != null) return false;
        if (areaName != null ? !areaName.equals(imAreas.areaName) : imAreas.areaName != null) return false;
        if (createdDate != null ? !createdDate.equals(imAreas.createdDate) : imAreas.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(imAreas.createdWho) : imAreas.createdWho != null) return false;
        if (flag != null ? !flag.equals(imAreas.flag) : imAreas.flag != null) return false;
        if (imAreasBranchesUsers != null ? !imAreasBranchesUsers.equals(imAreas.imAreasBranchesUsers) : imAreas.imAreasBranchesUsers != null)
            return false;
        if (lastUpdate != null ? !lastUpdate.equals(imAreas.lastUpdate) : imAreas.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(imAreas.lastUpdateWho) : imAreas.lastUpdateWho != null)
            return false;
        if (primaryKey != null ? !primaryKey.equals(imAreas.primaryKey) : imAreas.primaryKey != null) return false;

        return true;
    }

}