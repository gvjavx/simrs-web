package com.neurix.hris.master.cutiPanjang.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class CutiPanjang extends BaseModel {
    private String cutiPanjangId;
    private String golonganId;
    private String branchId;
    private Integer jumlahCuti;
    private String tipeHari;

    public String getCutiPanjangId() {
        return cutiPanjangId;
    }

    public void setCutiPanjangId(String cutiPanjangId) {
        this.cutiPanjangId = cutiPanjangId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Integer getJumlahCuti() {
        return jumlahCuti;
    }

    public void setJumlahCuti(Integer jumlahCuti) {
        this.jumlahCuti = jumlahCuti;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }
}