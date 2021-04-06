package com.neurix.simrs.transaksi.asesmenrawatinap.model;

import java.util.ArrayList;
import java.util.List;

public class PersetujuanTindakanMedis {

    private String parameter;
    private String jawaban;
    private String tipe;
    private String informasi;
    private String ttdPihak1;
    private String ttdPihak2;
    private String ttdMenyatakan;
    private String pihak1;
    private String pihak2;
    private String namaMenyatakan;
    private String sipMenyatakan;
    private String pernyataan1;
    private String pernyataan2;
    private String pernyataan3;
    private String namaPernyataan1;
    private String namaPernyataan2;
    private String sipPernyataan1;
    private String ttdPernyataan1;
    private String ttdPernyataan2;

    public String getTtdPernyataan1() {
        return ttdPernyataan1;
    }

    public void setTtdPernyataan1(String ttdPernyataan1) {
        this.ttdPernyataan1 = ttdPernyataan1;
    }

    public String getTtdPernyataan2() {
        return ttdPernyataan2;
    }

    public void setTtdPernyataan2(String ttdPernyataan2) {
        this.ttdPernyataan2 = ttdPernyataan2;
    }

    public String getPernyataan1() {
        return pernyataan1;
    }

    public void setPernyataan1(String pernyataan1) {
        this.pernyataan1 = pernyataan1;
    }

    public String getPernyataan2() {
        return pernyataan2;
    }

    public void setPernyataan2(String pernyataan2) {
        this.pernyataan2 = pernyataan2;
    }

    public String getPernyataan3() {
        return pernyataan3;
    }

    public void setPernyataan3(String pernyataan3) {
        this.pernyataan3 = pernyataan3;
    }

    public String getNamaPernyataan1() {
        return namaPernyataan1;
    }

    public void setNamaPernyataan1(String namaPernyataan1) {
        this.namaPernyataan1 = namaPernyataan1;
    }

    public String getNamaPernyataan2() {
        return namaPernyataan2;
    }

    public void setNamaPernyataan2(String namaPernyataan2) {
        this.namaPernyataan2 = namaPernyataan2;
    }

    public String getSipPernyataan1() {
        return sipPernyataan1;
    }

    public void setSipPernyataan1(String sipPernyataan1) {
        this.sipPernyataan1 = sipPernyataan1;
    }

    private List<PersetujuanTindakanMedis> tindakanMedisList = new ArrayList<>();

    public List<PersetujuanTindakanMedis> getTindakanMedisList() {
        return tindakanMedisList;
    }

    public void setTindakanMedisList(List<PersetujuanTindakanMedis> tindakanMedisList) {
        this.tindakanMedisList = tindakanMedisList;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }

    public String getTtdPihak1() {
        return ttdPihak1;
    }

    public void setTtdPihak1(String ttdPihak1) {
        this.ttdPihak1 = ttdPihak1;
    }

    public String getTtdPihak2() {
        return ttdPihak2;
    }

    public void setTtdPihak2(String ttdPihak2) {
        this.ttdPihak2 = ttdPihak2;
    }

    public String getTtdMenyatakan() {
        return ttdMenyatakan;
    }

    public void setTtdMenyatakan(String ttdMenyatakan) {
        this.ttdMenyatakan = ttdMenyatakan;
    }

    public String getPihak1() {
        return pihak1;
    }

    public void setPihak1(String pihak1) {
        this.pihak1 = pihak1;
    }

    public String getPihak2() {
        return pihak2;
    }

    public void setPihak2(String pihak2) {
        this.pihak2 = pihak2;
    }

    public String getNamaMenyatakan() {
        return namaMenyatakan;
    }

    public void setNamaMenyatakan(String namaMenyatakan) {
        this.namaMenyatakan = namaMenyatakan;
    }

    public String getSipMenyatakan() {
        return sipMenyatakan;
    }

    public void setSipMenyatakan(String sipMenyatakan) {
        this.sipMenyatakan = sipMenyatakan;
    }
}
