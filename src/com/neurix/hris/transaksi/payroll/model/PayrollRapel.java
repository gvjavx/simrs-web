package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollRapel extends BaseModel {
    private String rapelId;
    private String payrollId;

    private String nama;
    private String golonganName;
    private String statusKeluarga;
    private int jumlahAnak;
    private int point;
    private Date tanggalAwal;
    private Date tanggalAkhir;

    private String tunjanganUmk;
    private String tunjanganStruktural;
    private String tunjanganJabatanStruktural;
    private String tunjanganStrategis;
    private String tunjanganAirListrik;
    private String tunjanganPerumahan;
    private String gajiGolongan;

    private String tunjanganUmkLama;
    private String tunjanganStrukturalLama;
    private String tunjanganJabatanStrukturalLama;
    private String tunjanganStrategisLama;
    private String tunjanganAirListrikLama;
    private String tunjanganPerumahanLama;
    private String gajiGolonganLama;
    private String peralihanLama;

    private String tunjanganUmkBaru;
    private String tunjanganStrukturalBaru;
    private String tunjanganJabatanStrukturalBaru;
    private String tunjanganStrategisBaru;
    private String tunjanganAirListrikBaru;
    private String tunjanganPerumahanBaru;
    private String gajiGolonganBaru;
    private String peralihanBaru;

    private String totalRapelJubileum;
    private String totalRapelThr;
    private String totalRapelPendidikan;
    private String totalRapelInsentif;
    private String totalRapelLembur;

    private int jumlahBulan;
    private BigDecimal gajiGolonganNilai;
    private BigDecimal peralihanNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private BigDecimal tunjanganStrategisNilai;
    private BigDecimal tunjanganAirListrikNilai;
    private BigDecimal tunjanganPerumahanNilai;

    private BigDecimal jamLembur;
    private BigDecimal peralihanNilaiLama;
    private BigDecimal gajiGolonganLamaNilai;
    private BigDecimal tunjanganUmkLamaNilai;
    private BigDecimal tunjanganStrukturalLamaNilai;
    private BigDecimal tunjanganJabatanStrukturalLamaNilai;
    private BigDecimal tunjanganStrategisLamaNilai;
    private BigDecimal tunjanganAirListrikLamaNilai;
    private BigDecimal tunjanganPerumahanLamaNilai;

    private BigDecimal gajiGolonganBaruNilai;
    private BigDecimal peralihanBaruNilai;
    private BigDecimal tunjanganUmkBaruNilai;
    private BigDecimal tunjanganStrukturalBaruNilai;
    private BigDecimal tunjanganJabatanStrukturalBaruNilai;
    private BigDecimal tunjanganStrategisBaruNilai;
    private BigDecimal tunjanganAirListrikBaruNilai;
    private BigDecimal tunjanganPerumahanBaruNilai;

    private String rapelBulan;
    private String rapelTahun;
    private String flagRapel;
    private String nip;
    private String golonganLama;
    private String golonganIdLama;
    private String golonganIdBaru;
    private String golonganBaru;
    private String pointLebihLama;
    private String pointLama;
    private String pointBaru;
    private String totalRapel;
    private String totalRapelBulan;
    private String totalRapelFinal;
    private BigDecimal totalRapelBulanNilai;
    private BigDecimal totalRapelNilai;
    private BigDecimal totalRapelFinalNilai;
    private BigDecimal pphRapelNilai;
    private BigDecimal pphRapelPribadiNilai;
    private BigDecimal tunjanganPphNilai;
    private BigDecimal rapelBersihNilai;

    private String pphRapel;
    private String pphRapelPribadi;
    private String tunjanganPph;
    private String rapelBersih;

    private BigDecimal totalRapelJubileumNilai;
    private BigDecimal totalRapelThrNilai;
    private BigDecimal totalRapelPendidikanNilai;
    private BigDecimal totalRapelInsentifNilai;
    private BigDecimal totalRapelLemburNilai;

    private String flagGajiDasar;
    private String flagUmk;
    private String flagStruktural;
    private String flagJabatanStruktural;
    private String flagSrategis;
    private String flagAirListrik;
    private String flagPerumahan;

    public String getPphRapel() {
        return pphRapel;
    }

    public void setPphRapel(String pphRapel) {
        this.pphRapel = pphRapel;
    }

    public String getPphRapelPribadi() {
        return pphRapelPribadi;
    }

    public void setPphRapelPribadi(String pphRapelPribadi) {
        this.pphRapelPribadi = pphRapelPribadi;
    }

    public String getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(String tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public String getRapelBersih() {
        return rapelBersih;
    }

    public void setRapelBersih(String rapelBersih) {
        this.rapelBersih = rapelBersih;
    }

    public BigDecimal getPphRapelNilai() {
        return pphRapelNilai;
    }

    public void setPphRapelNilai(BigDecimal pphRapelNilai) {
        this.pphRapelNilai = pphRapelNilai;
    }

    public BigDecimal getPphRapelPribadiNilai() {
        return pphRapelPribadiNilai;
    }

    public void setPphRapelPribadiNilai(BigDecimal pphRapelPribadiNilai) {
        this.pphRapelPribadiNilai = pphRapelPribadiNilai;
    }

    public BigDecimal getTunjanganPphNilai() {
        return tunjanganPphNilai;
    }

    public void setTunjanganPphNilai(BigDecimal tunjanganPphNilai) {
        this.tunjanganPphNilai = tunjanganPphNilai;
    }

    public BigDecimal getRapelBersihNilai() {
        return rapelBersihNilai;
    }

    public void setRapelBersihNilai(BigDecimal rapelBersihNilai) {
        this.rapelBersihNilai = rapelBersihNilai;
    }

    public BigDecimal getPeralihanNilaiLama() {
        return peralihanNilaiLama;
    }

    public void setPeralihanNilaiLama(BigDecimal peralihanNilaiLama) {
        this.peralihanNilaiLama = peralihanNilaiLama;
    }

    public String getPeralihanLama() {
        return peralihanLama;
    }

    public void setPeralihanLama(String peralihanLama) {
        this.peralihanLama = peralihanLama;
    }

    public String getPeralihanBaru() {
        return peralihanBaru;
    }

    public void setPeralihanBaru(String peralihanBaru) {
        this.peralihanBaru = peralihanBaru;
    }

    public BigDecimal getPeralihanNilai() {
        return peralihanNilai;
    }

    public void setPeralihanNilai(BigDecimal peralihanNilai) {
        this.peralihanNilai = peralihanNilai;
    }

    public BigDecimal getPeralihanBaruNilai() {
        return peralihanBaruNilai;
    }

    public void setPeralihanBaruNilai(BigDecimal peralihanBaruNilai) {
        this.peralihanBaruNilai = peralihanBaruNilai;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getRapelBulan() {
        return rapelBulan;
    }

    public void setRapelBulan(String rapelBulan) {
        this.rapelBulan = rapelBulan;
    }

    public String getRapelTahun() {
        return rapelTahun;
    }

    public void setRapelTahun(String rapelTahun) {
        this.rapelTahun = rapelTahun;
    }

    public String getPointLebihLama() {
        return pointLebihLama;
    }

    public void setPointLebihLama(String pointLebihLama) {
        this.pointLebihLama = pointLebihLama;
    }

    public BigDecimal getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(BigDecimal jamLembur) {
        this.jamLembur = jamLembur;
    }

    public String getTotalRapelLembur() {
        return totalRapelLembur;
    }

    public void setTotalRapelLembur(String totalRapelLembur) {
        this.totalRapelLembur = totalRapelLembur;
    }

    public BigDecimal getTotalRapelLemburNilai() {
        return totalRapelLemburNilai;
    }

    public void setTotalRapelLemburNilai(BigDecimal totalRapelLemburNilai) {
        this.totalRapelLemburNilai = totalRapelLemburNilai;
    }

    public String getTotalRapelBulan() {
        return totalRapelBulan;
    }

    public void setTotalRapelBulan(String totalRapelBulan) {
        this.totalRapelBulan = totalRapelBulan;
    }

    public BigDecimal getTotalRapelBulanNilai() {
        return totalRapelBulanNilai;
    }

    public void setTotalRapelBulanNilai(BigDecimal totalRapelBulanNilai) {
        this.totalRapelBulanNilai = totalRapelBulanNilai;
    }

    public String getTotalRapelFinal() {
        return totalRapelFinal;
    }

    public void setTotalRapelFinal(String totalRapelFinal) {
        this.totalRapelFinal = totalRapelFinal;
    }

    public BigDecimal getTotalRapelFinalNilai() {
        return totalRapelFinalNilai;
    }

    public void setTotalRapelFinalNilai(BigDecimal totalRapelFinalNilai) {
        this.totalRapelFinalNilai = totalRapelFinalNilai;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getGolonganIdLama() {
        return golonganIdLama;
    }

    public void setGolonganIdLama(String golonganIdLama) {
        this.golonganIdLama = golonganIdLama;
    }

    public String getGolonganIdBaru() {
        return golonganIdBaru;
    }

    public void setGolonganIdBaru(String golonganIdBaru) {
        this.golonganIdBaru = golonganIdBaru;
    }

    public String getPointLama() {
        return pointLama;
    }

    public void setPointLama(String pointLama) {
        this.pointLama = pointLama;
    }

    public String getPointBaru() {
        return pointBaru;
    }

    public void setPointBaru(String pointBaru) {
        this.pointBaru = pointBaru;
    }

    public String getTotalRapelJubileum() {
        return totalRapelJubileum;
    }

    public void setTotalRapelJubileum(String totalRapelJubileum) {
        this.totalRapelJubileum = totalRapelJubileum;
    }

    public String getTotalRapelThr() {
        return totalRapelThr;
    }

    public void setTotalRapelThr(String totalRapelThr) {
        this.totalRapelThr = totalRapelThr;
    }

    public String getTotalRapelPendidikan() {
        return totalRapelPendidikan;
    }

    public void setTotalRapelPendidikan(String totalRapelPendidikan) {
        this.totalRapelPendidikan = totalRapelPendidikan;
    }

    public String getTotalRapelInsentif() {
        return totalRapelInsentif;
    }

    public void setTotalRapelInsentif(String totalRapelInsentif) {
        this.totalRapelInsentif = totalRapelInsentif;
    }

    public BigDecimal getTotalRapelJubileumNilai() {
        return totalRapelJubileumNilai;
    }

    public void setTotalRapelJubileumNilai(BigDecimal totalRapelJubileumNilai) {
        this.totalRapelJubileumNilai = totalRapelJubileumNilai;
    }

    public BigDecimal getTotalRapelThrNilai() {
        return totalRapelThrNilai;
    }

    public void setTotalRapelThrNilai(BigDecimal totalRapelThrNilai) {
        this.totalRapelThrNilai = totalRapelThrNilai;
    }

    public BigDecimal getTotalRapelPendidikanNilai() {
        return totalRapelPendidikanNilai;
    }

    public void setTotalRapelPendidikanNilai(BigDecimal totalRapelPendidikanNilai) {
        this.totalRapelPendidikanNilai = totalRapelPendidikanNilai;
    }

    public BigDecimal getTotalRapelInsentifNilai() {
        return totalRapelInsentifNilai;
    }

    public void setTotalRapelInsentifNilai(BigDecimal totalRapelInsentifNilai) {
        this.totalRapelInsentifNilai = totalRapelInsentifNilai;
    }

    public String getGolonganLama() {
        return golonganLama;
    }

    public void setGolonganLama(String golonganLama) {
        this.golonganLama = golonganLama;
    }

    public String getGolonganBaru() {
        return golonganBaru;
    }

    public void setGolonganBaru(String golonganBaru) {
        this.golonganBaru = golonganBaru;
    }

    public String getFlagPerumahan() {
        return flagPerumahan;
    }

    public void setFlagPerumahan(String flagPerumahan) {
        this.flagPerumahan = flagPerumahan;
    }

    public String getTunjanganPerumahanBaru() {
        return tunjanganPerumahanBaru;
    }

    public void setTunjanganPerumahanBaru(String tunjanganPerumahanBaru) {
        this.tunjanganPerumahanBaru = tunjanganPerumahanBaru;
    }

    public String getTunjanganPerumahan() {
        return tunjanganPerumahan;
    }

    public void setTunjanganPerumahan(String tunjanganPerumahan) {
        this.tunjanganPerumahan = tunjanganPerumahan;
    }

    public String getTunjanganPerumahanLama() {
        return tunjanganPerumahanLama;
    }

    public void setTunjanganPerumahanLama(String tunjanganPerumahanLama) {
        this.tunjanganPerumahanLama = tunjanganPerumahanLama;
    }

    public BigDecimal getTunjanganPerumahanNilai() {
        return tunjanganPerumahanNilai;
    }

    public void setTunjanganPerumahanNilai(BigDecimal tunjanganPerumahanNilai) {
        this.tunjanganPerumahanNilai = tunjanganPerumahanNilai;
    }

    public BigDecimal getTunjanganPerumahanLamaNilai() {
        return tunjanganPerumahanLamaNilai;
    }

    public void setTunjanganPerumahanLamaNilai(BigDecimal tunjanganPerumahanLamaNilai) {
        this.tunjanganPerumahanLamaNilai = tunjanganPerumahanLamaNilai;
    }

    public BigDecimal getTunjanganPerumahanBaruNilai() {
        return tunjanganPerumahanBaruNilai;
    }

    public void setTunjanganPerumahanBaruNilai(BigDecimal tunjanganPerumahanBaruNilai) {
        this.tunjanganPerumahanBaruNilai = tunjanganPerumahanBaruNilai;
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

    public String getFlagGajiDasar() {
        return flagGajiDasar;
    }

    public void setFlagGajiDasar(String flagGajiDasar) {
        this.flagGajiDasar = flagGajiDasar;
    }

    public String getFlagUmk() {
        return flagUmk;
    }

    public void setFlagUmk(String flagUmk) {
        this.flagUmk = flagUmk;
    }

    public String getFlagStruktural() {
        return flagStruktural;
    }

    public void setFlagStruktural(String flagStruktural) {
        this.flagStruktural = flagStruktural;
    }

    public String getFlagJabatanStruktural() {
        return flagJabatanStruktural;
    }

    public void setFlagJabatanStruktural(String flagJabatanStruktural) {
        this.flagJabatanStruktural = flagJabatanStruktural;
    }

    public String getFlagSrategis() {
        return flagSrategis;
    }

    public void setFlagSrategis(String flagSrategis) {
        this.flagSrategis = flagSrategis;
    }

    public String getFlagAirListrik() {
        return flagAirListrik;
    }

    public void setFlagAirListrik(String flagAirListrik) {
        this.flagAirListrik = flagAirListrik;
    }

    public int getJumlahBulan() {
        return jumlahBulan;
    }

    public void setJumlahBulan(int jumlahBulan) {
        this.jumlahBulan = jumlahBulan;
    }

    public String getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(String gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public String getFlagRapel() {
        return flagRapel;
    }

    public void setFlagRapel(String flagRapel) {
        this.flagRapel = flagRapel;
    }

    public String getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(String tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getTunjanganUmkNilai() {
        return tunjanganUmkNilai;
    }

    public void setTunjanganUmkNilai(BigDecimal tunjanganUmkNilai) {
        this.tunjanganUmkNilai = tunjanganUmkNilai;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTotalRapel() {
        return totalRapel;
    }

    public void setTotalRapel(String totalRapel) {
        this.totalRapel = totalRapel;
    }

    public BigDecimal getTotalRapelNilai() {
        return totalRapelNilai;
    }

    public void setTotalRapelNilai(BigDecimal totalRapelNilai) {
        this.totalRapelNilai = totalRapelNilai;
    }

    public BigDecimal getTunjanganAirListrikNilai() {
        return tunjanganAirListrikNilai;
    }

    public void setTunjanganAirListrikNilai(BigDecimal tunjanganAirListrikNilai) {
        this.tunjanganAirListrikNilai = tunjanganAirListrikNilai;
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

    public BigDecimal getTunjanganStrukturalNilai() {
        return tunjanganStrukturalNilai;
    }

    public void setTunjanganStrukturalNilai(BigDecimal tunjanganStrukturalNilai) {
        this.tunjanganStrukturalNilai = tunjanganStrukturalNilai;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getRapelId() {
        return rapelId;
    }

    public void setRapelId(String rapelId) {
        this.rapelId = rapelId;
    }

    public String getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public void setTunjanganAirListrik(String tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
    }

    public String getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(String tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
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
}