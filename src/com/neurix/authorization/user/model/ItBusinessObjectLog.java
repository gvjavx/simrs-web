package com.neurix.authorization.user.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 28/02/13
 * Time: 9:48
 * To change this template use File | Settings | File Templates.
 */
public class ItBusinessObjectLog  implements Serializable {
    private Long id;
    private String moduleMethod;
    private String message;
    private Timestamp errorTimestamp;

    private String userId;
    private String branchId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleMethod() {
        return moduleMethod;
    }

    public void setModuleMethod(String moduleMethod) {
        this.moduleMethod = moduleMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getErrorTimestamp() {
        return errorTimestamp;
    }

    public void setErrorTimestamp(Timestamp errorTimestamp) {
        this.errorTimestamp = errorTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItBusinessObjectLog)) return false;

        ItBusinessObjectLog that = (ItBusinessObjectLog) o;

        if (errorTimestamp != null ? !errorTimestamp.equals(that.errorTimestamp) : that.errorTimestamp != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (moduleMethod != null ? !moduleMethod.equals(that.moduleMethod) : that.moduleMethod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (moduleMethod != null ? moduleMethod.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (errorTimestamp != null ? errorTimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItBusinessObjectLog{" +
                "id=" + id +
                ", moduleMethod='" + moduleMethod + '\'' +
                ", message='" + message + '\'' +
                ", errorTimestamp=" + errorTimestamp +
                '}';
    }
}
