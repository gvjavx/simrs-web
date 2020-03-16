package com.neurix.hris.transaksi.sppd.model;

import com.neurix.common.model.BaseModel;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SppdPerson extends BaseModel {
    private String sppdPersonId ;
    private String personId ;
    private String divisiId ;
    private String divisiName ;
    private String branchId ;
    private String biayaMakan;
    private String biayaLain;
    private String transportLokal;
    private String transportLokasi;
    private BigDecimal tiketPergi;
    private BigDecimal tiketKembali;
    private BigDecimal biayaLokalBerangkat;
    private BigDecimal biayaLokalKembali;
    private BigDecimal biayaTujuan;
    private String tiket;
    private BigDecimal biayaTambahan;
    private String biayaTambahanSppd;
    private String jumlah;
    private String total;
    private Date tanggalBerangkatRealisasi;
    private Date tanggalKembaliRealisasi;
    private String stTanggalBerangkatRealisasi;
    private String stTanggalKembaliRealisasi;
    private String berangkatViaRealisasi;
    private String pulangViaRealisasi;
    private String noteAtasanTransport;
    private String noteSdmTransport;
    private String penginapan;

    private String sppdTanggalId1;
    private String sppdTanggalId2;

    private String tmpApprove;
    private String tmpClosed;

    private String kelompokId;
    private String golonganId;

    private String approvalId;
    private String approvalFlag;
    private Timestamp approvalDate;
    private DateTime tanggalPergi;
    private String approvalName ;
    private String notApprovalNote ;
    private String approvalSdmId ;
    private String approvalSdmName ;
    private String approvalSdmFlag ;
    private Timestamp approvalSdmDate ;
    private String notApprovalSdmNote;

    public String getSppdTanggalId1() {
        return sppdTanggalId1;
    }

    public void setSppdTanggalId1(String sppdTanggalId1) {
        this.sppdTanggalId1 = sppdTanggalId1;
    }

    public String getSppdTanggalId2() {
        return sppdTanggalId2;
    }

    public void setSppdTanggalId2(String sppdTanggalId2) {
        this.sppdTanggalId2 = sppdTanggalId2;
    }

    private String personName ;
    private String branchName ;
    private String positionName ;
    private String tipePerson ;
    private String positionId ;
    private String berangkatDari ;
    private String berangkatDariNama ;
    private String dinas;
    private String tujuanKe ;
    private String tujuanKeNama ;
    private Date tanggalBerangkat ;
    private Date tanggalKembali ;
    private String stTanggalBerangkat ;
    private String stTanggalKembali ;
    private String pejabatSementara ;
    private String pejabatSementaraNama ;
    private String pejabatSementaraBranch ;
    private String pejabatSementaraDivisi ;
    private String pejabatSementaraPosition ;
    private String keterangan ;
    private String flagReroute ;
    private String sppdId ;
    private String berangkatVia ;
    private String kembaliVia ;
    private String linkAtasan ;
    private String linkSdm ;

    private String userIdActive;
    private String userNameActive;

    private boolean sppdBlmApprove = false;
    private boolean sppdApprove = false;
    private boolean sppdApproveStatus = false;
    private boolean sppdApproveSdmStatus = false;
    private boolean sppdBlmApproveSdm = false;
    private boolean sppdApproveSdm = false;
    private boolean sppdClosed = false;

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public BigDecimal getBiayaTujuan() {
        return biayaTujuan;
    }

    public void setBiayaTujuan(BigDecimal biayaTujuan) {
        this.biayaTujuan = biayaTujuan;
    }

    public BigDecimal getBiayaLokalBerangkat() {
        return biayaLokalBerangkat;
    }

    public void setBiayaLokalBerangkat(BigDecimal biayaLokalBerangkat) {
        this.biayaLokalBerangkat = biayaLokalBerangkat;
    }

    public BigDecimal getBiayaLokalKembali() {
        return biayaLokalKembali;
    }

    public void setBiayaLokalKembali(BigDecimal biayaLokalKembali) {
        this.biayaLokalKembali = biayaLokalKembali;
    }

    public String getUserIdActive() {
        return userIdActive;
    }

    public void setUserIdActive(String userIdActive) {
        this.userIdActive = userIdActive;
    }

    public String getUserNameActive() {
        return userNameActive;
    }

    public void setUserNameActive(String userNameActive) {
        this.userNameActive = userNameActive;
    }

    public String getBiayaTambahanSppd() {
        return biayaTambahanSppd;
    }

    public void setBiayaTambahanSppd(String biayaTambahanSppd) {
        this.biayaTambahanSppd = biayaTambahanSppd;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }

    public String getBiayaLain() {
        return biayaLain;
    }

    public void setBiayaLain(String biayaLain) {
        this.biayaLain = biayaLain;
    }

    public String getBiayaMakan() {
        return biayaMakan;
    }

    public void setBiayaMakan(String biayaMakan) {
        this.biayaMakan = biayaMakan;
    }

    public DateTime getTanggalPergi() {
        return tanggalPergi;
    }

    public void setTanggalPergi(DateTime tanggalPergi) {
        this.tanggalPergi = tanggalPergi;
    }

    public String getBerangkatDariNama() {
        return berangkatDariNama;
    }

    public void setBerangkatDariNama(String berangkatDariNama) {
        this.berangkatDariNama = berangkatDariNama;
    }

    public String getTujuanKeNama() {
        return tujuanKeNama;
    }

    public void setTujuanKeNama(String tujuanKeNama) {
        this.tujuanKeNama = tujuanKeNama;
    }

    public String getBerangkatViaRealisasi() {
        return berangkatViaRealisasi;
    }

    public void setBerangkatViaRealisasi(String berangkatViaRealisasi) {
        this.berangkatViaRealisasi = berangkatViaRealisasi;
    }

    public String getNoteAtasanTransport() {
        return noteAtasanTransport;
    }

    public void setNoteAtasanTransport(String noteAtasanTransport) {
        this.noteAtasanTransport = noteAtasanTransport;
    }

    public String getNoteSdmTransport() {
        return noteSdmTransport;
    }

    public void setNoteSdmTransport(String noteSdmTransport) {
        this.noteSdmTransport = noteSdmTransport;
    }

    public String getPenginapan() {
        return penginapan;
    }

    public void setPenginapan(String penginapan) {
        this.penginapan = penginapan;
    }

    public String getPulangViaRealisasi() {
        return pulangViaRealisasi;
    }

    public void setPulangViaRealisasi(String pulangViaRealisasi) {
        this.pulangViaRealisasi = pulangViaRealisasi;
    }

    public String getStTanggalBerangkatRealisasi() {
        return stTanggalBerangkatRealisasi;
    }

    public void setStTanggalBerangkatRealisasi(String stTanggalBerangkatRealisasi) {
        this.stTanggalBerangkatRealisasi = stTanggalBerangkatRealisasi;
    }

    public String getStTanggalKembaliRealisasi() {
        return stTanggalKembaliRealisasi;
    }

    public void setStTanggalKembaliRealisasi(String stTanggalKembaliRealisasi) {
        this.stTanggalKembaliRealisasi = stTanggalKembaliRealisasi;
    }

    public Date getTanggalBerangkatRealisasi() {
        return tanggalBerangkatRealisasi;
    }

    public void setTanggalBerangkatRealisasi(Date tanggalBerangkatRealisasi) {
        this.tanggalBerangkatRealisasi = tanggalBerangkatRealisasi;
    }

    public Date getTanggalKembaliRealisasi() {
        return tanggalKembaliRealisasi;
    }

    public void setTanggalKembaliRealisasi(Date tanggalKembaliRealisasi) {
        this.tanggalKembaliRealisasi = tanggalKembaliRealisasi;
    }

    public String getPejabatSementaraBranch() {
        return pejabatSementaraBranch;
    }

    public void setPejabatSementaraBranch(String pejabatSementaraBranch) {
        this.pejabatSementaraBranch = pejabatSementaraBranch;
    }

    public String getPejabatSementaraDivisi() {
        return pejabatSementaraDivisi;
    }

    public void setPejabatSementaraDivisi(String pejabatSementaraDivisi) {
        this.pejabatSementaraDivisi = pejabatSementaraDivisi;
    }

    public String getPejabatSementaraPosition() {
        return pejabatSementaraPosition;
    }

    public void setPejabatSementaraPosition(String pejabatSementaraPosition) {
        this.pejabatSementaraPosition = pejabatSementaraPosition;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTransportLokal() {
        return transportLokal;
    }

    public void setTransportLokal(String transportLokal) {
        this.transportLokal = transportLokal;
    }

    public String getTransportLokasi() {
        return transportLokasi;
    }

    public void setTransportLokasi(String transportLokasi) {
        this.transportLokasi = transportLokasi;
    }

    public BigDecimal getBiayaTambahan() {
        return biayaTambahan;
    }

    public void setBiayaTambahan(BigDecimal biayaTambahan) {
        this.biayaTambahan = biayaTambahan;
    }

    public BigDecimal getTiketKembali() {
        return tiketKembali;
    }

    public void setTiketKembali(BigDecimal tiketKembali) {
        this.tiketKembali = tiketKembali;
    }

    public BigDecimal getTiketPergi() {
        return tiketPergi;
    }

    public void setTiketPergi(BigDecimal tiketPergi) {
        this.tiketPergi = tiketPergi;
    }

    public boolean isSppdClosed() {
        return sppdClosed;
    }

    public void setSppdClosed(boolean sppdClosed) {
        this.sppdClosed = sppdClosed;
    }

    public String getTmpClosed() {
        return tmpClosed;
    }

    public void setTmpClosed(String tmpClosed) {
        this.tmpClosed = tmpClosed;
    }

    public String getDinas() {
        return dinas;
    }

    public void setDinas(String dinas) {
        this.dinas = dinas;
    }

    public String getLinkAtasan() {
        return linkAtasan;
    }

    public void setLinkAtasan(String linkAtasan) {
        this.linkAtasan = linkAtasan;
    }

    public String getLinkSdm() {
        return linkSdm;
    }

    public void setLinkSdm(String linkSdm) {
        this.linkSdm = linkSdm;
    }

    public boolean isSppdApproveSdmStatus() {
        return sppdApproveSdmStatus;
    }

    public void setSppdApproveSdmStatus(boolean sppdApproveSdmStatus) {
        this.sppdApproveSdmStatus = sppdApproveSdmStatus;
    }

    public boolean isSppdBlmApprove() {
        return sppdBlmApprove;
    }

    public void setSppdBlmApprove(boolean sppdBlmApprove) {
        this.sppdBlmApprove = sppdBlmApprove;
    }

    public boolean isSppdApprove() {
        return sppdApprove;
    }

    public void setSppdApprove(boolean sppdApprove) {
        this.sppdApprove = sppdApprove;
    }

    public boolean isSppdApproveStatus() {
        return sppdApproveStatus;
    }

    public void setSppdApproveStatus(boolean sppdApproveStatus) {
        this.sppdApproveStatus = sppdApproveStatus;
    }

    public boolean isSppdBlmApproveSdm() {
        return sppdBlmApproveSdm;
    }

    public void setSppdBlmApproveSdm(boolean sppdBlmApproveSdm) {
        this.sppdBlmApproveSdm = sppdBlmApproveSdm;
    }

    public boolean isSppdApproveSdm() {
        return sppdApproveSdm;
    }

    public void setSppdApproveSdm(boolean sppdApproveSdm) {
        this.sppdApproveSdm = sppdApproveSdm;
    }

    public String getTmpApprove() {
        return tmpApprove;
    }

    public void setTmpApprove(String tmpApprove) {
        this.tmpApprove = tmpApprove;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getApprovalSdmId() {
        return approvalSdmId;
    }

    public void setApprovalSdmId(String approvalSdmId) {
        this.approvalSdmId = approvalSdmId;
    }

    public String getApprovalSdmName() {
        return approvalSdmName;
    }

    public void setApprovalSdmName(String approvalSdmName) {
        this.approvalSdmName = approvalSdmName;
    }

    public String getApprovalSdmFlag() {
        return approvalSdmFlag;
    }

    public void setApprovalSdmFlag(String approvalSdmFlag) {
        this.approvalSdmFlag = approvalSdmFlag;
    }

    public Timestamp getApprovalSdmDate() {
        return approvalSdmDate;
    }

    public void setApprovalSdmDate(Timestamp approvalSdmDate) {
        this.approvalSdmDate = approvalSdmDate;
    }

    public String getNotApprovalSdmNote() {
        return notApprovalSdmNote;
    }

    public void setNotApprovalSdmNote(String notApprovalSdmNote) {
        this.notApprovalSdmNote = notApprovalSdmNote;
    }

    public String getPejabatSementaraNama() {
        return pejabatSementaraNama;
    }

    public void setPejabatSementaraNama(String pejabatSementaraNama) {
        this.pejabatSementaraNama = pejabatSementaraNama;
    }

    public String getBerangkatVia() {
        return berangkatVia;
    }

    public void setBerangkatVia(String berangkatVia) {
        this.berangkatVia = berangkatVia;
    }

    public String getKembaliVia() {
        return kembaliVia;
    }

    public void setKembaliVia(String kembaliVia) {
        this.kembaliVia = kembaliVia;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getSppdPersonId() {
        return sppdPersonId;
    }

    public void setSppdPersonId(String sppdPersonId) {
        this.sppdPersonId = sppdPersonId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTipePerson() {
        return tipePerson;
    }

    public void setTipePerson(String tipePerson) {
        this.tipePerson = tipePerson;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getBerangkatDari() {
        return berangkatDari;
    }

    public void setBerangkatDari(String berangkatDari) {
        this.berangkatDari = berangkatDari;
    }

    public String getTujuanKe() {
        return tujuanKe;
    }

    public void setTujuanKe(String tujuanKe) {
        this.tujuanKe = tujuanKe;
    }

    public Date getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(Date tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getStTanggalBerangkat() {
        return stTanggalBerangkat;
    }

    public void setStTanggalBerangkat(String stTanggalBerangkat) {
        this.stTanggalBerangkat = stTanggalBerangkat;
    }

    public String getStTanggalKembali() {
        return stTanggalKembali;
    }

    public void setStTanggalKembali(String stTanggalKembali) {
        this.stTanggalKembali = stTanggalKembali;
    }

    public String getPejabatSementara() {
        return pejabatSementara;
    }

    public void setPejabatSementara(String pejabatSementara) {
        this.pejabatSementara = pejabatSementara;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlagReroute() {
        return flagReroute;
    }

    public void setFlagReroute(String flagReroute) {
        this.flagReroute = flagReroute;
    }

    public String getSppdId() {
        return sppdId;
    }

    public void setSppdId(String sppdId) {
        this.sppdId = sppdId;
    }

}