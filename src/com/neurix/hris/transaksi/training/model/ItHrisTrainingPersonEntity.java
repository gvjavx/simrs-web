package com.neurix.hris.transaksi.training.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ItHrisTrainingPersonEntity implements Serializable {

    private String trainingPersonId;
    private String trainingId;
    private String personId;
    private String personName;
    private String divisiId;
    private String approvalId;
    private String approvalName;
    private Timestamp approvalDate;
    private String approvalFlag;
    private String notApprovalNote;
    private String approvalBosId;
    private String approvalBosName;
    private Timestamp approvalBosDate;
    private String approvalBosFlag;
    private String notApprovalBosNote;
    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private Timestamp approvalSdmDate;
    private String approvalSdm;
    private String notApprovalSdmNote;

    public Timestamp getApprovalSdmDate() {
        return approvalSdmDate;
    }

    public void setApprovalSdmDate(Timestamp approvalSdmDate) {
        this.approvalSdmDate = approvalSdmDate;
    }

    public String getApprovalSdm() {
        return approvalSdm;
    }

    public void setApprovalSdm(String approvalSdm) {
        this.approvalSdm = approvalSdm;
    }

    public String getNotApprovalSdmNote() {
        return notApprovalSdmNote;
    }

    public void setNotApprovalSdmNote(String notApprovalSdmNote) {
        this.notApprovalSdmNote = notApprovalSdmNote;
    }

    public Timestamp getApprovalBosDate() {
        return approvalBosDate;
    }

    public String getTrainingPersonId() {
        return trainingPersonId;
    }

    public void setTrainingPersonId(String trainingPersonId) {
        this.trainingPersonId = trainingPersonId;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public void setApprovalBosDate(Timestamp approvalBosDate) {
        this.approvalBosDate = approvalBosDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getApprovalBosId() {
        return approvalBosId;
    }

    public void setApprovalBosId(String approvalBosId) {
        this.approvalBosId = approvalBosId;
    }

    public String getApprovalBosName() {
        return approvalBosName;
    }

    public void setApprovalBosName(String approvalBosName) {
        this.approvalBosName = approvalBosName;
    }

    public String getApprovalBosFlag() {
        return approvalBosFlag;
    }

    public void setApprovalBosFlag(String approvalBosFlag) {
        this.approvalBosFlag = approvalBosFlag;
    }

    public String getNotApprovalBosNote() {
        return notApprovalBosNote;
    }

    public void setNotApprovalBosNote(String notApprovalBosNote) {
        this.notApprovalBosNote = notApprovalBosNote;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateDateWho() {
        return createDateWho;
    }

    public void setCreateDateWho(String createDateWho) {
        this.createDateWho = createDateWho;
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
}
