package com.neurix.hris.master.payrollTunjanganPerumahan.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPayrollTunjanganPerumahanEntity implements Serializable {
    private String tunjRumahId ;
    private String golonganId ;
    private String golonganName ;
    private String kelompokId ;
    private String kelompokName ;
    private BigDecimal nilai ;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImGolonganEntity imGolonganEntity;
    private ImKelompokPositionEntity imKelompokPositionEntity;
    private ImBranches imBranches;

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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
    }

    public ImKelompokPositionEntity getImKelompokPositionEntity() {
        return imKelompokPositionEntity;
    }

    public void setImKelompokPositionEntity(ImKelompokPositionEntity imKelompokPositionEntity) {
        this.imKelompokPositionEntity = imKelompokPositionEntity;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getTunjRumahId() {
        return tunjRumahId;
    }

    public void setTunjRumahId(String tunjRumahId) {
        this.tunjRumahId = tunjRumahId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getKelompokName() {
        return kelompokName;
    }

    public void setKelompokName(String kelompokName) {
        this.kelompokName = kelompokName;
    }
}