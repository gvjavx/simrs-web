package com.neurix.hris.master.payrollJabatanStruktural.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class payrollJabatanStruktural extends BaseModel {
    private String tunjStrukturId ;
    private String golonganId ;
    private String golonganName ;
    private BigDecimal nilai ;

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

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getTunjStrukturId() {
        return tunjStrukturId;
    }

    public void setTunjStrukturId(String tunjStrukturId) {
        this.tunjStrukturId = tunjStrukturId;
    }
}