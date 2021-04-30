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

public class PayrollJasprod extends BaseModel {
    private String jasprodId;
    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganJabStrukturalNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganStrategisNilai;
    private BigDecimal tunjPeralihanNilai;
    private BigDecimal gajiBrutoNilai;
    private BigDecimal gajiMasaKerjaNilai;
    private BigDecimal selisihGajiSmkFaktor;
    private BigDecimal jumlahPersenSmk;
    private BigDecimal totalJasprodNilai;

    private String gajiBruto;
    private String gajiMasaKerja;
    private String totalJasprod;
    private String gajiGolongan;
    private String tunjanganUmk;
    private String tunjanganJabStruktural;
    private String tunjanganStruktural;
    private String tunjanganStrategis;
    private String tunjPeralihan;
    private String perhitungan;
    private String gajiMasaKerjaFaktor;
    private String tambahan;
    private String nilaiJasprod;
    private String finalNilaiJasprod;
    private String strPotKoperasi;
    private String strPotTaliAsih;
    private String strPotLain;

    private String nip;
    private int masaKerja;
    private String golongan;
    private String point;
    private String flagJasprod;
    private BigDecimal faktor;
    private String stFaktor;
    private String stNilaiSmk;
    private String stPersenSmk;
    private double nilaiSmk;
    private double persenSmk;
    private BigDecimal potKoperasi;
    private BigDecimal potTaliAsih;
    private BigDecimal potLain;
    private BigDecimal perhitunganNilai;
    private BigDecimal gajiMasaKerjaFaktorNilai;
    private BigDecimal tambahanNilai;
    private BigDecimal jasprodNilai;
    private BigDecimal jasprodNilaiFinal;

    private BigDecimal pphJasprod;
    private BigDecimal finalJasprod;

    public String getStrPotKoperasi() {
        return strPotKoperasi;
    }

    public void setStrPotKoperasi(String strPotKoperasi) {
        this.strPotKoperasi = strPotKoperasi;
    }

    public String getStrPotTaliAsih() {
        return strPotTaliAsih;
    }

    public void setStrPotTaliAsih(String strPotTaliAsih) {
        this.strPotTaliAsih = strPotTaliAsih;
    }

    public String getStrPotLain() {
        return strPotLain;
    }

    public void setStrPotLain(String strPotLain) {
        this.strPotLain = strPotLain;
    }

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

    public BigDecimal getPphJasprod() {
        return pphJasprod;
    }

    public void setPphJasprod(BigDecimal pphJasprod) {
        this.pphJasprod = pphJasprod;
    }

    public BigDecimal getFinalJasprod() {
        return finalJasprod;
    }

    public void setFinalJasprod(BigDecimal finalJasprod) {
        this.finalJasprod = finalJasprod;
    }

    public BigDecimal getTunjanganJabStrukturalNilai() {
        return tunjanganJabStrukturalNilai;
    }

    public void setTunjanganJabStrukturalNilai(BigDecimal tunjanganJabStrukturalNilai) {
        this.tunjanganJabStrukturalNilai = tunjanganJabStrukturalNilai;
    }

    public String getTunjanganJabStruktural() {
        return tunjanganJabStruktural;
    }

    public void setTunjanganJabStruktural(String tunjanganJabStruktural) {
        this.tunjanganJabStruktural = tunjanganJabStruktural;
    }

    public String getFinalNilaiJasprod() {
        return finalNilaiJasprod;
    }

    public void setFinalNilaiJasprod(String finalNilaiJasprod) {
        this.finalNilaiJasprod = finalNilaiJasprod;
    }

    public BigDecimal getJasprodNilaiFinal() {
        return jasprodNilaiFinal;
    }

    public void setJasprodNilaiFinal(BigDecimal jasprodNilaiFinal) {
        this.jasprodNilaiFinal = jasprodNilaiFinal;
    }

    public String getStFaktor() {
        return stFaktor;
    }

    public void setStFaktor(String stFaktor) {
        this.stFaktor = stFaktor;
    }

    public String getStNilaiSmk() {
        return stNilaiSmk;
    }

    public void setStNilaiSmk(String stNilaiSmk) {
        this.stNilaiSmk = stNilaiSmk;
    }

    public String getStPersenSmk() {
        return stPersenSmk;
    }

    public void setStPersenSmk(String stPersenSmk) {
        this.stPersenSmk = stPersenSmk;
    }

    public BigDecimal getJumlahPersenSmk() {
        return jumlahPersenSmk;
    }

    public void setJumlahPersenSmk(BigDecimal jumlahPersenSmk) {
        this.jumlahPersenSmk = jumlahPersenSmk;
    }

    public BigDecimal getSelisihGajiSmkFaktor() {
        return selisihGajiSmkFaktor;
    }

    public void setSelisihGajiSmkFaktor(BigDecimal selisihGajiSmkFaktor) {
        this.selisihGajiSmkFaktor = selisihGajiSmkFaktor;
    }

    public String getGajiMasaKerjaFaktor() {
        return gajiMasaKerjaFaktor;
    }

    public void setGajiMasaKerjaFaktor(String gajiMasaKerjaFaktor) {
        this.gajiMasaKerjaFaktor = gajiMasaKerjaFaktor;
    }

    public BigDecimal getGajiMasaKerjaFaktorNilai() {
        return gajiMasaKerjaFaktorNilai;
    }

    public void setGajiMasaKerjaFaktorNilai(BigDecimal gajiMasaKerjaFaktorNilai) {
        this.gajiMasaKerjaFaktorNilai = gajiMasaKerjaFaktorNilai;
    }

    public BigDecimal getJasprodNilai() {
        return jasprodNilai;
    }

    public void setJasprodNilai(BigDecimal jasprodNilai) {
        this.jasprodNilai = jasprodNilai;
    }

    public String getNilaiJasprod() {
        return nilaiJasprod;
    }

    public void setNilaiJasprod(String nilaiJasprod) {
        this.nilaiJasprod = nilaiJasprod;
    }

    public String getPerhitungan() {
        return perhitungan;
    }

    public void setPerhitungan(String perhitungan) {
        this.perhitungan = perhitungan;
    }

    public BigDecimal getPerhitunganNilai() {
        return perhitunganNilai;
    }

    public void setPerhitunganNilai(BigDecimal perhitunganNilai) {
        this.perhitunganNilai = perhitunganNilai;
    }

    public String getTambahan() {
        return tambahan;
    }

    public void setTambahan(String tambahan) {
        this.tambahan = tambahan;
    }

    public BigDecimal getTambahanNilai() {
        return tambahanNilai;
    }

    public void setTambahanNilai(BigDecimal tambahanNilai) {
        this.tambahanNilai = tambahanNilai;
    }

    public double getPersenSmk() {
        return persenSmk;
    }

    public void setPersenSmk(double persenSmk) {
        this.persenSmk = persenSmk;
    }

    public String getGajiBruto() {
        return gajiBruto;
    }

    public void setGajiBruto(String gajiBruto) {
        this.gajiBruto = gajiBruto;
    }

    public BigDecimal getGajiBrutoNilai() {
        return gajiBrutoNilai;
    }

    public void setGajiBrutoNilai(BigDecimal gajiBrutoNilai) {
        this.gajiBrutoNilai = gajiBrutoNilai;
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

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public int getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(int masaKerja) {
        this.masaKerja = masaKerja;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getFlagJasprod() {
        return flagJasprod;
    }

    public void setFlagJasprod(String flagJasprod) {
        this.flagJasprod = flagJasprod;
    }

    public BigDecimal getTotalJasprodNilai() {
        return totalJasprodNilai;
    }

    public void setTotalJasprodNilai(BigDecimal totalJasprodNilai) {
        this.totalJasprodNilai = totalJasprodNilai;
    }

    public String getTotalJasprod() {
        return totalJasprod;
    }

    public void setTotalJasprod(String totalJasprod) {
        this.totalJasprod = totalJasprod;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getJasprodId() {
        return jasprodId;
    }

    public void setJasprodId(String jasprodId) {
        this.jasprodId = jasprodId;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
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

    public BigDecimal getTunjanganStrategisNilai() {
        return tunjanganStrategisNilai;
    }

    public void setTunjanganStrategisNilai(BigDecimal tunjanganStrategisNilai) {
        this.tunjanganStrategisNilai = tunjanganStrategisNilai;
    }

    public BigDecimal getTunjPeralihanNilai() {
        return tunjPeralihanNilai;
    }

    public void setTunjPeralihanNilai(BigDecimal tunjPeralihanNilai) {
        this.tunjPeralihanNilai = tunjPeralihanNilai;
    }

    public String getGajiGolongan() {
        return gajiGolongan;
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

    public String getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(String tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public String getTunjPeralihan() {
        return tunjPeralihan;
    }

    public void setTunjPeralihan(String tunjPeralihan) {
        this.tunjPeralihan = tunjPeralihan;
    }

    public BigDecimal getFaktor() {
        return faktor;
    }

    public void setFaktor(BigDecimal faktor) {
        this.faktor = faktor;
    }

    public double getNilaiSmk() {
        return nilaiSmk;
    }

    public void setNilaiSmk(double nilaiSmk) {
        this.nilaiSmk = nilaiSmk;
    }
}