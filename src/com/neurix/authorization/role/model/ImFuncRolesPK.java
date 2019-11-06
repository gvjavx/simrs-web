package com.neurix.authorization.role.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Ferdi on 26/01/2015.
 */
public class ImFuncRolesPK implements Serializable {
    private Long funcId;
    private Long roleId;

    @Column(name = "func_id", nullable = false, insertable = true, updatable = true)
    @Id
    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    @Column(name = "role_id", nullable = false, insertable = true, updatable = true)
    @Id
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImFuncRolesPK that = (ImFuncRolesPK) o;

        if (funcId != null ? !funcId.equals(that.funcId) : that.funcId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = funcId != null ? funcId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
