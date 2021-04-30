/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

public class ItHrisPayrollEntity {
    private String payrollId;
    private String payrollHeaderId;
    private String tipePayroll;
    private String bulan;
    private String tahun;
    private String tahunSkalaGaji;
    private String nip;
    private String nipLama;
    private String namaPegawai;
    private String gelar;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private String departmentId;
    private String departmentName;
    private String subDivisi;
    private String subDivisiName;
    private String kelompokPositionId;
    private String kelompokPositionName;
    private String profesiId;
    private String profesiName;
    private String branchId;
    private String branchName;
    private String golonganDapen;
    private Integer gradeLevel;
    private String tipePegawaiId;
    private String tipePegawaiName;
    private String statusPegawaiId;
    private String statusPegawaiName;
    private String jenisPegawaiId;
    private String jenisPegawaiName;
    private String dapenId;
    private String dapenName;
    private String statusKeluarga;
    private Integer jumlahAnak;
    private Integer jumlahAnakDitanggung;
    private String npwp;
    private String noRekBank;
    private String namaBank;
    private String gender;
    private Integer masaKerjaGol;
    private Integer masaKerja;
    private Date tanggalAktif;
    private Date tanggalAkhirKontrak;
    private Date tanggalPraPensiun;
    private Date tanggalPensiun;
    private Date tanggalKeluar;
    private Date tanggalAwalLembur;
    private Date tanggalAkhirLembur;
    private String kodering;
    private BigDecimal gajiPensiun;
    private BigDecimal gajiPensiunBpjs;
    private BigDecimal gajiPokok;
    private BigDecimal gajiKotor;
    private BigDecimal thp;
    private BigDecimal gajiBersih;
    private BigDecimal totalA;
    private BigDecimal totalB;
    private BigDecimal totalC;
    private BigDecimal totalD;
    private BigDecimal totalRlab;
    private BigDecimal tunjunganSankhus;
    private BigDecimal tunjanganUmk;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganPeralihan;
    private BigDecimal tunjanganJabatanStruktural;
    private BigDecimal tunjanganStrategis;
    private BigDecimal kompensasi;
    private BigDecimal tunjanganTransport;
    private BigDecimal tunjanganAirListrik;
    private BigDecimal tunjanganPengobatan;
    private BigDecimal tunjanganPerumahan;
    private BigDecimal tunjanganAir;
    private BigDecimal tunjanganListrik;
    private BigDecimal tunjanganBbm;
    private BigDecimal tunjanganFungsional;
    private BigDecimal tunjanganTambahan;
    private BigDecimal tunjanganPph;
    private BigDecimal tunjanganBajuDinas;
    private BigDecimal tunjanganLain;
    private BigDecimal tunjanganLembur;
    private BigDecimal peralihanGapok;
    private BigDecimal peralihanSankhus;
    private BigDecimal peralihanTunjangan;
    private BigDecimal tunjanganPemondokan;
    private BigDecimal tunjanganKomunikasi;
    private BigDecimal tunjanganSupervisi;
    private BigDecimal tunjanganLokal;
    private BigDecimal tunjanganSiaga;
    private BigDecimal pphGaji;
    private BigDecimal pphPengobatan;
    private BigDecimal pphSeharusnya;
    private BigDecimal selisihPph;
    private BigDecimal tunjanganDapen;
    private BigDecimal persenDapenKary;
    private BigDecimal persenDapenPers;
    private BigDecimal iuranDapenKary;
    private BigDecimal iuranDapenPers;
    private BigDecimal iuranKopkar;
    private BigDecimal iuranSp;
    private BigDecimal iuranPiikb;
    private BigDecimal iuranBankMandiri;
    private BigDecimal iuranBankBri;
    private BigDecimal iuranInfaq;
    private BigDecimal iuranPerkesDanObat;
    private BigDecimal iuranListrik;
    private BigDecimal iuranProfesi;
    private BigDecimal iuranPotonganLain;
    private BigDecimal tambahanLain;
    private BigDecimal uangMukaLainnya;
    private BigDecimal kekuranganBpjsTk;
    private BigDecimal pengobatan;
    private BigDecimal koperasi;
    private BigDecimal dansos;
    private BigDecimal sp;
    private BigDecimal bazis;
    private BigDecimal bapor;
    private BigDecimal zakat;
    private BigDecimal lainLain;
    private BigDecimal umr;
    private BigDecimal totalPotonganLain;
    private BigDecimal ptkpPegawai;
    private BigDecimal tunjanganBpjsKs;
    private BigDecimal tunjanganBpjsTk;
    private BigDecimal totalIuranBpjsTkKary;
    private BigDecimal totalIuranBpjsTkPers;
    private BigDecimal minBpjsKs;
    private BigDecimal maxBpjsKs;
    private BigDecimal persenBpjsKsKary;
    private BigDecimal persenBpjsKsPers;
    private BigDecimal minBpjsTk;
    private BigDecimal maxBpjsTk;
    private BigDecimal persenBpjsTkIuranKary;
    private BigDecimal persenBpjsTkJpkKary;
    private BigDecimal persenBpjsTkJkkPers;
    private BigDecimal persenBpjsTkJhtPers;
    private BigDecimal persenBpjsTkJkmPers;
    private BigDecimal persenBpjsTkJpkPers;
    private BigDecimal dasarBpjsKs;
    private BigDecimal dasarBpjsTk;
    private BigDecimal iuranBpjsKsKary;
    private BigDecimal iuranBpjsKsPers;
    private BigDecimal iuranBpjsTkKary;
    private BigDecimal jpkBpjsTkKary;
    private BigDecimal jkkBpjsTkPers;
    private BigDecimal jhtBpjsTkPers;
    private BigDecimal jkmBpjsTkPers;
    private BigDecimal jpkBpjsTkPers;

    private String createdWho;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    private BigDecimal multifikator;
    private Set<ItHrisPayrollPphEntity> itHrisPayrollPph;


    public String getPayrollHeaderId() {
        return payrollHeaderId;
    }

    public void setPayrollHeaderId(String payrollHeaderId) {
        this.payrollHeaderId = payrollHeaderId;
    }

    public BigDecimal getMultifikator() {
        return multifikator;
    }

    public void setMultifikator(BigDecimal multifikator) {
        this.multifikator = multifikator;
    }

    public Set<ItHrisPayrollPphEntity> getItHrisPayrollPph() {
        return itHrisPayrollPph;
    }

    public void setItHrisPayrollPph(Set<ItHrisPayrollPphEntity> itHrisPayrollPph) {
        this.itHrisPayrollPph = itHrisPayrollPph;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getTipePayroll() {
        return tipePayroll;
    }

    public void setTipePayroll(String tipePayroll) {
        this.tipePayroll = tipePayroll;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahunSkalaGaji() {
        return tahunSkalaGaji;
    }

    public void setTahunSkalaGaji(String tahunSkalaGaji) {
        this.tahunSkalaGaji = tahunSkalaGaji;
    }

    public Date getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(Date tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNipLama() {
        return nipLama;
    }

    public void setNipLama(String nipLama) {
        this.nipLama = nipLama;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
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

    public String getSubDivisi() {
        return subDivisi;
    }

    public void setSubDivisi(String subDivisi) {
        this.subDivisi = subDivisi;
    }

    public String getSubDivisiName() {
        return subDivisiName;
    }

    public void setSubDivisiName(String subDivisiName) {
        this.subDivisiName = subDivisiName;
    }

    public String getKelompokPositionId() {
        return kelompokPositionId;
    }

    public void setKelompokPositionId(String kelompokPositionId) {
        this.kelompokPositionId = kelompokPositionId;
    }

    public String getKelompokPositionName() {
        return kelompokPositionName;
    }

    public void setKelompokPositionName(String kelompokPositionName) {
        this.kelompokPositionName = kelompokPositionName;
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

    public String getGolonganDapen() {
        return golonganDapen;
    }

    public void setGolonganDapen(String golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public String getStatusPegawaiId() {
        return statusPegawaiId;
    }

    public void setStatusPegawaiId(String statusPegawaiId) {
        this.statusPegawaiId = statusPegawaiId;
    }

    public String getStatusPegawaiName() {
        return statusPegawaiName;
    }

    public void setStatusPegawaiName(String statusPegawaiName) {
        this.statusPegawaiName = statusPegawaiName;
    }

    public String getJenisPegawaiId() {
        return jenisPegawaiId;
    }

    public void setJenisPegawaiId(String jenisPegawaiId) {
        this.jenisPegawaiId = jenisPegawaiId;
    }

    public String getJenisPegawaiName() {
        return jenisPegawaiName;
    }

    public void setJenisPegawaiName(String jenisPegawaiName) {
        this.jenisPegawaiName = jenisPegawaiName;
    }

    public String getDapenId() {
        return dapenId;
    }

    public void setDapenId(String dapenId) {
        this.dapenId = dapenId;
    }

    public String getDapenName() {
        return dapenName;
    }

    public void setDapenName(String dapenName) {
        this.dapenName = dapenName;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public Integer getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(Integer jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public Integer getJumlahAnakDitanggung() {
        return jumlahAnakDitanggung;
    }

    public void setJumlahAnakDitanggung(Integer jumlahAnakDitanggung) {
        this.jumlahAnakDitanggung = jumlahAnakDitanggung;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getNoRekBank() {
        return noRekBank;
    }

    public void setNoRekBank(String noRekBank) {
        this.noRekBank = noRekBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMasaKerjaGol() {
        return masaKerjaGol;
    }

    public void setMasaKerjaGol(Integer masaKerjaGol) {
        this.masaKerjaGol = masaKerjaGol;
    }

    public Integer getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(Integer masaKerja) {
        this.masaKerja = masaKerja;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public Date getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(Date tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public Date getTanggalPraPensiun() {
        return tanggalPraPensiun;
    }

    public void setTanggalPraPensiun(Date tanggalPraPensiun) {
        this.tanggalPraPensiun = tanggalPraPensiun;
    }

    public Date getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(Date tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public Date getTanggalAwalLembur() {
        return tanggalAwalLembur;
    }

    public void setTanggalAwalLembur(Date tanggalAwalLembur) {
        this.tanggalAwalLembur = tanggalAwalLembur;
    }

    public Date getTanggalAkhirLembur() {
        return tanggalAkhirLembur;
    }

    public void setTanggalAkhirLembur(Date tanggalAkhirLembur) {
        this.tanggalAkhirLembur = tanggalAkhirLembur;
    }

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
    }

    public BigDecimal getGajiPensiun() {
        return gajiPensiun;
    }

    public void setGajiPensiun(BigDecimal gajiPensiun) {
        this.gajiPensiun = gajiPensiun;
    }

    public BigDecimal getGajiPensiunBpjs() {
        return gajiPensiunBpjs;
    }

    public void setGajiPensiunBpjs(BigDecimal gajiPensiunBpjs) {
        this.gajiPensiunBpjs = gajiPensiunBpjs;
    }

    public BigDecimal getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(BigDecimal gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public BigDecimal getGajiKotor() {
        return gajiKotor;
    }

    public void setGajiKotor(BigDecimal gajiKotor) {
        this.gajiKotor = gajiKotor;
    }

    public BigDecimal getThp() {
        return thp;
    }

    public void setThp(BigDecimal thp) {
        this.thp = thp;
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

    public BigDecimal getTotalD() {
        return totalD;
    }

    public void setTotalD(BigDecimal totalD) {
        this.totalD = totalD;
    }

    public BigDecimal getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(BigDecimal totalRlab) {
        this.totalRlab = totalRlab;
    }

    public BigDecimal getTunjunganSankhus() {
        return tunjunganSankhus;
    }

    public void setTunjunganSankhus(BigDecimal tunjunganSankhus) {
        this.tunjunganSankhus = tunjunganSankhus;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(BigDecimal tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(BigDecimal tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public BigDecimal getKompensasi() {
        return kompensasi;
    }

    public void setKompensasi(BigDecimal kompensasi) {
        this.kompensasi = kompensasi;
    }

    public BigDecimal getTunjanganTransport() {
        return tunjanganTransport;
    }

    public void setTunjanganTransport(BigDecimal tunjanganTransport) {
        this.tunjanganTransport = tunjanganTransport;
    }

    public BigDecimal getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public void setTunjanganAirListrik(BigDecimal tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
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

    public BigDecimal getTunjanganAir() {
        return tunjanganAir;
    }

    public void setTunjanganAir(BigDecimal tunjanganAir) {
        this.tunjanganAir = tunjanganAir;
    }

    public BigDecimal getTunjanganListrik() {
        return tunjanganListrik;
    }

    public void setTunjanganListrik(BigDecimal tunjanganListrik) {
        this.tunjanganListrik = tunjanganListrik;
    }

    public BigDecimal getTunjanganBbm() {
        return tunjanganBbm;
    }

    public void setTunjanganBbm(BigDecimal tunjanganBbm) {
        this.tunjanganBbm = tunjanganBbm;
    }

    public BigDecimal getTunjanganFungsional() {
        return tunjanganFungsional;
    }

    public void setTunjanganFungsional(BigDecimal tunjanganFungsional) {
        this.tunjanganFungsional = tunjanganFungsional;
    }

    public BigDecimal getTunjanganTambahan() {
        return tunjanganTambahan;
    }

    public void setTunjanganTambahan(BigDecimal tunjanganTambahan) {
        this.tunjanganTambahan = tunjanganTambahan;
    }

    public BigDecimal getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(BigDecimal tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public BigDecimal getTunjanganBajuDinas() {
        return tunjanganBajuDinas;
    }

    public void setTunjanganBajuDinas(BigDecimal tunjanganBajuDinas) {
        this.tunjanganBajuDinas = tunjanganBajuDinas;
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

    public BigDecimal getPeralihanGapok() {
        return peralihanGapok;
    }

    public void setPeralihanGapok(BigDecimal peralihanGapok) {
        this.peralihanGapok = peralihanGapok;
    }

    public BigDecimal getPeralihanSankhus() {
        return peralihanSankhus;
    }

    public void setPeralihanSankhus(BigDecimal peralihanSankhus) {
        this.peralihanSankhus = peralihanSankhus;
    }

    public BigDecimal getPeralihanTunjangan() {
        return peralihanTunjangan;
    }

    public void setPeralihanTunjangan(BigDecimal peralihanTunjangan) {
        this.peralihanTunjangan = peralihanTunjangan;
    }

    public BigDecimal getTunjanganPemondokan() {
        return tunjanganPemondokan;
    }

    public void setTunjanganPemondokan(BigDecimal tunjanganPemondokan) {
        this.tunjanganPemondokan = tunjanganPemondokan;
    }

    public BigDecimal getTunjanganKomunikasi() {
        return tunjanganKomunikasi;
    }

    public void setTunjanganKomunikasi(BigDecimal tunjanganKomunikasi) {
        this.tunjanganKomunikasi = tunjanganKomunikasi;
    }

    public BigDecimal getTunjanganSupervisi() {
        return tunjanganSupervisi;
    }

    public void setTunjanganSupervisi(BigDecimal tunjanganSupervisi) {
        this.tunjanganSupervisi = tunjanganSupervisi;
    }

    public BigDecimal getTunjanganLokal() {
        return tunjanganLokal;
    }

    public void setTunjanganLokal(BigDecimal tunjanganLokal) {
        this.tunjanganLokal = tunjanganLokal;
    }

    public BigDecimal getTunjanganSiaga() {
        return tunjanganSiaga;
    }

    public void setTunjanganSiaga(BigDecimal tunjanganSiaga) {
        this.tunjanganSiaga = tunjanganSiaga;
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


    public BigDecimal getTunjanganDapen() {
        return tunjanganDapen;
    }

    public void setTunjanganDapen(BigDecimal tunjanganDapen) {
        this.tunjanganDapen = tunjanganDapen;
    }

    public BigDecimal getPersenDapenKary() {
        return persenDapenKary;
    }

    public void setPersenDapenKary(BigDecimal persenDapenKary) {
        this.persenDapenKary = persenDapenKary;
    }

    public BigDecimal getPersenDapenPers() {
        return persenDapenPers;
    }

    public void setPersenDapenPers(BigDecimal persenDapenPers) {
        this.persenDapenPers = persenDapenPers;
    }

    public BigDecimal getIuranDapenKary() {
        return iuranDapenKary;
    }

    public void setIuranDapenKary(BigDecimal iuranDapenKary) {
        this.iuranDapenKary = iuranDapenKary;
    }

    public BigDecimal getIuranDapenPers() {
        return iuranDapenPers;
    }

    public void setIuranDapenPers(BigDecimal iuranDapenPers) {
        this.iuranDapenPers = iuranDapenPers;
    }

    public BigDecimal getIuranKopkar() {
        return iuranKopkar;
    }

    public void setIuranKopkar(BigDecimal iuranKopkar) {
        this.iuranKopkar = iuranKopkar;
    }

    public BigDecimal getIuranSp() {
        return iuranSp;
    }

    public void setIuranSp(BigDecimal iuranSp) {
        this.iuranSp = iuranSp;
    }

    public BigDecimal getIuranPiikb() {
        return iuranPiikb;
    }

    public void setIuranPiikb(BigDecimal iuranPiikb) {
        this.iuranPiikb = iuranPiikb;
    }

    public BigDecimal getIuranBankMandiri() {
        return iuranBankMandiri;
    }

    public void setIuranBankMandiri(BigDecimal iuranBankMandiri) {
        this.iuranBankMandiri = iuranBankMandiri;
    }

    public BigDecimal getIuranBankBri() {
        return iuranBankBri;
    }

    public void setIuranBankBri(BigDecimal iuranBankBri) {
        this.iuranBankBri = iuranBankBri;
    }

    public BigDecimal getIuranInfaq() {
        return iuranInfaq;
    }

    public void setIuranInfaq(BigDecimal iuranInfaq) {
        this.iuranInfaq = iuranInfaq;
    }

    public BigDecimal getIuranPerkesDanObat() {
        return iuranPerkesDanObat;
    }

    public void setIuranPerkesDanObat(BigDecimal iuranPerkesDanObat) {
        this.iuranPerkesDanObat = iuranPerkesDanObat;
    }

    public BigDecimal getIuranListrik() {
        return iuranListrik;
    }

    public void setIuranListrik(BigDecimal iuranListrik) {
        this.iuranListrik = iuranListrik;
    }

    public BigDecimal getIuranProfesi() {
        return iuranProfesi;
    }

    public void setIuranProfesi(BigDecimal iuranProfesi) {
        this.iuranProfesi = iuranProfesi;
    }

    public BigDecimal getIuranPotonganLain() {
        return iuranPotonganLain;
    }

    public void setIuranPotonganLain(BigDecimal iuranPotonganLain) {
        this.iuranPotonganLain = iuranPotonganLain;
    }

    public BigDecimal getTambahanLain() {
        return tambahanLain;
    }

    public void setTambahanLain(BigDecimal tambahanLain) {
        this.tambahanLain = tambahanLain;
    }

    public BigDecimal getUangMukaLainnya() {
        return uangMukaLainnya;
    }

    public void setUangMukaLainnya(BigDecimal uangMukaLainnya) {
        this.uangMukaLainnya = uangMukaLainnya;
    }

    public BigDecimal getKekuranganBpjsTk() {
        return kekuranganBpjsTk;
    }

    public void setKekuranganBpjsTk(BigDecimal kekuranganBpjsTk) {
        this.kekuranganBpjsTk = kekuranganBpjsTk;
    }

    public BigDecimal getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(BigDecimal pengobatan) {
        this.pengobatan = pengobatan;
    }

    public BigDecimal getKoperasi() {
        return koperasi;
    }

    public void setKoperasi(BigDecimal koperasi) {
        this.koperasi = koperasi;
    }

    public BigDecimal getDansos() {
        return dansos;
    }

    public void setDansos(BigDecimal dansos) {
        this.dansos = dansos;
    }

    public BigDecimal getSp() {
        return sp;
    }

    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }

    public BigDecimal getBazis() {
        return bazis;
    }

    public void setBazis(BigDecimal bazis) {
        this.bazis = bazis;
    }

    public BigDecimal getBapor() {
        return bapor;
    }

    public void setBapor(BigDecimal bapor) {
        this.bapor = bapor;
    }

    public BigDecimal getZakat() {
        return zakat;
    }

    public void setZakat(BigDecimal zakat) {
        this.zakat = zakat;
    }

    public BigDecimal getLainLain() {
        return lainLain;
    }

    public void setLainLain(BigDecimal lainLain) {
        this.lainLain = lainLain;
    }

    public BigDecimal getUmr() {
        return umr;
    }

    public void setUmr(BigDecimal umr) {
        this.umr = umr;
    }

    public BigDecimal getTotalPotonganLain() {
        return totalPotonganLain;
    }

    public void setTotalPotonganLain(BigDecimal totalPotonganLain) {
        this.totalPotonganLain = totalPotonganLain;
    }

    public BigDecimal getPtkpPegawai() {
        return ptkpPegawai;
    }

    public void setPtkpPegawai(BigDecimal ptkpPegawai) {
        this.ptkpPegawai = ptkpPegawai;
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

    public BigDecimal getTotalIuranBpjsTkKary() {
        return totalIuranBpjsTkKary;
    }

    public void setTotalIuranBpjsTkKary(BigDecimal totalIuranBpjsTkKary) {
        this.totalIuranBpjsTkKary = totalIuranBpjsTkKary;
    }

    public BigDecimal getTotalIuranBpjsTkPers() {
        return totalIuranBpjsTkPers;
    }

    public void setTotalIuranBpjsTkPers(BigDecimal totalIuranBpjsTkPers) {
        this.totalIuranBpjsTkPers = totalIuranBpjsTkPers;
    }

    public BigDecimal getMinBpjsKs() {
        return minBpjsKs;
    }

    public void setMinBpjsKs(BigDecimal minBpjsKs) {
        this.minBpjsKs = minBpjsKs;
    }

    public BigDecimal getMaxBpjsKs() {
        return maxBpjsKs;
    }

    public void setMaxBpjsKs(BigDecimal maxBpjsKs) {
        this.maxBpjsKs = maxBpjsKs;
    }

    public BigDecimal getPersenBpjsKsKary() {
        return persenBpjsKsKary;
    }

    public void setPersenBpjsKsKary(BigDecimal persenBpjsKsKary) {
        this.persenBpjsKsKary = persenBpjsKsKary;
    }

    public BigDecimal getPersenBpjsKsPers() {
        return persenBpjsKsPers;
    }

    public void setPersenBpjsKsPers(BigDecimal persenBpjsKsPers) {
        this.persenBpjsKsPers = persenBpjsKsPers;
    }

    public BigDecimal getMinBpjsTk() {
        return minBpjsTk;
    }

    public void setMinBpjsTk(BigDecimal minBpjsTk) {
        this.minBpjsTk = minBpjsTk;
    }

    public BigDecimal getMaxBpjsTk() {
        return maxBpjsTk;
    }

    public void setMaxBpjsTk(BigDecimal maxBpjsTk) {
        this.maxBpjsTk = maxBpjsTk;
    }

    public BigDecimal getPersenBpjsTkIuranKary() {
        return persenBpjsTkIuranKary;
    }

    public void setPersenBpjsTkIuranKary(BigDecimal persenBpjsTkIuranKary) {
        this.persenBpjsTkIuranKary = persenBpjsTkIuranKary;
    }

    public BigDecimal getPersenBpjsTkJpkKary() {
        return persenBpjsTkJpkKary;
    }

    public void setPersenBpjsTkJpkKary(BigDecimal persenBpjsTkJpkKary) {
        this.persenBpjsTkJpkKary = persenBpjsTkJpkKary;
    }

    public BigDecimal getPersenBpjsTkJkkPers() {
        return persenBpjsTkJkkPers;
    }

    public void setPersenBpjsTkJkkPers(BigDecimal persenBpjsTkJkkPers) {
        this.persenBpjsTkJkkPers = persenBpjsTkJkkPers;
    }

    public BigDecimal getPersenBpjsTkJhtPers() {
        return persenBpjsTkJhtPers;
    }

    public void setPersenBpjsTkJhtPers(BigDecimal persenBpjsTkJhtPers) {
        this.persenBpjsTkJhtPers = persenBpjsTkJhtPers;
    }

    public BigDecimal getPersenBpjsTkJkmPers() {
        return persenBpjsTkJkmPers;
    }

    public void setPersenBpjsTkJkmPers(BigDecimal persenBpjsTkJkmPers) {
        this.persenBpjsTkJkmPers = persenBpjsTkJkmPers;
    }

    public BigDecimal getPersenBpjsTkJpkPers() {
        return persenBpjsTkJpkPers;
    }

    public void setPersenBpjsTkJpkPers(BigDecimal persenBpjsTkJpkPers) {
        this.persenBpjsTkJpkPers = persenBpjsTkJpkPers;
    }

    public BigDecimal getDasarBpjsKs() {
        return dasarBpjsKs;
    }

    public void setDasarBpjsKs(BigDecimal dasarBpjsKs) {
        this.dasarBpjsKs = dasarBpjsKs;
    }

    public BigDecimal getDasarBpjsTk() {
        return dasarBpjsTk;
    }

    public void setDasarBpjsTk(BigDecimal dasarBpjsTk) {
        this.dasarBpjsTk = dasarBpjsTk;
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

    public BigDecimal getJpkBpjsTkKary() {
        return jpkBpjsTkKary;
    }

    public void setJpkBpjsTkKary(BigDecimal jpkBpjsTkKary) {
        this.jpkBpjsTkKary = jpkBpjsTkKary;
    }

    public BigDecimal getJkkBpjsTkPers() {
        return jkkBpjsTkPers;
    }

    public void setJkkBpjsTkPers(BigDecimal jkkBpjsTkPers) {
        this.jkkBpjsTkPers = jkkBpjsTkPers;
    }

    public BigDecimal getJhtBpjsTkPers() {
        return jhtBpjsTkPers;
    }

    public void setJhtBpjsTkPers(BigDecimal jhtBpjsTkPers) {
        this.jhtBpjsTkPers = jhtBpjsTkPers;
    }

    public BigDecimal getJkmBpjsTkPers() {
        return jkmBpjsTkPers;
    }

    public void setJkmBpjsTkPers(BigDecimal jkmBpjsTkPers) {
        this.jkmBpjsTkPers = jkmBpjsTkPers;
    }

    public BigDecimal getJpkBpjsTkPers() {
        return jpkBpjsTkPers;
    }

    public void setJpkBpjsTkPers(BigDecimal jpkBpjsTkPers) {
        this.jpkBpjsTkPers = jpkBpjsTkPers;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "ItHrisPayrollEntity{" +
                "payrollId='" + payrollId + '\'' +
                ", payrollHeaderId='" + payrollHeaderId + '\'' +
                ", tipePayroll='" + tipePayroll + '\'' +
                ", bulan='" + bulan + '\'' +
                ", tahun='" + tahun + '\'' +
                ", tahunSkalaGaji='" + tahunSkalaGaji + '\'' +
                ", nip='" + nip + '\'' +
                ", nipLama='" + nipLama + '\'' +
                ", namaPegawai='" + namaPegawai + '\'' +
                ", gelar='" + gelar + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", golonganId='" + golonganId + '\'' +
                ", golonganName='" + golonganName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", subDivisi='" + subDivisi + '\'' +
                ", subDivisiName='" + subDivisiName + '\'' +
                ", kelompokPositionId='" + kelompokPositionId + '\'' +
                ", kelompokPositionName='" + kelompokPositionName + '\'' +
                ", profesiId='" + profesiId + '\'' +
                ", profesiName='" + profesiName + '\'' +
                ", branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", golonganDapen='" + golonganDapen + '\'' +
                ", gradeLevel=" + gradeLevel +
                ", tipePegawaiId='" + tipePegawaiId + '\'' +
                ", tipePegawaiName='" + tipePegawaiName + '\'' +
                ", statusPegawaiId='" + statusPegawaiId + '\'' +
                ", statusPegawaiName='" + statusPegawaiName + '\'' +
                ", jenisPegawaiId='" + jenisPegawaiId + '\'' +
                ", jenisPegawaiName='" + jenisPegawaiName + '\'' +
                ", dapenId='" + dapenId + '\'' +
                ", dapenName='" + dapenName + '\'' +
                ", statusKeluarga='" + statusKeluarga + '\'' +
                ", jumlahAnak=" + jumlahAnak +
                ", jumlahAnakDitanggung=" + jumlahAnakDitanggung +
                ", npwp='" + npwp + '\'' +
                ", noRekBank='" + noRekBank + '\'' +
                ", namaBank='" + namaBank + '\'' +
                ", gender='" + gender + '\'' +
                ", masaKerjaGol=" + masaKerjaGol +
                ", masaKerja=" + masaKerja +
                ", tanggalAktif=" + tanggalAktif +
                ", tanggalAkhirKontrak=" + tanggalAkhirKontrak +
                ", tanggalPraPensiun=" + tanggalPraPensiun +
                ", tanggalPensiun=" + tanggalPensiun +
                ", tanggalKeluar=" + tanggalKeluar +
                ", tanggalAwalLembur=" + tanggalAwalLembur +
                ", tanggalAkhirLembur=" + tanggalAkhirLembur +
                ", kodering='" + kodering + '\'' +
                ", gajiPensiun=" + gajiPensiun +
                ", gajiPensiunBpjs=" + gajiPensiunBpjs +
                ", gajiPokok=" + gajiPokok +
                ", gajiKotor=" + gajiKotor +
                ", thp=" + thp +
                ", gajiBersih=" + gajiBersih +
                ", totalA=" + totalA +
                ", totalB=" + totalB +
                ", totalC=" + totalC +
                ", totalD=" + totalD +
                ", totalRlab=" + totalRlab +
                ", tunjunganSankhus=" + tunjunganSankhus +
                ", tunjanganUmk=" + tunjanganUmk +
                ", tunjanganStruktural=" + tunjanganStruktural +
                ", tunjanganPeralihan=" + tunjanganPeralihan +
                ", tunjanganJabatanStruktural=" + tunjanganJabatanStruktural +
                ", tunjanganStrategis=" + tunjanganStrategis +
                ", kompensasi=" + kompensasi +
                ", tunjanganTransport=" + tunjanganTransport +
                ", tunjanganAirListrik=" + tunjanganAirListrik +
                ", tunjanganPengobatan=" + tunjanganPengobatan +
                ", tunjanganPerumahan=" + tunjanganPerumahan +
                ", tunjanganAir=" + tunjanganAir +
                ", tunjanganListrik=" + tunjanganListrik +
                ", tunjanganBbm=" + tunjanganBbm +
                ", tunjanganFungsional=" + tunjanganFungsional +
                ", tunjanganTambahan=" + tunjanganTambahan +
                ", tunjanganPph=" + tunjanganPph +
                ", tunjanganBajuDinas=" + tunjanganBajuDinas +
                ", tunjanganLain=" + tunjanganLain +
                ", tunjanganLembur=" + tunjanganLembur +
                ", peralihanGapok=" + peralihanGapok +
                ", peralihanSankhus=" + peralihanSankhus +
                ", peralihanTunjangan=" + peralihanTunjangan +
                ", tunjanganPemondokan=" + tunjanganPemondokan +
                ", tunjanganKomunikasi=" + tunjanganKomunikasi +
                ", tunjanganSupervisi=" + tunjanganSupervisi +
                ", tunjanganLokal=" + tunjanganLokal +
                ", tunjanganSiaga=" + tunjanganSiaga +
                ", pphGaji=" + pphGaji +
                ", pphPengobatan=" + pphPengobatan +
                ", pphSeharusnya=" + pphSeharusnya +
                ", selisihPph=" + selisihPph +
                ", tunjanganDapen=" + tunjanganDapen +
                ", persenDapenKary=" + persenDapenKary +
                ", persenDapenPers=" + persenDapenPers +
                ", iuranDapenKary=" + iuranDapenKary +
                ", iuranDapenPers=" + iuranDapenPers +
                ", iuranKopkar=" + iuranKopkar +
                ", iuranSp=" + iuranSp +
                ", iuranPiikb=" + iuranPiikb +
                ", iuranBankMandiri=" + iuranBankMandiri +
                ", iuranBankBri=" + iuranBankBri +
                ", iuranInfaq=" + iuranInfaq +
                ", iuranPerkesDanObat=" + iuranPerkesDanObat +
                ", iuranListrik=" + iuranListrik +
                ", iuranProfesi=" + iuranProfesi +
                ", iuranPotonganLain=" + iuranPotonganLain +
                ", tambahanLain=" + tambahanLain +
                ", uangMukaLainnya=" + uangMukaLainnya +
                ", kekuranganBpjsTk=" + kekuranganBpjsTk +
                ", pengobatan=" + pengobatan +
                ", koperasi=" + koperasi +
                ", dansos=" + dansos +
                ", sp=" + sp +
                ", bazis=" + bazis +
                ", bapor=" + bapor +
                ", zakat=" + zakat +
                ", lainLain=" + lainLain +
                ", umr=" + umr +
                ", totalPotonganLain=" + totalPotonganLain +
                ", ptkpPegawai=" + ptkpPegawai +
                ", tunjanganBpjsKs=" + tunjanganBpjsKs +
                ", tunjanganBpjsTk=" + tunjanganBpjsTk +
                ", totalIuranBpjsTkKary=" + totalIuranBpjsTkKary +
                ", totalIuranBpjsTkPers=" + totalIuranBpjsTkPers +
                ", minBpjsKs=" + minBpjsKs +
                ", maxBpjsKs=" + maxBpjsKs +
                ", persenBpjsKsKary=" + persenBpjsKsKary +
                ", persenBpjsKsPers=" + persenBpjsKsPers +
                ", minBpjsTk=" + minBpjsTk +
                ", maxBpjsTk=" + maxBpjsTk +
                ", persenBpjsTkIuranKary=" + persenBpjsTkIuranKary +
                ", persenBpjsTkJpkKary=" + persenBpjsTkJpkKary +
                ", persenBpjsTkJkkPers=" + persenBpjsTkJkkPers +
                ", persenBpjsTkJhtPers=" + persenBpjsTkJhtPers +
                ", persenBpjsTkJkmPers=" + persenBpjsTkJkmPers +
                ", persenBpjsTkJpkPers=" + persenBpjsTkJpkPers +
                ", dasarBpjsKs=" + dasarBpjsKs +
                ", dasarBpjsTk=" + dasarBpjsTk +
                ", iuranBpjsKsKary=" + iuranBpjsKsKary +
                ", iuranBpjsKsPers=" + iuranBpjsKsPers +
                ", iuranBpjsTkKary=" + iuranBpjsTkKary +
                ", jpkBpjsTkKary=" + jpkBpjsTkKary +
                ", jkkBpjsTkPers=" + jkkBpjsTkPers +
                ", jhtBpjsTkPers=" + jhtBpjsTkPers +
                ", jkmBpjsTkPers=" + jkmBpjsTkPers +
                ", jpkBpjsTkPers=" + jpkBpjsTkPers +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdate=" + lastUpdate +
                ", multifikator=" + multifikator +
                ", itHrisPayrollPph=" + itHrisPayrollPph +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItHrisPayrollEntity that = (ItHrisPayrollEntity) o;
        return Objects.equals(payrollId, that.payrollId) &&
                Objects.equals(payrollHeaderId, that.payrollHeaderId) &&
                Objects.equals(tipePayroll, that.tipePayroll) &&
                Objects.equals(bulan, that.bulan) &&
                Objects.equals(tahun, that.tahun) &&
                Objects.equals(tahunSkalaGaji, that.tahunSkalaGaji) &&
                Objects.equals(nip, that.nip) &&
                Objects.equals(nipLama, that.nipLama) &&
                Objects.equals(namaPegawai, that.namaPegawai) &&
                Objects.equals(gelar, that.gelar) &&
                Objects.equals(positionId, that.positionId) &&
                Objects.equals(positionName, that.positionName) &&
                Objects.equals(golonganId, that.golonganId) &&
                Objects.equals(golonganName, that.golonganName) &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(subDivisi, that.subDivisi) &&
                Objects.equals(subDivisiName, that.subDivisiName) &&
                Objects.equals(kelompokPositionId, that.kelompokPositionId) &&
                Objects.equals(kelompokPositionName, that.kelompokPositionName) &&
                Objects.equals(profesiId, that.profesiId) &&
                Objects.equals(profesiName, that.profesiName) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(branchName, that.branchName) &&
                Objects.equals(golonganDapen, that.golonganDapen) &&
                Objects.equals(gradeLevel, that.gradeLevel) &&
                Objects.equals(tipePegawaiId, that.tipePegawaiId) &&
                Objects.equals(tipePegawaiName, that.tipePegawaiName) &&
                Objects.equals(statusPegawaiId, that.statusPegawaiId) &&
                Objects.equals(statusPegawaiName, that.statusPegawaiName) &&
                Objects.equals(jenisPegawaiId, that.jenisPegawaiId) &&
                Objects.equals(jenisPegawaiName, that.jenisPegawaiName) &&
                Objects.equals(dapenId, that.dapenId) &&
                Objects.equals(dapenName, that.dapenName) &&
                Objects.equals(statusKeluarga, that.statusKeluarga) &&
                Objects.equals(jumlahAnak, that.jumlahAnak) &&
                Objects.equals(jumlahAnakDitanggung, that.jumlahAnakDitanggung) &&
                Objects.equals(npwp, that.npwp) &&
                Objects.equals(noRekBank, that.noRekBank) &&
                Objects.equals(namaBank, that.namaBank) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(masaKerjaGol, that.masaKerjaGol) &&
                Objects.equals(masaKerja, that.masaKerja) &&
                Objects.equals(tanggalAktif, that.tanggalAktif) &&
                Objects.equals(tanggalAkhirKontrak, that.tanggalAkhirKontrak) &&
                Objects.equals(tanggalPraPensiun, that.tanggalPraPensiun) &&
                Objects.equals(tanggalPensiun, that.tanggalPensiun) &&
                Objects.equals(tanggalKeluar, that.tanggalKeluar) &&
                Objects.equals(tanggalAwalLembur, that.tanggalAwalLembur) &&
                Objects.equals(tanggalAkhirLembur, that.tanggalAkhirLembur) &&
                Objects.equals(kodering, that.kodering) &&
                Objects.equals(gajiPensiun, that.gajiPensiun) &&
                Objects.equals(gajiPensiunBpjs, that.gajiPensiunBpjs) &&
                Objects.equals(gajiPokok, that.gajiPokok) &&
                Objects.equals(gajiKotor, that.gajiKotor) &&
                Objects.equals(thp, that.thp) &&
                Objects.equals(gajiBersih, that.gajiBersih) &&
                Objects.equals(totalA, that.totalA) &&
                Objects.equals(totalB, that.totalB) &&
                Objects.equals(totalC, that.totalC) &&
                Objects.equals(totalD, that.totalD) &&
                Objects.equals(totalRlab, that.totalRlab) &&
                Objects.equals(tunjunganSankhus, that.tunjunganSankhus) &&
                Objects.equals(tunjanganUmk, that.tunjanganUmk) &&
                Objects.equals(tunjanganStruktural, that.tunjanganStruktural) &&
                Objects.equals(tunjanganPeralihan, that.tunjanganPeralihan) &&
                Objects.equals(tunjanganJabatanStruktural, that.tunjanganJabatanStruktural) &&
                Objects.equals(tunjanganStrategis, that.tunjanganStrategis) &&
                Objects.equals(kompensasi, that.kompensasi) &&
                Objects.equals(tunjanganTransport, that.tunjanganTransport) &&
                Objects.equals(tunjanganAirListrik, that.tunjanganAirListrik) &&
                Objects.equals(tunjanganPengobatan, that.tunjanganPengobatan) &&
                Objects.equals(tunjanganPerumahan, that.tunjanganPerumahan) &&
                Objects.equals(tunjanganAir, that.tunjanganAir) &&
                Objects.equals(tunjanganListrik, that.tunjanganListrik) &&
                Objects.equals(tunjanganBbm, that.tunjanganBbm) &&
                Objects.equals(tunjanganFungsional, that.tunjanganFungsional) &&
                Objects.equals(tunjanganTambahan, that.tunjanganTambahan) &&
                Objects.equals(tunjanganPph, that.tunjanganPph) &&
                Objects.equals(tunjanganBajuDinas, that.tunjanganBajuDinas) &&
                Objects.equals(tunjanganLain, that.tunjanganLain) &&
                Objects.equals(tunjanganLembur, that.tunjanganLembur) &&
                Objects.equals(peralihanGapok, that.peralihanGapok) &&
                Objects.equals(peralihanSankhus, that.peralihanSankhus) &&
                Objects.equals(peralihanTunjangan, that.peralihanTunjangan) &&
                Objects.equals(tunjanganPemondokan, that.tunjanganPemondokan) &&
                Objects.equals(tunjanganKomunikasi, that.tunjanganKomunikasi) &&
                Objects.equals(tunjanganSupervisi, that.tunjanganSupervisi) &&
                Objects.equals(tunjanganLokal, that.tunjanganLokal) &&
                Objects.equals(tunjanganSiaga, that.tunjanganSiaga) &&
                Objects.equals(pphGaji, that.pphGaji) &&
                Objects.equals(pphPengobatan, that.pphPengobatan) &&
                Objects.equals(pphSeharusnya, that.pphSeharusnya) &&
                Objects.equals(selisihPph, that.selisihPph) &&
                Objects.equals(tunjanganDapen, that.tunjanganDapen) &&
                Objects.equals(persenDapenKary, that.persenDapenKary) &&
                Objects.equals(persenDapenPers, that.persenDapenPers) &&
                Objects.equals(iuranDapenKary, that.iuranDapenKary) &&
                Objects.equals(iuranDapenPers, that.iuranDapenPers) &&
                Objects.equals(iuranKopkar, that.iuranKopkar) &&
                Objects.equals(iuranSp, that.iuranSp) &&
                Objects.equals(iuranPiikb, that.iuranPiikb) &&
                Objects.equals(iuranBankMandiri, that.iuranBankMandiri) &&
                Objects.equals(iuranBankBri, that.iuranBankBri) &&
                Objects.equals(iuranInfaq, that.iuranInfaq) &&
                Objects.equals(iuranPerkesDanObat, that.iuranPerkesDanObat) &&
                Objects.equals(iuranListrik, that.iuranListrik) &&
                Objects.equals(iuranProfesi, that.iuranProfesi) &&
                Objects.equals(iuranPotonganLain, that.iuranPotonganLain) &&
                Objects.equals(tambahanLain, that.tambahanLain) &&
                Objects.equals(uangMukaLainnya, that.uangMukaLainnya) &&
                Objects.equals(kekuranganBpjsTk, that.kekuranganBpjsTk) &&
                Objects.equals(pengobatan, that.pengobatan) &&
                Objects.equals(koperasi, that.koperasi) &&
                Objects.equals(dansos, that.dansos) &&
                Objects.equals(sp, that.sp) &&
                Objects.equals(bazis, that.bazis) &&
                Objects.equals(bapor, that.bapor) &&
                Objects.equals(zakat, that.zakat) &&
                Objects.equals(lainLain, that.lainLain) &&
                Objects.equals(umr, that.umr) &&
                Objects.equals(totalPotonganLain, that.totalPotonganLain) &&
                Objects.equals(ptkpPegawai, that.ptkpPegawai) &&
                Objects.equals(tunjanganBpjsKs, that.tunjanganBpjsKs) &&
                Objects.equals(tunjanganBpjsTk, that.tunjanganBpjsTk) &&
                Objects.equals(totalIuranBpjsTkKary, that.totalIuranBpjsTkKary) &&
                Objects.equals(totalIuranBpjsTkPers, that.totalIuranBpjsTkPers) &&
                Objects.equals(minBpjsKs, that.minBpjsKs) &&
                Objects.equals(maxBpjsKs, that.maxBpjsKs) &&
                Objects.equals(persenBpjsKsKary, that.persenBpjsKsKary) &&
                Objects.equals(persenBpjsKsPers, that.persenBpjsKsPers) &&
                Objects.equals(minBpjsTk, that.minBpjsTk) &&
                Objects.equals(maxBpjsTk, that.maxBpjsTk) &&
                Objects.equals(persenBpjsTkIuranKary, that.persenBpjsTkIuranKary) &&
                Objects.equals(persenBpjsTkJpkKary, that.persenBpjsTkJpkKary) &&
                Objects.equals(persenBpjsTkJkkPers, that.persenBpjsTkJkkPers) &&
                Objects.equals(persenBpjsTkJhtPers, that.persenBpjsTkJhtPers) &&
                Objects.equals(persenBpjsTkJkmPers, that.persenBpjsTkJkmPers) &&
                Objects.equals(persenBpjsTkJpkPers, that.persenBpjsTkJpkPers) &&
                Objects.equals(dasarBpjsKs, that.dasarBpjsKs) &&
                Objects.equals(dasarBpjsTk, that.dasarBpjsTk) &&
                Objects.equals(iuranBpjsKsKary, that.iuranBpjsKsKary) &&
                Objects.equals(iuranBpjsKsPers, that.iuranBpjsKsPers) &&
                Objects.equals(iuranBpjsTkKary, that.iuranBpjsTkKary) &&
                Objects.equals(jpkBpjsTkKary, that.jpkBpjsTkKary) &&
                Objects.equals(jkkBpjsTkPers, that.jkkBpjsTkPers) &&
                Objects.equals(jhtBpjsTkPers, that.jhtBpjsTkPers) &&
                Objects.equals(jkmBpjsTkPers, that.jkmBpjsTkPers) &&
                Objects.equals(jpkBpjsTkPers, that.jpkBpjsTkPers) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(multifikator, that.multifikator) &&
                Objects.equals(itHrisPayrollPph, that.itHrisPayrollPph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payrollId, payrollHeaderId, tipePayroll, bulan, tahun, tahunSkalaGaji, nip, nipLama, namaPegawai, gelar, positionId, positionName, golonganId, golonganName, departmentId, departmentName, subDivisi, subDivisiName, kelompokPositionId, kelompokPositionName, profesiId, profesiName, branchId, branchName, golonganDapen, gradeLevel, tipePegawaiId, tipePegawaiName, statusPegawaiId, statusPegawaiName, jenisPegawaiId, jenisPegawaiName, dapenId, dapenName, statusKeluarga, jumlahAnak, jumlahAnakDitanggung, npwp, noRekBank, namaBank, gender, masaKerjaGol, masaKerja, tanggalAktif, tanggalAkhirKontrak, tanggalPraPensiun, tanggalPensiun, tanggalKeluar, tanggalAwalLembur, tanggalAkhirLembur, kodering, gajiPensiun, gajiPensiunBpjs, gajiPokok, gajiKotor, thp, gajiBersih, totalA, totalB, totalC, totalD, totalRlab, tunjunganSankhus, tunjanganUmk, tunjanganStruktural, tunjanganPeralihan, tunjanganJabatanStruktural, tunjanganStrategis, kompensasi, tunjanganTransport, tunjanganAirListrik, tunjanganPengobatan, tunjanganPerumahan, tunjanganAir, tunjanganListrik, tunjanganBbm, tunjanganFungsional, tunjanganTambahan, tunjanganPph, tunjanganBajuDinas, tunjanganLain, tunjanganLembur, peralihanGapok, peralihanSankhus, peralihanTunjangan, tunjanganPemondokan, tunjanganKomunikasi, tunjanganSupervisi, tunjanganLokal, tunjanganSiaga, pphGaji, pphPengobatan, pphSeharusnya, selisihPph, tunjanganDapen, persenDapenKary, persenDapenPers, iuranDapenKary, iuranDapenPers, iuranKopkar, iuranSp, iuranPiikb, iuranBankMandiri, iuranBankBri, iuranInfaq, iuranPerkesDanObat, iuranListrik, iuranProfesi, iuranPotonganLain, tambahanLain, uangMukaLainnya, kekuranganBpjsTk, pengobatan, koperasi, dansos, sp, bazis, bapor, zakat, lainLain, umr, totalPotonganLain, ptkpPegawai, tunjanganBpjsKs, tunjanganBpjsTk, totalIuranBpjsTkKary, totalIuranBpjsTkPers, minBpjsKs, maxBpjsKs, persenBpjsKsKary, persenBpjsKsPers, minBpjsTk, maxBpjsTk, persenBpjsTkIuranKary, persenBpjsTkJpkKary, persenBpjsTkJkkPers, persenBpjsTkJhtPers, persenBpjsTkJkmPers, persenBpjsTkJpkPers, dasarBpjsKs, dasarBpjsTk, iuranBpjsKsKary, iuranBpjsKsPers, iuranBpjsTkKary, jpkBpjsTkKary, jkkBpjsTkPers, jhtBpjsTkPers, jkmBpjsTkPers, jpkBpjsTkPers, createdWho, lastUpdateWho, flag, action, createdDate, lastUpdate, multifikator, itHrisPayrollPph);
    }
}
