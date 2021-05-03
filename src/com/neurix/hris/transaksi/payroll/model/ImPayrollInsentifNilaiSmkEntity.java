package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ImPayrollInsentifNilaiSmkEntity implements Serializable {
    private String insentifNilaiSmkId;
    private String nip;
    private BigDecimal nilaiSmkInsentif;
    private String nilaiHuruf;
    private int jumlahBulan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public int getJumlahBulan() {
        return jumlahBulan;
    }

    public void setJumlahBulan(int jumlahBulan) {
        this.jumlahBulan = jumlahBulan;
    }

    public String getNilaiHuruf() {
        return nilaiHuruf;
    }

    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }

    public String getInsentifNilaiSmkId() {
        return insentifNilaiSmkId;
    }

    public void setInsentifNilaiSmkId(String insentifNilaiSmkId) {
        this.insentifNilaiSmkId = insentifNilaiSmkId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getNilaiSmkInsentif() {
        return nilaiSmkInsentif;
    }

    public void setNilaiSmkInsentif(BigDecimal nilaiSmkInsentif) {
        this.nilaiSmkInsentif = nilaiSmkInsentif;
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