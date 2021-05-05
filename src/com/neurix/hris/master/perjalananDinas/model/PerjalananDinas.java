package com.neurix.hris.master.perjalananDinas.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PerjalananDinas extends BaseModel {
    private String perjalananDinasId;
    private String kelompokId;
    private String kelompokName;
    private String positionName;
    private String biayaDinasId;
    private String biayaDinasName;
    private BigDecimal jumlahBiaya;
    private String dinas;
    private String golonganId;
    private String golonganName;

    public String getKelompokName() {
        return kelompokName;
    }

    public void setKelompokName(String kelompokName) {
        this.kelompokName = kelompokName;
    }

    public String getPerjalananDinasId() {
        return perjalananDinasId;
    }

    public void setPerjalananDinasId(String perjalananDinasId) {
        this.perjalananDinasId = perjalananDinasId;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getBiayaDinasId() {
        return biayaDinasId;
    }

    public void setBiayaDinasId(String biayaDinasId) {
        this.biayaDinasId = biayaDinasId;
    }

    public String getBiayaDinasName() {
        return biayaDinasName;
    }

    public void setBiayaDinasName(String biayaDinasName) {
        this.biayaDinasName = biayaDinasName;
    }

    public BigDecimal getJumlahBiaya() {
        return jumlahBiaya;
    }

    public void setJumlahBiaya(BigDecimal jumlahBiaya) {
        this.jumlahBiaya = jumlahBiaya;
    }

    public String getDinas() {
        return dinas;
    }

    public void setDinas(String dinas) {
        this.dinas = dinas;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }
}