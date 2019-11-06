package com.neurix.hris.master.payrollTunjanganJabatanStruktural.model;

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
    private String tunjJabatanStrukturalId ;

    private String positionId ;
    private BigDecimal nilai ;
    private String branchId;
    private String positionName;
    private String branchName;

    public String getBranchName() {return branchName;}

    public void setBranchName(String branchName) {this.branchName = branchName;}

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getTunjJabatanStrukturalId() {
        return tunjJabatanStrukturalId;
    }

    public void setTunjJabatanStrukturalId(String tunjJabatanStrukturalId) {
        this.tunjJabatanStrukturalId = tunjJabatanStrukturalId;
    }
}