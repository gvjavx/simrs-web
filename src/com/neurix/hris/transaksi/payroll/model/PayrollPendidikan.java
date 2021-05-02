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

public class PayrollPendidikan extends BaseModel {
    private String pendidikanId;
    private String payrollId;
    
    private BigDecimal gajiNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private BigDecimal tunjanganStrategisNilai;
    private BigDecimal tunjanganKompensasiNilai;
    private BigDecimal tunjanganAirListrikNilai;
    private BigDecimal tunjanganPphNilai;
    private BigDecimal tunjanganPeralihanNilai;

    private String tunjanganPeralihan;
    private String gaji;
    private String tunjanganUmk;
    private String tunjanganStruktural;
    private String tunjanganJabatanStruktural;
    private String tunjanganStrategis;
    private String tunjanganKompensasi;
    private String tunjanganAirListrik;
    private String tunjanganPph;

    private String flagPendidikan;
    private String nip;
    private String totalPendidikan;
    private String totalPendidikanBersih;
    private int bulanAktif;
    private BigDecimal totalPendidikanNilai;
    private BigDecimal totalPendidikanBersihNilai;

    public BigDecimal getTunjanganPeralihanNilai() {
        return tunjanganPeralihanNilai;
    }

    public void setTunjanganPeralihanNilai(BigDecimal tunjanganPeralihanNilai) {
        this.tunjanganPeralihanNilai = tunjanganPeralihanNilai;
    }

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(String tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public String getTotalPendidikanBersih() {
        return totalPendidikanBersih;
    }

    public void setTotalPendidikanBersih(String totalPendidikanBersih) {
        this.totalPendidikanBersih = totalPendidikanBersih;
    }

    public BigDecimal getTotalPendidikanBersihNilai() {
        return totalPendidikanBersihNilai;
    }

    public void setTotalPendidikanBersihNilai(BigDecimal totalPendidikanBersihNilai) {
        this.totalPendidikanBersihNilai = totalPendidikanBersihNilai;
    }

    public int getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(int bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public String getFlagPendidikan() {
        return flagPendidikan;
    }

    public void setFlagPendidikan(String flagPendidikan) {
        this.flagPendidikan = flagPendidikan;
    }

    public BigDecimal getGajiNilai() {
        return gajiNilai;
    }

    public void setGajiNilai(BigDecimal gajiNilai) {
        this.gajiNilai = gajiNilai;
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

    public BigDecimal getTunjanganStrategisNilai() {
        return tunjanganStrategisNilai;
    }

    public void setTunjanganStrategisNilai(BigDecimal tunjanganStrategisNilai) {
        this.tunjanganStrategisNilai = tunjanganStrategisNilai;
    }

    public BigDecimal getTunjanganKompensasiNilai() {
        return tunjanganKompensasiNilai;
    }

    public void setTunjanganKompensasiNilai(BigDecimal tunjanganKompensasiNilai) {
        this.tunjanganKompensasiNilai = tunjanganKompensasiNilai;
    }

    public BigDecimal getTunjanganAirListrikNilai() {
        return tunjanganAirListrikNilai;
    }

    public void setTunjanganAirListrikNilai(BigDecimal tunjanganAirListrikNilai) {
        this.tunjanganAirListrikNilai = tunjanganAirListrikNilai;
    }

    public BigDecimal getTunjanganPphNilai() {
        return tunjanganPphNilai;
    }

    public void setTunjanganPphNilai(BigDecimal tunjanganPphNilai) {
        this.tunjanganPphNilai = tunjanganPphNilai;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public void setTunjanganUmk(String tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public void setTunjanganStruktural(String tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public void setTunjanganJabatanStruktural(String tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public void setTunjanganStrategis(String tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public void setTunjanganKompensasi(String tunjanganKompensasi) {
        this.tunjanganKompensasi = tunjanganKompensasi;
    }

    public void setTunjanganAirListrik(String tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
    }

    public void setTunjanganPph(String tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTotalPendidikan() {
        return totalPendidikan;
    }

    public void setTotalPendidikan(String totalPendidikan) {
        this.totalPendidikan = totalPendidikan;
    }

    public BigDecimal getTotalPendidikanNilai() {
        return totalPendidikanNilai;
    }

    public void setTotalPendidikanNilai(BigDecimal totalPendidikanNilai) {
        this.totalPendidikanNilai = totalPendidikanNilai;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getPendidikanId() {
        return pendidikanId;
    }

    public void setPendidikanId(String pendidikanId) {
        this.pendidikanId = pendidikanId;
    }

    public String getTunjanganUmk() {
        return tunjanganUmk;
    }

    public String getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public String getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public String getTunjanganKompensasi() {
        return tunjanganKompensasi;
    }

    public String getTunjanganPph() {
        return tunjanganPph;
    }

    public String getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public String getTunjanganStruktural() {
        return tunjanganStruktural;
    }
}