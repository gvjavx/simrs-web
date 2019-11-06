package com.neurix.hris.master.smkIndikatorPenilaianAspek.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkIndikatorPenilaianAspek extends BaseModel {
    private String indikatorPenilaianAspekId;
    private String kategoriAspekId;
    private String kategoriAspekName;
    private String indikatorName;
    private String status;
    private String branchId;
    private Long nilai;
    private double bobot;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
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
}