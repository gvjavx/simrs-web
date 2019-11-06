package com.neurix.hris.transaksi.ijinKeluar.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class IjinKeluarAnggota extends BaseModel {
    private String ijinKeluarAnggotaId;
    private String ijinKeluarKantorId;
    private String nip;
    private String namaPegawai;
    private String positionId;
    private String positionName;
    private String no;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIjinKeluarKantorId() {
        return ijinKeluarKantorId;
    }

    public void setIjinKeluarKantorId(String ijinKeluarKantorId) {
        this.ijinKeluarKantorId = ijinKeluarKantorId;
    }

    public String getIjinKeluarAnggotaId() {
        return ijinKeluarAnggotaId;
    }

    public void setIjinKeluarAnggotaId(String ijinKeluarAnggotaId) {
        this.ijinKeluarAnggotaId = ijinKeluarAnggotaId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
