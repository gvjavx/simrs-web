package com.neurix.authorization.role.model;

import com.neurix.authorization.function.model.ImFunctions;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Ferdi on 26/01/2015.
 */
@Entity
@Table(name = "im_func_roles", schema = "public")
@IdClass(ImFuncRolesPK.class)
public class ImFuncRoles implements Serializable {
    private String flag;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private ImRoles imRoles;
    private ImFunctions imFunctions;

    private ImFuncRolesPK primaryKey;

    public ImFuncRolesPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ImFuncRolesPK primaryKey) {
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

    public ImRoles getImRoles() {
        return imRoles;
    }

    public void setImRoles(ImRoles imRoles) {
        this.imRoles = imRoles;
    }

    public ImFunctions getImFunctions() {
        return imFunctions;
    }

    public void setImFunctions(ImFunctions imFunctions) {
        this.imFunctions = imFunctions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImFuncRoles)) return false;

        ImFuncRoles that = (ImFuncRoles) o;

        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (imFunctions != null ? !imFunctions.equals(that.imFunctions) : that.imFunctions != null) return false;
        if (imRoles != null ? !imRoles.equals(that.imRoles) : that.imRoles != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flag != null ? flag.hashCode() : 0;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (imRoles != null ? imRoles.hashCode() : 0);
        result = 31 * result + (imFunctions != null ? imFunctions.hashCode() : 0);
        result = 31 * result + (primaryKey != null ? primaryKey.hashCode() : 0);
        return result;
    }
}
