package com.neurix.hris.master.smkPersenSmkNilai.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSmkPersenSmkNilaiEntity implements Serializable {
    private String smkNilaiId ;
    private String nama ;
    private String branchId ;
    private String branchName ;
    private BigDecimal nilai ;
    private BigDecimal nilaiAtas ;
    private BigDecimal nilaiBawah ;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBranches imBranches;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiAtas() {
        return nilaiAtas;
    }

    public void setNilaiAtas(BigDecimal nilaiAtas) {
        this.nilaiAtas = nilaiAtas;
    }

    public BigDecimal getNilaiBawah() {
        return nilaiBawah;
    }

    public void setNilaiBawah(BigDecimal nilaiBawah) {
        this.nilaiBawah = nilaiBawah;
    }

    public String getSmkNilaiId() {
        return smkNilaiId;
    }

    public void setSmkNilaiId(String smkNilaiId) {
        this.smkNilaiId = smkNilaiId;
    }
}