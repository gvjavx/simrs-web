package com.neurix.simrs.transaksi.checkupdetail.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HeaderDetailCheckup {
    private String idDetailCheckup;
    private String noCheckup;
    private String idPelayanan;
    private String statusPeriksa;
    private String statusBayar;
    private BigInteger totalBiaya;
    private String keteranganSelesai;
    private String jenisLab;
    private String branchId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getStatusPeriksa() {
        return statusPeriksa;
    }

    public void setStatusPeriksa(String statusPeriksa) {
        this.statusPeriksa = statusPeriksa;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public BigInteger getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(BigInteger totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public String getKeteranganSelesai() {
        return keteranganSelesai;
    }

    public void setKeteranganSelesai(String keteranganSelesai) {
        this.keteranganSelesai = keteranganSelesai;
    }

    public String getJenisLab() {
        return jenisLab;
    }

    public void setJenisLab(String jenisLab) {
        this.jenisLab = jenisLab;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

    @Override
    public String toString() {
        return "ItSimrsHeaderDetailCheckupEntity{" +
                "idDetailCheckup='" + idDetailCheckup + '\'' +
                ", noCheckup='" + noCheckup + '\'' +
                ", idPelayanan='" + idPelayanan + '\'' +
                ", statusPeriksa='" + statusPeriksa + '\'' +
                ", statusBayar='" + statusBayar + '\'' +
                ", totalBiaya=" + totalBiaya +
                ", keteranganSelesai='" + keteranganSelesai + '\'' +
                ", jenisLab='" + jenisLab + '\'' +
                ", branchId='" + branchId + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                '}';
    }
}
