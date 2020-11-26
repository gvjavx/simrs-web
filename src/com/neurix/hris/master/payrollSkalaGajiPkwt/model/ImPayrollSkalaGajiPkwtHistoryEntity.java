package com.neurix.hris.master.payrollSkalaGajiPkwt.model;

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
public class ImPayrollSkalaGajiPkwtHistoryEntity implements Serializable {

    private String skalaGajiPkwtIdHistory;
    private String skalaGajiPkwtId;
    private String golonganPkwtId;
    private BigDecimal gajiPokok;
    private BigDecimal santunanKhusus;
    private BigDecimal tunjFunsional;
    private BigDecimal tunjtambahan;
    private String tahun;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getSkalaGajiPkwtIdHistory() {
        return skalaGajiPkwtIdHistory;
    }

    public void setSkalaGajiPkwtIdHistory(String skalaGajiPkwtIdHistory) {
        this.skalaGajiPkwtIdHistory = skalaGajiPkwtIdHistory;
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

    public BigDecimal getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(BigDecimal gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public String getGolonganPkwtId() {
        return golonganPkwtId;
    }

    public void setGolonganPkwtId(String golonganPkwtId) {
        this.golonganPkwtId = golonganPkwtId;
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

    public BigDecimal getSantunanKhusus() {
        return santunanKhusus;
    }

    public void setSantunanKhusus(BigDecimal santunanKhusus) {
        this.santunanKhusus = santunanKhusus;
    }

    public String getSkalaGajiPkwtId() {
        return skalaGajiPkwtId;
    }

    public void setSkalaGajiPkwtId(String skalaGajiPkwtId) {
        this.skalaGajiPkwtId = skalaGajiPkwtId;
    }

    public BigDecimal getTunjFunsional() {
        return tunjFunsional;
    }

    public void setTunjFunsional(BigDecimal tunjFunsional) {
        this.tunjFunsional = tunjFunsional;
    }

    public BigDecimal getTunjtambahan() {
        return tunjtambahan;
    }

    public void setTunjtambahan(BigDecimal tunjtambahan) {
        this.tunjtambahan = tunjtambahan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
