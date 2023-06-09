package com.neurix.simrs.transaksi.permintaanvendor.model;

import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendor{
    private String idPermintaanVendor;
    private String idVendor;
    private String idApprovalObat;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private String createdWho;
    private String urlDocPo;
    private String branchId;

    List<TransaksiObatDetail> listOfTransaksiObatDetail = new ArrayList<>();

    private String idObat;
    private String namaObat;
    private String namaVendor;
    private BigInteger jumlahObat;
    private String jenisSatuan;
    private Integer jumlahSatuan;
    private BigDecimal harga;

    private String idPelayanan;

    private String stCreatedDate;

    private String keterangan;
    private String approvalFlag;

    private Boolean enableApprove = false;
    private String notFlagR;

    private Integer noBatch;

    private String isMobile;
    private String notaVendor;
    private String urlDoc;

    private String tglCair;
    private String noFaktur;
    private Date tanggalFaktur;
    private String noInvoice;
    private String noDo;
    private String tipeTransaksi;
    private String branchName;
    private Date tglDo;
    private Date tglInvoice;

    public Date getTglDo() {
        return tglDo;
    }

    public void setTglDo(Date tglDo) {
        this.tglDo = tglDo;
    }

    public Date getTglInvoice() {
        return tglInvoice;
    }

    public void setTglInvoice(Date tglInvoice) {
        this.tglInvoice = tglInvoice;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public Date getTanggalFaktur() {
        return tanggalFaktur;
    }

    public void setTanggalFaktur(Date tanggalFaktur) {
        this.tanggalFaktur = tanggalFaktur;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public String getNoDo() {
        return noDo;
    }

    public void setNoDo(String noDo) {
        this.noDo = noDo;
    }

    public String getTglCair() {
        return tglCair;
    }

    public void setTglCair(String tglCair) {
        this.tglCair = tglCair;
    }

    public String getNotaVendor() {
        return notaVendor;
    }

    public void setNotaVendor(String notaVendor) {
        this.notaVendor = notaVendor;
    }

    public String getUrlDoc() {
        return urlDoc;
    }

    public void setUrlDoc(String urlDoc) {
        this.urlDoc = urlDoc;
    }

    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    public String getNotFlagR() {
        return notFlagR;
    }

    public void setNotFlagR(String notFlagR) {
        this.notFlagR = notFlagR;
    }

    public Boolean getEnableApprove() {
        return enableApprove;
    }

    public void setEnableApprove(Boolean enableApprove) {
        this.enableApprove = enableApprove;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public List<TransaksiObatDetail> getListOfTransaksiObatDetail() {
        return listOfTransaksiObatDetail;
    }

    public void setListOfTransaksiObatDetail(List<TransaksiObatDetail> listOfTransaksiObatDetail) {
        this.listOfTransaksiObatDetail = listOfTransaksiObatDetail;
    }

    public Integer getJumlahSatuan() {
        return jumlahSatuan;
    }

    public void setJumlahSatuan(Integer jumlahSatuan) {
        this.jumlahSatuan = jumlahSatuan;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getNamaVendor() {
        return namaVendor;
    }

    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }

    public BigInteger getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(BigInteger jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdPermintaanVendor() {
        return idPermintaanVendor;
    }

    public void setIdPermintaanVendor(String idPermintaanVendor) {
        this.idPermintaanVendor = idPermintaanVendor;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
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

    public String getUrlDocPo() {
        return urlDocPo;
    }

    public void setUrlDocPo(String urlDocPo) {
        this.urlDocPo = urlDocPo;
    }

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }
}
