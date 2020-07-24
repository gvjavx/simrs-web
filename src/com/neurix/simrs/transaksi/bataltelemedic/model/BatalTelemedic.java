package com.neurix.simrs.transaksi.bataltelemedic.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 23/07/20.
 */
public class BatalTelemedic {
    private String id;
    private String idAntrianTelemedic;
    private String idDokterBatal;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String noJurnal;
    private String alasan;
    private BigDecimal kembaliKonsultasi;
    private String flagKembaliKonsultasi;
    private BigDecimal kembaliResep;
    private String flagKembaliResep;
    private String idTransaksiOnline;

    public String getIdTransaksiOnline() {
        return idTransaksiOnline;
    }

    public void setIdTransaksiOnline(String idTransaksiOnline) {
        this.idTransaksiOnline = idTransaksiOnline;
    }

    public BigDecimal getKembaliKonsultasi() {
        return kembaliKonsultasi;
    }

    public void setKembaliKonsultasi(BigDecimal kembaliKonsultasi) {
        this.kembaliKonsultasi = kembaliKonsultasi;
    }

    public String getFlagKembaliKonsultasi() {
        return flagKembaliKonsultasi;
    }

    public void setFlagKembaliKonsultasi(String flagKembaliKonsultasi) {
        this.flagKembaliKonsultasi = flagKembaliKonsultasi;
    }

    public BigDecimal getKembaliResep() {
        return kembaliResep;
    }

    public void setKembaliResep(BigDecimal kembaliResep) {
        this.kembaliResep = kembaliResep;
    }

    public String getFlagKembaliResep() {
        return flagKembaliResep;
    }

    public void setFlagKembaliResep(String flagKembaliResep) {
        this.flagKembaliResep = flagKembaliResep;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAntrianTelemedic() {
        return idAntrianTelemedic;
    }

    public void setIdAntrianTelemedic(String idAntrianTelemedic) {
        this.idAntrianTelemedic = idAntrianTelemedic;
    }

    public String getIdDokterBatal() {
        return idDokterBatal;
    }

    public void setIdDokterBatal(String idDokterBatal) {
        this.idDokterBatal = idDokterBatal;
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

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }
}
