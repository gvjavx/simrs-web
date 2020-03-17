package com.neurix.akuntansi.master.settingReportUser.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SettingReportUser extends BaseModel {
   private String settingReportUserId;
   private String reportId;
   private String userId;
   private String userName;
   private String reportName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getSettingReportUserId() {
        return settingReportUserId;
    }

    public void setSettingReportUserId(String settingReportUserId) {
        this.settingReportUserId = settingReportUserId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}