package com.neurix.hris.master.payrollSkalaGaji.model;

import com.neurix.hris.master.golongan.model.ImGolonganEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPayrollSkalaGajiEntity implements Serializable {

    private String skalaGajiId ;
    private String golonganId ;
    private int point ;
    private BigDecimal nilai ;
    private String tahun;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;



    private BigDecimal santunanKhusus;
    private BigDecimal rumah;
    private BigDecimal listrik;
    private BigDecimal air;
    private BigDecimal bbm;
    private BigDecimal total;
    private String noSk;

    public BigDecimal getAir() {
        return air;
    }

    public void setAir(BigDecimal air) {
        this.air = air;
    }

    public BigDecimal getBbm() {
        return bbm;
    }

    public void setBbm(BigDecimal bbm) {
        this.bbm = bbm;
    }

    public BigDecimal getListrik() {
        return listrik;
    }

    public void setListrik(BigDecimal listrik) {
        this.listrik = listrik;
    }

    public BigDecimal getRumah() {
        return rumah;
    }

    public void setRumah(BigDecimal rumah) {
        this.rumah = rumah;
    }

    public BigDecimal getSantunanKhusus() {
        return santunanKhusus;
    }

    public void setSantunanKhusus(BigDecimal santunanKhusus) {
        this.santunanKhusus = santunanKhusus;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    private ImGolonganEntity imGolonganEntity;

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getSkalaGajiId() {
        return skalaGajiId;
    }

    public void setSkalaGajiId(String skalaGajiId) {
        this.skalaGajiId = skalaGajiId;
    }

    public String getNoSk() {
        return noSk;
    }

    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }
}