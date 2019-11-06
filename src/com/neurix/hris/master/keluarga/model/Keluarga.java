package com.neurix.hris.master.keluarga.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Keluarga extends BaseModel {
    private String keluargaId;
    private String gender;
    private String name;
    private String nip;
    private String statusKeluargaId;
    private String statusKeluargaName;
    private String stTanggalLahir;
    private Date tanggalLahir;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getKeluargaId() {
        return keluargaId;
    }

    public void setKeluargaId(String keluargaId) {
        this.keluargaId = keluargaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusKeluargaId() {
        return statusKeluargaId;
    }

    public void setStatusKeluargaId(String statusKeluargaId) {
        this.statusKeluargaId = statusKeluargaId;
    }

    public String getStatusKeluargaName() {
        return statusKeluargaName;
    }

    public void setStatusKeluargaName(String statusKeluargaName) {
        this.statusKeluargaName = statusKeluargaName;
    }

    public String getStTanggalLahir() {
        return stTanggalLahir;
    }

    public void setStTanggalLahir(String stTanggalLahir) {
        this.stTanggalLahir = stTanggalLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setTanggalLahir(Timestamp tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}