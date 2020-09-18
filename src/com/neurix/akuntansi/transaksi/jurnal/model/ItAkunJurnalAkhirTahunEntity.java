package com.neurix.akuntansi.transaksi.jurnal.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by reza on 18/09/20.
 */
public class ItAkunJurnalAkhirTahunEntity {
    private String noJurnal;
    private String tipeJurnalId;
    private Date tanggalJurnal;
    private String sumber;
    private String mataUangId;
    private BigDecimal kurs;
    private String keterangan;
    private String flag;
    private String userName;
    private String workStation;
    private String cabangId;
    private Timestamp createdDate;
    private Date registeredDate;
    private String registeredFlag;
    private String registeredUser;
    private String registerId;
    private String periode;
    private String branchId;
    private String masterId;
    private String sumberDanaId;
    private String carryOver;
    private String createdWho;
    private Timestamp lastUpdate;
    private Date printedDate;
    private String printedFlag;
    private String spumId;
    private BigDecimal printCount;
    private BigDecimal printRegisterCount;
    private Date rePrintedDate;
    private Date reRegisteredDate;
    private String caraPenerimaan;
    private String noSeriPajakDipungut;
    private String noSeriPajakDitanggung;
    private String personal;
    private String posAnggaran;
    private String buktiPendukung;
    private Date tanggalPendukung;
    private BigDecimal dppDipungut;
    private BigDecimal dppDitanggung;
    private String lastUpdateWho;
    private String action;
    private String pengajuanBiayaId;

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getTipeJurnalId() {
        return tipeJurnalId;
    }

    public void setTipeJurnalId(String tipeJurnalId) {
        this.tipeJurnalId = tipeJurnalId;
    }

    public Date getTanggalJurnal() {
        return tanggalJurnal;
    }

    public void setTanggalJurnal(Date tanggalJurnal) {
        this.tanggalJurnal = tanggalJurnal;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getMataUangId() {
        return mataUangId;
    }

    public void setMataUangId(String mataUangId) {
        this.mataUangId = mataUangId;
    }

    public BigDecimal getKurs() {
        return kurs;
    }

    public void setKurs(BigDecimal kurs) {
        this.kurs = kurs;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

    public String getCabangId() {
        return cabangId;
    }

    public void setCabangId(String cabangId) {
        this.cabangId = cabangId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRegisteredFlag() {
        return registeredFlag;
    }

    public void setRegisteredFlag(String registeredFlag) {
        this.registeredFlag = registeredFlag;
    }

    public String getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(String registeredUser) {
        this.registeredUser = registeredUser;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItAkunJurnalAkhirTahunEntity that = (ItAkunJurnalAkhirTahunEntity) o;

        if (noJurnal != null ? !noJurnal.equals(that.noJurnal) : that.noJurnal != null) return false;
        if (tipeJurnalId != null ? !tipeJurnalId.equals(that.tipeJurnalId) : that.tipeJurnalId != null) return false;
        if (tanggalJurnal != null ? !tanggalJurnal.equals(that.tanggalJurnal) : that.tanggalJurnal != null)
            return false;
        if (sumber != null ? !sumber.equals(that.sumber) : that.sumber != null) return false;
        if (mataUangId != null ? !mataUangId.equals(that.mataUangId) : that.mataUangId != null) return false;
        if (kurs != null ? !kurs.equals(that.kurs) : that.kurs != null) return false;
        if (keterangan != null ? !keterangan.equals(that.keterangan) : that.keterangan != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (workStation != null ? !workStation.equals(that.workStation) : that.workStation != null) return false;
        if (cabangId != null ? !cabangId.equals(that.cabangId) : that.cabangId != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (registeredDate != null ? !registeredDate.equals(that.registeredDate) : that.registeredDate != null)
            return false;
        if (registeredFlag != null ? !registeredFlag.equals(that.registeredFlag) : that.registeredFlag != null)
            return false;
        if (registeredUser != null ? !registeredUser.equals(that.registeredUser) : that.registeredUser != null)
            return false;
        if (registerId != null ? !registerId.equals(that.registerId) : that.registerId != null) return false;
        if (periode != null ? !periode.equals(that.periode) : that.periode != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (masterId != null ? !masterId.equals(that.masterId) : that.masterId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noJurnal != null ? noJurnal.hashCode() : 0;
        result = 31 * result + (tipeJurnalId != null ? tipeJurnalId.hashCode() : 0);
        result = 31 * result + (tanggalJurnal != null ? tanggalJurnal.hashCode() : 0);
        result = 31 * result + (sumber != null ? sumber.hashCode() : 0);
        result = 31 * result + (mataUangId != null ? mataUangId.hashCode() : 0);
        result = 31 * result + (kurs != null ? kurs.hashCode() : 0);
        result = 31 * result + (keterangan != null ? keterangan.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (workStation != null ? workStation.hashCode() : 0);
        result = 31 * result + (cabangId != null ? cabangId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (registeredFlag != null ? registeredFlag.hashCode() : 0);
        result = 31 * result + (registeredUser != null ? registeredUser.hashCode() : 0);
        result = 31 * result + (registerId != null ? registerId.hashCode() : 0);
        result = 31 * result + (periode != null ? periode.hashCode() : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (masterId != null ? masterId.hashCode() : 0);
        return result;
    }

    public String getSumberDanaId() {
        return sumberDanaId;
    }

    public void setSumberDanaId(String sumberDanaId) {
        this.sumberDanaId = sumberDanaId;
    }

    public String getCarryOver() {
        return carryOver;
    }

    public void setCarryOver(String carryOver) {
        this.carryOver = carryOver;
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

    public Date getPrintedDate() {
        return printedDate;
    }

    public void setPrintedDate(Date printedDate) {
        this.printedDate = printedDate;
    }

    public String getPrintedFlag() {
        return printedFlag;
    }

    public void setPrintedFlag(String printedFlag) {
        this.printedFlag = printedFlag;
    }

    public String getSpumId() {
        return spumId;
    }

    public void setSpumId(String spumId) {
        this.spumId = spumId;
    }

    public BigDecimal getPrintCount() {
        return printCount;
    }

    public void setPrintCount(BigDecimal printCount) {
        this.printCount = printCount;
    }

    public BigDecimal getPrintRegisterCount() {
        return printRegisterCount;
    }

    public void setPrintRegisterCount(BigDecimal printRegisterCount) {
        this.printRegisterCount = printRegisterCount;
    }

    public Date getRePrintedDate() {
        return rePrintedDate;
    }

    public void setRePrintedDate(Date rePrintedDate) {
        this.rePrintedDate = rePrintedDate;
    }

    public Date getReRegisteredDate() {
        return reRegisteredDate;
    }

    public void setReRegisteredDate(Date reRegisteredDate) {
        this.reRegisteredDate = reRegisteredDate;
    }

    public String getCaraPenerimaan() {
        return caraPenerimaan;
    }

    public void setCaraPenerimaan(String caraPenerimaan) {
        this.caraPenerimaan = caraPenerimaan;
    }

    public String getNoSeriPajakDipungut() {
        return noSeriPajakDipungut;
    }

    public void setNoSeriPajakDipungut(String noSeriPajakDipungut) {
        this.noSeriPajakDipungut = noSeriPajakDipungut;
    }

    public String getNoSeriPajakDitanggung() {
        return noSeriPajakDitanggung;
    }

    public void setNoSeriPajakDitanggung(String noSeriPajakDitanggung) {
        this.noSeriPajakDitanggung = noSeriPajakDitanggung;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getPosAnggaran() {
        return posAnggaran;
    }

    public void setPosAnggaran(String posAnggaran) {
        this.posAnggaran = posAnggaran;
    }

    public String getBuktiPendukung() {
        return buktiPendukung;
    }

    public void setBuktiPendukung(String buktiPendukung) {
        this.buktiPendukung = buktiPendukung;
    }

    public Date getTanggalPendukung() {
        return tanggalPendukung;
    }

    public void setTanggalPendukung(Date tanggalPendukung) {
        this.tanggalPendukung = tanggalPendukung;
    }

    public BigDecimal getDppDipungut() {
        return dppDipungut;
    }

    public void setDppDipungut(BigDecimal dppDipungut) {
        this.dppDipungut = dppDipungut;
    }

    public BigDecimal getDppDitanggung() {
        return dppDitanggung;
    }

    public void setDppDitanggung(BigDecimal dppDitanggung) {
        this.dppDitanggung = dppDitanggung;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPengajuanBiayaId() {
        return pengajuanBiayaId;
    }

    public void setPengajuanBiayaId(String pengajuanBiayaId) {
        this.pengajuanBiayaId = pengajuanBiayaId;
    }
}
