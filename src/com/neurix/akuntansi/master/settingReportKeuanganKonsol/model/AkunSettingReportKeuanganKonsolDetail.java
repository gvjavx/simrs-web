package com.neurix.akuntansi.master.settingReportKeuanganKonsol.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

public class AkunSettingReportKeuanganKonsolDetail extends BaseModel {
    private String settingReportKonsolDetailId;
    private String settingReportKonsolId;
    private String rekeningId;
    private String operator;
    private String sbOperator;

    private String kodeRekening;
    private String namaRekening;
    private BigDecimal saldoUnit1;
    private BigDecimal saldoUnit2;
    private BigDecimal saldoUnit3;
    private BigDecimal saldoUnit4;
    private BigDecimal saldoUnitAll;

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
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

    public String getSettingReportKonsolDetailId() {
        return settingReportKonsolDetailId;
    }

    public void setSettingReportKonsolDetailId(String settingReportKonsolDetailId) {
        this.settingReportKonsolDetailId = settingReportKonsolDetailId;
    }

    public String getSettingReportKonsolId() {
        return settingReportKonsolId;
    }

    public void setSettingReportKonsolId(String settingReportKonsolId) {
        this.settingReportKonsolId = settingReportKonsolId;
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

    public String getSbOperator() {
        return sbOperator;
    }

    public void setSbOperator(String sbOperator) {
        this.sbOperator = sbOperator;
    }
}
