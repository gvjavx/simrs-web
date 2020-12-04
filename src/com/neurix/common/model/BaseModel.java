package com.neurix.common.model;

import com.neurix.common.util.CommonUtil;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 12/03/13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class BaseModel {

    protected String action;
    protected String flag;
    protected String flagKalkulasiPph;
    protected String stFlag;
    protected Timestamp createdDate;
    protected String stCreatedDate;
    protected String createdWho;
    protected Timestamp lastUpdate;
    protected String stLastUpdate;
    protected String lastUpdateWho;
    protected String successMessage;
    protected String errorMessage;

    protected boolean flagYes;

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getFlagKalkulasiPph() {
        return flagKalkulasiPph;
    }

    public void setFlagKalkulasiPph(String flagKalkulasiPph) {
        this.flagKalkulasiPph = flagKalkulasiPph;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getStCreatedDate() {
        if (this.createdDate!=null) {
            stCreatedDate=CommonUtil.longDateFormat(this.createdDate);
        } else {
            stCreatedDate = "";
        }

        return stCreatedDate;
    }

    public String getStLastUpdate() {
        if (this.lastUpdate!=null) {
            stLastUpdate=CommonUtil.longDateFormat(this.lastUpdate);
        } else {
            stLastUpdate = "";
        }

        return stLastUpdate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStFlag() {
        return stFlag;
    }

    public boolean isFlagYes() {
        if (this.flag != null) {
            if (this.flag.equalsIgnoreCase("Y")) return true;
            else return false;
        } else {
            return true;
        }
    }

    public void setStFlag(String stFlag) {
        this.stFlag = stFlag;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
