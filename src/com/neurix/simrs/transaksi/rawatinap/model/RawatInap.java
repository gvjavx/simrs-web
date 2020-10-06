package com.neurix.simrs.transaksi.rawatinap.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class RawatInap {
    private String idRawatInap;
    private String idDetailCheckup;
    private String noCheckup;
    private String idRuangan;
    private String namaRangan;
    private String noRuangan;
    private String keterangan;
    private BigInteger tarif;
    private Timestamp tglMasuk;
    private Timestamp tglKeluar;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    private String idPasien;
    private String namaPasien;
    private String idPelayanan;
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

    private String tempatTglLahir;
    private String idJenisPeriksa;
    private String jenisPeriksaPasien;
    private String nik;

    private String statusPeriksa;
    private String statusPeriksaName;

    private String stTglFrom;
    private String stTglTo;

    private String idKelas;
    private String idRuang;

    private String keteranganSelesai;
    private String kelasRuanganName;

    private String urlKtp;
    private String noSep;

    private String branchId;

    private boolean cekApprove;
    private String klaimBpjsFlag;

    private String idOrderGizi;
    private String approveFlag;
    private String diterimaFlag;

    private String statusBayar;

    private String metodePembayaran;

    private String notLike;

    private String noRujukan;
    private String tglRujukan;
    private String suratRujukan;
    private String namaAsuransi;

    private BigDecimal coverBiaya;
    private String idAsuransi;
    private String idPaket;
    private String noKartuAsuransi;

    private String isLaka;

    private String anamnese;
    private String alergi;
    private String penunjangMedis;
    private String keluhanUtama;
    private String suhu;
    private String tensi;
    private String nadi;
    private String pernafasan;
    private String alamatLengkap;
    private String umur;
    private String namaDiagnosa;
    private String berat;
    private String tinggi;

    private String tindakLanjut;
    private String catatan;
    private String flagTppri;

    private String isStay;
    private String isOrderLab;
    private String rsRujukan;

    private String kategoriRuangan;
    private String idKelasRuangan;

    private String idRuangLama;
    private String status;
    private BigInteger lamakamar;
    private String isKasir;
    private String isBayar;
    private String namaTempatTidur;
    private String idTempatTidur;

    public String getIdTempatTidur() {
        return idTempatTidur;
    }

    public void setIdTempatTidur(String idTempatTidur) {
        this.idTempatTidur = idTempatTidur;
    }

    public String getNamaTempatTidur() {
        return namaTempatTidur;
    }

    public void setNamaTempatTidur(String namaTempatTidur) {
        this.namaTempatTidur = namaTempatTidur;
    }

    public String getIsBayar() {
        return isBayar;
    }

    public void setIsBayar(String isBayar) {
        this.isBayar = isBayar;
    }

    public String getIsKasir() {
        return isKasir;
    }

    public void setIsKasir(String isKasir) {
        this.isKasir = isKasir;
    }

    public BigInteger getLamakamar() {
        return lamakamar;
    }

    public void setLamakamar(BigInteger lamakamar) {
        this.lamakamar = lamakamar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdRuangLama() {
        return idRuangLama;
    }

    public void setIdRuangLama(String idRuangLama) {
        this.idRuangLama = idRuangLama;
    }

    public String getIdKelasRuangan() {
        return idKelasRuangan;
    }

    public void setIdKelasRuangan(String idKelasRuangan) {
        this.idKelasRuangan = idKelasRuangan;
    }

    public String getKategoriRuangan() {
        return kategoriRuangan;
    }

    public void setKategoriRuangan(String kategoriRuangan) {
        this.kategoriRuangan = kategoriRuangan;
    }

    public String getRsRujukan() {
        return rsRujukan;
    }

    public void setRsRujukan(String rsRujukan) {
        this.rsRujukan = rsRujukan;
    }

    public String getIsOrderLab() {
        return isOrderLab;
    }

    public void setIsOrderLab(String isOrderLab) {
        this.isOrderLab = isOrderLab;
    }

    public String getIsStay() {
        return isStay;
    }

    public void setIsStay(String isStay) {
        this.isStay = isStay;
    }

    public String getFlagTppri() {
        return flagTppri;
    }

    public void setFlagTppri(String flagTppri) {
        this.flagTppri = flagTppri;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTindakLanjut() {
        return tindakLanjut;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getNamaDiagnosa() {
        return namaDiagnosa;
    }

    public void setNamaDiagnosa(String namaDiagnosa) {
        this.namaDiagnosa = namaDiagnosa;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getAlergi() {
        return alergi;
    }

    public void setAlergi(String alergi) {
        this.alergi = alergi;
    }

    public String getPenunjangMedis() {
        return penunjangMedis;
    }

    public void setPenunjangMedis(String penunjangMedis) {
        this.penunjangMedis = penunjangMedis;
    }

    public String getKeluhanUtama() {
        return keluhanUtama;
    }

    public void setKeluhanUtama(String keluhanUtama) {
        this.keluhanUtama = keluhanUtama;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getTensi() {
        return tensi;
    }

    public void setTensi(String tensi) {
        this.tensi = tensi;
    }

    public String getNadi() {
        return nadi;
    }

    public void setNadi(String nadi) {
        this.nadi = nadi;
    }

    public String getPernafasan() {
        return pernafasan;
    }

    public void setPernafasan(String pernafasan) {
        this.pernafasan = pernafasan;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
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

    public String getNoKartuAsuransi() {
        return noKartuAsuransi;
    }

    public void setNoKartuAsuransi(String noKartuAsuransi) {
        this.noKartuAsuransi = noKartuAsuransi;
    }

    public String getIsLaka() {
        return isLaka;
    }

    public void setIsLaka(String isLaka) {
        this.isLaka = isLaka;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getTglRujukan() {
        return tglRujukan;
    }

    public void setTglRujukan(String tglRujukan) {
        this.tglRujukan = tglRujukan;
    }

    public String getSuratRujukan() {
        return suratRujukan;
    }

    public void setSuratRujukan(String suratRujukan) {
        this.suratRujukan = suratRujukan;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public String getNotLike() {
        return notLike;
    }

    public void setNotLike(String notLike) {
        this.notLike = notLike;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public String getDiterimaFlag() {
        return diterimaFlag;
    }

    public void setDiterimaFlag(String diterimaFlag) {
        this.diterimaFlag = diterimaFlag;
    }

    public String getIdOrderGizi() {
        return idOrderGizi;
    }

    public void setIdOrderGizi(String idOrderGizi) {
        this.idOrderGizi = idOrderGizi;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getKlaimBpjsFlag() {
        return klaimBpjsFlag;
    }

    public void setKlaimBpjsFlag(String klaimBpjsFlag) {
        this.klaimBpjsFlag = klaimBpjsFlag;
    }

    public boolean isCekApprove() {
        return cekApprove;
    }

    public void setCekApprove(boolean cekApprove) {
        this.cekApprove = cekApprove;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
    }

    public String getKelasRuanganName() {
        return kelasRuanganName;
    }

    public void setKelasRuanganName(String kelasRuanganName) {
        this.kelasRuanganName = kelasRuanganName;
    }

    public String getKeteranganSelesai() {
        return keteranganSelesai;
    }

    public void setKeteranganSelesai(String keteranganSelesai) {
        this.keteranganSelesai = keteranganSelesai;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getIdRuang() {
        return idRuang;
    }

    public void setIdRuang(String idRuang) {
        this.idRuang = idRuang;
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

    public String getStatusPeriksa() {
        return statusPeriksa;
    }

    public void setStatusPeriksa(String idStatusPeriksa) {
        this.statusPeriksa = idStatusPeriksa;
    }

    public String getStatusPeriksaName() {
        return statusPeriksaName;
    }

    public void setStatusPeriksaName(String statusPeriksaName) {
        this.statusPeriksaName = statusPeriksaName;
    }

    public String getIdJenisPeriksa() {
        return idJenisPeriksa;
    }

    public void setIdJenisPeriksa(String idJenisPeriksa) {
        this.idJenisPeriksa = idJenisPeriksa;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getJenisPeriksaPasien() {
        return jenisPeriksaPasien;
    }

    public void setJenisPeriksaPasien(String jenisPeriksaPasien) {
        this.jenisPeriksaPasien = jenisPeriksaPasien;
    }

    public String getTempatTglLahir() {
        return tempatTglLahir;
    }

    public void setTempatTglLahir(String tempatTglLahir) {
        this.tempatTglLahir = tempatTglLahir;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
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

    public String getIdRawatInap() {
        return idRawatInap;
    }

    public void setIdRawatInap(String idRawatInap) {
        this.idRawatInap = idRawatInap;
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

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRangan() {
        return namaRangan;
    }

    public void setNamaRangan(String namaRangan) {
        this.namaRangan = namaRangan;
    }

    public String getNoRuangan() {
        return noRuangan;
    }

    public void setNoRuangan(String noRuangan) {
        this.noRuangan = noRuangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigInteger getTarif() {
        return tarif;
    }

    public void setTarif(BigInteger tarif) {
        this.tarif = tarif;
    }

    public Timestamp getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(Timestamp tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public Timestamp getTglKeluar() {
        return tglKeluar;
    }

    public void setTglKeluar(Timestamp tglKeluar) {
        this.tglKeluar = tglKeluar;
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
        return "ItSimrsRawatInapEntity{" +
                "idRawatInap='" + idRawatInap + '\'' +
                ", idDetailCheckup='" + idDetailCheckup + '\'' +
                ", noCheckup='" + noCheckup + '\'' +
                ", idRuangan='" + idRuangan + '\'' +
                ", namaRangan='" + namaRangan + '\'' +
                ", noRuangan='" + noRuangan + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", tarif=" + tarif +
                ", tglMasuk=" + tglMasuk +
                ", tglKeluar=" + tglKeluar +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                '}';
    }
}
