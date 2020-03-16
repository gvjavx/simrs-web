package com.neurix.authorization.company.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class ImAreasBranchesUsersPK implements Serializable {

    private String areaId;
    private String branchId;
    private String userId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImAreasBranchesUsersPK)) return false;

        ImAreasBranchesUsersPK that = (ImAreasBranchesUsersPK) o;

        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId != null ? areaId.hashCode() : 0;
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImAreasBranchesUsersPK{" +
                "areaId='" + areaId + '\'' +
                ", branchId='" + branchId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
