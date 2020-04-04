package com.neurix.hris.transaksi.payroll.model;


import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollEntity implements Serializable {

    // lembur
    private String absensiId ;
    private double jamLembur ;
    private BigDecimal biayaLemburLama;


    private String payrollId ;
    private String bulan ;
    private String tahun ;
    private String nip ;
    private String nama ;
    private String namaBagian ;
    private String kelompokId ;
    private String kelompokName ;
    private String positionId ;
    private String positionName ;
    private String golonganId ;
    private String golonganName ;
    private String departmentId ;
    private String departmentName ;
    private String branchId ;
    private String branchName ;
    private String golonganDapen ;
    private String golonganDapenNusindo ;
    private int point  ;
    private int pointLebih  ;
    private String statusKeluarga  ;
    private String statusPegawai  ;
    private int jumlahAnak ;
    private String faktorKeluargaId;
    private BigDecimal nilaiFaktorKeluarga;
    private String multifikator;
    private BigDecimal gajiPensiunBpjs;
    private BigDecimal gajiGolongan;
    private BigDecimal gajiPensiun;
    private BigDecimal tunjanganUmk;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganPendidikan;
    private BigDecimal tunjanganJabatanStruktural;
    private BigDecimal tunjanganStrategis;
    private BigDecimal kompensasi;
    private BigDecimal tunjanganTransport;
    private BigDecimal tunjanganAirListrik;
    private BigDecimal tunjanganPengobatan;
    private BigDecimal tunjanganPerumahan;
    private BigDecimal potPph;
    private BigDecimal potPphLain;
    private BigDecimal tunjanganBajuDinas;
    private BigDecimal tunjanganLembur;
    private BigDecimal tunjanganPeralihan;

    private BigDecimal pphGaji;
    private BigDecimal pphGajiRapel;
    private BigDecimal pphGajiRapelPribadi;
    private BigDecimal pphPengobatan;
    private BigDecimal jumlahPph;
    private BigDecimal iuranPensiun;
    private BigDecimal iuranBpjsTk;
    private BigDecimal iuranBpjsPensiun;
    private BigDecimal iuranPensiunPerusahaan;
    private BigDecimal iuranPensiunJumlah;
    private BigDecimal iuranBpjsKesehatan;
    private BigDecimal uangMukaLainnya;
    private BigDecimal kekuranganBpjsTk;
    private BigDecimal pengobatan;
    private BigDecimal koperasi;
    private BigDecimal dansos;
    private BigDecimal SP;
    private BigDecimal Bazis;
    private BigDecimal bapor;
    private BigDecimal zakat;
    private BigDecimal umr;

    private String ApprovalFlag;
    private Timestamp approvalDate;
    private String approvalId;
    private String approvalName;

    //approve unit
    private String approvalUnitFlag;
    private String approvalUnitId;
    private Timestamp approvalUnitDate;
    private String approvalUnitName;

    private String flagPayroll;
    private String danaPensiun;
    private Date tanggalAktif;
    private Date tanggalPensiun;
    private Date tanggalAkhirKontrak;

    private String flagThr;
    private String flagPendidikan;
    private String flagJasprod;
    private String flagInsentif;
    private String flagPensiun;
    private String flagJubileum;
    private String flagRapel;
    private String flagZakat;

    private BigDecimal totalThr;
    private BigDecimal totalPendidikan;
    private BigDecimal totalJasProd;
    private BigDecimal totalInsentif;
    private BigDecimal totalRapel;
    private BigDecimal totalRapelBulan;
    private BigDecimal totalRapelThr;
    private BigDecimal totalRapelPendidikan;
    private BigDecimal totalRapelInsentif;
    private BigDecimal totalRapelJubileum;
    private BigDecimal totalJubileum;
    private BigDecimal nettoJubileum;
    private BigDecimal totalPensiun;
    private BigDecimal nettoPensiun;
    private BigDecimal pphTahun;
    private BigDecimal pphJubileum;

    private BigDecimal totalA;
    private BigDecimal totalB;
    private BigDecimal totalC;
    private BigDecimal totalD;
    private BigDecimal gajiBersih;

    private BigDecimal asumsiThr;
    private BigDecimal asumsiPendidikan;
    private BigDecimal asumsiJasprod;
    private String pphId;
    private String namaBank;
    private String cabangBank;
    private String noRek;

    private String centangListrikAir;
    private String centangPerumahan;

    private int bulanAktif;

    //untuk JASPROD
    private BigDecimal nilaiSmk;
    private BigDecimal gajiMasaKerja;
    private BigDecimal faktorKali;
    private BigDecimal persenSmk;
    private BigDecimal gajiMasaKerjaFaktorSmk;
    private BigDecimal gajiMasaKerjaFaktor;
    private BigDecimal tambahan;
    private BigDecimal brutoJasprod;
    private BigDecimal selisihTotalGajiSmkFaktor;
    private double jumlahPersenSmk;

    // untuk insentif
    private double potonganInsentif;
    private BigDecimal potonganInsentifIndividu;
    private double smkStandart;
    private String smkHuruf;
    private double smkAngka;
    private BigDecimal insentifDiterima;

    private Integer masaKerjaBulan;

    private BigDecimal iuranJkmJkk;

    //tambahan irfan
    //data pegawai
    private int masaKerjaGol;
    private String golonganDapenId;
    private String golonganDapenName;
    private String profesiId;
    private String profesiName;
    //tunjangan - tunjangan
    private BigDecimal tambahanLain;
    private BigDecimal tunjanganLain;
    private BigDecimal tunjanganDapen;
    private BigDecimal tunjanganTambahan;
    private BigDecimal tunjanganPph;
    private BigDecimal tunjanganBpjsKs;
    private BigDecimal tunjanganBpjsTk;
    private BigDecimal tunjanganSosialLain;
    private BigDecimal pemondokan;
    private BigDecimal komunikasi;


    //tunjangan RLAB

    private BigDecimal tunjanganRumah;
    private BigDecimal tunjanganListrik;
    private BigDecimal tunjanganAir;
    private BigDecimal tunjanganBBM;
    private BigDecimal totalRlab;

    //iuran Pensiun
    private BigDecimal iuranDapenPeg;
    private BigDecimal iuranDapenPersh;

    //iuran bpjs
    private BigDecimal iuranBpjsTkKary;
    private BigDecimal iuranBpjsTkPers;
    private BigDecimal iuranBpjsKsKary;
    private BigDecimal iuranBpjsKsPers;

    //iuran ypks
    private BigDecimal iuranYpks;

    //potongan rincian C
    private BigDecimal totalPotonganLain;
    private BigDecimal gajiKotor;
    private BigDecimal kopkar;
    private BigDecimal iuranSp;
    private BigDecimal iuranPiikb;
    private BigDecimal bankBri;
    private BigDecimal bankMandiri;
    private BigDecimal infaq;
    private BigDecimal PerkesDanObat;
    private BigDecimal listrik;
    private BigDecimal iuranProfesi;
    private BigDecimal potonganLain;
    private BigDecimal lainLain;
    private String idLainLain;

    //pph bulan 12
    private BigDecimal pphSeharusnya;
    private BigDecimal pph11Bulan;
    private BigDecimal selisihPph;
    private BigDecimal totalLain11Bulan;

    private String gender;
    private String tipePegawai;
    private String tipePegawaiName;
    private String strukturGaji;
    private Date tanggal;
    private BigDecimal biodataGaji;
    private int masaKerja;
    private String statusGiling;
    private String danaPensiunName;
    private String flagPjs;
    private String npwp;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private int jumlahPegawai;

    private ImHrisTipePegawai imHrisTipePegawai;
    private ImBranches imBranches;
    private ImDepartmentEntity imDepartmentEntity;
    private ImPosition imPosition;
    private ImBiodataEntity imBiodataEntity;
    private ItPayrollPphEntity itPayrollPphEntity;


    public String getApprovalUnitFlag() {
        return approvalUnitFlag;
    }

    public void setApprovalUnitFlag(String approvalUnitFlag) {
        this.approvalUnitFlag = approvalUnitFlag;
    }

    public String getApprovalUnitId() {
        return approvalUnitId;
    }

    public void setApprovalUnitId(String approvalUnitId) {
        this.approvalUnitId = approvalUnitId;
    }

    public Timestamp getApprovalUnitDate() {
        return approvalUnitDate;
    }

    public void setApprovalUnitDate(Timestamp approvalUnitDate) {
        this.approvalUnitDate = approvalUnitDate;
    }

    public String getApprovalUnitName() {
        return approvalUnitName;
    }

    public void setApprovalUnitName(String approvalUnitName) {
        this.approvalUnitName = approvalUnitName;
    }

    public BigDecimal getPph11Bulan() {
        return pph11Bulan;
    }

    public void setPph11Bulan(BigDecimal pph11Bulan) {
        this.pph11Bulan = pph11Bulan;
    }

    public BigDecimal getPphSeharusnya() {
        return pphSeharusnya;
    }

    public void setPphSeharusnya(BigDecimal pphSeharusnya) {
        this.pphSeharusnya = pphSeharusnya;
    }

    public BigDecimal getSelisihPph() {
        return selisihPph;
    }

    public void setSelisihPph(BigDecimal selisihPph) {
        this.selisihPph = selisihPph;
    }

    public BigDecimal getTotalLain11Bulan() {
        return totalLain11Bulan;
    }

    public void setTotalLain11Bulan(BigDecimal totalLain11Bulan) {
        this.totalLain11Bulan = totalLain11Bulan;
    }

    //flag baru
    private String flagCutiTahunan;
    private String flagCutiPanjang;

    public String getIdLainLain() {
        return idLainLain;
    }

    public void setIdLainLain(String idLainLain) {
        this.idLainLain = idLainLain;
    }

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public String getProfesiName() {
        return profesiName;
    }

    public void setProfesiName(String profesiName) {
        this.profesiName = profesiName;
    }

    public String getFlagCutiPanjang() {
        return flagCutiPanjang;
    }

    public void setFlagCutiPanjang(String flagCutiPanjang) {
        this.flagCutiPanjang = flagCutiPanjang;
    }

    public String getFlagCutiTahunan() {
        return flagCutiTahunan;
    }

    public void setFlagCutiTahunan(String flagCutiTahunan) {
        this.flagCutiTahunan = flagCutiTahunan;
    }

    public Date getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(Date tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public BigDecimal getTambahanLain() {
        return tambahanLain;
    }

    public void setTambahanLain(BigDecimal tambahanLain) {
        this.tambahanLain = tambahanLain;
    }

    public BigDecimal getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(BigDecimal komunikasi) {
        this.komunikasi = komunikasi;
    }

    public BigDecimal getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(BigDecimal pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getBankBri() {
        return bankBri;
    }

    public void setBankBri(BigDecimal bankBri) {
        this.bankBri = bankBri;
    }

    public BigDecimal getBankMandiri() {
        return bankMandiri;
    }

    public void setBankMandiri(BigDecimal bankMandiri) {
        this.bankMandiri = bankMandiri;
    }

    public BigDecimal getGajiKotor() {
        return gajiKotor;
    }

    public void setGajiKotor(BigDecimal gajiKotor) {
        this.gajiKotor = gajiKotor;
    }

    public String getGolonganDapenName() {
        return golonganDapenName;
    }

    public void setGolonganDapenName(String golonganDapenName) {
        this.golonganDapenName = golonganDapenName;
    }

    public BigDecimal getInfaq() {
        return infaq;
    }

    public void setInfaq(BigDecimal infaq) {
        this.infaq = infaq;
    }

    public BigDecimal getIuranBpjsKsKary() {
        return iuranBpjsKsKary;
    }

    public void setIuranBpjsKsKary(BigDecimal iuranBpjsKsKary) {
        this.iuranBpjsKsKary = iuranBpjsKsKary;
    }

    public BigDecimal getIuranBpjsKsPers() {
        return iuranBpjsKsPers;
    }

    public void setIuranBpjsKsPers(BigDecimal iuranBpjsKsPers) {
        this.iuranBpjsKsPers = iuranBpjsKsPers;
    }

    public BigDecimal getIuranBpjsTkKary() {
        return iuranBpjsTkKary;
    }

    public void setIuranBpjsTkKary(BigDecimal iuranBpjsTkKary) {
        this.iuranBpjsTkKary = iuranBpjsTkKary;
    }

    public BigDecimal getIuranBpjsTkPers() {
        return iuranBpjsTkPers;
    }

    public void setIuranBpjsTkPers(BigDecimal iuranBpjsTkPers) {
        this.iuranBpjsTkPers = iuranBpjsTkPers;
    }

    public BigDecimal getIuranDapenPeg() {
        return iuranDapenPeg;
    }

    public void setIuranDapenPeg(BigDecimal iuranDapenPeg) {
        this.iuranDapenPeg = iuranDapenPeg;
    }

    public BigDecimal getIuranDapenPersh() {
        return iuranDapenPersh;
    }

    public void setIuranDapenPersh(BigDecimal iuranDapenPersh) {
        this.iuranDapenPersh = iuranDapenPersh;
    }

    public BigDecimal getIuranPiikb() {
        return iuranPiikb;
    }

    public void setIuranPiikb(BigDecimal iuranPiikb) {
        this.iuranPiikb = iuranPiikb;
    }

    public BigDecimal getIuranProfesi() {
        return iuranProfesi;
    }

    public void setIuranProfesi(BigDecimal iuranProfesi) {
        this.iuranProfesi = iuranProfesi;
    }

    public BigDecimal getIuranSp() {
        return iuranSp;
    }

    public void setIuranSp(BigDecimal iuranSp) {
        this.iuranSp = iuranSp;
    }

    public BigDecimal getIuranYpks() {
        return iuranYpks;
    }

    public void setIuranYpks(BigDecimal iuranYpks) {
        this.iuranYpks = iuranYpks;
    }

    public BigDecimal getKopkar() {
        return kopkar;
    }

    public void setKopkar(BigDecimal kopkar) {
        this.kopkar = kopkar;
    }

    public BigDecimal getListrik() {
        return listrik;
    }

    public void setListrik(BigDecimal listrik) {
        this.listrik = listrik;
    }

    public BigDecimal getPerkesDanObat() {
        return PerkesDanObat;
    }

    public void setPerkesDanObat(BigDecimal perkesDanObat) {
        PerkesDanObat = perkesDanObat;
    }

    public BigDecimal getPotonganLain() {
        return potonganLain;
    }

    public void setPotonganLain(BigDecimal potonganLain) {
        this.potonganLain = potonganLain;
    }

    public BigDecimal getTotalPotonganLain() {
        return totalPotonganLain;
    }

    public void setTotalPotonganLain(BigDecimal totalPotonganLain) {
        this.totalPotonganLain = totalPotonganLain;
    }

    public BigDecimal getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(BigDecimal totalRlab) {
        this.totalRlab = totalRlab;
    }

    public BigDecimal getTunjanganAir() {
        return tunjanganAir;
    }

    public void setTunjanganAir(BigDecimal tunjanganAir) {
        this.tunjanganAir = tunjanganAir;
    }

    public BigDecimal getTunjanganBBM() {
        return tunjanganBBM;
    }

    public void setTunjanganBBM(BigDecimal tunjanganBBM) {
        this.tunjanganBBM = tunjanganBBM;
    }

    public BigDecimal getTunjanganBpjsKs() {
        return tunjanganBpjsKs;
    }

    public void setTunjanganBpjsKs(BigDecimal tunjanganBpjsKs) {
        this.tunjanganBpjsKs = tunjanganBpjsKs;
    }

    public BigDecimal getTunjanganBpjsTk() {
        return tunjanganBpjsTk;
    }

    public void setTunjanganBpjsTk(BigDecimal tunjanganBpjsTk) {
        this.tunjanganBpjsTk = tunjanganBpjsTk;
    }

    public BigDecimal getTunjanganDapen() {
        return tunjanganDapen;
    }

    public void setTunjanganDapen(BigDecimal tunjanganDapen) {
        this.tunjanganDapen = tunjanganDapen;
    }

    public BigDecimal getTunjanganListrik() {
        return tunjanganListrik;
    }

    public void setTunjanganListrik(BigDecimal tunjanganListrik) {
        this.tunjanganListrik = tunjanganListrik;
    }

    public BigDecimal getTunjanganRumah() {
        return tunjanganRumah;
    }

    public void setTunjanganRumah(BigDecimal tunjanganRumah) {
        this.tunjanganRumah = tunjanganRumah;
    }

    public BigDecimal getTunjanganSosialLain() {
        return tunjanganSosialLain;
    }

    public void setTunjanganSosialLain(BigDecimal tunjanganSosialLain) {
        this.tunjanganSosialLain = tunjanganSosialLain;
    }

    public BigDecimal getTunjanganTambahan() {
        return tunjanganTambahan;
    }

    public void setTunjanganTambahan(BigDecimal tunjanganTambahan) {
        this.tunjanganTambahan = tunjanganTambahan;
    }

    public String getGolonganDapenId() {
        return golonganDapenId;
    }

    public void setGolonganDapenId(String golonganDapenId) {
        this.golonganDapenId = golonganDapenId;
    }

    public int getMasaKerjaGol() {
        return masaKerjaGol;
    }

    public void setMasaKerjaGol(int masaKerjaGol) {
        this.masaKerjaGol = masaKerjaGol;
    }

    public BigDecimal getIuranJkmJkk() {
        return iuranJkmJkk;
    }

    public void setIuranJkmJkk(BigDecimal iuranJkmJkk) {
        this.iuranJkmJkk = iuranJkmJkk;
    }

    public Integer getMasaKerjaBulan() {
        return masaKerjaBulan;
    }

    public void setMasaKerjaBulan(Integer masaKerjaBulan) {
        this.masaKerjaBulan = masaKerjaBulan;
    }

    public BigDecimal getPphGajiRapel() {
        return pphGajiRapel;
    }

    public void setPphGajiRapel(BigDecimal pphGajiRapel) {
        this.pphGajiRapel = pphGajiRapel;
    }

    public BigDecimal getPphGajiRapelPribadi() {
        return pphGajiRapelPribadi;
    }

    public void setPphGajiRapelPribadi(BigDecimal pphGajiRapelPribadi) {
        this.pphGajiRapelPribadi = pphGajiRapelPribadi;
    }

    public BigDecimal getTotalRapelInsentif() {
        return totalRapelInsentif;
    }

    public void setTotalRapelInsentif(BigDecimal totalRapelInsentif) {
        this.totalRapelInsentif = totalRapelInsentif;
    }

    public BigDecimal getTotalRapelJubileum() {
        return totalRapelJubileum;
    }

    public void setTotalRapelJubileum(BigDecimal totalRapelJubileum) {
        this.totalRapelJubileum = totalRapelJubileum;
    }

    public BigDecimal getTotalRapelPendidikan() {
        return totalRapelPendidikan;
    }

    public void setTotalRapelPendidikan(BigDecimal totalRapelPendidikan) {
        this.totalRapelPendidikan = totalRapelPendidikan;
    }

    public BigDecimal getTotalRapelThr() {
        return totalRapelThr;
    }

    public void setTotalRapelThr(BigDecimal totalRapelThr) {
        this.totalRapelThr = totalRapelThr;
    }

    public BigDecimal getTotalRapelBulan() {
        return totalRapelBulan;
    }

    public void setTotalRapelBulan(BigDecimal totalRapelBulan) {
        this.totalRapelBulan = totalRapelBulan;
    }

    public BigDecimal getPphJubileum() {
        return pphJubileum;
    }

    public void setPphJubileum(BigDecimal pphJubileum) {
        this.pphJubileum = pphJubileum;
    }

    public double getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(double jamLembur) {
        this.jamLembur = jamLembur;
    }

    public BigDecimal getBiayaLemburLama() {
        return biayaLemburLama;
    }

    public void setBiayaLemburLama(BigDecimal biayaLemburLama) {
        this.biayaLemburLama = biayaLemburLama;
    }

    public String getAbsensiId() {
        return absensiId;
    }

    public void setAbsensiId(String absensiId) {
        this.absensiId = absensiId;
    }

    public BigDecimal getPotPphLain() {
        return potPphLain;
    }

    public void setPotPphLain(BigDecimal potPphLain) {
        this.potPphLain = potPphLain;
    }

    public BigDecimal getInsentifDiterima() {
        return insentifDiterima;
    }

    public void setInsentifDiterima(BigDecimal insentifDiterima) {
        this.insentifDiterima = insentifDiterima;
    }

    public double getSmkStandart() {
        return smkStandart;
    }

    public void setSmkStandart(double smkStandart) {
        this.smkStandart = smkStandart;
    }

    public String getSmkHuruf() {
        return smkHuruf;
    }

    public void setSmkHuruf(String smkHuruf) {
        this.smkHuruf = smkHuruf;
    }

    public double getSmkAngka() {
        return smkAngka;
    }

    public void setSmkAngka(double smkAngka) {
        this.smkAngka = smkAngka;
    }

    public BigDecimal getPotonganInsentifIndividu() {
        return potonganInsentifIndividu;
    }

    public void setPotonganInsentifIndividu(BigDecimal potonganInsentifIndividu) {
        this.potonganInsentifIndividu = potonganInsentifIndividu;
    }

    public double getPotonganInsentif() {
        return potonganInsentif;
    }

    public void setPotonganInsentif(double potonganInsentif) {
        this.potonganInsentif = potonganInsentif;
    }

    public BigDecimal getTotalInsentif() {
        return totalInsentif;
    }

    public void setTotalInsentif(BigDecimal totalInsentif) {
        this.totalInsentif = totalInsentif;
    }

    public String getFlagInsentif() {
        return flagInsentif;
    }

    public void setFlagInsentif(String flagInsentif) {
        this.flagInsentif = flagInsentif;
    }

    public BigDecimal getPotPph() {
        return potPph;
    }

    public void setPotPph(BigDecimal potPph) {
        this.potPph = potPph;
    }

    public BigDecimal getNilaiSmk() {
        return nilaiSmk;
    }

    public void setNilaiSmk(BigDecimal nilaiSmk) {
        this.nilaiSmk = nilaiSmk;
    }

    public BigDecimal getGajiMasaKerja() {
        return gajiMasaKerja;
    }

    public void setGajiMasaKerja(BigDecimal gajiMasaKerja) {
        this.gajiMasaKerja = gajiMasaKerja;
    }

    public BigDecimal getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(BigDecimal faktorKali) {
        this.faktorKali = faktorKali;
    }

    public BigDecimal getPersenSmk() {
        return persenSmk;
    }

    public void setPersenSmk(BigDecimal persenSmk) {
        this.persenSmk = persenSmk;
    }

    public BigDecimal getGajiMasaKerjaFaktorSmk() {
        return gajiMasaKerjaFaktorSmk;
    }

    public void setGajiMasaKerjaFaktorSmk(BigDecimal gajiMasaKerjaFaktorSmk) {
        this.gajiMasaKerjaFaktorSmk = gajiMasaKerjaFaktorSmk;
    }

    public BigDecimal getGajiMasaKerjaFaktor() {
        return gajiMasaKerjaFaktor;
    }

    public void setGajiMasaKerjaFaktor(BigDecimal gajiMasaKerjaFaktor) {
        this.gajiMasaKerjaFaktor = gajiMasaKerjaFaktor;
    }

    public BigDecimal getTambahan() {
        return tambahan;
    }

    public void setTambahan(BigDecimal tambahan) {
        this.tambahan = tambahan;
    }

    public BigDecimal getBrutoJasprod() {
        return brutoJasprod;
    }

    public void setBrutoJasprod(BigDecimal brutoJasprod) {
        this.brutoJasprod = brutoJasprod;
    }

    public BigDecimal getSelisihTotalGajiSmkFaktor() {
        return selisihTotalGajiSmkFaktor;
    }

    public void setSelisihTotalGajiSmkFaktor(BigDecimal selisihTotalGajiSmkFaktor) {
        this.selisihTotalGajiSmkFaktor = selisihTotalGajiSmkFaktor;
    }

    public double getJumlahPersenSmk() {
        return jumlahPersenSmk;
    }

    public void setJumlahPersenSmk(double jumlahPersenSmk) {
        this.jumlahPersenSmk = jumlahPersenSmk;
    }

    public BigDecimal getNettoPensiun() {
        return nettoPensiun;
    }

    public void setNettoPensiun(BigDecimal nettoPensiun) {
        this.nettoPensiun = nettoPensiun;
    }

    public BigDecimal getNettoJubileum() {
        return nettoJubileum;
    }

    public void setNettoJubileum(BigDecimal nettoJubileum) {
        this.nettoJubileum = nettoJubileum;
    }

    public int getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(int bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public String getCabangBank() {
        return cabangBank;
    }

    public void setCabangBank(String cabangBank) {
        this.cabangBank = cabangBank;
    }

    public BigDecimal getJumlahPph() {
        return jumlahPph;
    }

    public void setJumlahPph(BigDecimal jumlahPph) {
        this.jumlahPph = jumlahPph;
    }

    public String getNamaBagian() {
        return namaBagian;
    }

    public void setNamaBagian(String namaBagian) {
        this.namaBagian = namaBagian;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public BigDecimal getIuranPensiunJumlah() {
        return iuranPensiunJumlah;
    }

    public void setIuranPensiunJumlah(BigDecimal iuranPensiunJumlah) {
        this.iuranPensiunJumlah = iuranPensiunJumlah;
    }

    public BigDecimal getIuranPensiunPerusahaan() {
        return iuranPensiunPerusahaan;
    }

    public void setIuranPensiunPerusahaan(BigDecimal iuranPensiunPerusahaan) {
        this.iuranPensiunPerusahaan = iuranPensiunPerusahaan;
    }

    public String getPphId() {
        return pphId;
    }

    public void setPphId(String pphId) {
        this.pphId = pphId;
    }

    public BigDecimal getAsumsiThr() {
        return asumsiThr;
    }

    public void setAsumsiThr(BigDecimal asumsiThr) {
        this.asumsiThr = asumsiThr;
    }

    public BigDecimal getAsumsiPendidikan() {
        return asumsiPendidikan;
    }

    public void setAsumsiPendidikan(BigDecimal asumsiPendidikan) {
        this.asumsiPendidikan = asumsiPendidikan;
    }

    public BigDecimal getAsumsiJasprod() {
        return asumsiJasprod;
    }

    public void setAsumsiJasprod(BigDecimal asumsiJasprod) {
        this.asumsiJasprod = asumsiJasprod;
    }

    public int getPointLebih() {
        return pointLebih;
    }

    public void setPointLebih(int pointLebih) {
        this.pointLebih = pointLebih;
    }

    public String getGolonganDapenNusindo() {
        return golonganDapenNusindo;
    }

    public void setGolonganDapenNusindo(String golonganDapenNusindo) {
        this.golonganDapenNusindo = golonganDapenNusindo;
    }

    public String getGolonganDapen() {
        return golonganDapen;
    }

    public void setGolonganDapen(String golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    public BigDecimal getPphTahun() {
        return pphTahun;
    }

    public void setPphTahun(BigDecimal pphTahun) {
        this.pphTahun = pphTahun;
    }

    public String getCentangListrikAir() {
        return centangListrikAir;
    }

    public void setCentangListrikAir(String centangListrikAir) {
        this.centangListrikAir = centangListrikAir;
    }

    public String getCentangPerumahan() {
        return centangPerumahan;
    }

    public void setCentangPerumahan(String centangPerumahan) {
        this.centangPerumahan = centangPerumahan;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public BigDecimal getUmr() {
        return umr;
    }

    public void setUmr(BigDecimal umr) {
        this.umr = umr;
    }

    public ItPayrollPphEntity getItPayrollPphEntity() {
        return itPayrollPphEntity;
    }

    public void setItPayrollPphEntity(ItPayrollPphEntity itPayrollPphEntity) {
        this.itPayrollPphEntity = itPayrollPphEntity;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getDanaPensiunName() {
        return danaPensiunName;
    }

    public void setDanaPensiunName(String danaPensiunName) {
        this.danaPensiunName = danaPensiunName;
    }

    public String getFlagPjs() {
        return flagPjs;
    }

    public void setFlagPjs(String flagPjs) {
        this.flagPjs = flagPjs;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public ImHrisTipePegawai getImHrisTipePegawai() {
        return imHrisTipePegawai;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public void setImHrisTipePegawai(ImHrisTipePegawai imHrisTipePegawai) {
        this.imHrisTipePegawai = imHrisTipePegawai;
    }

    public BigDecimal getBiodataGaji() {
        return biodataGaji;
    }

    public void setBiodataGaji(BigDecimal biodataGaji) {
        this.biodataGaji = biodataGaji;
    }

    public String getStrukturGaji() {
        return strukturGaji;
    }

    public void setStrukturGaji(String strukturGaji) {
        this.strukturGaji = strukturGaji;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public int getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(int masaKerja) {
        this.masaKerja = masaKerja;
    }

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public BigDecimal getTotalPensiun() {
        return totalPensiun;
    }

    public void setTotalPensiun(BigDecimal totalPensiun) {
        this.totalPensiun = totalPensiun;
    }

    public String getFlagJubileum() {
        return flagJubileum;
    }

    public void setFlagJubileum(String flagJubileum) {
        this.flagJubileum = flagJubileum;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public Date getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(Date tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public String getFlagPensiun() {
        return flagPensiun;
    }

    public void setFlagPensiun(String flagPensiun) {
        this.flagPensiun = flagPensiun;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public BigDecimal getTotalD() {
        return totalD;
    }

    public void setTotalD(BigDecimal totalD) {
        this.totalD = totalD;
    }

    public BigDecimal getTotalJubileum() {
        return totalJubileum;
    }

    public void setTotalJubileum(BigDecimal totalJubileum) {
        this.totalJubileum = totalJubileum;
    }

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public BigDecimal getGajiBersih() {
        return gajiBersih;
    }

    public void setGajiBersih(BigDecimal gajiBersih) {
        this.gajiBersih = gajiBersih;
    }

    public BigDecimal getTotalA() {
        return totalA;
    }

    public void setTotalA(BigDecimal totalA) {
        this.totalA = totalA;
    }

    public BigDecimal getTotalB() {
        return totalB;
    }

    public void setTotalB(BigDecimal totalB) {
        this.totalB = totalB;
    }

    public BigDecimal getTotalC() {
        return totalC;
    }

    public void setTotalC(BigDecimal totalC) {
        this.totalC = totalC;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getJumlahPegawai() {
        return jumlahPegawai;
    }

    public void setJumlahPegawai(int jumlahPegawai) {
        this.jumlahPegawai = jumlahPegawai;
    }

    public String getFlagZakat() {
        return flagZakat;
    }

    public void setFlagZakat(String flagZakat) {
        this.flagZakat = flagZakat;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getKelompokName() {
        return kelompokName;
    }

    public void setKelompokName(String kelompokName) {
        this.kelompokName = kelompokName;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public BigDecimal getGajiPensiun() {
        return gajiPensiun;
    }

    public void setGajiPensiun(BigDecimal gajiPensiun) {
        this.gajiPensiun = gajiPensiun;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApprovalFlag() {
        return ApprovalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        ApprovalFlag = approvalFlag;
    }

    public BigDecimal getBapor() {
        return bapor;
    }

    public void setBapor(BigDecimal bapor) {
        this.bapor = bapor;
    }

    public BigDecimal getBazis() {
        return Bazis;
    }

    public void setBazis(BigDecimal bazis) {
        Bazis = bazis;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
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

    public BigDecimal getDansos() {
        return dansos;
    }

    public void setDansos(BigDecimal dansos) {
        this.dansos = dansos;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFaktorKeluargaId() {
        return faktorKeluargaId;
    }

    public void setFaktorKeluargaId(String faktorKeluargaId) {
        this.faktorKeluargaId = faktorKeluargaId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagJasprod() {
        return flagJasprod;
    }

    public void setFlagJasprod(String flagJasprod) {
        this.flagJasprod = flagJasprod;
    }

    public String getFlagPayroll() {
        return flagPayroll;
    }

    public void setFlagPayroll(String flagPayroll) {
        this.flagPayroll = flagPayroll;
    }

    public String getFlagPendidikan() {
        return flagPendidikan;
    }

    public void setFlagPendidikan(String flagPendidikan) {
        this.flagPendidikan = flagPendidikan;
    }

    public String getFlagRapel() {
        return flagRapel;
    }

    public void setFlagRapel(String flagRapel) {
        this.flagRapel = flagRapel;
    }

    public String getFlagThr() {
        return flagThr;
    }

    public void setFlagThr(String flagThr) {
        this.flagThr = flagThr;
    }

    public BigDecimal getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(BigDecimal gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public BigDecimal getGajiPensiunBpjs() {
        return gajiPensiunBpjs;
    }

    public void setGajiPensiunBpjs(BigDecimal gajiPensiunBpjs) {
        this.gajiPensiunBpjs = gajiPensiunBpjs;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public BigDecimal getIuranBpjsKesehatan() {
        return iuranBpjsKesehatan;
    }

    public void setIuranBpjsKesehatan(BigDecimal iuranBpjsKesehatan) {
        this.iuranBpjsKesehatan = iuranBpjsKesehatan;
    }

    public BigDecimal getIuranBpjsPensiun() {
        return iuranBpjsPensiun;
    }

    public void setIuranBpjsPensiun(BigDecimal iuranBpjsPensiun) {
        this.iuranBpjsPensiun = iuranBpjsPensiun;
    }

    public BigDecimal getIuranBpjsTk() {
        return iuranBpjsTk;
    }

    public void setIuranBpjsTk(BigDecimal iuranBpjsTk) {
        this.iuranBpjsTk = iuranBpjsTk;
    }

    public BigDecimal getIuranPensiun() {
        return iuranPensiun;
    }

    public void setIuranPensiun(BigDecimal iuranPensiun) {
        this.iuranPensiun = iuranPensiun;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public BigDecimal getKekuranganBpjsTk() {
        return kekuranganBpjsTk;
    }

    public void setKekuranganBpjsTk(BigDecimal kekuranganBpjsTk) {
        this.kekuranganBpjsTk = kekuranganBpjsTk;
    }

    public BigDecimal getKompensasi() {
        return kompensasi;
    }

    public void setKompensasi(BigDecimal kompensasi) {
        this.kompensasi = kompensasi;
    }

    public BigDecimal getKoperasi() {
        return koperasi;
    }

    public void setKoperasi(BigDecimal koperasi) {
        this.koperasi = koperasi;
    }

    public BigDecimal getLainLain() {
        return lainLain;
    }

    public void setLainLain(BigDecimal lainLain) {
        this.lainLain = lainLain;
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

    public String getMultifikator() {
        return multifikator;
    }

    public void setMultifikator(String multifikator) {
        this.multifikator = multifikator;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getNilaiFaktorKeluarga() {
        return nilaiFaktorKeluarga;
    }

    public void setNilaiFaktorKeluarga(BigDecimal nilaiFaktorKeluarga) {
        this.nilaiFaktorKeluarga = nilaiFaktorKeluarga;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public BigDecimal getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(BigDecimal pengobatan) {
        this.pengobatan = pengobatan;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public BigDecimal getPphGaji() {
        return pphGaji;
    }

    public void setPphGaji(BigDecimal pphGaji) {
        this.pphGaji = pphGaji;
    }

    public BigDecimal getPphPengobatan() {
        return pphPengobatan;
    }

    public void setPphPengobatan(BigDecimal pphPengobatan) {
        this.pphPengobatan = pphPengobatan;
    }

    public BigDecimal getSP() {
        return SP;
    }

    public void setSP(BigDecimal SP) {
        this.SP = SP;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getTotalJasProd() {
        return totalJasProd;
    }

    public void setTotalJasProd(BigDecimal totalJasProd) {
        this.totalJasProd = totalJasProd;
    }

    public BigDecimal getTotalPendidikan() {
        return totalPendidikan;
    }

    public void setTotalPendidikan(BigDecimal totalPendidikan) {
        this.totalPendidikan = totalPendidikan;
    }

    public BigDecimal getTotalRapel() {
        return totalRapel;
    }

    public void setTotalRapel(BigDecimal totalRapel) {
        this.totalRapel = totalRapel;
    }

    public BigDecimal getTotalThr() {
        return totalThr;
    }

    public void setTotalThr(BigDecimal totalThr) {
        this.totalThr = totalThr;
    }

    public BigDecimal getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public void setTunjanganAirListrik(BigDecimal tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
    }

    public BigDecimal getTunjanganBajuDinas() {
        return tunjanganBajuDinas;
    }

    public void setTunjanganBajuDinas(BigDecimal tunjanganBajuDinas) {
        this.tunjanganBajuDinas = tunjanganBajuDinas;
    }

    public BigDecimal getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(BigDecimal tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganLain() {
        return tunjanganLain;
    }

    public void setTunjanganLain(BigDecimal tunjanganLain) {
        this.tunjanganLain = tunjanganLain;
    }

    public BigDecimal getTunjanganLembur() {
        return tunjanganLembur;
    }

    public void setTunjanganLembur(BigDecimal tunjanganLembur) {
        this.tunjanganLembur = tunjanganLembur;
    }

    public BigDecimal getTunjanganPendidikan() {
        return tunjanganPendidikan;
    }

    public void setTunjanganPendidikan(BigDecimal tunjanganPendidikan) {
        this.tunjanganPendidikan = tunjanganPendidikan;
    }

    public BigDecimal getTunjanganPengobatan() {
        return tunjanganPengobatan;
    }

    public void setTunjanganPengobatan(BigDecimal tunjanganPengobatan) {
        this.tunjanganPengobatan = tunjanganPengobatan;
    }

    public BigDecimal getTunjanganPerumahan() {
        return tunjanganPerumahan;
    }

    public void setTunjanganPerumahan(BigDecimal tunjanganPerumahan) {
        this.tunjanganPerumahan = tunjanganPerumahan;
    }

    public BigDecimal getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(BigDecimal tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public BigDecimal getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(BigDecimal tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganTransport() {
        return tunjanganTransport;
    }

    public void setTunjanganTransport(BigDecimal tunjanganTransport) {
        this.tunjanganTransport = tunjanganTransport;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getUangMukaLainnya() {
        return uangMukaLainnya;
    }

    public void setUangMukaLainnya(BigDecimal uangMukaLainnya) {
        this.uangMukaLainnya = uangMukaLainnya;
    }

    public BigDecimal getZakat() {
        return zakat;
    }

    public void setZakat(BigDecimal zakat) {
        this.zakat = zakat;
    }
}