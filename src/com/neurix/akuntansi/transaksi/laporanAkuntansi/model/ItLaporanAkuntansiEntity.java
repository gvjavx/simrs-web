package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItLaporanAkuntansiEntity implements Serializable {
    private String laporanAkuntansiId;
    private String url;
    private String laporanAkuntansiName;
    private String levelKodeRekening;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getLevelKodeRekening() {
        return levelKodeRekening;
    }

    public void setLevelKodeRekening(String levelKodeRekening) {
        this.levelKodeRekening = levelKodeRekening;
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

    public String getLaporanAkuntansiId() {
        return laporanAkuntansiId;
    }

    public void setLaporanAkuntansiId(String laporanAkuntansiId) {
        this.laporanAkuntansiId = laporanAkuntansiId;
    }

    public String getLaporanAkuntansiName() {
        return laporanAkuntansiName;
    }

    public void setLaporanAkuntansiName(String laporanAkuntansiName) {
        this.laporanAkuntansiName = laporanAkuntansiName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
