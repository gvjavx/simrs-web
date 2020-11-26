package com.neurix.akuntansi.transaksi.jurnal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItJurnalEntity implements Serializable {
    private String noJurnal;
    private String tipeJurnalId;
    private Date tanggalJurnal;
    private String sumber;
    private String sumberDanaId;
    private String carryOver;
    private String mataUangId;
    private BigDecimal kurs;
    private String keterangan;
    private String branchId;
    private Date printedDate;
    private String printedFlag;
    private Date registeredDate;
    private String registeredFlag;
    private String registeredUser;
    private String registerId;
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

    private String pengajuanBiayaId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getPengajuanBiayaId() {
        return pengajuanBiayaId;
    }

    public void setPengajuanBiayaId(String pengajuanBiayaId) {
        this.pengajuanBiayaId = pengajuanBiayaId;
    }

    public String getRegisteredFlag() {
        return registeredFlag;
    }

    public void setRegisteredFlag(String registeredFlag) {
        this.registeredFlag = registeredFlag;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
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
