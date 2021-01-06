package com.neurix.hris.master.golongan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImGolonganEntity implements Serializable {

    private String golonganId;
    private String golonganName;
    private Integer level;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String golonganPensiun;
    private String masaKerjaGolAwal;
    private String masaKerjaGolAkhir;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getGolonganPensiun() {
        return golonganPensiun;
    }

    public void setGolonganPensiun(String golonganPensiun) {
        this.golonganPensiun = golonganPensiun;
    }

    public String getMasaKerjaGolAwal() {
        return masaKerjaGolAwal;
    }

    public void setMasaKerjaGolAwal(String masaKerjaGolAwal) {
        this.masaKerjaGolAwal = masaKerjaGolAwal;
    }

    public String getMasaKerjaGolAkhir() {
        return masaKerjaGolAkhir;
    }

    public void setMasaKerjaGolAkhir(String masaKerjaGolAkhir) {
        this.masaKerjaGolAkhir = masaKerjaGolAkhir;
    }
}
