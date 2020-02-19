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

public class PayrollInsentif extends BaseModel {
    private String insentifId;
    private String nip;
    private String payrollId;
    private String bulan;
    private String tahun;

    private BigDecimal totalInsentifNilai;
    private BigDecimal totalInsentifBersihNilai;
    private BigDecimal pphInsentifNilai;
    private BigDecimal gajiNilai;
    private BigDecimal sankhusNilai;
    private BigDecimal tunjJabatanNilai;
    private BigDecimal tunjStruturalNilai;
    private BigDecimal tunjFungsionalNilai;
    private BigDecimal tunjPeralihanNilai;
    private BigDecimal tunjTambahanNilai;
    private BigDecimal tunjRumahNilai;
    private BigDecimal tunjListrikNilai;
    private BigDecimal tunjAirNilai;
    private BigDecimal tunjBbmNilai;
    private BigDecimal totalThpNilai;

    private String totalInsentif;
    private String totalInsentifBersih;
    private String pphInsentif;
    private String gaji;
    private String sankhus;
    private String tunjJabatan;
    private String tunjStrutural;
    private String tunjFungsional;
    private String tunjPeralihan;
    private String tunjTambahan;
    private String tunjRumah;
    private String tunjListrik;
    private String tunjAir;
    private String tunjBbm;
    private String totalThp;

    public String getTotalThp() {
        return totalThp;
    }

    public void setTotalThp(String totalThp) {
        this.totalThp = totalThp;
    }

    public BigDecimal getTotalThpNilai() {
        return totalThpNilai;
    }

    public void setTotalThpNilai(BigDecimal totalThpNilai) {
        this.totalThpNilai = totalThpNilai;
    }

    public String getTunjTambahan() {
        return tunjTambahan;
    }

    public void setTunjTambahan(String tunjTambahan) {
        this.tunjTambahan = tunjTambahan;
    }

    public BigDecimal getTunjTambahanNilai() {
        return tunjTambahanNilai;
    }

    public void setTunjTambahanNilai(BigDecimal tunjTambahanNilai) {
        this.tunjTambahanNilai = tunjTambahanNilai;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
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

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getPphInsentif() {
        return pphInsentif;
    }

    public void setPphInsentif(String pphInsentif) {
        this.pphInsentif = pphInsentif;
    }

    public BigDecimal getPphInsentifNilai() {
        return pphInsentifNilai;
    }

    public void setPphInsentifNilai(BigDecimal pphInsentifNilai) {
        this.pphInsentifNilai = pphInsentifNilai;
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

    public String getTotalInsentif() {
        return totalInsentif;
    }

    public void setTotalInsentif(String totalInsentif) {
        this.totalInsentif = totalInsentif;
    }

    public String getTotalInsentifBersih() {
        return totalInsentifBersih;
    }

    public void setTotalInsentifBersih(String totalInsentifBersih) {
        this.totalInsentifBersih = totalInsentifBersih;
    }

    public BigDecimal getTotalInsentifBersihNilai() {
        return totalInsentifBersihNilai;
    }

    public void setTotalInsentifBersihNilai(BigDecimal totalInsentifBersihNilai) {
        this.totalInsentifBersihNilai = totalInsentifBersihNilai;
    }

    public BigDecimal getTotalInsentifNilai() {
        return totalInsentifNilai;
    }

    public void setTotalInsentifNilai(BigDecimal totalInsentifNilai) {
        this.totalInsentifNilai = totalInsentifNilai;
    }

    public String getTunjAir() {
        return tunjAir;
    }

    public void setTunjAir(String tunjAir) {
        this.tunjAir = tunjAir;
    }

    public BigDecimal getTunjAirNilai() {
        return tunjAirNilai;
    }

    public void setTunjAirNilai(BigDecimal tunjAirNilai) {
        this.tunjAirNilai = tunjAirNilai;
    }

    public String getTunjBbm() {
        return tunjBbm;
    }

    public void setTunjBbm(String tunjBbm) {
        this.tunjBbm = tunjBbm;
    }

    public BigDecimal getTunjBbmNilai() {
        return tunjBbmNilai;
    }

    public void setTunjBbmNilai(BigDecimal tunjBbmNilai) {
        this.tunjBbmNilai = tunjBbmNilai;
    }

    public String getTunjFungsional() {
        return tunjFungsional;
    }

    public void setTunjFungsional(String tunjFungsional) {
        this.tunjFungsional = tunjFungsional;
    }

    public BigDecimal getTunjFungsionalNilai() {
        return tunjFungsionalNilai;
    }

    public void setTunjFungsionalNilai(BigDecimal tunjFungsionalNilai) {
        this.tunjFungsionalNilai = tunjFungsionalNilai;
    }

    public String getTunjJabatan() {
        return tunjJabatan;
    }

    public void setTunjJabatan(String tunjJabatan) {
        this.tunjJabatan = tunjJabatan;
    }

    public BigDecimal getTunjJabatanNilai() {
        return tunjJabatanNilai;
    }

    public void setTunjJabatanNilai(BigDecimal tunjJabatanNilai) {
        this.tunjJabatanNilai = tunjJabatanNilai;
    }

    public String getTunjListrik() {
        return tunjListrik;
    }

    public void setTunjListrik(String tunjListrik) {
        this.tunjListrik = tunjListrik;
    }

    public BigDecimal getTunjListrikNilai() {
        return tunjListrikNilai;
    }

    public void setTunjListrikNilai(BigDecimal tunjListrikNilai) {
        this.tunjListrikNilai = tunjListrikNilai;
    }

    public String getTunjPeralihan() {
        return tunjPeralihan;
    }

    public void setTunjPeralihan(String tunjPeralihan) {
        this.tunjPeralihan = tunjPeralihan;
    }

    public BigDecimal getTunjPeralihanNilai() {
        return tunjPeralihanNilai;
    }

    public void setTunjPeralihanNilai(BigDecimal tunjPeralihanNilai) {
        this.tunjPeralihanNilai = tunjPeralihanNilai;
    }

    public String getTunjRumah() {
        return tunjRumah;
    }

    public void setTunjRumah(String tunjRumah) {
        this.tunjRumah = tunjRumah;
    }

    public BigDecimal getTunjRumahNilai() {
        return tunjRumahNilai;
    }

    public void setTunjRumahNilai(BigDecimal tunjRumahNilai) {
        this.tunjRumahNilai = tunjRumahNilai;
    }

    public String getTunjStrutural() {
        return tunjStrutural;
    }

    public void setTunjStrutural(String tunjStrutural) {
        this.tunjStrutural = tunjStrutural;
    }

    public BigDecimal getTunjStruturalNilai() {
        return tunjStruturalNilai;
    }

    public void setTunjStruturalNilai(BigDecimal tunjStruturalNilai) {
        this.tunjStruturalNilai = tunjStruturalNilai;
    }

    private String nama;
    private String branchId;
    private String branchName;
    private BigDecimal jumlahInsentif ;
    private BigDecimal jumlahPph ;

    private String strJumlahInsentif ;
    private String strJumlahPph ;

    private String flagInsentif;

    private String gajiGolongan;
    private String tunjanganUmk;
    private String tunjanganStruktural;
    private String tunjanganPeralihan;
    private String tunjanganJabatanStruktural;
    private String tunjanganStrategis;
    private String jumlahBruto;

    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganPeralihanNilai;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private BigDecimal tunjanganStrategisNilai;
    private BigDecimal jumlahBrutoNilai;

    private Integer masaKerja;
    private double smkStandart;
    private String smkHuruf;
    private double smkAngka;
    private BigDecimal potonganinsentifNilai;
    private BigDecimal potonganinsentifIndividuNilai;
    private BigDecimal insentifyangDiterimaNilai;

    private String potonganinsentif;
    private String potonganinsentifIndividu;
    private String insentifyangDiterima;

    private int bulanMulai;
    private int bulanSampai;
    private int tahunInsentif;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public int getBulanMulai() {
        return bulanMulai;
    }

    public void setBulanMulai(int bulanMulai) {
        this.bulanMulai = bulanMulai;
    }

    public int getBulanSampai() {
        return bulanSampai;
    }

    public void setBulanSampai(int bulanSampai) {
        this.bulanSampai = bulanSampai;
    }

    public int getTahunInsentif() {
        return tahunInsentif;
    }

    public void setTahunInsentif(int tahunInsentif) {
        this.tahunInsentif = tahunInsentif;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(String tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
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

    public String getJumlahBruto() {
        return jumlahBruto;
    }

    public void setJumlahBruto(String jumlahBruto) {
        this.jumlahBruto = jumlahBruto;
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

    public BigDecimal getTunjanganPeralihanNilai() {
        return tunjanganPeralihanNilai;
    }

    public void setTunjanganPeralihanNilai(BigDecimal tunjanganPeralihanNilai) {
        this.tunjanganPeralihanNilai = tunjanganPeralihanNilai;
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

    public BigDecimal getJumlahBrutoNilai() {
        return jumlahBrutoNilai;
    }

    public void setJumlahBrutoNilai(BigDecimal jumlahBrutoNilai) {
        this.jumlahBrutoNilai = jumlahBrutoNilai;
    }

    public Integer getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(Integer masaKerja) {
        this.masaKerja = masaKerja;
    }

    public double getSmkStandart() {
        return smkStandart;
    }

    public void setSmkStandart(double smkStandart) {
        this.smkStandart = smkStandart;
    }

    public String getSmkHuruf() {
        return smkHuruf;
    }

    public void setSmkHuruf(String smkHuruf) {
        this.smkHuruf = smkHuruf;
    }

    public double getSmkAngka() {
        return smkAngka;
    }

    public void setSmkAngka(double smkAngka) {
        this.smkAngka = smkAngka;
    }

    public BigDecimal getPotonganinsentifNilai() {
        return potonganinsentifNilai;
    }

    public void setPotonganinsentifNilai(BigDecimal potonganinsentifNilai) {
        this.potonganinsentifNilai = potonganinsentifNilai;
    }

    public BigDecimal getPotonganinsentifIndividuNilai() {
        return potonganinsentifIndividuNilai;
    }

    public void setPotonganinsentifIndividuNilai(BigDecimal potonganinsentifIndividuNilai) {
        this.potonganinsentifIndividuNilai = potonganinsentifIndividuNilai;
    }

    public BigDecimal getInsentifyangDiterimaNilai() {
        return insentifyangDiterimaNilai;
    }

    public void setInsentifyangDiterimaNilai(BigDecimal insentifyangDiterimaNilai) {
        this.insentifyangDiterimaNilai = insentifyangDiterimaNilai;
    }

    public String getPotonganinsentif() {
        return potonganinsentif;
    }

    public void setPotonganinsentif(String potonganinsentif) {
        this.potonganinsentif = potonganinsentif;
    }

    public String getPotonganinsentifIndividu() {
        return potonganinsentifIndividu;
    }

    public void setPotonganinsentifIndividu(String potonganinsentifIndividu) {
        this.potonganinsentifIndividu = potonganinsentifIndividu;
    }

    public String getInsentifyangDiterima() {
        return insentifyangDiterima;
    }

    public void setInsentifyangDiterima(String insentifyangDiterima) {
        this.insentifyangDiterima = insentifyangDiterima;
    }

    public String getFlagInsentif() {
        return flagInsentif;
    }

    public void setFlagInsentif(String flagInsentif) {
        this.flagInsentif = flagInsentif;
    }


    public String getStrJumlahInsentif() {
        return strJumlahInsentif;
    }

    public void setStrJumlahInsentif(String strJumlahInsentif) {
        this.strJumlahInsentif = strJumlahInsentif;
    }

    public String getStrJumlahPph() {
        return strJumlahPph;
    }

    public void setStrJumlahPph(String strJumlahPph) {
        this.strJumlahPph = strJumlahPph;
    }

    public String getInsentifId() {
        return insentifId;
    }

    public void setInsentifId(String insentifId) {
        this.insentifId = insentifId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public BigDecimal getJumlahInsentif() {
        return jumlahInsentif;
    }

    public void setJumlahInsentif(BigDecimal jumlahInsentif) {
        this.jumlahInsentif = jumlahInsentif;
    }

    public BigDecimal getJumlahPph() {
        return jumlahPph;
    }

    public void setJumlahPph(BigDecimal jumlahPph) {
        this.jumlahPph = jumlahPph;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}