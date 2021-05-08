package com.neurix.authorization.user.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Ferdi on 09/02/2015.
 */
@Entity
@Table(name = "im_users_roles", schema = "public")
@IdClass(ImUsersRolesPK.class)
public class ImUsersRoles implements Serializable {
    private ImUsersRolesPK primaryKey;
    private String flag;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public ImUsersRolesPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ImUsersRolesPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Basic
    @Column(name = "flag", nullable = true, insertable = true, updatable = true, length = 1)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "created_date", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "last_update", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Basic
    @Column(name = "created_who", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Basic
    @Column(name = "last_update_who", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImUsersRoles)) return false;

        ImUsersRoles that = (ImUsersRoles) o;

        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null)
            return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryKey != null ? primaryKey.hashCode() : 0;
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
