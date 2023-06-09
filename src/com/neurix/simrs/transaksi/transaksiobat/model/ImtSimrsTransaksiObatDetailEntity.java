package com.neurix.simrs.transaksi.transaksiobat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ImtSimrsTransaksiObatDetailEntity implements Serializable{

    private String idTransaksiObatDetail;
    private String idApprovalObat;
    private String idObat;
    private BigInteger qty;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String keterangan;
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

    private String namaObatBaru;
    private String idPabrik;
    private String mrek;
    private String flagVerifikasi;
    private String flagRacik;
    private Integer hariKronis;
    private String flagKronisDiambil;
    private String idPelayanan;

    private String flagObatBpjs;
    private String idRacik;
    private String namaRacik;
    private String jenisResep;

    public String getJenisResep() {
        return jenisResep;
    }

    public void setJenisResep(String jenisResep) {
        this.jenisResep = jenisResep;
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

    public String getFlagObatBpjs() {
        return flagObatBpjs;
    }

    public void setFlagObatBpjs(String flagObatBpjs) {
        this.flagObatBpjs = flagObatBpjs;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlagVerifikasi() {
        return flagVerifikasi;
    }

    public void setFlagVerifikasi(String flagVerifikasi) {
        this.flagVerifikasi = flagVerifikasi;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public String getMrek() {
        return mrek;
    }

    public void setMrek(String mrek) {
        this.mrek = mrek;
    }

    public String getNamaObatBaru() {
        return namaObatBaru;
    }

    public void setNamaObatBaru(String namaObatBaru) {
        this.namaObatBaru = namaObatBaru;
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

    public BigInteger getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(BigInteger qtyApprove) {
        this.qtyApprove = qtyApprove;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

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

    public String getFlagDiterima() {
        return flagDiterima;
    }

    public void setFlagDiterima(String flagDiterima) {
        this.flagDiterima = flagDiterima;
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
}