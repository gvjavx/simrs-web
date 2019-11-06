package com.neurix.hris.transaksi.laporan.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class Laporan extends BaseModel {
    private String laporanId;
    private String url;
    private String laporanName;

    public String getLaporanId() {
        return laporanId;
    }

    public void setLaporanId(String laporanId) {
        this.laporanId = laporanId;
    }

    public String getLaporanName() {
        return laporanName;
    }

    public void setLaporanName(String laporanName) {
        this.laporanName = laporanName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}