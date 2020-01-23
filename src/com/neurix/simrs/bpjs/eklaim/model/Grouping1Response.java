package com.neurix.simrs.bpjs.eklaim.model;

import java.util.List;

public class Grouping1Response {
    private String cbgCode;
    private String cbgDescription;
    private String cbgTarif;
    private String subAcuteCode;
    private String subAcuteDescription;
    private String subAcuteTarif;
    private String chronicCode;
    private String chronicDescription;
    private String chronicTarif;
    private String kelas;
    private String addPaymentAmt;
    private String inacbgVersion;
    private List<Grouping1SpecialCmgResponse> specialCmgResponseList;
    private List<Grouping1TarifAltResponse> tarifAltResponseList;

    public String getCbgCode() {
        return cbgCode;
    }

    public void setCbgCode(String cbgCode) {
        this.cbgCode = cbgCode;
    }

    public String getCbgDescription() {
        return cbgDescription;
    }

    public void setCbgDescription(String cbgDescription) {
        this.cbgDescription = cbgDescription;
    }

    public String getCbgTarif() {
        return cbgTarif;
    }

    public void setCbgTarif(String cbgTarif) {
        this.cbgTarif = cbgTarif;
    }

    public String getSubAcuteCode() {
        return subAcuteCode;
    }

    public void setSubAcuteCode(String subAcuteCode) {
        this.subAcuteCode = subAcuteCode;
    }

    public String getSubAcuteDescription() {
        return subAcuteDescription;
    }

    public void setSubAcuteDescription(String subAcuteDescription) {
        this.subAcuteDescription = subAcuteDescription;
    }

    public String getSubAcuteTarif() {
        return subAcuteTarif;
    }

    public void setSubAcuteTarif(String subAcuteTarif) {
        this.subAcuteTarif = subAcuteTarif;
    }

    public String getChronicCode() {
        return chronicCode;
    }

    public void setChronicCode(String chronicCode) {
        this.chronicCode = chronicCode;
    }

    public String getChronicDescription() {
        return chronicDescription;
    }

    public void setChronicDescription(String chronicDescription) {
        this.chronicDescription = chronicDescription;
    }

    public String getChronicTarif() {
        return chronicTarif;
    }

    public void setChronicTarif(String chronicTarif) {
        this.chronicTarif = chronicTarif;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getAddPaymentAmt() {
        return addPaymentAmt;
    }

    public void setAddPaymentAmt(String addPaymentAmt) {
        this.addPaymentAmt = addPaymentAmt;
    }

    public String getInacbgVersion() {
        return inacbgVersion;
    }

    public void setInacbgVersion(String inacbgVersion) {
        this.inacbgVersion = inacbgVersion;
    }

    public List<Grouping1SpecialCmgResponse> getSpecialCmgResponseList() {
        return specialCmgResponseList;
    }

    public void setSpecialCmgResponseList(List<Grouping1SpecialCmgResponse> specialCmgResponseList) {
        this.specialCmgResponseList = specialCmgResponseList;
    }

    public List<Grouping1TarifAltResponse> getTarifAltResponseList() {
        return tarifAltResponseList;
    }

    public void setTarifAltResponseList(List<Grouping1TarifAltResponse> tarifAltResponseList) {
        this.tarifAltResponseList = tarifAltResponseList;
    }
}
