package com.neurix.simrs.transaksi.kandungan.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsPartografEntity {
    private String idPartograf;
    private String idDetailCheckup;
    private String waktu;
    private String djj;
    private String airKetuban;
    private String molase;
    private String pembukaan;
    private String kontraksi;
    private String oksitosin;
    private String tetes;
    private String obatCairan;
    private String nadi;
    private String tensi;
    private String suhu;
    private String rr;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String namaTerang;
    private String sip;
    private String ttd;

    public String getTtd() {
        return ttd;
    }

    public void setTtd(String ttd) {
        this.ttd = ttd;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIdPartograf() {
        return idPartograf;
    }

    public void setIdPartograf(String idPartograf) {
        this.idPartograf = idPartograf;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getDjj() {
        return djj;
    }

    public void setDjj(String djj) {
        this.djj = djj;
    }

    public String getAirKetuban() {
        return airKetuban;
    }

    public void setAirKetuban(String airKetuban) {
        this.airKetuban = airKetuban;
    }

    public String getMolase() {
        return molase;
    }

    public void setMolase(String molase) {
        this.molase = molase;
    }

    public String getPembukaan() {
        return pembukaan;
    }

    public void setPembukaan(String pembukaan) {
        this.pembukaan = pembukaan;
    }

    public String getKontraksi() {
        return kontraksi;
    }

    public void setKontraksi(String kontraksi) {
        this.kontraksi = kontraksi;
    }

    public String getOksitosin() {
        return oksitosin;
    }

    public void setOksitosin(String oksitosin) {
        this.oksitosin = oksitosin;
    }

    public String getTetes() {
        return tetes;
    }

    public void setTetes(String tetes) {
        this.tetes = tetes;
    }

    public String getObatCairan() {
        return obatCairan;
    }

    public void setObatCairan(String obatCairan) {
        this.obatCairan = obatCairan;
    }

    public String getNadi() {
        return nadi;
    }

    public void setNadi(String nadi) {
        this.nadi = nadi;
    }

    public String getTensi() {
        return tensi;
    }

    public void setTensi(String tensi) {
        this.tensi = tensi;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getNamaTerang() {
        return namaTerang;
    }

    public void setNamaTerang(String namaTerang) {
        this.namaTerang = namaTerang;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItSimrsPartografEntity that = (ItSimrsPartografEntity) o;
        return Objects.equals(idPartograf, that.idPartograf) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(djj, that.djj) &&
                Objects.equals(airKetuban, that.airKetuban) &&
                Objects.equals(molase, that.molase) &&
                Objects.equals(pembukaan, that.pembukaan) &&
                Objects.equals(kontraksi, that.kontraksi) &&
                Objects.equals(oksitosin, that.oksitosin) &&
                Objects.equals(tetes, that.tetes) &&
                Objects.equals(obatCairan, that.obatCairan) &&
                Objects.equals(nadi, that.nadi) &&
                Objects.equals(tensi, that.tensi) &&
                Objects.equals(suhu, that.suhu) &&
                Objects.equals(rr, that.rr) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(namaTerang, that.namaTerang) &&
                Objects.equals(sip, that.sip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPartograf, idDetailCheckup, djj, airKetuban, molase, pembukaan, kontraksi, oksitosin, tetes, obatCairan, nadi, tensi, suhu, rr, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho, namaTerang, sip);
    }
}
