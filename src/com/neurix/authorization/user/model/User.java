package com.neurix.authorization.user.model;

import com.neurix.common.displaytag.DisplayObject;
import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Ferdi on 14/02/2015.
 */
public class User extends BaseModel implements Serializable, Comparable<User> {

    private String userId;
    private String divisiId;
    private String divisiName;
    private String username;
    private String positionId;
    private String positionName;
    private String password;
    private String photoUserUrl;
    private DisplayObject displayObjectCheck;
    private String email;
    private String previewPhoto;
    private String confirmPassword;
    private String roleId;
    private String roleName;
    private byte[] contentFile;

    private String areaId;
    private String branchId;
    private String lokasiKebun;
    private String areaName;
    private String branchName;
    private String idPelayanan;
    private String idDevice;
    private String noMaster;

    private String logoBranch;

    private String departmentId;

    private String idRuangan;

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getLogoBranch() {
        return logoBranch;
    }

    public void setLogoBranch(String logoBranch) {
        this.logoBranch = logoBranch;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getLokasiKebun() {
        return lokasiKebun;
    }

    public void setLokasiKebun(String lokasiKebun) {
        this.lokasiKebun = lokasiKebun;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhotoUserUrl() {
        return photoUserUrl;
    }

    public void setPhotoUserUrl(String photoUserUrl) {
        this.photoUserUrl = photoUserUrl;
    }

    public byte[] getContentFile() {
        return contentFile;
    }

    public void setContentFile(byte[] contentFile) {
        this.contentFile = contentFile;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPreviewPhoto() {
        return previewPhoto;
    }

    public void setPreviewPhoto(String previewPhoto) {
        this.previewPhoto = previewPhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DisplayObject getDisplayObjectCheck() {
        return displayObjectCheck;
    }

    public void setDisplayObjectCheck(DisplayObject displayObjectCheck) {
        this.displayObjectCheck = displayObjectCheck;
    }

    public String getNoMaster() {
        return noMaster;
    }

    public void setNoMaster(String noMaster) {
        this.noMaster = noMaster;
    }

    @Override
    public int compareTo(User o) {
        return this.username.toLowerCase().compareTo(o.getUsername().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (branchId != null ? !branchId.equals(user.branchId) : user.branchId != null) return false;
        if (confirmPassword != null ? !confirmPassword.equals(user.confirmPassword) : user.confirmPassword != null)
            return false;
        if (!Arrays.equals(contentFile, user.contentFile)) return false;
        if (displayObjectCheck != null ? !displayObjectCheck.equals(user.displayObjectCheck) : user.displayObjectCheck != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (lokasiKebun != null ? !lokasiKebun.equals(user.lokasiKebun) : user.lokasiKebun != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (photoUserUrl != null ? !photoUserUrl.equals(user.photoUserUrl) : user.photoUserUrl != null) return false;
        if (positionId != null ? !positionId.equals(user.positionId) : user.positionId != null) return false;
        if (positionName != null ? !positionName.equals(user.positionName) : user.positionName != null) return false;
        if (previewPhoto != null ? !previewPhoto.equals(user.previewPhoto) : user.previewPhoto != null) return false;
        if (roleId != null ? !roleId.equals(user.roleId) : user.roleId != null) return false;
        if (roleName != null ? !roleName.equals(user.roleName) : user.roleName != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (photoUserUrl != null ? photoUserUrl.hashCode() : 0);
        result = 31 * result + (displayObjectCheck != null ? displayObjectCheck.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (previewPhoto != null ? previewPhoto.hashCode() : 0);
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (contentFile != null ? Arrays.hashCode(contentFile) : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (lokasiKebun != null ? lokasiKebun.hashCode() : 0);
        return result;
    }
}
