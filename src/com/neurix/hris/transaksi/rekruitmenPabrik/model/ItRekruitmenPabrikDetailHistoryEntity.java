package com.neurix.hris.transaksi.rekruitmenPabrik.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.model.BaseModel;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class ItRekruitmenPabrikDetailHistoryEntity extends BaseModel {
    private String rekruitmenPabrikDetailId;
    private String rekruitmenPabrikId;
    private String nip;
    private String namaPegawai;
    private String posisiLama;
    private String posisiBaru;
    private Timestamp tanggalAktif;
    private String statusGiling;
    private String approvalGmId;
    private String approvalGmFlag;
    private String approvalGmName;
    private Timestamp approvalGmdate;
    private String approvalSdmId;
    private String approvalSdmFlag;
    private String approvalSdmName;
    private Timestamp approvalSdmdate;
    private String noKontrak;
    private String id;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBiodataEntity imBiodataEntity;
    private ImPosition imPosition;
    private ImPosition imPosition2;
    private ImBranches imBranches;
    private ImDepartmentEntity imDepartmentEntity;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImPosition getImPosition2() {
        return imPosition2;
    }

    public void setImPosition2(ImPosition imPosition2) {
        this.imPosition2 = imPosition2;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public String getRekruitmenPabrikDetailId() {
        return rekruitmenPabrikDetailId;
    }

    public void setRekruitmenPabrikDetailId(String rekruitmenPabrikDetailId) {
        this.rekruitmenPabrikDetailId = rekruitmenPabrikDetailId;
    }

    public String getRekruitmenPabrikId() {
        return rekruitmenPabrikId;
    }

    public void setRekruitmenPabrikId(String rekruitmenPabrikId) {
        this.rekruitmenPabrikId = rekruitmenPabrikId;
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

    public String getPosisiLama() {
        return posisiLama;
    }

    public void setPosisiLama(String posisiLama) {
        this.posisiLama = posisiLama;
    }

    public String getPosisiBaru() {
        return posisiBaru;
    }

    public void setPosisiBaru(String posisiBaru) {
        this.posisiBaru = posisiBaru;
    }

    public Timestamp getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Timestamp tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getApprovalGmId() {
        return approvalGmId;
    }

    public void setApprovalGmId(String approvalGmId) {
        this.approvalGmId = approvalGmId;
    }

    public String getApprovalGmFlag() {
        return approvalGmFlag;
    }

    public void setApprovalGmFlag(String approvalGmFlag) {
        this.approvalGmFlag = approvalGmFlag;
    }

    public String getApprovalGmName() {
        return approvalGmName;
    }

    public void setApprovalGmName(String approvalGmName) {
        this.approvalGmName = approvalGmName;
    }

    public Timestamp getApprovalGmdate() {
        return approvalGmdate;
    }

    public void setApprovalGmdate(Timestamp approvalGmdate) {
        this.approvalGmdate = approvalGmdate;
    }

    public String getApprovalSdmId() {
        return approvalSdmId;
    }

    public void setApprovalSdmId(String approvalSdmId) {
        this.approvalSdmId = approvalSdmId;
    }

    public String getApprovalSdmFlag() {
        return approvalSdmFlag;
    }

    public void setApprovalSdmFlag(String approvalSdmFlag) {
        this.approvalSdmFlag = approvalSdmFlag;
    }

    public String getApprovalSdmName() {
        return approvalSdmName;
    }

    public void setApprovalSdmName(String approvalSdmName) {
        this.approvalSdmName = approvalSdmName;
    }

    public Timestamp getApprovalSdmdate() {
        return approvalSdmdate;
    }

    public void setApprovalSdmdate(Timestamp approvalSdmdate) {
        this.approvalSdmdate = approvalSdmdate;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}