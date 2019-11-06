package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import java.sql.Date;
import com.neurix.common.model.BaseModel;

public class JadwalKerjaDTO extends BaseModel {
    private String no;
    private String nama;
    private String senin;
    private String selasa;
    private String rabu;
    private String kamis;
    private String jumat;
    private String sabtu;
    private String minggu;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSenin() {
        return senin;
    }

    public void setSenin(String senin) {
        this.senin = senin;
    }

    public String getSelasa() {
        return selasa;
    }

    public void setSelasa(String selasa) {
        this.selasa = selasa;
    }

    public String getRabu() {
        return rabu;
    }

    public void setRabu(String rabu) {
        this.rabu = rabu;
    }

    public String getKamis() {
        return kamis;
    }

    public void setKamis(String kamis) {
        this.kamis = kamis;
    }

    public String getJumat() {
        return jumat;
    }

    public void setJumat(String jumat) {
        this.jumat = jumat;
    }

    public String getSabtu() {
        return sabtu;
    }

    public void setSabtu(String sabtu) {
        this.sabtu = sabtu;
    }

    public String getMinggu() {
        return minggu;
    }

    public void setMinggu(String minggu) {
        this.minggu = minggu;
    }
}
