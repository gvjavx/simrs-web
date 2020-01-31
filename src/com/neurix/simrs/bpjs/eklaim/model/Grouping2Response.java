package com.neurix.simrs.bpjs.eklaim.model;

import java.util.List;

public class Grouping2Response {
    private String cbgCode;
    private String cbgDescription;
    private String cbgTarif;
    private List<Grouping2SpesialCmgResponse> grouping2SpesialCmgResponseList;
    private String kelas;
    private String addPaymentAmt;
    private String inacbgVersion;
    private List<Grouping1SpecialCmgResponse> grouping1SpecialCmgResponseList;
    private List<Grouping1TarifAltResponse> grouping1TarifAltResponseList;

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

    public List<Grouping2SpesialCmgResponse> getGrouping2SpesialCmgResponseList() {
        return grouping2SpesialCmgResponseList;
    }

    public void setGrouping2SpesialCmgResponseList(List<Grouping2SpesialCmgResponse> grouping2SpesialCmgResponseList) {
        this.grouping2SpesialCmgResponseList = grouping2SpesialCmgResponseList;
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

    public List<Grouping1SpecialCmgResponse> getGrouping1SpecialCmgResponseList() {
        return grouping1SpecialCmgResponseList;
    }

    public void setGrouping1SpecialCmgResponseList(List<Grouping1SpecialCmgResponse> grouping1SpecialCmgResponseList) {
        this.grouping1SpecialCmgResponseList = grouping1SpecialCmgResponseList;
    }

    public List<Grouping1TarifAltResponse> getGrouping1TarifAltResponseList() {
        return grouping1TarifAltResponseList;
    }

    public void setGrouping1TarifAltResponseList(List<Grouping1TarifAltResponse> grouping1TarifAltResponseList) {
        this.grouping1TarifAltResponseList = grouping1TarifAltResponseList;
    }
}
