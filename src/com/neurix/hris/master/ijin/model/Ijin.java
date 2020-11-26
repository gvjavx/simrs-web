package com.neurix.hris.master.ijin.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Ijin extends BaseModel {
    private String ijinId;
    private String ijinName;
    private Long jumlahIjin;
    private String gender;
    private String tipeHari;
    private String flagDiajukanAdmin;
    private String agama;
    private String agamaName;

    public String getAgamaName() {
        return agamaName;
    }

    public void setAgamaName(String agamaName) {
        this.agamaName = agamaName;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getFlagDiajukanAdmin() {
        return flagDiajukanAdmin;
    }

    public void setFlagDiajukanAdmin(String flagDiajukanAdmin) {
        this.flagDiajukanAdmin = flagDiajukanAdmin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIjinId() {
        return ijinId;
    }

    public void setIjinId(String ijinId) {
        this.ijinId = ijinId;
    }

    public String getIjinName() {
        return ijinName;
    }

    public void setIjinName(String ijinName) {
        this.ijinName = ijinName;
    }

    public Long getJumlahIjin() {
        return jumlahIjin;
    }

    public void setJumlahIjin(Long jumlahIjin) {
        this.jumlahIjin = jumlahIjin;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }
}