package com.neurix.hris.transaksi.smk.model;

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

public class ItHistorySmkEvaluasiPegawaiEntity implements Serializable {
    private int id;
    private String evaluasiPegawaiId;
    private String evaluasiPegawaiAspekId;
    private String nip;
    private String pegawaiName;
    private String divisiId;
    private String divisiName;
    private String branchName;
    private String branchId;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private int poin;
    private String tipePegawaiId;
    private String statusPegawai;
    private String periode;

    private String nilaiPrestasi;
    private String closed;
    private int masaKerjaGolongan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String jabatanSmkId;
    private BigDecimal jumlahBobot;
    private String jabatanSmkDetailId;
    private String indikatorKinerja;
    private String checkList;
    private String subAspekA;
    private double bobot;
    private double pointPrestasi;
    private int pointnew;
    private double grandTotalNilaiPrestasi;
    private double grandTotalNilaiPrestasiRevisi;
    private String target;
    private String realisasi;
    private String evaluasiPegawaiAspekDetailId;
    private String activityId;
    private double nilai;
    private double nilaiPrestasiItem;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
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

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getEvaluasiPegawaiAspekDetailId() {
        return evaluasiPegawaiAspekDetailId;
    }

    public void setEvaluasiPegawaiAspekDetailId(String evaluasiPegawaiAspekDetailId) {
        this.evaluasiPegawaiAspekDetailId = evaluasiPegawaiAspekDetailId;
    }

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }

    public String getEvaluasiPegawaiId() {
        return evaluasiPegawaiId;
    }

    public void setEvaluasiPegawaiId(String evaluasiPegawaiId) {
        this.evaluasiPegawaiId = evaluasiPegawaiId;
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

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public double getGrandTotalNilaiPrestasi() {
        return grandTotalNilaiPrestasi;
    }

    public void setGrandTotalNilaiPrestasi(double grandTotalNilaiPrestasi) {
        this.grandTotalNilaiPrestasi = grandTotalNilaiPrestasi;
    }

    public double getGrandTotalNilaiPrestasiRevisi() {
        return grandTotalNilaiPrestasiRevisi;
    }

    public void setGrandTotalNilaiPrestasiRevisi(double grandTotalNilaiPrestasiRevisi) {
        this.grandTotalNilaiPrestasiRevisi = grandTotalNilaiPrestasiRevisi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndikatorKinerja() {
        return indikatorKinerja;
    }

    public void setIndikatorKinerja(String indikatorKinerja) {
        this.indikatorKinerja = indikatorKinerja;
    }

    public String getJabatanSmkDetailId() {
        return jabatanSmkDetailId;
    }

    public void setJabatanSmkDetailId(String jabatanSmkDetailId) {
        this.jabatanSmkDetailId = jabatanSmkDetailId;
    }

    public String getJabatanSmkId() {
        return jabatanSmkId;
    }

    public void setJabatanSmkId(String jabatanSmkId) {
        this.jabatanSmkId = jabatanSmkId;
    }

    public BigDecimal getJumlahBobot() {
        return jumlahBobot;
    }

    public void setJumlahBobot(BigDecimal jumlahBobot) {
        this.jumlahBobot = jumlahBobot;
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

    public int getMasaKerjaGolongan() {
        return masaKerjaGolongan;
    }

    public void setMasaKerjaGolongan(int masaKerjaGolongan) {
        this.masaKerjaGolongan = masaKerjaGolongan;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getNilaiPrestasi() {
        return nilaiPrestasi;
    }

    public void setNilaiPrestasi(String nilaiPrestasi) {
        this.nilaiPrestasi = nilaiPrestasi;
    }

    public double getNilaiPrestasiItem() {
        return nilaiPrestasiItem;
    }

    public void setNilaiPrestasiItem(double nilaiPrestasiItem) {
        this.nilaiPrestasiItem = nilaiPrestasiItem;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPegawaiName() {
        return pegawaiName;
    }

    public void setPegawaiName(String pegawaiName) {
        this.pegawaiName = pegawaiName;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public int getPointnew() {
        return pointnew;
    }

    public void setPointnew(int pointnew) {
        this.pointnew = pointnew;
    }

    public double getPointPrestasi() {
        return pointPrestasi;
    }

    public void setPointPrestasi(double pointPrestasi) {
        this.pointPrestasi = pointPrestasi;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(String realisasi) {
        this.realisasi = realisasi;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getSubAspekA() {
        return subAspekA;
    }

    public void setSubAspekA(String subAspekA) {
        this.subAspekA = subAspekA;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }
}
