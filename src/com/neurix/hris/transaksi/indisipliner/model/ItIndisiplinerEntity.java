package com.neurix.hris.transaksi.indisipliner.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItIndisiplinerEntity implements Serializable {
    private String indisiplinerId;
    private String nip;
    private String nama;
    private String bagianId;
    private String bagianName;
    private String branchId;
    private String tipeIndisipliner;
    private String keteranganPelanggaran;
    private Date tanggalAwalPantau;
    private Date tanggalAkhirPantau;
    private Date tanggalAwalBlokirAbsensi;
    private Date tanggalAkhirBlokirAbsensi;
    private Date tanggalAkhirBlokirSetuju;
    private Timestamp approvalDate;
    private String approvalPersonId;
    private String approvalPersonName;
    private String approvalNote;
    private String approvalFlag;
    private String notApprovalNote;
    private String closedFlag;
    private String closedNote;
    private Timestamp closedDate;
    private String dampak;
    private Date tanggal;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getIndisiplinerId() {
        return indisiplinerId;
    }

    public void setIndisiplinerId(String indisiplinerId) {
        this.indisiplinerId = indisiplinerId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTipeIndisipliner() {
        return tipeIndisipliner;
    }

    public void setTipeIndisipliner(String tipeIndisipliner) {
        this.tipeIndisipliner = tipeIndisipliner;
    }

    public String getKeteranganPelanggaran() {
        return keteranganPelanggaran;
    }

    public void setKeteranganPelanggaran(String keteranganPelanggaran) {
        this.keteranganPelanggaran = keteranganPelanggaran;
    }

    public Date getTanggalAwalPantau() {
        return tanggalAwalPantau;
    }

    public void setTanggalAwalPantau(Date tanggalAwalPantau) {
        this.tanggalAwalPantau = tanggalAwalPantau;
    }

    public Date getTanggalAkhirPantau() {
        return tanggalAkhirPantau;
    }

    public void setTanggalAkhirPantau(Date tanggalAkhirPantau) {
        this.tanggalAkhirPantau = tanggalAkhirPantau;
    }

    public Date getTanggalAwalBlokirAbsensi() {
        return tanggalAwalBlokirAbsensi;
    }

    public void setTanggalAwalBlokirAbsensi(Date tanggalAwalBlokirAbsensi) {
        this.tanggalAwalBlokirAbsensi = tanggalAwalBlokirAbsensi;
    }

    public Date getTanggalAkhirBlokirAbsensi() {
        return tanggalAkhirBlokirAbsensi;
    }

    public void setTanggalAkhirBlokirAbsensi(Date tanggalAkhirBlokirAbsensi) {
        this.tanggalAkhirBlokirAbsensi = tanggalAkhirBlokirAbsensi;
    }

    public Date getTanggalAkhirBlokirSetuju() {
        return tanggalAkhirBlokirSetuju;
    }

    public void setTanggalAkhirBlokirSetuju(Date tanggalAkhirBlokirSetuju) {
        this.tanggalAkhirBlokirSetuju = tanggalAkhirBlokirSetuju;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalPersonId() {
        return approvalPersonId;
    }

    public void setApprovalPersonId(String approvalPersonId) {
        this.approvalPersonId = approvalPersonId;
    }

    public String getApprovalPersonName() {
        return approvalPersonName;
    }

    public void setApprovalPersonName(String approvalPersonName) {
        this.approvalPersonName = approvalPersonName;
    }

    public String getApprovalNote() {
        return approvalNote;
    }

    public void setApprovalNote(String approvalNote) {
        this.approvalNote = approvalNote;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getClosedFlag() {
        return closedFlag;
    }

    public void setClosedFlag(String closedFlag) {
        this.closedFlag = closedFlag;
    }

    public String getClosedNote() {
        return closedNote;
    }

    public void setClosedNote(String closedNote) {
        this.closedNote = closedNote;
    }

    public Timestamp getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Timestamp closedDate) {
        this.closedDate = closedDate;
    }

    public String getDampak() {
        return dampak;
    }

    public void setDampak(String dampak) {
        this.dampak = dampak;
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
