package com.neurix.simrs.master.obat.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Obat extends BaseModel {

    private String idObat;
    private String namaObat;
    private String idJenisObat;
    private BigInteger harga;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private BigInteger qty;
    private String branchId;

    private String jenisObat;
    private List<Obat> labelJenisObat;

    private BigInteger qtyBox;
    private BigInteger qtyLembar;
    private BigInteger qtyBiji;
    private BigInteger lembarPerBox;
    private BigInteger bijiPerLembar;
    private BigDecimal averageHargaBox;
    private BigDecimal averageHargaLembar;
    private BigDecimal averageHargaBiji;
    private String idPabrik;
    private String merk;

    private BigDecimal hargaTerakhir;
    private String idSeqObat;
    private Date expiredDate;
    private String idTransaksiDetail;

    private BigInteger qtyApprove;
    private Integer noBatch;

    private String idBarang;
    private String jenisSatuan;
    private BigDecimal hargaJual;
    private BigDecimal hargaNet;
    private BigDecimal diskon;

    private BigInteger minStok;

    private String isMinStok;

    private String flagBpjs;
    private String flagKronis;

    private String idPermintaanVendor;
    private String idApprovalObat;
    private String idVendor;
    private Timestamp tglRetur;
    private String namaVendor;
    private String idRetureObat;

    private String stTglRetur;
    private String noJurnal;
    private BigDecimal bruto;
    private BigDecimal netto;
    private String keterangan;
    private String idPelayanan;
    private String tahun;
    private String bulan;
    private String periode;
    private String namaPelayanan;
    private String type;
    private String bentuk;
    private Integer standarMargin;
    private Integer margin;
    private String flagKurangMargin;
    private String flagGeneric;
    private String isKp;
    private String idKategoriPersediaan;
    private BigDecimal hargaBeli;
    private BigDecimal diskonUmum;
    private BigDecimal hargaJualUmum;
    private Integer marginUmum;
    private BigDecimal hargaNetUmum;
    private String idBentuk;
    private String flagFormula;
    private String flagParenteral;
    private String idJenisBentuk;
    private String idJenisSub;

    public String getIdJenisBentuk() {
        return idJenisBentuk;
    }

    public void setIdJenisBentuk(String idJenisBentuk) {
        this.idJenisBentuk = idJenisBentuk;
    }

    public String getIdJenisSub() {
        return idJenisSub;
    }

    public void setIdJenisSub(String idJenisSub) {
        this.idJenisSub = idJenisSub;
    }

    public String getFlagParenteral() {
        return flagParenteral;
    }

    public void setFlagParenteral(String flagParenteral) {
        this.flagParenteral = flagParenteral;
    }

    public String getFlagFormula() {
        return flagFormula;
    }

    public void setFlagFormula(String flagFormula) {
        this.flagFormula = flagFormula;
    }

    public String getIdBentuk() {
        return idBentuk;
    }

    public void setIdBentuk(String idBentuk) {
        this.idBentuk = idBentuk;
    }

    public BigDecimal getHargaNetUmum() {
        return hargaNetUmum;
    }

    public void setHargaNetUmum(BigDecimal hargaNetUmum) {
        this.hargaNetUmum = hargaNetUmum;
    }

    public Integer getMarginUmum() {
        return marginUmum;
    }

    public void setMarginUmum(Integer marginUmum) {
        this.marginUmum = marginUmum;
    }

    public BigDecimal getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(BigDecimal hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public BigDecimal getDiskonUmum() {
        return diskonUmum;
    }

    public void setDiskonUmum(BigDecimal diskonUmum) {
        this.diskonUmum = diskonUmum;
    }

    public BigDecimal getHargaJualUmum() {
        return hargaJualUmum;
    }

    public void setHargaJualUmum(BigDecimal hargaJualUmum) {
        this.hargaJualUmum = hargaJualUmum;
    }

    public String getIdKategoriPersediaan() {
        return idKategoriPersediaan;
    }

    public void setIdKategoriPersediaan(String idKategoriPersediaan) {
        this.idKategoriPersediaan = idKategoriPersediaan;
    }

    public String getIsKp() {
        return isKp;
    }

    public void setIsKp(String isKp) {
        this.isKp = isKp;
    }

    public String getFlagGeneric() {
        return flagGeneric;
    }

    public void setFlagGeneric(String flagGeneric) {
        this.flagGeneric = flagGeneric;
    }

    public String getFlagKurangMargin() {
        return flagKurangMargin;
    }

    public void setFlagKurangMargin(String flagKurangMargin) {
        this.flagKurangMargin = flagKurangMargin;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Integer getStandarMargin() {
        return standarMargin;
    }

    public void setStandarMargin(Integer standarMargin) {
        this.standarMargin = standarMargin;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }

    private List<KandunganObat> kandunganObats = new ArrayList<>();

    public List<KandunganObat> getKandunganObats() {
        return kandunganObats;
    }

    public void setKandunganObats(List<KandunganObat> kandunganObats) {
        this.kandunganObats = kandunganObats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlagKronis() {
        return flagKronis;
    }

    public void setFlagKronis(String flagKronis) {
        this.flagKronis = flagKronis;
    }

    private String stTglFrom;
    private String stTglTo;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStTglFrom() {
        return stTglFrom;
    }

    public void setStTglFrom(String stTglFrom) {
        this.stTglFrom = stTglFrom;
    }

    public String getStTglTo() {
        return stTglTo;
    }

    public void setStTglTo(String stTglTo) {
        this.stTglTo = stTglTo;
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

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getStTglRetur() {
        return stTglRetur;
    }

    public void setStTglRetur(String stTglRetur) {
        this.stTglRetur = stTglRetur;
    }

    public String getIdRetureObat() {
        return idRetureObat;
    }

    public void setIdRetureObat(String idRetureObat) {
        this.idRetureObat = idRetureObat;
    }

    public String getNamaVendor() {
        return namaVendor;
    }

    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }

    public Timestamp getTglRetur() {
        return tglRetur;
    }

    public void setTglRetur(Timestamp tglRetur) {
        this.tglRetur = tglRetur;
    }

    public String getIdPermintaanVendor() {
        return idPermintaanVendor;
    }

    public void setIdPermintaanVendor(String idPermintaanVendor) {
        this.idPermintaanVendor = idPermintaanVendor;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public String getFlagBpjs() {
        return flagBpjs;
    }

    public void setFlagBpjs(String flagBpjs) {
        this.flagBpjs = flagBpjs;
    }

    public String getIsMinStok() {
        return isMinStok;
    }

    public void setIsMinStok(String isMinStok) {
        this.isMinStok = isMinStok;
    }

    public BigInteger getMinStok() {
        return minStok;
    }

    public void setMinStok(BigInteger minStok) {
        this.minStok = minStok;
    }

    public BigDecimal getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(BigDecimal hargaJual) {
        this.hargaJual = hargaJual;
    }

    public BigDecimal getHargaNet() {
        return hargaNet;
    }

    public void setHargaNet(BigDecimal hargaNet) {
        this.hargaNet = hargaNet;
    }

    public BigDecimal getDiskon() {
        return diskon;
    }

    public void setDiskon(BigDecimal diskon) {
        this.diskon = diskon;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public BigInteger getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(BigInteger qtyApprove) {
        this.qtyApprove = qtyApprove;
    }

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }

    public String getIdTransaksiDetail() {
        return idTransaksiDetail;
    }

    public void setIdTransaksiDetail(String idTransaksiDetail) {
        this.idTransaksiDetail = idTransaksiDetail;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public BigDecimal getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(BigDecimal hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public String getIdSeqObat() {
        return idSeqObat;
    }

    public void setIdSeqObat(String idSeqObat) {
        this.idSeqObat = idSeqObat;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }

    public List<Obat> getLabelJenisObat() {
        return labelJenisObat;
    }

    public void setLabelJenisObat(List<Obat> labelJenisObat) {
        this.labelJenisObat = labelJenisObat;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public String getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(String jenisObat) {
        this.jenisObat = jenisObat;
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

    public String getIdJenisObat() {
        return idJenisObat;
    }

    public void setIdJenisObat(String idJenisObat) {
        this.idJenisObat = idJenisObat;
    }

    public BigInteger getHarga() {
        return harga;
    }

    public void setHarga(BigInteger harga) {
        this.harga = harga;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
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
}