package com.neurix.hris.transaksi.smk.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SmkEvaluasiPegawaiAspekDetail extends BaseModel {
    private String evaluasiPegawaiAspekDetailId ;
    private String evaluasiPegawaiAspekId ;
    private String target ;
    private String realisasi ;
    private double nilai;
    private String subAspekId;
    private String uraian;

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

    public Double getNilai() {
        return nilai;
    }

    public void setNilai(Double nilai) {
        this.nilai = nilai;
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