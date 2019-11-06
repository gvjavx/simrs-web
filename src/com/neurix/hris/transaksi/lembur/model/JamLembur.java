package com.neurix.hris.transaksi.lembur.model;
import com.neurix.common.model.BaseModel;

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
public class JamLembur extends BaseModel implements Serializable {
    private String jamLemburId;
    private String tipeHari;
    private Integer jamLembur;
    private Double pengaliJamLembur;

    public String getJamLemburId() {
        return jamLemburId;
    }

    public void setJamLemburId(String jamLemburId) {
        this.jamLemburId = jamLemburId;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }

    public Integer getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(Integer jamLembur) {
        this.jamLembur = jamLembur;
    }

    public Double getPengaliJamLembur() {
        return pengaliJamLembur;
    }

    public void setPengaliJamLembur(Double pengaliJamLembur) {
        this.pengaliJamLembur = pengaliJamLembur;
    }
}
