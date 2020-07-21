package com.neurix.simrs.mobileapi.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author gondok
 * Wednesday, 10/06/20 11:26
 */
public class TelemedicineMobile {
    private String noAntrian;
    private String id;
    private String idPasien;
    private String idDokter;
    private String idPelayanan;
    private String flagResep;
    private String status;
    private String biayaKonsultasi;
    private String flagBayarKonsultasi;
    private String flagBayarResep;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String namaPasien;
    private String namaDokter;
    private String namaPelayanan;
    private String ketFlagResep;
    private String ketStatus;
    private String asuransi;
    private String noKartu;
    private String idJenisPeriksaPasien;
    private String idAsuransi;
    private String kodeBank;
    private String keluhan;
    private String branchId;

    private String idDetailCheckup;
    private String noCheckup;

    private String message;
    private String jumlahAntrian;

    private String lat;
    private String lon;
    private String alamat;
    private String jenisPengambilan;
    private String noTelp;

    private String flagEresep;
    private String urlResep;

    private String idPembayaran;
    private String nominal;
    private String jenisPembayaran;
    private String noRujukan;
    private String jenisRujukan;
    private String keterangan;
    private String approvedFlag;

    private String idStrukAsuransi;
    private String jenisStruk;
    private String urlFotoStruk;
    private String approveFlagStruk;
    private String jumlahCover;
    private String coverDitanggung;

    private String dibayarPasien;

    public String getDibayarPasien() {
        return dibayarPasien;
    }

    public void setDibayarPasien(String dibayarPasien) {
        this.dibayarPasien = dibayarPasien;
    }

    public String getIdStrukAsuransi() {
        return idStrukAsuransi;
    }

    public void setIdStrukAsuransi(String idStrukAsuransi) {
        this.idStrukAsuransi = idStrukAsuransi;
    }

    public String getJenisStruk() {
        return jenisStruk;
    }

    public void setJenisStruk(String jenisStruk) {
        this.jenisStruk = jenisStruk;
    }

    public String getUrlFotoStruk() {
        return urlFotoStruk;
    }

    public void setUrlFotoStruk(String urlFotoStruk) {
        this.urlFotoStruk = urlFotoStruk;
    }

    public String getApproveFlagStruk() {
        return approveFlagStruk;
    }

    public void setApproveFlagStruk(String approveFlagStruk) {
        this.approveFlagStruk = approveFlagStruk;
    }

    public String getJumlahCover() {
        return jumlahCover;
    }

    public void setJumlahCover(String jumlahCover) {
        this.jumlahCover = jumlahCover;
    }

    public String getCoverDitanggung() {
        return coverDitanggung;
    }

    public void setCoverDitanggung(String coverDitanggung) {
        this.coverDitanggung = coverDitanggung;
    }

    public String getApprovedFlag() {
        return approvedFlag;
    }

    public void setApprovedFlag(String approvedFlag) {
        this.approvedFlag = approvedFlag;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getJenisRujukan() {
        return jenisRujukan;
    }

    public void setJenisRujukan(String jenisRujukan) {
        this.jenisRujukan = jenisRujukan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getUrlResep() {
        return urlResep;
    }

    public void setUrlResep(String urlResep) {
        this.urlResep = urlResep;
    }

    public String getFlagEresep() {
        return flagEresep;
    }

    public void setFlagEresep(String flagEresep) {
        this.flagEresep = flagEresep;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisPengambilan() {
        return jenisPengambilan;
    }

    public void setJenisPengambilan(String jenisPengambilan) {
        this.jenisPengambilan = jenisPengambilan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getJumlahAntrian() {
        return jumlahAntrian;
    }

    public void setJumlahAntrian(String jumlahAntrian) {
        this.jumlahAntrian = jumlahAntrian;
    }

    public String getBiayaKonsultasi() {
        return biayaKonsultasi;
    }

    public void setBiayaKonsultasi(String biayaKonsultasi) {
        this.biayaKonsultasi = biayaKonsultasi;
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

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getKetFlagResep() {
        return ketFlagResep;
    }

    public void setKetFlagResep(String ketFlagResep) {
        this.ketFlagResep = ketFlagResep;
    }

    public String getKetStatus() {
        return ketStatus;
    }

    public void setKetStatus(String ketStatus) {
        this.ketStatus = ketStatus;
    }

    public String getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(String asuransi) {
        this.asuransi = asuransi;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlagResep() {
        return flagResep;
    }

    public void setFlagResep(String flagResep) {
        this.flagResep = flagResep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlagBayarKonsultasi() {
        return flagBayarKonsultasi;
    }

    public void setFlagBayarKonsultasi(String flagBayarKonsultasi) {
        this.flagBayarKonsultasi = flagBayarKonsultasi;
    }

    public String getFlagBayarResep() {
        return flagBayarResep;
    }

    public void setFlagBayarResep(String flagBayarResep) {
        this.flagBayarResep = flagBayarResep;
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

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }
}
