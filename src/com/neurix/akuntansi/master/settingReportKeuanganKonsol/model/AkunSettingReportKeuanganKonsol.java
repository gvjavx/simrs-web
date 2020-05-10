package com.neurix.akuntansi.master.settingReportKeuanganKonsol.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

public class AkunSettingReportKeuanganKonsol extends BaseModel {
    private String settingReportKonsolId;
    private String kodeRekeningAlias;
    private String namaKodeRekeningAlias;
    private String flagLabel;

    private BigDecimal saldoUnit1;
    private BigDecimal saldoUnit2;
    private BigDecimal saldoUnit3;
    private BigDecimal saldoUnit4;
    private BigDecimal saldoUnitAll;

    private BigDecimal lastSaldoUnit1;
    private BigDecimal lastSaldoUnit2;
    private BigDecimal lastSaldoUnit3;
    private BigDecimal lastSaldoUnit4;
    private BigDecimal lastSaldoUnitAll;

    private BigDecimal curSaldoUnit1;
    private BigDecimal curSaldoUnit2;
    private BigDecimal curSaldoUnit3;
    private BigDecimal curSaldoUnit4;
    private BigDecimal curSaldoUnit5;
    private BigDecimal curSaldoUnitAll;

    private String level1;
    private String level2;

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
}
