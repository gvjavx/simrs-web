package com.neurix.authorization.company.model;

import com.neurix.authorization.user.model.User;
import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class AreaBranchUser extends BaseModel implements Serializable {
    private String areaId;
    private String branchId;
    private String userId;
    private String areaName;
    private String branchName;
    private String userName;
    private List<User> listOfUser;

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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<User> getListOfUser() {
        return listOfUser;
    }

    public void setListOfUser(List<User> listOfUser) {
        this.listOfUser = listOfUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaBranchUser)) return false;

        AreaBranchUser that = (AreaBranchUser) o;

        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (areaName != null ? !areaName.equals(that.areaName) : that.areaName != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (branchName != null ? !branchName.equals(that.branchName) : that.branchName != null) return false;
        if (listOfUser != null ? !listOfUser.equals(that.listOfUser) : that.listOfUser != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId != null ? areaId.hashCode() : 0;
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        result = 31 * result + (branchName != null ? branchName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (listOfUser != null ? listOfUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AreaBranchUser{" +
                "areaId='" + areaId + '\'' +
                ", branchId='" + branchId + '\'' +
                ", userId='" + userId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", userName='" + userName + '\'' +
                ", listOfUser=" + listOfUser +
                '}';
    }
}
