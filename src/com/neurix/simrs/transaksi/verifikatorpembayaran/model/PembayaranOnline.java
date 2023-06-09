package com.neurix.simrs.transaksi.verifikatorpembayaran.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 10/06/20.
 */
public class PembayaranOnline {
    private String id;
    private String idItem;
    private String keterangan;
    private String idAntrianTelemedic;
    private String urlFotoBukti;
    private String approvedFlag;
    private String approvedWho;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private BigDecimal nominal;
    private String idRiwayatTindakan;
    private String kodeBank;
    private String flagBayar;
    private String jenisPengambilan;
    private String alamat;
    private String message;
    private String flagEresep;
    private String noKartu;
    private BigDecimal jumlahCover;
    private String namaAsuransi;
    private String noSep;
    private String flagAuthorization;
    private String flagConfirmation;
    private String idRekening;
    private Timestamp waktuBayar;
    private String flagUploadUlang;

    public String getFlagUploadUlang() {
        return flagUploadUlang;
    }

    public void setFlagUploadUlang(String flagUploadUlang) {
        this.flagUploadUlang = flagUploadUlang;
    }

    public Timestamp getWaktuBayar() {
        return waktuBayar;
    }

    public void setWaktuBayar(Timestamp waktuBayar) {
        this.waktuBayar = waktuBayar;
    }

    public String getIdRekening() {
        return idRekening;
    }

    public void setIdRekening(String idRekening) {
        this.idRekening = idRekening;
    }

    public String getFlagAuthorization() {
        return flagAuthorization;
    }

    public void setFlagAuthorization(String flagAuthorization) {
        this.flagAuthorization = flagAuthorization;
    }

    public String getFlagConfirmation() {
        return flagConfirmation;
    }

    public void setFlagConfirmation(String flagConfirmation) {
        this.flagConfirmation = flagConfirmation;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public BigDecimal getJumlahCover() {
        return jumlahCover;
    }

    public void setJumlahCover(BigDecimal jumlahCover) {
        this.jumlahCover = jumlahCover;
    }

    public String getFlagEresep() {
        return flagEresep;
    }

    public void setFlagEresep(String flagEresep) {
        this.flagEresep = flagEresep;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getFlagBayar() {
        return flagBayar;
    }

    public void setFlagBayar(String flagBayar) {
        this.flagBayar = flagBayar;
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

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdAntrianTelemedic() {
        return idAntrianTelemedic;
    }

    public void setIdAntrianTelemedic(String idAntrianTelemedic) {
        this.idAntrianTelemedic = idAntrianTelemedic;
    }

    public String getUrlFotoBukti() {
        return urlFotoBukti;
    }

    public void setUrlFotoBukti(String urlFotoBukti) {
        this.urlFotoBukti = urlFotoBukti;
    }

    public String getApprovedFlag() {
        return approvedFlag;
    }

    public void setApprovedFlag(String approvedFlag) {
        this.approvedFlag = approvedFlag;
    }

    public String getApprovedWho() {
        return approvedWho;
    }

    public void setApprovedWho(String approvedWho) {
        this.approvedWho = approvedWho;
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

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public String getIdRiwayatTindakan() {
        return idRiwayatTindakan;
    }

    public void setIdRiwayatTindakan(String idRiwayatTindakan) {
        this.idRiwayatTindakan = idRiwayatTindakan;
    }
}
