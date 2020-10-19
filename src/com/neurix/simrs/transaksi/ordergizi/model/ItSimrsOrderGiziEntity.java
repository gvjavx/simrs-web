package com.neurix.simrs.transaksi.ordergizi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class ItSimrsOrderGiziEntity implements Serializable{
    private String idOrderGizi;
    private String idRawatInap;
    private Timestamp tglOrder;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String approveFlag;
    private String diterimaFlag;
    private String idDietGizi;
    private String keterangan;
    private String bentukDiet;
    private String waktu;

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
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

    public Timestamp getTglOrder() {
        return tglOrder;
    }

    public void setTglOrder(Timestamp tglOrder) {
        this.tglOrder = tglOrder;
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
}
