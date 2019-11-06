package com.neurix.authorization.company.model;

import com.neurix.authorization.user.model.ImUsers;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class ImAreasBranchesUsers implements Serializable {
    private ImAreasBranchesUsersPK primaryKey;

    public ImAreasBranchesUsersPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ImAreasBranchesUsersPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    private ImUsers imUser;

    public ImUsers getImUser() {
        return imUser;
    }

    public void setImUser(ImUsers imUser) {
        this.imUser = imUser;
    }

    private ImBranches imBranch;

    public ImBranches getImBranch() {
        return imBranch;
    }

    public void setImBranch(ImBranches imBranch) {
        this.imBranch = imBranch;
    }

    private ImAreas imArea;

    public ImAreas getImArea() {
        return imArea;
    }

    public void setImArea(ImAreas imArea) {
        this.imArea = imArea;
    }

    private ImCompany imCompany;

    public ImCompany getImCompany() {
        return imCompany;
    }

    public void setImCompany(ImCompany imCompany) {
        this.imCompany = imCompany;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImAreasBranchesUsers)) return false;

        ImAreasBranchesUsers that = (ImAreasBranchesUsers) o;

        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (imArea != null ? !imArea.equals(that.imArea) : that.imArea != null) return false;
        if (imBranch != null ? !imBranch.equals(that.imBranch) : that.imBranch != null) return false;
        if (imCompany != null ? !imCompany.equals(that.imCompany) : that.imCompany != null) return false;
        if (imUser != null ? !imUser.equals(that.imUser) : that.imUser != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        int result = primaryKey != null ? primaryKey.hashCode() : 0;
//        result = 31 * result + (imUser != null ? imUser.hashCode() : 0);
//        result = 31 * result + (imBranch != null ? imBranch.hashCode() : 0);
//        result = 31 * result + (imArea != null ? imArea.hashCode() : 0);
//        result = 31 * result + (imCompany != null ? imCompany.hashCode() : 0);
//        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
//        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
//        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
//        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
//        result = 31 * result + (flag != null ? flag.hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return "ImAreasBranchesUsers{" +
                "primaryKey=" + primaryKey +
                ", imUser=" + imUser +
                ", imBranch=" + imBranch +
                ", imArea=" + imArea +
                ", imCompany=" + imCompany +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

}
