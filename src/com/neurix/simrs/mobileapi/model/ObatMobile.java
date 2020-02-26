package com.neurix.simrs.mobileapi.model;

import com.neurix.simrs.master.obat.model.Obat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author gondok
 * Monday, 03/02/20 11:11
 */
public class ObatMobile implements Serializable {
    private String idObat;
    private String namaObat;
    private String idJenisObat;
    private String harga;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;
    private String qty;
    private String branchId;

    private String jenisObat;
    private List<Obat> labelJenisObat;

    private String qtyBox;
    private String qtyLembar;
    private String qtyBiji;
    private String lembarPerBox;
    private String bijiPerLembar;
    private String averageHargaBox;
    private String averageHargaLembar;
    private String averageHargaBiji;
    private String idPabrik;
    private String merk;

    private String hargaTerakhir;
    private String idSeqObat;
    private String expiredDate;
    private String idTransaksiDetail;

    private String qtyApprove;
    private String noBatch;

    private String idBarang;
    private String jenisSatuan;

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

    public String getIdJenisObat() {
        return idJenisObat;
    }

    public void setIdJenisObat(String idJenisObat) {
        this.idJenisObat = idJenisObat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(String jenisObat) {
        this.jenisObat = jenisObat;
    }

    public List<Obat> getLabelJenisObat() {
        return labelJenisObat;
    }

    public void setLabelJenisObat(List<Obat> labelJenisObat) {
        this.labelJenisObat = labelJenisObat;
    }

    public String getQtyBox() {
        return qtyBox;
    }

    public void setQtyBox(String qtyBox) {
        this.qtyBox = qtyBox;
    }

    public String getQtyLembar() {
        return qtyLembar;
    }

    public void setQtyLembar(String qtyLembar) {
        this.qtyLembar = qtyLembar;
    }

    public String getQtyBiji() {
        return qtyBiji;
    }

    public void setQtyBiji(String qtyBiji) {
        this.qtyBiji = qtyBiji;
    }

    public String getLembarPerBox() {
        return lembarPerBox;
    }

    public void setLembarPerBox(String lembarPerBox) {
        this.lembarPerBox = lembarPerBox;
    }

    public String getBijiPerLembar() {
        return bijiPerLembar;
    }

    public void setBijiPerLembar(String bijiPerLembar) {
        this.bijiPerLembar = bijiPerLembar;
    }

    public String getAverageHargaBox() {
        return averageHargaBox;
    }

    public void setAverageHargaBox(String averageHargaBox) {
        this.averageHargaBox = averageHargaBox;
    }

    public String getAverageHargaLembar() {
        return averageHargaLembar;
    }

    public void setAverageHargaLembar(String averageHargaLembar) {
        this.averageHargaLembar = averageHargaLembar;
    }

    public String getAverageHargaBiji() {
        return averageHargaBiji;
    }

    public void setAverageHargaBiji(String averageHargaBiji) {
        this.averageHargaBiji = averageHargaBiji;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(String hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public String getIdSeqObat() {
        return idSeqObat;
    }

    public void setIdSeqObat(String idSeqObat) {
        this.idSeqObat = idSeqObat;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getIdTransaksiDetail() {
        return idTransaksiDetail;
    }

    public void setIdTransaksiDetail(String idTransaksiDetail) {
        this.idTransaksiDetail = idTransaksiDetail;
    }

    public String getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(String qtyApprove) {
        this.qtyApprove = qtyApprove;
    }

    public String getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(String noBatch) {
        this.noBatch = noBatch;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }
}
