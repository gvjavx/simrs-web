package com.neurix.akuntansi.master.trans.model;

import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
// tambahkan tipe_jurnal , is_otomatis*/

public class ImTransEntity implements Serializable {

    private String transId;
    private String transName;
    private String tipePembayaran;

    //updated by ferdi, 01-12-2020
    private String tipeJurnalId;
    private String isOtomatis;
    private String flagPengajuanBiaya;
    private String flagSumberBaru;

    private String master;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private Set<ImMappingJurnalEntity> imMappingJurnal;

    public Set<ImMappingJurnalEntity> getImMappingJurnal() {
        return imMappingJurnal;
    }

    public void setImMappingJurnal(Set<ImMappingJurnalEntity> imMappingJurnal) {
        this.imMappingJurnal = imMappingJurnal;
    }

    public String getIsOtomatis() {
        return isOtomatis;
    }

    public void setIsOtomatis(String isOtomatis) {
        this.isOtomatis = isOtomatis;
    }

    public String getFlagPengajuanBiaya() {
        return flagPengajuanBiaya;
    }

    public void setFlagPengajuanBiaya(String flagPengajuanBiaya) {
        this.flagPengajuanBiaya = flagPengajuanBiaya;
    }

    public String getTipeJurnalId() {
        return tipeJurnalId;
    }

    public void setTipeJurnalId(String tipeJurnalId) {
        this.tipeJurnalId = tipeJurnalId;
    }

    public String getFlagSumberBaru() {
        return flagSumberBaru;
    }

    public void setFlagSumberBaru(String flagSumberBaru) {
        this.flagSumberBaru = flagSumberBaru;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }


    public String getTipePembayaran() {
        return tipePembayaran;
    }

    public void setTipePembayaran(String tipePembayaran) {
        this.tipePembayaran = tipePembayaran;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
