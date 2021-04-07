package com.neurix.simrs.transaksi.asesmenrawatinap.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AsesmenRawatInap extends BaseModel {

    private String idAsesmenKeperawatanRawatInap;
    private String idDetailCheckup;
    private String parameter;
    private String jawaban;
    private String keterangan;
    private String jenis;
    private Integer skor;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String tipe;
    private String informasi;
    private String namaTerang;
    private String sip;
    private String noCheckup;
    private String ttdPihak1;
    private String ttdPihak2;
    private String ttdMenyatakan;
    private String pihak1;
    private String pihak2;
    private String namaMenyatakan;
    private String sipMenyatakan;

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

    private List<AsesmenRawatInap> asesmenRawatInapList = new ArrayList<>();

    public List<AsesmenRawatInap> getAsesmenRawatInapList() {
        return asesmenRawatInapList;
    }

    public void setAsesmenRawatInapList(List<AsesmenRawatInap> asesmenRawatInapList) {
        this.asesmenRawatInapList = asesmenRawatInapList;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getNamaTerang() {
        return namaTerang;
    }

    public void setNamaTerang(String namaTerang) {
        this.namaTerang = namaTerang;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
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

    public String getIdAsesmenKeperawatanRawatInap() {
        return idAsesmenKeperawatanRawatInap;
    }

    public void setIdAsesmenKeperawatanRawatInap(String idAsesmenKeperawatanRawatInap) {
        this.idAsesmenKeperawatanRawatInap = idAsesmenKeperawatanRawatInap;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Integer getSkor() {
        return skor;
    }

    public void setSkor(Integer skor) {
        this.skor = skor;
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
}
