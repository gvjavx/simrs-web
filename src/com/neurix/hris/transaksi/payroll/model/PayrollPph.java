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
    private String payrollId;
    private String pkp;
    private BigDecimal pkpNilai;
    private String pphGaji;
    private BigDecimal pphGajiNilai ;
    private String bruto ;
    private BigDecimal brutoNilai ;
    private String reduce ;
    private BigDecimal reduceNilai ;
    private String nip;
    private String namaPegawai;
    private String bulan;
    private String tahun;
    private String ptkp;
    private BigDecimal ptkpNilai;
    private String netto ;
    private BigDecimal nettoNilai ;
    private String biayaJabatan;
    private BigDecimal biayaJabatanNilai;
    private String hutangPph; //hutang pph setahun
    private BigDecimal hutangPphNilai; //hutang pph setahun
    private String selisihPph; //selisih pph setahun
    private BigDecimal selisihPphNilai; //selisih pph setahun

    private String tunjanganPphBulan; //tunjangan pph
    private BigDecimal tunjanganPphNilaiBulan; ////tunjangan pph
    private String gaji;
    private BigDecimal gajiNilai;
    private String sankhus;
    private BigDecimal sankhusNilai;
    private String tunjanganStrategis; //tunjangan fungsional
    private BigDecimal tunjanganStrategisNilai; ///tunjangan fungsional nilai
    private String tunjanganPeralihan;
    private BigDecimal tunjanganPeralihanNilai;
    private String tunjanganJabatanStruktural; //tunjangan jabatan
    private BigDecimal tunjanganJabatanStrukturalNilai; // tunjangan jabatan nilai
    private String tunjanganStruktural;
    private BigDecimal tunjanganStrukturalNilai;
    private String totalTunjanganLain;
    private BigDecimal totalTunjanganLainNilai;
    private String tunjanganTambahan;
    private BigDecimal tunjanganTambahanNilai;
    private String pemondokan;
    private BigDecimal pemondokanNilai;
    private String komunikasi;
    private BigDecimal komunikasiNilai;
    private String totalRlab;
    private BigDecimal totalRlabNilai;
    private String iuranPegawai;
    private BigDecimal iuranPegawaiNilai;
    private String tunjanganLembur;
    private BigDecimal tunjanganLemburNilai;
    private String tunjanganPensiun;
    private BigDecimal tunjanganPensiunNilai;
    private String bpjsTk;
    private BigDecimal bpjsTkNilai;
    private String bpjsKs;
    private BigDecimal bpjsKsNilai;
    private String incomeTidakTetap;
    private BigDecimal incomeTidakTetapNilai;
    private String keterangan;

    //updated by ferdi, 01-12-2020, tambahkan tipe payroll
    private String tipePayroll;


    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getTipePayroll() {
        return tipePayroll;
    }

    public void setTipePayroll(String tipePayroll) {
        this.tipePayroll = tipePayroll;
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

    public String getTotalTunjanganLain() {
        return totalTunjanganLain;
    }

    public void setTotalTunjanganLain(String totalTunjanganLain) {
        this.totalTunjanganLain = totalTunjanganLain;
    }

    public BigDecimal getTotalTunjanganLainNilai() {
        return totalTunjanganLainNilai;
    }

    public void setTotalTunjanganLainNilai(BigDecimal totalTunjanganLainNilai) {
        this.totalTunjanganLainNilai = totalTunjanganLainNilai;
    }

    public String getIncomeTidakTetap() {
        return incomeTidakTetap;
    }

    public void setIncomeTidakTetap(String incomeTidakTetap) {
        this.incomeTidakTetap = incomeTidakTetap;
    }

    public BigDecimal getIncomeTidakTetapNilai() {
        return incomeTidakTetapNilai;
    }

    public void setIncomeTidakTetapNilai(BigDecimal incomeTidakTetapNilai) {
        this.incomeTidakTetapNilai = incomeTidakTetapNilai;
    }

    public String getBpjsKs() {
        return bpjsKs;
    }

    public void setBpjsKs(String bpjsKs) {
        this.bpjsKs = bpjsKs;
    }

    public BigDecimal getBpjsKsNilai() {
        return bpjsKsNilai;
    }

    public void setBpjsKsNilai(BigDecimal bpjsKsNilai) {
        this.bpjsKsNilai = bpjsKsNilai;
    }

    public String getBpjsTk() {
        return bpjsTk;
    }

    public void setBpjsTk(String bpjsTk) {
        this.bpjsTk = bpjsTk;
    }

    public BigDecimal getBpjsTkNilai() {
        return bpjsTkNilai;
    }

    public void setBpjsTkNilai(BigDecimal bpjsTkNilai) {
        this.bpjsTkNilai = bpjsTkNilai;
    }

    public String getIuranPegawai() {
        return iuranPegawai;
    }

    public void setIuranPegawai(String iuranPegawai) {
        this.iuranPegawai = iuranPegawai;
    }

    public BigDecimal getIuranPegawaiNilai() {
        return iuranPegawaiNilai;
    }

    public void setIuranPegawaiNilai(BigDecimal iuranPegawaiNilai) {
        this.iuranPegawaiNilai = iuranPegawaiNilai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getReduce() {
        return reduce;
    }

    public void setReduce(String reduce) {
        this.reduce = reduce;
    }

    public BigDecimal getReduceNilai() {
        return reduceNilai;
    }

    public void setReduceNilai(BigDecimal reduceNilai) {
        this.reduceNilai = reduceNilai;
    }

    public String getSankhus() {
        return sankhus;
    }

    public void setSankhus(String sankhus) {
        this.sankhus = sankhus;
    }

    public BigDecimal getSankhusNilai() {
        return sankhusNilai;
    }

    public void setSankhusNilai(BigDecimal sankhusNilai) {
        this.sankhusNilai = sankhusNilai;
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

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
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

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public BigDecimal getNettoNilai() {
        return nettoNilai;
    }

    public void setNettoNilai(BigDecimal nettoNilai) {
        this.nettoNilai = nettoNilai;
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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getTunjanganPphNilaiBulan() {
        return tunjanganPphNilaiBulan;
    }

    public void setTunjanganPphNilaiBulan(BigDecimal tunjanganPphNilaiBulan) {
        this.tunjanganPphNilaiBulan = tunjanganPphNilaiBulan;
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

    //pph RNI dulu, bisa dipakai kedepannya ( updated by ferdi, 01-12-2020 )

    private String iuranPensiun;
    private BigDecimal iuranPensiunNilai;

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

    private String tunjanganPphTahun;
    private BigDecimal tunjanganPphNilaiTahun;

    private String tunjanganLainLain;
    private BigDecimal tunjanganLainLainNilai;

    private String pakaianDinas;
    private BigDecimal pakaianDinasNilai;

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

    public String getSelisihPph() {
        return selisihPph;
    }

    public void setSelisihPph(String selisihPph) {
        this.selisihPph = selisihPph;
    }

    public BigDecimal getSelisihPphNilai() {
        return selisihPphNilai;
    }

    public void setSelisihPphNilai(BigDecimal selisihPphNilai) {
        this.selisihPphNilai = selisihPphNilai;
    }
}