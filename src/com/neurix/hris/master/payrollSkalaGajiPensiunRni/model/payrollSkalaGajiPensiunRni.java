package com.neurix.hris.master.payrollSkalaGajiPensiunRni.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class payrollSkalaGajiPensiunRni extends BaseModel {
    private String skalaGajiPensiunId;
    private String golonganId;
    private String golonganName;
    private int poin;
    private BigDecimal nilai;
    private String stNilai;

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

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getSkalaGajiPensiunId() {
        return skalaGajiPensiunId;
    }

    public void setSkalaGajiPensiunId(String skalaGajiPensiunId) {
        this.skalaGajiPensiunId = skalaGajiPensiunId;
    }

    public String getStNilai() {
        return stNilai;
    }

    public void setStNilai(String stNilai) {
        this.stNilai = stNilai;
    }
}