package com.neurix.simrs.transaksi.checkup.model;

import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.periksalab.model.UploadHasilPemeriksaan;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HeaderCheckup {
    private String noCheckup;
    private String idPasien;
    private String nama;
    private String jenisKelamin;
    private String noKtp;
    private String tempatLahir;
    private Date tglLahir;
    private BigInteger desaId;
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
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
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
    private BigDecimal tarifBpjs;
    private List<Tindakan> tindakanList = new ArrayList<>();
    private List<DokterTeam> dokterTeamList = new ArrayList<>();
    private List<Asesmen> asesmenList = new ArrayList<>();

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
    private BigInteger uangMuka;
    private String noNota;

    private String invoice;

    private String statusBayar;

    private String statusBpjs;
    private String namaProvider;
    private String kelasRawat;

    private String kodeCbg;
    private String ketRacik;
    private String idPermintaanResep;
    private String totalBiaya;

    private String isKronis;

    private String idPaket;
    private String idAsuransi;
    private BigDecimal coverBiaya;
    private String noKartuAsuransi;
    private String namaAsuransi;

    private Date tglCheckup;
    private String keterangan;
    private String isLaka;
    private String flagCall;

    private Timestamp tglKeluar;
    private String tglTindakan;
    private String idTindakan;
    private String namaTindakan;
    private String idRiwayatTindakan;

    private String acuan;
    private String kesimpulan;
    private String satuan;
    private String qty;
    private String namaObat;
    private String namaDetailLab;
    private String idDetailTindakan;
    private String idAntrianOnline;
    private String idTransaksiOnline;
    private String tipePelayanan;

    private String idLab;
    private String isOrderLab;
    private String lastIdDetailCheckup;
    private String isStay;

    private String penunjangMedis;
    private String keluhanUtama;
    private String suhu;
    private String tensi;
    private String nadi;
    private String pernafasan;
    private String kategoriPelayanan;

    private String autoanamnesis;
    private String heteroanamnesis;
    private String catatan;
    private String anamnese;
    private String flagCloseTransaksi;
    private String flagCover;
    private String urlLab;
    private String ketAcuanP;
    private String ketAcuanL;
    private Boolean rawatInap;

    private String jmlRJ;
    private String jmlRI;
    private String jmlIGD;
    private String jmlTelemedic;

    private String imgKtp;
    private String jamAwal;
    private String jamAkhir;
    private String tglDaftar;
    private String tglCheckupOnline;
    private String stTglAntrian;

    private String pendidikan;
    private String statusPerkawinan;
    private String kunjunganPoli;

    private String kodePoliVclaim;
    private String branchName;
    private String total;
    private Date tanggal;
    private String all;
    private String tahun;

    private String isEksekutif;
    private String isVaksin;

    private String tarif;
    private String diskon;
    private String jumlah;

    private String namaPaket;
    private String formatTglMasuk;
    private String namaPemeriksaan;
    private String isPeriksaLuar;
    private String idHeaderPemeriksaan;
    private List<UploadHasilPemeriksaan> uploadHasil = new ArrayList<>();
    private List<String> listPemeriksaan = new ArrayList<>();
    private String umur;
    private String namaDokter;
    private String idUangMuka;
    private String idKelasRuangan;

    public String getIdKelasRuangan() {
        return idKelasRuangan;
    }

    public void setIdKelasRuangan(String idKelasRuangan) {
        this.idKelasRuangan = idKelasRuangan;
    }

    public String getIdUangMuka() {
        return idUangMuka;
    }

    public void setIdUangMuka(String idUangMuka) {
        this.idUangMuka = idUangMuka;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public List<String> getListPemeriksaan() {
        return listPemeriksaan;
    }

    public void setListPemeriksaan(List<String> listPemeriksaan) {
        this.listPemeriksaan = listPemeriksaan;
    }

    public List<UploadHasilPemeriksaan> getUploadHasil() {
        return uploadHasil;
    }

    public void setUploadHasil(List<UploadHasilPemeriksaan> uploadHasil) {
        this.uploadHasil = uploadHasil;
    }

    public String getIsPeriksaLuar() {
        return isPeriksaLuar;
    }

    public void setIsPeriksaLuar(String isPeriksaLuar) {
        this.isPeriksaLuar = isPeriksaLuar;
    }

    public String getIdHeaderPemeriksaan() {
        return idHeaderPemeriksaan;
    }

    public void setIdHeaderPemeriksaan(String idHeaderPemeriksaan) {
        this.idHeaderPemeriksaan = idHeaderPemeriksaan;
    }

    public String getNamaPemeriksaan() {
        return namaPemeriksaan;
    }

    public void setNamaPemeriksaan(String namaPemeriksaan) {
        this.namaPemeriksaan = namaPemeriksaan;
    }

    public String getFormatTglMasuk() {
        return formatTglMasuk;
    }

    public void setFormatTglMasuk(String formatTglMasuk) {
        this.formatTglMasuk = formatTglMasuk;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getIsEksekutif() {
        return isEksekutif;
    }

    public void setIsEksekutif(String isEksekutif) {
        this.isEksekutif = isEksekutif;
    }

    public String getIsVaksin() {
        return isVaksin;
    }

    public void setIsVaksin(String isVaksin) {
        this.isVaksin = isVaksin;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
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

    public String getKodePoliVclaim() {
        return kodePoliVclaim;
    }

    public void setKodePoliVclaim(String kodePoliVclaim) {
        this.kodePoliVclaim = kodePoliVclaim;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getKunjunganPoli() {
        return kunjunganPoli;
    }

    public void setKunjunganPoli(String kunjunganPoli) {
        this.kunjunganPoli = kunjunganPoli;
    }

    public String getStTglAntrian() {
        return stTglAntrian;
    }

    public void setStTglAntrian(String stTglAntrian) {
        this.stTglAntrian = stTglAntrian;
    }

    public String getTglCheckupOnline() {
        return tglCheckupOnline;
    }

    public void setTglCheckupOnline(String tglCheckupOnline) {
        this.tglCheckupOnline = tglCheckupOnline;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    public String getTglDaftar() {
        return tglDaftar;
    }

    public void setTglDaftar(String tglDaftar) {
        this.tglDaftar = tglDaftar;
    }

    public String getImgKtp() {
        return imgKtp;
    }

    public void setImgKtp(String imgKtp) {
        this.imgKtp = imgKtp;
    }

    public String getJmlRJ() {
        return jmlRJ;
    }

    public void setJmlRJ(String jmlRJ) {
        this.jmlRJ = jmlRJ;
    }

    public String getJmlRI() {
        return jmlRI;
    }

    public void setJmlRI(String jmlRI) {
        this.jmlRI = jmlRI;
    }

    public String getJmlIGD() {
        return jmlIGD;
    }

    public void setJmlIGD(String jmlIGD) {
        this.jmlIGD = jmlIGD;
    }

    public String getJmlTelemedic() {
        return jmlTelemedic;
    }

    public void setJmlTelemedic(String jmlTelemedic) {
        this.jmlTelemedic = jmlTelemedic;
    }

    public Boolean getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(Boolean rawatInap) {
        this.rawatInap = rawatInap;
    }

    public List<DokterTeam> getDokterTeamList() {
        return dokterTeamList;
    }

    public void setDokterTeamList(List<DokterTeam> dokterTeamList) {
        this.dokterTeamList = dokterTeamList;
    }

    public String getKetAcuanP() {
        return ketAcuanP;
    }

    public void setKetAcuanP(String ketAcuanP) {
        this.ketAcuanP = ketAcuanP;
    }

    public String getKetAcuanL() {
        return ketAcuanL;
    }

    public void setKetAcuanL(String ketAcuanL) {
        this.ketAcuanL = ketAcuanL;
    }

    private String isRekananWithBpjs;

    public String getIsRekananWithBpjs() {
        return isRekananWithBpjs;
    }

    public void setIsRekananWithBpjs(String isRekananWithBpjs) {
        this.isRekananWithBpjs = isRekananWithBpjs;
    }

    public String getUrlLab() {
        return urlLab;
    }

    public void setUrlLab(String urlLab) {
        this.urlLab = urlLab;
    }

    public String getFlagCloseTransaksi() {
        return flagCloseTransaksi;
    }

    public void setFlagCloseTransaksi(String flagCloseTransaksi) {
        this.flagCloseTransaksi = flagCloseTransaksi;
    }

    public String getFlagCover() {
        return flagCover;
    }

    public void setFlagCover(String flagCover) {
        this.flagCover = flagCover;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getAutoanamnesis() {
        return autoanamnesis;
    }

    public void setAutoanamnesis(String autoanamnesis) {
        this.autoanamnesis = autoanamnesis;
    }

    public String getHeteroanamnesis() {
        return heteroanamnesis;
    }

    public void setHeteroanamnesis(String heteroanamnesis) {
        this.heteroanamnesis = heteroanamnesis;
    }

    public String getIsStay() {
        return isStay;
    }

    public void setIsStay(String isStay) {
        this.isStay = isStay;
    }

    public String getIsOrderLab() {
        return isOrderLab;
    }

    public void setIsOrderLab(String isOrderLab) {
        this.isOrderLab = isOrderLab;
    }

    public String getLastIdDetailCheckup() {
        return lastIdDetailCheckup;
    }

    public void setLastIdDetailCheckup(String lastIdDetailCheckup) {
        this.lastIdDetailCheckup = lastIdDetailCheckup;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }

    public String getIdTransaksiOnline() {
        return idTransaksiOnline;
    }

    public void setIdTransaksiOnline(String idTransaksiOnline) {
        this.idTransaksiOnline = idTransaksiOnline;
    }

    public String getIdAntrianOnline() {
        return idAntrianOnline;
    }

    public void setIdAntrianOnline(String idAntrianOnline) {
        this.idAntrianOnline = idAntrianOnline;
    }

    public String getKategoriPelayanan() {
        return kategoriPelayanan;
    }

    public void setKategoriPelayanan(String kategoriPelayanan) {
        this.kategoriPelayanan = kategoriPelayanan;
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

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
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

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public Timestamp getTglKeluar() {
        return tglKeluar;
    }

    public void setTglKeluar(Timestamp tglKeluar) {
        this.tglKeluar = tglKeluar;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    private String belumBayarUangMuka;
    private Integer noAntrian;

    public Integer getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(Integer noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getBelumBayarUangMuka() {
        return belumBayarUangMuka;
    }

    public void setBelumBayarUangMuka(String belumBayarUangMuka) {
        this.belumBayarUangMuka = belumBayarUangMuka;
    }

    public String getIsLaka() {
        return isLaka;
    }

    public void setIsLaka(String isLaka) {
        this.isLaka = isLaka;
    }

    public Date getTglCheckup() {
        return tglCheckup;
    }

    public void setTglCheckup(Date tglCheckup) {
        this.tglCheckup = tglCheckup;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public String getNoKartuAsuransi() {
        return noKartuAsuransi;
    }

    public void setNoKartuAsuransi(String noKartuAsuransi) {
        this.noKartuAsuransi = noKartuAsuransi;
    }

    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public BigDecimal getCoverBiaya() {
        return coverBiaya;
    }

    public void setCoverBiaya(BigDecimal coverBiaya) {
        this.coverBiaya = coverBiaya;
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

    public String getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(String totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    private String urlTtd;

    public String getUrlTtd() {
        return urlTtd;
    }

    public void setUrlTtd(String urlTtd) {
        this.urlTtd = urlTtd;
    }

    public List<Asesmen> getAsesmenList() {
        return asesmenList;
    }

    public void setAsesmenList(List<Asesmen> asesmenList) {
        this.asesmenList = asesmenList;
    }

    public String getKodeCbg() {
        return kodeCbg;
    }

    public void setKodeCbg(String kodeCbg) {
        this.kodeCbg = kodeCbg;
    }

    public String getKelasRawat() {
        return kelasRawat;
    }

    public void setKelasRawat(String kelasRawat) {
        this.kelasRawat = kelasRawat;
    }

    public String getNamaProvider() {
        return namaProvider;
    }

    public void setNamaProvider(String namaProvider) {
        this.namaProvider = namaProvider;
    }

    public String getStatusBpjs() {
        return statusBpjs;
    }

    public void setStatusBpjs(String statusBpjs) {
        this.statusBpjs = statusBpjs;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    private String idRawatInap;
    private String idRuangan;

    private String noCheckupOnline;

    private String metodePembayaran;
    private String stNoAntrian;

    public String getStNoAntrian() {
        return stNoAntrian;
    }

    public void setStNoAntrian(String stNoAntrian) {
        this.stNoAntrian = stNoAntrian;
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

    public String getIdRawatInap() {
        return idRawatInap;
    }

    public void setIdRawatInap(String idRawatInap) {
        this.idRawatInap = idRawatInap;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public BigInteger getUangMuka() {
        return uangMuka;
    }

    public void setUangMuka(BigInteger uangMuka) {
        this.uangMuka = uangMuka;
    }

    private String idPelayananBpjs;

    public String getIdPelayananBpjs() {
        return idPelayananBpjs;
    }

    public void setIdPelayananBpjs(String idPelayananBpjs) {
        this.idPelayananBpjs = idPelayananBpjs;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    private Timestamp tglAntian;

    public Timestamp getTglAntian() {
        return tglAntian;
    }

    public void setTglAntian(Timestamp tglAntian) {
        this.tglAntian = tglAntian;
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

    public String getAdmisi() {
        return admisi;
    }

    public void setAdmisi(String admisi) {
        this.admisi = admisi;
    }

    public String getAlergi() {
        return alergi;
    }

    public void setAlergi(String alergi) {
        this.alergi = alergi;
    }

    public String getGangguanLain() {
        return gangguanLain;
    }

    public void setGangguanLain(String gangguanLain) {
        this.gangguanLain = gangguanLain;
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

    public String getKetPerujuk() {
        return ketPerujuk;
    }

    public void setKetPerujuk(String ketPerujuk) {
        this.ketPerujuk = ketPerujuk;
    }

    private BigDecimal tarifTindakan;
    private String noRujukan;
    private String noPpkRujukan;
    private String tglRujukan;
    private String klaimBpjsFlag;

    private String videoRm;

    public String getVideoRm() {
        return videoRm;
    }

    public void setVideoRm(String videoRm) {
        this.videoRm = videoRm;
    }

    public String getKlaimBpjsFlag() {
        return klaimBpjsFlag;
    }

    public void setKlaimBpjsFlag(String klaimBpjsFlag) {
        this.klaimBpjsFlag = klaimBpjsFlag;
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

    public String getTglRujukan() {
        return tglRujukan;
    }

    public void setTglRujukan(String tglRujukan) {
        this.tglRujukan = tglRujukan;
    }

    public BigDecimal getTarifTindakan() {
        return tarifTindakan;
    }

    public void setTarifTindakan(BigDecimal tarifTindakan) {
        this.tarifTindakan = tarifTindakan;
    }

    public List<Tindakan> getTindakanList() {
        return tindakanList;
    }

    public void setTindakanList(List<Tindakan> tindakanList) {
        this.tindakanList = tindakanList;
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

    public BigDecimal getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(BigDecimal tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
    }

    public String getNoBpjs() {
        return noBpjs;
    }

    public void setNoBpjs(String noBpjs) {
        this.noBpjs = noBpjs;
    }

    public String getNamaDiagnosa() {
        return namaDiagnosa;
    }

    public void setNamaDiagnosa(String namaDiagnosa) {
        this.namaDiagnosa = namaDiagnosa;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
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

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
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

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
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

    public String getStTglLahir() {
        return stTglLahir;
    }

    public void setStTglLahir(String stTglLahir) {
        this.stTglLahir = stTglLahir;
    }

    public String getJenisKunjungan() {
        return jenisKunjungan;
    }

    public void setJenisKunjungan(String jenisKunjungan) {
        this.jenisKunjungan = jenisKunjungan;
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

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
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

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public BigInteger getDesaId() {
        return desaId;
    }

    public void setDesaId(BigInteger desaId) {
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

    public String getKetRacik() {
        return ketRacik;
    }

    public void setKetRacik(String ketRacik) {
        this.ketRacik = ketRacik;
    }

    public String getIdPermintaanResep() {
        return idPermintaanResep;
    }

    public void setIdPermintaanResep(String idPermintaanResep) {
        this.idPermintaanResep = idPermintaanResep;
    }
}
