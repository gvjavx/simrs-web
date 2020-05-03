package com.neurix.simrs.transaksi.checkup.model;

import com.neurix.simrs.master.tindakan.model.Tindakan;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HeaderDetailCheckupLog {

    private String idHeaderCheckupLog;
    private String noCheckup;
    private String idDetailCheckup;
    private String idJenisPeriksaPasienSebelumnya;
    private String idJenisPeriksaPasienSetelahnya;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String keterangan;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdHeaderCheckupLog() {
        return idHeaderCheckupLog;
    }

    public void setIdHeaderCheckupLog(String idHeaderCheckupLog) {
        this.idHeaderCheckupLog = idHeaderCheckupLog;
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

    public String getIdJenisPeriksaPasienSebelumnya() {
        return idJenisPeriksaPasienSebelumnya;
    }

    public void setIdJenisPeriksaPasienSebelumnya(String idJenisPeriksaPasienSebelumnya) {
        this.idJenisPeriksaPasienSebelumnya = idJenisPeriksaPasienSebelumnya;
    }

    public String getIdJenisPeriksaPasienSetelahnya() {
        return idJenisPeriksaPasienSetelahnya;
    }

    public void setIdJenisPeriksaPasienSetelahnya(String idJenisPeriksaPasienSetelahnya) {
        this.idJenisPeriksaPasienSetelahnya = idJenisPeriksaPasienSetelahnya;
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
