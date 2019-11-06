package com.neurix.hris.transaksi.rekruitmenPabrik.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class RekruitmenPabrikDetail extends BaseModel {
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
    private String divisi;
    private String bagianId;

    private String divisiName;
    private String posisiLamaName;
    private String PosisiBaruName;
    private String statusPegawaiName;
    private String tipePegawaiName;
    private String bagianName;
    private Integer jmlIndisipliner;

    private boolean rekruitmenPabrikDetailApprove = false;
    private boolean rekruitmenPabrikDetailStatus = false;

    public Integer getJmlIndisipliner() {
        return jmlIndisipliner;
    }

    public void setJmlIndisipliner(Integer jmlIndisipliner) {
        this.jmlIndisipliner = jmlIndisipliner;
    }

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

    public boolean isRekruitmenPabrikDetailApprove() {
        return rekruitmenPabrikDetailApprove;
    }

    public void setRekruitmenPabrikDetailApprove(boolean rekruitmenPabrikDetailApprove) {
        this.rekruitmenPabrikDetailApprove = rekruitmenPabrikDetailApprove;
    }

    public boolean isRekruitmenPabrikDetailStatus() {
        return rekruitmenPabrikDetailStatus;
    }

    public void setRekruitmenPabrikDetailStatus(boolean rekruitmenPabrikDetailStatus) {
        this.rekruitmenPabrikDetailStatus = rekruitmenPabrikDetailStatus;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getPosisiLamaName() {
        return posisiLamaName;
    }

    public void setPosisiLamaName(String posisiLamaName) {
        this.posisiLamaName = posisiLamaName;
    }

    public String getPosisiBaruName() {
        return PosisiBaruName;
    }

    public void setPosisiBaruName(String posisiBaruName) {
        PosisiBaruName = posisiBaruName;
    }

    public String getStatusPegawaiName() {
        return statusPegawaiName;
    }

    public void setStatusPegawaiName(String statusPegawaiName) {
        this.statusPegawaiName = statusPegawaiName;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
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
}