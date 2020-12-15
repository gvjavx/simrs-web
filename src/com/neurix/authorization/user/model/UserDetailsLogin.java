package com.neurix.authorization.user.model;

import com.neurix.authorization.role.model.Roles;
import com.neurix.common.displaytag.DisplayObject;
import com.neurix.common.model.BaseModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsLogin implements UserDetails, Serializable {

    private String userId;
    private String username;
    private String companyId;
    private String companyName;
    private String areaId;
    private String areaName;
    private String branchId;
    private String branchName;
    private String divisiId;
    private String divisiName;
    private String positionId;
    private String positionName;
    private String password;
    private String nip;
    private String statusCaption;
    private String photoUpload;
    private List<Roles> roles;
    private List<String> menus;
    private String photoUserUrl;
    private String jenisKelamin;
    private boolean isNonExpired = false;
    private boolean isNonBlocked = false;
    private boolean isUserCredentialsNonExpired = false;
    private boolean isEnabled = false;
    private String flagFingerMoblie;
    private String bagianId;
    private String bagianName;

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

    public String getFlagFingerMoblie() {
        return flagFingerMoblie;
    }

    public void setFlagFingerMoblie(String flagFingerMoblie) {
        this.flagFingerMoblie = flagFingerMoblie;
    }

    //set customer id, customer name, npwp, and address -> for user payment gateway
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerNPWP;
    private String customerEmail;

    private String idPleyanan;
    private String idDevice;
    private String idRuangan;
    private String idVendor;
    private String pin;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isNonExpired() {
        return isNonExpired;
    }

    public boolean isNonBlocked() {
        return isNonBlocked;
    }

    public boolean isUserCredentialsNonExpired() {
        return isUserCredentialsNonExpired;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getIdPleyanan() {
        return idPleyanan;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public void setIdPleyanan(String idPleyanan) {
        this.idPleyanan = idPleyanan;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getPhotoUpload() {
        return photoUpload;
    }

    public void setPhotoUpload(String photoUpload) {
        this.photoUpload = photoUpload;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getStatusCaption() {
        return statusCaption;
    }

    public void setStatusCaption(String statusCaption) {
        this.statusCaption = statusCaption;
    }

    //set username detail, updated 30-04-2016
    private String userNameDetail;

    public String getUserNameDetail() {
        return userNameDetail;
    }

    public void setUserNameDetail(String userNameDetail) {
        this.userNameDetail = userNameDetail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerNPWP() {
        return customerNPWP;
    }

    public void setCustomerNPWP(String customerNPWP) {
        this.customerNPWP = customerNPWP;
    }

    public UserDetailsLogin() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public void setNonExpired(boolean nonExpired) {
        isNonExpired = nonExpired;
    }

    public void setNonBlocked(boolean nonBlocked) {
        isNonBlocked = nonBlocked;
    }

    public void setUserCredentialsNonExpired(boolean userCredentialsNonExpired) {
        isUserCredentialsNonExpired = userCredentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Collection<GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> listAuthority = new ArrayList();

        for (Roles roleUser : this.roles) {
            String roleName = roleUser.getRoleName();
            if (roleName != null) listAuthority.add(new GrantedAuthorityImpl(roleName));
        }

        return listAuthority;
    }

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public String getUserId() {
        return userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return isNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isNonBlocked;
    }

    public boolean isCredentialsNonExpired() {
        return isUserCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
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

    public String getPhotoUserUrl() {
        return photoUserUrl;
    }

    public void setPhotoUserUrl(String photoUserUrl) {
        this.photoUserUrl = photoUserUrl;
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
        if (!(o instanceof UserDetailsLogin)) return false;

        UserDetailsLogin that = (UserDetailsLogin) o;

        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDetailsLogin{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", isNonExpired=" + isNonExpired +
                ", isNonBlocked=" + isNonBlocked +
                ", isUserCredentialsNonExpired=" + isUserCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                ", photoUserUrl=" + photoUserUrl +
                ", menus = " + menus +
                '}';
    }
}
