package com.neurix.simrs.mobileapi.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author gondok
 * Tuesday, 03/03/20 8:14
 */
public class HeaderDetailCheckupMobile {
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
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    private String statusPeriksaName;
    private String idRuangan;
    private String namaRuangan;
    private String noRuangan;

    private String idDokter;
    private String idPasien;
    private String namaPasien;
    private String namaPelayanan;
    private String alamat;
    private String desaId;
    private String desa;
    private String kecamatan;
    private String kota;
    private String provinsi;

    private String jenisKelamin;
    private String tempatLahir;
    private String tglLahir;

    private String stDateFrom;
    private String stDateTo;

    private String tempatTglLahir;
    private Boolean rawatInap = false;

    private String nik;
    private String idJenisPeriksaPasien;
    private String jenisPeriksaPasien;

    private String urlKtp;
    private Boolean enabledPoli;

    private String tinggi;
    private String berat;

    private String status;

    private String klaimBpjsFlag;

    private String noSep;

    private boolean cekApprove;

    private String caraPasienPulang;
    private String pendamping;
    private String tempatTujuan;

    private BigDecimal tarifBpjs;
    private BigDecimal tarifTindakan;
    private String typeTransaction;
    private String jenisPembayaran;
    private String noUangMuka;
    private BigInteger jumlahUangMuka;
    private BigInteger jumlahUangMukaDibayar;
    private String noJurnal;

    private String notLike;
    private String invoice;

    private String noCheckupOnline;
    private String metodePembayaran;
    private String tipePelayanan;

    private String urlTtd;
    private String flagCall;

    private String idRiwayatTindakan;
    private String namaTindakan;
    private String tglTindakan;
    private String keterangan;
    private String videoRm;
    private String keteranganKeluar;

    private String idTindakan;
    private String idDetailTindakan;
    private String namaDetailLab;
    private String satuan;
    private String acuan;
    private String kesimpulan;

    private String namaObat;
    private String qty;

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getIdDetailTindakan() {
        return idDetailTindakan;
    }

    public void setIdDetailTindakan(String idDetailTindakan) {
        this.idDetailTindakan = idDetailTindakan;
    }

    public String getNamaDetailLab() {
        return namaDetailLab;
    }

    public void setNamaDetailLab(String namaDetailLab) {
        this.namaDetailLab = namaDetailLab;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getAcuan() {
        return acuan;
    }

    public void setAcuan(String acuan) {
        this.acuan = acuan;
    }

    public String getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getKeteranganKeluar() {
        return keteranganKeluar;
    }

    public void setKeteranganKeluar(String keteranganKeluar) {
        this.keteranganKeluar = keteranganKeluar;
    }

    public String getIdRiwayatTindakan() {
        return idRiwayatTindakan;
    }

    public void setIdRiwayatTindakan(String idRiwayatTindakan) {
        this.idRiwayatTindakan = idRiwayatTindakan;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
    }

    public String getTglTindakan() {
        return tglTindakan;
    }

    public void setTglTindakan(String tglTindakan) {
        this.tglTindakan = tglTindakan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getVideoRm() {
        return videoRm;
    }

    public void setVideoRm(String videoRm) {
        this.videoRm = videoRm;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUrlTtd() {
        return urlTtd;
    }

    public void setUrlTtd(String urlTtd) {
        this.urlTtd = urlTtd;
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


    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getStatusPeriksaName() {
        return statusPeriksaName;
    }

    public void setStatusPeriksaName(String statusPeriksaName) {
        this.statusPeriksaName = statusPeriksaName;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public String getNoRuangan() {
        return noRuangan;
    }

    public void setNoRuangan(String noRuangan) {
        this.noRuangan = noRuangan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getStDateFrom() {
        return stDateFrom;
    }

    public void setStDateFrom(String stDateFrom) {
        this.stDateFrom = stDateFrom;
    }

    public String getStDateTo() {
        return stDateTo;
    }

    public void setStDateTo(String stDateTo) {
        this.stDateTo = stDateTo;
    }

    public String getTempatTglLahir() {
        return tempatTglLahir;
    }

    public void setTempatTglLahir(String tempatTglLahir) {
        this.tempatTglLahir = tempatTglLahir;
    }

    public Boolean getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(Boolean rawatInap) {
        this.rawatInap = rawatInap;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getJenisPeriksaPasien() {
        return jenisPeriksaPasien;
    }

    public void setJenisPeriksaPasien(String jenisPeriksaPasien) {
        this.jenisPeriksaPasien = jenisPeriksaPasien;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
    }

    public Boolean getEnabledPoli() {
        return enabledPoli;
    }

    public void setEnabledPoli(Boolean enabledPoli) {
        this.enabledPoli = enabledPoli;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public boolean isCekApprove() {
        return cekApprove;
    }

    public void setCekApprove(boolean cekApprove) {
        this.cekApprove = cekApprove;
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

    public BigDecimal getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(BigDecimal tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
    }

    public BigDecimal getTarifTindakan() {
        return tarifTindakan;
    }

    public void setTarifTindakan(BigDecimal tarifTindakan) {
        this.tarifTindakan = tarifTindakan;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getNoUangMuka() {
        return noUangMuka;
    }

    public void setNoUangMuka(String noUangMuka) {
        this.noUangMuka = noUangMuka;
    }

    public BigInteger getJumlahUangMuka() {
        return jumlahUangMuka;
    }

    public void setJumlahUangMuka(BigInteger jumlahUangMuka) {
        this.jumlahUangMuka = jumlahUangMuka;
    }

    public BigInteger getJumlahUangMukaDibayar() {
        return jumlahUangMukaDibayar;
    }

    public void setJumlahUangMukaDibayar(BigInteger jumlahUangMukaDibayar) {
        this.jumlahUangMukaDibayar = jumlahUangMukaDibayar;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getNotLike() {
        return notLike;
    }

    public void setNotLike(String notLike) {
        this.notLike = notLike;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }
}
