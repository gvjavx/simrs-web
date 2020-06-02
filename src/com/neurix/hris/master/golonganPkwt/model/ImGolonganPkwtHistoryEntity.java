package com.neurix.hris.master.golonganPkwt.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImGolonganPkwtHistoryEntity implements Serializable {

    private String golonganPkwtHistoryId;
    private String golonganPkwtId;
    private String golonganPkwtName;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getGolonganPkwtHistoryId() {
        return golonganPkwtHistoryId;
    }

    public void setGolonganPkwtHistoryId(String golonganPkwtHistoryId) {
        this.golonganPkwtHistoryId = golonganPkwtHistoryId;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGolonganPkwtId() {
        return golonganPkwtId;
    }

    public void setGolonganPkwtId(String golonganPkwtId) {
        this.golonganPkwtId = golonganPkwtId;
    }

    public String getGolonganPkwtName() {
        return golonganPkwtName;
    }

    public void setGolonganPkwtName(String golonganPkwtName) {
        this.golonganPkwtName = golonganPkwtName;
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
}
