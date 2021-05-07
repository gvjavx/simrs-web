package com.neurix.hris.transaksi.payroll.model;


import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollMinimumPromosiEntity implements Serializable {
    private String promosiId;
    private int masaKerjaMin;
    private int masaKerjaMaks;
    private int minimalPoin;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

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

    public int getMasaKerjaMaks() {
        return masaKerjaMaks;
    }

    public void setMasaKerjaMaks(int masaKerjaMaks) {
        this.masaKerjaMaks = masaKerjaMaks;
    }

    public int getMasaKerjaMin() {
        return masaKerjaMin;
    }

    public void setMasaKerjaMin(int masaKerjaMin) {
        this.masaKerjaMin = masaKerjaMin;
    }

    public int getMinimalPoin() {
        return minimalPoin;
    }

    public void setMinimalPoin(int minimalPoin) {
        this.minimalPoin = minimalPoin;
    }

    public String getPromosiId() {
        return promosiId;
    }

    public void setPromosiId(String promosiId) {
        this.promosiId = promosiId;
    }
}
