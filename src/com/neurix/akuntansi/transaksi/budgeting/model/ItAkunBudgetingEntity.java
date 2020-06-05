package com.neurix.akuntansi.transaksi.budgeting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 29/04/20.
 */
public class ItAkunBudgetingEntity implements Serializable {

    private String idBudgeting;
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

    private BigDecimal januari;
    private BigDecimal februari;
    private BigDecimal maret;
    private BigDecimal april;
    private BigDecimal mei;
    private BigDecimal juni;
    private BigDecimal juli;
    private BigDecimal agustus;
    private BigDecimal september;
    private BigDecimal oktober;
    private BigDecimal november;
    private BigDecimal desember;
    private String noTrans;

    public String getNoTrans() {
        return noTrans;
    }

    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }

    public String getIdBudgeting() {
        return idBudgeting;
    }

    public void setIdBudgeting(String idBudgeting) {
        this.idBudgeting = idBudgeting;
    }

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

    public BigDecimal getJanuari() {
        return januari;
    }

    public void setJanuari(BigDecimal januari) {
        this.januari = januari;
    }

    public BigDecimal getFebruari() {
        return februari;
    }

    public void setFebruari(BigDecimal februari) {
        this.februari = februari;
    }

    public BigDecimal getMaret() {
        return maret;
    }

    public void setMaret(BigDecimal maret) {
        this.maret = maret;
    }

    public BigDecimal getApril() {
        return april;
    }

    public void setApril(BigDecimal april) {
        this.april = april;
    }

    public BigDecimal getMei() {
        return mei;
    }

    public void setMei(BigDecimal mei) {
        this.mei = mei;
    }

    public BigDecimal getJuni() {
        return juni;
    }

    public void setJuni(BigDecimal juni) {
        this.juni = juni;
    }

    public BigDecimal getJuli() {
        return juli;
    }

    public void setJuli(BigDecimal juli) {
        this.juli = juli;
    }

    public BigDecimal getAgustus() {
        return agustus;
    }

    public void setAgustus(BigDecimal agustus) {
        this.agustus = agustus;
    }

    public BigDecimal getSeptember() {
        return september;
    }

    public void setSeptember(BigDecimal september) {
        this.september = september;
    }

    public BigDecimal getOktober() {
        return oktober;
    }

    public void setOktober(BigDecimal oktober) {
        this.oktober = oktober;
    }

    public BigDecimal getNovember() {
        return november;
    }

    public void setNovember(BigDecimal november) {
        this.november = november;
    }

    public BigDecimal getDesember() {
        return desember;
    }

    public void setDesember(BigDecimal desember) {
        this.desember = desember;
    }
}
