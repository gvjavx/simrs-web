package com.neurix.authorization.user.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Ferdi on 16/02/2015.
 */
public class ErrorLog extends BaseModel implements Serializable {
    private Long id;
    private String errorId;
    private String moduleMethod;
    private String message;
    private Timestamp errorTimestamp;
    private String stErrorTimestamp;
    private Timestamp errorTimestampFrom;
    private String stErrorTimestampFrom;
    private Timestamp errorTimestampTo;
    private String stErrorTimestampTo;

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

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
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

    public String getStErrorTimestamp() {
        return stErrorTimestamp;
    }

    public void setStErrorTimestamp(String stErrorTimestamp) {
        this.stErrorTimestamp = stErrorTimestamp;
    }

    public String getStErrorTimestampFrom() {
        return stErrorTimestampFrom;
    }

    public void setStErrorTimestampFrom(String stErrorTimestampFrom) {
        this.stErrorTimestampFrom = stErrorTimestampFrom;
    }

    public String getStErrorTimestampTo() {
        return stErrorTimestampTo;
    }

    public void setStErrorTimestampTo(String stErrorTimestampTo) {
        this.stErrorTimestampTo = stErrorTimestampTo;
    }

    public Timestamp getErrorTimestampFrom() {
        return errorTimestampFrom;
    }

    public void setErrorTimestampFrom(Timestamp errorTimestampFrom) {
        this.errorTimestampFrom = errorTimestampFrom;
    }

    public Timestamp getErrorTimestampTo() {
        return errorTimestampTo;
    }

    public void setErrorTimestampTo(Timestamp errorTimestampTo) {
        this.errorTimestampTo = errorTimestampTo;
    }

    @Override
    public String toString() {
        return "ErrorLog{" +
                "id=" + id +
                ", errorId='" + errorId + '\'' +
                ", moduleMethod='" + moduleMethod + '\'' +
                ", message='" + message + '\'' +
                ", errorTimestamp=" + errorTimestamp +
                ", stErrorTimestamp='" + stErrorTimestamp + '\'' +
                '}';
    }
}
