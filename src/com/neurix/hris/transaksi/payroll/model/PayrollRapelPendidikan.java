package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollRapelPendidikan extends BaseModel {
    private String rapelPendidikanId;
    private int bulanAktif;
    private String payrollPendidikanId;
    private String payrollRapelId;
    private String nip;

    private BigDecimal pendidikanGajiGolonganNilaiBaru;
    private BigDecimal pendidikanPeralihanNilaiBaru;
    private BigDecimal pendidikanUmkNilaiBaru;
    private BigDecimal pendidikanStrukturalNilaiBaru;
    private BigDecimal pendidikanJabStrukturalNilaiBaru;
    private BigDecimal pendidikanStrategisNilaiBaru;
    private BigDecimal pendidikanAirListrikNilaiBaru;
    private BigDecimal pendidikanKompensasiNilaiBaru;
    private BigDecimal totalRapelNilaiBaru;

    private BigDecimal pendidikanGajiGolonganNilaiLama;
    private BigDecimal pendidikanPeralihanNilaiLama;
    private BigDecimal pendidikanUmkNilaiLama;
    private BigDecimal pendidikanStrukturalNilaiLama;
    private BigDecimal pendidikanJabStrukturalNilaiLama;
    private BigDecimal pendidikanStrategisNilaiLama;
    private BigDecimal pendidikanAirListrikNilaiLama;
    private BigDecimal pendidikanKompensasiNilaiLama;

    private String pendidikanGajiGolonganBaru;
    private String pendidikanPeralihanBaru;
    private String pendidikanUmkBaru;
    private String pendidikanStrukturalBaru;
    private String pendidikanJabStrukturalBaru;
    private String pendidikanStrategisBaru;
    private String pendidikanAirListrikBaru;
    private String pendidikanKompensasiBaru;
    private String totalRapelBaru;

    private String pendidikanGajiGolonganLama;
    private String pendidikanPeralihanLama;
    private String pendidikanUmkLama;
    private String pendidikanStrukturalLama;
    private String pendidikanJabStrukturalLama;
    private String pendidikanStrategisLama;
    private String pendidikanKompensasiLama;
    private String pendidikanAirListrikLama;

    private BigDecimal pendidikanGajiGolonganNilaiSelisihBaru;
    private BigDecimal pendidikanPeralihanNilaiSelisihBaru;
    private BigDecimal pendidikanUmkNilaiSelisihBaru;
    private BigDecimal pendidikanStrukturalNilaiSelisihBaru;
    private BigDecimal pendidikanJabStrukturalNilaiSelisihBaru;
    private BigDecimal pendidikanStrategisNilaiSelisihBaru;
    private BigDecimal pendidikanAirListrikNilaiSelisihBaru;
    private BigDecimal pendidikanKompensasiNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisih;

    private String pendidikanGajiGolonganSelisihBaru;
    private String pendidikanPeralihanSelisihBaru;
    private String pendidikanUmkSelisihBaru;
    private String pendidikanStrukturalSelisihBaru;
    private String pendidikanJabStrukturalSelisihBaru;
    private String pendidikanStrategisSelisihBaru;
    private String pendidikanAirListrikSelisihBaru;
    private String pendidikanKompensasiSelisihBaru;
    private String totalRapelSelisihBaru;
    private String totalRapelSelisih;

    private String golonganName;
    private String golonganNameLama;
    private String payrollId;
    private String statusKeluarga;
    private String nama;
    private String rapelId;
    private String pendidikanId;
    private int jumlahAnak;
    private int point;
    private int pointLama;

    public BigDecimal getPendidikanKompensasiNilaiBaru() {
        return pendidikanKompensasiNilaiBaru;
    }

    public void setPendidikanKompensasiNilaiBaru(BigDecimal pendidikanKompensasiNilaiBaru) {
        this.pendidikanKompensasiNilaiBaru = pendidikanKompensasiNilaiBaru;
    }

    public BigDecimal getPendidikanKompensasiNilaiLama() {
        return pendidikanKompensasiNilaiLama;
    }

    public void setPendidikanKompensasiNilaiLama(BigDecimal pendidikanKompensasiNilaiLama) {
        this.pendidikanKompensasiNilaiLama = pendidikanKompensasiNilaiLama;
    }

    public String getPendidikanKompensasiBaru() {
        return pendidikanKompensasiBaru;
    }

    public void setPendidikanKompensasiBaru(String pendidikanKompensasiBaru) {
        this.pendidikanKompensasiBaru = pendidikanKompensasiBaru;
    }

    public String getPendidikanKompensasiLama() {
        return pendidikanKompensasiLama;
    }

    public void setPendidikanKompensasiLama(String pendidikanKompensasiLama) {
        this.pendidikanKompensasiLama = pendidikanKompensasiLama;
    }

    public BigDecimal getPendidikanKompensasiNilaiSelisihBaru() {
        return pendidikanKompensasiNilaiSelisihBaru;
    }

    public void setPendidikanKompensasiNilaiSelisihBaru(BigDecimal pendidikanKompensasiNilaiSelisihBaru) {
        this.pendidikanKompensasiNilaiSelisihBaru = pendidikanKompensasiNilaiSelisihBaru;
    }

    public String getPendidikanKompensasiSelisihBaru() {
        return pendidikanKompensasiSelisihBaru;
    }

    public void setPendidikanKompensasiSelisihBaru(String pendidikanKompensasiSelisihBaru) {
        this.pendidikanKompensasiSelisihBaru = pendidikanKompensasiSelisihBaru;
    }

    public BigDecimal getTotalRapelNilaiSelisih() {
        return totalRapelNilaiSelisih;
    }

    public void setTotalRapelNilaiSelisih(BigDecimal totalRapelNilaiSelisih) {
        this.totalRapelNilaiSelisih = totalRapelNilaiSelisih;
    }

    public String getTotalRapelSelisih() {
        return totalRapelSelisih;
    }

    public void setTotalRapelSelisih(String totalRapelSelisih) {
        this.totalRapelSelisih = totalRapelSelisih;
    }

    public int getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(int bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public BigDecimal getPendidikanPeralihanNilaiBaru() {
        return pendidikanPeralihanNilaiBaru;
    }

    public void setPendidikanPeralihanNilaiBaru(BigDecimal pendidikanPeralihanNilaiBaru) {
        this.pendidikanPeralihanNilaiBaru = pendidikanPeralihanNilaiBaru;
    }

    public BigDecimal getPendidikanPeralihanNilaiLama() {
        return pendidikanPeralihanNilaiLama;
    }

    public void setPendidikanPeralihanNilaiLama(BigDecimal pendidikanPeralihanNilaiLama) {
        this.pendidikanPeralihanNilaiLama = pendidikanPeralihanNilaiLama;
    }

    public String getPendidikanPeralihanSelisihBaru() {
        return pendidikanPeralihanSelisihBaru;
    }

    public void setPendidikanPeralihanSelisihBaru(String pendidikanPeralihanSelisihBaru) {
        this.pendidikanPeralihanSelisihBaru = pendidikanPeralihanSelisihBaru;
    }

    public String getPendidikanPeralihanBaru() {
        return pendidikanPeralihanBaru;
    }

    public void setPendidikanPeralihanBaru(String pendidikanPeralihanBaru) {
        this.pendidikanPeralihanBaru = pendidikanPeralihanBaru;
    }

    public String getPendidikanPeralihanLama() {
        return pendidikanPeralihanLama;
    }

    public void setPendidikanPeralihanLama(String pendidikanPeralihanLama) {
        this.pendidikanPeralihanLama = pendidikanPeralihanLama;
    }

    public BigDecimal getPendidikanPeralihanNilaiSelisihBaru() {
        return pendidikanPeralihanNilaiSelisihBaru;
    }

    public void setPendidikanPeralihanNilaiSelisihBaru(BigDecimal pendidikanPeralihanNilaiSelisihBaru) {
        this.pendidikanPeralihanNilaiSelisihBaru = pendidikanPeralihanNilaiSelisihBaru;
    }

    public String getGolonganNameLama() {
        return golonganNameLama;
    }

    public void setGolonganNameLama(String golonganNameLama) {
        this.golonganNameLama = golonganNameLama;
    }

    public int getPointLama() {
        return pointLama;
    }

    public void setPointLama(int pointLama) {
        this.pointLama = pointLama;
    }

    public BigDecimal getPendidikanGajiGolonganNilaiLama() {
        return pendidikanGajiGolonganNilaiLama;
    }

    public void setPendidikanGajiGolonganNilaiLama(BigDecimal pendidikanGajiGolonganNilaiLama) {
        this.pendidikanGajiGolonganNilaiLama = pendidikanGajiGolonganNilaiLama;
    }

    public BigDecimal getPendidikanUmkNilaiLama() {
        return pendidikanUmkNilaiLama;
    }

    public void setPendidikanUmkNilaiLama(BigDecimal pendidikanUmkNilaiLama) {
        this.pendidikanUmkNilaiLama = pendidikanUmkNilaiLama;
    }

    public BigDecimal getPendidikanStrukturalNilaiLama() {
        return pendidikanStrukturalNilaiLama;
    }

    public void setPendidikanStrukturalNilaiLama(BigDecimal pendidikanStrukturalNilaiLama) {
        this.pendidikanStrukturalNilaiLama = pendidikanStrukturalNilaiLama;
    }

    public BigDecimal getPendidikanJabStrukturalNilaiLama() {
        return pendidikanJabStrukturalNilaiLama;
    }

    public void setPendidikanJabStrukturalNilaiLama(BigDecimal pendidikanJabStrukturalNilaiLama) {
        this.pendidikanJabStrukturalNilaiLama = pendidikanJabStrukturalNilaiLama;
    }

    public BigDecimal getPendidikanStrategisNilaiLama() {
        return pendidikanStrategisNilaiLama;
    }

    public void setPendidikanStrategisNilaiLama(BigDecimal pendidikanStrategisNilaiLama) {
        this.pendidikanStrategisNilaiLama = pendidikanStrategisNilaiLama;
    }

    public BigDecimal getPendidikanAirListrikNilaiLama() {
        return pendidikanAirListrikNilaiLama;
    }

    public void setPendidikanAirListrikNilaiLama(BigDecimal pendidikanAirListrikNilaiLama) {
        this.pendidikanAirListrikNilaiLama = pendidikanAirListrikNilaiLama;
    }

    public String getRapelId() {
        return rapelId;
    }

    public void setRapelId(String rapelId) {
        this.rapelId = rapelId;
    }

    public String getPendidikanId() {
        return pendidikanId;
    }

    public void setPendidikanId(String pendidikanId) {
        this.pendidikanId = pendidikanId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
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

    public String getPayrollRapelId() {
        return payrollRapelId;
    }

    public void setPayrollRapelId(String payrollRapelId) {
        this.payrollRapelId = payrollRapelId;
    }

    public String getPendidikanGajiGolonganLama() {
        return pendidikanGajiGolonganLama;
    }

    public void setPendidikanGajiGolonganLama(String pendidikanGajiGolonganLama) {
        this.pendidikanGajiGolonganLama = pendidikanGajiGolonganLama;
    }

    public String getPendidikanUmkLama() {
        return pendidikanUmkLama;
    }

    public void setPendidikanUmkLama(String pendidikanUmkLama) {
        this.pendidikanUmkLama = pendidikanUmkLama;
    }

    public String getPendidikanStrukturalLama() {
        return pendidikanStrukturalLama;
    }

    public void setPendidikanStrukturalLama(String pendidikanStrukturalLama) {
        this.pendidikanStrukturalLama = pendidikanStrukturalLama;
    }

    public String getPendidikanJabStrukturalLama() {
        return pendidikanJabStrukturalLama;
    }

    public void setPendidikanJabStrukturalLama(String pendidikanJabStrukturalLama) {
        this.pendidikanJabStrukturalLama = pendidikanJabStrukturalLama;
    }

    public String getPendidikanStrategisLama() {
        return pendidikanStrategisLama;
    }

    public void setPendidikanStrategisLama(String pendidikanStrategisLama) {
        this.pendidikanStrategisLama = pendidikanStrategisLama;
    }

    public String getPendidikanAirListrikLama() {
        return pendidikanAirListrikLama;
    }

    public void setPendidikanAirListrikLama(String pendidikanAirListrikLama) {
        this.pendidikanAirListrikLama = pendidikanAirListrikLama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getPendidikanStrukturalNilaiBaru() {
        return pendidikanStrukturalNilaiBaru;
    }

    public void setPendidikanStrukturalNilaiBaru(BigDecimal pendidikanStrukturalNilaiBaru) {
        this.pendidikanStrukturalNilaiBaru = pendidikanStrukturalNilaiBaru;
    }

    public String getRapelPendidikanId() {
        return rapelPendidikanId;
    }

    public void setRapelPendidikanId(String rapelPendidikanId) {
        this.rapelPendidikanId = rapelPendidikanId;
    }

    public String getPayrollPendidikanId() {
        return payrollPendidikanId;
    }

    public void setPayrollPendidikanId(String payrollPendidikanId) {
        this.payrollPendidikanId = payrollPendidikanId;
    }

    public BigDecimal getPendidikanGajiGolonganNilaiBaru() {
        return pendidikanGajiGolonganNilaiBaru;
    }

    public void setPendidikanGajiGolonganNilaiBaru(BigDecimal pendidikanGajiGolonganNilaiBaru) {
        this.pendidikanGajiGolonganNilaiBaru = pendidikanGajiGolonganNilaiBaru;
    }

    public BigDecimal getPendidikanUmkNilaiBaru() {
        return pendidikanUmkNilaiBaru;
    }

    public void setPendidikanUmkNilaiBaru(BigDecimal pendidikanUmkNilaiBaru) {
        this.pendidikanUmkNilaiBaru = pendidikanUmkNilaiBaru;
    }

    public BigDecimal getPendidikanJabStrukturalNilaiBaru() {
        return pendidikanJabStrukturalNilaiBaru;
    }

    public void setPendidikanJabStrukturalNilaiBaru(BigDecimal pendidikanJabStrukturalNilaiBaru) {
        this.pendidikanJabStrukturalNilaiBaru = pendidikanJabStrukturalNilaiBaru;
    }

    public BigDecimal getPendidikanStrategisNilaiBaru() {
        return pendidikanStrategisNilaiBaru;
    }

    public void setPendidikanStrategisNilaiBaru(BigDecimal pendidikanStrategisNilaiBaru) {
        this.pendidikanStrategisNilaiBaru = pendidikanStrategisNilaiBaru;
    }

    public BigDecimal getPendidikanAirListrikNilaiBaru() {
        return pendidikanAirListrikNilaiBaru;
    }

    public void setPendidikanAirListrikNilaiBaru(BigDecimal pendidikanAirListrikNilaiBaru) {
        this.pendidikanAirListrikNilaiBaru = pendidikanAirListrikNilaiBaru;
    }

    public BigDecimal getTotalRapelNilaiBaru() {
        return totalRapelNilaiBaru;
    }

    public void setTotalRapelNilaiBaru(BigDecimal totalRapelNilaiBaru) {
        this.totalRapelNilaiBaru = totalRapelNilaiBaru;
    }

    public String getPendidikanGajiGolonganBaru() {
        return pendidikanGajiGolonganBaru;
    }

    public void setPendidikanGajiGolonganBaru(String pendidikanGajiGolonganBaru) {
        this.pendidikanGajiGolonganBaru = pendidikanGajiGolonganBaru;
    }

    public String getPendidikanUmkBaru() {
        return pendidikanUmkBaru;
    }

    public void setPendidikanUmkBaru(String pendidikanUmkBaru) {
        this.pendidikanUmkBaru = pendidikanUmkBaru;
    }

    public String getPendidikanStrukturalBaru() {
        return pendidikanStrukturalBaru;
    }

    public void setPendidikanStrukturalBaru(String pendidikanStrukturalBaru) {
        this.pendidikanStrukturalBaru = pendidikanStrukturalBaru;
    }

    public String getPendidikanJabStrukturalBaru() {
        return pendidikanJabStrukturalBaru;
    }

    public void setPendidikanJabStrukturalBaru(String pendidikanJabStrukturalBaru) {
        this.pendidikanJabStrukturalBaru = pendidikanJabStrukturalBaru;
    }

    public String getPendidikanStrategisBaru() {
        return pendidikanStrategisBaru;
    }

    public void setPendidikanStrategisBaru(String pendidikanStrategisBaru) {
        this.pendidikanStrategisBaru = pendidikanStrategisBaru;
    }

    public String getPendidikanAirListrikBaru() {
        return pendidikanAirListrikBaru;
    }

    public void setPendidikanAirListrikBaru(String pendidikanAirListrikBaru) {
        this.pendidikanAirListrikBaru = pendidikanAirListrikBaru;
    }

    public String getTotalRapelBaru() {
        return totalRapelBaru;
    }

    public void setTotalRapelBaru(String totalRapelBaru) {
        this.totalRapelBaru = totalRapelBaru;
    }

    public BigDecimal getPendidikanGajiGolonganNilaiSelisihBaru() {
        return pendidikanGajiGolonganNilaiSelisihBaru;
    }

    public void setPendidikanGajiGolonganNilaiSelisihBaru(BigDecimal pendidikanGajiGolonganNilaiSelisihBaru) {
        this.pendidikanGajiGolonganNilaiSelisihBaru = pendidikanGajiGolonganNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanUmkNilaiSelisihBaru() {
        return pendidikanUmkNilaiSelisihBaru;
    }

    public void setPendidikanUmkNilaiSelisihBaru(BigDecimal pendidikanUmkNilaiSelisihBaru) {
        this.pendidikanUmkNilaiSelisihBaru = pendidikanUmkNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanStrukturalNilaiSelisihBaru() {
        return pendidikanStrukturalNilaiSelisihBaru;
    }

    public void setPendidikanStrukturalNilaiSelisihBaru(BigDecimal pendidikanStrukturalNilaiSelisihBaru) {
        this.pendidikanStrukturalNilaiSelisihBaru = pendidikanStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanJabStrukturalNilaiSelisihBaru() {
        return pendidikanJabStrukturalNilaiSelisihBaru;
    }

    public void setPendidikanJabStrukturalNilaiSelisihBaru(BigDecimal pendidikanJabStrukturalNilaiSelisihBaru) {
        this.pendidikanJabStrukturalNilaiSelisihBaru = pendidikanJabStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanStrategisNilaiSelisihBaru() {
        return pendidikanStrategisNilaiSelisihBaru;
    }

    public void setPendidikanStrategisNilaiSelisihBaru(BigDecimal pendidikanStrategisNilaiSelisihBaru) {
        this.pendidikanStrategisNilaiSelisihBaru = pendidikanStrategisNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanAirListrikNilaiSelisihBaru() {
        return pendidikanAirListrikNilaiSelisihBaru;
    }

    public void setPendidikanAirListrikNilaiSelisihBaru(BigDecimal pendidikanAirListrikNilaiSelisihBaru) {
        this.pendidikanAirListrikNilaiSelisihBaru = pendidikanAirListrikNilaiSelisihBaru;
    }

    public BigDecimal getTotalRapelNilaiSelisihBaru() {
        return totalRapelNilaiSelisihBaru;
    }

    public void setTotalRapelNilaiSelisihBaru(BigDecimal totalRapelNilaiSelisihBaru) {
        this.totalRapelNilaiSelisihBaru = totalRapelNilaiSelisihBaru;
    }

    public String getPendidikanGajiGolonganSelisihBaru() {
        return pendidikanGajiGolonganSelisihBaru;
    }

    public void setPendidikanGajiGolonganSelisihBaru(String pendidikanGajiGolonganSelisihBaru) {
        this.pendidikanGajiGolonganSelisihBaru = pendidikanGajiGolonganSelisihBaru;
    }

    public String getPendidikanUmkSelisihBaru() {
        return pendidikanUmkSelisihBaru;
    }

    public void setPendidikanUmkSelisihBaru(String pendidikanUmkSelisihBaru) {
        this.pendidikanUmkSelisihBaru = pendidikanUmkSelisihBaru;
    }

    public String getPendidikanStrukturalSelisihBaru() {
        return pendidikanStrukturalSelisihBaru;
    }

    public void setPendidikanStrukturalSelisihBaru(String pendidikanStrukturalSelisihBaru) {
        this.pendidikanStrukturalSelisihBaru = pendidikanStrukturalSelisihBaru;
    }

    public String getPendidikanJabStrukturalSelisihBaru() {
        return pendidikanJabStrukturalSelisihBaru;
    }

    public void setPendidikanJabStrukturalSelisihBaru(String pendidikanJabStrukturalSelisihBaru) {
        this.pendidikanJabStrukturalSelisihBaru = pendidikanJabStrukturalSelisihBaru;
    }

    public String getPendidikanStrategisSelisihBaru() {
        return pendidikanStrategisSelisihBaru;
    }

    public void setPendidikanStrategisSelisihBaru(String pendidikanStrategisSelisihBaru) {
        this.pendidikanStrategisSelisihBaru = pendidikanStrategisSelisihBaru;
    }

    public String getPendidikanAirListrikSelisihBaru() {
        return pendidikanAirListrikSelisihBaru;
    }

    public void setPendidikanAirListrikSelisihBaru(String pendidikanAirListrikSelisihBaru) {
        this.pendidikanAirListrikSelisihBaru = pendidikanAirListrikSelisihBaru;
    }

    public String getTotalRapelSelisihBaru() {
        return totalRapelSelisihBaru;
    }

    public void setTotalRapelSelisihBaru(String totalRapelSelisihBaru) {
        this.totalRapelSelisihBaru = totalRapelSelisihBaru;
    }
}