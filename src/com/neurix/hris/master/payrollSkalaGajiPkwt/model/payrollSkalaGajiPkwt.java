package com.neurix.hris.master.payrollSkalaGajiPkwt.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class payrollSkalaGajiPkwt extends BaseModel {
    private String skalaGajiPkwtId;
    private String golonganPkwtId;
    private String golonganPkwtName;
    private BigDecimal gajiPokokNilai;
    private BigDecimal santunanKhususNilai;
    private BigDecimal tunjFunsionalNilai;
    private BigDecimal tunjtambahanNilai;
    private String tahun;

    private String gajiPokok;
    private String santunanKhusus;
    private String tunjFunsional;
    private String tunjtambahan;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getGolonganPkwtName() {
        return golonganPkwtName;
    }

    public void setGolonganPkwtName(String golonganPkwtName) {
        this.golonganPkwtName = golonganPkwtName;
    }

    public String getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(String gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public BigDecimal getGajiPokokNilai() {
        return gajiPokokNilai;
    }

    public void setGajiPokokNilai(BigDecimal gajiPokokNilai) {
        this.gajiPokokNilai = gajiPokokNilai;
    }

    public String getGolonganPkwtId() {
        return golonganPkwtId;
    }

    public void setGolonganPkwtId(String golonganPkwtId) {
        this.golonganPkwtId = golonganPkwtId;
    }

    public String getSantunanKhusus() {
        return santunanKhusus;
    }

    public void setSantunanKhusus(String santunanKhusus) {
        this.santunanKhusus = santunanKhusus;
    }

    public BigDecimal getSantunanKhususNilai() {
        return santunanKhususNilai;
    }

    public void setSantunanKhususNilai(BigDecimal santunanKhususNilai) {
        this.santunanKhususNilai = santunanKhususNilai;
    }

    public String getSkalaGajiPkwtId() {
        return skalaGajiPkwtId;
    }

    public void setSkalaGajiPkwtId(String skalaGajiPkwtId) {
        this.skalaGajiPkwtId = skalaGajiPkwtId;
    }

    public String getTunjFunsional() {
        return tunjFunsional;
    }

    public void setTunjFunsional(String tunjFunsional) {
        this.tunjFunsional = tunjFunsional;
    }

    public BigDecimal getTunjFunsionalNilai() {
        return tunjFunsionalNilai;
    }

    public void setTunjFunsionalNilai(BigDecimal tunjFunsionalNilai) {
        this.tunjFunsionalNilai = tunjFunsionalNilai;
    }

    public String getTunjtambahan() {
        return tunjtambahan;
    }

    public void setTunjtambahan(String tunjtambahan) {
        this.tunjtambahan = tunjtambahan;
    }

    public BigDecimal getTunjtambahanNilai() {
        return tunjtambahanNilai;
    }

    public void setTunjtambahanNilai(BigDecimal tunjtambahanNilai) {
        this.tunjtambahanNilai = tunjtambahanNilai;
    }
}