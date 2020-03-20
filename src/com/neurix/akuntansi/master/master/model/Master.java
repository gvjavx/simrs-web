package com.neurix.akuntansi.master.master.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Master extends BaseModel {
    private MasterPK primaryKey;
    private String nama;
    private String alamat;
    private String npwp;
    private String email;
    private String noTelp;

    private String nomorVendor;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNomorVendor() {
        return nomorVendor;
    }

    public void setNomorVendor(String nomorVendor) {
        this.nomorVendor = nomorVendor;
    }

    public MasterPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(MasterPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

}