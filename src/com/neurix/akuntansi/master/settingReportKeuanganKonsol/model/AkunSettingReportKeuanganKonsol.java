package com.neurix.akuntansi.master.settingReportKeuanganKonsol.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class AkunSettingReportKeuanganKonsol extends BaseModel {
    private String settingReportKonsolId;
    private String kodeRekeningAlias;
    private String namaKodeRekeningAlias;
    private String flagLabel;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private String stCreatedDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String stLastUpdate;
    private String lastUpdateWho;

    private String rekeningId;
    private String operator;

    private BigDecimal saldoUnit1;
    private BigDecimal saldoUnit2;
    private BigDecimal saldoUnit3;
    private BigDecimal saldoUnit4;
    private BigDecimal saldoUnitAll;
    private String level1;
    private String level2;

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public BigDecimal getSaldoUnit1() {
        return saldoUnit1;
    }

    public void setSaldoUnit1(BigDecimal saldoUnit1) {
        this.saldoUnit1 = saldoUnit1;
    }

    public BigDecimal getSaldoUnit2() {
        return saldoUnit2;
    }

    public void setSaldoUnit2(BigDecimal saldoUnit2) {
        this.saldoUnit2 = saldoUnit2;
    }

    public BigDecimal getSaldoUnit3() {
        return saldoUnit3;
    }

    public void setSaldoUnit3(BigDecimal saldoUnit3) {
        this.saldoUnit3 = saldoUnit3;
    }

    public BigDecimal getSaldoUnit4() {
        return saldoUnit4;
    }

    public void setSaldoUnit4(BigDecimal saldoUnit4) {
        this.saldoUnit4 = saldoUnit4;
    }

    public BigDecimal getSaldoUnitAll() {
        return saldoUnitAll;
    }

    public void setSaldoUnitAll(BigDecimal saldoUnitAll) {
        this.saldoUnitAll = saldoUnitAll;
    }

    public String getSettingReportKonsolId() {
        return settingReportKonsolId;
    }

    public void setSettingReportKonsolId(String settingReportKonsolId) {
        this.settingReportKonsolId = settingReportKonsolId;
    }

    public String getKodeRekeningAlias() {
        return kodeRekeningAlias;
    }

    public void setKodeRekeningAlias(String kodeRekeningAlias) {
        this.kodeRekeningAlias = kodeRekeningAlias;
    }

    public String getNamaKodeRekeningAlias() {
        return namaKodeRekeningAlias;
    }

    public void setNamaKodeRekeningAlias(String namaKodeRekeningAlias) {
        this.namaKodeRekeningAlias = namaKodeRekeningAlias;
    }

    public String getFlagLabel() {
        return flagLabel;
    }

    public void setFlagLabel(String flagLabel) {
        this.flagLabel = flagLabel;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }
}
