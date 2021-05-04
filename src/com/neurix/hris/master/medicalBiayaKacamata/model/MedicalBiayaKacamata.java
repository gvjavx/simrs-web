package com.neurix.hris.master.medicalBiayaKacamata.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class MedicalBiayaKacamata extends BaseModel {
    private String biayaKacamataId;
    private String nip;
    private String branchId;
    private String branchName;
    private String namaPegawai;
    private String golonganId;
    private String golonganName;
    private BigDecimal biayaLensa;
    private BigDecimal biayaGagang;
    private String strBiayaLensa;
    private String strBiayaGagang;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStrBiayaGagang() {
        return strBiayaGagang;
    }

    public void setStrBiayaGagang(String strBiayaGagang) {
        this.strBiayaGagang = strBiayaGagang;
    }

    public String getStrBiayaLensa() {
        return strBiayaLensa;
    }

    public void setStrBiayaLensa(String strBiayaLensa) {
        this.strBiayaLensa = strBiayaLensa;
    }

    public String getBiayaKacamataId() {
        return biayaKacamataId;
    }

    public void setBiayaKacamataId(String biayaKacamataId) {
        this.biayaKacamataId = biayaKacamataId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
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

    public BigDecimal getBiayaLensa() {
        return biayaLensa;
    }

    public void setBiayaLensa(BigDecimal biayaLensa) {
        this.biayaLensa = biayaLensa;
    }

    public BigDecimal getBiayaGagang() {
        return biayaGagang;
    }

    public void setBiayaGagang(BigDecimal biayaGagang) {
        this.biayaGagang = biayaGagang;
    }
}