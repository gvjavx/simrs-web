package com.neurix.simrs.mobileapi.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author gondok
 * Saturday, 15/02/20 9:03
 */
public class OrderGiziMobile {

    private String idOrderGizi;
    private String idRawatInap;
    private String tglOrder;
    private String dietPagi;
    private String bentukMakanPagi;
    private String dietSiang;
    private String bentukMakanSiang;
    private String dietMalam;
    private String bentukMakanMalam;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;
    private String approveFlag;
    private String diterimaFlag;

    private String tarifTotal;

    private String idDietGizi;
    private String keterangan;
    private String bentukDiet;

    public String getTarifTotal() {
        return tarifTotal;
    }

    public void setTarifTotal(String tarifTotal) {
        this.tarifTotal = tarifTotal;
    }

    public String getIdDietGizi() {
        return idDietGizi;
    }

    public void setIdDietGizi(String idDietGizi) {
        this.idDietGizi = idDietGizi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getBentukDiet() {
        return bentukDiet;
    }

    public void setBentukDiet(String bentukDiet) {
        this.bentukDiet = bentukDiet;
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

    public String getTglOrder() {
        return tglOrder;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setTglOrder(String tglOrder) {
        this.tglOrder = tglOrder;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getDiterimaFlag() {
        return diterimaFlag;
    }

    public void setDiterimaFlag(String diterimaFlag) {
        this.diterimaFlag = diterimaFlag;
    }
}
