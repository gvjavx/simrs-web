package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class RekruitmenUraianKerjaan extends BaseModel {

    private String rekruitmenUraianKerjaanId;
    private String no;
    private String uraianPekerjaan;
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

    public String getCalonPegawaiId() {
        return calonPegawaiId;
    }

    public void setCalonPegawaiId(String calonPegawaiId) {
        this.calonPegawaiId = calonPegawaiId;
    }
}