package com.neurix.simrs.mobileapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 28/01/20 14:20
 */
public class TransaksiObatMobile implements Serializable {

    @SerializedName("idTransaksiObatDetail")
    @Expose
    private String idTransaksiObatDetail;

    @SerializedName("idApprovalObat")
    @Expose
    private String idApprovalObat;

    @SerializedName("idObat")
    @Expose
    private String idObat;

    @SerializedName("dosis")
    @Expose
    private String dosis;

    @SerializedName("idRacik")
    @Expose
    private String idRacik;

    @SerializedName("isRacik")
    @Expose
    private String isRacik;

    @SerializedName("namaRacik")
    @Expose
    private String namaRacik;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("idPermintaanResep")
    @Expose
    private String idPermintaanResep;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("namaObat")
    @Expose
    private String namaObat;

    @SerializedName("namaPoli")
    @Expose
    private String namaPoli;

    @SerializedName("namaPasien")
    @Expose
    private String namaPasien;

    @SerializedName("harga")
    @Expose
    private String harga;

    @SerializedName("totalHarga")
    @Expose
    private String totalHarga;

    @SerializedName("totalBayar")
    @Expose
    private String totalBayar;

    @SerializedName("totalDibayar")
    @Expose
    private String totalDibayar;

    @SerializedName("nominal")
    @Expose
    private String nominal;

    @SerializedName("kembalian")
    @Expose
    private String kembalian;

    @SerializedName("qtyApprove")
    @Expose
    private String qtyApprove;

    @SerializedName("qtyBox")
    @Expose
    private String qtyBox;

    @SerializedName("qtyLembar")
    @Expose
    private String qtyLembar;

    @SerializedName("qtyBiji")
    @Expose
    private String qtyBiji;

    @SerializedName("lembarPerBox")
    @Expose
    private String lembarPerBox;

    @SerializedName("bijiPerLembar")
    @Expose
    private String bijiPerLembar;

    @SerializedName("averageHargaBox")
    @Expose
    private String averageHargaBox;

    @SerializedName("averageHargaLembar")
    @Expose
    private String averageHargaLembar;

    @SerializedName("averageHargaBiji")
    @Expose
    private String averageHargaBiji;

    @SerializedName("flagDiterima")
    @Expose
    private String flagDiterima;

    @SerializedName("jenisSatuan")
    @Expose
    private String jenisSatuan;

    @SerializedName("hargaTerakhir")
    @Expose
    private String hargaTerakhir;

    @SerializedName("hargaPo")
    @Expose
    private String hargaPo;

    @SerializedName("merek")
    @Expose
    private String merek;

    @SerializedName("idPabrik")
    @Expose
    private String idPabrik;

    @SerializedName("sumQtyApprove")
    @Expose
    private String sumQtyApprove;

    @SerializedName("isFullOfQty")
    @Expose
    private String isFullOfQty;

    @SerializedName("noBatch")
    @Expose
    private String noBatch;

    @SerializedName("expDate")
    @Expose
    private String expDate;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("idSeqObat")
    @Expose
    private String idSeqObat;

    @SerializedName("idBarang")
    @Expose
    private String idBarang;

    @SerializedName("idPelayanan")
    @Expose
    private String idPelayanan;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("listTransaksiObat")
    @Expose
    private List<String> listTransaksiObat;

    @SerializedName("action")
    @Expose
    private String action;

    @SerializedName("flagVerifikasi")
    @Expose
    private String flagVerifikasi;

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getIsRacik() {
        return isRacik;
    }

    public void setIsRacik(String isRacik) {
        this.isRacik = isRacik;
    }

    public String getNamaRacik() {
        return namaRacik;
    }

    public void setNamaRacik(String namaRacik) {
        this.namaRacik = namaRacik;
    }

    public String getIdRacik() {
        return idRacik;
    }

    public void setIdRacik(String idRacik) {
        this.idRacik = idRacik;
    }
    public String getFlagVerifikasi() {
        return flagVerifikasi;
    }

    public void setFlagVerifikasi(String flagVerifikasi) {
        this.flagVerifikasi = flagVerifikasi;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getListTransaksiObat() {
        return listTransaksiObat;
    }

    public void setListTransaksiObat(List<String> listTransaksiObat) {
        this.listTransaksiObat = listTransaksiObat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdTransaksiObatDetail() {
        return idTransaksiObatDetail;
    }

    public void setIdTransaksiObatDetail(String idTransaksiObatDetail) {
        this.idTransaksiObatDetail = idTransaksiObatDetail;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdPermintaanResep() {
        return idPermintaanResep;
    }

    public void setIdPermintaanResep(String idPermintaanResep) {
        this.idPermintaanResep = idPermintaanResep;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getNamaPoli() {
        return namaPoli;
    }

    public void setNamaPoli(String namaPoli) {
        this.namaPoli = namaPoli;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getTotalDibayar() {
        return totalDibayar;
    }

    public void setTotalDibayar(String totalDibayar) {
        this.totalDibayar = totalDibayar;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKembalian() {
        return kembalian;
    }

    public void setKembalian(String kembalian) {
        this.kembalian = kembalian;
    }

    public String getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(String qtyApprove) {
        this.qtyApprove = qtyApprove;
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

    public String getFlagDiterima() {
        return flagDiterima;
    }

    public void setFlagDiterima(String flagDiterima) {
        this.flagDiterima = flagDiterima;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }

    public String getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(String hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public String getHargaPo() {
        return hargaPo;
    }

    public void setHargaPo(String hargaPo) {
        this.hargaPo = hargaPo;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public String getSumQtyApprove() {
        return sumQtyApprove;
    }

    public void setSumQtyApprove(String sumQtyApprove) {
        this.sumQtyApprove = sumQtyApprove;
    }

    public String getIsFullOfQty() {
        return isFullOfQty;
    }

    public void setIsFullOfQty(String isFullOfQty) {
        this.isFullOfQty = isFullOfQty;
    }

    public String getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(String noBatch) {
        this.noBatch = noBatch;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdSeqObat() {
        return idSeqObat;
    }

    public void setIdSeqObat(String idSeqObat) {
        this.idSeqObat = idSeqObat;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }
}
