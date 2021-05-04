package com.neurix.hris.transaksi.smk.model;

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

public class ItHistorySmkEvaluasiPegawaiAspekDetailEntity implements Serializable {

    private int id ;
    private String evaluasiPegawaiAspekDetailId ;
    private String evaluasiPegawaiAspekId ;
    private String target ;
    private String realisasi ;
    private BigDecimal nilai;
    private BigDecimal nilaiPrestasi;
    private BigDecimal bobot;
    private String subAspekId;
    private String uraian;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBobot() {
        return bobot;
    }

    public void setBobot(BigDecimal bobot) {
        this.bobot = bobot;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiPrestasi() {
        return nilaiPrestasi;
    }

    public void setNilaiPrestasi(BigDecimal nilaiPrestasi) {
        this.nilaiPrestasi = nilaiPrestasi;
    }

    private ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity;

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

    public String getEvaluasiPegawaiAspekDetailId() {
        return evaluasiPegawaiAspekDetailId;
    }

    public void setEvaluasiPegawaiAspekDetailId(String evaluasiPegawaiAspekDetailId) {
        this.evaluasiPegawaiAspekDetailId = evaluasiPegawaiAspekDetailId;
    }

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ItSmkEvaluasiPegawaiAspekEntity getItSmkEvaluasiPegawaiAspekEntity() {
        return itSmkEvaluasiPegawaiAspekEntity;
    }

    public void setItSmkEvaluasiPegawaiAspekEntity(ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity) {
        this.itSmkEvaluasiPegawaiAspekEntity = itSmkEvaluasiPegawaiAspekEntity;
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

    public BigDecimal getNilai() {
        return nilai;
    }

    public String getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(String realisasi) {
        this.realisasi = realisasi;
    }

    public String getSubAspekId() {
        return subAspekId;
    }

    public void setSubAspekId(String subAspekId) {
        this.subAspekId = subAspekId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }
}
