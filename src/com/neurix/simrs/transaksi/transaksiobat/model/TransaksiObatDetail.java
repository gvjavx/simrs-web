package com.neurix.simrs.transaksi.transaksiobat.model;

import com.neurix.common.model.BaseModel;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.transketeranganobat.model.ItSimrsKeteranganResepEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransaksiObatDetail extends BaseModel {

    private String idTransaksiObatDetail;
    private String idApprovalObat;
    private String idObat;
    private BigInteger qty;
    private String keterangan;

    private String idPermintaanResep;
    private String branchId;

    private String namaObat;
    private String namaPoli;
    private String namaPasien;

    private BigInteger harga;
    private BigInteger totalHarga;

    private BigInteger totalBayar;
    private BigInteger totalDibayar;
    private BigInteger nominal;
    private BigInteger kembalian;
    private BigInteger ppnBayar;

    private BigInteger qtyApprove;

    private BigInteger qtyBox;
    private BigInteger qtyLembar;
    private BigInteger qtyBiji;
    private BigInteger lembarPerBox;
    private BigInteger bijiPerLembar;
    private BigDecimal averageHargaBox;
    private BigDecimal averageHargaLembar;
    private BigDecimal averageHargaBiji;
    private String flagDiterima;
    private String jenisSatuan;

    private BigDecimal hargaTerakhir;

    private BigDecimal hargaPo;

    private String merek;
    private String idPabrik;

    private BigInteger sumQtyApprove;
    private String isFullOfQty;
    private Integer noBatch;
    private Date expDate;
    private String status;
    private String idSeqObat;
    private String idBarang;
    private String idPelayanan;

    private String stTotalbayar;

    private String flagVerifikasi;
    private String noJurnal;
    private String flagRacik;
    private Integer hariKronis;
    private String flagKronisDiambil;

    private String idPasien;
    private String idDetailCheckup;

    private String jenisPeriksaPasien;
    private String noCheckup;

    private String tipeObat;
    private BigInteger minStok;

    private BigDecimal diskon;
    private BigDecimal bruto;
    private BigDecimal netto;

    private String namaObatBaru;
    private String rekanan;
    private String jenisResep;
    private String idVendor;
    private String idPelayananTujuan;

    private String ttdDokter;
    private BigInteger qtyAfter;
    private BigInteger qtyReture;

    private BigDecimal hargaRata;
    private String branchAsal;
    private BigDecimal hargaJual;
    private String tipePermintaan;
    private String idRacik;
    private String namaRacik;
    private String isOrder;
    private List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
    private List<ItSimrsRiwayatTindakanEntity> biayaTambahanList = new ArrayList<>();
    private List<ItSimrsKeteranganResepEntity> keteranganResepEntityList = new ArrayList<>();
    private String idPabrikObat;

    public String getIdPabrikObat() {
        return idPabrikObat;
    }

    public void setIdPabrikObat(String idPabrikObat) {
        this.idPabrikObat = idPabrikObat;
    }

    public List<ItSimrsKeteranganResepEntity> getKeteranganResepEntityList() {
        return keteranganResepEntityList;
    }

    public void setKeteranganResepEntityList(List<ItSimrsKeteranganResepEntity> keteranganResepEntityList) {
        this.keteranganResepEntityList = keteranganResepEntityList;
    }

    public List<ItSimrsRiwayatTindakanEntity> getBiayaTambahanList() {
        return biayaTambahanList;
    }

    public void setBiayaTambahanList(List<ItSimrsRiwayatTindakanEntity> biayaTambahanList) {
        this.biayaTambahanList = biayaTambahanList;
    }

    public List<TransaksiObatDetail> getObatDetailList() {
        return obatDetailList;
    }

    public void setObatDetailList(List<TransaksiObatDetail> obatDetailList) {
        this.obatDetailList = obatDetailList;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public String getIdRacik() {
        return idRacik;
    }

    public void setIdRacik(String idRacik) {
        this.idRacik = idRacik;
    }

    public String getNamaRacik() {
        return namaRacik;
    }

    public void setNamaRacik(String namaRacik) {
        this.namaRacik = namaRacik;
    }

    public String getTipePermintaan() {
        return tipePermintaan;
    }

    public void setTipePermintaan(String tipePermintaan) {
        this.tipePermintaan = tipePermintaan;
    }

    public BigDecimal getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(BigDecimal hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getBranchAsal() {
        return branchAsal;
    }

    public void setBranchAsal(String branchAsal) {
        this.branchAsal = branchAsal;
    }

    public BigDecimal getHargaRata() {
        return hargaRata;
    }

    public void setHargaRata(BigDecimal hargaRata) {
        this.hargaRata = hargaRata;
    }

    public BigInteger getQtyReture() {
        return qtyReture;
    }

    public void setQtyReture(BigInteger qtyReture) {
        this.qtyReture = qtyReture;
    }

    public BigInteger getQtyAfter() {
        return qtyAfter;
    }

    public void setQtyAfter(BigInteger qtyAfter) {
        this.qtyAfter = qtyAfter;
    }

    public String getTtdDokter() {
        return ttdDokter;
    }

    public void setTtdDokter(String ttdDokter) {
        this.ttdDokter = ttdDokter;
    }

    public String getIdPelayananTujuan() {
        return idPelayananTujuan;
    }

    public void setIdPelayananTujuan(String idPelayananTujuan) {
        this.idPelayananTujuan = idPelayananTujuan;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public String getJenisResep() {
        return jenisResep;
    }

    public void setJenisResep(String jenisResep) {
        this.jenisResep = jenisResep;
    }

    public String getRekanan() {
        return rekanan;
    }

    public void setRekanan(String rekanan) {
        this.rekanan = rekanan;
    }

    public String getNamaObatBaru() {
        return namaObatBaru;
    }

    public void setNamaObatBaru(String namaObatBaru) {
        this.namaObatBaru = namaObatBaru;
    }

    public BigDecimal getDiskon() {
        return diskon;
    }

    public void setDiskon(BigDecimal diskon) {
        this.diskon = diskon;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    public void setNetto(BigDecimal netto) {
        this.netto = netto;
    }

    public BigInteger getMinStok() {
        return minStok;
    }

    public void setMinStok(BigInteger minStok) {
        this.minStok = minStok;
    }

    public String getTipeObat() {
        return tipeObat;
    }

    public void setTipeObat(String tipeObat) {
        this.tipeObat = tipeObat;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getJenisPeriksaPasien() {
        return jenisPeriksaPasien;
    }

    public void setJenisPeriksaPasien(String jenisPeriksaPasien) {
        this.jenisPeriksaPasien = jenisPeriksaPasien;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigInteger getPpnBayar() {
        return ppnBayar;
    }

    public void setPpnBayar(BigInteger ppnBayar) {
        this.ppnBayar = ppnBayar;
    }

    public String getFlagVerifikasi() {
        return flagVerifikasi;
    }

    public void setFlagVerifikasi(String flagVerifikasi) {
        this.flagVerifikasi = flagVerifikasi;
    }

    public String getStTotalbayar() {
        return stTotalbayar;
    }

    public void setStTotalbayar(String stTotalbayar) {
        this.stTotalbayar = stTotalbayar;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdSeqObat() {
        return idSeqObat;
    }

    public void setIdSeqObat(String idSeqObat) {
        this.idSeqObat = idSeqObat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getIsFullOfQty() {
        return isFullOfQty;
    }

    public void setIsFullOfQty(String isFullOfQty) {
        this.isFullOfQty = isFullOfQty;
    }

    public BigInteger getSumQtyApprove() {
        return sumQtyApprove;
    }

    public void setSumQtyApprove(BigInteger sumQtyApprove) {
        this.sumQtyApprove = sumQtyApprove;
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

    public BigDecimal getHargaPo() {
        return hargaPo;
    }

    public void setHargaPo(BigDecimal hargaPo) {
        this.hargaPo = hargaPo;
    }

    public BigDecimal getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(BigDecimal hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }

    public String getFlagDiterima() {
        return flagDiterima;
    }

    public void setFlagDiterima(String flagDiterima) {
        this.flagDiterima = flagDiterima;
    }

    public BigInteger getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(BigInteger qtyApprove) {
        this.qtyApprove = qtyApprove;
    }

    public BigInteger getNominal() {
        return nominal;
    }

    public void setNominal(BigInteger nominal) {
        this.nominal = nominal;
    }

    public BigInteger getKembalian() {
        return kembalian;
    }

    public void setKembalian(BigInteger kembalian) {
        this.kembalian = kembalian;
    }

    public BigInteger getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(BigInteger totalBayar) {
        this.totalBayar = totalBayar;
    }

    public BigInteger getTotalDibayar() {
        return totalDibayar;
    }

    public void setTotalDibayar(BigInteger totalDibayar) {
        this.totalDibayar = totalDibayar;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
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

    public BigInteger getHarga() {
        return harga;
    }

    public void setHarga(BigInteger harga) {
        this.harga = harga;
    }

    public BigInteger getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(BigInteger totalHarga) {
        this.totalHarga = totalHarga;
    }

    public BigInteger getQtyBox() {
        return qtyBox;
    }

    public void setQtyBox(BigInteger qtyBox) {
        this.qtyBox = qtyBox;
    }

    public BigInteger getQtyLembar() {
        return qtyLembar;
    }

    public void setQtyLembar(BigInteger qtyLembar) {
        this.qtyLembar = qtyLembar;
    }

    public BigInteger getQtyBiji() {
        return qtyBiji;
    }

    public void setQtyBiji(BigInteger qtyBiji) {
        this.qtyBiji = qtyBiji;
    }

    public BigInteger getLembarPerBox() {
        return lembarPerBox;
    }

    public void setLembarPerBox(BigInteger lembarPerBox) {
        this.lembarPerBox = lembarPerBox;
    }

    public BigInteger getBijiPerLembar() {
        return bijiPerLembar;
    }

    public void setBijiPerLembar(BigInteger bijiPerLembar) {
        this.bijiPerLembar = bijiPerLembar;
    }

    public BigDecimal getAverageHargaBox() {
        return averageHargaBox;
    }

    public void setAverageHargaBox(BigDecimal averageHargaBox) {
        this.averageHargaBox = averageHargaBox;
    }

    public BigDecimal getAverageHargaLembar() {
        return averageHargaLembar;
    }

    public void setAverageHargaLembar(BigDecimal averageHargaLembar) {
        this.averageHargaLembar = averageHargaLembar;
    }

    public BigDecimal getAverageHargaBiji() {
        return averageHargaBiji;
    }

    public void setAverageHargaBiji(BigDecimal averageHargaBiji) {
        this.averageHargaBiji = averageHargaBiji;
    }

    public String getFlagRacik() {
        return flagRacik;
    }

    public void setFlagRacik(String flagRacik) {
        this.flagRacik = flagRacik;
    }

    public Integer getHariKronis() {
        return hariKronis;
    }

    public void setHariKronis(Integer hariKronis) {
        this.hariKronis = hariKronis;
    }

    public String getFlagKronisDiambil() {
        return flagKronisDiambil;
    }

    public void setFlagKronisDiambil(String flagKronisDiambil) {
        this.flagKronisDiambil = flagKronisDiambil;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }
}