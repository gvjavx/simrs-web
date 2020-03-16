package com.neurix.hris.transaksi.absensi.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class AbsensiDashboard implements Serializable {
    private String absensiDashboardId;
    private String pin;
    private String status;
    private Timestamp scanDate;

    private String statusName;
    private String tanggal;
    private String jenisLembur;
    private String jamAwalLembur;
    private String jamSelesaiLembur;
    private String ijinName;
    private String jamKeluarIjin;
    private String jamKembaliIjin;

    public String getJenisLembur() {
        return jenisLembur;
    }

    public void setJenisLembur(String jenisLembur) {
        this.jenisLembur = jenisLembur;
    }

    public String getJamAwalLembur() {
        return jamAwalLembur;
    }

    public void setJamAwalLembur(String jamAwalLembur) {
        this.jamAwalLembur = jamAwalLembur;
    }

    public String getJamSelesaiLembur() {
        return jamSelesaiLembur;
    }

    public void setJamSelesaiLembur(String jamSelesaiLembur) {
        this.jamSelesaiLembur = jamSelesaiLembur;
    }

    public String getIjinName() {
        return ijinName;
    }

    public void setIjinName(String ijinName) {
        this.ijinName = ijinName;
    }

    public String getJamKeluarIjin() {
        return jamKeluarIjin;
    }

    public void setJamKeluarIjin(String jamKeluarIjin) {
        this.jamKeluarIjin = jamKeluarIjin;
    }

    public String getJamKembaliIjin() {
        return jamKembaliIjin;
    }

    public void setJamKembaliIjin(String jamKembaliIjin) {
        this.jamKembaliIjin = jamKembaliIjin;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getScanDate() {
        return scanDate;
    }

    public void setScanDate(Timestamp scanDate) {
        this.scanDate = scanDate;
    }

    public String getAbsensiDashboardId() {
        return absensiDashboardId;
    }

    public void setAbsensiDashboardId(String absensiDashboardId) {
        this.absensiDashboardId = absensiDashboardId;
    }
}
