package com.neurix.simrs.transaksi.permintaanvendor.model;

import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

    List<ImtSimrsTransaksiObatDetailEntity> transaksiObatDetails = new ArrayList<>();

    private String idObat;
    private String namaObat;
    private String namaVendor;
    private BigInteger jumlahObat;
    private String jenisSatuan;
    private Integer jumlahSatuan;
    private BigDecimal harga;

    public List<ImtSimrsTransaksiObatDetailEntity> getTransaksiObatDetails() {
        return transaksiObatDetails;
    }

    public void setTransaksiObatDetails(List<ImtSimrsTransaksiObatDetailEntity> transaksiObatDetails) {
        this.transaksiObatDetails = transaksiObatDetails;
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
}
