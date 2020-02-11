package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class Payroll extends BaseModel {
    //tambahan irfan
    //data pegawai
    private int masaKerjaGol;
    private String stMasaKerjaGol;
    private String golonganDapenId;
    private String golonganDapenName;

    private String tambahanLain;
    private BigDecimal tambahanLainNilai;

    //tunjangan - tunjangan
    private String tunjanganLain;
    private BigDecimal tunjanganLainNilai;
    private String tunjanganDapen;
    private BigDecimal tunjanganDapenNilai;
    private String tunjanganTambahan;
    private BigDecimal tunjanganTambahanNilai;
    private String tunjanganPph;
    private BigDecimal tunjanganPphNilai;
    private String tunjanganBpjsKs;
    private BigDecimal tunjanganBpjsKsNilai;
    private String tunjanganBpjsTk;
    private BigDecimal tunjanganBpjsTkNilai;
    private String tunjanganSosialLain;
    private BigDecimal tunjanganSosialLainNilai;
    private String pemondokan;
    private BigDecimal pemondokanNilai;
    private String komunikasi;
    private BigDecimal komunikasiNilai;


    //tunjangan RLAB
    private String tunjanganRumah;
    private String tunjanganListrik;
    private String tunjanganAir;
    private String tunjanganBbm;
    private String totalRlab;

    private BigDecimal tunjanganRumahNilai;
    private BigDecimal tunjanganListrikNilai;
    private BigDecimal tunjanganAirNilai;
    private BigDecimal tunjanganBBMNilai;
    private BigDecimal totalRlabNilai;

    //iuran Pensiun
    private String iuranDapenPeg;
    private String iuranDapenPersh;

    private BigDecimal iuranDapenPegNilai;
    private BigDecimal iuranDapenPershNilai;

    //iuran bpjs
    private String iuranBpjsTkKary;
    private String iuranBpjsTkPers;
    private String iuranBpjsKsKary;
    private String iuranBpjsKsPersh;

    private BigDecimal iuranBpjsTkKaryNilai;
    private BigDecimal iuranBpjsTkPersNilai;
    private BigDecimal iuranBpjsKsKaryNilai;
    private BigDecimal iuranBpjsKsPersNilai;

    //iuran ypks
    private String iuranYpks;
    private BigDecimal iuranYpksNilai;

    //potongan rincian C
    private String totalPotonganLain;
    private BigDecimal totalPotonganLainNilai;
    private String gajiKotor;
    private BigDecimal gajiKotorNilai;
    private String kopkar;
    private String iuranSp;
    private String iuranPiikb;
    private String bankBri;
    private String bankMandiri;
    private String infaq;
    private String PerkesDanObat;
    private String listrik;
    private String iuranProfesi;
    private String potonganLain;
    private BigDecimal kopkarNilai;
    private BigDecimal iuranSpNilai;
    private BigDecimal iuranPiikbNilai;
    private BigDecimal bankBriNilai;
    private BigDecimal bankMandiriNilai;
    private BigDecimal infaqNilai;
    private BigDecimal PerkesDanObatNilai;
    private BigDecimal listrikNilai;
    private BigDecimal iuranProfesiNilai;
    private BigDecimal potonganLainNilai;

    public String getTambahanLain() {
        return tambahanLain;
    }

    public void setTambahanLain(String tambahanLain) {
        this.tambahanLain = tambahanLain;
    }

    public BigDecimal getTambahanLainNilai() {
        return tambahanLainNilai;
    }

    public void setTambahanLainNilai(BigDecimal tambahanLainNilai) {
        this.tambahanLainNilai = tambahanLainNilai;
    }

    public String getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(String komunikasi) {
        this.komunikasi = komunikasi;
    }

    public BigDecimal getKomunikasiNilai() {
        return komunikasiNilai;
    }

    public void setKomunikasiNilai(BigDecimal komunikasiNilai) {
        this.komunikasiNilai = komunikasiNilai;
    }

    public String getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(String pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getPemondokanNilai() {
        return pemondokanNilai;
    }

    public void setPemondokanNilai(BigDecimal pemondokanNilai) {
        this.pemondokanNilai = pemondokanNilai;
    }

    public String getBankBri() {
        return bankBri;
    }

    public void setBankBri(String bankBri) {
        this.bankBri = bankBri;
    }

    public BigDecimal getBankBriNilai() {
        return bankBriNilai;
    }

    public void setBankBriNilai(BigDecimal bankBriNilai) {
        this.bankBriNilai = bankBriNilai;
    }

    public String getBankMandiri() {
        return bankMandiri;
    }

    public void setBankMandiri(String bankMandiri) {
        this.bankMandiri = bankMandiri;
    }

    public BigDecimal getBankMandiriNilai() {
        return bankMandiriNilai;
    }

    public void setBankMandiriNilai(BigDecimal bankMandiriNilai) {
        this.bankMandiriNilai = bankMandiriNilai;
    }

    public String getInfaq() {
        return infaq;
    }

    public void setInfaq(String infaq) {
        this.infaq = infaq;
    }

    public BigDecimal getInfaqNilai() {
        return infaqNilai;
    }

    public void setInfaqNilai(BigDecimal infaqNilai) {
        this.infaqNilai = infaqNilai;
    }

    public String getIuranPiikb() {
        return iuranPiikb;
    }

    public void setIuranPiikb(String iuranPiikb) {
        this.iuranPiikb = iuranPiikb;
    }

    public BigDecimal getIuranPiikbNilai() {
        return iuranPiikbNilai;
    }

    public void setIuranPiikbNilai(BigDecimal iuranPiikbNilai) {
        this.iuranPiikbNilai = iuranPiikbNilai;
    }

    public String getIuranProfesi() {
        return iuranProfesi;
    }

    public void setIuranProfesi(String iuranProfesi) {
        this.iuranProfesi = iuranProfesi;
    }

    public BigDecimal getIuranProfesiNilai() {
        return iuranProfesiNilai;
    }

    public void setIuranProfesiNilai(BigDecimal iuranProfesiNilai) {
        this.iuranProfesiNilai = iuranProfesiNilai;
    }

    public String getIuranSp() {
        return iuranSp;
    }

    public void setIuranSp(String iuranSp) {
        this.iuranSp = iuranSp;
    }

    public BigDecimal getIuranSpNilai() {
        return iuranSpNilai;
    }

    public void setIuranSpNilai(BigDecimal iuranSpNilai) {
        this.iuranSpNilai = iuranSpNilai;
    }

    public String getKopkar() {
        return kopkar;
    }

    public void setKopkar(String kopkar) {
        this.kopkar = kopkar;
    }

    public BigDecimal getKopkarNilai() {
        return kopkarNilai;
    }

    public void setKopkarNilai(BigDecimal kopkarNilai) {
        this.kopkarNilai = kopkarNilai;
    }

    public String getListrik() {
        return listrik;
    }

    public void setListrik(String listrik) {
        this.listrik = listrik;
    }

    public BigDecimal getListrikNilai() {
        return listrikNilai;
    }

    public void setListrikNilai(BigDecimal listrikNilai) {
        this.listrikNilai = listrikNilai;
    }

    public String getPerkesDanObat() {
        return PerkesDanObat;
    }

    public void setPerkesDanObat(String perkesDanObat) {
        PerkesDanObat = perkesDanObat;
    }

    public BigDecimal getPerkesDanObatNilai() {
        return PerkesDanObatNilai;
    }

    public void setPerkesDanObatNilai(BigDecimal perkesDanObatNilai) {
        PerkesDanObatNilai = perkesDanObatNilai;
    }

    public String getPotonganLain() {
        return potonganLain;
    }

    public void setPotonganLain(String potonganLain) {
        this.potonganLain = potonganLain;
    }

    public BigDecimal getPotonganLainNilai() {
        return potonganLainNilai;
    }

    public void setPotonganLainNilai(BigDecimal potonganLainNilai) {
        this.potonganLainNilai = potonganLainNilai;
    }

    public String getIuranYpks() {
        return iuranYpks;
    }

    public void setIuranYpks(String iuranYpks) {
        this.iuranYpks = iuranYpks;
    }

    public BigDecimal getIuranYpksNilai() {
        return iuranYpksNilai;
    }

    public void setIuranYpksNilai(BigDecimal iuranYpksNilai) {
        this.iuranYpksNilai = iuranYpksNilai;
    }

    public String getTotalPotonganLain() {
        return totalPotonganLain;
    }

    public void setTotalPotonganLain(String totalPotonganLain) {
        this.totalPotonganLain = totalPotonganLain;
    }

    public BigDecimal getTotalPotonganLainNilai() {
        return totalPotonganLainNilai;
    }

    public void setTotalPotonganLainNilai(BigDecimal totalPotonganLainNilai) {
        this.totalPotonganLainNilai = totalPotonganLainNilai;
    }

    public String getTunjanganSosialLain() {
        return tunjanganSosialLain;
    }

    public void setTunjanganSosialLain(String tunjanganSosialLain) {
        this.tunjanganSosialLain = tunjanganSosialLain;
    }

    public BigDecimal getTunjanganSosialLainNilai() {
        return tunjanganSosialLainNilai;
    }

    public void setTunjanganSosialLainNilai(BigDecimal tunjanganSosialLainNilai) {
        this.tunjanganSosialLainNilai = tunjanganSosialLainNilai;
    }

    public String getTunjanganBpjsKs() {
        return tunjanganBpjsKs;
    }

    public void setTunjanganBpjsKs(String tunjanganBpjsKs) {
        this.tunjanganBpjsKs = tunjanganBpjsKs;
    }

    public BigDecimal getTunjanganBpjsKsNilai() {
        return tunjanganBpjsKsNilai;
    }

    public void setTunjanganBpjsKsNilai(BigDecimal tunjanganBpjsKsNilai) {
        this.tunjanganBpjsKsNilai = tunjanganBpjsKsNilai;
    }

    public String getTunjanganBpjsTk() {
        return tunjanganBpjsTk;
    }

    public void setTunjanganBpjsTk(String tunjanganBpjsTk) {
        this.tunjanganBpjsTk = tunjanganBpjsTk;
    }

    public BigDecimal getTunjanganBpjsTkNilai() {
        return tunjanganBpjsTkNilai;
    }

    public void setTunjanganBpjsTkNilai(BigDecimal tunjanganBpjsTkNilai) {
        this.tunjanganBpjsTkNilai = tunjanganBpjsTkNilai;
    }

    public String getTunjanganDapen() {
        return tunjanganDapen;
    }

    public void setTunjanganDapen(String tunjanganDapen) {
        this.tunjanganDapen = tunjanganDapen;
    }

    public BigDecimal getTunjanganDapenNilai() {
        return tunjanganDapenNilai;
    }

    public void setTunjanganDapenNilai(BigDecimal tunjanganDapenNilai) {
        this.tunjanganDapenNilai = tunjanganDapenNilai;
    }

    public String getGolonganDapenName() {
        return golonganDapenName;
    }

    public void setGolonganDapenName(String golonganDapenName) {
        this.golonganDapenName = golonganDapenName;
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

    public String getStMasaKerjaGol() {
        return stMasaKerjaGol;
    }

    public void setStMasaKerjaGol(String stMasaKerjaGol) {
        this.stMasaKerjaGol = stMasaKerjaGol;
    }

    public String getTunjanganTambahan() {
        return tunjanganTambahan;
    }

    public void setTunjanganTambahan(String tunjanganTambahan) {
        this.tunjanganTambahan = tunjanganTambahan;
    }

    public BigDecimal getTunjanganTambahanNilai() {
        return tunjanganTambahanNilai;
    }

    public void setTunjanganTambahanNilai(BigDecimal tunjanganTambahanNilai) {
        this.tunjanganTambahanNilai = tunjanganTambahanNilai;
    }

    public String getIuranBpjsKsKary() {
        return iuranBpjsKsKary;
    }

    public void setIuranBpjsKsKary(String iuranBpjsKsKary) {
        this.iuranBpjsKsKary = iuranBpjsKsKary;
    }

    public BigDecimal getIuranBpjsKsKaryNilai() {
        return iuranBpjsKsKaryNilai;
    }

    public void setIuranBpjsKsKaryNilai(BigDecimal iuranBpjsKsKaryNilai) {
        this.iuranBpjsKsKaryNilai = iuranBpjsKsKaryNilai;
    }

    public String getIuranBpjsKsPersh() {
        return iuranBpjsKsPersh;
    }

    public void setIuranBpjsKsPersh(String iuranBpjsKsPersh) {
        this.iuranBpjsKsPersh = iuranBpjsKsPersh;
    }

    public BigDecimal getIuranBpjsKsPersNilai() {
        return iuranBpjsKsPersNilai;
    }

    public void setIuranBpjsKsPersNilai(BigDecimal iuranBpjsKsPersNilai) {
        this.iuranBpjsKsPersNilai = iuranBpjsKsPersNilai;
    }

    public String getIuranBpjsTkKary() {
        return iuranBpjsTkKary;
    }

    public void setIuranBpjsTkKary(String iuranBpjsTkKary) {
        this.iuranBpjsTkKary = iuranBpjsTkKary;
    }

    public BigDecimal getIuranBpjsTkKaryNilai() {
        return iuranBpjsTkKaryNilai;
    }

    public void setIuranBpjsTkKaryNilai(BigDecimal iuranBpjsTkKaryNilai) {
        this.iuranBpjsTkKaryNilai = iuranBpjsTkKaryNilai;
    }

    public String getIuranBpjsTkPers() {
        return iuranBpjsTkPers;
    }

    public void setIuranBpjsTkPers(String iuranBpjsTkPers) {
        this.iuranBpjsTkPers = iuranBpjsTkPers;
    }

    public BigDecimal getIuranBpjsTkPersNilai() {
        return iuranBpjsTkPersNilai;
    }

    public void setIuranBpjsTkPersNilai(BigDecimal iuranBpjsTkPersNilai) {
        this.iuranBpjsTkPersNilai = iuranBpjsTkPersNilai;
    }



    public String getGajiKotor() {
        return gajiKotor;
    }

    public void setGajiKotor(String gajiKotor) {
        this.gajiKotor = gajiKotor;
    }

    public BigDecimal getGajiKotorNilai() {
        return gajiKotorNilai;
    }

    public void setGajiKotorNilai(BigDecimal gajiKotorNilai) {
        this.gajiKotorNilai = gajiKotorNilai;
    }

    public String getIuranDapenPeg() {
        return iuranDapenPeg;
    }

    public void setIuranDapenPeg(String iuranDapenPeg) {
        this.iuranDapenPeg = iuranDapenPeg;
    }

    public BigDecimal getIuranDapenPegNilai() {
        return iuranDapenPegNilai;
    }

    public void setIuranDapenPegNilai(BigDecimal iuranDapenPegNilai) {
        this.iuranDapenPegNilai = iuranDapenPegNilai;
    }

    public String getIuranDapenPersh() {
        return iuranDapenPersh;
    }

    public void setIuranDapenPersh(String iuranDapenPersh) {
        this.iuranDapenPersh = iuranDapenPersh;
    }

    public BigDecimal getIuranDapenPershNilai() {
        return iuranDapenPershNilai;
    }

    public void setIuranDapenPershNilai(BigDecimal iuranDapenPershNilai) {
        this.iuranDapenPershNilai = iuranDapenPershNilai;
    }

    public String getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(String totalRlab) {
        this.totalRlab = totalRlab;
    }

    public BigDecimal getTotalRlabNilai() {
        return totalRlabNilai;
    }

    public void setTotalRlabNilai(BigDecimal totalRlabNilai) {
        this.totalRlabNilai = totalRlabNilai;
    }

    public BigDecimal getTunjanganAirNilai() {
        return tunjanganAirNilai;
    }

    public void setTunjanganAirNilai(BigDecimal tunjanganAirNilai) {
        this.tunjanganAirNilai = tunjanganAirNilai;
    }

    public BigDecimal getTunjanganBBMNilai() {
        return tunjanganBBMNilai;
    }

    public void setTunjanganBBMNilai(BigDecimal tunjanganBBMNilai) {
        this.tunjanganBBMNilai = tunjanganBBMNilai;
    }

    public BigDecimal getTunjanganListrikNilai() {
        return tunjanganListrikNilai;
    }

    public void setTunjanganListrikNilai(BigDecimal tunjanganListrikNilai) {
        this.tunjanganListrikNilai = tunjanganListrikNilai;
    }

    public BigDecimal getTunjanganRumahNilai() {
        return tunjanganRumahNilai;
    }

    public void setTunjanganRumahNilai(BigDecimal tunjanganRumahNilai) {
        this.tunjanganRumahNilai = tunjanganRumahNilai;
    }

    public String getTunjanganAir() {
        return tunjanganAir;
    }

    public void setTunjanganAir(String tunjanganAir) {
        this.tunjanganAir = tunjanganAir;
    }

    public String getTunjanganBbm() {
        return tunjanganBbm;
    }

    public void setTunjanganBbm(String tunjanganBbm) {
        this.tunjanganBbm = tunjanganBbm;
    }

    public String getTunjanganListrik() {
        return tunjanganListrik;
    }

    public void setTunjanganListrik(String tunjanganListrik) {
        this.tunjanganListrik = tunjanganListrik;
    }

    public String getTunjanganRumah() {
        return tunjanganRumah;
    }

    public void setTunjanganRumah(String tunjanganRumah) {
        this.tunjanganRumah = tunjanganRumah;
    }

    private String payrollId;
    private String bulan;
    private String pembetulan;
    private String tahun;
    private String bulan1;
    private String tahun1;
    private String nip;
    private String namaBagian;
    private String nama;
    private String npwp;
    private String kelompokId;
    private String kelompokName;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private String departmentId;
    private String departmentName;
    private String branchId;
    private String branchName;
    private int point;
    private String statusKeluarga;
    private String statusPegawai;
    private int jumlahAnak;
    private String faktorKeluargaId;
    private String nilaiFaktorKeluarga;
    private String multifikator;
    private String gajiPensiunBpjs;
    private BigDecimal gajiPensiunBpjsNilai;
    private String gajiBpjs;
    private BigDecimal gajiBpjsNilai;
    private String gajiPensiun;
    private BigDecimal gajiPensiunNilai;
    private String gajiGolongan;
    private BigDecimal gajiGolonganNilai;
    private String tunjanganUmk;
    private BigDecimal tunjanganUmkNilai;
    private String tunjanganPeralihan;
    private BigDecimal tunjanganPeralihanNilai;
    private String tunjanganStruktural;
    private BigDecimal tunjanganStrukturalNilai;
    private String tunjanganPendidikan;
    private BigDecimal tunjanganPendidikanNilai;
    private String tunjanganJabatanStruktural;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private String tunjanganStrategis;
    private BigDecimal tunjanganStrategisNilai;
    private String kompensasi;
    private BigDecimal kompensasiNilai;
    private String tunjanganTransport;
    private BigDecimal tunjanganTransportNilai;
    private String tunjanganAirListrik;
    private BigDecimal tunjanganAirListrikNilai;
    private String tunjanganPengobatan;
    private BigDecimal tunjanganPengobatanNilai;
    private String tunjanganPerumahan;
    private BigDecimal tunjanganPerumahanNilai;
    private String potPph;
    private BigDecimal potPphNilai;
    private String tunjanganBajuDinas;
    private BigDecimal tunjanganBajuDinasNilai;
    private String tunjanganLembur;
    private BigDecimal tunjanganLemburNilai;
    private BigDecimal umr;
    private String gender;
    private String tipeThr;

    private String totalA;
    private BigDecimal totalANilai;
    private String totalB;
    private BigDecimal totalBNilai;
    private String totalC;
    private BigDecimal totalCNilai;

    private String pphGaji;
    private BigDecimal pphGajiNilai;
    private String pphPengobatan;
    private BigDecimal pphPengobatanNilai;
    private BigDecimal jumlahPph;
    private String iuranPensiun;
    private String iuranPensiunPerusahaan;
    private String iuranPensiunJumlah;
    private BigDecimal iuranPensiunNilai;
    private BigDecimal iuranPensiunPerusahaanNilai;
    private BigDecimal iuranPensiunJumlahNilai;
    private String iuranBpjsTk;
    private BigDecimal iuranBpjsTkNilai;
    private String iuranBpjsPensiun;
    private BigDecimal iuranBpjsPensiunNilai;
    private String iuranBpjsKesehatan;
    private BigDecimal iuranBpjsKesehatanNilai;
    private String uangMukaLainnya;
    private BigDecimal uangMukaLainnyaNilai;
    private String kekuranganBpjsTk;
    private BigDecimal kekuranganBpjsTkNilai;
    private String pengobatan;
    private BigDecimal pengobatanNilai;
    private String koperasi;
    private BigDecimal koperasiNilai;
    private String dansos;
    private BigDecimal dansosNilai;
    private String SP;
    private BigDecimal SPNilai;
    private String Bazis;
    private BigDecimal BazisNilai;
    private String bapor;
    private BigDecimal baporNilai;
    private String zakat;
    private BigDecimal zakatNilai;
    private String lainLain;
    private BigDecimal lainLainNilai;
    private String ApprovalFlag;
    private String ApprovalId;
    private Timestamp ApprovalDate;
    private String stApprovalDate;
    private String flagPayroll;

    private String flagThr;
    private String flagPendidikan;
    private String flagJasprod;
    private String flagRapel;
    private String flagZakat;
    private String flagJubileum;
    private String flagPensiun;
    private String flagInsentif;
    private String flagPromosi;
    private String flagPjs;

    private String flagRapelGadas;
    private String flagRapelStruktural;
    private String flagRapelUmk;
    private String flagRapelJabatanStruktural;
    private String flagRapelStrategis;
    private String flagRapelAirListrik;
    private String flagRapelPerumahan;
    private String flagRapelPeralihan;

    private String flagRapelThrGadas;
    private String flagRapelThrStruktural;
    private String flagRapelThrUmk;
    private String flagRapelThrJabStruktural;
    private String flagRapelThrStrategis;
    private String flagRapelThrPeralihan;

    private String flagRapelPendidikanGadas;
    private String flagRapelPendidikanStruktural;
    private String flagRapelPendidikanUmk;
    private String flagRapelPendidikanJabStruktural;
    private String flagRapelPendidikanStrategis;
    private String flagRapelPendidikanPeralihan;
    private String flagRapelPendidikanAirListrik;

    private String flagRapelInsentifGadas;
    private String flagRapelInsentifStruktural;
    private String flagRapelInsentifUmk;
    private String flagRapelInsentifJabStruktural;
    private String flagRapelInsentifStrategis;
    private String flagRapelInsentifPeralihan;

    private String flagRapelJubileumGadas;
    private String flagRapelJubileumStruktural;
    private String flagRapelJubileumUmk;
    private String flagRapelJubileumJabStruktural;
    private String flagRapelJubileumStrategis;
    private String flagRapelJubileumPeralihan;


    private String flagRapelThr;
    private String flagRapelPendidikan;
    private String flagRapelInsentif;
    private String flagRapelJasprod;
    private String flagRapelJubileum;
    private String flagRapelLembur;

    private String flagRapelGadasTanggal1;
    private String flagRapelGadasTanggal2;
    private String flagRapelStrukturalTanggal1;
    private String flagRapelStrukturalTanggal2;
    private String flagRapelUmkTanggal1;
    private String flagRapelUmkTanggal2;
    private String flagRapelJabatanStrukturalTanggal1;
    private String flagRapelJabatanStrukturalTanggal2;
    private String flagRapelStrategisTanggal1;
    private String flagRapelStrategisTanggal2;
    private String flagRapelAirListrikTanggal1;
    private String flagRapelAirListrikTanggal2;
    private String flagRapelPerumahanTanggal1;
    private String flagRapelPerumahanTanggal2;
    private String flagRapelLemburTanggal1;
    private String flagRapelLemburTanggal2;
    private String flagRapelThrTanggal1;
    private String flagRapelThrTanggal2;
    private String flagRapelPendidikanTanggal1;
    private String flagRapelPendidikanTanggal2;
    private String flagRapelInsentifTanggal1;
    private String flagRapelInsentifTanggal2;
    private String flagRapelJubileumTanggal1;
    private String flagRapelJubileumTanggal2;

    private String danaPensiunName;
    private String danaPensiun;
    private BigDecimal danaPensiunNilai;
    private String bpjsJht;
    private BigDecimal bpjsJhtNilai;
    private String bpjsPensiun;
    private BigDecimal bpjsPensiunNilai;

    private String totalThr;
    private BigDecimal totalThrNilai;
    private String totalThrBersih;
    private BigDecimal totalThrBersihNilai;
    private String totalPendidikan;
    private BigDecimal totalPendidikanNilai;
    private String totalJasProd;
    private String totalInsentif;
    private BigDecimal totalPensiunNilai;
    private String totalPensiun;
    private BigDecimal nettoPensiunNilai;
    private String nettoPensiun;
    private BigDecimal pphPensiunNilai;
    private String pphPensiun;
    private BigDecimal totalJasProdNilai;
    private BigDecimal totalInsentifNilai;
    private String totalRapel;
    private BigDecimal totalRapelNilai;
    private String besarJubileum;
    private BigDecimal besarJubileumNilai;
    private String totalJubileum;
    private BigDecimal totalJubileumNilai;
    private String totalLembur;
    private BigDecimal totalLemburNilai;
    private String totalKaliJubileum;
    private BigDecimal totalKaliJubileumNilai;
    private String nettoJubileum;
    private BigDecimal nettoJubileumNilai;
    private String totalTambahan;
    private BigDecimal totalTambahanNilai;
    private String tanggalJubileum;
    private String totalGajiBersih;
    private BigDecimal totalGajiBersihNilai;

    //untuk JASPROD
    private String strNilaiSmk;
    private String nilaiSmk;
    private BigDecimal nilaiSmkNilai;
    private String gajiMasaKerja;
    private BigDecimal gajiMasaKerjaNilai;
    private BigDecimal faktorKaliNilai;
    private String faktorKali;
    private String faktorKaliSmk;
    private String strPersenSmk;
    private BigDecimal persenSmkNilai;
    private String persenSmk;
    private BigDecimal jumlahPersenSmk;

    private String perhitungan;
    private BigDecimal perhitunganNilai;
    private String gajiXfaktorNormal;
    private BigDecimal gajiXfaktorNormalNilai;

    private String gajiMasaKerjaFaktorSmk;
    private String gajiMasaKerjaFaktor;
    private String tambahan;
    private String brutoJasprod;
    private String selisihTotalGajiSmkFaktor;
    private String pphJasprod;
    private BigDecimal pphJasprodNilai;
    private String nettoJasprod;
    private BigDecimal nettoJasprodNilai;
    private BigDecimal gajiMasaKerjaFaktorSmkNilai;
    private BigDecimal gajiMasaKerjaFaktorNilai;
    private BigDecimal tambahanNilai;
    private BigDecimal brutoJasprodNilai;
    private BigDecimal selisihTotalGajiSmkFaktorNilai;
    private BigDecimal potPphLainNilai;

    private String potonganInsentif;
    private BigDecimal potonganInsentifNilai;
    private BigDecimal insentifDiterimaNilai;
    private BigDecimal insentifDiterimaLamaNilai;
    private String potPphLain;
    private String insentifDiterima;
    private String insentifDiterimaLama;
    private String smkStandart;
    private BigDecimal smkStandartNilai;
    private String smkHuruf;
    private String smkAngka;
    private BigDecimal smkAngkaNilai;

    private BigDecimal jamLembur;
    private String strJamLembur;
    private BigDecimal biayaLemburLama;
    private String strBiayaLemburLama;
    private BigDecimal biayaLemburBaru;
    private String strBiayaLemburBaru;
    private BigDecimal selisihBiayaLemburBaru;
    private String strselisihBiayaLemburBaru;

    private String tunjanganUmkLama;
    private String tunjanganStrukturalLama;
    private String tunjanganJabatanStrukturalLama;
    private String tunjanganStrategisLama;
    private String tunjanganAirListrikLama;
    private String tunjanganPerumahanLama;
    private String tunjanganPeralihanLama;
    private String gajiGolonganLama;

    private String tunjanganUmkBaru;
    private String tunjanganStrukturalBaru;
    private String tunjanganJabatanStrukturalBaru;
    private String tunjanganStrategisBaru;
    private String tunjanganAirListrikBaru;
    private String tunjanganPeralihanBaru;
    private String tunjanganPerumahanBaru;
    private String gajiGolonganBaru;

    private BigDecimal gajiGolonganLamaNilai;
    private BigDecimal tunjanganUmkLamaNilai;
    private BigDecimal tunjanganStrukturalLamaNilai;
    private BigDecimal tunjanganJabatanStrukturalLamaNilai;
    private BigDecimal tunjanganStrategisLamaNilai;
    private BigDecimal tunjanganAirListrikLamaNilai;
    private BigDecimal tunjanganPeralihanLamaNilai;
    private BigDecimal tunjanganPerumahanLamaNilai;
    private String brutoInsentifLama;

    private BigDecimal gajiGolonganBaruNilai;
    private BigDecimal tunjanganUmkBaruNilai;
    private BigDecimal tunjanganStrukturalBaruNilai;
    private BigDecimal tunjanganJabatanStrukturalBaruNilai;
    private BigDecimal tunjanganStrategisBaruNilai;
    private BigDecimal tunjanganAirListrikBaruNilai;
    private BigDecimal tunjanganPerumahanBaruNilai;
    private BigDecimal tunjanganPeralihanBaruNilai;

    private BigDecimal brutoInsentifLamaNilai;
    private BigDecimal potonganInsentifIndividuLamaNilai;
    private BigDecimal potonganInsentifIndividuNilai;

    private String potonganInsentifIndividuLama;
    private String potonganInsentifIndividu;
    private String golonganIdLama;
    private String golonganNameLama;

    private BigDecimal jumlahPengobatanNilai;
    private String jumlahPengobatan;
    private BigDecimal jumlahPphPengobatanNilai;
    private String jumlahPphPengobatan;
    private BigDecimal hutangPphPengobatanNilai;
    private String hutangPphPengobatan;
    private BigDecimal kurangPphPengobatanNilai;
    private String kurangPphPengobatan;
    private BigDecimal ptkpNilai;
    private String ptkp;

    private BigDecimal rapelGajiGolongan;
    private BigDecimal rapelTunjangan;
    private BigDecimal kastPrs;

    public BigDecimal getKastPrs() {
        return kastPrs;
    }

    public void setKastPrs(BigDecimal kastPrs) {
        this.kastPrs = kastPrs;
    }

    public BigDecimal getRapelGajiGolongan() {
        return rapelGajiGolongan;
    }

    public void setRapelGajiGolongan(BigDecimal rapelGajiGolongan) {
        this.rapelGajiGolongan = rapelGajiGolongan;
    }

    public BigDecimal getRapelTunjangan() {
        return rapelTunjangan;
    }

    public void setRapelTunjangan(BigDecimal rapelTunjangan) {
        this.rapelTunjangan = rapelTunjangan;
    }

    public BigDecimal getPtkpNilai() {
        return ptkpNilai;
    }

    public void setPtkpNilai(BigDecimal ptkpNilai) {
        this.ptkpNilai = ptkpNilai;
    }

    public String getPtkp() {
        return ptkp;
    }

    public void setPtkp(String ptkp) {
        this.ptkp = ptkp;
    }

    public String getHutangPphPengobatan() {
        return hutangPphPengobatan;
    }

    public void setHutangPphPengobatan(String hutangPphPengobatan) {
        this.hutangPphPengobatan = hutangPphPengobatan;
    }

    public BigDecimal getHutangPphPengobatanNilai() {
        return hutangPphPengobatanNilai;
    }

    public void setHutangPphPengobatanNilai(BigDecimal hutangPphPengobatanNilai) {
        this.hutangPphPengobatanNilai = hutangPphPengobatanNilai;
    }

    public String getJumlahPengobatan() {
        return jumlahPengobatan;
    }

    public void setJumlahPengobatan(String jumlahPengobatan) {
        this.jumlahPengobatan = jumlahPengobatan;
    }

    public BigDecimal getJumlahPengobatanNilai() {
        return jumlahPengobatanNilai;
    }

    public void setJumlahPengobatanNilai(BigDecimal jumlahPengobatanNilai) {
        this.jumlahPengobatanNilai = jumlahPengobatanNilai;
    }

    public String getJumlahPphPengobatan() {
        return jumlahPphPengobatan;
    }

    public void setJumlahPphPengobatan(String jumlahPphPengobatan) {
        this.jumlahPphPengobatan = jumlahPphPengobatan;
    }

    public BigDecimal getJumlahPphPengobatanNilai() {
        return jumlahPphPengobatanNilai;
    }

    public void setJumlahPphPengobatanNilai(BigDecimal jumlahPphPengobatanNilai) {
        this.jumlahPphPengobatanNilai = jumlahPphPengobatanNilai;
    }

    public String getKurangPphPengobatan() {
        return kurangPphPengobatan;
    }

    public void setKurangPphPengobatan(String kurangPphPengobatan) {
        this.kurangPphPengobatan = kurangPphPengobatan;
    }

    public BigDecimal getKurangPphPengobatanNilai() {
        return kurangPphPengobatanNilai;
    }

    public void setKurangPphPengobatanNilai(BigDecimal kurangPphPengobatanNilai) {
        this.kurangPphPengobatanNilai = kurangPphPengobatanNilai;
    }

    public String getFlagRapelJubileumJabStruktural() {
        return flagRapelJubileumJabStruktural;
    }

    public void setFlagRapelJubileumJabStruktural(String flagRapelJubileumJabStruktural) {
        this.flagRapelJubileumJabStruktural = flagRapelJubileumJabStruktural;
    }

    public String getFlagRapelPeralihan() {
        return flagRapelPeralihan;
    }

    public void setFlagRapelPeralihan(String flagRapelPeralihan) {
        this.flagRapelPeralihan = flagRapelPeralihan;
    }

    public String getFlagRapelThrGadas() {
        return flagRapelThrGadas;
    }

    public void setFlagRapelThrGadas(String flagRapelThrGadas) {
        this.flagRapelThrGadas = flagRapelThrGadas;
    }

    public String getFlagRapelThrStruktural() {
        return flagRapelThrStruktural;
    }

    public void setFlagRapelThrStruktural(String flagRapelThrStruktural) {
        this.flagRapelThrStruktural = flagRapelThrStruktural;
    }

    public String getFlagRapelThrUmk() {
        return flagRapelThrUmk;
    }

    public void setFlagRapelThrUmk(String flagRapelThrUmk) {
        this.flagRapelThrUmk = flagRapelThrUmk;
    }

    public String getFlagRapelThrJabStruktural() {
        return flagRapelThrJabStruktural;
    }

    public void setFlagRapelThrJabStruktural(String flagRapelThrJabStruktural) {
        this.flagRapelThrJabStruktural = flagRapelThrJabStruktural;
    }

    public String getFlagRapelThrStrategis() {
        return flagRapelThrStrategis;
    }

    public void setFlagRapelThrStrategis(String flagRapelThrStrategis) {
        this.flagRapelThrStrategis = flagRapelThrStrategis;
    }

    public String getFlagRapelThrPeralihan() {
        return flagRapelThrPeralihan;
    }

    public void setFlagRapelThrPeralihan(String flagRapelThrPeralihan) {
        this.flagRapelThrPeralihan = flagRapelThrPeralihan;
    }

    public String getFlagRapelPendidikanGadas() {
        return flagRapelPendidikanGadas;
    }

    public void setFlagRapelPendidikanGadas(String flagRapelPendidikanGadas) {
        this.flagRapelPendidikanGadas = flagRapelPendidikanGadas;
    }

    public String getFlagRapelPendidikanStruktural() {
        return flagRapelPendidikanStruktural;
    }

    public void setFlagRapelPendidikanStruktural(String flagRapelPendidikanStruktural) {
        this.flagRapelPendidikanStruktural = flagRapelPendidikanStruktural;
    }

    public String getFlagRapelPendidikanUmk() {
        return flagRapelPendidikanUmk;
    }

    public void setFlagRapelPendidikanUmk(String flagRapelPendidikanUmk) {
        this.flagRapelPendidikanUmk = flagRapelPendidikanUmk;
    }

    public String getFlagRapelPendidikanJabStruktural() {
        return flagRapelPendidikanJabStruktural;
    }

    public void setFlagRapelPendidikanJabStruktural(String flagRapelPendidikanJabStruktural) {
        this.flagRapelPendidikanJabStruktural = flagRapelPendidikanJabStruktural;
    }

    public String getFlagRapelPendidikanStrategis() {
        return flagRapelPendidikanStrategis;
    }

    public void setFlagRapelPendidikanStrategis(String flagRapelPendidikanStrategis) {
        this.flagRapelPendidikanStrategis = flagRapelPendidikanStrategis;
    }

    public String getFlagRapelPendidikanPeralihan() {
        return flagRapelPendidikanPeralihan;
    }

    public void setFlagRapelPendidikanPeralihan(String flagRapelPendidikanPeralihan) {
        this.flagRapelPendidikanPeralihan = flagRapelPendidikanPeralihan;
    }

    public String getFlagRapelPendidikanAirListrik() {
        return flagRapelPendidikanAirListrik;
    }

    public void setFlagRapelPendidikanAirListrik(String flagRapelPendidikanAirListrik) {
        this.flagRapelPendidikanAirListrik = flagRapelPendidikanAirListrik;
    }

    public String getFlagRapelInsentifGadas() {
        return flagRapelInsentifGadas;
    }

    public void setFlagRapelInsentifGadas(String flagRapelInsentifGadas) {
        this.flagRapelInsentifGadas = flagRapelInsentifGadas;
    }

    public String getFlagRapelInsentifStruktural() {
        return flagRapelInsentifStruktural;
    }

    public void setFlagRapelInsentifStruktural(String flagRapelInsentifStruktural) {
        this.flagRapelInsentifStruktural = flagRapelInsentifStruktural;
    }

    public String getFlagRapelInsentifUmk() {
        return flagRapelInsentifUmk;
    }

    public void setFlagRapelInsentifUmk(String flagRapelInsentifUmk) {
        this.flagRapelInsentifUmk = flagRapelInsentifUmk;
    }

    public String getFlagRapelInsentifJabStruktural() {
        return flagRapelInsentifJabStruktural;
    }

    public void setFlagRapelInsentifJabStruktural(String flagRapelInsentifJabStruktural) {
        this.flagRapelInsentifJabStruktural = flagRapelInsentifJabStruktural;
    }

    public String getFlagRapelInsentifStrategis() {
        return flagRapelInsentifStrategis;
    }

    public void setFlagRapelInsentifStrategis(String flagRapelInsentifStrategis) {
        this.flagRapelInsentifStrategis = flagRapelInsentifStrategis;
    }

    public String getFlagRapelInsentifPeralihan() {
        return flagRapelInsentifPeralihan;
    }

    public void setFlagRapelInsentifPeralihan(String flagRapelInsentifPeralihan) {
        this.flagRapelInsentifPeralihan = flagRapelInsentifPeralihan;
    }

    public String getFlagRapelJubileumGadas() {
        return flagRapelJubileumGadas;
    }

    public void setFlagRapelJubileumGadas(String flagRapelJubileumGadas) {
        this.flagRapelJubileumGadas = flagRapelJubileumGadas;
    }

    public String getFlagRapelJubileumStruktural() {
        return flagRapelJubileumStruktural;
    }

    public void setFlagRapelJubileumStruktural(String flagRapelJubileumStruktural) {
        this.flagRapelJubileumStruktural = flagRapelJubileumStruktural;
    }

    public String getFlagRapelJubileumUmk() {
        return flagRapelJubileumUmk;
    }

    public void setFlagRapelJubileumUmk(String flagRapelJubileumUmk) {
        this.flagRapelJubileumUmk = flagRapelJubileumUmk;
    }

    public String getFlagRapelJubileumStrategis() {
        return flagRapelJubileumStrategis;
    }

    public void setFlagRapelJubileumStrategis(String flagRapelJubileumStrategis) {
        this.flagRapelJubileumStrategis = flagRapelJubileumStrategis;
    }

    public String getFlagRapelJubileumPeralihan() {
        return flagRapelJubileumPeralihan;
    }

    public void setFlagRapelJubileumPeralihan(String flagRapelJubileumPeralihan) {
        this.flagRapelJubileumPeralihan = flagRapelJubileumPeralihan;
    }

    public String getFlagRapelJubileum() {
        return flagRapelJubileum;
    }

    public void setFlagRapelJubileum(String flagRapelJubileum) {
        this.flagRapelJubileum = flagRapelJubileum;
    }

    public String getFlagRapelAirListrikTanggal1() {
        return flagRapelAirListrikTanggal1;
    }

    public void setFlagRapelAirListrikTanggal1(String flagRapelAirListrikTanggal1) {
        this.flagRapelAirListrikTanggal1 = flagRapelAirListrikTanggal1;
    }

    public String getFlagRapelThr() {
        return flagRapelThr;
    }

    public void setFlagRapelThr(String flagRapelThr) {
        this.flagRapelThr = flagRapelThr;
    }

    public String getFlagRapelPendidikan() {
        return flagRapelPendidikan;
    }

    public void setFlagRapelPendidikan(String flagRapelPendidikan) {
        this.flagRapelPendidikan = flagRapelPendidikan;
    }

    public String getFlagRapelInsentif() {
        return flagRapelInsentif;
    }

    public void setFlagRapelInsentif(String flagRapelInsentif) {
        this.flagRapelInsentif = flagRapelInsentif;
    }

    public String getFlagRapelJasprod() {
        return flagRapelJasprod;
    }

    public void setFlagRapelJasprod(String flagRapelJasprod) {
        this.flagRapelJasprod = flagRapelJasprod;
    }

    public String getFlagRapelLembur() {
        return flagRapelLembur;
    }

    public void setFlagRapelLembur(String flagRapelLembur) {
        this.flagRapelLembur = flagRapelLembur;
    }

    public String getFlagRapelGadasTanggal1() {
        return flagRapelGadasTanggal1;
    }

    public void setFlagRapelGadasTanggal1(String flagRapelGadasTanggal1) {
        this.flagRapelGadasTanggal1 = flagRapelGadasTanggal1;
    }

    public String getFlagRapelGadasTanggal2() {
        return flagRapelGadasTanggal2;
    }

    public void setFlagRapelGadasTanggal2(String flagRapelGadasTanggal2) {
        this.flagRapelGadasTanggal2 = flagRapelGadasTanggal2;
    }

    public String getFlagRapelStrukturalTanggal1() {
        return flagRapelStrukturalTanggal1;
    }

    public void setFlagRapelStrukturalTanggal1(String flagRapelStrukturalTanggal1) {
        this.flagRapelStrukturalTanggal1 = flagRapelStrukturalTanggal1;
    }

    public String getFlagRapelStrukturalTanggal2() {
        return flagRapelStrukturalTanggal2;
    }

    public void setFlagRapelStrukturalTanggal2(String flagRapelStrukturalTanggal2) {
        this.flagRapelStrukturalTanggal2 = flagRapelStrukturalTanggal2;
    }

    public String getFlagRapelUmkTanggal1() {
        return flagRapelUmkTanggal1;
    }

    public void setFlagRapelUmkTanggal1(String flagRapelUmkTanggal1) {
        this.flagRapelUmkTanggal1 = flagRapelUmkTanggal1;
    }

    public String getFlagRapelUmkTanggal2() {
        return flagRapelUmkTanggal2;
    }

    public void setFlagRapelUmkTanggal2(String flagRapelUmkTanggal2) {
        this.flagRapelUmkTanggal2 = flagRapelUmkTanggal2;
    }

    public String getFlagRapelJabatanStrukturalTanggal1() {
        return flagRapelJabatanStrukturalTanggal1;
    }

    public void setFlagRapelJabatanStrukturalTanggal1(String flagRapelJabatanStrukturalTanggal1) {
        this.flagRapelJabatanStrukturalTanggal1 = flagRapelJabatanStrukturalTanggal1;
    }

    public String getFlagRapelJabatanStrukturalTanggal2() {
        return flagRapelJabatanStrukturalTanggal2;
    }

    public void setFlagRapelJabatanStrukturalTanggal2(String flagRapelJabatanStrukturalTanggal2) {
        this.flagRapelJabatanStrukturalTanggal2 = flagRapelJabatanStrukturalTanggal2;
    }

    public String getFlagRapelStrategisTanggal1() {
        return flagRapelStrategisTanggal1;
    }

    public void setFlagRapelStrategisTanggal1(String flagRapelStrategisTanggal1) {
        this.flagRapelStrategisTanggal1 = flagRapelStrategisTanggal1;
    }

    public String getFlagRapelStrategisTanggal2() {
        return flagRapelStrategisTanggal2;
    }

    public void setFlagRapelStrategisTanggal2(String flagRapelStrategisTanggal2) {
        this.flagRapelStrategisTanggal2 = flagRapelStrategisTanggal2;
    }

    public String getFlagRapelAirListrikTanggal2() {
        return flagRapelAirListrikTanggal2;
    }

    public void setFlagRapelAirListrikTanggal2(String flagRapelAirListrikTanggal2) {
        this.flagRapelAirListrikTanggal2 = flagRapelAirListrikTanggal2;
    }

    public String getFlagRapelPerumahanTanggal1() {
        return flagRapelPerumahanTanggal1;
    }

    public void setFlagRapelPerumahanTanggal1(String flagRapelPerumahanTanggal1) {
        this.flagRapelPerumahanTanggal1 = flagRapelPerumahanTanggal1;
    }

    public String getFlagRapelPerumahanTanggal2() {
        return flagRapelPerumahanTanggal2;
    }

    public void setFlagRapelPerumahanTanggal2(String flagRapelPerumahanTanggal2) {
        this.flagRapelPerumahanTanggal2 = flagRapelPerumahanTanggal2;
    }

    public String getFlagRapelLemburTanggal1() {
        return flagRapelLemburTanggal1;
    }

    public void setFlagRapelLemburTanggal1(String flagRapelLemburTanggal1) {
        this.flagRapelLemburTanggal1 = flagRapelLemburTanggal1;
    }

    public String getFlagRapelLemburTanggal2() {
        return flagRapelLemburTanggal2;
    }

    public void setFlagRapelLemburTanggal2(String flagRapelLemburTanggal2) {
        this.flagRapelLemburTanggal2 = flagRapelLemburTanggal2;
    }

    public String getFlagRapelThrTanggal1() {
        return flagRapelThrTanggal1;
    }

    public void setFlagRapelThrTanggal1(String flagRapelThrTanggal1) {
        this.flagRapelThrTanggal1 = flagRapelThrTanggal1;
    }

    public String getFlagRapelThrTanggal2() {
        return flagRapelThrTanggal2;
    }

    public void setFlagRapelThrTanggal2(String flagRapelThrTanggal2) {
        this.flagRapelThrTanggal2 = flagRapelThrTanggal2;
    }

    public String getFlagRapelPendidikanTanggal1() {
        return flagRapelPendidikanTanggal1;
    }

    public void setFlagRapelPendidikanTanggal1(String flagRapelPendidikanTanggal1) {
        this.flagRapelPendidikanTanggal1 = flagRapelPendidikanTanggal1;
    }

    public String getFlagRapelPendidikanTanggal2() {
        return flagRapelPendidikanTanggal2;
    }

    public void setFlagRapelPendidikanTanggal2(String flagRapelPendidikanTanggal2) {
        this.flagRapelPendidikanTanggal2 = flagRapelPendidikanTanggal2;
    }

    public String getFlagRapelInsentifTanggal1() {
        return flagRapelInsentifTanggal1;
    }

    public void setFlagRapelInsentifTanggal1(String flagRapelInsentifTanggal1) {
        this.flagRapelInsentifTanggal1 = flagRapelInsentifTanggal1;
    }

    public String getFlagRapelInsentifTanggal2() {
        return flagRapelInsentifTanggal2;
    }

    public void setFlagRapelInsentifTanggal2(String flagRapelInsentifTanggal2) {
        this.flagRapelInsentifTanggal2 = flagRapelInsentifTanggal2;
    }

    public String getFlagRapelJubileumTanggal1() {
        return flagRapelJubileumTanggal1;
    }

    public void setFlagRapelJubileumTanggal1(String flagRapelJubileumTanggal1) {
        this.flagRapelJubileumTanggal1 = flagRapelJubileumTanggal1;
    }

    public String getFlagRapelJubileumTanggal2() {
        return flagRapelJubileumTanggal2;
    }

    public void setFlagRapelJubileumTanggal2(String flagRapelJubileumTanggal2) {
        this.flagRapelJubileumTanggal2 = flagRapelJubileumTanggal2;
    }

    public String getTunjanganPeralihanBaru() {
        return tunjanganPeralihanBaru;
    }

    public void setTunjanganPeralihanBaru(String tunjanganPeralihanBaru) {
        this.tunjanganPeralihanBaru = tunjanganPeralihanBaru;
    }

    public BigDecimal getTunjanganPeralihanBaruNilai() {
        return tunjanganPeralihanBaruNilai;
    }

    public void setTunjanganPeralihanBaruNilai(BigDecimal tunjanganPeralihanBaruNilai) {
        this.tunjanganPeralihanBaruNilai = tunjanganPeralihanBaruNilai;
    }

    public BigDecimal getSmkAngkaNilai() {
        return smkAngkaNilai;
    }

    public void setSmkAngkaNilai(BigDecimal smkAngkaNilai) {
        this.smkAngkaNilai = smkAngkaNilai;
    }

    public BigDecimal getSmkStandartNilai() {
        return smkStandartNilai;
    }

    public void setSmkStandartNilai(BigDecimal smkStandartNilai) {
        this.smkStandartNilai = smkStandartNilai;
    }

    public BigDecimal getPotonganInsentifNilai() {
        return potonganInsentifNilai;
    }

    public void setPotonganInsentifNilai(BigDecimal potonganInsentifNilai) {
        this.potonganInsentifNilai = potonganInsentifNilai;
    }

    public String getPotonganInsentifIndividuLama() {
        return potonganInsentifIndividuLama;
    }

    public void setPotonganInsentifIndividuLama(String potonganInsentifIndividuLama) {
        this.potonganInsentifIndividuLama = potonganInsentifIndividuLama;
    }

    public BigDecimal getPotonganInsentifIndividuLamaNilai() {
        return potonganInsentifIndividuLamaNilai;
    }

    public void setPotonganInsentifIndividuLamaNilai(BigDecimal potonganInsentifIndividuLamaNilai) {
        this.potonganInsentifIndividuLamaNilai = potonganInsentifIndividuLamaNilai;
    }

    public String getBrutoInsentifLama() {
        return brutoInsentifLama;
    }

    public void setBrutoInsentifLama(String brutoInsentifLama) {
        this.brutoInsentifLama = brutoInsentifLama;
    }

    public BigDecimal getBrutoInsentifLamaNilai() {
        return brutoInsentifLamaNilai;
    }

    public void setBrutoInsentifLamaNilai(BigDecimal brutoInsentifLamaNilai) {
        this.brutoInsentifLamaNilai = brutoInsentifLamaNilai;
    }

    public String getInsentifDiterimaLama() {
        return insentifDiterimaLama;
    }

    public void setInsentifDiterimaLama(String insentifDiterimaLama) {
        this.insentifDiterimaLama = insentifDiterimaLama;
    }

    public BigDecimal getInsentifDiterimaLamaNilai() {
        return insentifDiterimaLamaNilai;
    }

    public void setInsentifDiterimaLamaNilai(BigDecimal insentifDiterimaLamaNilai) {
        this.insentifDiterimaLamaNilai = insentifDiterimaLamaNilai;
    }

    public String getTunjanganPeralihanLama() {
        return tunjanganPeralihanLama;
    }

    public void setTunjanganPeralihanLama(String tunjanganPeralihanLama) {
        this.tunjanganPeralihanLama = tunjanganPeralihanLama;
    }

    public BigDecimal getTunjanganPeralihanLamaNilai() {
        return tunjanganPeralihanLamaNilai;
    }

    public void setTunjanganPeralihanLamaNilai(BigDecimal tunjanganPeralihanLamaNilai) {
        this.tunjanganPeralihanLamaNilai = tunjanganPeralihanLamaNilai;
    }

    public String getTotalLembur() {
        return totalLembur;
    }

    public void setTotalLembur(String totalLembur) {
        this.totalLembur = totalLembur;
    }

    public BigDecimal getTotalLemburNilai() {
        return totalLemburNilai;
    }

    public void setTotalLemburNilai(BigDecimal totalLemburNilai) {
        this.totalLemburNilai = totalLemburNilai;
    }

    public String getGolonganIdLama() {
        return golonganIdLama;
    }

    public void setGolonganIdLama(String golonganIdLama) {
        this.golonganIdLama = golonganIdLama;
    }

    public String getGolonganNameLama() {
        return golonganNameLama;
    }

    public void setGolonganNameLama(String golonganNameLama) {
        this.golonganNameLama = golonganNameLama;
    }

    public String getTunjanganUmkLama() {
        return tunjanganUmkLama;
    }

    public void setTunjanganUmkLama(String tunjanganUmkLama) {
        this.tunjanganUmkLama = tunjanganUmkLama;
    }

    public String getTunjanganStrukturalLama() {
        return tunjanganStrukturalLama;
    }

    public void setTunjanganStrukturalLama(String tunjanganStrukturalLama) {
        this.tunjanganStrukturalLama = tunjanganStrukturalLama;
    }

    public String getTunjanganJabatanStrukturalLama() {
        return tunjanganJabatanStrukturalLama;
    }

    public void setTunjanganJabatanStrukturalLama(String tunjanganJabatanStrukturalLama) {
        this.tunjanganJabatanStrukturalLama = tunjanganJabatanStrukturalLama;
    }

    public String getTunjanganStrategisLama() {
        return tunjanganStrategisLama;
    }

    public void setTunjanganStrategisLama(String tunjanganStrategisLama) {
        this.tunjanganStrategisLama = tunjanganStrategisLama;
    }

    public String getTunjanganAirListrikLama() {
        return tunjanganAirListrikLama;
    }

    public void setTunjanganAirListrikLama(String tunjanganAirListrikLama) {
        this.tunjanganAirListrikLama = tunjanganAirListrikLama;
    }

    public String getTunjanganPerumahanLama() {
        return tunjanganPerumahanLama;
    }

    public void setTunjanganPerumahanLama(String tunjanganPerumahanLama) {
        this.tunjanganPerumahanLama = tunjanganPerumahanLama;
    }

    public String getGajiGolonganLama() {
        return gajiGolonganLama;
    }

    public void setGajiGolonganLama(String gajiGolonganLama) {
        this.gajiGolonganLama = gajiGolonganLama;
    }

    public String getTunjanganUmkBaru() {
        return tunjanganUmkBaru;
    }

    public void setTunjanganUmkBaru(String tunjanganUmkBaru) {
        this.tunjanganUmkBaru = tunjanganUmkBaru;
    }

    public String getTunjanganStrukturalBaru() {
        return tunjanganStrukturalBaru;
    }

    public void setTunjanganStrukturalBaru(String tunjanganStrukturalBaru) {
        this.tunjanganStrukturalBaru = tunjanganStrukturalBaru;
    }

    public String getTunjanganJabatanStrukturalBaru() {
        return tunjanganJabatanStrukturalBaru;
    }

    public void setTunjanganJabatanStrukturalBaru(String tunjanganJabatanStrukturalBaru) {
        this.tunjanganJabatanStrukturalBaru = tunjanganJabatanStrukturalBaru;
    }

    public String getTunjanganStrategisBaru() {
        return tunjanganStrategisBaru;
    }

    public void setTunjanganStrategisBaru(String tunjanganStrategisBaru) {
        this.tunjanganStrategisBaru = tunjanganStrategisBaru;
    }

    public String getTunjanganAirListrikBaru() {
        return tunjanganAirListrikBaru;
    }

    public void setTunjanganAirListrikBaru(String tunjanganAirListrikBaru) {
        this.tunjanganAirListrikBaru = tunjanganAirListrikBaru;
    }

    public String getTunjanganPerumahanBaru() {
        return tunjanganPerumahanBaru;
    }

    public void setTunjanganPerumahanBaru(String tunjanganPerumahanBaru) {
        this.tunjanganPerumahanBaru = tunjanganPerumahanBaru;
    }

    public String getGajiGolonganBaru() {
        return gajiGolonganBaru;
    }

    public void setGajiGolonganBaru(String gajiGolonganBaru) {
        this.gajiGolonganBaru = gajiGolonganBaru;
    }

    public BigDecimal getGajiGolonganLamaNilai() {
        return gajiGolonganLamaNilai;
    }

    public void setGajiGolonganLamaNilai(BigDecimal gajiGolonganLamaNilai) {
        this.gajiGolonganLamaNilai = gajiGolonganLamaNilai;
    }

    public BigDecimal getTunjanganUmkLamaNilai() {
        return tunjanganUmkLamaNilai;
    }

    public void setTunjanganUmkLamaNilai(BigDecimal tunjanganUmkLamaNilai) {
        this.tunjanganUmkLamaNilai = tunjanganUmkLamaNilai;
    }

    public BigDecimal getTunjanganStrukturalLamaNilai() {
        return tunjanganStrukturalLamaNilai;
    }

    public void setTunjanganStrukturalLamaNilai(BigDecimal tunjanganStrukturalLamaNilai) {
        this.tunjanganStrukturalLamaNilai = tunjanganStrukturalLamaNilai;
    }

    public BigDecimal getTunjanganJabatanStrukturalLamaNilai() {
        return tunjanganJabatanStrukturalLamaNilai;
    }

    public void setTunjanganJabatanStrukturalLamaNilai(BigDecimal tunjanganJabatanStrukturalLamaNilai) {
        this.tunjanganJabatanStrukturalLamaNilai = tunjanganJabatanStrukturalLamaNilai;
    }

    public BigDecimal getTunjanganStrategisLamaNilai() {
        return tunjanganStrategisLamaNilai;
    }

    public void setTunjanganStrategisLamaNilai(BigDecimal tunjanganStrategisLamaNilai) {
        this.tunjanganStrategisLamaNilai = tunjanganStrategisLamaNilai;
    }

    public BigDecimal getTunjanganAirListrikLamaNilai() {
        return tunjanganAirListrikLamaNilai;
    }

    public void setTunjanganAirListrikLamaNilai(BigDecimal tunjanganAirListrikLamaNilai) {
        this.tunjanganAirListrikLamaNilai = tunjanganAirListrikLamaNilai;
    }

    public BigDecimal getTunjanganPerumahanLamaNilai() {
        return tunjanganPerumahanLamaNilai;
    }

    public void setTunjanganPerumahanLamaNilai(BigDecimal tunjanganPerumahanLamaNilai) {
        this.tunjanganPerumahanLamaNilai = tunjanganPerumahanLamaNilai;
    }

    public BigDecimal getGajiGolonganBaruNilai() {
        return gajiGolonganBaruNilai;
    }

    public void setGajiGolonganBaruNilai(BigDecimal gajiGolonganBaruNilai) {
        this.gajiGolonganBaruNilai = gajiGolonganBaruNilai;
    }

    public BigDecimal getTunjanganUmkBaruNilai() {
        return tunjanganUmkBaruNilai;
    }

    public void setTunjanganUmkBaruNilai(BigDecimal tunjanganUmkBaruNilai) {
        this.tunjanganUmkBaruNilai = tunjanganUmkBaruNilai;
    }

    public BigDecimal getTunjanganStrukturalBaruNilai() {
        return tunjanganStrukturalBaruNilai;
    }

    public void setTunjanganStrukturalBaruNilai(BigDecimal tunjanganStrukturalBaruNilai) {
        this.tunjanganStrukturalBaruNilai = tunjanganStrukturalBaruNilai;
    }

    public BigDecimal getTunjanganJabatanStrukturalBaruNilai() {
        return tunjanganJabatanStrukturalBaruNilai;
    }

    public void setTunjanganJabatanStrukturalBaruNilai(BigDecimal tunjanganJabatanStrukturalBaruNilai) {
        this.tunjanganJabatanStrukturalBaruNilai = tunjanganJabatanStrukturalBaruNilai;
    }

    public BigDecimal getTunjanganStrategisBaruNilai() {
        return tunjanganStrategisBaruNilai;
    }

    public void setTunjanganStrategisBaruNilai(BigDecimal tunjanganStrategisBaruNilai) {
        this.tunjanganStrategisBaruNilai = tunjanganStrategisBaruNilai;
    }

    public BigDecimal getTunjanganAirListrikBaruNilai() {
        return tunjanganAirListrikBaruNilai;
    }

    public void setTunjanganAirListrikBaruNilai(BigDecimal tunjanganAirListrikBaruNilai) {
        this.tunjanganAirListrikBaruNilai = tunjanganAirListrikBaruNilai;
    }

    public BigDecimal getTunjanganPerumahanBaruNilai() {
        return tunjanganPerumahanBaruNilai;
    }

    public void setTunjanganPerumahanBaruNilai(BigDecimal tunjanganPerumahanBaruNilai) {
        this.tunjanganPerumahanBaruNilai = tunjanganPerumahanBaruNilai;
    }

    public BigDecimal getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(BigDecimal jamLembur) {
        this.jamLembur = jamLembur;
    }

    public String getStrJamLembur() {
        return strJamLembur;
    }

    public void setStrJamLembur(String strJamLembur) {
        this.strJamLembur = strJamLembur;
    }

    public BigDecimal getBiayaLemburLama() {
        return biayaLemburLama;
    }

    public void setBiayaLemburLama(BigDecimal biayaLemburLama) {
        this.biayaLemburLama = biayaLemburLama;
    }

    public String getStrBiayaLemburLama() {
        return strBiayaLemburLama;
    }

    public void setStrBiayaLemburLama(String strBiayaLemburLama) {
        this.strBiayaLemburLama = strBiayaLemburLama;
    }

    public BigDecimal getBiayaLemburBaru() {
        return biayaLemburBaru;
    }

    public void setBiayaLemburBaru(BigDecimal biayaLemburBaru) {
        this.biayaLemburBaru = biayaLemburBaru;
    }

    public String getStrBiayaLemburBaru() {
        return strBiayaLemburBaru;
    }

    public void setStrBiayaLemburBaru(String strBiayaLemburBaru) {
        this.strBiayaLemburBaru = strBiayaLemburBaru;
    }

    public BigDecimal getSelisihBiayaLemburBaru() {
        return selisihBiayaLemburBaru;
    }

    public void setSelisihBiayaLemburBaru(BigDecimal selisihBiayaLemburBaru) {
        this.selisihBiayaLemburBaru = selisihBiayaLemburBaru;
    }

    public String getStrselisihBiayaLemburBaru() {
        return strselisihBiayaLemburBaru;
    }

    public void setStrselisihBiayaLemburBaru(String strselisihBiayaLemburBaru) {
        this.strselisihBiayaLemburBaru = strselisihBiayaLemburBaru;
    }


    public String getFlagRapelGadas() {
        return flagRapelGadas;
    }

    public void setFlagRapelGadas(String flagRapelGadas) {
        this.flagRapelGadas = flagRapelGadas;
    }

    public String getFlagRapelStruktural() {
        return flagRapelStruktural;
    }

    public void setFlagRapelStruktural(String flagRapelStruktural) {
        this.flagRapelStruktural = flagRapelStruktural;
    }

    public String getFlagRapelUmk() {
        return flagRapelUmk;
    }

    public void setFlagRapelUmk(String flagRapelUmk) {
        this.flagRapelUmk = flagRapelUmk;
    }

    public String getFlagRapelJabatanStruktural() {
        return flagRapelJabatanStruktural;
    }

    public void setFlagRapelJabatanStruktural(String flagRapelJabatanStruktural) {
        this.flagRapelJabatanStruktural = flagRapelJabatanStruktural;
    }

    public String getFlagRapelStrategis() {
        return flagRapelStrategis;
    }

    public void setFlagRapelStrategis(String flagRapelStrategis) {
        this.flagRapelStrategis = flagRapelStrategis;
    }

    public String getFlagRapelAirListrik() {
        return flagRapelAirListrik;
    }

    public void setFlagRapelAirListrik(String flagRapelAirListrik) {
        this.flagRapelAirListrik = flagRapelAirListrik;
    }

    public String getFlagRapelPerumahan() {
        return flagRapelPerumahan;
    }

    public void setFlagRapelPerumahan(String flagRapelPerumahan) {
        this.flagRapelPerumahan = flagRapelPerumahan;
    }

    public BigDecimal getPotPphLainNilai() {
        return potPphLainNilai;
    }

    public void setPotPphLainNilai(BigDecimal potPphLainNilai) {
        this.potPphLainNilai = potPphLainNilai;
    }

    public String getPotPphLain() {
        return potPphLain;
    }

    public void setPotPphLain(String potPphLain) {
        this.potPphLain = potPphLain;
    }

    public String getSmkStandart() {
        return smkStandart;
    }

    public void setSmkStandart(String smkStandart) {
        this.smkStandart = smkStandart;
    }

    public String getSmkAngka() {
        return smkAngka;
    }

    public void setSmkAngka(String smkAngka) {
        this.smkAngka = smkAngka;
    }

    public BigDecimal getInsentifDiterimaNilai() {
        return insentifDiterimaNilai;
    }

    public void setInsentifDiterimaNilai(BigDecimal insentifDiterimaNilai) {
        this.insentifDiterimaNilai = insentifDiterimaNilai;
    }

    public String getInsentifDiterima() {
        return insentifDiterima;
    }

    public void setInsentifDiterima(String insentifDiterima) {
        this.insentifDiterima = insentifDiterima;
    }

    public String getSmkHuruf() {
        return smkHuruf;
    }

    public void setSmkHuruf(String smkHuruf) {
        this.smkHuruf = smkHuruf;
    }

    public String getPotonganInsentifIndividu() {
        return potonganInsentifIndividu;
    }

    public void setPotonganInsentifIndividu(String potonganInsentifIndividu) {
        this.potonganInsentifIndividu = potonganInsentifIndividu;
    }

    public BigDecimal getPotonganInsentifIndividuNilai() {
        return potonganInsentifIndividuNilai;
    }

    public void setPotonganInsentifIndividuNilai(BigDecimal potonganInsentifIndividuNilai) {
        this.potonganInsentifIndividuNilai = potonganInsentifIndividuNilai;
    }

    public String getPotonganInsentif() {

        return potonganInsentif;
    }

    public void setPotonganInsentif(String potonganInsentif) {
        this.potonganInsentif = potonganInsentif;
    }

    public String getTotalInsentif() {
        return totalInsentif;
    }

    public void setTotalInsentif(String totalInsentif) {
        this.totalInsentif = totalInsentif;
    }

    public BigDecimal getTotalInsentifNilai() {
        return totalInsentifNilai;
    }

    public void setTotalInsentifNilai(BigDecimal totalInsentifNilai) {
        this.totalInsentifNilai = totalInsentifNilai;
    }

    public String getFlagInsentif() {
        return flagInsentif;
    }

    public void setFlagInsentif(String flagInsentif) {
        this.flagInsentif = flagInsentif;
    }

    public String getPotPph() {
        return potPph;
    }

    public void setPotPph(String potPph) {
        this.potPph = potPph;
    }

    public BigDecimal getPotPphNilai() {
        return potPphNilai;
    }

    public void setPotPphNilai(BigDecimal potPphNilai) {
        this.potPphNilai = potPphNilai;
    }

    public BigDecimal getPphJasprodNilai() {
        return pphJasprodNilai;
    }

    public void setPphJasprodNilai(BigDecimal pphJasprodNilai) {
        this.pphJasprodNilai = pphJasprodNilai;
    }

    public BigDecimal getNettoJasprodNilai() {
        return nettoJasprodNilai;
    }

    public void setNettoJasprodNilai(BigDecimal nettoJasprodNilai) {
        this.nettoJasprodNilai = nettoJasprodNilai;
    }

    public BigDecimal getGajiXfaktorNormalNilai() {
        return gajiXfaktorNormalNilai;
    }

    public void setGajiXfaktorNormalNilai(BigDecimal gajiXfaktorNormalNilai) {
        this.gajiXfaktorNormalNilai = gajiXfaktorNormalNilai;
    }

    public BigDecimal getPerhitunganNilai() {
        return perhitunganNilai;
    }

    public void setPerhitunganNilai(BigDecimal perhitunganNilai) {
        this.perhitunganNilai = perhitunganNilai;
    }

    public String getPphJasprod() {
        return pphJasprod;
    }

    public void setPphJasprod(String pphJasprod) {
        this.pphJasprod = pphJasprod;
    }

    public String getNettoJasprod() {
        return nettoJasprod;
    }

    public void setNettoJasprod(String nettoJasprod) {
        this.nettoJasprod = nettoJasprod;
    }

    public String getPerhitungan() {
        return perhitungan;
    }

    public void setPerhitungan(String perhitungan) {
        this.perhitungan = perhitungan;
    }

    public String getGajiXfaktorNormal() {
        return gajiXfaktorNormal;
    }

    public void setGajiXfaktorNormal(String gajiXfaktorNormal) {
        this.gajiXfaktorNormal = gajiXfaktorNormal;
    }

    public String getStrNilaiSmk() {
        return strNilaiSmk;
    }

    public void setStrNilaiSmk(String strNilaiSmk) {
        this.strNilaiSmk = strNilaiSmk;
    }

    public String getStrPersenSmk() {
        return strPersenSmk;
    }

    public void setStrPersenSmk(String strPersenSmk) {
        this.strPersenSmk = strPersenSmk;
    }

    public String getFaktorKaliSmk() {
        return faktorKaliSmk;
    }

    public void setFaktorKaliSmk(String faktorKaliSmk) {
        this.faktorKaliSmk = faktorKaliSmk;
    }

    public String getNilaiSmk() {
        return nilaiSmk;
    }

    public void setNilaiSmk(String nilaiSmk) {
        this.nilaiSmk = nilaiSmk;
    }

    public BigDecimal getNilaiSmkNilai() {
        return nilaiSmkNilai;
    }

    public void setNilaiSmkNilai(BigDecimal nilaiSmkNilai) {
        this.nilaiSmkNilai = nilaiSmkNilai;
    }

    public String getGajiMasaKerja() {
        return gajiMasaKerja;
    }

    public void setGajiMasaKerja(String gajiMasaKerja) {
        this.gajiMasaKerja = gajiMasaKerja;
    }

    public BigDecimal getGajiMasaKerjaNilai() {
        return gajiMasaKerjaNilai;
    }

    public void setGajiMasaKerjaNilai(BigDecimal gajiMasaKerjaNilai) {
        this.gajiMasaKerjaNilai = gajiMasaKerjaNilai;
    }

    public BigDecimal getFaktorKaliNilai() {
        return faktorKaliNilai;
    }

    public void setFaktorKaliNilai(BigDecimal faktorKaliNilai) {
        this.faktorKaliNilai = faktorKaliNilai;
    }

    public String getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(String faktorKali) {
        this.faktorKali = faktorKali;
    }

    public BigDecimal getPersenSmkNilai() {
        return persenSmkNilai;
    }

    public void setPersenSmkNilai(BigDecimal persenSmkNilai) {
        this.persenSmkNilai = persenSmkNilai;
    }

    public String getPersenSmk() {
        return persenSmk;
    }

    public void setPersenSmk(String persenSmk) {
        this.persenSmk = persenSmk;
    }

    public BigDecimal getJumlahPersenSmk() {
        return jumlahPersenSmk;
    }

    public void setJumlahPersenSmk(BigDecimal jumlahPersenSmk) {
        this.jumlahPersenSmk = jumlahPersenSmk;
    }

    public String getGajiMasaKerjaFaktorSmk() {
        return gajiMasaKerjaFaktorSmk;
    }

    public void setGajiMasaKerjaFaktorSmk(String gajiMasaKerjaFaktorSmk) {
        this.gajiMasaKerjaFaktorSmk = gajiMasaKerjaFaktorSmk;
    }

    public String getGajiMasaKerjaFaktor() {
        return gajiMasaKerjaFaktor;
    }

    public void setGajiMasaKerjaFaktor(String gajiMasaKerjaFaktor) {
        this.gajiMasaKerjaFaktor = gajiMasaKerjaFaktor;
    }

    public String getTambahan() {
        return tambahan;
    }

    public void setTambahan(String tambahan) {
        this.tambahan = tambahan;
    }

    public String getBrutoJasprod() {
        return brutoJasprod;
    }

    public void setBrutoJasprod(String brutoJasprod) {
        this.brutoJasprod = brutoJasprod;
    }

    public String getSelisihTotalGajiSmkFaktor() {
        return selisihTotalGajiSmkFaktor;
    }

    public void setSelisihTotalGajiSmkFaktor(String selisihTotalGajiSmkFaktor) {
        this.selisihTotalGajiSmkFaktor = selisihTotalGajiSmkFaktor;
    }

    public BigDecimal getGajiMasaKerjaFaktorSmkNilai() {
        return gajiMasaKerjaFaktorSmkNilai;
    }

    public void setGajiMasaKerjaFaktorSmkNilai(BigDecimal gajiMasaKerjaFaktorSmkNilai) {
        this.gajiMasaKerjaFaktorSmkNilai = gajiMasaKerjaFaktorSmkNilai;
    }

    public BigDecimal getGajiMasaKerjaFaktorNilai() {
        return gajiMasaKerjaFaktorNilai;
    }

    public void setGajiMasaKerjaFaktorNilai(BigDecimal gajiMasaKerjaFaktorNilai) {
        this.gajiMasaKerjaFaktorNilai = gajiMasaKerjaFaktorNilai;
    }

    public BigDecimal getTambahanNilai() {
        return tambahanNilai;
    }

    public void setTambahanNilai(BigDecimal tambahanNilai) {
        this.tambahanNilai = tambahanNilai;
    }

    public BigDecimal getBrutoJasprodNilai() {
        return brutoJasprodNilai;
    }

    public void setBrutoJasprodNilai(BigDecimal brutoJasprodNilai) {
        this.brutoJasprodNilai = brutoJasprodNilai;
    }

    public BigDecimal getSelisihTotalGajiSmkFaktorNilai() {
        return selisihTotalGajiSmkFaktorNilai;
    }

    public void setSelisihTotalGajiSmkFaktorNilai(BigDecimal selisihTotalGajiSmkFaktorNilai) {
        this.selisihTotalGajiSmkFaktorNilai = selisihTotalGajiSmkFaktorNilai;
    }

    public String getTipeThr() {
        return tipeThr;
    }

    public void setTipeThr(String tipeThr) {
        this.tipeThr = tipeThr;
    }

    public String getTotalThrBersih() {
        return totalThrBersih;
    }

    public void setTotalThrBersih(String totalThrBersih) {
        this.totalThrBersih = totalThrBersih;
    }

    public BigDecimal getTotalThrBersihNilai() {
        return totalThrBersihNilai;
    }

    public void setTotalThrBersihNilai(BigDecimal totalThrBersihNilai) {
        this.totalThrBersihNilai = totalThrBersihNilai;
    }

    public BigDecimal getNettoPensiunNilai() {
        return nettoPensiunNilai;
    }

    public void setNettoPensiunNilai(BigDecimal nettoPensiunNilai) {
        this.nettoPensiunNilai = nettoPensiunNilai;
    }

    public String getNettoPensiun() {
        return nettoPensiun;
    }

    public void setNettoPensiun(String nettoPensiun) {
        this.nettoPensiun = nettoPensiun;
    }

    private Date tanggalAktif;
    private String stTanggalPayroll;
    private String stTanggalAktif;
    private String stTanggalAktifSekarang;
    private String stTanggalPensiun;
    private String tipePegawai;
    private String tipePegawaiName;
    private String strukturGaji;
    private BigDecimal biodataGaji;
    private String masaKerja;
    private int masaKerjaTahun;
    private int masaKerjaBulan;
    private String labelJubileum;
    private String labelPensiun;
    private String tipe;
    private String noRek;
    private String namaBank;
    private String cabangBank;
    private String kodePajak;
    private String kodeNegara;
    private int bulanAktif;
    private int bulanInsentifMulai;
    private int bulanInsentifSampai;
    private int insentifTahun;

    public int getInsentifTahun() {
        return insentifTahun;
    }

    public void setInsentifTahun(int insentifTahun) {
        this.insentifTahun = insentifTahun;
    }

    public int getBulanInsentifMulai() {
        return bulanInsentifMulai;
    }

    public void setBulanInsentifMulai(int bulanInsentifMulai) {
        this.bulanInsentifMulai = bulanInsentifMulai;
    }

    public int getBulanInsentifSampai() {
        return bulanInsentifSampai;
    }

    public void setBulanInsentifSampai(int bulanInsentifSampai) {
        this.bulanInsentifSampai = bulanInsentifSampai;
    }

    public String getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(String masaKerja) {
        this.masaKerja = masaKerja;
    }

    public String getNettoJubileum() {
        return nettoJubileum;
    }

    public void setNettoJubileum(String nettoJubileum) {
        this.nettoJubileum = nettoJubileum;
    }

    public BigDecimal getNettoJubileumNilai() {
        return nettoJubileumNilai;
    }

    public void setNettoJubileumNilai(BigDecimal nettoJubileumNilai) {
        this.nettoJubileumNilai = nettoJubileumNilai;
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

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getIuranPensiunJumlah() {
        return iuranPensiunJumlah;
    }

    public void setIuranPensiunJumlah(String iuranPensiunJumlah) {
        this.iuranPensiunJumlah = iuranPensiunJumlah;
    }

    public BigDecimal getIuranPensiunJumlahNilai() {
        return iuranPensiunJumlahNilai;
    }

    public void setIuranPensiunJumlahNilai(BigDecimal iuranPensiunJumlahNilai) {
        this.iuranPensiunJumlahNilai = iuranPensiunJumlahNilai;
    }

    public BigDecimal getIuranPensiunPerusahaanNilai() {
        return iuranPensiunPerusahaanNilai;
    }

    public void setIuranPensiunPerusahaanNilai(BigDecimal iuranPensiunPerusahaanNilai) {
        this.iuranPensiunPerusahaanNilai = iuranPensiunPerusahaanNilai;
    }

    public String getIuranPensiunPerusahaan() {
        return iuranPensiunPerusahaan;
    }

    public void setIuranPensiunPerusahaan(String iuranPensiunPerusahaan) {
        this.iuranPensiunPerusahaan = iuranPensiunPerusahaan;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getKodeNegara() {
        return kodeNegara;
    }

    public void setKodeNegara(String kodeNegara) {
        this.kodeNegara = kodeNegara;
    }

    public String getKodePajak() {
        return kodePajak;
    }

    public void setKodePajak(String kodePajak) {
        this.kodePajak = kodePajak;
    }

    public String getPembetulan() {
        return pembetulan;
    }

    public void setPembetulan(String pembetulan) {
        this.pembetulan = pembetulan;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getStTanggalPayroll() {
        return stTanggalPayroll;
    }

    public void setStTanggalPayroll(String stTanggalPayroll) {
        this.stTanggalPayroll = stTanggalPayroll;
    }

    public BigDecimal getPphPensiunNilai() {
        return pphPensiunNilai;
    }

    public void setPphPensiunNilai(BigDecimal pphPensiunNilai) {
        this.pphPensiunNilai = pphPensiunNilai;
    }

    public String getPphPensiun() {
        return pphPensiun;
    }

    public void setPphPensiun(String pphPensiun) {
        this.pphPensiun = pphPensiun;
    }

    public BigDecimal getUmr() {
        return umr;
    }

    public void setUmr(BigDecimal umr) {
        this.umr = umr;
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

    public String getLabelJubileum() {
        return labelJubileum;
    }

    public void setLabelJubileum(String labelJubileum) {
        this.labelJubileum = labelJubileum;
    }

    public String getLabelPensiun() {
        return labelPensiun;
    }

    public void setLabelPensiun(String labelPensiun) {
        this.labelPensiun = labelPensiun;
    }

    public String getFlagPromosi() {
        return flagPromosi;
    }

    public void setFlagPromosi(String flagPromosi) {
        this.flagPromosi = flagPromosi;
    }

    private boolean flagEdit = true;
    private boolean flagSlip = true;

    public boolean isFlagSlip() {
        return flagSlip;
    }

    public void setFlagSlip(boolean flagSlip) {
        this.flagSlip = flagSlip;
    }

    public boolean isFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(boolean flagEdit) {
        this.flagEdit = flagEdit;
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

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getBesarJubileum() {
        return besarJubileum;
    }

    public void setBesarJubileum(String besarJubileum) {
        this.besarJubileum = besarJubileum;
    }

    public BigDecimal getBesarJubileumNilai() {
        return besarJubileumNilai;
    }

    public void setBesarJubileumNilai(BigDecimal besarJubileumNilai) {
        this.besarJubileumNilai = besarJubileumNilai;
    }

    public BigDecimal getTotalPensiunNilai() {
        return totalPensiunNilai;
    }

    public void setTotalPensiunNilai(BigDecimal totalPensiunNilai) {
        this.totalPensiunNilai = totalPensiunNilai;
    }

    public String getTotalPensiun() {
        return totalPensiun;
    }

    public void setTotalPensiun(String totalPensiun) {
        this.totalPensiun = totalPensiun;
    }

    public String getStTanggalPensiun() {
        return stTanggalPensiun;
    }

    public void setStTanggalPensiun(String stTanggalPensiun) {
        this.stTanggalPensiun = stTanggalPensiun;
    }

    public String getFlagPensiun() {
        return flagPensiun;
    }

    public void setFlagPensiun(String flagPensiun) {
        this.flagPensiun = flagPensiun;
    }

    public int getMasaKerjaBulan() {
        return masaKerjaBulan;
    }

    public void setMasaKerjaBulan(int masaKerjaBulan) {
        this.masaKerjaBulan = masaKerjaBulan;
    }

    public int getMasaKerjaTahun() {
        return masaKerjaTahun;
    }

    public void setMasaKerjaTahun(int masaKerjaTahun) {
        this.masaKerjaTahun = masaKerjaTahun;
    }

    public String getStTanggalAktifSekarang() {
        return stTanggalAktifSekarang;
    }

    public void setStTanggalAktifSekarang(String stTanggalAktifSekarang) {
        this.stTanggalAktifSekarang = stTanggalAktifSekarang;
    }

    public String getStTanggalAktif() {
        return stTanggalAktif;
    }

    public void setStTanggalAktif(String stTanggalAktif) {
        this.stTanggalAktif = stTanggalAktif;
    }

    public String getTotalTambahan() {
        return totalTambahan;
    }

    public void setTotalTambahan(String totalTambahan) {
        this.totalTambahan = totalTambahan;
    }

    public BigDecimal getTotalTambahanNilai() {
        return totalTambahanNilai;
    }

    public void setTotalTambahanNilai(BigDecimal totalTambahanNilai) {
        this.totalTambahanNilai = totalTambahanNilai;
    }

    String centangJubileum = "N";
    String centangPensiun = "N";
    String centangListrikAir = "N";
    String centangPerumahan = "N";
    String centangKalkulasiPph = "N";
    String centangKalkulasiPphPengobatan = "N";
    private boolean flagJubileumOn = false ;
    private boolean flagPensiunOn = false ;
    private boolean flagPromosiOn = false ;
    private boolean flagPerumahanOn = false ;
    private boolean flagListrikAirOn = false ;
    private boolean flagKonsistensi = false ;

    public String getCentangKalkulasiPphPengobatan() {
        return centangKalkulasiPphPengobatan;
    }

    public void setCentangKalkulasiPphPengobatan(String centangKalkulasiPphPengobatan) {
        this.centangKalkulasiPphPengobatan = centangKalkulasiPphPengobatan;
    }

    public String getCentangKalkulasiPph() {
        return centangKalkulasiPph;
    }

    public void setCentangKalkulasiPph(String centangKalkulasiPph) {
        this.centangKalkulasiPph = centangKalkulasiPph;
    }

    public boolean isFlagKonsistensi() {
        return flagKonsistensi;
    }

    public void setFlagKonsistensi(boolean flagKonsistensi) {
        this.flagKonsistensi = flagKonsistensi;
    }

    public boolean isFlagListrikAirOn() {
        return flagListrikAirOn;
    }

    public void setFlagListrikAirOn(boolean flagListrikAirOn) {
        this.flagListrikAirOn = flagListrikAirOn;
    }

    public boolean isFlagPerumahanOn() {
        return flagPerumahanOn;
    }

    public void setFlagPerumahanOn(boolean flagPerumahanOn) {
        this.flagPerumahanOn = flagPerumahanOn;
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

    public boolean isFlagPromosiOn() {
        return flagPromosiOn;
    }

    public void setFlagPromosiOn(boolean flagPromosiOn) {
        this.flagPromosiOn = flagPromosiOn;
    }

    public boolean isFlagPensiunOn() {
        return flagPensiunOn;
    }

    public String getCentangPensiun() {
        return centangPensiun;
    }

    public void setCentangPensiun(String centangPensiun) {
        this.centangPensiun = centangPensiun;
    }

    public void setFlagPensiunOn(boolean flagPensiunOn) {
        this.flagPensiunOn = flagPensiunOn;
    }

    public String getCentangJubileum() {
        return centangJubileum;
    }

    public void setCentangJubileum(String centangJubileum) {
        this.centangJubileum = centangJubileum;
    }

    public String getTotalKaliJubileum() {
        return totalKaliJubileum;
    }

    public void setTotalKaliJubileum(String totalKaliJubileum) {
        this.totalKaliJubileum = totalKaliJubileum;
    }

    public BigDecimal getTotalKaliJubileumNilai() {
        return totalKaliJubileumNilai;
    }

    public void setTotalKaliJubileumNilai(BigDecimal totalKaliJubileumNilai) {
        this.totalKaliJubileumNilai = totalKaliJubileumNilai;
    }

    public String getTanggalJubileum() {
        return tanggalJubileum;
    }

    public void setTanggalJubileum(String tanggalJubileum) {
        this.tanggalJubileum = tanggalJubileum;
    }

    public boolean isFlagJubileumOn() {
        return flagJubileumOn;
    }

    public void setFlagJubileumOn(boolean flagJubileumOn) {
        this.flagJubileumOn = flagJubileumOn;
    }

    public String getTotalJubileum() {
        return totalJubileum;
    }

    public void setTotalJubileum(String totalJubileum) {
        this.totalJubileum = totalJubileum;
    }

    public BigDecimal getTotalJubileumNilai() {
        return totalJubileumNilai;
    }

    public void setTotalJubileumNilai(BigDecimal totalJubileumNilai) {
        this.totalJubileumNilai = totalJubileumNilai;
    }

    public String getFlagJubileum() {
        return flagJubileum;
    }

    public void setFlagJubileum(String flagJubileum) {
        this.flagJubileum = flagJubileum;
    }

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public BigDecimal getDanaPensiunNilai() {
        return danaPensiunNilai;
    }

    public void setDanaPensiunNilai(BigDecimal danaPensiunNilai) {
        this.danaPensiunNilai = danaPensiunNilai;
    }

    public String getBpjsJht() {
        return bpjsJht;
    }

    public void setBpjsJht(String bpjsJht) {
        this.bpjsJht = bpjsJht;
    }

    public BigDecimal getBpjsJhtNilai() {
        return bpjsJhtNilai;
    }

    public void setBpjsJhtNilai(BigDecimal bpjsJhtNilai) {
        this.bpjsJhtNilai = bpjsJhtNilai;
    }

    public String getBpjsPensiun() {
        return bpjsPensiun;
    }

    public void setBpjsPensiun(String bpjsPensiun) {
        this.bpjsPensiun = bpjsPensiun;
    }

    public BigDecimal getBpjsPensiunNilai() {
        return bpjsPensiunNilai;
    }

    public void setBpjsPensiunNilai(BigDecimal bpjsPensiunNilai) {
        this.bpjsPensiunNilai = bpjsPensiunNilai;
    }

    public String getTotalGajiBersih() {
        return totalGajiBersih;
    }

    public void setTotalGajiBersih(String totalGajiBersih) {
        this.totalGajiBersih = totalGajiBersih;
    }

    public BigDecimal getTotalGajiBersihNilai() {
        return totalGajiBersihNilai;
    }

    public void setTotalGajiBersihNilai(BigDecimal totalGajiBersihNilai) {
        this.totalGajiBersihNilai = totalGajiBersihNilai;
    }

    private boolean sudahApprove = false;

    public String getStApprovalDate() {
        return stApprovalDate;
    }

    public void setStApprovalDate(String stApprovalDate) {
        this.stApprovalDate = stApprovalDate;
    }

    public boolean isSudahApprove() {
        return sudahApprove;
    }

    public void setSudahApprove(boolean sudahApprove) {
        this.sudahApprove = sudahApprove;
    }

    public String getApprovalId() {
        return ApprovalId;
    }

    public void setApprovalId(String approvalId) {
        ApprovalId = approvalId;
    }

    public BigDecimal getGajiPensiunBpjsNilai() {
        return gajiPensiunBpjsNilai;
    }

    public void setGajiPensiunBpjsNilai(BigDecimal gajiPensiunBpjsNilai) {
        this.gajiPensiunBpjsNilai = gajiPensiunBpjsNilai;
    }

    public Timestamp getApprovalDate() {
        return ApprovalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        ApprovalDate = approvalDate;
    }

    private int jumlahPegawai;

    public int getJumlahPegawai() {
        return jumlahPegawai;
    }

    public void setJumlahPegawai(int jumlahPegawai) {
        this.jumlahPegawai = jumlahPegawai;
    }

    public String getBulan1() {
        return bulan1;
    }

    public void setBulan1(String bulan1) {
        this.bulan1 = bulan1;
    }

    public String getTahun1() {
        return tahun1;
    }

    public void setTahun1(String tahun1) {
        this.tahun1 = tahun1;
    }

    public String getFlagZakat() {
        return flagZakat;
    }

    public void setFlagZakat(String flagZakat) {
        this.flagZakat = flagZakat;
    }

    public BigDecimal getPphGajiNilai() {
        return pphGajiNilai;
    }

    public void setPphGajiNilai(BigDecimal pphGajiNilai) {
        this.pphGajiNilai = pphGajiNilai;
    }

    public BigDecimal getPphPengobatanNilai() {
        return pphPengobatanNilai;
    }

    public void setPphPengobatanNilai(BigDecimal pphPengobatanNilai) {
        this.pphPengobatanNilai = pphPengobatanNilai;
    }

    public BigDecimal getIuranPensiunNilai() {
        return iuranPensiunNilai;
    }

    public void setIuranPensiunNilai(BigDecimal iuranPensiunNilai) {
        this.iuranPensiunNilai = iuranPensiunNilai;
    }

    public BigDecimal getIuranBpjsTkNilai() {
        return iuranBpjsTkNilai;
    }

    public void setIuranBpjsTkNilai(BigDecimal iuranBpjsTkNilai) {
        this.iuranBpjsTkNilai = iuranBpjsTkNilai;
    }

    public BigDecimal getIuranBpjsPensiunNilai() {
        return iuranBpjsPensiunNilai;
    }

    public void setIuranBpjsPensiunNilai(BigDecimal iuranBpjsPensiunNilai) {
        this.iuranBpjsPensiunNilai = iuranBpjsPensiunNilai;
    }

    public BigDecimal getIuranBpjsKesehatanNilai() {
        return iuranBpjsKesehatanNilai;
    }

    public void setIuranBpjsKesehatanNilai(BigDecimal iuranBpjsKesehatanNilai) {
        this.iuranBpjsKesehatanNilai = iuranBpjsKesehatanNilai;
    }

    public BigDecimal getUangMukaLainnyaNilai() {
        return uangMukaLainnyaNilai;
    }

    public void setUangMukaLainnyaNilai(BigDecimal uangMukaLainnyaNilai) {
        this.uangMukaLainnyaNilai = uangMukaLainnyaNilai;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getKekuranganBpjsTkNilai() {
        return kekuranganBpjsTkNilai;
    }

    public void setKekuranganBpjsTkNilai(BigDecimal kekuranganBpjsTkNilai) {
        this.kekuranganBpjsTkNilai = kekuranganBpjsTkNilai;
    }

    public BigDecimal getPengobatanNilai() {
        return pengobatanNilai;
    }

    public void setPengobatanNilai(BigDecimal pengobatanNilai) {
        this.pengobatanNilai = pengobatanNilai;
    }

    public BigDecimal getKoperasiNilai() {
        return koperasiNilai;
    }

    public void setKoperasiNilai(BigDecimal koperasiNilai) {
        this.koperasiNilai = koperasiNilai;
    }

    public BigDecimal getDansosNilai() {
        return dansosNilai;
    }

    public void setDansosNilai(BigDecimal dansosNilai) {
        this.dansosNilai = dansosNilai;
    }

    public BigDecimal getSPNilai() {
        return SPNilai;
    }

    public void setSPNilai(BigDecimal SPNilai) {
        this.SPNilai = SPNilai;
    }

    public BigDecimal getBazisNilai() {
        return BazisNilai;
    }

    public void setBazisNilai(BigDecimal bazisNilai) {
        BazisNilai = bazisNilai;
    }

    public BigDecimal getBaporNilai() {
        return baporNilai;
    }

    public void setBaporNilai(BigDecimal baporNilai) {
        this.baporNilai = baporNilai;
    }

    public BigDecimal getZakatNilai() {
        return zakatNilai;
    }

    public void setZakatNilai(BigDecimal zakatNilai) {
        this.zakatNilai = zakatNilai;
    }

    public BigDecimal getLainLainNilai() {
        return lainLainNilai;
    }

    public void setLainLainNilai(BigDecimal lainLainNilai) {
        this.lainLainNilai = lainLainNilai;
    }

    public BigDecimal getTotalANilai() {
        return totalANilai;
    }

    public void setTotalANilai(BigDecimal totalANilai) {
        this.totalANilai = totalANilai;
    }

    public BigDecimal getTotalBNilai() {
        return totalBNilai;
    }

    public void setTotalBNilai(BigDecimal totalBNilai) {
        this.totalBNilai = totalBNilai;
    }

    public BigDecimal getTotalCNilai() {
        return totalCNilai;
    }

    public void setTotalCNilai(BigDecimal totalCNilai) {
        this.totalCNilai = totalCNilai;
    }

    public BigDecimal getTotalThrNilai() {
        return totalThrNilai;
    }

    public void setTotalThrNilai(BigDecimal totalThrNilai) {
        this.totalThrNilai = totalThrNilai;
    }

    public BigDecimal getTotalPendidikanNilai() {
        return totalPendidikanNilai;
    }

    public void setTotalPendidikanNilai(BigDecimal totalPendidikanNilai) {
        this.totalPendidikanNilai = totalPendidikanNilai;
    }

    public BigDecimal getTotalJasProdNilai() {
        return totalJasProdNilai;
    }

    public void setTotalJasProdNilai(BigDecimal totalJasProdNilai) {
        this.totalJasProdNilai = totalJasProdNilai;
    }

    public BigDecimal getTotalRapelNilai() {
        return totalRapelNilai;
    }

    public void setTotalRapelNilai(BigDecimal totalRapelNilai) {
        this.totalRapelNilai = totalRapelNilai;
    }

    public BigDecimal getKompensasiNilai() {
        return kompensasiNilai;
    }

    public void setKompensasiNilai(BigDecimal kompensasiNilai) {
        this.kompensasiNilai = kompensasiNilai;
    }

    public BigDecimal getTunjanganUmkNilai() {
        return tunjanganUmkNilai;
    }

    public void setTunjanganUmkNilai(BigDecimal tunjanganUmkNilai) {
        this.tunjanganUmkNilai = tunjanganUmkNilai;
    }

    public BigDecimal getTunjanganPeralihanNilai() {
        return tunjanganPeralihanNilai;
    }

    public void setTunjanganPeralihanNilai(BigDecimal tunjanganPeralihanNilai) {
        this.tunjanganPeralihanNilai = tunjanganPeralihanNilai;
    }

    public BigDecimal getTunjanganStrukturalNilai() {
        return tunjanganStrukturalNilai;
    }

    public void setTunjanganStrukturalNilai(BigDecimal tunjanganStrukturalNilai) {
        this.tunjanganStrukturalNilai = tunjanganStrukturalNilai;
    }

    public BigDecimal getTunjanganPendidikanNilai() {
        return tunjanganPendidikanNilai;
    }

    public void setTunjanganPendidikanNilai(BigDecimal tunjanganPendidikanNilai) {
        this.tunjanganPendidikanNilai = tunjanganPendidikanNilai;
    }

    public BigDecimal getTunjanganJabatanStrukturalNilai() {
        return tunjanganJabatanStrukturalNilai;
    }

    public void setTunjanganJabatanStrukturalNilai(BigDecimal tunjanganJabatanStrukturalNilai) {
        this.tunjanganJabatanStrukturalNilai = tunjanganJabatanStrukturalNilai;
    }

    public BigDecimal getTunjanganStrategisNilai() {
        return tunjanganStrategisNilai;
    }

    public void setTunjanganStrategisNilai(BigDecimal tunjanganStrategisNilai) {
        this.tunjanganStrategisNilai = tunjanganStrategisNilai;
    }

    public BigDecimal getTunjanganTransportNilai() {
        return tunjanganTransportNilai;
    }

    public void setTunjanganTransportNilai(BigDecimal tunjanganTransportNilai) {
        this.tunjanganTransportNilai = tunjanganTransportNilai;
    }

    public BigDecimal getTunjanganAirListrikNilai() {
        return tunjanganAirListrikNilai;
    }

    public void setTunjanganAirListrikNilai(BigDecimal tunjanganAirListrikNilai) {
        this.tunjanganAirListrikNilai = tunjanganAirListrikNilai;
    }

    public BigDecimal getTunjanganPengobatanNilai() {
        return tunjanganPengobatanNilai;
    }

    public void setTunjanganPengobatanNilai(BigDecimal tunjanganPengobatanNilai) {
        this.tunjanganPengobatanNilai = tunjanganPengobatanNilai;
    }

    public BigDecimal getTunjanganPerumahanNilai() {
        return tunjanganPerumahanNilai;
    }

    public void setTunjanganPerumahanNilai(BigDecimal tunjanganPerumahanNilai) {
        this.tunjanganPerumahanNilai = tunjanganPerumahanNilai;
    }

    public BigDecimal getTunjanganPphNilai() {
        return tunjanganPphNilai;
    }

    public void setTunjanganPphNilai(BigDecimal tunjanganPphNilai) {
        this.tunjanganPphNilai = tunjanganPphNilai;
    }

    public BigDecimal getTunjanganBajuDinasNilai() {
        return tunjanganBajuDinasNilai;
    }

    public void setTunjanganBajuDinasNilai(BigDecimal tunjanganBajuDinasNilai) {
        this.tunjanganBajuDinasNilai = tunjanganBajuDinasNilai;
    }

    public BigDecimal getTunjanganLainNilai() {
        return tunjanganLainNilai;
    }

    public void setTunjanganLainNilai(BigDecimal tunjanganLainNilai) {
        this.tunjanganLainNilai = tunjanganLainNilai;
    }

    public BigDecimal getTunjanganLemburNilai() {
        return tunjanganLemburNilai;
    }

    public void setTunjanganLemburNilai(BigDecimal tunjanganLemburNilai) {
        this.tunjanganLemburNilai = tunjanganLemburNilai;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public BigDecimal getGajiBpjsNilai() {
        return gajiBpjsNilai;
    }

    public void setGajiBpjsNilai(BigDecimal gajiBpjsNilai) {
        this.gajiBpjsNilai = gajiBpjsNilai;
    }

    public BigDecimal getGajiPensiunNilai() {
        return gajiPensiunNilai;
    }

    public void setGajiPensiunNilai(BigDecimal gajiPensiunNilai) {
        this.gajiPensiunNilai = gajiPensiunNilai;
    }

    public String getGajiBpjs() {
        return gajiBpjs;
    }

    public void setGajiBpjs(String gajiBpjs) {
        this.gajiBpjs = gajiBpjs;
    }

    public String getGajiPensiun() {
        return gajiPensiun;
    }

    public void setGajiPensiun(String gajiPensiun) {
        this.gajiPensiun = gajiPensiun;
    }

    public String getTotalA() {
        return totalA;
    }

    public void setTotalA(String totalA) {
        this.totalA = totalA;
    }

    public String getTotalB() {
        return totalB;
    }

    public void setTotalB(String totalB) {
        this.totalB = totalB;
    }

    public String getTotalC() {
        return totalC;
    }

    public void setTotalC(String totalC) {
        this.totalC = totalC;
    }

    public String getTunjanganLembur() {
        return tunjanganLembur;
    }

    public void setTunjanganLembur(String tunjanganLembur) {
        this.tunjanganLembur = tunjanganLembur;
    }

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(String tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public String getKelompokId() {
        return kelompokId;
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

    public String getApprovalFlag() {
        return ApprovalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        ApprovalFlag = approvalFlag;
    }

    public String getBapor() {
        return bapor;
    }

    public void setBapor(String bapor) {
        this.bapor = bapor;
    }

    public String getBazis() {
        return Bazis;
    }

    public void setBazis(String bazis) {
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

    public String getDansos() {
        return dansos;
    }

    public void setDansos(String dansos) {
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

    public String getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(String gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public String getGajiPensiunBpjs() {
        return gajiPensiunBpjs;
    }

    public void setGajiPensiunBpjs(String gajiPensiunBpjs) {
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

    public String getIuranBpjsKesehatan() {
        return iuranBpjsKesehatan;
    }

    public void setIuranBpjsKesehatan(String iuranBpjsKesehatan) {
        this.iuranBpjsKesehatan = iuranBpjsKesehatan;
    }

    public String getIuranBpjsPensiun() {
        return iuranBpjsPensiun;
    }

    public void setIuranBpjsPensiun(String iuranBpjsPensiun) {
        this.iuranBpjsPensiun = iuranBpjsPensiun;
    }

    public String getIuranBpjsTk() {
        return iuranBpjsTk;
    }

    public void setIuranBpjsTk(String iuranBpjsTk) {
        this.iuranBpjsTk = iuranBpjsTk;
    }

    public String getIuranPensiun() {
        return iuranPensiun;
    }

    public void setIuranPensiun(String iuranPensiun) {
        this.iuranPensiun = iuranPensiun;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getKekuranganBpjsTk() {
        return kekuranganBpjsTk;
    }

    public void setKekuranganBpjsTk(String kekuranganBpjsTk) {
        this.kekuranganBpjsTk = kekuranganBpjsTk;
    }

    public String getKompensasi() {
        return kompensasi;
    }

    public void setKompensasi(String kompensasi) {
        this.kompensasi = kompensasi;
    }

    public String getKoperasi() {
        return koperasi;
    }

    public void setKoperasi(String koperasi) {
        this.koperasi = koperasi;
    }

    public String getLainLain() {
        return lainLain;
    }

    public void setLainLain(String lainLain) {
        this.lainLain = lainLain;
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

    public String getNilaiFaktorKeluarga() {
        return nilaiFaktorKeluarga;
    }

    public void setNilaiFaktorKeluarga(String nilaiFaktorKeluarga) {
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

    public String getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(String pengobatan) {
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

    public String getPphGaji() {
        return pphGaji;
    }

    public void setPphGaji(String pphGaji) {
        this.pphGaji = pphGaji;
    }

    public String getPphPengobatan() {
        return pphPengobatan;
    }

    public void setPphPengobatan(String pphPengobatan) {
        this.pphPengobatan = pphPengobatan;
    }

    public String getSP() {
        return SP;
    }

    public void setSP(String SP) {
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

    public String getTotalJasProd() {
        return totalJasProd;
    }

    public void setTotalJasProd(String totalJasProd) {
        this.totalJasProd = totalJasProd;
    }

    public String getTotalPendidikan() {
        return totalPendidikan;
    }

    public void setTotalPendidikan(String totalPendidikan) {
        this.totalPendidikan = totalPendidikan;
    }

    public String getTotalRapel() {
        return totalRapel;
    }

    public void setTotalRapel(String totalRapel) {
        this.totalRapel = totalRapel;
    }

    public String getTotalThr() {
        return totalThr;
    }

    public void setTotalThr(String totalThr) {
        this.totalThr = totalThr;
    }

    public String getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public void setTunjanganAirListrik(String tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
    }

    public String getTunjanganBajuDinas() {
        return tunjanganBajuDinas;
    }

    public void setTunjanganBajuDinas(String tunjanganBajuDinas) {
        this.tunjanganBajuDinas = tunjanganBajuDinas;
    }

    public String getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(String tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public String getTunjanganLain() {
        return tunjanganLain;
    }

    public void setTunjanganLain(String tunjanganLain) {
        this.tunjanganLain = tunjanganLain;
    }

    public String getTunjanganPendidikan() {
        return tunjanganPendidikan;
    }

    public void setTunjanganPendidikan(String tunjanganPendidikan) {
        this.tunjanganPendidikan = tunjanganPendidikan;
    }

    public String getTunjanganPengobatan() {
        return tunjanganPengobatan;
    }

    public void setTunjanganPengobatan(String tunjanganPengobatan) {
        this.tunjanganPengobatan = tunjanganPengobatan;
    }

    public String getTunjanganPerumahan() {
        return tunjanganPerumahan;
    }

    public void setTunjanganPerumahan(String tunjanganPerumahan) {
        this.tunjanganPerumahan = tunjanganPerumahan;
    }

    public String getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(String tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public String getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(String tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public String getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(String tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public String getTunjanganTransport() {
        return tunjanganTransport;
    }

    public void setTunjanganTransport(String tunjanganTransport) {
        this.tunjanganTransport = tunjanganTransport;
    }

    public String getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(String tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public String getUangMukaLainnya() {
        return uangMukaLainnya;
    }

    public void setUangMukaLainnya(String uangMukaLainnya) {
        this.uangMukaLainnya = uangMukaLainnya;
    }

    public String getZakat() {
        return zakat;
    }

    public void setZakat(String zakat) {
        this.zakat = zakat;
    }
}