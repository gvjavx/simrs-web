package com.neurix.akuntansi.transaksi.kas.model;

import com.neurix.common.model.BaseModel;

public class Lampiran extends BaseModel {
    private String namaLampiran;
    private String uploadFile;

    private String lampiranId;
    private String transaksiId;
    private String flag;
    private String action;

    public String getLampiranId() {
        return lampiranId;
    }

    public void setLampiranId(String lampiranId) {
        this.lampiranId = lampiranId;
    }

    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    public String getNamaLampiran() {
        return namaLampiran;
    }

    public void setNamaLampiran(String namaLampiran) {
        this.namaLampiran = namaLampiran;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }
}
