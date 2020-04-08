package com.neurix.hris.master.cuti.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Cuti extends BaseModel {
    private String cutiId;
    private String cutiName;
    private String tipeHari;
    private String golonganId;
    private String golonganName;
    private String branchName;
    private String branchId;
    private Long jumlahCuti;

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public String getCutiName() {
        return cutiName;
    }

    public void setCutiName(String cutiName) {
        this.cutiName = cutiName;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
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

    public Long getJumlahCuti() {
        return jumlahCuti;
    }

    public void setJumlahCuti(Long jumlahCuti) {
        this.jumlahCuti = jumlahCuti;
    }
}