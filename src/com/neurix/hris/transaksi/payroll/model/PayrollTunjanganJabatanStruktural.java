package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollTunjanganJabatanStruktural extends BaseModel {
    private String tunjJabStrukturId ;
    private String positionId ;
    private double nilai ;
    private String kelompokId;
    private String kelompokName;
    private BigDecimal tunjJabatan;
    private BigDecimal tunjStruktural;
    private String stTunjJabatan;
    private String stTunjStruktural;

    private String stCreatedDate;
    private String stLastUpdate;

    public String getKelompokName() {
        return kelompokName;
    }

    public void setKelompokName(String kelompokName) {
        this.kelompokName = kelompokName;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public BigDecimal getTunjJabatan() {
        return tunjJabatan;
    }

    public void setTunjJabatan(BigDecimal tunjJabatan) {
        this.tunjJabatan = tunjJabatan;
    }

    public BigDecimal getTunjStruktural() {
        return tunjStruktural;
    }

    public void setTunjStruktural(BigDecimal tunjStruktural) {
        this.tunjStruktural = tunjStruktural;
    }

    public String getStTunjJabatan() {
        return stTunjJabatan;
    }

    public void setStTunjJabatan(String stTunjJabatan) {
        this.stTunjJabatan = stTunjJabatan;
    }

    public String getStTunjStruktural() {
        return stTunjStruktural;
    }

    public void setStTunjStruktural(String stTunjStruktural) {
        this.stTunjStruktural = stTunjStruktural;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getTunjJabStrukturId() {
        return tunjJabStrukturId;
    }

    public void setTunjJabStrukturId(String tunjJabStrukturId) {
        this.tunjJabStrukturId = tunjJabStrukturId;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }
}