package com.neurix.simrs.mobileapi.model;

/**
 * @author gondok
 * Tuesday, 28/01/20 10:46
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PurchaseOrderMobile implements Serializable {

    @SerializedName("idPermintaanVendor")
    @Expose
    private String idPermintaanVendor;

    @SerializedName("idVendor")
    @Expose
    private String idVendor;

    @SerializedName("idApprovalObat")
    @Expose
    private String idApprovalObat;

    @SerializedName("flag")
    @Expose
    private String flag;

    @SerializedName("action")
    @Expose
    private String action;

    @SerializedName("urlDocPo")
    @Expose
    private String urlDocPo;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("idObat")
    @Expose
    private String idObat;

    @SerializedName("namaObat")
    @Expose
    private String namaObat;

    @SerializedName("namaVendor")
    @Expose
    private String namaVendor;

    @SerializedName("jumlahObat")
    @Expose
    private String jumlahObat;

    @SerializedName("jenisSatuan")
    @Expose
    private String jenisSatuan;

    @SerializedName("jumlahSatuan")
    @Expose
    private String jumlahSatuan;

    @SerializedName("harga")
    @Expose
    private String harga;

    @SerializedName("idPelayanan")
    @Expose
    private String idPelayanan;

    @SerializedName("stCreatedDate")
    @Expose
    private String stCreatedDate;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("approvalFlag")
    @Expose
    private String approvalFlag;

    @SerializedName("notFlagR")
    @Expose
    private String notFlagR;

    @SerializedName("noBatch")
    @Expose
    private String noBatch;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getUrlDocPo() {
        return urlDocPo;
    }

    public void setUrlDocPo(String urlDocPo) {
        this.urlDocPo = urlDocPo;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(String jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }

    public String getJumlahSatuan() {
        return jumlahSatuan;
    }

    public void setJumlahSatuan(String jumlahSatuan) {
        this.jumlahSatuan = jumlahSatuan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getNotFlagR() {
        return notFlagR;
    }

    public void setNotFlagR(String notFlagR) {
        this.notFlagR = notFlagR;
    }

    public String getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(String noBatch) {
        this.noBatch = noBatch;
    }
}

