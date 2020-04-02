package com.neurix.simrs.transaksi.checkupdetail.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class ItSimrsHeaderDetailCheckupEntity implements Serializable{
    private String idDetailCheckup;
    private String noCheckup;
    private String idPelayanan;
    private String statusPeriksa;
    private String statusBayar;
    private BigInteger totalBiaya;
    private String keteranganSelesai;
    private String jenisLab;
    private String branchId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private Timestamp tglAntrian;
    private Date tglCekup;
    private String keteranganCekupUlang;

    private String caraPasienPulang;
    private String pendamping;
    private String tempatTujuan;

    private String noSep;
    private BigDecimal tarifBpjs;
    private String klaimBpjsFlag;

    private String noCheckupOnline;

    private String metodePembayaran;
    private String invoice;
    private String noJurnal;

    private String kodeCbg;
    private String isKronis;

    private BigDecimal coverBiaya;
    private String idAsuransi;
    private String idPaket;
    private String noKartuAsuransi;

    private String idJenisPeriksaPasien;
    private String rujuk;
    private String urlDocRujuk;
    private String ketRujukan;
    private String kelasPasien;
    private String idPelayananBpjs;
    private String noRujukan;
    private String noPpkRujukan;
    private Date tglRujukan;

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getRujuk() {
        return rujuk;
    }

    public void setRujuk(String rujuk) {
        this.rujuk = rujuk;
    }

    public String getUrlDocRujuk() {
        return urlDocRujuk;
    }

    public void setUrlDocRujuk(String urlDocRujuk) {
        this.urlDocRujuk = urlDocRujuk;
    }

    public String getKetRujukan() {
        return ketRujukan;
    }

    public void setKetRujukan(String ketRujukan) {
        this.ketRujukan = ketRujukan;
    }

    public String getKelasPasien() {
        return kelasPasien;
    }

    public void setKelasPasien(String kelasPasien) {
        this.kelasPasien = kelasPasien;
    }

    public String getIdPelayananBpjs() {
        return idPelayananBpjs;
    }

    public void setIdPelayananBpjs(String idPelayananBpjs) {
        this.idPelayananBpjs = idPelayananBpjs;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNoPpkRujukan() {
        return noPpkRujukan;
    }

    public void setNoPpkRujukan(String noPpkRujukan) {
        this.noPpkRujukan = noPpkRujukan;
    }

    public Date getTglRujukan() {
        return tglRujukan;
    }

    public void setTglRujukan(Date tglRujukan) {
        this.tglRujukan = tglRujukan;
    }

    public String getNoKartuAsuransi() {
        return noKartuAsuransi;
    }

    public void setNoKartuAsuransi(String noKartuAsuransi) {
        this.noKartuAsuransi = noKartuAsuransi;
    }

    public BigDecimal getCoverBiaya() {
        return coverBiaya;
    }

    public void setCoverBiaya(BigDecimal coverBiaya) {
        this.coverBiaya = coverBiaya;
    }

    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getIsKronis() {
        return isKronis;
    }

    public void setIsKronis(String isKronis) {
        this.isKronis = isKronis;
    }

    private String urlTtd;

    public String getUrlTtd() {
        return urlTtd;
    }

    public void setUrlTtd(String urlTtd) {
        this.urlTtd = urlTtd;
    }

    public String getKodeCbg() {
        return kodeCbg;
    }

    public void setKodeCbg(String kodeCbg) {
        this.kodeCbg = kodeCbg;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
    }

    public String getKlaimBpjsFlag() {
        return klaimBpjsFlag;
    }

    public void setKlaimBpjsFlag(String klaimBpjsFlag) {
        this.klaimBpjsFlag = klaimBpjsFlag;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public BigDecimal getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(BigDecimal tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
    }

    public String getCaraPasienPulang() {
        return caraPasienPulang;
    }

    public void setCaraPasienPulang(String caraPasienPulang) {
        this.caraPasienPulang = caraPasienPulang;
    }

    public String getPendamping() {
        return pendamping;
    }

    public void setPendamping(String pendamping) {
        this.pendamping = pendamping;
    }

    public String getTempatTujuan() {
        return tempatTujuan;
    }

    public void setTempatTujuan(String tempatTujuan) {
        this.tempatTujuan = tempatTujuan;
    }

    public Date getTglCekup() {
        return tglCekup;
    }

    public void setTglCekup(Date tglCekup) {
        this.tglCekup = tglCekup;
    }

    public String getKeteranganCekupUlang() {
        return keteranganCekupUlang;
    }

    public void setKeteranganCekupUlang(String keteranganCekupUlang) {
        this.keteranganCekupUlang = keteranganCekupUlang;
    }

    public Timestamp getTglAntrian() {
        return tglAntrian;
    }

    public void setTglAntrian(Timestamp tglAntrian) {
        this.tglAntrian = tglAntrian;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getStatusPeriksa() {
        return statusPeriksa;
    }

    public void setStatusPeriksa(String statusPeriksa) {
        this.statusPeriksa = statusPeriksa;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public BigInteger getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(BigInteger totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public String getKeteranganSelesai() {
        return keteranganSelesai;
    }

    public void setKeteranganSelesai(String keteranganSelesai) {
        this.keteranganSelesai = keteranganSelesai;
    }

    public String getJenisLab() {
        return jenisLab;
    }

    public void setJenisLab(String jenisLab) {
        this.jenisLab = jenisLab;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    @Override
    public String toString() {
        return "ItSimrsHeaderDetailCheckupEntity{" +
                "idDetailCheckup='" + idDetailCheckup + '\'' +
                ", noCheckup='" + noCheckup + '\'' +
                ", idPelayanan='" + idPelayanan + '\'' +
                ", statusPeriksa='" + statusPeriksa + '\'' +
                ", statusBayar='" + statusBayar + '\'' +
                ", totalBiaya=" + totalBiaya +
                ", keteranganSelesai='" + keteranganSelesai + '\'' +
                ", jenisLab='" + jenisLab + '\'' +
                ", branchId='" + branchId + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", tglAntrian=" + tglAntrian +
                '}';
    }
}
