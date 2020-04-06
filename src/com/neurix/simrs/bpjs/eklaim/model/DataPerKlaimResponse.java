package com.neurix.simrs.bpjs.eklaim.model;

import java.util.List;

public class DataPerKlaimResponse {
    private String kodeRs;
    private String kelasRs;
    private Integer kelasRawat;
    private String kodeTarif;
    private Integer jenisRawat;
    private String tglMasuk;
    private String tglPulang;
    private String tglLahir;
    private String beratLahir;
    private Integer gender;
    private Integer dischargeStatus;
    private String diagnosa;
    private String procedure;
    private Integer adlSubAcute;
    private Integer adlChronic;

    private Integer tarifRsProsedurNonBedah;
    private Integer tarifRsProsedurBedah;
    private Integer tarifRsKonsultasi;
    private Integer tarifRsTenagaAhli;
    private Integer tarifRsKeperawatan;
    private Integer tarifRsPenunjang;
    private Integer tarifRsRadiologi;
    private Integer tarifRsLaboratorium;
    private Integer tarifRsPelayananDarah;
    private Integer tarifRsRehabilitasi;
    private Integer tarifRsKamar;
    private Integer tarifRsRawatIntensif;
    private Integer tarifRsObat;
    private Integer tarifRsObatKronis;
    private Integer tarifRsObatKemotrapis;
    private Integer tarifRsAlkes;
    private Integer tarifRsBmhp;
    private Integer tarifRsSewaAlat;

    private String los;
    private Integer icuIndicator;
    private String icuLos;
    private String ventilatorHour;
    private String upgradeClassInd;
    private String upgradeClassClass;
    private String upgradeClassLos;
    private String addPaymenPct;
    private String addPaymentAmt;
    private String namaPasien;
    private String nomorRm;
    private Integer umurTahun;
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

    public Integer getKelasRawat() {
        return kelasRawat;
    }

    public void setKelasRawat(Integer kelasRawat) {
        this.kelasRawat = kelasRawat;
    }

    public String getKodeTarif() {
        return kodeTarif;
    }

    public void setKodeTarif(String kodeTarif) {
        this.kodeTarif = kodeTarif;
    }

    public Integer getJenisRawat() {
        return jenisRawat;
    }

    public void setJenisRawat(Integer jenisRawat) {
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDischargeStatus() {
        return dischargeStatus;
    }

    public void setDischargeStatus(Integer dischargeStatus) {
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

    public Integer getAdlSubAcute() {
        return adlSubAcute;
    }

    public void setAdlSubAcute(Integer adlSubAcute) {
        this.adlSubAcute = adlSubAcute;
    }

    public Integer getAdlChronic() {
        return adlChronic;
    }

    public void setAdlChronic(Integer adlChronic) {
        this.adlChronic = adlChronic;
    }

    public Integer getTarifRsProsedurNonBedah() {
        return tarifRsProsedurNonBedah;
    }

    public void setTarifRsProsedurNonBedah(Integer tarifRsProsedurNonBedah) {
        this.tarifRsProsedurNonBedah = tarifRsProsedurNonBedah;
    }

    public Integer getTarifRsProsedurBedah() {
        return tarifRsProsedurBedah;
    }

    public void setTarifRsProsedurBedah(Integer tarifRsProsedurBedah) {
        this.tarifRsProsedurBedah = tarifRsProsedurBedah;
    }

    public Integer getTarifRsKonsultasi() {
        return tarifRsKonsultasi;
    }

    public void setTarifRsKonsultasi(Integer tarifRsKonsultasi) {
        this.tarifRsKonsultasi = tarifRsKonsultasi;
    }

    public Integer getTarifRsTenagaAhli() {
        return tarifRsTenagaAhli;
    }

    public void setTarifRsTenagaAhli(Integer tarifRsTenagaAhli) {
        this.tarifRsTenagaAhli = tarifRsTenagaAhli;
    }

    public Integer getTarifRsKeperawatan() {
        return tarifRsKeperawatan;
    }

    public void setTarifRsKeperawatan(Integer tarifRsKeperawatan) {
        this.tarifRsKeperawatan = tarifRsKeperawatan;
    }

    public Integer getTarifRsPenunjang() {
        return tarifRsPenunjang;
    }

    public void setTarifRsPenunjang(Integer tarifRsPenunjang) {
        this.tarifRsPenunjang = tarifRsPenunjang;
    }

    public Integer getTarifRsRadiologi() {
        return tarifRsRadiologi;
    }

    public void setTarifRsRadiologi(Integer tarifRsRadiologi) {
        this.tarifRsRadiologi = tarifRsRadiologi;
    }

    public Integer getTarifRsLaboratorium() {
        return tarifRsLaboratorium;
    }

    public void setTarifRsLaboratorium(Integer tarifRsLaboratorium) {
        this.tarifRsLaboratorium = tarifRsLaboratorium;
    }

    public Integer getTarifRsPelayananDarah() {
        return tarifRsPelayananDarah;
    }

    public void setTarifRsPelayananDarah(Integer tarifRsPelayananDarah) {
        this.tarifRsPelayananDarah = tarifRsPelayananDarah;
    }

    public Integer getTarifRsRehabilitasi() {
        return tarifRsRehabilitasi;
    }

    public void setTarifRsRehabilitasi(Integer tarifRsRehabilitasi) {
        this.tarifRsRehabilitasi = tarifRsRehabilitasi;
    }

    public Integer getTarifRsKamar() {
        return tarifRsKamar;
    }

    public void setTarifRsKamar(Integer tarifRsKamar) {
        this.tarifRsKamar = tarifRsKamar;
    }

    public Integer getTarifRsRawatIntensif() {
        return tarifRsRawatIntensif;
    }

    public void setTarifRsRawatIntensif(Integer tarifRsRawatIntensif) {
        this.tarifRsRawatIntensif = tarifRsRawatIntensif;
    }

    public Integer getTarifRsObat() {
        return tarifRsObat;
    }

    public void setTarifRsObat(Integer tarifRsObat) {
        this.tarifRsObat = tarifRsObat;
    }

    public Integer getTarifRsObatKronis() {
        return tarifRsObatKronis;
    }

    public void setTarifRsObatKronis(Integer tarifRsObatKronis) {
        this.tarifRsObatKronis = tarifRsObatKronis;
    }

    public Integer getTarifRsObatKemotrapis() {
        return tarifRsObatKemotrapis;
    }

    public void setTarifRsObatKemotrapis(Integer tarifRsObatKemotrapis) {
        this.tarifRsObatKemotrapis = tarifRsObatKemotrapis;
    }

    public Integer getTarifRsAlkes() {
        return tarifRsAlkes;
    }

    public void setTarifRsAlkes(Integer tarifRsAlkes) {
        this.tarifRsAlkes = tarifRsAlkes;
    }

    public Integer getTarifRsBmhp() {
        return tarifRsBmhp;
    }

    public void setTarifRsBmhp(Integer tarifRsBmhp) {
        this.tarifRsBmhp = tarifRsBmhp;
    }

    public Integer getTarifRsSewaAlat() {
        return tarifRsSewaAlat;
    }

    public void setTarifRsSewaAlat(Integer tarifRsSewaAlat) {
        this.tarifRsSewaAlat = tarifRsSewaAlat;
    }

    public String getLos() {
        return los;
    }

    public void setLos(String los) {
        this.los = los;
    }

    public Integer getIcuIndicator() {
        return icuIndicator;
    }

    public void setIcuIndicator(Integer icuIndicator) {
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

    public Integer getUmurTahun() {
        return umurTahun;
    }

    public void setUmurTahun(Integer umurTahun) {
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
