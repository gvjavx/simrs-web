package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollPensiun extends BaseModel {
    private String pensiunId;
    private String payrollId;
    private Date tanggalAktif;
    private Date tanggalPensiun;
    private int faktorKaliGaji;
    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private BigDecimal tunjanganPeralihanNilai;

    private BigDecimal jumlahTunjanganNilai;
    private BigDecimal tunjanganPensiunNilai;
    private BigDecimal tunjanganPenghargaanNilai;
    private BigDecimal penggantianPerumahanNilai;

    private String jumlahTunjangan;
    private String tunjanganPensiun;
    private String tunjanganPenghargaan;
    private String gajiGolongan;
    private String tunjanganUmk;
    private String tunjanganStruktural;
    private String tunjanganJabatanStruktural;
    private String tunjanganPeralihan;
    private String penggantianPerumahan;


    private String stTanggalAktif;
    private String stTanggalPensiun;
    private String stTanggalPayroll;
    private String golongan;
    private String point;
    private String masaKerjaTahun;
    private String masaKerjaBulan;
    private String faktorPensiun;
    private String faktorPenghargaan;
    private String nip;
    private BigDecimal hasilNilai;
    private BigDecimal hasilKaliFaktorPensiunNilai;
    private BigDecimal hasilKaliFaktorPenghargaanNilai;
    private BigDecimal jumlahBiayaPensiunNilai;
    private BigDecimal pphPensiunNilai;
    private BigDecimal pph1Nilai;
    private BigDecimal pph2Nilai;
    private BigDecimal pph3Nilai;
    private BigDecimal pph4Nilai;
    private BigDecimal nettoPensiunNilai;
    private BigDecimal totalPensiunNilai;

    private String pph1;
    private String pph2;
    private String pph3;
    private String pph4;
    private String nettoPensiun;
    private String jumlahBiayaPensiun;
    private String pphPensiun;
    private String hasil;
    private String hasilKaliFaktorPensiun;
    private String hasilKaliFaktorPenghargaan;
    private String flagPensiun = "N";
    private String stMasaKerja ;
    private String totalPensiun ;

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

    public BigDecimal getPph1Nilai() {
        return pph1Nilai;
    }

    public void setPph1Nilai(BigDecimal pph1Nilai) {
        this.pph1Nilai = pph1Nilai;
    }

    public BigDecimal getPph2Nilai() {
        return pph2Nilai;
    }

    public void setPph2Nilai(BigDecimal pph2Nilai) {
        this.pph2Nilai = pph2Nilai;
    }

    public BigDecimal getPph3Nilai() {
        return pph3Nilai;
    }

    public void setPph3Nilai(BigDecimal pph3Nilai) {
        this.pph3Nilai = pph3Nilai;
    }

    public BigDecimal getPph4Nilai() {
        return pph4Nilai;
    }

    public void setPph4Nilai(BigDecimal pph4Nilai) {
        this.pph4Nilai = pph4Nilai;
    }

    public String getPph1() {
        return pph1;
    }

    public void setPph1(String pph1) {
        this.pph1 = pph1;
    }

    public String getPph2() {
        return pph2;
    }

    public void setPph2(String pph2) {
        this.pph2 = pph2;
    }

    public String getPph3() {
        return pph3;
    }

    public void setPph3(String pph3) {
        this.pph3 = pph3;
    }

    public String getPph4() {
        return pph4;
    }

    public void setPph4(String pph4) {
        this.pph4 = pph4;
    }

    public BigDecimal getPenggantianPerumahanNilai() {
        return penggantianPerumahanNilai;
    }

    public void setPenggantianPerumahanNilai(BigDecimal penggantianPerumahanNilai) {
        this.penggantianPerumahanNilai = penggantianPerumahanNilai;
    }

    public String getPenggantianPerumahan() {
        return penggantianPerumahan;
    }

    public void setPenggantianPerumahan(String penggantianPerumahan) {
        this.penggantianPerumahan = penggantianPerumahan;
    }

    public String getStMasaKerja() {
        return stMasaKerja;
    }

    public void setStMasaKerja(String stMasaKerja) {
        this.stMasaKerja = stMasaKerja;
    }

    private String nama ;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlahTunjangan() {
        return jumlahTunjangan;
    }

    public void setJumlahTunjangan(String jumlahTunjangan) {
        this.jumlahTunjangan = jumlahTunjangan;
    }

    public BigDecimal getJumlahTunjanganNilai() {
        return jumlahTunjanganNilai;
    }

    public void setJumlahTunjanganNilai(BigDecimal jumlahTunjanganNilai) {
        this.jumlahTunjanganNilai = jumlahTunjanganNilai;
    }

    public String getTunjanganPenghargaan() {
        return tunjanganPenghargaan;
    }

    public void setTunjanganPenghargaan(String tunjanganPenghargaan) {
        this.tunjanganPenghargaan = tunjanganPenghargaan;
    }

    public BigDecimal getTunjanganPenghargaanNilai() {
        return tunjanganPenghargaanNilai;
    }

    public void setTunjanganPenghargaanNilai(BigDecimal tunjanganPenghargaanNilai) {
        this.tunjanganPenghargaanNilai = tunjanganPenghargaanNilai;
    }

    public String getTunjanganPensiun() {
        return tunjanganPensiun;
    }

    public void setTunjanganPensiun(String tunjanganPensiun) {
        this.tunjanganPensiun = tunjanganPensiun;
    }

    public BigDecimal getTunjanganPensiunNilai() {
        return tunjanganPensiunNilai;
    }

    public void setTunjanganPensiunNilai(BigDecimal tunjanganPensiunNilai) {
        this.tunjanganPensiunNilai = tunjanganPensiunNilai;
    }
    public String getStTanggalPayroll() {
        return stTanggalPayroll;
    }

    public void setStTanggalPayroll(String stTanggalPayroll) {
        this.stTanggalPayroll = stTanggalPayroll;
    }

    public String getPphPensiun() {
        return pphPensiun;
    }

    public void setPphPensiun(String pphPensiun) {
        this.pphPensiun = pphPensiun;
    }

    public BigDecimal getPphPensiunNilai() {
        return pphPensiunNilai;
    }

    public void setPphPensiunNilai(BigDecimal pphPensiunNilai) {
        this.pphPensiunNilai = pphPensiunNilai;
    }

    public String getJumlahBiayaPensiun() {
        return jumlahBiayaPensiun;
    }

    public void setJumlahBiayaPensiun(String jumlahBiayaPensiun) {
        this.jumlahBiayaPensiun = jumlahBiayaPensiun;
    }

    public BigDecimal getJumlahBiayaPensiunNilai() {
        return jumlahBiayaPensiunNilai;
    }

    public void setJumlahBiayaPensiunNilai(BigDecimal jumlahBiayaPensiunNilai) {
        this.jumlahBiayaPensiunNilai = jumlahBiayaPensiunNilai;
    }

    public String getHasilKaliFaktorPenghargaan() {
        return hasilKaliFaktorPenghargaan;
    }

    public void setHasilKaliFaktorPenghargaan(String hasilKaliFaktorPenghargaan) {
        this.hasilKaliFaktorPenghargaan = hasilKaliFaktorPenghargaan;
    }

    public BigDecimal getHasilKaliFaktorPenghargaanNilai() {
        return hasilKaliFaktorPenghargaanNilai;
    }

    public void setHasilKaliFaktorPenghargaanNilai(BigDecimal hasilKaliFaktorPenghargaanNilai) {
        this.hasilKaliFaktorPenghargaanNilai = hasilKaliFaktorPenghargaanNilai;
    }

    public String getFlagPensiun() {
        return flagPensiun;
    }

    public void setFlagPensiun(String flagPensiun) {
        this.flagPensiun = flagPensiun;
    }

    public String getFaktorPenghargaan() {
        return faktorPenghargaan;
    }

    public void setFaktorPenghargaan(String faktorPenghargaan) {
        this.faktorPenghargaan = faktorPenghargaan;
    }

    public String getFaktorPensiun() {
        return faktorPensiun;
    }

    public void setFaktorPensiun(String faktorPensiun) {
        this.faktorPensiun = faktorPensiun;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getMasaKerjaBulan() {
        return masaKerjaBulan;
    }

    public void setMasaKerjaBulan(String masaKerjaBulan) {
        this.masaKerjaBulan = masaKerjaBulan;
    }

    public String getMasaKerjaTahun() {
        return masaKerjaTahun;
    }

    public void setMasaKerjaTahun(String masaKerjaTahun) {
        this.masaKerjaTahun = masaKerjaTahun;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getStTanggalAktif() {
        return stTanggalAktif;
    }

    public void setStTanggalAktif(String stTanggalAktif) {
        this.stTanggalAktif = stTanggalAktif;
    }

    public String getStTanggalPensiun() {
        return stTanggalPensiun;
    }

    public void setStTanggalPensiun(String stTanggalPensiun) {
        this.stTanggalPensiun = stTanggalPensiun;
    }

    public String getGajiGolongan() {
        return gajiGolongan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getHasilNilai() {
        return hasilNilai;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public void setHasilNilai(BigDecimal hasilNilai) {
        this.hasilNilai = hasilNilai;
    }

    public String getPensiunId() {
        return pensiunId;
    }

    public void setPensiunId(String pensiunId) {
        this.pensiunId = pensiunId;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
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

    public int getFaktorKaliGaji() {
        return faktorKaliGaji;
    }

    public void setFaktorKaliGaji(int faktorKaliGaji) {
        this.faktorKaliGaji = faktorKaliGaji;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public String getHasilKaliFaktorPensiun() {
        return hasilKaliFaktorPensiun;
    }

    public void setHasilKaliFaktorPensiun(String hasilKaliFaktorPensiun) {
        this.hasilKaliFaktorPensiun = hasilKaliFaktorPensiun;
    }

    public BigDecimal getHasilKaliFaktorPensiunNilai() {
        return hasilKaliFaktorPensiunNilai;
    }

    public void setHasilKaliFaktorPensiunNilai(BigDecimal hasilKaliFaktorPensiunNilai) {
        this.hasilKaliFaktorPensiunNilai = hasilKaliFaktorPensiunNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public BigDecimal getTunjanganUmkNilai() {
        return tunjanganUmkNilai;
    }

    public void setTunjanganUmkNilai(BigDecimal tunjanganUmkNilai) {
        this.tunjanganUmkNilai = tunjanganUmkNilai;
    }

    public BigDecimal getTunjanganStrukturalNilai() {
        return tunjanganStrukturalNilai;
    }

    public void setTunjanganStrukturalNilai(BigDecimal tunjanganStrukturalNilai) {
        this.tunjanganStrukturalNilai = tunjanganStrukturalNilai;
    }

    public BigDecimal getTunjanganJabatanStrukturalNilai() {
        return tunjanganJabatanStrukturalNilai;
    }

    public void setTunjanganJabatanStrukturalNilai(BigDecimal tunjanganJabatanStrukturalNilai) {
        this.tunjanganJabatanStrukturalNilai = tunjanganJabatanStrukturalNilai;
    }

    public BigDecimal getTunjanganPeralihanNilai() {
        return tunjanganPeralihanNilai;
    }

    public void setTunjanganPeralihanNilai(BigDecimal tunjanganPeralihanNilai) {
        this.tunjanganPeralihanNilai = tunjanganPeralihanNilai;
    }

    public void setGajiGolongan(String gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public String getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(String tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public String getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(String tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public String getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(String tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(String tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }
}