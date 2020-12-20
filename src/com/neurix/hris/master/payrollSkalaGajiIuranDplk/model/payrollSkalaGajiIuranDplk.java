package com.neurix.hris.master.payrollSkalaGajiIuranDplk.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class payrollSkalaGajiIuranDplk extends BaseModel {
    private String skalaGajiIuranDplkId;
    private String golonganId;
    private String golonganName;
    private int masaKerjaGol;
    private BigDecimal nilai;
    private String stNilai;

    public String getStNilai() {
        return stNilai;
    }

    public void setStNilai(String stNilai) {
        this.stNilai = stNilai;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public int getMasaKerjaGol() {
        return masaKerjaGol;
    }

    public void setMasaKerjaGol(int masaKerjaGol) {
        this.masaKerjaGol = masaKerjaGol;
    }

    public String getSkalaGajiIuranDplkId() {
        return skalaGajiIuranDplkId;
    }

    public void setSkalaGajiIuranDplkId(String skalaGajiIuranDplkId) {
        this.skalaGajiIuranDplkId = skalaGajiIuranDplkId;
    }
}