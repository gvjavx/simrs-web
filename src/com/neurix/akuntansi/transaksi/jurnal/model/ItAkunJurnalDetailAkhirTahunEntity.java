package com.neurix.akuntansi.transaksi.jurnal.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 18/09/20.
 */
public class ItAkunJurnalDetailAkhirTahunEntity {
    private String noJurnal;
    private String rekeningId;
    private String masterId;
    private String noNota;
    private BigDecimal jumlahDebit;
    private BigDecimal jumlahKredit;
    private String biaya;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String jurnalDetailId;
    private String kdBarang;
    private String pasienId;
    private String nomorRekening;
    private String divisiId;

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public BigDecimal getJumlahDebit() {
        return jumlahDebit;
    }

    public void setJumlahDebit(BigDecimal jumlahDebit) {
        this.jumlahDebit = jumlahDebit;
    }

    public BigDecimal getJumlahKredit() {
        return jumlahKredit;
    }

    public void setJumlahKredit(BigDecimal jumlahKredit) {
        this.jumlahKredit = jumlahKredit;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
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

    public String getJurnalDetailId() {
        return jurnalDetailId;
    }

    public void setJurnalDetailId(String jurnalDetailId) {
        this.jurnalDetailId = jurnalDetailId;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public String getPasienId() {
        return pasienId;
    }

    public void setPasienId(String pasienId) {
        this.pasienId = pasienId;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItAkunJurnalDetailAkhirTahunEntity that = (ItAkunJurnalDetailAkhirTahunEntity) o;

        if (noJurnal != null ? !noJurnal.equals(that.noJurnal) : that.noJurnal != null) return false;
        if (rekeningId != null ? !rekeningId.equals(that.rekeningId) : that.rekeningId != null) return false;
        if (masterId != null ? !masterId.equals(that.masterId) : that.masterId != null) return false;
        if (noNota != null ? !noNota.equals(that.noNota) : that.noNota != null) return false;
        if (jumlahDebit != null ? !jumlahDebit.equals(that.jumlahDebit) : that.jumlahDebit != null) return false;
        if (jumlahKredit != null ? !jumlahKredit.equals(that.jumlahKredit) : that.jumlahKredit != null) return false;
        if (biaya != null ? !biaya.equals(that.biaya) : that.biaya != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (jurnalDetailId != null ? !jurnalDetailId.equals(that.jurnalDetailId) : that.jurnalDetailId != null)
            return false;
        if (kdBarang != null ? !kdBarang.equals(that.kdBarang) : that.kdBarang != null) return false;
        if (pasienId != null ? !pasienId.equals(that.pasienId) : that.pasienId != null) return false;
        if (nomorRekening != null ? !nomorRekening.equals(that.nomorRekening) : that.nomorRekening != null)
            return false;
        if (divisiId != null ? !divisiId.equals(that.divisiId) : that.divisiId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noJurnal != null ? noJurnal.hashCode() : 0;
        result = 31 * result + (rekeningId != null ? rekeningId.hashCode() : 0);
        result = 31 * result + (masterId != null ? masterId.hashCode() : 0);
        result = 31 * result + (noNota != null ? noNota.hashCode() : 0);
        result = 31 * result + (jumlahDebit != null ? jumlahDebit.hashCode() : 0);
        result = 31 * result + (jumlahKredit != null ? jumlahKredit.hashCode() : 0);
        result = 31 * result + (biaya != null ? biaya.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (jurnalDetailId != null ? jurnalDetailId.hashCode() : 0);
        result = 31 * result + (kdBarang != null ? kdBarang.hashCode() : 0);
        result = 31 * result + (pasienId != null ? pasienId.hashCode() : 0);
        result = 31 * result + (nomorRekening != null ? nomorRekening.hashCode() : 0);
        result = 31 * result + (divisiId != null ? divisiId.hashCode() : 0);
        return result;
    }
}
