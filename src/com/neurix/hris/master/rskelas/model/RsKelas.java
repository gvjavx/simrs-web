package com.neurix.hris.master.rskelas.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class RsKelas extends BaseModel implements Serializable {
    private String rsKelasId;
    private String rsId;
    private String kelompokId;
    private String golonganId;
    private String kelas;
    private String branchId;

    public String getRsKelasId() {
        return rsKelasId;
    }

    public void setRsKelasId(String rsKelasId) {
        this.rsKelasId = rsKelasId;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
