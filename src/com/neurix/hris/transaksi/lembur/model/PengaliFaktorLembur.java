package com.neurix.hris.transaksi.lembur.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class PengaliFaktorLembur extends BaseModel implements Serializable {
    private String pengaliFaktorId;
    private String tipePegawaiId;
    private Double faktor;

    public String getPengaliFaktorId() {
        return pengaliFaktorId;
    }

    public void setPengaliFaktorId(String pengaliFaktorId) {
        this.pengaliFaktorId = pengaliFaktorId;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public Double getFaktor() {
        return faktor;
    }

    public void setFaktor(Double faktor) {
        this.faktor = faktor;
    }
}
