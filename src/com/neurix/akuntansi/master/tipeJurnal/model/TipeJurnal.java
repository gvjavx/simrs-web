package com.neurix.akuntansi.master.tipeJurnal.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class TipeJurnal extends BaseModel {
    private String tipeJurnalId;
    private String tipeJurnalName;
    private String flagSumberBaru;

    public String getFlagSumberBaru() {
        return flagSumberBaru;
    }

    public void setFlagSumberBaru(String flagSumberBaru) {
        this.flagSumberBaru = flagSumberBaru;
    }

    public String getTipeJurnalId() {
        return tipeJurnalId;
    }

    public void setTipeJurnalId(String tipeJurnalId) {
        this.tipeJurnalId = tipeJurnalId;
    }

    public String getTipeJurnalName() {
        return tipeJurnalName;
    }

    public void setTipeJurnalName(String tipeJurnalName) {
        this.tipeJurnalName = tipeJurnalName;
    }
}