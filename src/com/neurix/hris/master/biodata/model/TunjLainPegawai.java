package com.neurix.hris.master.biodata.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class TunjLainPegawai extends BaseModel {
    private String tunjLainId;
    private String nip;
    private String flagTunjSupervisi;
    private String flagTunjLokasi;
    private String flagTunjSiaga;
    private String flagTunjProfesional;
    private String flagTunjPeralihanGapok;
    private String flagTunjPeralihanSankhus;
    private String flagTunjPeralihanTunjangan;
    private String flagTunjPemondokan;
    private BigDecimal tunjPeralihanGapok;
    private BigDecimal tunjPeralihanSankhus;
    private BigDecimal tunjPeralihanTunjangan;

    public String getFlagTunjPeralihanGapok() {
        return flagTunjPeralihanGapok;
    }

    public void setFlagTunjPeralihanGapok(String flagTunjPeralihanGapok) {
        this.flagTunjPeralihanGapok = flagTunjPeralihanGapok;
    }

    public String getFlagTunjPeralihanSankhus() {
        return flagTunjPeralihanSankhus;
    }

    public void setFlagTunjPeralihanSankhus(String flagTunjPeralihanSankhus) {
        this.flagTunjPeralihanSankhus = flagTunjPeralihanSankhus;
    }

    public String getFlagTunjPeralihanTunjangan() {
        return flagTunjPeralihanTunjangan;
    }

    public void setFlagTunjPeralihanTunjangan(String flagTunjPeralihanTunjangan) {
        this.flagTunjPeralihanTunjangan = flagTunjPeralihanTunjangan;
    }

    public String getFlagTunjPemondokan() {
        return flagTunjPemondokan;
    }

    public void setFlagTunjPemondokan(String flagTunjPemondokan) {
        this.flagTunjPemondokan = flagTunjPemondokan;
    }

    public BigDecimal getTunjPeralihanGapok() {
        return tunjPeralihanGapok;
    }

    public void setTunjPeralihanGapok(BigDecimal tunjPeralihanGapok) {
        this.tunjPeralihanGapok = tunjPeralihanGapok;
    }

    public BigDecimal getTunjPeralihanSankhus() {
        return tunjPeralihanSankhus;
    }

    public void setTunjPeralihanSankhus(BigDecimal tunjPeralihanSankhus) {
        this.tunjPeralihanSankhus = tunjPeralihanSankhus;
    }

    public BigDecimal getTunjPeralihanTunjangan() {
        return tunjPeralihanTunjangan;
    }

    public void setTunjPeralihanTunjangan(BigDecimal tunjPeralihanTunjangan) {
        this.tunjPeralihanTunjangan = tunjPeralihanTunjangan;
    }

    public String getFlagTunjLokasi() {
        return flagTunjLokasi;
    }

    public void setFlagTunjLokasi(String flagTunjLokasi) {
        this.flagTunjLokasi = flagTunjLokasi;
    }

    public String getFlagTunjProfesional() {
        return flagTunjProfesional;
    }

    public void setFlagTunjProfesional(String flagTunjProfesional) {
        this.flagTunjProfesional = flagTunjProfesional;
    }

    public String getFlagTunjSiaga() {
        return flagTunjSiaga;
    }

    public void setFlagTunjSiaga(String flagTunjSiaga) {
        this.flagTunjSiaga = flagTunjSiaga;
    }

    public String getFlagTunjSupervisi() {
        return flagTunjSupervisi;
    }

    public void setFlagTunjSupervisi(String flagTunjSupervisi) {
        this.flagTunjSupervisi = flagTunjSupervisi;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTunjLainId() {
        return tunjLainId;
    }

    public void setTunjLainId(String tunjLainId) {
        this.tunjLainId = tunjLainId;
    }
}