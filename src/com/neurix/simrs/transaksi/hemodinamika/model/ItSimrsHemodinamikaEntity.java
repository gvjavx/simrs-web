package com.neurix.simrs.transaksi.hemodinamika.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsHemodinamikaEntity {

    private String idHemodinamika;
    private String idDetailCheckup;
    private String waktu;
    private Date tanggal;
    private Integer tensi;
    private Integer bp;
    private Integer hi;
    private Integer rr;
    private String ekg;
    private Integer icp;
    private Integer ibp;
    private Integer cvp;
    private Integer map;
    private String keterangan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private Integer sistole;
    private Integer diastole;

    public Integer getSistole() {
        return sistole;
    }

    public void setSistole(Integer sistole) {
        this.sistole = sistole;
    }

    public Integer getDiastole() {
        return diastole;
    }

    public void setDiastole(Integer diastole) {
        this.diastole = diastole;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIdHemodinamika() {
        return idHemodinamika;
    }

    public void setIdHemodinamika(String idHemodinamika) {
        this.idHemodinamika = idHemodinamika;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Integer getTensi() {
        return tensi;
    }

    public void setTensi(Integer tensi) {
        this.tensi = tensi;
    }

    public Integer getBp() {
        return bp;
    }

    public void setBp(Integer bp) {
        this.bp = bp;
    }

    public Integer getHi() {
        return hi;
    }

    public void setHi(Integer hi) {
        this.hi = hi;
    }

    public Integer getRr() {
        return rr;
    }

    public void setRr(Integer rr) {
        this.rr = rr;
    }

    public String getEkg() {
        return ekg;
    }

    public void setEkg(String ekg) {
        this.ekg = ekg;
    }

    public Integer getIcp() {
        return icp;
    }

    public void setIcp(Integer icp) {
        this.icp = icp;
    }

    public Integer getIbp() {
        return ibp;
    }

    public void setIbp(Integer ibp) {
        this.ibp = ibp;
    }

    public Integer getCvp() {
        return cvp;
    }

    public void setCvp(Integer cvp) {
        this.cvp = cvp;
    }

    public Integer getMap() {
        return map;
    }

    public void setMap(Integer map) {
        this.map = map;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItSimrsHemodinamikaEntity that = (ItSimrsHemodinamikaEntity) o;
        return Objects.equals(idHemodinamika, that.idHemodinamika) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(tensi, that.tensi) &&
                Objects.equals(bp, that.bp) &&
                Objects.equals(hi, that.hi) &&
                Objects.equals(rr, that.rr) &&
                Objects.equals(ekg, that.ekg) &&
                Objects.equals(icp, that.icp) &&
                Objects.equals(ibp, that.ibp) &&
                Objects.equals(cvp, that.cvp) &&
                Objects.equals(map, that.map) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHemodinamika, idDetailCheckup, tensi, bp, hi, rr, ekg, icp, ibp, cvp, map, keterangan, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho);
    }
}
