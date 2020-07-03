package com.neurix.hris.mobileapi.model;

import java.io.Serializable;
import java.util.Date;

public class Notifikasi implements Serializable {

    private String notifId;
    private String cutiPegawaiId;
    private String nip;
    private String namaPegawai;
    private Date tanggalDari;
    private String tanggalDariSt;
    private Date tanggalSelesai;
    private String tanggalSelesaiSt;
    private Integer lamaCuti;
    private String unit;
    private String departement;
    private String position;
    private String jamAwal;
    private String jamSelesai;
    private String note;
    private String read;
    private String tanggal;

    private String actionError;

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNotifId() {
        return notifId;
    }

    public void setNotifId(String notifId) {
        this.notifId = notifId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTanggalDariSt() {
        return tanggalDariSt;
    }

    public void setTanggalDariSt(String tanggalDariSt) {
        this.tanggalDariSt = tanggalDariSt;
    }

    public String getTanggalSelesaiSt() {
        return tanggalSelesaiSt;
    }

    public void setTanggalSelesaiSt(String tanggalSelesaiSt) {
        this.tanggalSelesaiSt = tanggalSelesaiSt;
    }

    public String getCutiPegawaiId() {
        return cutiPegawaiId;
    }

    public void setCutiPegawaiId(String cutiPegawaiId) {
        this.cutiPegawaiId = cutiPegawaiId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public Date getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(Date tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public Integer getLamaCuti() {
        return lamaCuti;
    }

    public void setLamaCuti(Integer lamaCuti) {
        this.lamaCuti = lamaCuti;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getActionError() {
        return actionError;
    }

    public void setActionError(String actionError) {
        this.actionError = actionError;
    }
}
