package com.neurix.hris.transaksi.smk.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SmkHistoryEvaluasiPegawai extends BaseModel {
    private String historyEvaluasiPegawaiId;
    private String nip;
    private String pegawaiName;
    private String golonganId;
    private String golonganName;
    private String periode;
    private String nilaiPrestasi;

    private int poin;
    private double nilai;

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getHistoryEvaluasiPegawaiId() {
        return historyEvaluasiPegawaiId;
    }

    public void setHistoryEvaluasiPegawaiId(String historyEvaluasiPegawaiId) {
        this.historyEvaluasiPegawaiId = historyEvaluasiPegawaiId;
    }

    public String getNilaiPrestasi() {
        return nilaiPrestasi;
    }

    public void setNilaiPrestasi(String nilaiPrestasi) {
        this.nilaiPrestasi = nilaiPrestasi;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPegawaiName() {
        return pegawaiName;
    }

    public void setPegawaiName(String pegawaiName) {
        this.pegawaiName = pegawaiName;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
}