package com.neurix.simrs.mobileapi.model;

import com.neurix.simrs.master.tindakan.model.Tindakan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 26/02/20 14:29
 */
public class HeaderCheckupMobile implements Serializable {
    private String noAntrian;
    private String noCheckup;
    private String idPasien;
    private String nama;
    private String jenisKelamin;
    private String noKtp;
    private String tempatLahir;
    private String tglLahir;
    private String desaId;
    private String jalan;
    private String suku;
    private String agama;
    private String profesi;
    private String noTelp;
    private String idJenisPeriksaPasien;
    private String keteranganKeluar;
    private String urlKtp;
    private String branchId;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;
    private String jenisKunjungan;

    private String idPelayanan;
    private String statusPeriksa;
    private String statusPeriksaName;
    private String namaPelayanan;
    private String namaRuangan;
    private String noRuangan;

    private String stTglLahir;
    private String stTglFrom;
    private String getStTglTo;

    private String namaDesa;
    private String namaKecamatan;
    private String namaKota;
    private String namaProvinsi;
    private String kecamatanId;
    private String kotaId;
    private String provinsiId;

    private String idDokter;
    private String idDetailCheckup;
    private String idTeamDokter;

    private String namaPenanggung;
    private String hubunganKeluarga;
    private String rujuk;
    private String ketPerujuk;

    private String urlDocRujuk;
    private String tinggi;
    private String berat;

    private String diagnosa;
    private String namaDiagnosa;
    private String noBpjs;

    private String noSep;
    private String jenisTransaksi;
    private String tarifBpjs;

    private String ketKeyakinan;
    private String bantuanBahasa;
    private String bahasa;
    private String alatBantu;
    private String gangguanLain;
    private String alergi;

    private String admisi;

    private String kelasPasien;
    private String noMr;

    private String isOnline;

    private String idRiwayatTindakan;
    private String namaTindakan;
    private String tglTindakan;
    private String keterangan;
    private String videoRm;

    private String branchName;
    private String total;

    private String all;
    private String tanggal;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getVideoRm() {
        return videoRm;
    }

    public void setVideoRm(String videoRm) {
        this.videoRm = videoRm;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public String getIdRiwayatTindakan() {
        return idRiwayatTindakan;
    }

    public void setIdRiwayatTindakan(String idRiwayatTindakan) {
        this.idRiwayatTindakan = idRiwayatTindakan;
    }

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
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

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getSuku() {
        return suku;
    }

    public void setSuku(String suku) {
        this.suku = suku;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getKeteranganKeluar() {
        return keteranganKeluar;
    }

    public void setKeteranganKeluar(String keteranganKeluar) {
        this.keteranganKeluar = keteranganKeluar;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
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

    public String getJenisKunjungan() {
        return jenisKunjungan;
    }

    public void setJenisKunjungan(String jenisKunjungan) {
        this.jenisKunjungan = jenisKunjungan;
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

    public String getStatusPeriksaName() {
        return statusPeriksaName;
    }

    public void setStatusPeriksaName(String statusPeriksaName) {
        this.statusPeriksaName = statusPeriksaName;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
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

    public String getStTglLahir() {
        return stTglLahir;
    }

    public void setStTglLahir(String stTglLahir) {
        this.stTglLahir = stTglLahir;
    }

    public String getStTglFrom() {
        return stTglFrom;
    }

    public void setStTglFrom(String stTglFrom) {
        this.stTglFrom = stTglFrom;
    }

    public String getGetStTglTo() {
        return getStTglTo;
    }

    public void setGetStTglTo(String getStTglTo) {
        this.getStTglTo = getStTglTo;
    }

    public String getNamaDesa() {
        return namaDesa;
    }

    public void setNamaDesa(String namaDesa) {
        this.namaDesa = namaDesa;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public void setNamaProvinsi(String namaProvinsi) {
        this.namaProvinsi = namaProvinsi;
    }

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdTeamDokter() {
        return idTeamDokter;
    }

    public void setIdTeamDokter(String idTeamDokter) {
        this.idTeamDokter = idTeamDokter;
    }

    public String getNamaPenanggung() {
        return namaPenanggung;
    }

    public void setNamaPenanggung(String namaPenanggung) {
        this.namaPenanggung = namaPenanggung;
    }

    public String getHubunganKeluarga() {
        return hubunganKeluarga;
    }

    public void setHubunganKeluarga(String hubunganKeluarga) {
        this.hubunganKeluarga = hubunganKeluarga;
    }

    public String getRujuk() {
        return rujuk;
    }

    public void setRujuk(String rujuk) {
        this.rujuk = rujuk;
    }

    public String getKetPerujuk() {
        return ketPerujuk;
    }

    public void setKetPerujuk(String ketPerujuk) {
        this.ketPerujuk = ketPerujuk;
    }

    public String getUrlDocRujuk() {
        return urlDocRujuk;
    }

    public void setUrlDocRujuk(String urlDocRujuk) {
        this.urlDocRujuk = urlDocRujuk;
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

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getNamaDiagnosa() {
        return namaDiagnosa;
    }

    public void setNamaDiagnosa(String namaDiagnosa) {
        this.namaDiagnosa = namaDiagnosa;
    }

    public String getNoBpjs() {
        return noBpjs;
    }

    public void setNoBpjs(String noBpjs) {
        this.noBpjs = noBpjs;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public String getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(String tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
    }

    public String getKetKeyakinan() {
        return ketKeyakinan;
    }

    public void setKetKeyakinan(String ketKeyakinan) {
        this.ketKeyakinan = ketKeyakinan;
    }

    public String getBantuanBahasa() {
        return bantuanBahasa;
    }

    public void setBantuanBahasa(String bantuanBahasa) {
        this.bantuanBahasa = bantuanBahasa;
    }

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public String getAlatBantu() {
        return alatBantu;
    }

    public void setAlatBantu(String alatBantu) {
        this.alatBantu = alatBantu;
    }

    public String getGangguanLain() {
        return gangguanLain;
    }

    public void setGangguanLain(String gangguanLain) {
        this.gangguanLain = gangguanLain;
    }

    public String getAlergi() {
        return alergi;
    }

    public void setAlergi(String alergi) {
        this.alergi = alergi;
    }

    public String getAdmisi() {
        return admisi;
    }

    public void setAdmisi(String admisi) {
        this.admisi = admisi;
    }

    public String getKelasPasien() {
        return kelasPasien;
    }

    public void setKelasPasien(String kelasPasien) {
        this.kelasPasien = kelasPasien;
    }

    public String getNoMr() {
        return noMr;
    }

    public void setNoMr(String noMr) {
        this.noMr = noMr;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }
}
