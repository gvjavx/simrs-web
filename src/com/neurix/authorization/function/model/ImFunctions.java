package com.neurix.authorization.function.model;

import com.neurix.authorization.role.model.ImRoles;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public class ImFunctions implements Serializable, Comparable<ImFunctions> {

    private Long funcId;

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    private String funcName;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private Long parent;

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    private Long funcLevel;

    public Long getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(Long funcLevel) {
        this.funcLevel = funcLevel;
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

    private Long menu;

    public Long getMenu() {
        return menu;
    }

    public void setMenu(Long menu) {
        this.menu = menu;
    }

    private Set<ImRoles> imRole;

    public Set<ImRoles> getImRole() {
        return imRole;
    }

    public void setImRole(Set<ImRoles> imRole) {
        this.imRole = imRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImFunctions)) return false;

        ImFunctions that = (ImFunctions) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (funcLevel != null ? !funcLevel.equals(that.funcLevel) : that.funcLevel != null) return false;
        if (funcName != null ? !funcName.equals(that.funcName) : that.funcName != null) return false;
        if (imRole != null ? !imRole.equals(that.imRole) : that.imRole != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (funcId != null ? !funcId.equals(that.funcId) : that.funcId != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (menu != null ? !menu.equals(that.menu) : that.menu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = funcId != null ? funcId.hashCode() : 0;
        result = 31 * result + (funcName != null ? funcName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (funcLevel != null ? funcLevel.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (imRole != null ? imRole.hashCode() : 0);
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImFunctions{" +
                "funcId=" + funcId +
                ", funcName='" + funcName + '\'' +
                ", url='" + url + '\'' +
                ", parent=" + parent +
                ", funcLevel=" + funcLevel +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", action='" + action + '\'' +
                ", flag='" + flag + '\'' +
                ", imRole=" + imRole +
                ", menu=" + menu +
                '}';
    }

    public int compareTo(ImFunctions o) {
        int result=funcId.compareTo(o.getFuncId());
        return result;
    }
}
