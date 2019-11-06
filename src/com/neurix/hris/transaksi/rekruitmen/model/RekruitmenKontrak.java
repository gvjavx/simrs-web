package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class RekruitmenKontrak extends BaseModel {
    private String rekruitmenKontrakId;
    private String isi;
    private String no;
    private String pasal;

    public String getRekruitmenKontrakId() {
        return rekruitmenKontrakId;
    }

    public void setRekruitmenKontrakId(String rekruitmenKontrakId) {
        this.rekruitmenKontrakId = rekruitmenKontrakId;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPasal() {
        return pasal;
    }

    public void setPasal(String pasal) {
        this.pasal = pasal;
    }
}