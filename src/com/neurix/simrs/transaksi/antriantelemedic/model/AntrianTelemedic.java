package com.neurix.simrs.transaksi.antriantelemedic.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 08/06/20.
 */
public class AntrianTelemedic {
    private String id;
    private String idPasien;
    private String idDokter;
    private String idPelayanan;
    private String flagResep;
    private String status;
    private BigInteger biayaKonsultasi;
    private String flagBayarKonsultasi;
    private String flagBayarResep;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String namaPasien;
    private String namaDokter;
    private String namaPelayanan;
    private String ketFlagResep;
    private String ketStatus;
    private String asuransi;
    private String noKartu;
    private String idJenisPeriksaPasien;
    private String idAsuransi;
    private String kodeBank;

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getKetFlagResep() {
        return ketFlagResep;
    }

    public void setKetFlagResep(String ketFlagResep) {
        this.ketFlagResep = ketFlagResep;
    }

    public String getKetStatus() {
        return ketStatus;
    }

    public void setKetStatus(String ketStatus) {
        this.ketStatus = ketStatus;
    }

    public String getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(String asuransi) {
        this.asuransi = asuransi;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlagResep() {
        return flagResep;
    }

    public void setFlagResep(String flagResep) {
        this.flagResep = flagResep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getBiayaKonsultasi() {
        return biayaKonsultasi;
    }

    public void setBiayaKonsultasi(BigInteger biayaKonsultasi) {
        this.biayaKonsultasi = biayaKonsultasi;
    }

    public String getFlagBayarKonsultasi() {
        return flagBayarKonsultasi;
    }

    public void setFlagBayarKonsultasi(String flagBayarKonsultasi) {
        this.flagBayarKonsultasi = flagBayarKonsultasi;
    }

    public String getFlagBayarResep() {
        return flagBayarResep;
    }

    public void setFlagBayarResep(String flagBayarResep) {
        this.flagBayarResep = flagBayarResep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AntrianTelemedic that = (AntrianTelemedic) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idPasien != null ? !idPasien.equals(that.idPasien) : that.idPasien != null) return false;
        if (idDokter != null ? !idDokter.equals(that.idDokter) : that.idDokter != null) return false;
        if (idPelayanan != null ? !idPelayanan.equals(that.idPelayanan) : that.idPelayanan != null) return false;
        if (flagResep != null ? !flagResep.equals(that.flagResep) : that.flagResep != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (biayaKonsultasi != null ? !biayaKonsultasi.equals(that.biayaKonsultasi) : that.biayaKonsultasi != null)
            return false;
        if (flagBayarKonsultasi != null ? !flagBayarKonsultasi.equals(that.flagBayarKonsultasi) : that.flagBayarKonsultasi != null)
            return false;
        if (flagBayarResep != null ? !flagBayarResep.equals(that.flagBayarResep) : that.flagBayarResep != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idPasien != null ? idPasien.hashCode() : 0);
        result = 31 * result + (idDokter != null ? idDokter.hashCode() : 0);
        result = 31 * result + (idPelayanan != null ? idPelayanan.hashCode() : 0);
        result = 31 * result + (flagResep != null ? flagResep.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (biayaKonsultasi != null ? biayaKonsultasi.hashCode() : 0);
        result = 31 * result + (flagBayarKonsultasi != null ? flagBayarKonsultasi.hashCode() : 0);
        result = 31 * result + (flagBayarResep != null ? flagBayarResep.hashCode() : 0);
        return result;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

}
