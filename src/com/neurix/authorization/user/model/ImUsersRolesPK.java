package com.neurix.authorization.user.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Ferdi on 09/02/2015.
 */
public class ImUsersRolesPK implements Serializable {
    private String userId;
    private Long roleId;

    @Column(name = "user_id", nullable = false, insertable = true, updatable = true, length = 100)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

        ImUsersRolesPK that = (ImUsersRolesPK) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
