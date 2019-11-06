package com.neurix.hris.mobileapi.model;

import java.io.Serializable;

/**
 * @author gondok
 * Wednesday, 20/02/19 13:39
 */
public class Branch implements Serializable {
    private String branchId;
    private String branchName;
    private String branchAddress;
    private String enabled;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }


}
