package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollRapelDetailEntity implements Serializable {
    private String rapelDetailId;
    private String rapelId;
    private String nip;
    private String golonganId;
    private String point;
    private String pointLebih;
    private String golonganIdLama;
    private String pointLama;
    private String pointLebihLama;
    private String rapelBulan;
    private String rapelTahun;

    private String gajiGolonganLama;
    private String gajiGolonganBaru;
    private String gajiGolongan;
    private BigDecimal gajiGolonganLamaNilai;
    private BigDecimal gajiGolonganBaruNilai;
    private BigDecimal gajiGolonganNilai;

    private String peralihanLama;
    private String peralihanBaru;
    private String peralihan;
    private BigDecimal peralihanLamaNilai;
    private BigDecimal peralihanBaruNilai;
    private BigDecimal peralihanNilai;

    private String umkLama;
    private String umkBaru;
    private String umk;
    private BigDecimal umkLamaNilai;
    private BigDecimal umkBaruNilai;
    private BigDecimal umkNilai;

    private String strukturalLama;
    private String strukturalBaru;
    private String struktural;
    private BigDecimal strukturalLamaNilai;
    private BigDecimal strukturalBaruNilai;
    private BigDecimal strukturalNilai;

    private String jabStrukturalLama;
    private String jabStrukturalBaru;
    private String jabStruktural;
    private BigDecimal jabStrukturalLamaNilai;
    private BigDecimal jabStrukturalBaruNilai;
    private BigDecimal jabStrukturalNilai;

    private String strategisLama;
    private String strategisBaru;
    private String strategis;
    private BigDecimal strategisLamaNilai;
    private BigDecimal strategisBaruNilai;
    private BigDecimal strategisNilai;

    private String airListrikLama;
    private String airListrikBaru;
    private String airListrik;
    private BigDecimal airListrikLamaNilai;
    private BigDecimal airListrikBaruNilai;
    private BigDecimal airListrikNilai;

    private String perumahanLama;
    private String perumahanBaru;
    private String perumahan;
    private BigDecimal perumahanLamaNilai;
    private BigDecimal perumahanBaruNilai;
    private BigDecimal perumahanNilai;
    private BigDecimal perlihanLamaNilai;
    private BigDecimal perlihanBaruNilai;
    private BigDecimal perlihanNilai;

    private String bulan;
    private String tahun;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

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

    public String getPeralihan() {
        return peralihan;
    }

    public void setPeralihan(String peralihan) {
        this.peralihan = peralihan;
    }

    public BigDecimal getPeralihanLamaNilai() {
        return peralihanLamaNilai;
    }

    public void setPeralihanLamaNilai(BigDecimal peralihanLamaNilai) {
        this.peralihanLamaNilai = peralihanLamaNilai;
    }

    public BigDecimal getPeralihanBaruNilai() {
        return peralihanBaruNilai;
    }

    public void setPeralihanBaruNilai(BigDecimal peralihanBaruNilai) {
        this.peralihanBaruNilai = peralihanBaruNilai;
    }

    public BigDecimal getPeralihanNilai() {
        return peralihanNilai;
    }

    public void setPeralihanNilai(BigDecimal peralihanNilai) {
        this.peralihanNilai = peralihanNilai;
    }

    public BigDecimal getPerlihanLamaNilai() {
        return perlihanLamaNilai;
    }

    public void setPerlihanLamaNilai(BigDecimal perlihanLamaNilai) {
        this.perlihanLamaNilai = perlihanLamaNilai;
    }

    public BigDecimal getPerlihanBaruNilai() {
        return perlihanBaruNilai;
    }

    public void setPerlihanBaruNilai(BigDecimal perlihanBaruNilai) {
        this.perlihanBaruNilai = perlihanBaruNilai;
    }

    public BigDecimal getPerlihanNilai() {
        return perlihanNilai;
    }

    public void setPerlihanNilai(BigDecimal perlihanNilai) {
        this.perlihanNilai = perlihanNilai;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getRapelDetailId() {
        return rapelDetailId;
    }

    public void setRapelDetailId(String rapelDetailId) {
        this.rapelDetailId = rapelDetailId;
    }

    public String getRapelId() {
        return rapelId;
    }

    public void setRapelId(String rapelId) {
        this.rapelId = rapelId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPointLebih() {
        return pointLebih;
    }

    public void setPointLebih(String pointLebih) {
        this.pointLebih = pointLebih;
    }

    public String getGolonganIdLama() {
        return golonganIdLama;
    }

    public void setGolonganIdLama(String golonganIdLama) {
        this.golonganIdLama = golonganIdLama;
    }

    public String getPointLama() {
        return pointLama;
    }

    public void setPointLama(String pointLama) {
        this.pointLama = pointLama;
    }

    public String getPointLebihLama() {
        return pointLebihLama;
    }

    public void setPointLebihLama(String pointLebihLama) {
        this.pointLebihLama = pointLebihLama;
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

    public String getGajiGolonganLama() {
        return gajiGolonganLama;
    }

    public void setGajiGolonganLama(String gajiGolonganLama) {
        this.gajiGolonganLama = gajiGolonganLama;
    }

    public String getGajiGolonganBaru() {
        return gajiGolonganBaru;
    }

    public void setGajiGolonganBaru(String gajiGolonganBaru) {
        this.gajiGolonganBaru = gajiGolonganBaru;
    }

    public String getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(String gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public BigDecimal getGajiGolonganLamaNilai() {
        return gajiGolonganLamaNilai;
    }

    public void setGajiGolonganLamaNilai(BigDecimal gajiGolonganLamaNilai) {
        this.gajiGolonganLamaNilai = gajiGolonganLamaNilai;
    }

    public BigDecimal getGajiGolonganBaruNilai() {
        return gajiGolonganBaruNilai;
    }

    public void setGajiGolonganBaruNilai(BigDecimal gajiGolonganBaruNilai) {
        this.gajiGolonganBaruNilai = gajiGolonganBaruNilai;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public String getUmkLama() {
        return umkLama;
    }

    public void setUmkLama(String umkLama) {
        this.umkLama = umkLama;
    }

    public String getUmkBaru() {
        return umkBaru;
    }

    public void setUmkBaru(String umkBaru) {
        this.umkBaru = umkBaru;
    }

    public String getUmk() {
        return umk;
    }

    public void setUmk(String umk) {
        this.umk = umk;
    }

    public BigDecimal getUmkLamaNilai() {
        return umkLamaNilai;
    }

    public void setUmkLamaNilai(BigDecimal umkLamaNilai) {
        this.umkLamaNilai = umkLamaNilai;
    }

    public BigDecimal getUmkBaruNilai() {
        return umkBaruNilai;
    }

    public void setUmkBaruNilai(BigDecimal umkBaruNilai) {
        this.umkBaruNilai = umkBaruNilai;
    }

    public BigDecimal getUmkNilai() {
        return umkNilai;
    }

    public void setUmkNilai(BigDecimal umkNilai) {
        this.umkNilai = umkNilai;
    }

    public String getStrukturalLama() {
        return strukturalLama;
    }

    public void setStrukturalLama(String strukturalLama) {
        this.strukturalLama = strukturalLama;
    }

    public String getStrukturalBaru() {
        return strukturalBaru;
    }

    public void setStrukturalBaru(String strukturalBaru) {
        this.strukturalBaru = strukturalBaru;
    }

    public String getStruktural() {
        return struktural;
    }

    public void setStruktural(String struktural) {
        this.struktural = struktural;
    }

    public BigDecimal getStrukturalLamaNilai() {
        return strukturalLamaNilai;
    }

    public void setStrukturalLamaNilai(BigDecimal strukturalLamaNilai) {
        this.strukturalLamaNilai = strukturalLamaNilai;
    }

    public BigDecimal getStrukturalBaruNilai() {
        return strukturalBaruNilai;
    }

    public void setStrukturalBaruNilai(BigDecimal strukturalBaruNilai) {
        this.strukturalBaruNilai = strukturalBaruNilai;
    }

    public BigDecimal getStrukturalNilai() {
        return strukturalNilai;
    }

    public void setStrukturalNilai(BigDecimal strukturalNilai) {
        this.strukturalNilai = strukturalNilai;
    }

    public String getJabStrukturalLama() {
        return jabStrukturalLama;
    }

    public void setJabStrukturalLama(String jabStrukturalLama) {
        this.jabStrukturalLama = jabStrukturalLama;
    }

    public String getJabStrukturalBaru() {
        return jabStrukturalBaru;
    }

    public void setJabStrukturalBaru(String jabStrukturalBaru) {
        this.jabStrukturalBaru = jabStrukturalBaru;
    }

    public String getJabStruktural() {
        return jabStruktural;
    }

    public void setJabStruktural(String jabStruktural) {
        this.jabStruktural = jabStruktural;
    }

    public BigDecimal getJabStrukturalLamaNilai() {
        return jabStrukturalLamaNilai;
    }

    public void setJabStrukturalLamaNilai(BigDecimal jabStrukturalLamaNilai) {
        this.jabStrukturalLamaNilai = jabStrukturalLamaNilai;
    }

    public BigDecimal getJabStrukturalBaruNilai() {
        return jabStrukturalBaruNilai;
    }

    public void setJabStrukturalBaruNilai(BigDecimal jabStrukturalBaruNilai) {
        this.jabStrukturalBaruNilai = jabStrukturalBaruNilai;
    }

    public BigDecimal getJabStrukturalNilai() {
        return jabStrukturalNilai;
    }

    public void setJabStrukturalNilai(BigDecimal jabStrukturalNilai) {
        this.jabStrukturalNilai = jabStrukturalNilai;
    }

    public String getStrategisLama() {
        return strategisLama;
    }

    public void setStrategisLama(String strategisLama) {
        this.strategisLama = strategisLama;
    }

    public String getStrategisBaru() {
        return strategisBaru;
    }

    public void setStrategisBaru(String strategisBaru) {
        this.strategisBaru = strategisBaru;
    }

    public String getStrategis() {
        return strategis;
    }

    public void setStrategis(String strategis) {
        this.strategis = strategis;
    }

    public BigDecimal getStrategisLamaNilai() {
        return strategisLamaNilai;
    }

    public void setStrategisLamaNilai(BigDecimal strategisLamaNilai) {
        this.strategisLamaNilai = strategisLamaNilai;
    }

    public BigDecimal getStrategisBaruNilai() {
        return strategisBaruNilai;
    }

    public void setStrategisBaruNilai(BigDecimal strategisBaruNilai) {
        this.strategisBaruNilai = strategisBaruNilai;
    }

    public BigDecimal getStrategisNilai() {
        return strategisNilai;
    }

    public void setStrategisNilai(BigDecimal strategisNilai) {
        this.strategisNilai = strategisNilai;
    }

    public String getAirListrikLama() {
        return airListrikLama;
    }

    public void setAirListrikLama(String airListrikLama) {
        this.airListrikLama = airListrikLama;
    }

    public String getAirListrikBaru() {
        return airListrikBaru;
    }

    public void setAirListrikBaru(String airListrikBaru) {
        this.airListrikBaru = airListrikBaru;
    }

    public String getAirListrik() {
        return airListrik;
    }

    public void setAirListrik(String airListrik) {
        this.airListrik = airListrik;
    }

    public BigDecimal getAirListrikLamaNilai() {
        return airListrikLamaNilai;
    }

    public void setAirListrikLamaNilai(BigDecimal airListrikLamaNilai) {
        this.airListrikLamaNilai = airListrikLamaNilai;
    }

    public BigDecimal getAirListrikBaruNilai() {
        return airListrikBaruNilai;
    }

    public void setAirListrikBaruNilai(BigDecimal airListrikBaruNilai) {
        this.airListrikBaruNilai = airListrikBaruNilai;
    }

    public BigDecimal getAirListrikNilai() {
        return airListrikNilai;
    }

    public void setAirListrikNilai(BigDecimal airListrikNilai) {
        this.airListrikNilai = airListrikNilai;
    }

    public String getPerumahanLama() {
        return perumahanLama;
    }

    public void setPerumahanLama(String perumahanLama) {
        this.perumahanLama = perumahanLama;
    }

    public String getPerumahanBaru() {
        return perumahanBaru;
    }

    public void setPerumahanBaru(String perumahanBaru) {
        this.perumahanBaru = perumahanBaru;
    }

    public String getPerumahan() {
        return perumahan;
    }

    public void setPerumahan(String perumahan) {
        this.perumahan = perumahan;
    }

    public BigDecimal getPerumahanLamaNilai() {
        return perumahanLamaNilai;
    }

    public void setPerumahanLamaNilai(BigDecimal perumahanLamaNilai) {
        this.perumahanLamaNilai = perumahanLamaNilai;
    }

    public BigDecimal getPerumahanBaruNilai() {
        return perumahanBaruNilai;
    }

    public void setPerumahanBaruNilai(BigDecimal perumahanBaruNilai) {
        this.perumahanBaruNilai = perumahanBaruNilai;
    }

    public BigDecimal getPerumahanNilai() {
        return perumahanNilai;
    }

    public void setPerumahanNilai(BigDecimal perumahanNilai) {
        this.perumahanNilai = perumahanNilai;
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
}