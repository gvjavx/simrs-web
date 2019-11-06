package com.neurix.hris.transaksi.personilPosition.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class HistoryJabatanPegawai extends BaseModel {
    private String historyJabatanPegawaiId;
    private String nip;
    private String bidang;
    private String status;
    private String positionName;
    private String branchName;
    private String tahun;
    private String tanggal;
    private String pjsFlag;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getHistoryJabatanPegawaiId() {
        return historyJabatanPegawaiId;
    }

    public void setHistoryJabatanPegawaiId(String historyJabatanPegawaiId) {
        this.historyJabatanPegawaiId = historyJabatanPegawaiId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPjsFlag() {
        return pjsFlag;
    }

    public void setPjsFlag(String pjsFlag) {
        this.pjsFlag = pjsFlag;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}