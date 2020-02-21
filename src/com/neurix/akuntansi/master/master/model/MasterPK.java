package com.neurix.akuntansi.master.master.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class MasterPK implements Serializable {
    private String masterId;
    private String nomorMaster;

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNomorMaster() {
        return nomorMaster;
    }

    public void setNomorMaster(String nomorMaster) {
        this.nomorMaster = nomorMaster;
    }
}
