package com.neurix.hris.master.smkIndikatorPenilaianCheckList.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.hris.master.smkCheckList.model.ImSmkCheckListEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSmkIndikatorPenilaianCheckListEntity implements Serializable {

    private String indikatorPenilaianCheckListId;
    private String checkListId;
    private String indikatorName;
    private String nilai;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImSmkCheckListEntity imSmkCheckListEntity;

    public ImSmkCheckListEntity getImSmkCheckListEntity() {
        return imSmkCheckListEntity;
    }

    public void setImSmkCheckListEntity(ImSmkCheckListEntity imSmkCheckListEntity) {
        this.imSmkCheckListEntity = imSmkCheckListEntity;
    }

    public String getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public String getIndikatorPenilaianCheckListId() {
        return indikatorPenilaianCheckListId;
    }

    public void setIndikatorPenilaianCheckListId(String indikatorPenilaianCheckListId) {
        this.indikatorPenilaianCheckListId = indikatorPenilaianCheckListId;
    }

    public String getIndikatorName() {
        return indikatorName;
    }

    public void setIndikatorName(String indikatorName) {
        this.indikatorName = indikatorName;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
