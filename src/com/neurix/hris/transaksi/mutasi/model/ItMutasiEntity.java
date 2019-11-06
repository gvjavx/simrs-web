package com.neurix.hris.transaksi.mutasi.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItMutasiEntity implements Serializable {
    private String mutasiId ;
    private String stTanggalEfektif;
    private Timestamp tanggalEfektif;
    private String nip;
    private String status;
    private String divisiLamaId ;
    private String divisiLamaName ;
    private String branchLamaId ;
    private String branchLamaName ;
    private String positionLamaId ;
    private String positionLamaName ;
    private String branchBaruId ;
    private String branchBaruName ;
    private String divisiBaruId ;
    private String divisiBaruName ;
    private String positionBaruId ;
    private String positionBaruNama ;
    private String pjs;
    private String menggantikanNip;
    private String tipeMutasi;

    public String getTipeMutasi() {
        return tipeMutasi;
    }

    public void setTipeMutasi(String tipeMutasi) {
        this.tipeMutasi = tipeMutasi;
    }

    public String getMenggantikanNip() {
        return menggantikanNip;
    }

    public void setMenggantikanNip(String menggantikanNip) {
        this.menggantikanNip = menggantikanNip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPjs() {
        return pjs;
    }

    public void setPjs(String pjs) {
        this.pjs = pjs;
    }

    public String getStTanggalEfektif() {
        return stTanggalEfektif;
    }

    public void setStTanggalEfektif(String stTanggalEfektif) {
        this.stTanggalEfektif = stTanggalEfektif;
    }

    public Timestamp getTanggalEfektif() {
        return tanggalEfektif;
    }

    public void setTanggalEfektif(Timestamp tanggalEfektif) {
        this.tanggalEfektif = tanggalEfektif;
    }

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBiodataEntity imBiodataEntity;
    private ImBranches imBranchesLama;
    private ImDepartmentEntity imDepartmentEntityLama ;
    private ImPosition imPositionLama ;

    private ImBranches imBranchesBaru;
    private ImDepartmentEntity imDepartmentEntityBaru ;
    private ImPosition imPositionBaru ;
    private ItMutasiDocEntity itMutasiDocEntity;

    public ItMutasiDocEntity getItMutasiDocEntity() {
        return itMutasiDocEntity;
    }

    public void setItMutasiDocEntity(ItMutasiDocEntity itMutasiDocEntity) {
        this.itMutasiDocEntity = itMutasiDocEntity;
    }

    public ImBranches getImBranchesLama() {
        return imBranchesLama;
    }

    public void setImBranchesLama(ImBranches imBranchesLama) {
        this.imBranchesLama = imBranchesLama;
    }

    public ImDepartmentEntity getImDepartmentEntityLama() {
        return imDepartmentEntityLama;
    }

    public void setImDepartmentEntityLama(ImDepartmentEntity imDepartmentEntityLama) {
        this.imDepartmentEntityLama = imDepartmentEntityLama;
    }

    public ImPosition getImPositionLama() {
        return imPositionLama;
    }

    public void setImPositionLama(ImPosition imPositionLama) {
        this.imPositionLama = imPositionLama;
    }

    public ImBranches getImBranchesBaru() {
        return imBranchesBaru;
    }

    public void setImBranchesBaru(ImBranches imBranchesBaru) {
        this.imBranchesBaru = imBranchesBaru;
    }

    public ImDepartmentEntity getImDepartmentEntityBaru() {
        return imDepartmentEntityBaru;
    }

    public void setImDepartmentEntityBaru(ImDepartmentEntity imDepartmentEntityBaru) {
        this.imDepartmentEntityBaru = imDepartmentEntityBaru;
    }

    public ImPosition getImPositionBaru() {
        return imPositionBaru;
    }

    public void setImPositionBaru(ImPosition imPositionBaru) {
        this.imPositionBaru = imPositionBaru;
    }

    public String getPositionBaruNama() {
        return positionBaruNama;
    }

    public void setPositionBaruNama(String positionBaruNama) {
        this.positionBaruNama = positionBaruNama;
    }

    public String getMutasiId() {
        return mutasiId;
    }

    public void setMutasiId(String mutasiId) {
        this.mutasiId = mutasiId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getDivisiLamaId() {
        return divisiLamaId;
    }

    public void setDivisiLamaId(String divisiLamaId) {
        this.divisiLamaId = divisiLamaId;
    }

    public String getDivisiLamaName() {
        return divisiLamaName;
    }

    public void setDivisiLamaName(String divisiLamaName) {
        this.divisiLamaName = divisiLamaName;
    }

    public String getBranchLamaId() {
        return branchLamaId;
    }

    public void setBranchLamaId(String branchLamaId) {
        this.branchLamaId = branchLamaId;
    }

    public String getBranchLamaName() {
        return branchLamaName;
    }

    public void setBranchLamaName(String branchLamaName) {
        this.branchLamaName = branchLamaName;
    }

    public String getPositionLamaName() {
        return positionLamaName;
    }

    public void setPositionLamaName(String positionLamaName) {
        this.positionLamaName = positionLamaName;
    }

    public String getBranchBaruId() {
        return branchBaruId;
    }

    public void setBranchBaruId(String branchBaruId) {
        this.branchBaruId = branchBaruId;
    }

    public String getBranchBaruName() {
        return branchBaruName;
    }

    public void setBranchBaruName(String branchBaruName) {
        this.branchBaruName = branchBaruName;
    }

    public String getDivisiBaruId() {
        return divisiBaruId;
    }

    public void setDivisiBaruId(String divisiBaruId) {
        this.divisiBaruId = divisiBaruId;
    }

    public String getDivisiBaruName() {
        return divisiBaruName;
    }

    public void setDivisiBaruName(String divisiBaruName) {
        this.divisiBaruName = divisiBaruName;
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

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public String getPositionLamaId() {
        return positionLamaId;
    }

    public void setPositionLamaId(String positionLamaId) {
        this.positionLamaId = positionLamaId;
    }

    public String getPositionBaruId() {
        return positionBaruId;
    }

    public void setPositionBaruId(String positionBaruId) {
        this.positionBaruId = positionBaruId;
    }
}
