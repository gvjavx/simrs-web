package com.neurix.authorization.role.model;

import com.neurix.authorization.function.model.ImFunctions;
import com.neurix.authorization.user.model.ImUsers;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class ImRoles implements Serializable {
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    private Set<ImUsers> imUser=new HashSet<ImUsers>(0);

    public Set<ImUsers> getImUser() {
        return imUser;
    }

    public void setImUser(Set<ImUsers> imUser) {
        this.imUser = imUser;
    }

    private Set<ImFunctions> imFunction;

    public Set<ImFunctions> getImFunction() {
        return imFunction;
    }

    public void setImFunction(Set<ImFunctions> imFunction) {
        this.imFunction = imFunction;
    }

    private String tipePelayanan;

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImRoles)) return false;

        ImRoles imRoles = (ImRoles) o;

        if (action != null ? !action.equals(imRoles.action) : imRoles.action != null) return false;
        if (flag != null ? !flag.equals(imRoles.flag) : imRoles.flag != null) return false;
        if (createdDate != null ? !createdDate.equals(imRoles.createdDate) : imRoles.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(imRoles.createdWho) : imRoles.createdWho != null) return false;
        if (imFunction != null ? !imFunction.equals(imRoles.imFunction) : imRoles.imFunction != null) return false;
        if (imUser != null ? !imUser.equals(imRoles.imUser) : imRoles.imUser != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(imRoles.lastUpdate) : imRoles.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(imRoles.lastUpdateWho) : imRoles.lastUpdateWho != null)
            return false;
        if (roleId != null ? !roleId.equals(imRoles.roleId) : imRoles.roleId != null) return false;
        if (roleName != null ? !roleName.equals(imRoles.roleName) : imRoles.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImRoles{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", action='" + action + '\'' +
                ", flag='" + flag + '\'' +
                ", imUser=" + imUser +
                ", imFunction=" + imFunction +
                '}';
    }
}
