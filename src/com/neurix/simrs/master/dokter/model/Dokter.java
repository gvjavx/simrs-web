package com.neurix.simrs.master.dokter.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class Dokter {
    private String idDokter;
    private String namaDokter;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String stCreatedDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String stLastUpdate;
    private String lastUpdateWho;
    private String idPelayanan;

    private String idSpesialis;
    private String namaSpesialis;
    private String namaPelayanan;

    private String kuota;
    private String kodeDpjp;
    private String kodering;
    private String positionId;

    private String kode;

    private String flagCall;
    private String flagTele;
    private String kuotaTele;
    private String kuotaBpjs;
    private String sip;
    private BigInteger kuotaOnSite;

    private String idTeamDokter;
    private String flagApprove;
    private String jenisDpjp;
    private String keterangan;


    public String getKuotaBpjs() {
        return kuotaBpjs;
    }

    public void setKuotaBpjs(String kuotaBpjs) {
        this.kuotaBpjs = kuotaBpjs;
    }

    public String getFlagApprove() {
        return flagApprove;
    }

    public void setFlagApprove(String flagApprove) {
        this.flagApprove = flagApprove;
    }

    public String getJenisDpjp() {
        return jenisDpjp;
    }

    public void setJenisDpjp(String jenisDpjp) {
        this.jenisDpjp = jenisDpjp;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigInteger getKuotaOnSite() {
        return kuotaOnSite;
    }

    public void setKuotaOnSite(BigInteger kuotaOnSite) {
        this.kuotaOnSite = kuotaOnSite;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getIdTeamDokter() {
        return idTeamDokter;
    }

    public void setIdTeamDokter(String idTeamDokter) {
        this.idTeamDokter = idTeamDokter;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKuotaTele() {
        return kuotaTele;
    }

    public void setKuotaTele(String kuotaTele) {
        this.kuotaTele = kuotaTele;
    }

    public String getFlagTele() {
        return flagTele;
    }

    public void setFlagTele(String flagTele) {
        this.flagTele = flagTele;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    public String getKodeDpjp() {
        return kodeDpjp;
    }

    public void setKodeDpjp(String kodeDpjp) {
        this.kodeDpjp = kodeDpjp;
    }

    private String lat;
    private String lon;

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

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
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

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }

    public String getNamaSpesialis() {
        return namaSpesialis;
    }

    public void setNamaSpesialis(String namaSpesialis) {
        this.namaSpesialis = namaSpesialis;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }
}
