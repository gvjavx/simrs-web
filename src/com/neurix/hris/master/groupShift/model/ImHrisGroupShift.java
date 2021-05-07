package com.neurix.hris.master.groupShift.model;

import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ImHrisGroupShift implements Serializable {
    private String groupShiftId;
    private String groupId;
    private String shiftId;
    private String groupShiftName;

    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    private ImHrisShiftEntity imHrisShiftEntity;
    private ImHrisGroupEntity imHrisGroupEntity;

    public ImHrisShiftEntity getImHrisShiftEntity() {
        return imHrisShiftEntity;
    }

    public String getGroupShiftName() {
        return groupShiftName;
    }

    public void setGroupShiftName(String groupShiftName) {
        this.groupShiftName = groupShiftName;
    }

    public void setImHrisShiftEntity(ImHrisShiftEntity imHrisShiftEntity) {
        this.imHrisShiftEntity = imHrisShiftEntity;
    }

    public ImHrisGroupEntity getImHrisGroupEntity() {
        return imHrisGroupEntity;
    }

    public void setImHrisGroupEntity(ImHrisGroupEntity imHrisGroupEntity) {
        this.imHrisGroupEntity = imHrisGroupEntity;
    }

    public String getGroupShiftId() {
        return groupShiftId;
    }

    public void setGroupShiftId(String groupShiftId) {
        this.groupShiftId = groupShiftId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
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
