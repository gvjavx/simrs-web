package com.neurix.simrs.transaksi.checkupdetail.model;

import com.neurix.common.model.BaseModel;
import com.neurix.simrs.master.rekammedis.model.RekamMedisPasien;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HeaderDetailCheckup extends BaseModel{
    private String idDetailCheckup;
    private String noCheckup;
    private String idPelayanan;
    private String statusPeriksa;
    private String statusBayar;
    private BigInteger totalBiaya;
    private BigInteger totalBiayaDariBpjs;
    private String keteranganSelesai;
    private String jenisLab;
    private String branchId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
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

    private String noFpk;
    private String idFpk;
    private String statusFPK;

    private String kodeCbg;

    private String isKronis;

    private String diagnosa;
    private String namaDiagnosa;

    private String idKelas;
    private String idRuang;

    private String idRawatInap;
    private String namaKelasRuangan;

    private String noBpjs;

    private BigDecimal coverBiaya;
    private String idAsuransi;
    private String idPaket;
    private String noKartuAsuransi;

    private String noRujukan;
    private String tglRujukan;
    private String suratRujukan;
    private String namaAsuransi;

    private String noPpk;
    private String perujuk;
    private String namaPerujuk;

    private String flagRefund;
    private String idPelayananBpjs;
    private String kodering;

    private String noJurnalTrans;
    private Timestamp transDate;
    private String transPeriode;
    private String invoiceTrans;

    private String stTanggalKeluar;
    private String stTanggalMasuk;

    private String isLaka;

    private String videoRm;
    private String flagCall;

    private String tglKeluar;

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

    private String asesmenLabel;
    private String kategoriPelayanan;
    private String pemeriksaanTerakhir;
    private String dibayarPasien;

    public String getDibayarPasien() {
        return dibayarPasien;
    }

    public void setDibayarPasien(String dibayarPasien) {
        this.dibayarPasien = dibayarPasien;
    }

    private String idPelayananDokter;
    private String noCheckupUlang;
    private String isOrderLab;

    private String flagCloseTraksaksi;
    private String tindakLanjut;
    private String catatan;
    private String rsRujukan;
    private String flagTppri;
    private String isStay;

    private String autoanamnesis;
    private String heteroanamnesis;
    private String flagCover;
    private String isCover;
    private BigDecimal pasienBayar;
    private String isBayar;
    private String justLab;
    private String flagSisa;

    private String namaRekanan;
    private String tipeLanjut;

    private String flagKunjungan;
    private String berkas;
    private String idx;
    private String url;

    private String flagSendKlaim;
    private String poliRujukanInternal;
    private String noRujukanInternal;

    private String noTelp;

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNoRujukanInternal() {
        return noRujukanInternal;
    }

    public void setNoRujukanInternal(String noRujukanInternal) {
        this.noRujukanInternal = noRujukanInternal;
    }

    public String getPoliRujukanInternal() {
        return poliRujukanInternal;
    }

    public void setPoliRujukanInternal(String poliRujukanInternal) {
        this.poliRujukanInternal = poliRujukanInternal;
    }

    public String getFlagSendKlaim() {
        return flagSendKlaim;
    }

    public void setFlagSendKlaim(String flagSendKlaim) {
        this.flagSendKlaim = flagSendKlaim;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getFlagKunjungan() {
        return flagKunjungan;
    }

    public void setFlagKunjungan(String flagKunjungan) {
        this.flagKunjungan = flagKunjungan;
    }

    public String getBerkas() {
        return berkas;
    }

    public void setBerkas(String berkas) {
        this.berkas = berkas;
    }

    public String getTipeLanjut() {
        return tipeLanjut;
    }

    public void setTipeLanjut(String tipeLanjut) {
        this.tipeLanjut = tipeLanjut;
    }

    public String getNamaRekanan() {
        return namaRekanan;
    }

    public void setNamaRekanan(String namaRekanan) {
        this.namaRekanan = namaRekanan;
    }

    public String getFlagSisa() {
        return flagSisa;
    }

    public void setFlagSisa(String flagSisa) {
        this.flagSisa = flagSisa;
    }

    public String getJustLab() {
        return justLab;
    }

    public void setJustLab(String justLab) {
        this.justLab = justLab;
    }

    public String getIsBayar() {
        return isBayar;
    }

    public void setIsBayar(String isBayar) {
        this.isBayar = isBayar;
    }

    public BigDecimal getPasienBayar() {
        return pasienBayar;
    }

    public void setPasienBayar(BigDecimal pasienBayar) {
        this.pasienBayar = pasienBayar;
    }

    public void setFlagCover(String flagCover) {
        this.flagCover = flagCover;
    }

    public String getIsCover() {
        return isCover;
    }

    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }

    public String getFlagCover() {
        return flagCover;
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

    private List<DokterTeam> dokterTeamList = new ArrayList<>();

    public List<DokterTeam> getDokterTeamList() {
        return dokterTeamList;
    }

    public void setDokterTeamList(List<DokterTeam> dokterTeamList) {
        this.dokterTeamList = dokterTeamList;
    }

    public String getFlagTppri() {
        return flagTppri;
    }

    public void setFlagTppri(String flagTppri) {
        this.flagTppri = flagTppri;
    }

    public String getRsRujukan() {
        return rsRujukan;
    }

    public void setRsRujukan(String rsRujukan) {
        this.rsRujukan = rsRujukan;
    }

    public String getFlagCloseTraksaksi() {
        return flagCloseTraksaksi;
    }

    public void setFlagCloseTraksaksi(String flagCloseTraksaksi) {
        this.flagCloseTraksaksi = flagCloseTraksaksi;
    }

    public String getTindakLanjut() {
        return tindakLanjut;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIsOrderLab() {
        return isOrderLab;
    }

    public void setIsOrderLab(String isOrderLab) {
        this.isOrderLab = isOrderLab;
    }

    public String getNoCheckupUlang() {
        return noCheckupUlang;
    }

    public void setNoCheckupUlang(String noCheckupUlang) {
        this.noCheckupUlang = noCheckupUlang;
    }

    public String getIdPelayananDokter() {
        return idPelayananDokter;
    }

    public void setIdPelayananDokter(String idPelayananDokter) {
        this.idPelayananDokter = idPelayananDokter;
    }

    private List<RekamMedisPasien> listRekamMedis;

    public List<RekamMedisPasien> getListRekamMedis() {
        return listRekamMedis;
    }

    public void setListRekamMedis(List<RekamMedisPasien> listRekamMedis) {
        this.listRekamMedis = listRekamMedis;
    }

    public String getPemeriksaanTerakhir() {
        return pemeriksaanTerakhir;
    }

    public void setPemeriksaanTerakhir(String pemeriksaanTerakhir) {
        this.pemeriksaanTerakhir = pemeriksaanTerakhir;
    }

    public String getKategoriPelayanan() {
        return kategoriPelayanan;
    }

    public void setKategoriPelayanan(String kategoriPelayanan) {
        this.kategoriPelayanan = kategoriPelayanan;
    }

    public String getAsesmenLabel() {
        return asesmenLabel;
    }

    public void setAsesmenLabel(String asesmenLabel) {
        this.asesmenLabel = asesmenLabel;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
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

    public String getAlergi() {
        return alergi;
    }

    public void setAlergi(String alergi) {
        this.alergi = alergi;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getTglKeluar() {
        return tglKeluar;
    }

    public void setTglKeluar(String tglKeluar) {
        this.tglKeluar = tglKeluar;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    public String getVideoRm() {
        return videoRm;
    }

    public void setVideoRm(String videoRm) {
        this.videoRm = videoRm;
    }

    public String getIsLaka() {
        return isLaka;
    }

    public void setIsLaka(String isLaka) {
        this.isLaka = isLaka;
    }

    private String idJenisPeriksaSebelumnya;

    public String getIdJenisPeriksaSebelumnya() {
        return idJenisPeriksaSebelumnya;
    }

    public void setIdJenisPeriksaSebelumnya(String idJenisPeriksaSebelumnya) {
        this.idJenisPeriksaSebelumnya = idJenisPeriksaSebelumnya;
    }

    public String getStTanggalKeluar() {
        return stTanggalKeluar;
    }

    public void setStTanggalKeluar(String stTanggalKeluar) {
        this.stTanggalKeluar = stTanggalKeluar;
    }

    public String getStTanggalMasuk() {
        return stTanggalMasuk;
    }

    public void setStTanggalMasuk(String stTanggalMasuk) {
        this.stTanggalMasuk = stTanggalMasuk;
    }

    public String getInvoiceTrans() {
        return invoiceTrans;
    }

    public void setInvoiceTrans(String invoiceTrans) {
        this.invoiceTrans = invoiceTrans;
    }

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
    }

    public String getNoJurnalTrans() {
        return noJurnalTrans;
    }

    public void setNoJurnalTrans(String noJurnalTrans) {
        this.noJurnalTrans = noJurnalTrans;
    }

    public Timestamp getTransDate() {
        return transDate;
    }

    public void setTransDate(Timestamp transDate) {
        this.transDate = transDate;
    }

    public String getTransPeriode() {
        return transPeriode;
    }

    public void setTransPeriode(String transPeriode) {
        this.transPeriode = transPeriode;
    }

    private String stTotalBiayaBpjs;
    private String stTotalBiaya;

    public String getStTotalBiayaBpjs() {
        return stTotalBiayaBpjs;
    }

    public void setStTotalBiayaBpjs(String stTotalBiayaBpjs) {
        this.stTotalBiayaBpjs = stTotalBiayaBpjs;
    }

    public String getStTotalBiaya() {
        return stTotalBiaya;
    }

    public void setStTotalBiaya(String stTotalBiaya) {
        this.stTotalBiaya = stTotalBiaya;
    }

    public String getIdPelayananBpjs() {
        return idPelayananBpjs;
    }

    public void setIdPelayananBpjs(String idPelayananBpjs) {
        this.idPelayananBpjs = idPelayananBpjs;
    }

    public String getFlagRefund() {
        return flagRefund;
    }

    public void setFlagRefund(String flagRefund) {
        this.flagRefund = flagRefund;
    }

    public String getPerujuk() {
        return perujuk;
    }

    public void setPerujuk(String perujuk) {
        this.perujuk = perujuk;
    }

    public String getNamaPerujuk() {
        return namaPerujuk;
    }

    public void setNamaPerujuk(String namaPerujuk) {
        this.namaPerujuk = namaPerujuk;
    }

    public String getNoPpk() {
        return noPpk;
    }

    public void setNoPpk(String noPpk) {
        this.noPpk = noPpk;
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

    public String getNoBpjs() {
        return noBpjs;
    }

    public void setNoBpjs(String noBpjs) {
        this.noBpjs = noBpjs;
    }

    public String getIdRawatInap() {
        return idRawatInap;
    }

    public void setIdRawatInap(String idRawatInap) {
        this.idRawatInap = idRawatInap;
    }

    public String getNamaKelasRuangan() {
        return namaKelasRuangan;
    }

    public void setNamaKelasRuangan(String namaKelasRuangan) {
        this.namaKelasRuangan = namaKelasRuangan;
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

    //for import
    private String noSlipBank;
    private String bank;
    private String stTanggalFpk;


    public String getNoSlipBank() {
        return noSlipBank;
    }

    public void setNoSlipBank(String noSlipBank) {
        this.noSlipBank = noSlipBank;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getStTanggalFpk() {
        return stTanggalFpk;
    }

    public void setStTanggalFpk(String stTanggalFpk) {
        this.stTanggalFpk = stTanggalFpk;
    }

    public BigInteger getTotalBiayaDariBpjs() {
        return totalBiayaDariBpjs;
    }

    public void setTotalBiayaDariBpjs(BigInteger totalBiayaDariBpjs) {
        this.totalBiayaDariBpjs = totalBiayaDariBpjs;
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

    public String getStatusFPK() {
        return statusFPK;
    }

    public void setStatusFPK(String statusFPK) {
        this.statusFPK = statusFPK;
    }

    public String getIdFpk() {
        return idFpk;
    }

    public void setIdFpk(String idFpk) {
        this.idFpk = idFpk;
    }

    public String getNoFpk() {
        return noFpk;
    }

    public void setNoFpk(String noFpk) {
        this.noFpk = noFpk;
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

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }

    public BigInteger getJumlahUangMukaDibayar() {
        return jumlahUangMukaDibayar;
    }

    public void setJumlahUangMukaDibayar(BigInteger jumlahUangMukaDibayar) {
        this.jumlahUangMukaDibayar = jumlahUangMukaDibayar;
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

    public BigInteger getJumlahUangMuka() {
        return jumlahUangMuka;
    }

    public void setJumlahUangMuka(BigInteger jumlahUangMuka) {
        this.jumlahUangMuka = jumlahUangMuka;
    }

    public String getNoUangMuka() {
        return noUangMuka;
    }

    public void setNoUangMuka(String noUangMuka) {
        this.noUangMuka = noUangMuka;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    private List<Tindakan> tindakanList = new ArrayList<>();

    public List<Tindakan> getTindakanList() {
        return tindakanList;
    }

    public void setTindakanList(List<Tindakan> tindakanList) {
        this.tindakanList = tindakanList;
    }

    public BigDecimal getTarifTindakan() {
        return tarifTindakan;
    }

    public void setTarifTindakan(BigDecimal tarifTindakan) {
        this.tarifTindakan = tarifTindakan;
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

    public boolean isCekApprove() {
        return cekApprove;
    }

    public void setCekApprove(boolean cekApprove) {
        this.cekApprove = cekApprove;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getKlaimBpjsFlag() {
        return klaimBpjsFlag;
    }

    public void setKlaimBpjsFlag(String klaimBpjsFlag) {
        this.klaimBpjsFlag = klaimBpjsFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getEnabledPoli() {
        return enabledPoli;
    }

    public void setEnabledPoli(Boolean enabledPoli) {
        this.enabledPoli = enabledPoli;
    }

    private Date tglCekup;
    private String keteranganCekupUlang;

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

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
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

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getTempatTglLahir() {
        return tempatTglLahir;
    }

    public void setTempatTglLahir(String tempatTglLahir) {
        this.tempatTglLahir = tempatTglLahir;
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

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
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

    public String getStatusPeriksaName() {
        return statusPeriksaName;
    }

    public void setStatusPeriksaName(String statusPeriksaName) {
        this.statusPeriksaName = statusPeriksaName;
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

}
