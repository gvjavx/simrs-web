package com.neurix.hris.master.dashboard.model;

import com.neurix.common.model.BaseModel;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Dashboard extends BaseModel {
    private String updateGolonganId;
    private String nip;
    private String namaPegawai;
    private String strGolonganLama;
    private String strGolongan;
    private String golonganIdBefore;
    private String poinBefore;
    private String poinLebihBefore;
    private String nilaiAngka;
    private String nilaiHuruf;
    private String golonganId;
    private String poin;
    private String poinLebih;
    private String periode;
    private String branchId;
    private String branchName;
    private String approvalFlag;
    private String approvalWho;
    private String stApprovalDate;
    private Date approvalDate;
    private String status;
    private String bagianId;
    private String bagianName;
    private String positionId;

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStrGolonganLama() {
        return strGolonganLama;
    }

    public void setStrGolonganLama(String strGolonganLama) {
        this.strGolonganLama = strGolonganLama;
    }

    public String getStrGolongan() {
        return strGolongan;
    }

    public void setStrGolongan(String strGolongan) {
        this.strGolongan = strGolongan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getGolonganIdBefore() {
        return golonganIdBefore;
    }

    public void setGolonganIdBefore(String golonganIdBefore) {
        this.golonganIdBefore = golonganIdBefore;
    }

    public String getPoinBefore() {
        return poinBefore;
    }

    public void setPoinBefore(String poinBefore) {
        this.poinBefore = poinBefore;
    }

    public String getPoinLebihBefore() {
        return poinLebihBefore;
    }

    public void setPoinLebihBefore(String poinLebihBefore) {
        this.poinLebihBefore = poinLebihBefore;
    }

    public String getNilaiAngka() {
        return nilaiAngka;
    }

    public void setNilaiAngka(String nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
    }

    public String getNilaiHuruf() {
        return nilaiHuruf;
    }

    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getPoinLebih() {
        return poinLebih;
    }

    public void setPoinLebih(String poinLebih) {
        this.poinLebih = poinLebih;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getUpdateGolonganId() {
        return updateGolonganId;
    }

    public void setUpdateGolonganId(String updateGolonganId) {
        this.updateGolonganId = updateGolonganId;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovalWho() {
        return approvalWho;
    }

    public void setApprovalWho(String approvalWho) {
        this.approvalWho = approvalWho;
    }

    public String getStApprovalDate() {
        return stApprovalDate;
    }

    public void setStApprovalDate(String stApprovalDate) {
        this.stApprovalDate = stApprovalDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
}