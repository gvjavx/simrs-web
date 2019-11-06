package com.neurix.hris.transaksi.rekruitmenPabrik.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class RekruitmenPabrik extends BaseModel {
    private String rekruitmenPabrikId;
    private String branchId;
    private String divisiId;
    private String mt;
    private Integer kuota;
    private String bagianId;

    private String bagianName;
    private String divisiName;
    private String unitName;
    private Integer sisaKuota;

    private boolean approvalOneGm=false;
    private boolean approvalSdm=false;
    private boolean approvalGm=false;
    private boolean close=false;
    private boolean cekAdmin=false;


    public Integer getSisaKuota() {
        return sisaKuota;
    }

    public void setSisaKuota(Integer sisaKuota) {
        this.sisaKuota = sisaKuota;
    }

    public boolean isCekAdmin() {
        return cekAdmin;
    }

    public void setCekAdmin(boolean cekAdmin) {
        this.cekAdmin = cekAdmin;
    }

    private String rpbId;

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public Integer getKuota() {
        return kuota;
    }

    public void setKuota(Integer kuota) {
        this.kuota = kuota;
    }

    public String getRpbId() {
        return rpbId;
    }

    public void setRpbId(String rpbId) {
        this.rpbId = rpbId;
    }

    public boolean isApprovalOneGm() {
        return approvalOneGm;
    }

    public void setApprovalOneGm(boolean approvalOneGm) {
        this.approvalOneGm = approvalOneGm;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isApprovalSdm() {
        return approvalSdm;
    }

    public void setApprovalSdm(boolean approvalSdm) {
        this.approvalSdm = approvalSdm;
    }

    public boolean isApprovalGm() {
        return approvalGm;
    }

    public void setApprovalGm(boolean approvalGm) {
        this.approvalGm = approvalGm;
    }

    public String getRekruitmenPabrikId() {
        return rekruitmenPabrikId;
    }

    public void setRekruitmenPabrikId(String rekruitmenPabrikId) {
        this.rekruitmenPabrikId = rekruitmenPabrikId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }
}