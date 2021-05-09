package com.neurix.hris.transaksi.medicalKacamata.model;

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
public class MedicalKacamata extends BaseModel {
    private String medicalKacamataId;
    private String nip;
    private String namaPegawai;
    private String positionId;
    private String positionName;
    private String bidangId;
    private String bidangName;
    private String bagianId;
    private String bagianName;
    private String branchId;
    private String branchName;
    private String golonganId;
    private String tipePembayaran;
    private Date tanggalPembayaran;
    private String strTanggalPembayaran;
    private Date tanggalBerakhir;
    private String strTanggalBerakhir;
    private BigDecimal biaya;
    private String strBiaya;
    private String strBiayaGagang;
    private String strBiayaLensa;

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getStrTanggalBerakhir() {
        return strTanggalBerakhir;
    }

    public void setStrTanggalBerakhir(String strTanggalBerakhir) {
        this.strTanggalBerakhir = strTanggalBerakhir;
    }

    public String getStrTanggalPembayaran() {
        return strTanggalPembayaran;
    }

    public void setStrTanggalPembayaran(String strTanggalPembayaran) {
        this.strTanggalPembayaran = strTanggalPembayaran;
    }

    public String getStrBiaya() {
        return strBiaya;
    }

    public void setStrBiaya(String strBiaya) {
        this.strBiaya = strBiaya;
    }

    public String getStrBiayaGagang() {
        return strBiayaGagang;
    }

    public void setStrBiayaGagang(String strBiayaGagang) {
        this.strBiayaGagang = strBiayaGagang;
    }

    public String getStrBiayaLensa() {
        return strBiayaLensa;
    }

    public void setStrBiayaLensa(String strBiayaLensa) {
        this.strBiayaLensa = strBiayaLensa;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getBidangName() {
        return bidangName;
    }

    public void setBidangName(String bidangName) {
        this.bidangName = bidangName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public BigDecimal getBiaya() {
        return biaya;
    }

    public void setBiaya(BigDecimal biaya) {
        this.biaya = biaya;
    }

    public String getBidangId() {
        return bidangId;
    }

    public void setBidangId(String bidangId) {
        this.bidangId = bidangId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getMedicalKacamataId() {
        return medicalKacamataId;
    }

    public void setMedicalKacamataId(String medicalKacamataId) {
        this.medicalKacamataId = medicalKacamataId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Date getTanggalBerakhir() {
        return tanggalBerakhir;
    }

    public void setTanggalBerakhir(Date tanggalBerakhir) {
        this.tanggalBerakhir = tanggalBerakhir;
    }

    public Date getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(Date tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public String getTipePembayaran() {
        return tipePembayaran;
    }

    public void setTipePembayaran(String tipePembayaran) {
        this.tipePembayaran = tipePembayaran;
    }
}