package com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class payrollSkalaGajiDplkPegawai extends BaseModel {
    private String skalaGajiPensiunDplkPegawaiId;
    private String golonganId;
    private String golonganName;
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

    public String getSkalaGajiPensiunDplkPegawaiId() {
        return skalaGajiPensiunDplkPegawaiId;
    }

    public void setSkalaGajiPensiunDplkPegawaiId(String skalaGajiPensiunDplkPegawaiId) {
        this.skalaGajiPensiunDplkPegawaiId = skalaGajiPensiunDplkPegawaiId;
    }
}