package com.neurix.akuntansi.master.tipeRekening.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class TipeRekening extends BaseModel {
    private String tipeRekeningId;
    private String tipeRekeningName;

    public String getTipeRekeningId() {
        return tipeRekeningId;
    }

    public void setTipeRekeningId(String tipeRekeningId) {
        this.tipeRekeningId = tipeRekeningId;
    }

    public String getTipeRekeningName() {
        return tipeRekeningName;
    }

    public void setTipeRekeningName(String tipeRekeningName) {
        this.tipeRekeningName = tipeRekeningName;
    }
}