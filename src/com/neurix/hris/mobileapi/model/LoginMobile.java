package com.neurix.hris.mobileapi.model;

import java.io.Serializable;
import java.util.Objects;

public class LoginMobile implements Serializable {
    private String userId;
    private String userName;
    private String positionId;
    private String positionName;
    private String password;
    private String roleId;
    private String roleName;
    private String areaId;
    private String areaName;
    private String branchId;
    private String branchName;
    private String tokenId;
    private String actionMessage;
    private String jenisKelamin;

    public LoginMobile() {
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginMobile)) return false;
        LoginMobile that = (LoginMobile) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getPositionId(), that.getPositionId()) &&
                Objects.equals(getPositionName(), that.getPositionName()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getRoleId(), that.getRoleId()) &&
                Objects.equals(getRoleName(), that.getRoleName()) &&
                Objects.equals(getAreaId(), that.getAreaId()) &&
                Objects.equals(getAreaName(), that.getAreaName()) &&
                Objects.equals(getBranchId(), that.getBranchId()) &&
                Objects.equals(getBranchName(), that.getBranchName()) &&
                Objects.equals(getTokenId(), that.getTokenId()) &&
                Objects.equals(getActionMessage(), that.getActionMessage()) &&
                Objects.equals(getJenisKelamin(), that.getJenisKelamin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getPositionId(), getPositionName(), getPassword(), getRoleId(), getRoleName(), getAreaId(), getAreaName(), getBranchId(), getBranchName(), getTokenId(), getActionMessage(), getJenisKelamin());
    }

    @Override
    public String toString() {
        return "LoginMobile{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", actionMessage='" + actionMessage + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                '}';
    }
}
