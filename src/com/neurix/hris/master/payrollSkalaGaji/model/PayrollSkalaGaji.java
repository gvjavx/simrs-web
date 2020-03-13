package com.neurix.hris.master.payrollSkalaGaji.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollSkalaGaji extends BaseModel {
    private String skalaGajiId;
    private String golonganId;
    private String golonganName;
    private String stLevel;
    private String tahun;
    private int point;
    private BigDecimal nilai ;
    private BigDecimal santunanKhusus;
    private BigDecimal rumah;
    private BigDecimal listrik;
    private BigDecimal air;
    private BigDecimal bbm;
    private BigDecimal total;
    private String stNilai ;
    private String stSantunanKhusus;
    private String stRumah;
    private String stListrik;
    private String stAir;
    private String stBbm;
    private String stTotal;

    public String getStLevel() {
        return stLevel;
    }

    public void setStLevel(String stLevel) {
        this.stLevel = stLevel;
    }

    public String getStAir() {
        return stAir;
    }

    public void setStAir(String stAir) {
        this.stAir = stAir;
    }

    public String getStBbm() {
        return stBbm;
    }

    public void setStBbm(String stBbm) {
        this.stBbm = stBbm;
    }

    public String getStListrik() {
        return stListrik;
    }

    public void setStListrik(String stListrik) {
        this.stListrik = stListrik;
    }

    public String getStNilai() {
        return stNilai;
    }

    public void setStNilai(String stNilai) {
        this.stNilai = stNilai;
    }

    public String getStRumah() {
        return stRumah;
    }

    public void setStRumah(String stRumah) {
        this.stRumah = stRumah;
    }

    public String getStSantunanKhusus() {
        return stSantunanKhusus;
    }

    public void setStSantunanKhusus(String stSantunanKhusus) {
        this.stSantunanKhusus = stSantunanKhusus;
    }

    public String getStTotal() {
        return stTotal;
    }

    public void setStTotal(String stTotal) {
        this.stTotal = stTotal;
    }

    public BigDecimal getAir() {
        return air;
    }

    public void setAir(BigDecimal air) {
        this.air = air;
    }

    public BigDecimal getBbm() {
        return bbm;
    }

    public void setBbm(BigDecimal bbm) {
        this.bbm = bbm;
    }

    public BigDecimal getListrik() {
        return listrik;
    }

    public void setListrik(BigDecimal listrik) {
        this.listrik = listrik;
    }

    public BigDecimal getRumah() {
        return rumah;
    }

    public void setRumah(BigDecimal rumah) {
        this.rumah = rumah;
    }

    public BigDecimal getSantunanKhusus() {
        return santunanKhusus;
    }

    public void setSantunanKhusus(BigDecimal santunanKhusus) {
        this.santunanKhusus = santunanKhusus;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getSkalaGajiId() {
        return skalaGajiId;
    }

    public void setSkalaGajiId(String skalaGajiId) {
        this.skalaGajiId = skalaGajiId;
    }
}