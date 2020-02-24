package com.neurix.akuntansi.master.trans.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Trans extends BaseModel {
    private String transId;
    private String transName;
    private String flagPembayaranUtangPiutang;

    public String getFlagPembayaranUtangPiutang() {
        return flagPembayaranUtangPiutang;
    }

    public void setFlagPembayaranUtangPiutang(String flagPembayaranUtangPiutang) {
        this.flagPembayaranUtangPiutang = flagPembayaranUtangPiutang;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }
}