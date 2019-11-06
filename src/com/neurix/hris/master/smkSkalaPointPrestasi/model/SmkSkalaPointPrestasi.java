package com.neurix.hris.master.smkSkalaPointPrestasi.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkSkalaPointPrestasi extends BaseModel {
    private String pointPrestasiId;
    private Long point;
    private double nilaiAtas;
    private double nilaiBawah;
    private String branchId;
    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPointPrestasiId() {
        return pointPrestasiId;
    }

    public void setPointPrestasiId(String pointPrestasiId) {
        this.pointPrestasiId = pointPrestasiId;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public double getNilaiAtas() {
        return nilaiAtas;
    }

    public void setNilaiAtas(double nilaiAtas) {
        this.nilaiAtas = nilaiAtas;
    }

    public double getNilaiBawah() {
        return nilaiBawah;
    }

    public void setNilaiBawah(double nilaiBawah) {
        this.nilaiBawah = nilaiBawah;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}