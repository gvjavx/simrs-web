package com.neurix.hris.master.branchstatus.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class BranchStatus extends BaseModel implements Serializable {
    private Integer statusId;
    private String branchId;
    private String statusPabrik;
    private Date tanggalAwal;
    private Date tanggalAkhir;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStatusPabrik() {
        return statusPabrik;
    }

    public void setStatusPabrik(String statusPabrik) {
        this.statusPabrik = statusPabrik;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }
}
