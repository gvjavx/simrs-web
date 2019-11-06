package com.neurix.hris.master.smkBudget.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkBudget extends BaseModel {
    private String budgetId;
    private String strukturJabatanId;
    private String positionId;
    private String unitId;
    private String positionName;
    private Double bobot;
    private Double target;
    private Double realisasi;
    private String periode;
    private String bagianName;
    private Double nilaiRealisasi;
    private Double nilaiPrestasi;
    private Double pointPrestasiBagian;

    public Double getNilaiRealisasi() {
        return nilaiRealisasi;
    }

    public void setNilaiRealisasi(Double nilaiRealisasi) {
        this.nilaiRealisasi = nilaiRealisasi;
    }

    public Double getNilaiPrestasi() {
        return nilaiPrestasi;
    }

    public void setNilaiPrestasi(Double nilaiPrestasi) {
        this.nilaiPrestasi = nilaiPrestasi;
    }

    public Double getPointPrestasiBagian() {
        return pointPrestasiBagian;
    }

    public void setPointPrestasiBagian(Double pointPrestasiBagian) {
        this.pointPrestasiBagian = pointPrestasiBagian;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getStrukturJabatanId() {
        return strukturJabatanId;
    }

    public void setStrukturJabatanId(String strukturJabatanId) {
        this.strukturJabatanId = strukturJabatanId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Double getBobot() {
        return bobot;
    }

    public void setBobot(Double bobot) {
        this.bobot = bobot;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(Double realisasi) {
        this.realisasi = realisasi;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
}