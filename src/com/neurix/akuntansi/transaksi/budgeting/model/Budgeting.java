package com.neurix.akuntansi.transaksi.budgeting.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by reza on 29/04/20.
 */
public class Budgeting {

    private String idBudgeting;
    private String noBudgeting;
    private String branchId;
    private String tahun;
    private String rekeningId;
    private String status;
    private BigDecimal nilaiTotal;
    private BigDecimal semester1;
    private BigDecimal semester2;
    private BigDecimal quartal1;
    private BigDecimal quartal2;
    private BigDecimal quartal3;
    private BigDecimal quartal4;
    private BigDecimal selisih;
    private String approveFlag;
    private String approveWho;
    private Timestamp approveTime;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String kodeRekening;
    private String namaKodeRekening;
    private Long level;
    private String parentId;
    private String tipe;
    private BigDecimal nilaiDraf;
    private BigDecimal nilaiFinal;
    private BigDecimal nilaiRevisi;
    private String stLevel;
    private String kodeParent;
    private String namaParent;
    private BigDecimal nilaiAwal;
    private String flagDisable;

    private BigDecimal januari;
    private BigDecimal februari;
    private BigDecimal maret;
    private BigDecimal april;
    private BigDecimal mei;
    private BigDecimal juni;
    private BigDecimal juli;
    private BigDecimal agustus;
    private BigDecimal september;
    private BigDecimal oktober;
    private BigDecimal november;
    private BigDecimal desember;

    private String tipeCoa;
    private String flagDivisi;
    private String flagMaster;
    private String masterId;
    private String masterName;
    private String branchName;
    private String divisi;
    private String coa;
    private String idPengadaan;
    private String bulan;

    private BigDecimal saldoAkhir;
    private BigDecimal selisihSaldoAkhir;
    private String noTrans;

    //for pengajuan biaya
    private BigDecimal budgetSaatIni;
    private BigDecimal budgetSdSaatIni;
    private String stBudgetSaatIni;
    private String stBudgetSdSaatIni;
    private String tipeBudgeting;
    private List<String> rekeningIdList;
    private String flagKp;

    public String getFlagKp() {
        return flagKp;
    }

    public void setFlagKp(String flagKp) {
        this.flagKp = flagKp;
    }

    public List<String> getRekeningIdList() {
        return rekeningIdList;
    }

    public void setRekeningIdList(List<String> rekeningIdList) {
        this.rekeningIdList = rekeningIdList;
    }

    public String getTipeBudgeting() {
        return tipeBudgeting;
    }

    public void setTipeBudgeting(String tipeBudgeting) {
        this.tipeBudgeting = tipeBudgeting;
    }

    public BigDecimal getBudgetSaatIni() {
        return budgetSaatIni;
    }

    public void setBudgetSaatIni(BigDecimal budgetSaatIni) {
        this.budgetSaatIni = budgetSaatIni;
    }

    public BigDecimal getBudgetSdSaatIni() {
        return budgetSdSaatIni;
    }

    public void setBudgetSdSaatIni(BigDecimal budgetSdSaatIni) {
        this.budgetSdSaatIni = budgetSdSaatIni;
    }

    public String getStBudgetSaatIni() {
        return stBudgetSaatIni;
    }

    public void setStBudgetSaatIni(String stBudgetSaatIni) {
        this.stBudgetSaatIni = stBudgetSaatIni;
    }

    public String getStBudgetSdSaatIni() {
        return stBudgetSdSaatIni;
    }

    public void setStBudgetSdSaatIni(String stBudgetSdSaatIni) {
        this.stBudgetSdSaatIni = stBudgetSdSaatIni;
    }

    public String getNoTrans() {
        return noTrans;
    }

    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }

    private List<BudgetingPeriode> listPeriode = new ArrayList<>();
    private List<BudgetingDetail> budgetingDetailList = new ArrayList<>();

    public static Comparator<Budgeting> kodeRekeningSorting = new Comparator<Budgeting>() {

        public int compare(Budgeting s1, Budgeting s2) {
            String kdRekening1 = s1.getKodeRekening();
            String kdRekening2 = s2.getKodeRekening();

//            //ascending order
            return kdRekening1.compareTo(kdRekening2);

            //descending order
//            return kdRekening2.compareTo(kdRekening1);
        }};

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getIdPengadaan() {
        return idPengadaan;
    }

    public void setIdPengadaan(String idPengadaan) {
        this.idPengadaan = idPengadaan;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public static Comparator<Budgeting> getKodeRekeningSorting() {
        return kodeRekeningSorting;
    }

    public static void setKodeRekeningSorting(Comparator<Budgeting> kodeRekeningSorting) {
        Budgeting.kodeRekeningSorting = kodeRekeningSorting;
    }

    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
    }

    public String getFlagDisable() {
        return flagDisable;
    }

    public void setFlagDisable(String flagDisable) {
        this.flagDisable = flagDisable;
    }

    public BigDecimal getNilaiAwal() {
        return nilaiAwal;
    }

    public void setNilaiAwal(BigDecimal nilaiAwal) {
        this.nilaiAwal = nilaiAwal;
    }

    public String getIdBudgeting() {
        return idBudgeting;
    }

    public void setIdBudgeting(String idBudgeting) {
        this.idBudgeting = idBudgeting;
    }

    public String getKodeParent() {
        return kodeParent;
    }

    public void setKodeParent(String kodeParent) {
        this.kodeParent = kodeParent;
    }

    public String getNamaParent() {
        return namaParent;
    }

    public void setNamaParent(String namaParent) {
        this.namaParent = namaParent;
    }

    public String getNoBudgeting() {
        return noBudgeting;
    }

    public void setNoBudgeting(String noBudgeting) {
        this.noBudgeting = noBudgeting;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getNilaiTotal() {
        return nilaiTotal;
    }

    public void setNilaiTotal(BigDecimal nilaiTotal) {
        this.nilaiTotal = nilaiTotal;
    }

    public BigDecimal getSemester1() {
        return semester1;
    }

    public void setSemester1(BigDecimal semester1) {
        this.semester1 = semester1;
    }

    public BigDecimal getSemester2() {
        return semester2;
    }

    public void setSemester2(BigDecimal semester2) {
        this.semester2 = semester2;
    }

    public BigDecimal getQuartal1() {
        return quartal1;
    }

    public void setQuartal1(BigDecimal quartal1) {
        this.quartal1 = quartal1;
    }

    public BigDecimal getQuartal2() {
        return quartal2;
    }

    public void setQuartal2(BigDecimal quartal2) {
        this.quartal2 = quartal2;
    }

    public BigDecimal getQuartal3() {
        return quartal3;
    }

    public void setQuartal3(BigDecimal quartal3) {
        this.quartal3 = quartal3;
    }

    public BigDecimal getQuartal4() {
        return quartal4;
    }

    public void setQuartal4(BigDecimal quartal4) {
        this.quartal4 = quartal4;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getApproveWho() {
        return approveWho;
    }

    public void setApproveWho(String approveWho) {
        this.approveWho = approveWho;
    }

    public Timestamp getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Timestamp approveTime) {
        this.approveTime = approveTime;
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

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getNamaKodeRekening() {
        return namaKodeRekening;
    }

    public void setNamaKodeRekening(String namaKodeRekening) {
        this.namaKodeRekening = namaKodeRekening;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getSelisih() {
        return selisih;
    }

    public void setSelisih(BigDecimal selisih) {
        this.selisih = selisih;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public BigDecimal getNilaiDraf() {
        return nilaiDraf;
    }

    public void setNilaiDraf(BigDecimal nilaiDraf) {
        this.nilaiDraf = nilaiDraf;
    }

    public BigDecimal getNilaiFinal() {
        return nilaiFinal;
    }

    public void setNilaiFinal(BigDecimal nilaiFinal) {
        this.nilaiFinal = nilaiFinal;
    }

    public BigDecimal getNilaiRevisi() {
        return nilaiRevisi;
    }

    public void setNilaiRevisi(BigDecimal nilaiRevisi) {
        this.nilaiRevisi = nilaiRevisi;
    }

    public String getStLevel() {
        return stLevel;
    }

    public void setStLevel(String stLevel) {
        this.stLevel = stLevel;
    }

    public BigDecimal getJanuari() {
        return januari;
    }

    public void setJanuari(BigDecimal januari) {
        this.januari = januari;
    }

    public BigDecimal getFebruari() {
        return februari;
    }

    public void setFebruari(BigDecimal februari) {
        this.februari = februari;
    }

    public BigDecimal getMaret() {
        return maret;
    }

    public void setMaret(BigDecimal maret) {
        this.maret = maret;
    }

    public BigDecimal getApril() {
        return april;
    }

    public void setApril(BigDecimal april) {
        this.april = april;
    }

    public BigDecimal getMei() {
        return mei;
    }

    public void setMei(BigDecimal mei) {
        this.mei = mei;
    }

    public BigDecimal getJuni() {
        return juni;
    }

    public void setJuni(BigDecimal juni) {
        this.juni = juni;
    }

    public BigDecimal getJuli() {
        return juli;
    }

    public void setJuli(BigDecimal juli) {
        this.juli = juli;
    }

    public BigDecimal getAgustus() {
        return agustus;
    }

    public void setAgustus(BigDecimal agustus) {
        this.agustus = agustus;
    }

    public BigDecimal getSeptember() {
        return september;
    }

    public void setSeptember(BigDecimal september) {
        this.september = september;
    }

    public BigDecimal getOktober() {
        return oktober;
    }

    public void setOktober(BigDecimal oktober) {
        this.oktober = oktober;
    }

    public BigDecimal getNovember() {
        return november;
    }

    public void setNovember(BigDecimal november) {
        this.november = november;
    }

    public BigDecimal getDesember() {
        return desember;
    }

    public void setDesember(BigDecimal desember) {
        this.desember = desember;
    }

    public String getTipeCoa() {
        return tipeCoa;
    }

    public void setTipeCoa(String tipeCoa) {
        this.tipeCoa = tipeCoa;
    }

    public String getFlagDivisi() {
        return flagDivisi;
    }

    public void setFlagDivisi(String flagDivisi) {
        this.flagDivisi = flagDivisi;
    }

    public String getFlagMaster() {
        return flagMaster;
    }

    public void setFlagMaster(String flagMaster) {
        this.flagMaster = flagMaster;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public List<BudgetingPeriode> getListPeriode() {
        return listPeriode;
    }

    public void setListPeriode(List<BudgetingPeriode> listPeriode) {
        this.listPeriode = listPeriode;
    }

    public List<BudgetingDetail> getBudgetingDetailList() {
        return budgetingDetailList;
    }

    public void setBudgetingDetailList(List<BudgetingDetail> budgetingDetailList) {
        this.budgetingDetailList = budgetingDetailList;
    }

    public BigDecimal getSaldoAkhir() {
        return saldoAkhir;
    }

    public void setSaldoAkhir(BigDecimal saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }

    public BigDecimal getSelisihSaldoAkhir() {
        return selisihSaldoAkhir;
    }

    public void setSelisihSaldoAkhir(BigDecimal selisihSaldoAkhir) {
        this.selisihSaldoAkhir = selisihSaldoAkhir;
    }
}
