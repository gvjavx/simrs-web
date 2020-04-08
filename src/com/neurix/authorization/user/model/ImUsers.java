package com.neurix.authorization.user.model;

import com.neurix.authorization.company.model.ImAreasBranchesUsers;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.role.model.ImRoles;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class ImUsers implements Serializable {
    private ImUsersPK primaryKey;

    public ImUsersPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ImUsersPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    private String divisiName;

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    private String divisiId;

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    private ItPersonilPositionEntity itPersonilPositionEntity ;

    public ItPersonilPositionEntity getItPersonilPositionEntity() {
        return itPersonilPositionEntity;
    }

    public void setItPersonilPositionEntity(ItPersonilPositionEntity itPersonilPositionEntity) {
        this.itPersonilPositionEntity = itPersonilPositionEntity;
    }

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

    private ImDepartmentEntity imDepartmentEntity ;

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    private ImPosition imPosition;

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    private Set<ImAreasBranchesUsers> imAreasBranchesUsers;

    public Set<ImAreasBranchesUsers> getImAreasBranchesUsers() {
        return imAreasBranchesUsers;
    }

    public void setImAreasBranchesUsers(Set<ImAreasBranchesUsers> imAreasBranchesUsers) {
        this.imAreasBranchesUsers = imAreasBranchesUsers;
    }

    private Set<ImRoles> imRoles = new HashSet<ImRoles>(0);

    public Set<ImRoles> getImRoles() {
        return imRoles;
    }

    public void setImRoles(Set<ImRoles> imRoles) {
        this.imRoles = imRoles;
    }

    private String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    private String positionId;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    private String idPelayanan;

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    private String idDevice;

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    private ImBiodataEntity imBiodataEntity;

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImUsers)) return false;

        ImUsers imUsers = (ImUsers) o;

        if (action != null ? !action.equals(imUsers.action) : imUsers.action != null) return false;
        if (flag != null ? !flag.equals(imUsers.flag) : imUsers.flag != null) return false;
        if (createdDate != null ? !createdDate.equals(imUsers.createdDate) : imUsers.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(imUsers.createdWho) : imUsers.createdWho != null) return false;
        if (email != null ? !email.equals(imUsers.email) : imUsers.email != null) return false;
        if (imAreasBranchesUsers != null ? !imAreasBranchesUsers.equals(imUsers.imAreasBranchesUsers) : imUsers.imAreasBranchesUsers != null) return false;
        if (imPosition != null ? !imPosition.equals(imUsers.imPosition) : imUsers.imPosition != null) return false;
        if (imRoles != null ? !imRoles.equals(imUsers.imRoles) : imUsers.imRoles != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(imUsers.lastUpdate) : imUsers.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(imUsers.lastUpdateWho) : imUsers.lastUpdateWho != null)
            return false;
        if (password != null ? !password.equals(imUsers.password) : imUsers.password != null) return false;
        if (primaryKey != null ? !primaryKey.equals(imUsers.primaryKey) : imUsers.primaryKey != null) return false;
        if (userName != null ? !userName.equals(imUsers.userName) : imUsers.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryKey != null ? primaryKey.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImUsers{" +
                "primaryKey=" + primaryKey +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", action='" + action + '\'' +
                ", flag='" + flag + '\'' +
                ", imPosition=" + imPosition +
                ", imAreasBranchesUsers=" + imAreasBranchesUsers +
                ", imRoles=" + imRoles +
                '}';
    }
}
