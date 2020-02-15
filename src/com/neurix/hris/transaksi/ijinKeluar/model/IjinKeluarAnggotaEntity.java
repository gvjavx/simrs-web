package com.neurix.hris.transaksi.ijinKeluar.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class IjinKeluarAnggotaEntity implements Serializable {
    private String ijinKeluarAnggotaId;
    private String ijinKeluarKantorId;
    private String nip;
    private String namaPegawai;
    private String positionId;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String branchId;
    private String bidangId;
    private String bagianId;

    private Date tanggal;
    private String jamKeluar;
    private String jamKembali;

    public String getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getJamKembali() {
        return jamKembali;
    }

    public void setJamKembali(String jamKembali) {
        this.jamKembali = jamKembali;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBidangId() {
        return bidangId;
    }

    public void setBidangId(String bidangId) {
        this.bidangId = bidangId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIjinKeluarKantorId() {
        return ijinKeluarKantorId;
    }

    public void setIjinKeluarKantorId(String ijinKeluarKantorId) {
        this.ijinKeluarKantorId = ijinKeluarKantorId;
    }

    public String getIjinKeluarAnggotaId() {
        return ijinKeluarAnggotaId;
    }

    public void setIjinKeluarAnggotaId(String ijinKeluarAnggotaId) {
        this.ijinKeluarAnggotaId = ijinKeluarAnggotaId;
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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
