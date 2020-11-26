package com.neurix.simrs.transaksi.antriantelemedic.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 08/06/20.
 */
public class VideoRm {

    private String idVideoRm;
    private String path;
    private String idAntrianTelemedic;
    private String idDetailCheckup;
    private String tipeVideo;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    public String getIdVideoRm() {
        return idVideoRm;
    }

    public void setIdVideoRm(String idVideoRm) {
        this.idVideoRm = idVideoRm;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIdAntrianTelemedic() {
        return idAntrianTelemedic;
    }

    public void setIdAntrianTelemedic(String idAntrianTelemedic) {
        this.idAntrianTelemedic = idAntrianTelemedic;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getTipeVideo() {
        return tipeVideo;
    }

    public void setTipeVideo(String tipeVideo) {
        this.tipeVideo = tipeVideo;
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
