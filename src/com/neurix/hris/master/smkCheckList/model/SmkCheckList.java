package com.neurix.hris.master.smkCheckList.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkCheckList extends BaseModel {
    private String branchName;
/*
    private String tipeAspekName;
*/
    private String checkListId;
    private String checkListName;
    private String branchId;
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public String getCheckListName() {
        return checkListName;
    }

    public void setCheckListName(String checkListName) {
        this.checkListName = checkListName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

/*
    public String getTipeAspekName() {
        return tipeAspekName;
    }

    public void setTipeAspekName(String tipeAspekName) {
        this.tipeAspekName = tipeAspekName;
    }*/

}