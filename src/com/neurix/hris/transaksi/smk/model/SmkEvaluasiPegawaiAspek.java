package com.neurix.hris.transaksi.smk.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SmkEvaluasiPegawaiAspek extends BaseModel {
    private String evaluasiPegawaiAspekId ;
    private String evaluasiPegawaiId;
    private String tipeAspekId;
    private double pointPrestasi;

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }

    public String getEvaluasiPegawaiId() {
        return evaluasiPegawaiId;
    }

    public void setEvaluasiPegawaiId(String evaluasiPegawaiId) {
        this.evaluasiPegawaiId = evaluasiPegawaiId;
    }

    public Double getPointPrestasi() {
        return pointPrestasi;
    }

    public void setPointPrestasi(Double pointPrestasi) {
        this.pointPrestasi = pointPrestasi;
    }

    public String getTipeAspekId() {
        return tipeAspekId;
    }

    public void setTipeAspekId(String tipeAspekId) {
        this.tipeAspekId = tipeAspekId;
    }
}