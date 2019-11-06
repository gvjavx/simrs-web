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

public class PayrollPph extends BaseModel {
    private String pphId;
    private String nip;
    private String payrollId;
    private String ptkp;
    private BigDecimal ptkpNilai;
    private String gaji;
    private BigDecimal gajiNilai;
    private String tunjanganUmk;
    private BigDecimal tunjanganUmkNilai;
    private String tunjanganStrategis;
    private BigDecimal tunjanganStrategisNilai;
    private String tunjanganPeralihan;
    private BigDecimal tunjanganPeralihanNilai;
    private String tunjanganJabatanStruktural;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private String tunjanganStruktural;
    private BigDecimal tunjanganStrukturalNilai;
    private String tunjanganKompensasi;
    private BigDecimal tunjanganKompensasiNilai;
    private String tunjanganTransport;
    private BigDecimal tunjanganTransportNilai;
    private String tunjanganAirListrik;
    private BigDecimal tunjanganAirListrikNilai;
    private String tunjanganPerumahan;
    private BigDecimal tunjanganPerumahanNilai;
    private String tunjanganPengobatan;
    private BigDecimal tunjanganPengobatanNilai;
    private String tunjanganPphBulan;
    private BigDecimal tunjanganPphNilaiBulan;
    private String tunjanganPphTahun;
    private BigDecimal tunjanganPphNilaiTahun;
    private String tunjanganLembur;
    private BigDecimal tunjanganLemburNilai;
    private String tunjanganLainLain;
    private BigDecimal tunjanganLainLainNilai;
    private String thr;
    private BigDecimal thrNilai;
    private String pendidikan;
    private BigDecimal pendidikanNilai;
    private String jasprod;
    private BigDecimal jasprodNilai;
    private String insentif;
    private BigDecimal insentifNilai;
    private String asumsiThr;
    private BigDecimal asumsiThrNilai;
    private String asumsiPendidikan;
    private BigDecimal asumsiPendidikanNilai;
    private String asumsiJasprod;
    private BigDecimal asumsiJasprodNilai;
    private String Jubileum;
    private BigDecimal JubileumNilai;
    private String pakaianDinas;
    private BigDecimal pakaianDinasNilai;
    private String iuranJkmJkk;
    private BigDecimal iuranJkmJkkNilai;
    private String biayaJabatan;
    private BigDecimal biayaJabatanNilai;
    private String iuranPensiun;
    private BigDecimal iuranPensiunNilai;
    private String pkp;
    private BigDecimal pkpNilai;
    private String hutangPph;
    private BigDecimal hutangPphNilai;
    private String rapel ;
    private BigDecimal rapelNilai ;
    private String pensiun ;
    private BigDecimal pensiunNilai ;
    private String bruto ;
    private BigDecimal brutoNilai ;
    private String jumlahB;
    private BigDecimal jumlahBNilai ;
    private String pphGaji;
    private BigDecimal pphGajiNilai ;
    private String danaPensiun;
    private BigDecimal danaPensiunNilai;
    private String bpjsJht;
    private BigDecimal bpjsJhtNilai;
    private String bpjsPensiun;
    private BigDecimal bpjsPensiunNilai;

    private BigDecimal KalkulasiThr ;
    private BigDecimal kalkulasiPendidikan;
    private BigDecimal kalkulasiJasprod;

    private String centangKalkulasiPPhPengobatan;

    private BigDecimal jumlahPengobatanNilai;
    private String jumlahPengobatan;
    private BigDecimal jumlahPphPengobatanNilai;
    private String jumlahPphPengobatan;
    private BigDecimal hutangPphPengobatanNilai;
    private String hutangPphPengobatan;
    private BigDecimal kurangPphPengobatanNilai;
    private String kurangPphPengobatan;
    private BigDecimal pphPengobatanNilai;
    private String pphPengobatan;

    public String getCentangKalkulasiPPhPengobatan() {
        return centangKalkulasiPPhPengobatan;
    }

    public void setCentangKalkulasiPPhPengobatan(String centangKalkulasiPPhPengobatan) {
        this.centangKalkulasiPPhPengobatan = centangKalkulasiPPhPengobatan;
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

    public String getPphPengobatan() {
        return pphPengobatan;
    }

    public void setPphPengobatan(String pphPengobatan) {
        this.pphPengobatan = pphPengobatan;
    }

    public BigDecimal getPphPengobatanNilai() {
        return pphPengobatanNilai;
    }

    public void setPphPengobatanNilai(BigDecimal pphPengobatanNilai) {
        this.pphPengobatanNilai = pphPengobatanNilai;
    }

    public String getInsentif() {
        return insentif;
    }

    public void setInsentif(String insentif) {
        this.insentif = insentif;
    }

    public BigDecimal getInsentifNilai() {
        return insentifNilai;
    }

    public void setInsentifNilai(BigDecimal insentifNilai) {
        this.insentifNilai = insentifNilai;
    }

    public BigDecimal getKalkulasiThr() {
        return KalkulasiThr;
    }

    public void setKalkulasiThr(BigDecimal kalkulasiThr) {
        KalkulasiThr = kalkulasiThr;
    }

    public BigDecimal getKalkulasiPendidikan() {
        return kalkulasiPendidikan;
    }

    public void setKalkulasiPendidikan(BigDecimal kalkulasiPendidikan) {
        this.kalkulasiPendidikan = kalkulasiPendidikan;
    }

    public BigDecimal getKalkulasiJasprod() {
        return kalkulasiJasprod;
    }

    public void setKalkulasiJasprod(BigDecimal kalkulasiJasprod) {
        this.kalkulasiJasprod = kalkulasiJasprod;
    }

    public String getAsumsiThr() {
        return asumsiThr;
    }

    public void setAsumsiThr(String asumsiThr) {
        this.asumsiThr = asumsiThr;
    }

    public BigDecimal getAsumsiThrNilai() {
        return asumsiThrNilai;
    }

    public void setAsumsiThrNilai(BigDecimal asumsiThrNilai) {
        this.asumsiThrNilai = asumsiThrNilai;
    }

    public String getAsumsiPendidikan() {
        return asumsiPendidikan;
    }

    public void setAsumsiPendidikan(String asumsiPendidikan) {
        this.asumsiPendidikan = asumsiPendidikan;
    }

    public BigDecimal getAsumsiPendidikanNilai() {
        return asumsiPendidikanNilai;
    }

    public void setAsumsiPendidikanNilai(BigDecimal asumsiPendidikanNilai) {
        this.asumsiPendidikanNilai = asumsiPendidikanNilai;
    }

    public String getAsumsiJasprod() {
        return asumsiJasprod;
    }

    public void setAsumsiJasprod(String asumsiJasprod) {
        this.asumsiJasprod = asumsiJasprod;
    }

    public BigDecimal getAsumsiJasprodNilai() {
        return asumsiJasprodNilai;
    }

    public void setAsumsiJasprodNilai(BigDecimal asumsiJasprodNilai) {
        this.asumsiJasprodNilai = asumsiJasprodNilai;
    }

    public String getPensiun() {
        return pensiun;
    }

    public void setPensiun(String pensiun) {
        this.pensiun = pensiun;
    }

    public BigDecimal getPensiunNilai() {
        return pensiunNilai;
    }

    public void setPensiunNilai(BigDecimal pensiunNilai) {
        this.pensiunNilai = pensiunNilai;
    }

    public BigDecimal getIuranJkmJkkNilai() {
        return iuranJkmJkkNilai;
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

    public void setIuranJkmJkkNilai(BigDecimal iuranJkmJkkNilai) {
        this.iuranJkmJkkNilai = iuranJkmJkkNilai;
    }

    public String getPphGaji() {
        return pphGaji;
    }

    public void setPphGaji(String pphGaji) {
        this.pphGaji = pphGaji;
    }

    public BigDecimal getPphGajiNilai() {
        return pphGajiNilai;
    }

    public void setPphGajiNilai(BigDecimal pphGajiNilai) {
        this.pphGajiNilai = pphGajiNilai;
    }

    public String getJumlahB() {
        return jumlahB;
    }

    public void setJumlahB(String jumlahB) {
        this.jumlahB = jumlahB;
    }

    public BigDecimal getJumlahBNilai() {
        return jumlahBNilai;
    }

    public void setJumlahBNilai(BigDecimal jumlahBNilai) {
        this.jumlahBNilai = jumlahBNilai;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getBrutoNilai() {
        return brutoNilai;
    }

    public void setBrutoNilai(BigDecimal brutoNilai) {
        this.brutoNilai = brutoNilai;
    }

    public String getPphId() {
        return pphId;
    }

    public String getRapel() {
        return rapel;
    }

    public void setRapel(String rapel) {
        this.rapel = rapel;
    }

    public BigDecimal getRapelNilai() {
        return rapelNilai;
    }

    public void setRapelNilai(BigDecimal rapelNilai) {
        this.rapelNilai = rapelNilai;
    }

    public void setPphId(String pphId) {
        this.pphId = pphId;
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

    public String getPtkp() {
        return ptkp;
    }

    public void setPtkp(String ptkp) {
        this.ptkp = ptkp;
    }

    public BigDecimal getPtkpNilai() {
        return ptkpNilai;
    }

    public void setPtkpNilai(BigDecimal ptkpNilai) {
        this.ptkpNilai = ptkpNilai;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getGajiNilai() {
        return gajiNilai;
    }

    public void setGajiNilai(BigDecimal gajiNilai) {
        this.gajiNilai = gajiNilai;
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

    public String getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(String tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public BigDecimal getTunjanganStrategisNilai() {
        return tunjanganStrategisNilai;
    }

    public void setTunjanganStrategisNilai(BigDecimal tunjanganStrategisNilai) {
        this.tunjanganStrategisNilai = tunjanganStrategisNilai;
    }

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(String tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTunjanganPeralihanNilai() {
        return tunjanganPeralihanNilai;
    }

    public void setTunjanganPeralihanNilai(BigDecimal tunjanganPeralihanNilai) {
        this.tunjanganPeralihanNilai = tunjanganPeralihanNilai;
    }

    public String getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(String tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganJabatanStrukturalNilai() {
        return tunjanganJabatanStrukturalNilai;
    }

    public void setTunjanganJabatanStrukturalNilai(BigDecimal tunjanganJabatanStrukturalNilai) {
        this.tunjanganJabatanStrukturalNilai = tunjanganJabatanStrukturalNilai;
    }

    public String getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(String tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganStrukturalNilai() {
        return tunjanganStrukturalNilai;
    }

    public void setTunjanganStrukturalNilai(BigDecimal tunjanganStrukturalNilai) {
        this.tunjanganStrukturalNilai = tunjanganStrukturalNilai;
    }

    public String getTunjanganKompensasi() {
        return tunjanganKompensasi;
    }

    public void setTunjanganKompensasi(String tunjanganKompensasi) {
        this.tunjanganKompensasi = tunjanganKompensasi;
    }

    public BigDecimal getTunjanganKompensasiNilai() {
        return tunjanganKompensasiNilai;
    }

    public void setTunjanganKompensasiNilai(BigDecimal tunjanganKompensasiNilai) {
        this.tunjanganKompensasiNilai = tunjanganKompensasiNilai;
    }

    public String getTunjanganTransport() {
        return tunjanganTransport;
    }

    public void setTunjanganTransport(String tunjanganTransport) {
        this.tunjanganTransport = tunjanganTransport;
    }

    public BigDecimal getTunjanganTransportNilai() {
        return tunjanganTransportNilai;
    }

    public void setTunjanganTransportNilai(BigDecimal tunjanganTransportNilai) {
        this.tunjanganTransportNilai = tunjanganTransportNilai;
    }

    public String getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public void setTunjanganAirListrik(String tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
    }

    public BigDecimal getTunjanganAirListrikNilai() {
        return tunjanganAirListrikNilai;
    }

    public void setTunjanganAirListrikNilai(BigDecimal tunjanganAirListrikNilai) {
        this.tunjanganAirListrikNilai = tunjanganAirListrikNilai;
    }

    public String getTunjanganPerumahan() {
        return tunjanganPerumahan;
    }

    public void setTunjanganPerumahan(String tunjanganPerumahan) {
        this.tunjanganPerumahan = tunjanganPerumahan;
    }

    public BigDecimal getTunjanganPerumahanNilai() {
        return tunjanganPerumahanNilai;
    }

    public void setTunjanganPerumahanNilai(BigDecimal tunjanganPerumahanNilai) {
        this.tunjanganPerumahanNilai = tunjanganPerumahanNilai;
    }

    public String getTunjanganPengobatan() {
        return tunjanganPengobatan;
    }

    public void setTunjanganPengobatan(String tunjanganPengobatan) {
        this.tunjanganPengobatan = tunjanganPengobatan;
    }

    public BigDecimal getTunjanganPengobatanNilai() {
        return tunjanganPengobatanNilai;
    }

    public void setTunjanganPengobatanNilai(BigDecimal tunjanganPengobatanNilai) {
        this.tunjanganPengobatanNilai = tunjanganPengobatanNilai;
    }

    public String getTunjanganPphBulan() {
        return tunjanganPphBulan;
    }

    public void setTunjanganPphBulan(String tunjanganPphBulan) {
        this.tunjanganPphBulan = tunjanganPphBulan;
    }

    public BigDecimal getTunjanganPphNilaiBulan() {
        return tunjanganPphNilaiBulan;
    }

    public void setTunjanganPphNilaiBulan(BigDecimal tunjanganPphNilaiBulan) {
        this.tunjanganPphNilaiBulan = tunjanganPphNilaiBulan;
    }

    public BigDecimal getTunjanganPphNilaiTahun() {
        return tunjanganPphNilaiTahun;
    }

    public void setTunjanganPphNilaiTahun(BigDecimal tunjanganPphNilaiTahun) {
        this.tunjanganPphNilaiTahun = tunjanganPphNilaiTahun;
    }

    public String getTunjanganPphTahun() {
        return tunjanganPphTahun;
    }

    public void setTunjanganPphTahun(String tunjanganPphTahun) {
        this.tunjanganPphTahun = tunjanganPphTahun;
    }

    public String getTunjanganLembur() {
        return tunjanganLembur;
    }

    public void setTunjanganLembur(String tunjanganLembur) {
        this.tunjanganLembur = tunjanganLembur;
    }

    public BigDecimal getTunjanganLemburNilai() {
        return tunjanganLemburNilai;
    }

    public void setTunjanganLemburNilai(BigDecimal tunjanganLemburNilai) {
        this.tunjanganLemburNilai = tunjanganLemburNilai;
    }

    public String getTunjanganLainLain() {
        return tunjanganLainLain;
    }

    public void setTunjanganLainLain(String tunjanganLainLain) {
        this.tunjanganLainLain = tunjanganLainLain;
    }

    public BigDecimal getTunjanganLainLainNilai() {
        return tunjanganLainLainNilai;
    }

    public void setTunjanganLainLainNilai(BigDecimal tunjanganLainLainNilai) {
        this.tunjanganLainLainNilai = tunjanganLainLainNilai;
    }

    public String getThr() {
        return thr;
    }

    public void setThr(String thr) {
        this.thr = thr;
    }

    public BigDecimal getThrNilai() {
        return thrNilai;
    }

    public void setThrNilai(BigDecimal thrNilai) {
        this.thrNilai = thrNilai;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public BigDecimal getPendidikanNilai() {
        return pendidikanNilai;
    }

    public void setPendidikanNilai(BigDecimal pendidikanNilai) {
        this.pendidikanNilai = pendidikanNilai;
    }

    public String getJasprod() {
        return jasprod;
    }

    public void setJasprod(String jasprod) {
        this.jasprod = jasprod;
    }

    public BigDecimal getJasprodNilai() {
        return jasprodNilai;
    }

    public void setJasprodNilai(BigDecimal jasprodNilai) {
        this.jasprodNilai = jasprodNilai;
    }

    public String getJubileum() {
        return Jubileum;
    }

    public void setJubileum(String jubileum) {
        Jubileum = jubileum;
    }

    public BigDecimal getJubileumNilai() {
        return JubileumNilai;
    }

    public void setJubileumNilai(BigDecimal jubileumNilai) {
        JubileumNilai = jubileumNilai;
    }

    public String getPakaianDinas() {
        return pakaianDinas;
    }

    public void setPakaianDinas(String pakaianDinas) {
        this.pakaianDinas = pakaianDinas;
    }

    public BigDecimal getPakaianDinasNilai() {
        return pakaianDinasNilai;
    }

    public void setPakaianDinasNilai(BigDecimal pakaianDinasNilai) {
        this.pakaianDinasNilai = pakaianDinasNilai;
    }

    public String getIuranJkmJkk() {
        return iuranJkmJkk;
    }

    public void setIuranJkmJkk(String iuranJkmJkk) {
        this.iuranJkmJkk = iuranJkmJkk;
    }

    public String getBiayaJabatan() {
        return biayaJabatan;
    }

    public void setBiayaJabatan(String biayaJabatan) {
        this.biayaJabatan = biayaJabatan;
    }

    public BigDecimal getBiayaJabatanNilai() {
        return biayaJabatanNilai;
    }

    public void setBiayaJabatanNilai(BigDecimal biayaJabatanNilai) {
        this.biayaJabatanNilai = biayaJabatanNilai;
    }

    public String getIuranPensiun() {
        return iuranPensiun;
    }

    public void setIuranPensiun(String iuranPensiun) {
        this.iuranPensiun = iuranPensiun;
    }

    public BigDecimal getIuranPensiunNilai() {
        return iuranPensiunNilai;
    }

    public void setIuranPensiunNilai(BigDecimal iuranPensiunNilai) {
        this.iuranPensiunNilai = iuranPensiunNilai;
    }

    public String getPkp() {
        return pkp;
    }

    public void setPkp(String pkp) {
        this.pkp = pkp;
    }

    public BigDecimal getPkpNilai() {
        return pkpNilai;
    }

    public void setPkpNilai(BigDecimal pkpNilai) {
        this.pkpNilai = pkpNilai;
    }

    public String getHutangPph() {
        return hutangPph;
    }

    public void setHutangPph(String hutangPph) {
        this.hutangPph = hutangPph;
    }

    public BigDecimal getHutangPphNilai() {
        return hutangPphNilai;
    }

    public void setHutangPphNilai(BigDecimal hutangPphNilai) {
        this.hutangPphNilai = hutangPphNilai;
    }
}