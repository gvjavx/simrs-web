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

    private String historyJabatanPegawaiId;
    private String nip;
    private String bidang;
    private String status;
    private String positionName;
    private String branchName;
    private String tahun;
    private String pjsFlag;
    private String positionId;
    private String branchId;
    private String divisiId;
    private Date tanggalMutasiSk;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String fromPerson;
    private String noRequest;

    private String tipePegawai;
    private String tipePegawaiId;
    private String GolonganId;
    private String GolonganName;
    private String point;
    private String pointLebih;
    private BigDecimal nilaiSmk;
    private String gradeSmk;
    private String tanggal;

    private ImPosition imPosition;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public String getGolonganId() {
        return GolonganId;
    }

    public void setGolonganId(String golonganId) {
        GolonganId = golonganId;
    }

    public String getGolonganName() {
        return GolonganName;
    }

    public void setGolonganName(String golonganName) {
        GolonganName = golonganName;
    }

    public String getGradeSmk() {
        return gradeSmk;
    }

    public void setGradeSmk(String gradeSmk) {
        this.gradeSmk = gradeSmk;
    }

    public BigDecimal getNilaiSmk() {
        return nilaiSmk;
    }

    public void setNilaiSmk(BigDecimal nilaiSmk) {
        this.nilaiSmk = nilaiSmk;
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

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public Date getTanggalMutasiSk() {
        return tanggalMutasiSk;
    }

    public void setTanggalMutasiSk(Date tanggalMutasiSk) {
        this.tanggalMutasiSk = tanggalMutasiSk;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFromPerson() {
        return fromPerson;
    }

    public void setFromPerson(String fromPerson) {
        this.fromPerson = fromPerson;
    }

    public String getHistoryJabatanPegawaiId() {
        return historyJabatanPegawaiId;
    }

    public void setHistoryJabatanPegawaiId(String historyJabatanPegawaiId) {
        this.historyJabatanPegawaiId = historyJabatanPegawaiId;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNoRequest() {
        return noRequest;
    }

    public void setNoRequest(String noRequest) {
        this.noRequest = noRequest;
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
