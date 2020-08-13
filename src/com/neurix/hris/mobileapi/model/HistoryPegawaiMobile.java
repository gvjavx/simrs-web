package com.neurix.hris.mobileapi.model;

/**
 * @author gondok
 * Tuesday, 11/08/20 13:33
 */
public class HistoryPegawaiMobile {

    private String jumlahHadir;
    private String jumlahDispen;
    private String sisaCuti;
    private String bulan;
    private String tahun;

    private String ijinId;
    private String ijinKeluarName;
    private String lamaIjin;
    private String tanggalAwal;
    private String tanggalAkhir;

    private String absensiPegawaiId;
    private String jamDatang;
    private String jamPulang;
    private String status;
    private String namaStatus;
    private String tanggalAbsen;

    private String isLembur;
    private String isDispen;

    public String getNamaStatus() {
        return namaStatus;
    }

    public void setNamaStatus(String namaStatus) {
        this.namaStatus = namaStatus;
    }

    public String getIsLembur() {
        return isLembur;
    }

    public void setIsLembur(String isLembur) {
        this.isLembur = isLembur;
    }

    public String getIsDispen() {
        return isDispen;
    }

    public void setIsDispen(String isDispen) {
        this.isDispen = isDispen;
    }

    public String getAbsensiPegawaiId() {
        return absensiPegawaiId;
    }

    public void setAbsensiPegawaiId(String absensiPegawaiId) {
        this.absensiPegawaiId = absensiPegawaiId;
    }

    public String getIjinId() {
        return ijinId;
    }

    public void setIjinId(String ijinId) {
        this.ijinId = ijinId;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggalAbsen() {
        return tanggalAbsen;
    }

    public void setTanggalAbsen(String tanggalAbsen) {
        this.tanggalAbsen = tanggalAbsen;
    }

    public String getJamDatang() {
        return jamDatang;
    }

    public void setJamDatang(String jamDatang) {
        this.jamDatang = jamDatang;
    }

    private String message;

    public String getIjinKeluarName() {
        return ijinKeluarName;
    }

    public void setIjinKeluarName(String ijinKeluarName) {
        this.ijinKeluarName = ijinKeluarName;
    }

    public String getLamaIjin() {
        return lamaIjin;
    }

    public void setLamaIjin(String lamaIjin) {
        this.lamaIjin = lamaIjin;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(String tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJumlahHadir() {
        return jumlahHadir;
    }

    public void setJumlahHadir(String jumlahHadir) {
        this.jumlahHadir = jumlahHadir;
    }

    public String getJumlahDispen() {
        return jumlahDispen;
    }

    public void setJumlahDispen(String jumlahDispen) {
        this.jumlahDispen = jumlahDispen;
    }

    public String getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(String sisaCuti) {
        this.sisaCuti = sisaCuti;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
