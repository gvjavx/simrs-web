package com.neurix.hris.master.payrollMasaKerjaPensiun.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollMasaKerjaPensiun extends BaseModel {
    private String masaKerjaPensiunId;
    private int tahunDari;
    private int tahunSampai;
    private int faktorKali;
    private int faktorKali2;

    public String getMasaKerjaPensiunId() {
        return masaKerjaPensiunId;
    }

    public void setMasaKerjaPensiunId(String masaKerjaPensiunId) {
        this.masaKerjaPensiunId = masaKerjaPensiunId;
    }

    public int getTahunDari() {
        return tahunDari;
    }

    public void setTahunDari(int tahunDari) {
        this.tahunDari = tahunDari;
    }

    public int getTahunSampai() {
        return tahunSampai;
    }

    public void setTahunSampai(int tahunSampai) {
        this.tahunSampai = tahunSampai;
    }

    public int getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(int faktorKali) {
        this.faktorKali = faktorKali;
    }

    public int getFaktorKali2() {
        return faktorKali2;
    }

    public void setFaktorKali2(int faktorKali2) {
        this.faktorKali2 = faktorKali2;
    }
}