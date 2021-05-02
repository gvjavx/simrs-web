package com.neurix.hris.transaksi.smk.model;

import com.neurix.common.model.BaseModel;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SmkEvaluasiPegawaiAspekActivityPeristiwa extends BaseModel {
    private String evaluasiActivityPeristiwaId;
    private String aspekActivityMonthlyId;
    private java.sql.Date tanggalKejadian ;
    private String stTanggalKejadian ;
    private String kejadian;

    public String getStTanggalKejadian() {
        return stTanggalKejadian;
    }

    public void setStTanggalKejadian(String stTanggalKejadian) {
        this.stTanggalKejadian = stTanggalKejadian;
    }

    public String getAspekActivityMonthlyId() {
        return aspekActivityMonthlyId;
    }

    public void setAspekActivityMonthlyId(String aspekActivityMonthlyId) {
        this.aspekActivityMonthlyId = aspekActivityMonthlyId;
    }

    public String getEvaluasiActivityPeristiwaId() {
        return evaluasiActivityPeristiwaId;
    }

    public void setEvaluasiActivityPeristiwaId(String evaluasiActivityPeristiwaId) {
        this.evaluasiActivityPeristiwaId = evaluasiActivityPeristiwaId;
    }

    public String getKejadian() {
        return kejadian;
    }

    public void setKejadian(String kejadian) {
        this.kejadian = kejadian;
    }

    public java.sql.Date getTanggalKejadian() {
        return tanggalKejadian;
    }

    public void setTanggalKejadian(java.sql.Date tanggalKejadian) {
        this.tanggalKejadian = tanggalKejadian;
    }
}