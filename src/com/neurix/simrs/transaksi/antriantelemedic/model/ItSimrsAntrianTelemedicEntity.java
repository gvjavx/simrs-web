package com.neurix.simrs.transaksi.antriantelemedic.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 08/06/20.
 */
public class ItSimrsAntrianTelemedicEntity {
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
    private String noKartu;
    private String idJenisPeriksaPasien;
    private String idAsuransi;
    private String kodeBank;
    private String branchId;
    private String keluhan;
    private String flagEresep;
    private String urlFotoResep;
    private String jenisPengambilan;
    private String lat;
    private String lon;
    private String alamat;
    private String noTelp;
    private BigDecimal jumlahCover;
    private String jenisPembayaran;
    private String noJurnal;
    private String noRujukan;
    private String jenisRujukan;
    private String noSep;
    private String idDiagnosa;
    private String ketDiagnosa;

    public String getIdDiagnosa() {
        return idDiagnosa;
    }

    public void setIdDiagnosa(String idDiagnosa) {
        this.idDiagnosa = idDiagnosa;
    }

    public String getKetDiagnosa() {
        return ketDiagnosa;
    }

    public void setKetDiagnosa(String ketDiagnosa) {
        this.ketDiagnosa = ketDiagnosa;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getJenisRujukan() {
        return jenisRujukan;
    }

    public void setJenisRujukan(String jenisRujukan) {
        this.jenisRujukan = jenisRujukan;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigDecimal getJumlahCover() {
        return jumlahCover;
    }

    public void setJumlahCover(BigDecimal jumlahCover) {
        this.jumlahCover = jumlahCover;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisPengambilan() {
        return jenisPengambilan;
    }

    public void setJenisPengambilan(String jenisPengambilan) {
        this.jenisPengambilan = jenisPengambilan;
    }

    public String getFlagEresep() {
        return flagEresep;
    }

    public void setFlagEresep(String flagEresep) {
        this.flagEresep = flagEresep;
    }

    public String getUrlFotoResep() {
        return urlFotoResep;
    }

    public void setUrlFotoResep(String urlFotoResep) {
        this.urlFotoResep = urlFotoResep;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
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

        ItSimrsAntrianTelemedicEntity that = (ItSimrsAntrianTelemedicEntity) o;

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

    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }
}
