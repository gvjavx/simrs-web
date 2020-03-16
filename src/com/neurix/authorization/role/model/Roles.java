package com.neurix.authorization.role.model;

import com.neurix.authorization.function.model.Functions;
import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class Roles extends BaseModel implements Serializable, Comparable<Roles> {
    private Long roleId;
    private String roleName;
    private String stRoleId;

    //to get all url per function, for security reason --> filter url access
    private List<Functions> listFunctions;

    public Roles() {}

    public Roles(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public String getStRoleId() {
        return stRoleId;
    }

    public void setStRoleId(String stRoleId) {
        this.stRoleId = stRoleId;
    }

    public List<Functions> getListFunctions() {
        return listFunctions;
    }

    public void setListFunctions(List<Functions> listFunctions) {
        this.listFunctions = listFunctions;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int compareTo(Roles o) {
        return this.roleName.toLowerCase().compareTo(o.getRoleName().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;

        Roles roles = (Roles) o;

        if (listFunctions != null ? !listFunctions.equals(roles.listFunctions) : roles.listFunctions != null)
            return false;
        if (roleId != null ? !roleId.equals(roles.roleId) : roles.roleId != null) return false;
        if (roleName != null ? !roleName.equals(roles.roleName) : roles.roleName != null) return false;
        if (stRoleId != null ? !stRoleId.equals(roles.stRoleId) : roles.stRoleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (stRoleId != null ? stRoleId.hashCode() : 0);
        result = 31 * result + (listFunctions != null ? listFunctions.hashCode() : 0);
        return result;
    }
}
