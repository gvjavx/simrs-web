package com.neurix.simrs.transaksi.monvitalsign.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by reza on 12/02/20.
 */
public class ItSimrsMonVitalSignEntity implements Serializable {
    private String id;
    private String noCheckup;
    private String idDetailCheckup;
    private Integer jam;
    private Integer nafas;
    private Integer nadi;
    private Integer suhu;
    private Integer tensi;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Integer getJam() {
        return jam;
    }

    public void setJam(Integer jam) {
        this.jam = jam;
    }

    public Integer getNafas() {
        return nafas;
    }

    public void setNafas(Integer nafas) {
        this.nafas = nafas;
    }

    public Integer getNadi() {
        return nadi;
    }

    public void setNadi(Integer nadi) {
        this.nadi = nadi;
    }

    public Integer getSuhu() {
        return suhu;
    }

    public void setSuhu(Integer suhu) {
        this.suhu = suhu;
    }

    public Integer getTensi() {
        return tensi;
    }

    public void setTensi(Integer tensi) {
        this.tensi = tensi;
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
}
