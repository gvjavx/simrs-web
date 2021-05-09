package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PegawaiTambahan extends BaseModel {
    private String pegawaiTambahanId;
    private String nama;
    private String pin;
    private String bagian;

    private String stTanggalDari;
    private String stTanggalSelesai;

    public String getStTanggalDari() {
        return stTanggalDari;
    }

    public void setStTanggalDari(String stTanggalDari) {
        this.stTanggalDari = stTanggalDari;
    }

    public String getStTanggalSelesai() {
        return stTanggalSelesai;
    }

    public void setStTanggalSelesai(String stTanggalSelesai) {
        this.stTanggalSelesai = stTanggalSelesai;
    }

    public String getPegawaiTambahanId() {
        return pegawaiTambahanId;
    }

    public void setPegawaiTambahanId(String pegawaiTambahanId) {
        this.pegawaiTambahanId = pegawaiTambahanId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }
}