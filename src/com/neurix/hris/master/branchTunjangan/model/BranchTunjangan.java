package com.neurix.hris.master.branchTunjangan.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class BranchTunjangan extends BaseModel {
    private String branchTunjanganId;
    private String branchId;
    private String tunjanganId;

    public String getBranchTunjanganId() {
        return branchTunjanganId;
    }

    public void setBranchTunjanganId(String branchTunjanganId) {
        this.branchTunjanganId = branchTunjanganId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTunjanganId() {
        return tunjanganId;
    }

    public void setTunjanganId(String tunjanganId) {
        this.tunjanganId = tunjanganId;
    }
}