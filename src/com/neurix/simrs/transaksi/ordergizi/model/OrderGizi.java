package com.neurix.simrs.transaksi.ordergizi.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class OrderGizi {
    private String idOrderGizi;
    private String idRawatInap;
    private Date tglOrder;
    private String dietPagi;
    private String bentukMakanPagi;
    private String dietSiang;
    private String bentukMakanSiang;
    private String dietMalam;
    private String bentukMakanMalam;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String approveFlag;
    private String diterimaFlag;

    private BigDecimal tarifTotal;

    public BigDecimal getTarifTotal() {
        return tarifTotal;
    }

    public void setTarifTotal(BigDecimal tarifTotal) {
        this.tarifTotal = tarifTotal;
    }

    public String getDiterimaFlag() {
        return diterimaFlag;
    }

    public void setDiterimaFlag(String diterimaFlag) {
        this.diterimaFlag = diterimaFlag;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getIdOrderGizi() {
        return idOrderGizi;
    }

    public void setIdOrderGizi(String idOrderGizi) {
        this.idOrderGizi = idOrderGizi;
    }

    public String getIdRawatInap() {
        return idRawatInap;
    }

    public void setIdRawatInap(String idRawatInap) {
        this.idRawatInap = idRawatInap;
    }

    public Date getTglOrder() {
        return tglOrder;
    }

    public void setTglOrder(Date tglOrder) {
        this.tglOrder = tglOrder;
    }

    public String getDietPagi() {
        return dietPagi;
    }

    public void setDietPagi(String dietPagi) {
        this.dietPagi = dietPagi;
    }

    public String getBentukMakanPagi() {
        return bentukMakanPagi;
    }

    public void setBentukMakanPagi(String bentukMakanPagi) {
        this.bentukMakanPagi = bentukMakanPagi;
    }

    public String getDietSiang() {
        return dietSiang;
    }

    public void setDietSiang(String dietSiang) {
        this.dietSiang = dietSiang;
    }

    public String getBentukMakanSiang() {
        return bentukMakanSiang;
    }

    public void setBentukMakanSiang(String bentukMakanSiang) {
        this.bentukMakanSiang = bentukMakanSiang;
    }

    public String getDietMalam() {
        return dietMalam;
    }

    public void setDietMalam(String dietMalam) {
        this.dietMalam = dietMalam;
    }

    public String getBentukMakanMalam() {
        return bentukMakanMalam;
    }

    public void setBentukMakanMalam(String bentukMakanMalam) {
        this.bentukMakanMalam = bentukMakanMalam;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

    @Override
    public String toString() {
        return "ItSimrsOrderGiziEntity{" +
                "idOrderGizi='" + idOrderGizi + '\'' +
                ", idRawatInap='" + idRawatInap + '\'' +
                ", tglOrder=" + tglOrder +
                ", dietPagi='" + dietPagi + '\'' +
                ", bentukMakanPagi='" + bentukMakanPagi + '\'' +
                ", dietSiang='" + dietSiang + '\'' +
                ", bentukMakanSiang='" + bentukMakanSiang + '\'' +
                ", dietMalam='" + dietMalam + '\'' +
                ", bentukMakanMalam='" + bentukMakanMalam + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                '}';
    }
}
