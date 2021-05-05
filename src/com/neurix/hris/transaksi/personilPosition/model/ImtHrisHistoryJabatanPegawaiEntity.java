package com.neurix.hris.transaksi.personilPosition.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImtHrisHistoryJabatanPegawaiEntity implements Serializable {

    private String historyJabatanId;
    private String historyJabatanPegawaiId;
    private String nip;
    private String bidangName;
    private String status;
    private String positionName;
    private String branchName;
    private String tahun;
    private String pjsFlag;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String branchId;
    private String positionId;
    private String divisiId;
    private Date tanggalSkMutasi;
    private String tipePegawaiName;
    private String tipePegawaiId;
    private String golonganId;
    private String point;
    private String pointLebih;
    private BigDecimal nilaiSmk;
    private String gradeSmk;
    private String golonganName;
    private String tanggal;
    private String bagianId;
    private String bidangId;
    private String profesiId;
    private String divisiName;
    private String bagianName;
    private String perusahaanLain;
    private String jabatanLain;
    private String tanggalKeluar;

    private String jabatanFlag;
    private String mutasiFlag;
    private String jenisPegawai;
    private String flagDigaji;

    public String getHistoryJabatanPegawaiId() {
        return historyJabatanPegawaiId;
    }

    public void setHistoryJabatanPegawaiId(String historyJabatanPegawaiId) {
        this.historyJabatanPegawaiId = historyJabatanPegawaiId;
    }

    public String getJenisPegawai() {
        return jenisPegawai;
    }

    public void setJenisPegawai(String jenisPegawai) {
        this.jenisPegawai = jenisPegawai;
    }

    public String getFlagDigaji() {
        return flagDigaji;
    }

    public void setFlagDigaji(String flagDigaji) {
        this.flagDigaji = flagDigaji;
    }

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    private ImPosition imPosition;

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getBidangId() {
        return bidangId;
    }

    public void setBidangId(String bidangId) {
        this.bidangId = bidangId;
    }

    public String getBidangName() {
        return bidangName;
    }

    public void setBidangName(String bidangName) {
        this.bidangName = bidangName;
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

    public String getGradeSmk() {
        return gradeSmk;
    }

    public void setGradeSmk(String gradeSmk) {
        this.gradeSmk = gradeSmk;
    }

    public String getHistoryJabatanId() {
        return historyJabatanId;
    }

    public void setHistoryJabatanId(String historyJabatanId) {
        this.historyJabatanId = historyJabatanId;
    }

    public String getJabatanLain() {
        return jabatanLain;
    }

    public void setJabatanLain(String jabatanLain) {
        this.jabatanLain = jabatanLain;
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

    public BigDecimal getNilaiSmk() {
        return nilaiSmk;
    }

    public void setNilaiSmk(BigDecimal nilaiSmk) {
        this.nilaiSmk = nilaiSmk;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPerusahaanLain() {
        return perusahaanLain;
    }

    public void setPerusahaanLain(String perusahaanLain) {
        this.perusahaanLain = perusahaanLain;
    }

    public String getPjsFlag() {
        return pjsFlag;
    }

    public void setPjsFlag(String pjsFlag) {
        this.pjsFlag = pjsFlag;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPointLebih() {
        return pointLebih;
    }

    public void setPointLebih(String pointLebih) {
        this.pointLebih = pointLebih;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggalSkMutasi() {
        return tanggalSkMutasi;
    }

    public void setTanggalSkMutasi(Date tanggalSkMutasi) {
        this.tanggalSkMutasi = tanggalSkMutasi;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public String getJabatanFlag() {
        return jabatanFlag;
    }

    public void setJabatanFlag(String jabatanFlag) {
        this.jabatanFlag = jabatanFlag;
    }

    public String getMutasiFlag() {
        return mutasiFlag;
    }

    public void setMutasiFlag(String mutasiFlag) {
        this.mutasiFlag = mutasiFlag;
    }
}
