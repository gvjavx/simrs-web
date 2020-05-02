package com.neurix.akuntansi.transaksi.budgeting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 29/04/20.
 */
public class ItAkunBudgetingEntity implements Serializable {

    private String noBudgeting;
    private String branchId;
    private String tahun;
    private String rekeningId;
    private String status;
    private String tipe;
    private BigDecimal nilaiTotal;
    private BigDecimal semester1;
    private BigDecimal semester2;
    private BigDecimal quartal1;
    private BigDecimal quartal2;
    private BigDecimal quartal3;
    private BigDecimal quartal4;
    private BigDecimal selisih;
    private String approveFlag;
    private String approveWho;
    private Timestamp approveTime;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public BigDecimal getSelisih() {
        return selisih;
    }

    public void setSelisih(BigDecimal selisih) {
        this.selisih = selisih;
    }

    public String getNoBudgeting() {
        return noBudgeting;
    }

    public void setNoBudgeting(String noBudgeting) {
        this.noBudgeting = noBudgeting;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getNilaiTotal() {
        return nilaiTotal;
    }

    public void setNilaiTotal(BigDecimal nilaiTotal) {
        this.nilaiTotal = nilaiTotal;
    }

    public BigDecimal getSemester1() {
        return semester1;
    }

    public void setSemester1(BigDecimal semester1) {
        this.semester1 = semester1;
    }

    public BigDecimal getSemester2() {
        return semester2;
    }

    public void setSemester2(BigDecimal semester2) {
        this.semester2 = semester2;
    }

    public BigDecimal getQuartal1() {
        return quartal1;
    }

    public void setQuartal1(BigDecimal quartal1) {
        this.quartal1 = quartal1;
    }

    public BigDecimal getQuartal2() {
        return quartal2;
    }

    public void setQuartal2(BigDecimal quartal2) {
        this.quartal2 = quartal2;
    }

    public BigDecimal getQuartal3() {
        return quartal3;
    }

    public void setQuartal3(BigDecimal quartal3) {
        this.quartal3 = quartal3;
    }

    public BigDecimal getQuartal4() {
        return quartal4;
    }

    public void setQuartal4(BigDecimal quartal4) {
        this.quartal4 = quartal4;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getApproveWho() {
        return approveWho;
    }

    public void setApproveWho(String approveWho) {
        this.approveWho = approveWho;
    }

    public Timestamp getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Timestamp approveTime) {
        this.approveTime = approveTime;
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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
