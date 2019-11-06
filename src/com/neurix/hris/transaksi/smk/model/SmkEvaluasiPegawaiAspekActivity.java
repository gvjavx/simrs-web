package com.neurix.hris.transaksi.smk.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class SmkEvaluasiPegawaiAspekActivity extends BaseModel {
    private String evaluasiPegawaiAspekActivityId;
    private String evaluasiPegawaiAspekDetailId;
    private double rataRata;
    private double nilaiActivity;

    public String getEvaluasiPegawaiAspekActivityId() {
        return evaluasiPegawaiAspekActivityId;
    }

    public void setEvaluasiPegawaiAspekActivityId(String evaluasiPegawaiAspekActivityId) {
        this.evaluasiPegawaiAspekActivityId = evaluasiPegawaiAspekActivityId;
    }

    public String getEvaluasiPegawaiAspekDetailId() {
        return evaluasiPegawaiAspekDetailId;
    }

    public void setEvaluasiPegawaiAspekDetailId(String evaluasiPegawaiAspekDetailId) {
        this.evaluasiPegawaiAspekDetailId = evaluasiPegawaiAspekDetailId;
    }

    public double getNilaiActivity() {
        return nilaiActivity;
    }

    public void setNilaiActivity(double nilaiActivity) {
        this.nilaiActivity = nilaiActivity;
    }

    public double getRataRata() {
        return rataRata;
    }

    public void setRataRata(double rataRata) {
        this.rataRata = rataRata;
    }
}