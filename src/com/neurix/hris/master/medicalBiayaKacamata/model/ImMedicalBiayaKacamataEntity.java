package com.neurix.hris.master.medicalBiayaKacamata.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImMedicalBiayaKacamataEntity implements Serializable {

    private String biayaKacamataId;

    private String golonganId;
    private String unitId;
    private String golonganName;
    private BigDecimal biayaLensa;
    private BigDecimal biayaGagang;
    private BigDecimal strBiayaLensa;
    private BigDecimal strBiayaGagang;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImGolonganEntity golonganEntity;
    private ImBranches imBranches;

    public ImGolonganEntity getGolonganEntity() {
        return golonganEntity;
    }

    public void setGolonganEntity(ImGolonganEntity golonganEntity) {
        this.golonganEntity = golonganEntity;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getBiayaKacamataId() {
        return biayaKacamataId;
    }

    public void setBiayaKacamataId(String biayaKacamataId) {
        this.biayaKacamataId = biayaKacamataId;
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

    public BigDecimal getStrBiayaLensa() {
        return strBiayaLensa;
    }

    public void setStrBiayaLensa(BigDecimal strBiayaLensa) {
        this.strBiayaLensa = strBiayaLensa;
    }

    public BigDecimal getStrBiayaGagang() {
        return strBiayaGagang;
    }

    public void setStrBiayaGagang(BigDecimal strBiayaGagang) {
        this.strBiayaGagang = strBiayaGagang;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
