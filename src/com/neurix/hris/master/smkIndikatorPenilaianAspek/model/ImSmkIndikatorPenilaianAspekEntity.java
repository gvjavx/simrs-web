package com.neurix.hris.master.smkIndikatorPenilaianAspek.model;

import com.neurix.hris.master.smkKategoriAspek.model.ImSmkKategoriAspekEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSmkIndikatorPenilaianAspekEntity implements Serializable {

    private String indikatorPenilaianAspekId;
    private String kategoriAspekId;
    private String branchId;
    private String kategoriAspekName;
    private String indikatorName;
    private Long nilai;
    private double bobot;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
    }

    private ImSmkKategoriAspekEntity imSmkKategoriAspekEntity;

    public ImSmkKategoriAspekEntity getImSmkKategoriAspekEntity() {
        return imSmkKategoriAspekEntity;
    }

    public void setImSmkKategoriAspekEntity(ImSmkKategoriAspekEntity imSmkKategoriAspekEntity) {
        this.imSmkKategoriAspekEntity = imSmkKategoriAspekEntity;
    }

    public String getIndikatorPenilaianAspekId() {
        return indikatorPenilaianAspekId;
    }

    public void setIndikatorPenilaianAspekId(String indikatorPenilaianAspekId) {
        this.indikatorPenilaianAspekId = indikatorPenilaianAspekId;
    }

    public String getKategoriAspekId() {
        return kategoriAspekId;
    }

    public void setKategoriAspekId(String kategoriAspekId) {
        this.kategoriAspekId = kategoriAspekId;
    }

    public String getKategoriAspekName() {
        return kategoriAspekName;
    }

    public void setKategoriAspekName(String kategoriAspekName) {
        this.kategoriAspekName = kategoriAspekName;
    }

    public String getIndikatorName() {
        return indikatorName;
    }

    public void setIndikatorName(String indikatorName) {
        this.indikatorName = indikatorName;
    }

    public Long getNilai() {
        return nilai;
    }

    public void setNilai(Long nilai) {
        this.nilai = nilai;
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
