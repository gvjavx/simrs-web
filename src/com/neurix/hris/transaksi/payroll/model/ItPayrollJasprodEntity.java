package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollJasprodEntity implements Serializable {
    private String jasprodId;
    private String payrollId;
    private BigDecimal gajiGolonganNilai;
    private int masaKerja;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganJabStrukturalNilai;
    private BigDecimal tunjPeralihanNilai;
    private BigDecimal tunjanganStrategisNilai;
    private BigDecimal gajiBruto;

    private BigDecimal gajiMasaKerja;
    private BigDecimal faktorKali;
    private BigDecimal nilaiSmk;
    private BigDecimal persenSmk;
    private BigDecimal gajiMasaKerjaFaktorSmk;
    private BigDecimal gajiMasaKerjaFaktor;
    private BigDecimal tambahan;
    private BigDecimal bruto;
    private BigDecimal selisihTotalGajiSmkFaktor;
    private BigDecimal jumlahPersenSmk;
    private BigDecimal pphJasprod;
    private BigDecimal finalJasprodBersih;
    private BigDecimal potKoperasi;
    private BigDecimal potTaliAsih;
    private BigDecimal potLain;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public BigDecimal getPotKoperasi() {
        return potKoperasi;
    }

    public void setPotKoperasi(BigDecimal potKoperasi) {
        this.potKoperasi = potKoperasi;
    }

    public BigDecimal getPotTaliAsih() {
        return potTaliAsih;
    }

    public void setPotTaliAsih(BigDecimal potTaliAsih) {
        this.potTaliAsih = potTaliAsih;
    }

    public BigDecimal getPotLain() {
        return potLain;
    }

    public void setPotLain(BigDecimal potLain) {
        this.potLain = potLain;
    }

    public BigDecimal getFinalJasprodBersih() {
        return finalJasprodBersih;
    }

    public void setFinalJasprodBersih(BigDecimal finalJasprodBersih) {
        this.finalJasprodBersih = finalJasprodBersih;
    }

    public BigDecimal getPphJasprod() {
        return pphJasprod;
    }

    public void setPphJasprod(BigDecimal pphJasprod) {
        this.pphJasprod = pphJasprod;
    }

    public BigDecimal getGajiBruto() {
        return gajiBruto;
    }

    public void setGajiBruto(BigDecimal gajiBruto) {
        this.gajiBruto = gajiBruto;
    }

    public BigDecimal getTunjanganJabStrukturalNilai() {
        return tunjanganJabStrukturalNilai;
    }

    public void setTunjanganJabStrukturalNilai(BigDecimal tunjanganJabStrukturalNilai) {
        this.tunjanganJabStrukturalNilai = tunjanganJabStrukturalNilai;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(BigDecimal faktorKali) {
        this.faktorKali = faktorKali;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public BigDecimal getGajiMasaKerja() {
        return gajiMasaKerja;
    }

    public void setGajiMasaKerja(BigDecimal gajiMasaKerja) {
        this.gajiMasaKerja = gajiMasaKerja;
    }

    public BigDecimal getGajiMasaKerjaFaktor() {
        return gajiMasaKerjaFaktor;
    }

    public void setGajiMasaKerjaFaktor(BigDecimal gajiMasaKerjaFaktor) {
        this.gajiMasaKerjaFaktor = gajiMasaKerjaFaktor;
    }

    public BigDecimal getGajiMasaKerjaFaktorSmk() {
        return gajiMasaKerjaFaktorSmk;
    }

    public void setGajiMasaKerjaFaktorSmk(BigDecimal gajiMasaKerjaFaktorSmk) {
        this.gajiMasaKerjaFaktorSmk = gajiMasaKerjaFaktorSmk;
    }

    public String getJasprodId() {
        return jasprodId;
    }

    public void setJasprodId(String jasprodId) {
        this.jasprodId = jasprodId;
    }

    public BigDecimal getJumlahPersenSmk() {
        return jumlahPersenSmk;
    }

    public void setJumlahPersenSmk(BigDecimal jumlahPersenSmk) {
        this.jumlahPersenSmk = jumlahPersenSmk;
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

    public int getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(int masaKerja) {
        this.masaKerja = masaKerja;
    }

    public BigDecimal getNilaiSmk() {
        return nilaiSmk;
    }

    public void setNilaiSmk(BigDecimal nilaiSmk) {
        this.nilaiSmk = nilaiSmk;
    }

    public BigDecimal getPersenSmk() {
        return persenSmk;
    }

    public void setPersenSmk(BigDecimal persenSmk) {
        this.persenSmk = persenSmk;
    }

    public BigDecimal getSelisihTotalGajiSmkFaktor() {
        return selisihTotalGajiSmkFaktor;
    }

    public void setSelisihTotalGajiSmkFaktor(BigDecimal selisihTotalGajiSmkFaktor) {
        this.selisihTotalGajiSmkFaktor = selisihTotalGajiSmkFaktor;
    }

    public BigDecimal getTambahan() {
        return tambahan;
    }

    public void setTambahan(BigDecimal tambahan) {
        this.tambahan = tambahan;
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

    public BigDecimal getTunjanganUmkNilai() {
        return tunjanganUmkNilai;
    }

    public void setTunjanganUmkNilai(BigDecimal tunjanganUmkNilai) {
        this.tunjanganUmkNilai = tunjanganUmkNilai;
    }

    public BigDecimal getTunjPeralihanNilai() {
        return tunjPeralihanNilai;
    }

    public void setTunjPeralihanNilai(BigDecimal tunjPeralihanNilai) {
        this.tunjPeralihanNilai = tunjPeralihanNilai;
    }
}
