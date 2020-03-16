package com.neurix.hris.master.cutiPanjang.model;

import java.io.Serializable;
import java.sql.Timestamp;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImCutiPanjangEntity implements Serializable {

    private String cutiPanjangId;
    private String golonganId;
    private String branchId;
    private Integer jumlahCuti;
    private String tipeHari;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImGolonganEntity imGolongan;
    private ImBranches imBranch;


    public ImGolonganEntity getImGolongan() {
        return imGolongan;
    }

    public void setImGolongan(ImGolonganEntity imGolongan) {
        this.imGolongan = imGolongan;
    }

    public ImBranches getImBranch() {
        return imBranch;
    }

    public void setImBranch(ImBranches imBranch) {
        this.imBranch = imBranch;
    }

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}