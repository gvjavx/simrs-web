package com.neurix.authorization.user.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Ferdi on 16/02/2015.
 */
public class UserSessionLog extends BaseModel implements Serializable {
    private Long id;
    private String stId;
    private String sessionId;
    private String userName;
    private String name;
    private Timestamp loginTimestamp;
    private String stLoginTimestamp;
    private Timestamp loginTimestampFrom;
    private String stLoginTimestampFrom;
    private Timestamp loginTimestampTo;
    private String stLoginTimestampTo;
    private Timestamp logoutTimestamp;
    private String stLogoutTimestamp;
    private String companyName;
    private String branchName;
    private String areaName;
    private String ipAddress;
    private String jumlah;
    private String tipe;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    private boolean enabledKill;

    public boolean isEnabledKill() {
        return enabledKill;
    }

    public void setEnabledKill(boolean enabledKill) {
        this.enabledKill = enabledKill;
    }

    public Timestamp getLoginTimestampFrom() {
        return loginTimestampFrom;
    }

    public void setLoginTimestampFrom(Timestamp loginTimestampFrom) {
        this.loginTimestampFrom = loginTimestampFrom;
    }

    public Timestamp getLoginTimestampTo() {
        return loginTimestampTo;
    }

    public void setLoginTimestampTo(Timestamp loginTimestampTo) {
        this.loginTimestampTo = loginTimestampTo;
    }

    public String getStLoginTimestampFrom() {
        return stLoginTimestampFrom;
    }

    public void setStLoginTimestampFrom(String stLoginTimestampFrom) {
        this.stLoginTimestampFrom = stLoginTimestampFrom;
    }

    public String getStLoginTimestampTo() {
        return stLoginTimestampTo;
    }

    public void setStLoginTimestampTo(String stLoginTimestampTo) {
        this.stLoginTimestampTo = stLoginTimestampTo;
    }

    public String getStLoginTimestamp() {
        return stLoginTimestamp;
    }

    public void setStLoginTimestamp(String stLoginTimestamp) {
        this.stLoginTimestamp = stLoginTimestamp;
    }

    public String getStLogoutTimestamp() {
        return stLogoutTimestamp;
    }

    public void setStLogoutTimestamp(String stLogoutTimestamp) {
        this.stLogoutTimestamp = stLogoutTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(Timestamp loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    public Timestamp getLogoutTimestamp() {
        return logoutTimestamp;
    }

    public void setLogoutTimestamp(Timestamp logoutTimestamp) {
        this.logoutTimestamp = logoutTimestamp;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "UserSessionLog{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", userName='" + userName + '\'' +
                ", loginTimestamp=" + loginTimestamp +
                ", logoutTimestamp=" + logoutTimestamp +
                ", companyName='" + companyName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
