package com.neurix.hris.transaksi.payroll.model;


import com.neurix.hris.master.golongan.model.ImGolonganEntity;

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

public class ItPayrollRapelEntity implements Serializable {
    private String rapelId;
    private String payrollId;
    private String golonganIdBaru;
    private String golonganIdLama;
    private String pointLama;
    private String pointBaru;
    private BigDecimal gajiGolonganLama;
    private BigDecimal tunjanganStrukturalLama;
    private BigDecimal tunjanganJabatanStrukturalLama;
    private BigDecimal tunjanganStrategisLama;
    private BigDecimal tunjanganAirListrikLama;
    private BigDecimal tunjanganPerumahanLama;
    private BigDecimal tunjanganUmkLama;

    private BigDecimal gajiGolonganBaru;
    private BigDecimal tunjanganStrukturalBaru;
    private BigDecimal tunjanganJabatanStrukturalBaru;
    private BigDecimal tunjanganStrategisBaru;
    private BigDecimal tunjanganAirListrikBaru;
    private BigDecimal tunjanganPerumahanBaru;
    private BigDecimal tunjanganUmkBaru;
    private BigDecimal totalRapel;
    private BigDecimal totalRapelBulan;
    private BigDecimal totalRapelFinal;
    private BigDecimal pphRapel;
    private BigDecimal pphRapelPribadi;
    private BigDecimal tunjanganPph;
    private BigDecimal rapelBersih;

    private BigDecimal selisihGajiGolongan;
    private BigDecimal selisihTunjanganStruktural;
    private BigDecimal selisihTunjanganJabatanStruktural;
    private BigDecimal selisihTunjanganStrategis;
    private BigDecimal selisihTunjanganAirListrik;
    private BigDecimal selisihTunjanganPerumahan;
    private BigDecimal selisihTunjanganUmk;

    private BigDecimal totalRapelInsentif;
    private BigDecimal totalRapelThr;
    private BigDecimal totalRapelJubileum;
    private BigDecimal totalRapelLembur;
    private BigDecimal totalRapelPendidikan;

    private int jumlahBlnRapel;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private Date tanggalAwal;
    private Date tanggalAkhir;

    private ImGolonganEntity imGolonganLamaEntity;
    private ImGolonganEntity imGolonganBaruEntity;

    public BigDecimal getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(BigDecimal tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public BigDecimal getRapelBersih() {
        return rapelBersih;
    }

    public void setRapelBersih(BigDecimal rapelBersih) {
        this.rapelBersih = rapelBersih;
    }

    public BigDecimal getPphRapel() {
        return pphRapel;
    }

    public void setPphRapel(BigDecimal pphRapel) {
        this.pphRapel = pphRapel;
    }

    public BigDecimal getPphRapelPribadi() {
        return pphRapelPribadi;
    }

    public void setPphRapelPribadi(BigDecimal pphRapelPribadi) {
        this.pphRapelPribadi = pphRapelPribadi;
    }

    public ImGolonganEntity getImGolonganBaruEntity() {
        return imGolonganBaruEntity;
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

    public void setImGolonganBaruEntity(ImGolonganEntity imGolonganBaruEntity) {
        this.imGolonganBaruEntity = imGolonganBaruEntity;
    }

    public ImGolonganEntity getImGolonganLamaEntity() {
        return imGolonganLamaEntity;
    }

    public void setImGolonganLamaEntity(ImGolonganEntity imGolonganLamaEntity) {
        this.imGolonganLamaEntity = imGolonganLamaEntity;
    }

    public BigDecimal getTotalRapelLembur() {
        return totalRapelLembur;
    }

    public void setTotalRapelLembur(BigDecimal totalRapelLembur) {
        this.totalRapelLembur = totalRapelLembur;
    }

    public BigDecimal getTotalRapelBulan() {
        return totalRapelBulan;
    }

    public void setTotalRapelBulan(BigDecimal totalRapelBulan) {
        this.totalRapelBulan = totalRapelBulan;
    }

    public BigDecimal getTotalRapelFinal() {
        return totalRapelFinal;
    }

    public void setTotalRapelFinal(BigDecimal totalRapelFinal) {
        this.totalRapelFinal = totalRapelFinal;
    }

    public String getGolonganIdBaru() {
        return golonganIdBaru;
    }

    public void setGolonganIdBaru(String golonganIdBaru) {
        this.golonganIdBaru = golonganIdBaru;
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

    public String getPointBaru() {
        return pointBaru;
    }

    public void setPointBaru(String pointBaru) {
        this.pointBaru = pointBaru;
    }

    public BigDecimal getTotalRapelInsentif() {
        return totalRapelInsentif;
    }

    public void setTotalRapelInsentif(BigDecimal totalRapelInsentif) {
        this.totalRapelInsentif = totalRapelInsentif;
    }

    public BigDecimal getTotalRapelThr() {
        return totalRapelThr;
    }

    public void setTotalRapelThr(BigDecimal totalRapelThr) {
        this.totalRapelThr = totalRapelThr;
    }

    public BigDecimal getTotalRapelJubileum() {
        return totalRapelJubileum;
    }

    public void setTotalRapelJubileum(BigDecimal totalRapelJubileum) {
        this.totalRapelJubileum = totalRapelJubileum;
    }

    public BigDecimal getTotalRapelPendidikan() {
        return totalRapelPendidikan;
    }

    public void setTotalRapelPendidikan(BigDecimal totalRapelPendidikan) {
        this.totalRapelPendidikan = totalRapelPendidikan;
    }

    public int getJumlahBlnRapel() {
        return jumlahBlnRapel;
    }

    public void setJumlahBlnRapel(int jumlahBlnRapel) {
        this.jumlahBlnRapel = jumlahBlnRapel;
    }

    public BigDecimal getSelisihGajiGolongan() {
        return selisihGajiGolongan;
    }

    public void setSelisihGajiGolongan(BigDecimal selisihGajiGolongan) {
        this.selisihGajiGolongan = selisihGajiGolongan;
    }

    public BigDecimal getSelisihTunjanganStruktural() {
        return selisihTunjanganStruktural;
    }

    public void setSelisihTunjanganStruktural(BigDecimal selisihTunjanganStruktural) {
        this.selisihTunjanganStruktural = selisihTunjanganStruktural;
    }

    public BigDecimal getSelisihTunjanganJabatanStruktural() {
        return selisihTunjanganJabatanStruktural;
    }

    public void setSelisihTunjanganJabatanStruktural(BigDecimal selisihTunjanganJabatanStruktural) {
        this.selisihTunjanganJabatanStruktural = selisihTunjanganJabatanStruktural;
    }

    public BigDecimal getSelisihTunjanganStrategis() {
        return selisihTunjanganStrategis;
    }

    public void setSelisihTunjanganStrategis(BigDecimal selisihTunjanganStrategis) {
        this.selisihTunjanganStrategis = selisihTunjanganStrategis;
    }

    public BigDecimal getSelisihTunjanganAirListrik() {
        return selisihTunjanganAirListrik;
    }

    public void setSelisihTunjanganAirListrik(BigDecimal selisihTunjanganAirListrik) {
        this.selisihTunjanganAirListrik = selisihTunjanganAirListrik;
    }

    public BigDecimal getSelisihTunjanganPerumahan() {
        return selisihTunjanganPerumahan;
    }

    public void setSelisihTunjanganPerumahan(BigDecimal selisihTunjanganPerumahan) {
        this.selisihTunjanganPerumahan = selisihTunjanganPerumahan;
    }

    public BigDecimal getSelisihTunjanganUmk() {
        return selisihTunjanganUmk;
    }

    public void setSelisihTunjanganUmk(BigDecimal selisihTunjanganUmk) {
        this.selisihTunjanganUmk = selisihTunjanganUmk;
    }

    public BigDecimal getGajiGolonganBaru() {
        return gajiGolonganBaru;
    }

    public void setGajiGolonganBaru(BigDecimal gajiGolonganBaru) {
        this.gajiGolonganBaru = gajiGolonganBaru;
    }

    public BigDecimal getTunjanganStrukturalBaru() {
        return tunjanganStrukturalBaru;
    }

    public void setTunjanganStrukturalBaru(BigDecimal tunjanganStrukturalBaru) {
        this.tunjanganStrukturalBaru = tunjanganStrukturalBaru;
    }

    public BigDecimal getTunjanganJabatanStrukturalBaru() {
        return tunjanganJabatanStrukturalBaru;
    }

    public void setTunjanganJabatanStrukturalBaru(BigDecimal tunjanganJabatanStrukturalBaru) {
        this.tunjanganJabatanStrukturalBaru = tunjanganJabatanStrukturalBaru;
    }

    public BigDecimal getTunjanganStrategisBaru() {
        return tunjanganStrategisBaru;
    }

    public void setTunjanganStrategisBaru(BigDecimal tunjanganStrategisBaru) {
        this.tunjanganStrategisBaru = tunjanganStrategisBaru;
    }

    public BigDecimal getTunjanganAirListrikBaru() {
        return tunjanganAirListrikBaru;
    }

    public void setTunjanganAirListrikBaru(BigDecimal tunjanganAirListrikBaru) {
        this.tunjanganAirListrikBaru = tunjanganAirListrikBaru;
    }

    public BigDecimal getTunjanganPerumahanBaru() {
        return tunjanganPerumahanBaru;
    }

    public void setTunjanganPerumahanBaru(BigDecimal tunjanganPerumahanBaru) {
        this.tunjanganPerumahanBaru = tunjanganPerumahanBaru;
    }

    public BigDecimal getTunjanganUmkBaru() {
        return tunjanganUmkBaru;
    }

    public void setTunjanganUmkBaru(BigDecimal tunjanganUmkBaru) {
        this.tunjanganUmkBaru = tunjanganUmkBaru;
    }

    public BigDecimal getTunjanganPerumahanLama() {
        return tunjanganPerumahanLama;
    }

    public void setTunjanganPerumahanLama(BigDecimal tunjanganPerumahanLama) {
        this.tunjanganPerumahanLama = tunjanganPerumahanLama;
    }

    public BigDecimal getTotalRapel() {
        return totalRapel;
    }

    public void setTotalRapel(BigDecimal totalRapel) {
        this.totalRapel = totalRapel;
    }

    public String getRapelId() {
        return rapelId;
    }

    public void setRapelId(String rapelId) {
        this.rapelId = rapelId;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public BigDecimal getGajiGolonganLama() {
        return gajiGolonganLama;
    }

    public void setGajiGolonganLama(BigDecimal gajiGolonganLama) {
        this.gajiGolonganLama = gajiGolonganLama;
    }

    public BigDecimal getTunjanganStrukturalLama() {
        return tunjanganStrukturalLama;
    }

    public void setTunjanganStrukturalLama(BigDecimal tunjanganStrukturalLama) {
        this.tunjanganStrukturalLama = tunjanganStrukturalLama;
    }

    public BigDecimal getTunjanganJabatanStrukturalLama() {
        return tunjanganJabatanStrukturalLama;
    }

    public void setTunjanganJabatanStrukturalLama(BigDecimal tunjanganJabatanStrukturalLama) {
        this.tunjanganJabatanStrukturalLama = tunjanganJabatanStrukturalLama;
    }

    public BigDecimal getTunjanganStrategisLama() {
        return tunjanganStrategisLama;
    }

    public void setTunjanganStrategisLama(BigDecimal tunjanganStrategisLama) {
        this.tunjanganStrategisLama = tunjanganStrategisLama;
    }

    public BigDecimal getTunjanganAirListrikLama() {
        return tunjanganAirListrikLama;
    }

    public void setTunjanganAirListrikLama(BigDecimal tunjanganAirListrikLama) {
        this.tunjanganAirListrikLama = tunjanganAirListrikLama;
    }

    public BigDecimal getTunjanganUmkLama() {
        return tunjanganUmkLama;
    }

    public void setTunjanganUmkLama(BigDecimal tunjanganUmkLama) {
        this.tunjanganUmkLama = tunjanganUmkLama;
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