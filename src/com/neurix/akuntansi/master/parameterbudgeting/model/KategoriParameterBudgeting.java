package com.neurix.akuntansi.master.parameterbudgeting.model;
import java.sql.Timestamp;

public class KategoriParameterBudgeting {
    private String id;
    private String nama;
    private String idJenisBudgeting;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String jenisBudgeting;

    public String getJenisBudgeting() {
        return jenisBudgeting;
    }

    public void setJenisBudgeting(String jenisBudgeting) {
        this.jenisBudgeting = jenisBudgeting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdJenisBudgeting() {
        return idJenisBudgeting;
    }

    public void setIdJenisBudgeting(String idJenisBudgeting) {
        this.idJenisBudgeting = idJenisBudgeting;
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
