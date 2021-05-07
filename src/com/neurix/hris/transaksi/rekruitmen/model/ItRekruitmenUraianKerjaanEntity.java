package com.neurix.hris.transaksi.rekruitmen.model;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItRekruitmenUraianKerjaanEntity implements Serializable {

    private String rekruitmenUraianKerjaanId;
    private String no;
    private String uraianPekerjaan;
    private String action;
    private String flag;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String calonPegawaiId;


    public String getRekruitmenUraianKerjaanId() {
        return rekruitmenUraianKerjaanId;
    }

    public void setRekruitmenUraianKerjaanId(String rekruitmenUraianKerjaanId) {
        this.rekruitmenUraianKerjaanId = rekruitmenUraianKerjaanId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUraianPekerjaan() {
        return uraianPekerjaan;
    }

    public void setUraianPekerjaan(String uraianPekerjaan) {
        this.uraianPekerjaan = uraianPekerjaan;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getCalonPegawaiId() {
        return calonPegawaiId;
    }

    public void setCalonPegawaiId(String calonPegawaiId) {
        this.calonPegawaiId = calonPegawaiId;
    }
}
