package com.neurix.hris.master.groupShift.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class GroupShift extends BaseModel implements Serializable {
    private String groupShiftId;
    private String groupShiftName;
    private String groupId;
    private String shiftId;

    private String groupName;
    private String shiftName;

    public String getGroupShiftName() {
        return groupShiftName;
    }

    public void setGroupShiftName(String groupShiftName) {
        this.groupShiftName = groupShiftName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
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

    public String getGroupShiftId() {
        return groupShiftId;
    }

    public void setGroupShiftId(String groupShiftId) {
        this.groupShiftId = groupShiftId;
    }


}
