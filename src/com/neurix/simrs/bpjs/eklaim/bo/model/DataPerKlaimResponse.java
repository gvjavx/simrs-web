package com.neurix.simrs.bpjs.eklaim.bo.model;

import java.util.List;

public class DataPerKlaimResponse {
    private String kodeRs;
    private String kelasRs;
    private String kelasRawat;
    private String kodeTarif;
    private String jenisRawat;
    private String tglMasuk;
    private String tglPulang;
    private String tglLahir;
    private String beratLahir;
    private String gender;
    private String dischargeStatus;
    private String diagnosa;
    private String procedure;
    private String adlSubAcute;
    private String adlChronic;

    private String tarifRsProsedurNonBedah;
    private String tarifRsProsedurBedah;
    private String tarifRsKonsultasi;
    private String tarifRsTenagaAhli;
    private String tarifRsKeperawatan;
    private String tarifRsPenunjang;
    private String tarifRsRadiologi;
    private String tarifRsLaboratorium;
    private String tarifRsPelayananDarah;
    private String tarifRsRehabilitasi;
    private String tarifRsKamar;
    private String tarifRsRawatIntensif;
    private String tarifRsObat;
    private String tarifRsObatKronis;
    private String tarifRsObatKemotrapis;
    private String tarifRsAlkes;
    private String tarifRsBmhp;
    private String tarifRsSewaAlat;

    private String los;
    private String icuIndicator;
    private String icuLos;
    private String ventilatorHour;
    private String upgradeClassInd;
    private String upgradeClassClass;
    private String upgradeClassLos;
    private String addPaymenPct;
    private String addPaymentAmt;
    private String namaPasien;
    private String nomorRm;
    private String umurTahun;
    private String umurHari;
    private String tarifPoliEks;
    private String namaDokter;
    private String nomorSep;
    private String nomorKartu;
    private String payorId;
    private String payorNm;
    private String coderNm;
    private String coderNik;
    private String patientId;
    private String admissionId;
    private String hospitalAdmissionId;
    private String groupingCount;
    private String cbgCode;
    private String cbgDescription;
    private String cbgTarif;
    private List<Grouping2SpesialCmgResponse> grouping2SpesialCmgResponseList;
    private String inaCbgVersion;
    private List<Grouping1TarifAltResponse> grouping1TarifAltResponseList;
    private String kemenkesDcStatusCd;
    private String kemenkesDcSentDttm;
    private String bpjsDcStatusCd;
    private String bpjsDcSentDttm;
    private String klaimStatusCd;
    private String bpjsKlaimStatusCd;
    private String bpjsKlaimStatusNm;

    public String getKodeRs() {
        return kodeRs;
    }

    public void setKodeRs(String kodeRs) {
        this.kodeRs = kodeRs;
    }

    public String getKelasRs() {
        return kelasRs;
    }

    public void setKelasRs(String kelasRs) {
        this.kelasRs = kelasRs;
    }

    public String getKelasRawat() {
        return kelasRawat;
    }

    public void setKelasRawat(String kelasRawat) {
        this.kelasRawat = kelasRawat;
    }

    public String getKodeTarif() {
        return kodeTarif;
    }

    public void setKodeTarif(String kodeTarif) {
        this.kodeTarif = kodeTarif;
    }

    public String getJenisRawat() {
        return jenisRawat;
    }

    public void setJenisRawat(String jenisRawat) {
        this.jenisRawat = jenisRawat;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public String getTglPulang() {
        return tglPulang;
    }

    public void setTglPulang(String tglPulang) {
        this.tglPulang = tglPulang;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getBeratLahir() {
        return beratLahir;
    }

    public void setBeratLahir(String beratLahir) {
        this.beratLahir = beratLahir;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDischargeStatus() {
        return dischargeStatus;
    }

    public void setDischargeStatus(String dischargeStatus) {
        this.dischargeStatus = dischargeStatus;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getAdlSubAcute() {
        return adlSubAcute;
    }

    public void setAdlSubAcute(String adlSubAcute) {
        this.adlSubAcute = adlSubAcute;
    }

    public String getAdlChronic() {
        return adlChronic;
    }

    public void setAdlChronic(String adlChronic) {
        this.adlChronic = adlChronic;
    }

    public String getTarifRsProsedurNonBedah() {
        return tarifRsProsedurNonBedah;
    }

    public void setTarifRsProsedurNonBedah(String tarifRsProsedurNonBedah) {
        this.tarifRsProsedurNonBedah = tarifRsProsedurNonBedah;
    }

    public String getTarifRsProsedurBedah() {
        return tarifRsProsedurBedah;
    }

    public void setTarifRsProsedurBedah(String tarifRsProsedurBedah) {
        this.tarifRsProsedurBedah = tarifRsProsedurBedah;
    }

    public String getTarifRsKonsultasi() {
        return tarifRsKonsultasi;
    }

    public void setTarifRsKonsultasi(String tarifRsKonsultasi) {
        this.tarifRsKonsultasi = tarifRsKonsultasi;
    }

    public String getTarifRsTenagaAhli() {
        return tarifRsTenagaAhli;
    }

    public void setTarifRsTenagaAhli(String tarifRsTenagaAhli) {
        this.tarifRsTenagaAhli = tarifRsTenagaAhli;
    }

    public String getTarifRsKeperawatan() {
        return tarifRsKeperawatan;
    }

    public void setTarifRsKeperawatan(String tarifRsKeperawatan) {
        this.tarifRsKeperawatan = tarifRsKeperawatan;
    }

    public String getTarifRsPenunjang() {
        return tarifRsPenunjang;
    }

    public void setTarifRsPenunjang(String tarifRsPenunjang) {
        this.tarifRsPenunjang = tarifRsPenunjang;
    }

    public String getTarifRsRadiologi() {
        return tarifRsRadiologi;
    }

    public void setTarifRsRadiologi(String tarifRsRadiologi) {
        this.tarifRsRadiologi = tarifRsRadiologi;
    }

    public String getTarifRsLaboratorium() {
        return tarifRsLaboratorium;
    }

    public void setTarifRsLaboratorium(String tarifRsLaboratorium) {
        this.tarifRsLaboratorium = tarifRsLaboratorium;
    }

    public String getTarifRsPelayananDarah() {
        return tarifRsPelayananDarah;
    }

    public void setTarifRsPelayananDarah(String tarifRsPelayananDarah) {
        this.tarifRsPelayananDarah = tarifRsPelayananDarah;
    }

    public String getTarifRsRehabilitasi() {
        return tarifRsRehabilitasi;
    }

    public void setTarifRsRehabilitasi(String tarifRsRehabilitasi) {
        this.tarifRsRehabilitasi = tarifRsRehabilitasi;
    }

    public String getTarifRsKamar() {
        return tarifRsKamar;
    }

    public void setTarifRsKamar(String tarifRsKamar) {
        this.tarifRsKamar = tarifRsKamar;
    }

    public String getTarifRsRawatIntensif() {
        return tarifRsRawatIntensif;
    }

    public void setTarifRsRawatIntensif(String tarifRsRawatIntensif) {
        this.tarifRsRawatIntensif = tarifRsRawatIntensif;
    }

    public String getTarifRsObat() {
        return tarifRsObat;
    }

    public void setTarifRsObat(String tarifRsObat) {
        this.tarifRsObat = tarifRsObat;
    }

    public String getTarifRsObatKronis() {
        return tarifRsObatKronis;
    }

    public void setTarifRsObatKronis(String tarifRsObatKronis) {
        this.tarifRsObatKronis = tarifRsObatKronis;
    }

    public String getTarifRsObatKemotrapis() {
        return tarifRsObatKemotrapis;
    }

    public void setTarifRsObatKemotrapis(String tarifRsObatKemotrapis) {
        this.tarifRsObatKemotrapis = tarifRsObatKemotrapis;
    }

    public String getTarifRsAlkes() {
        return tarifRsAlkes;
    }

    public void setTarifRsAlkes(String tarifRsAlkes) {
        this.tarifRsAlkes = tarifRsAlkes;
    }

    public String getTarifRsBmhp() {
        return tarifRsBmhp;
    }

    public void setTarifRsBmhp(String tarifRsBmhp) {
        this.tarifRsBmhp = tarifRsBmhp;
    }

    public String getTarifRsSewaAlat() {
        return tarifRsSewaAlat;
    }

    public void setTarifRsSewaAlat(String tarifRsSewaAlat) {
        this.tarifRsSewaAlat = tarifRsSewaAlat;
    }

    public String getLos() {
        return los;
    }

    public void setLos(String los) {
        this.los = los;
    }

    public String getIcuIndicator() {
        return icuIndicator;
    }

    public void setIcuIndicator(String icuIndicator) {
        this.icuIndicator = icuIndicator;
    }

    public String getIcuLos() {
        return icuLos;
    }

    public void setIcuLos(String icuLos) {
        this.icuLos = icuLos;
    }

    public String getVentilatorHour() {
        return ventilatorHour;
    }

    public void setVentilatorHour(String ventilatorHour) {
        this.ventilatorHour = ventilatorHour;
    }

    public String getUpgradeClassInd() {
        return upgradeClassInd;
    }

    public void setUpgradeClassInd(String upgradeClassInd) {
        this.upgradeClassInd = upgradeClassInd;
    }

    public String getUpgradeClassClass() {
        return upgradeClassClass;
    }

    public void setUpgradeClassClass(String upgradeClassClass) {
        this.upgradeClassClass = upgradeClassClass;
    }

    public String getUpgradeClassLos() {
        return upgradeClassLos;
    }

    public void setUpgradeClassLos(String upgradeClassLos) {
        this.upgradeClassLos = upgradeClassLos;
    }

    public String getAddPaymenPct() {
        return addPaymenPct;
    }

    public void setAddPaymenPct(String addPaymenPct) {
        this.addPaymenPct = addPaymenPct;
    }

    public String getAddPaymentAmt() {
        return addPaymentAmt;
    }

    public void setAddPaymentAmt(String addPaymentAmt) {
        this.addPaymentAmt = addPaymentAmt;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNomorRm() {
        return nomorRm;
    }

    public void setNomorRm(String nomorRm) {
        this.nomorRm = nomorRm;
    }

    public String getUmurTahun() {
        return umurTahun;
    }

    public void setUmurTahun(String umurTahun) {
        this.umurTahun = umurTahun;
    }

    public String getUmurHari() {
        return umurHari;
    }

    public void setUmurHari(String umurHari) {
        this.umurHari = umurHari;
    }

    public String getTarifPoliEks() {
        return tarifPoliEks;
    }

    public void setTarifPoliEks(String tarifPoliEks) {
        this.tarifPoliEks = tarifPoliEks;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNomorSep() {
        return nomorSep;
    }

    public void setNomorSep(String nomorSep) {
        this.nomorSep = nomorSep;
    }

    public String getNomorKartu() {
        return nomorKartu;
    }

    public void setNomorKartu(String nomorKartu) {
        this.nomorKartu = nomorKartu;
    }

    public String getPayorId() {
        return payorId;
    }

    public void setPayorId(String payorId) {
        this.payorId = payorId;
    }

    public String getPayorNm() {
        return payorNm;
    }

    public void setPayorNm(String payorNm) {
        this.payorNm = payorNm;
    }

    public String getCoderNm() {
        return coderNm;
    }

    public void setCoderNm(String coderNm) {
        this.coderNm = coderNm;
    }

    public String getCoderNik() {
        return coderNik;
    }

    public void setCoderNik(String coderNik) {
        this.coderNik = coderNik;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public String getHospitalAdmissionId() {
        return hospitalAdmissionId;
    }

    public void setHospitalAdmissionId(String hospitalAdmissionId) {
        this.hospitalAdmissionId = hospitalAdmissionId;
    }

    public String getGroupingCount() {
        return groupingCount;
    }

    public void setGroupingCount(String groupingCount) {
        this.groupingCount = groupingCount;
    }

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

    public String getInaCbgVersion() {
        return inaCbgVersion;
    }

    public void setInaCbgVersion(String inaCbgVersion) {
        this.inaCbgVersion = inaCbgVersion;
    }

    public List<Grouping1TarifAltResponse> getGrouping1TarifAltResponseList() {
        return grouping1TarifAltResponseList;
    }

    public void setGrouping1TarifAltResponseList(List<Grouping1TarifAltResponse> grouping1TarifAltResponseList) {
        this.grouping1TarifAltResponseList = grouping1TarifAltResponseList;
    }

    public String getKemenkesDcStatusCd() {
        return kemenkesDcStatusCd;
    }

    public void setKemenkesDcStatusCd(String kemenkesDcStatusCd) {
        this.kemenkesDcStatusCd = kemenkesDcStatusCd;
    }

    public String getKemenkesDcSentDttm() {
        return kemenkesDcSentDttm;
    }

    public void setKemenkesDcSentDttm(String kemenkesDcSentDttm) {
        this.kemenkesDcSentDttm = kemenkesDcSentDttm;
    }

    public String getBpjsDcStatusCd() {
        return bpjsDcStatusCd;
    }

    public void setBpjsDcStatusCd(String bpjsDcStatusCd) {
        this.bpjsDcStatusCd = bpjsDcStatusCd;
    }

    public String getBpjsDcSentDttm() {
        return bpjsDcSentDttm;
    }

    public void setBpjsDcSentDttm(String bpjsDcSentDttm) {
        this.bpjsDcSentDttm = bpjsDcSentDttm;
    }

    public String getKlaimStatusCd() {
        return klaimStatusCd;
    }

    public void setKlaimStatusCd(String klaimStatusCd) {
        this.klaimStatusCd = klaimStatusCd;
    }

    public String getBpjsKlaimStatusCd() {
        return bpjsKlaimStatusCd;
    }

    public void setBpjsKlaimStatusCd(String bpjsKlaimStatusCd) {
        this.bpjsKlaimStatusCd = bpjsKlaimStatusCd;
    }

    public String getBpjsKlaimStatusNm() {
        return bpjsKlaimStatusNm;
    }

    public void setBpjsKlaimStatusNm(String bpjsKlaimStatusNm) {
        this.bpjsKlaimStatusNm = bpjsKlaimStatusNm;
    }
}
