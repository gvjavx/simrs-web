package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class LaporanAkuntansi extends BaseModel {
    private String laporanAkuntansiId;
    private String url;
    private String laporanAkuntansiName;

    public String getLaporanAkuntansiId() {
        return laporanAkuntansiId;
    }

    public void setLaporanAkuntansiId(String laporanAkuntansiId) {
        this.laporanAkuntansiId = laporanAkuntansiId;
    }

    public String getLaporanAkuntansiName() {
        return laporanAkuntansiName;
    }

    public void setLaporanAkuntansiName(String laporanAkuntansiName) {
        this.laporanAkuntansiName = laporanAkuntansiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}