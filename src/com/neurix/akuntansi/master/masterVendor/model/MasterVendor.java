package com.neurix.akuntansi.master.masterVendor.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class MasterVendor extends BaseModel {
    private String nomorMaster;
    private String nama;
    private String alamat;
    private String npwp;
    private String email;
    private String noTelp;
    private String vendorObat;
    private Boolean obat;
    private String noRekening;
    private String TipeVendor;
    private String TipeVendorName;

    public String getTipeVendorName() {
        return TipeVendorName;
    }

    public void setTipeVendorName(String tipeVendorName) {
        TipeVendorName = tipeVendorName;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getTipeVendor() {
        return TipeVendor;
    }

    public void setTipeVendor(String tipeVendor) {
        TipeVendor = tipeVendor;
    }

    public Boolean getObat() {
        return obat;
    }

    public void setObat(Boolean obat) {
        this.obat = obat;
    }

    public String getVendorObat() {
        return vendorObat;
    }

    public void setVendorObat(String vendorObat) {
        this.vendorObat = vendorObat;
    }

    public String getNomorMaster() {
        return nomorMaster;
    }

    public void setNomorMaster(String nomorMaster) {
        this.nomorMaster = nomorMaster;
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
}