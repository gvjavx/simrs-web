package com.neurix.akuntansi.master.settingReportArusKas.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AkunSettingReportKeuanganArusKas extends BaseModel {
    private String settingReportArusKasId;
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
    private BigDecimal saldoUnit5;
    private BigDecimal saldoUnit6;
    private BigDecimal saldoUnit7;
    private BigDecimal saldoUnitAll;

    private BigDecimal lastSaldoUnit1;
    private BigDecimal lastSaldoUnit2;
    private BigDecimal lastSaldoUnit3;
    private BigDecimal lastSaldoUnit4;
    private BigDecimal lastSaldoUnit5;
    private BigDecimal lastSaldoUnit6;
    private BigDecimal lastSaldoUnit7;
    private BigDecimal lastSaldoUnitAll;

    private BigDecimal curSaldoUnit1;
    private BigDecimal curSaldoUnit2;
    private BigDecimal curSaldoUnit3;
    private BigDecimal curSaldoUnit4;
    private BigDecimal curSaldoUnit5;
    private BigDecimal curSaldoUnit6;
    private BigDecimal curSaldoUnit7;
    private BigDecimal curSaldoUnitAll;

    private BigDecimal saldoUnit11TahunLalu;

    private BigDecimal saldoUnit12TahunLalu;

    private String level1;
    private String level2;

    public BigDecimal getSaldoUnit5() {
        return saldoUnit5;
    }

    public void setSaldoUnit5(BigDecimal saldoUnit5) {
        this.saldoUnit5 = saldoUnit5;
    }

    public BigDecimal getSaldoUnit6() {
        return saldoUnit6;
    }

    public void setSaldoUnit6(BigDecimal saldoUnit6) {
        this.saldoUnit6 = saldoUnit6;
    }

    public BigDecimal getSaldoUnit7() {
        return saldoUnit7;
    }

    public void setSaldoUnit7(BigDecimal saldoUnit7) {
        this.saldoUnit7 = saldoUnit7;
    }

    public BigDecimal getLastSaldoUnit5() {
        return lastSaldoUnit5;
    }

    public void setLastSaldoUnit5(BigDecimal lastSaldoUnit5) {
        this.lastSaldoUnit5 = lastSaldoUnit5;
    }

    public BigDecimal getLastSaldoUnit6() {
        return lastSaldoUnit6;
    }

    public void setLastSaldoUnit6(BigDecimal lastSaldoUnit6) {
        this.lastSaldoUnit6 = lastSaldoUnit6;
    }

    public BigDecimal getLastSaldoUnit7() {
        return lastSaldoUnit7;
    }

    public void setLastSaldoUnit7(BigDecimal lastSaldoUnit7) {
        this.lastSaldoUnit7 = lastSaldoUnit7;
    }

    public BigDecimal getCurSaldoUnit6() {
        return curSaldoUnit6;
    }

    public void setCurSaldoUnit6(BigDecimal curSaldoUnit6) {
        this.curSaldoUnit6 = curSaldoUnit6;
    }

    public BigDecimal getCurSaldoUnit7() {
        return curSaldoUnit7;
    }

    public void setCurSaldoUnit7(BigDecimal curSaldoUnit7) {
        this.curSaldoUnit7 = curSaldoUnit7;
    }

    public String getSettingReportArusKasId() {
        return settingReportArusKasId;
    }

    public void setSettingReportArusKasId(String settingReportArusKasId) {
        this.settingReportArusKasId = settingReportArusKasId;
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

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
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
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public BigDecimal getLastSaldoUnit1() {
        return lastSaldoUnit1;
    }

    public void setLastSaldoUnit1(BigDecimal lastSaldoUnit1) {
        this.lastSaldoUnit1 = lastSaldoUnit1;
    }

    public BigDecimal getLastSaldoUnit2() {
        return lastSaldoUnit2;
    }

    public void setLastSaldoUnit2(BigDecimal lastSaldoUnit2) {
        this.lastSaldoUnit2 = lastSaldoUnit2;
    }

    public BigDecimal getLastSaldoUnit3() {
        return lastSaldoUnit3;
    }

    public void setLastSaldoUnit3(BigDecimal lastSaldoUnit3) {
        this.lastSaldoUnit3 = lastSaldoUnit3;
    }

    public BigDecimal getLastSaldoUnit4() {
        return lastSaldoUnit4;
    }

    public void setLastSaldoUnit4(BigDecimal lastSaldoUnit4) {
        this.lastSaldoUnit4 = lastSaldoUnit4;
    }

    public BigDecimal getLastSaldoUnitAll() {
        return lastSaldoUnitAll;
    }

    public void setLastSaldoUnitAll(BigDecimal lastSaldoUnitAll) {
        this.lastSaldoUnitAll = lastSaldoUnitAll;
    }

    public BigDecimal getCurSaldoUnit1() {
        return curSaldoUnit1;
    }

    public void setCurSaldoUnit1(BigDecimal curSaldoUnit1) {
        this.curSaldoUnit1 = curSaldoUnit1;
    }

    public BigDecimal getCurSaldoUnit2() {
        return curSaldoUnit2;
    }

    public void setCurSaldoUnit2(BigDecimal curSaldoUnit2) {
        this.curSaldoUnit2 = curSaldoUnit2;
    }

    public BigDecimal getCurSaldoUnit3() {
        return curSaldoUnit3;
    }

    public void setCurSaldoUnit3(BigDecimal curSaldoUnit3) {
        this.curSaldoUnit3 = curSaldoUnit3;
    }

    public BigDecimal getCurSaldoUnit4() {
        return curSaldoUnit4;
    }

    public void setCurSaldoUnit4(BigDecimal curSaldoUnit4) {
        this.curSaldoUnit4 = curSaldoUnit4;
    }

    public BigDecimal getCurSaldoUnit5() {
        return curSaldoUnit5;
    }

    public void setCurSaldoUnit5(BigDecimal curSaldoUnit5) {
        this.curSaldoUnit5 = curSaldoUnit5;
    }

    public BigDecimal getCurSaldoUnitAll() {
        return curSaldoUnitAll;
    }

    public void setCurSaldoUnitAll(BigDecimal curSaldoUnitAll) {
        this.curSaldoUnitAll = curSaldoUnitAll;
    }

    public BigDecimal getSaldoUnit11TahunLalu() {
        return saldoUnit11TahunLalu;
    }

    public void setSaldoUnit11TahunLalu(BigDecimal saldoUnit11TahunLalu) {
        this.saldoUnit11TahunLalu = saldoUnit11TahunLalu;
    }

    public BigDecimal getSaldoUnit12TahunLalu() {
        return saldoUnit12TahunLalu;
    }

    public void setSaldoUnit12TahunLalu(BigDecimal saldoUnit12TahunLalu) {
        this.saldoUnit12TahunLalu = saldoUnit12TahunLalu;
    }

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
}
